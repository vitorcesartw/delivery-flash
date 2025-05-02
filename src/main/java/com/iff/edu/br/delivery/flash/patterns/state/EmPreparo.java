package com.iff.edu.br.delivery.flash.patterns.state;
import com.iff.edu.br.delivery.flash.model.Pedido;

public class EmPreparo implements EstadoPedido {
    @Override
    public void proximoEstado(Pedido pedido) {
        pedido.setEstado(new SaiuParaEntrega());
        System.out.println("Pedido saiu para entrega.");
    }

    @Override
    public void cancelarPedido(Pedido pedido) {
        pedido.setEstado(new Cancelado());
        System.out.println("Pedido foi cancelado.");
    }

    @Override
    public String getNomeEstado() {
        return "Em Preparo";
    }
}
