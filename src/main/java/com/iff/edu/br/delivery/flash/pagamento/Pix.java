package com.iff.edu.br.delivery.flash.pagamento;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PIX") // Valor deve corresponder ao que está no banco
public class Pix extends Pagamento {
    private String chavePix;

    @Override
    public void processarPagamento() {
        // Implementação específica para PIX
        System.out.println("Pagamento via PIX processado com chave: " + chavePix);
    }

    // Getters e Setters
    public String getChavePix() {
        return chavePix;
    }

    public void setChavePix(String chavePix) {
        this.chavePix = chavePix;
    }
    
}