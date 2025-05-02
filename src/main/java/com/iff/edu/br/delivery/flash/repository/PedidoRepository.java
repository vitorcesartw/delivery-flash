package com.iff.edu.br.delivery.flash.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iff.edu.br.delivery.flash.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByEstadoNome(String estadoNome); // Alteração aqui!
    List<Pedido> findByClienteId(Long clienteId);
}