package com.iff.edu.br.delivery.flash.patterns.state;
import com.iff.edu.br.delivery.flash.model.Pedido;

public class Entregue implements EstadoPedido {
    @Override
    public void proximoEstado(Pedido pedido) {
        System.out.println("Pedido já foi entregue, não pode avançar.");
    }

    @Override
    public void cancelarPedido(Pedido pedido) {
        System.out.println("Pedido já foi entregue, não pode ser cancelado.");
    }

    @Override
    public String getNomeEstado() {
        return "Entregue";
    }
}