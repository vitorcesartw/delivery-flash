package com.iff.edu.br.delivery.flash.pagamento;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CARTAO")
public class Credito extends Pagamento {
    private String numeroCartao;
    private String nomeTitular;
    private String validade;
    private String cvv;

    @Override
    public void processarPagamento() {
        System.out.println("Processando pagamento via Cartão de Crédito");
        System.out.println("Cartão final: " + this.numeroCartao.substring(this.numeroCartao.length() - 4));
    }

    // Getters e Setters
    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public String getNomeTitular() {
        return nomeTitular;
    }

    public void setNomeTitular(String nomeTitular) {
        this.nomeTitular = nomeTitular;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
}