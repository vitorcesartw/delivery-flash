package com.iff.edu.br.delivery.flash.pagamento;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import java.math.BigDecimal;

import com.iff.edu.br.delivery.flash.model.Pedido;

import jakarta.persistence.*;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "forma_pagamento") // Corrigido para match com o banco
public abstract class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "forma_pagamento", insertable = false, updatable = false)
    private String formaPagamento; // Campo espelho do discriminador
    private BigDecimal valor;
    private boolean pago = false;

    @OneToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    // Método para confirmar pagamento (movido para a classe base)
    public void confirmarPagamento() {
        this.pago = true;
        this.processarPagamento();
    }

    // Método abstrato que as subclasses devem implementar
    public abstract void processarPagamento();

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}