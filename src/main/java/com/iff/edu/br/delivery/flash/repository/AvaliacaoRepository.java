package com.iff.edu.br.delivery.flash.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iff.edu.br.delivery.flash.model.Avaliacao;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
    List<Avaliacao> findByRestauranteId(Long restauranteId);
    List<Avaliacao> findByEntregadorId(Long entregadorId);
}