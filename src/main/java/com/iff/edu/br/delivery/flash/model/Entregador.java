package com.iff.edu.br.delivery.flash.model;

import java.sql.Date;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Entregador {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String nome;
	    private String telefone;
	    private String areaAtuacao;

	    private String meioTransporte; // Moto, Bicicleta, Carro
	    private String status = "Disponível"; // Padrão: Disponível
	    private String cnh; // Gerado automaticamente

	    private LocalDate dataCadastro = LocalDate.now(); // Data gerada automaticamente

	    private Integer entregasRealizadas = 0; // Inicialmente nenhuma entrega



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

    public String getAreaAtuacao() {
        return areaAtuacao;
    }

    public void setAreaAtuacao(String areaAtuacao) {
        this.areaAtuacao = areaAtuacao;
    }

    public String getMeioTransporte() {
        return meioTransporte;
    }

    public void setMeioTransporte(String meioTransporte) {
        this.meioTransporte = meioTransporte;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Integer getEntregasRealizadas() {
        return entregasRealizadas;
    }

    public void setEntregasRealizadas(Integer entregasRealizadas) {
        this.entregasRealizadas = entregasRealizadas;
    }
}
