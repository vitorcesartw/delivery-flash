package com.iff.edu.br.delivery.flash.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iff.edu.br.delivery.flash.model.Entregador;

public interface EntregadorRepository extends JpaRepository<Entregador, Long> {
    // Métodos customizados podem ser adicionados futuramente, se necessário
}