package com.iff.edu.br.delivery.flash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iff.edu.br.delivery.flash.model.Entregador;
import com.iff.edu.br.delivery.flash.service.EntregadorService;

@RestController
@RequestMapping("/entregadores")
public class EntregadorController {

    @Autowired
    private EntregadorService entregadorService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Entregador> cadastrarEntregador(@ModelAttribute Entregador entregador) {
        Entregador novoEntregador = entregadorService.cadastrarEntregador(entregador);
        return ResponseEntity.ok(novoEntregador);
    }
}