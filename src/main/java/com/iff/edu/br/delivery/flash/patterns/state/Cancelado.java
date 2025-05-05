package com.iff.edu.br.delivery.flash.patterns.state;
import com.iff.edu.br.delivery.flash.model.Pedido;

public class Cancelado implements EstadoPedido {
    private final Pedido pedido;

    public Cancelado(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public void pagar() {
        throw new IllegalStateException("Pedido cancelado não pode ser pago");
    }

    @Override
    public void preparar() {
        throw new IllegalStateException("Pedido cancelado não pode ser preparado");
    }

    @Override
    public void entregar() {
        throw new IllegalStateException("Pedido cancelado não pode ser entregue");
    }

    @Override
    public void cancelar() {
        throw new IllegalStateException("Pedido já está cancelado");
    }

    @Override
    public String getNomeStatus() {
        return "Cancelado";
    }
}