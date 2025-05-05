package com.iff.edu.br.delivery.flash.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iff.edu.br.delivery.flash.model.ItemCardapio;
import com.iff.edu.br.delivery.flash.model.Restaurante;
import com.iff.edu.br.delivery.flash.service.ItemCardapioService;
import com.iff.edu.br.delivery.flash.service.RestauranteService;

@RestController
@RequestMapping("/cardapio")
@CrossOrigin
public class ItemCardapioController {

    @Autowired
    private ItemCardapioService itemCardapioService;
    
    @Autowired
    private RestauranteService restauranteService;

    @PostMapping("/cadastrar")
    public ResponseEntity<ItemCardapio> cadastrarItem(@RequestBody ItemCardapio itemCardapio) {
        ItemCardapio novoItem = itemCardapioService.cadastrarItem(itemCardapio);
        return ResponseEntity.ok(novoItem);
    }
    
    @GetMapping("/restaurantes/listar")
    public ResponseEntity<List<Restaurante>> listarRestaurantes() {
        List<Restaurante> restaurantes = restauranteService.listarRestaurantes();
        return ResponseEntity.ok(restaurantes);
    }
}
