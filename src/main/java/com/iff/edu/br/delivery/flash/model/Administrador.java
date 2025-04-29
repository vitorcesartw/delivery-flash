package com.iff.edu.br.delivery.flash.model;

import jakarta.persistence.Entity;


@Entity
public class Administrador extends Cliente {
    private String nivelAcesso; // Ex.: "Administrador Geral"

    // Métodos específicos
    public void gerenciarCadastro() {
        // Lógica específica para o administrador
    }

    public void resolverDisputa() {
        // Lógica para resolver disputas
    }

    // Getters e Setters adicionais
}
