package com.iff.edu.br.delivery.flash.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String formaPagamento; // Ex.: Cartão de Crédito, PIX, Dinheiro
    private float valor;

    @OneToOne
    private Pedido pedido; // Relacionamento com o pedido ao qual o pagamento está vinculado

    private boolean pago; // Indica se o pagamento foi confirmado ou não

    // Métodos de Comportamento
    public void confirmarPagamento() {
        this.pago = true; // Confirma o pagamento
    }

    // Getters e Setters
}
