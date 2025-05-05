package com.iff.edu.br.delivery.flash.pagamento;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue("DINHEIRO")
public class Dinheiro extends Pagamento {
    private boolean trocoNecessario;
    private BigDecimal valorTroco;

    @Override
    public void processarPagamento() {
        System.out.println("Processando pagamento em Dinheiro");
        if (trocoNecessario) {
            System.out.println("Troco necessário: R$ " + valorTroco);
        }
    }

    // Getters e Setters
    public boolean isTrocoNecessario() {
        return trocoNecessario;
    }

    public void setTrocoNecessario(boolean trocoNecessario) {
        this.trocoNecessario = trocoNecessario;
    }

    public BigDecimal getValorTroco() {
        return valorTroco;
    }

    public void setValorTroco(BigDecimal valorTroco) {
        this.valorTroco = valorTroco;
    }
}