package com.iff.edu.br.delivery.flash.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iff.edu.br.delivery.flash.model.Entregador;
import com.iff.edu.br.delivery.flash.model.Pedido;
import com.iff.edu.br.delivery.flash.repository.EntregadorRepository;
import com.iff.edu.br.delivery.flash.repository.PedidoRepository;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private EntregadorRepository entregadorRepository;

    public void atualizarStatusPedido(Long pedidoId, String novoStatus) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
            .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        pedido.setStatus(novoStatus);
        pedidoRepository.save(pedido);
    }

    public void designarEntregador(Long pedidoId, Long entregadorId) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
            .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        Entregador entregador = entregadorRepository.findById(entregadorId)
            .orElseThrow(() -> new RuntimeException("Entregador não encontrado"));

        pedido.setEntregador(entregador);
        pedido.setStatus("Em Rota");
        pedidoRepository.save(pedido);
    }
}