package com.iff.edu.br.delivery.flash.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @GetMapping
    public String listarPedidos() {
        return "Listagem de pedidos"; // Lógica real será adicionada.
    }

    @PostMapping
    public String criarPedido() {
        return "Pedido criado com sucesso"; // Implementação posterior.
    }
}