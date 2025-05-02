package com.iff.edu.br.delivery.flash.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iff.edu.br.delivery.flash.model.ItemCardapio;
import com.iff.edu.br.delivery.flash.model.Restaurante;
import com.iff.edu.br.delivery.flash.repository.RestauranteRepository;
import com.iff.edu.br.delivery.flash.service.ItemCardapioService;
import com.iff.edu.br.delivery.flash.service.RestauranteService;

import jakarta.servlet.http.HttpSession;

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
    public ResponseEntity<Restaurante> cadastrarRestaurante(@RequestBody Restaurante restaurante, HttpSession session) {
        Long clienteId = (Long) session.getAttribute("clienteId");

        if (clienteId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        Restaurante novoRestaurante = restauranteService.cadastrarRestaurante(restaurante, clienteId);
        return ResponseEntity.ok(novoRestaurante);
    }

    @GetMapping("/do-cliente/{clienteId}")
    public ResponseEntity<List<Restaurante>> listarRestaurantesDoCliente(@PathVariable Long clienteId) {
        List<Restaurante> restaurantes = restauranteService.listarRestaurantesDoCliente(clienteId);
        return ResponseEntity.ok(restaurantes);
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
    @GetMapping("/vinculados")
    public ResponseEntity<List<Restaurante>> listarRestaurantesVinculados(@RequestParam Long usuarioId) {
    	System.out.println("Recebendo usuarioId: " + usuarioId); // 🔥 Agora concatenamos corretamente a string e o número

        if (usuarioId == null || usuarioId <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.emptyList());
        }

        List<Restaurante> restaurantes = restauranteService.buscarPorCliente(usuarioId);
        return ResponseEntity.ok(restaurantes);
    }
}