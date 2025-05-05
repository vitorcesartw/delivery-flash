package com.iff.edu.br.delivery.flash.patterns.observer;

import com.iff.edu.br.delivery.flash.model.Pedido;

public interface PedidoObserver {
    void notificarAlteracaoPedido(Pedido pedido);
}
