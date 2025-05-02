package com.iff.edu.br.delivery.flash.patterns.state;

import com.iff.edu.br.delivery.flash.model.Pedido;

public interface EstadoPedido {
    void proximoEstado(Pedido pedido);
    void cancelarPedido(Pedido pedido);
    String getNomeEstado();
}