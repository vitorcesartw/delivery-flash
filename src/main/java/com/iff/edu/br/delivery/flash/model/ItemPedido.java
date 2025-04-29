package com.iff.edu.br.delivery.flash.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ItemCardapio itemCardapio; // Relação com o item do cardápio

    private int quantidade;
    private float precoTotal; // Quantidade * preço do ItemCardapio

    // Getters e Setters
}