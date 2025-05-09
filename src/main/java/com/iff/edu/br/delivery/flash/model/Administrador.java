package com.iff.edu.br.delivery.flash.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;


@Entity
public class Administrador extends Cliente {
    private String nivelAcesso; 


    public void gerenciarCadastro() {

    }

    public void resolverDisputa() {

    }

 
}
