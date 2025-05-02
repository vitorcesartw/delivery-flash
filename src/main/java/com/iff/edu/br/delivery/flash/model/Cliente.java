package com.iff.edu.br.delivery.flash.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinColumn;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // ⚠ Isso permite que todas as subclasses compartilhem a mesma tabela
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING)
public class Cliente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String telefone;
    private String endereco;
    private String cpf;
    private String email;
    private String senha;
    private String tipo_user;
    // Métodos comuns
    public void realizarPedido() {
        // Lógica comum para cliente
    }

    @ManyToMany
    @JoinTable(
        name = "cliente_restaurante",
        joinColumns = @JoinColumn(name = "cliente_id"),
        inverseJoinColumns = @JoinColumn(name = "restaurante_id") // ⚠ Correção aplicada!
    )
    
    private List<Restaurante> restaurantes;
    public void setRestaurantes(List<Restaurante> restaurantes) {
        this.restaurantes = restaurantes;
    }

    public List<Restaurante> getRestaurantes() {
        return restaurantes;
    }
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

	public String getTipo_user() {
		return tipo_user;
	}

	public void setTipo_user(String tipo_user) {
		this.tipo_user = tipo_user;
	}

}