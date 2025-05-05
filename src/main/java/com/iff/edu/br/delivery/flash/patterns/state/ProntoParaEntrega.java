package com.iff.edu.br.delivery.flash.patterns.state;

import com.iff.edu.br.delivery.flash.model.Pedido;

public class ProntoParaEntrega implements EstadoPedido {
    private final Pedido pedido;

    public ProntoParaEntrega(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public void pagar() {
        throw new IllegalStateException("Pedido já está pago");
    }

    @Override
    public void preparar() {
        throw new IllegalStateException("Pedido já está pronto para entrega");
    }

    @Override
    public void entregar() {
        pedido.setEstado(new SaiuParaEntrega(pedido));
    }

    @Override
    public void cancelar() {
        pedido.setEstado(new Cancelado(pedido));
    }

    @Override
    public String getNomeStatus() {
        return "Pronto para Entrega";
    }
}