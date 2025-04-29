package com.iff.edu.br.delivery.flash.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iff.edu.br.delivery.flash.model.ItemCardapio;
import com.iff.edu.br.delivery.flash.model.Restaurante;

public interface ItemCardapioRepository extends JpaRepository<ItemCardapio, Long> {
    List<ItemCardapio> findByVisivelTrue();
    List<ItemCardapio> findByCategoria(String categoria);
	List<ItemCardapio> findByRestauranteId(Long restauranteId);
	List<ItemCardapio> findByRestaurante(Restaurante restaurante);
}