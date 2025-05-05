package com.iff.edu.br.delivery.flash.dto;

import java.util.List;

public class CriarPedidoDTO {
    private Long idCliente;
    private Long idRestaurante;
    private PagamentoDTO pagamento;  // Agora é um PagamentoDTO
    private List<ItemPedidoDTO> itens;

    // Getters e Setters
    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Long getIdRestaurante() {
        return idRestaurante;
    }

    public void setIdRestaurante(Long idRestaurante) {
        this.idRestaurante = idRestaurante;
    }

    public PagamentoDTO getPagamento() {
        return pagamento;
    }

    public void setPagamento(PagamentoDTO pagamento) {
        this.pagamento = pagamento;
    }

    public List<ItemPedidoDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedidoDTO> itens) {
        this.itens = itens;
    }

    public static class ItemPedidoDTO {
        private Long idItemCardapio;
        private int quantidade;

        // Getters e Setters
        public Long getIdItemCardapio() {
            return idItemCardapio;
        }

        public void setIdItemCardapio(Long idItemCardapio) {
            this.idItemCardapio = idItemCardapio;
        }

        public int getQuantidade() {
            return quantidade;
        }

        public void setQuantidade(int quantidade) {
            this.quantidade = quantidade;
        }
    }

    public static class PagamentoDTO {
        private String tipo; // PIX, CARTAO, DINHEIRO
        private String chavePix; // Para PIX
        private String numeroCartao; // Para cartão
        private String nomeTitular;
        private String validade;
        private String cvv;
        private Boolean trocoNecessario; // Para dinheiro
        private Float valorTroco;

        // Getters e Setters
        public String getTipo() {
            return tipo;
        }

        public void setTipo(String tipo) {
            this.tipo = tipo;
        }

        public String getChavePix() {
            return chavePix;
        }

        public void setChavePix(String chavePix) {
            this.chavePix = chavePix;
        }

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

        public Boolean getTrocoNecessario() {
            return trocoNecessario;
        }

        public void setTrocoNecessario(Boolean trocoNecessario) {
            this.trocoNecessario = trocoNecessario;
        }

        public Float getValorTroco() {
            return valorTroco;
        }

        public void setValorTroco(Float valorTroco) {
            this.valorTroco = valorTroco;
        }
    }
}