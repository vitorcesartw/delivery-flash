package com.iff.edu.br.delivery.flash.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iff.edu.br.delivery.flash.model.Restaurante;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
    List<Restaurante> findByNomeContaining(String nome);
}