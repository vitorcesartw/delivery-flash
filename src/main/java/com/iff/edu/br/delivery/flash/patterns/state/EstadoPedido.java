package com.iff.edu.br.delivery.flash.patterns.state;



public interface EstadoPedido {
    public abstract String getNomeStatus();
    public abstract void pagar();
    public abstract void preparar();
    public abstract void entregar();
    public abstract void cancelar();


}