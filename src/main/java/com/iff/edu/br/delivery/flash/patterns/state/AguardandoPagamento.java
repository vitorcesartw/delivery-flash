package com.iff.edu.br.delivery.flash.patterns.state;

import com.iff.edu.br.delivery.flash.model.Pedido;

public class AguardandoPagamento implements EstadoPedido {
    @Override
    public void proximoEstado(Pedido pedido) {
        pedido.setEstado(new EmPreparo());
        System.out.println("Pedido agora está em preparo.");
    }

    @Override
    public void cancelarPedido(Pedido pedido) {
        pedido.setEstado(new Cancelado());
        System.out.println("Pedido foi cancelado.");
    }

    @Override
    public String getNomeEstado() {
        return "Aguardando Pagamento";
    }
}