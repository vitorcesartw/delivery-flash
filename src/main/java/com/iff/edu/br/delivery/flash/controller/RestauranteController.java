package com.iff.edu.br.delivery.flash.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iff.edu.br.delivery.flash.model.ItemCardapio;
import com.iff.edu.br.delivery.flash.model.Restaurante;
import com.iff.edu.br.delivery.flash.repository.RestauranteRepository;
import com.iff.edu.br.delivery.flash.service.ItemCardapioService;
import com.iff.edu.br.delivery.flash.service.RestauranteService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private ItemCardapioService itemCardapioService; 
    // Cadastrar restaurante
    @PostMapping("/cadastrar")
    public ResponseEntity<Restaurante> cadastrarRestaurante(@RequestBody Restaurante restaurante) {
        Restaurante novoRestaurante = restauranteRepository.save(restaurante);
        return ResponseEntity.ok(novoRestaurante);
    }

    // Listar restaurantes
    @GetMapping
    public ResponseEntity<List<Restaurante>> listarRestaurantes() {
        List<Restaurante> restaurantes = restauranteRepository.findAll();
        return ResponseEntity.ok(restaurantes);
    }

    // Listar cardápio de um restaurante
    @GetMapping("/{id}/cardapio")
    public ResponseEntity<List<ItemCardapio>> listarCardapio(@PathVariable Long id) {
        List<ItemCardapio> cardapio = itemCardapioService.listarPorRestaurante(id);
        return ResponseEntity.ok(cardapio);
    }
}