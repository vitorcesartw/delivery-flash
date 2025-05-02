package com.iff.edu.br.delivery.flash.patterns.state;
import com.iff.edu.br.delivery.flash.model.Pedido;

public class Cancelado implements EstadoPedido {
    @Override
    public void proximoEstado(Pedido pedido) {
        System.out.println("Pedido está cancelado e não pode avançar.");
    }

    @Override
    public void cancelarPedido(Pedido pedido) {
        System.out.println("Pedido já está cancelado.");
    }

    @Override
    public String getNomeEstado() {
        return "Cancelado";
    }
}