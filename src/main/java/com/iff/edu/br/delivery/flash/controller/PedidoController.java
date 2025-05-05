package com.iff.edu.br.delivery.flash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iff.edu.br.delivery.flash.dto.CriarPedidoDTO;
import com.iff.edu.br.delivery.flash.model.Pedido;
import com.iff.edu.br.delivery.flash.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;
    
    @GetMapping
    public String listarPedidos() {
        return "Listagem de pedidos"; // Lógica real será adicionada.
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getPedido(@PathVariable Long id) {
        Pedido pedido = pedidoService.buscarPedidoComEstado(id);
        return ResponseEntity.ok(pedido);
    }

    @PostMapping
    public ResponseEntity<Pedido> criarPedido(@RequestBody CriarPedidoDTO dto) {
        // Implementar lógica para criar pedido e itens
        Pedido pedido = pedidoService.criarPedido(dto);
        return ResponseEntity.ok(pedido);
    }
    
    @PostMapping("/{id}/avancar")
    public ResponseEntity<String> avancarEstado(@PathVariable Long id) {

        return ResponseEntity.ok("Pedido avançado para o próximo estado.");
    }

    @PostMapping("/{id}/cancelar")
    public ResponseEntity<String> cancelarPedido(@PathVariable Long id) {

        return ResponseEntity.ok("Pedido cancelado.");
    }
}