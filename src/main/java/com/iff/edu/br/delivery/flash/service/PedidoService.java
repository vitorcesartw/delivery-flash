package com.iff.edu.br.delivery.flash.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iff.edu.br.delivery.flash.model.Entregador;
import com.iff.edu.br.delivery.flash.model.Pedido;
import com.iff.edu.br.delivery.flash.patterns.state.AguardandoPagamento;
import com.iff.edu.br.delivery.flash.patterns.state.Cancelado;
import com.iff.edu.br.delivery.flash.patterns.state.EmPreparo;
import com.iff.edu.br.delivery.flash.patterns.state.Entregue;
import com.iff.edu.br.delivery.flash.patterns.state.EstadoPedido;
import com.iff.edu.br.delivery.flash.patterns.state.SaiuParaEntrega;
import com.iff.edu.br.delivery.flash.repository.EntregadorRepository;
import com.iff.edu.br.delivery.flash.repository.PedidoRepository;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private EntregadorRepository entregadorRepository;

    public Pedido buscarPedido(Long pedidoId) {
        Pedido pedido = pedidoRepository.findById(pedidoId).orElseThrow();
        
        // ⚠️ Corrigindo erro: Agora usamos `getEstadoNome()`
        pedido.setEstado(recuperarEstado(pedido.getEstadoNome()));

        return pedido;
    }

    public void avancarEstado(Long pedidoId) {
        Pedido pedido = buscarPedido(pedidoId);
        pedido.avancarEstado();
        pedidoRepository.save(pedido);
    }

    public void cancelarPedido(Long pedidoId) {
        Pedido pedido = buscarPedido(pedidoId);
        pedido.cancelarPedido();
        pedidoRepository.save(pedido);
    }

    private EstadoPedido recuperarEstado(String estadoNome) {
        return switch (estadoNome) {
            case "Aguardando Pagamento" -> new AguardandoPagamento();
            case "Em Preparo" -> new EmPreparo();
            case "Saiu para Entrega" -> new SaiuParaEntrega();
            case "Entregue" -> new Entregue();
            case "Cancelado" -> new Cancelado();
            default -> throw new IllegalStateException("Estado desconhecido: " + estadoNome);
        };
    }
}