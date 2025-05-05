package com.iff.edu.br.delivery.flash.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.iff.edu.br.delivery.flash.dto.CriarPedidoDTO;
import com.iff.edu.br.delivery.flash.model.Cliente;
import com.iff.edu.br.delivery.flash.model.ItemCardapio;
import com.iff.edu.br.delivery.flash.model.ItemPedido;
import com.iff.edu.br.delivery.flash.model.Pedido;
import com.iff.edu.br.delivery.flash.model.Restaurante;
import com.iff.edu.br.delivery.flash.pagamento.Credito;
import com.iff.edu.br.delivery.flash.pagamento.Dinheiro;
import com.iff.edu.br.delivery.flash.pagamento.Pagamento;
import com.iff.edu.br.delivery.flash.pagamento.Pix;
import com.iff.edu.br.delivery.flash.patterns.observer.PedidoObserver;
import com.iff.edu.br.delivery.flash.patterns.state.AguardandoPagamento;
import com.iff.edu.br.delivery.flash.patterns.state.Cancelado;
import com.iff.edu.br.delivery.flash.patterns.state.EmPreparo;
import com.iff.edu.br.delivery.flash.patterns.state.Entregue;
import com.iff.edu.br.delivery.flash.patterns.state.EstadoPedido;
import com.iff.edu.br.delivery.flash.patterns.state.ProntoParaEntrega;
import com.iff.edu.br.delivery.flash.patterns.state.SaiuParaEntrega;
import com.iff.edu.br.delivery.flash.repository.PedidoRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ItemCardapioService itemCardapioService;
    
    @Autowired
    private ClienteService clienteService;
    
    @Autowired
    private RestauranteService restauranteService;
    
    @Qualifier("aguardandoPagamento")
    private EstadoPedido estadoInicial;

    private List<PedidoObserver> observers = new ArrayList<>();

    private EstadoPedido criarEstado(String estadoNome, Pedido pedido) {
        return switch (estadoNome) {
            case "AguardandoPagamento" -> new AguardandoPagamento(pedido);
            case "EmPreparo" -> new EmPreparo(pedido);
            case "ProntoParaEntrega" -> new ProntoParaEntrega(pedido);
            case "SaiuParaEntrega" -> new SaiuParaEntrega(pedido);
            case "Entregue" -> new Entregue(pedido);
            case "Cancelado" -> new Cancelado(pedido);
            default -> throw new IllegalStateException("Estado desconhecido: " + estadoNome);
        };
    }
    
    @PostConstruct
    public void init() {
        pedidoRepository.findAll().forEach(this::carregarEstadoPedido);
    }
    
    private void carregarEstadoPedido(Pedido pedido) {
        if (pedido.getEstadoPersistido() != null) {
            EstadoPedido estado = criarEstado(pedido.getEstadoPersistido(), pedido);
            pedido.setEstado(estado);
        }
    }
    
    @Transactional
    public Pedido buscarPedidoComEstado(Long id) {
        Pedido pedido = pedidoRepository.findById(id).orElseThrow();
        carregarEstadoPedido(pedido);
        return pedido;
    }
    
    @Transactional
    public Pedido criarPedido(CriarPedidoDTO dto) {
        // 1. Validação básica
        if (dto == null || dto.getItens() == null || dto.getItens().isEmpty()) {
            throw new IllegalArgumentException("Dados do pedido inválidos!");
        }

        // 2. Busca entidades relacionadas
        Cliente cliente = clienteService.buscarClientePorId(dto.getIdCliente());
        Restaurante restaurante = restauranteService.buscarRestaurantePorId(dto.getIdRestaurante());

        // 3. Cria o pedido
        Pedido pedido = new Pedido();
        pedido.setData(LocalDateTime.now());
        pedido.setEstado(estadoInicial);
        pedido.setCliente(cliente);
        pedido.setRestaurante(restaurante);

        // 4. Processa itens (o cálculo já é feito no construtor do ItemPedido)
        List<ItemPedido> itens = new ArrayList<>();
        BigDecimal valorTotal = BigDecimal.ZERO;

        for (CriarPedidoDTO.ItemPedidoDTO itemDto : dto.getItens()) {
            ItemCardapio itemCardapio = itemCardapioService.buscarPorId(itemDto.getIdItemCardapio());
            
            ItemPedido itemPedido = new ItemPedido(
                itemCardapio,
                itemDto.getQuantidade(),
                pedido
            );
            
            itens.add(itemPedido);
            valorTotal = valorTotal.add(itemPedido.getPrecoTotal()); // Soma os totais
        }

        pedido.setItens(itens);
        pedido.setValorTotal(valorTotal); // Define o valor total do pedido

        // 5. Cria o pagamento (agora simplificado)
        Pagamento pagamento = criarPagamento(dto.getPagamento(), valorTotal);
        pagamento.setPedido(pedido);
        pedido.setPagamento(pagamento);

        // 6. Persiste o pedido (cascade irá salvar os itens e pagamento)
        return pedidoRepository.save(pedido);
    }

    
    private Pagamento criarPagamento(CriarPedidoDTO.PagamentoDTO pagamentoDTO, BigDecimal valorTotal) {
        Pagamento pagamento = switch (pagamentoDTO.getTipo().toUpperCase()) {
            case "PIX" -> {
                Pix pix = new Pix();
                pix.setChavePix(pagamentoDTO.getChavePix());
                yield pix;
            }
            case "CARTAO" -> {
                Credito credito = new Credito();
                credito.setNumeroCartao(pagamentoDTO.getNumeroCartao());
                credito.setNomeTitular(pagamentoDTO.getNomeTitular());
                credito.setValidade(pagamentoDTO.getValidade());
                credito.setCvv(pagamentoDTO.getCvv());
                yield credito;
            }
            case "DINHEIRO" -> {
                Dinheiro dinheiro = new Dinheiro();
                dinheiro.setTrocoNecessario(
                    pagamentoDTO.getTrocoNecessario() != null && pagamentoDTO.getTrocoNecessario()
                );
                dinheiro.setValorTroco(
                    pagamentoDTO.getValorTroco() != null 
                        ? BigDecimal.valueOf(pagamentoDTO.getValorTroco())
                        : BigDecimal.ZERO
                );
                yield dinheiro;
            }
            default -> throw new IllegalArgumentException("Forma de pagamento inválida: " + pagamentoDTO.getTipo());
        };
        
        pagamento.setValor(valorTotal); // Usa o valorTotal calculado
        return pagamento;
    }
    
    public void addObserver(PedidoObserver observer) {
        observers.add(observer);
    }
    
    private void notificarObservers(Pedido pedido) {
        observers.forEach(obs -> obs.notificarAlteracaoPedido(pedido));
    }
    
    @Transactional
    public void pagarPedido(Long pedidoId) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
            .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        
        // Obtém o pagamento e confirma
        Pagamento pagamento = pedido.getPagamento();
        if (pagamento == null) {
            throw new IllegalStateException("Pedido não possui pagamento associado");
        }
        
        pagamento.confirmarPagamento();
        pedido.getEstado().pagar();
        pedidoRepository.save(pedido);
        
        notificarObservers(pedido);
    }
}