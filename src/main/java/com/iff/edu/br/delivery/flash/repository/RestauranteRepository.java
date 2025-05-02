 package com.iff.edu.br.delivery.flash.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.iff.edu.br.delivery.flash.model.Restaurante;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
    List<Restaurante> findByNomeContaining(String nome);
    
    @Query("SELECT r FROM ClienteRestaurante cr JOIN cr.restaurante r WHERE cr.cliente.id = :clienteId")
    List<Restaurante> findByClienteId(@Param("clienteId") Long clienteId);
}