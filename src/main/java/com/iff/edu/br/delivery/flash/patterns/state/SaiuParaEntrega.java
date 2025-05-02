package com.iff.edu.br.delivery.flash.patterns.state;

import com.iff.edu.br.delivery.flash.model.Pedido;

public class SaiuParaEntrega implements EstadoPedido {
    @Override
    public void proximoEstado(Pedido pedido) {
        pedido.setEstado(new Entregue());
        System.out.println("Pedido foi entregue.");
    }

    @Override
    public void cancelarPedido(Pedido pedido) {
        System.out.println("Pedido não pode ser cancelado, já saiu para entrega.");
    }

    @Override
    public String getNomeEstado() {
        return "Saiu para Entrega";
    }
}