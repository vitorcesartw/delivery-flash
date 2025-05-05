package com.iff.edu.br.delivery.flash.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private int quantidade;
    
    @Column(name = "preco_unitario", nullable = false, precision = 10, scale = 2)
    private BigDecimal precoUnitario;
    
    @Column(name = "preco_total", nullable = false, precision = 10, scale = 2)
    private BigDecimal precoTotal;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_cardapio_id", nullable = false)
    private ItemCardapio itemCardapio;
    
    // Construtor padrão exigido pelo JPA
    public ItemPedido() {}
    
    // Construtor para facilitar a criação
    public ItemPedido(ItemCardapio itemCardapio, int quantidade, Pedido pedido) {
        this.itemCardapio = itemCardapio;
        this.quantidade = quantidade;
        this.pedido = pedido;
        this.precoUnitario = BigDecimal.valueOf(itemCardapio.getPreco());
        this.precoTotal = this.precoUnitario.multiply(BigDecimal.valueOf(quantidade));
    }
    
    // Getters e Setters
    public Long getId() {
        return id;
    }
    
    public int getQuantidade() {
        return quantidade;
    }
    
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
        calcularPrecoTotal();
    }
    
    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }
    
    public BigDecimal getPrecoTotal() {
        return precoTotal;
    }
    
    public Pedido getPedido() {
        return pedido;
    }
    
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    
    public ItemCardapio getItemCardapio() {
        return itemCardapio;
    }
    
    public void setItemCardapio(ItemCardapio itemCardapio) {
        this.itemCardapio = itemCardapio;
        this.precoUnitario = BigDecimal.valueOf(itemCardapio.getPreco());
        calcularPrecoTotal();
    }
    
    private void calcularPrecoTotal() {
        if (precoUnitario != null) {
            this.precoTotal = precoUnitario.multiply(BigDecimal.valueOf(quantidade));
        }
    }
}