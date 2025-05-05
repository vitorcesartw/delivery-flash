package com.iff.edu.br.delivery.flash.patterns.state;
import com.iff.edu.br.delivery.flash.model.Pedido;

public class EmPreparo implements EstadoPedido {
    private final Pedido pedido;

    public EmPreparo(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public void pagar() {
        throw new IllegalStateException("Pedido já está pago e em preparação");
    }

    @Override
    public void preparar() {
        throw new IllegalStateException("Pedido já está em preparo");
    }

    @Override
    public void entregar() {
        throw new IllegalStateException("Pedido precisa ser marcado como pronto antes da entrega");
    }

    @Override
    public void cancelar() {
        pedido.setEstado(new Cancelado(pedido));
    }

    @Override
    public String getNomeStatus() {
        return "Em Preparo";
    }
}