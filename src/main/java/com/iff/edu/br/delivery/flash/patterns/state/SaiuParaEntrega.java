package com.iff.edu.br.delivery.flash.patterns.state;

import com.iff.edu.br.delivery.flash.model.Pedido;

public class SaiuParaEntrega implements EstadoPedido {
    private final Pedido pedido;

    public SaiuParaEntrega(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public void pagar() {
        throw new IllegalStateException("Pedido já está pago e saiu para entrega");
    }

    @Override
    public void preparar() {
        throw new IllegalStateException("Pedido já saiu para entrega");
    }

    @Override
    public void entregar() {
        pedido.setEstado(new Entregue(pedido));
    }

    @Override
    public void cancelar() {
        throw new IllegalStateException("Pedido já saiu para entrega e não pode ser cancelado");
    }

    @Override
    public String getNomeStatus() {
        return "Saiu para Entrega";
    }
}