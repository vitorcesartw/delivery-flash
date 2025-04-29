package com.iff.edu.br.delivery.flash.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ItemCardapio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idItem;

    private String nome;
    private String descricao;
    private float preco;
    private String categoria;
    private boolean visivel; // Campo adicionado
    
    @ManyToOne // Relacionamento com Restaurante
    @JoinColumn(name = "restaurante_id", nullable = false) // Nome da coluna no banco de dados
    private Restaurante restaurante;
    
    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }
    
	public Long getIdItem() {
		return idItem;
	}
	public void setIdItem(Long idItem) {
		this.idItem = idItem;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public float getPreco() {
		return preco;
	}
    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
	public void setPreco(float preco) {
		this.preco = preco;
	}
	public boolean isVisivel() {
		return visivel;
	}
	public void setVisivel(boolean visivel) {
		this.visivel = visivel;
	}

    // Getters e Setters
}