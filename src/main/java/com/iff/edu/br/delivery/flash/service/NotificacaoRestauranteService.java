package com.iff.edu.br.delivery.flash.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iff.edu.br.delivery.flash.model.Pedido;
import com.iff.edu.br.delivery.flash.patterns.observer.PedidoObserver;
import com.iff.edu.br.delivery.flash.patterns.state.EstadoPedido;

@Service
public class NotificacaoRestauranteService implements PedidoObserver {
    
    @Autowired
    private WebSocketService webSocketService;
    
    @Override
    public void notificarAlteracaoPedido(Pedido pedido) {
        String nomeEstado = extrairNomeEstado(pedido.getEstado());
        
        String mensagem = String.format(
            "Pedido #%d | Status: %s | Valor: R$%.2f",
            pedido.getId(),
            nomeEstado,
            pedido.getValorTotal()
        );
        
        webSocketService.enviarNotificacao(
            pedido.getRestaurante().getId(),
            mensagem
        );
    }
    
    private String extrairNomeEstado(EstadoPedido estado) {
        // Obtém o nome simples da classe (sem o pacote)
        String nomeClasse = estado.getClass().getSimpleName();
        
        // Remove "Estado" ou "Pedido" se existir no nome (opcional)
        return nomeClasse.replace("Estado", "")
                       .replace("Pedido", "");
    }
}