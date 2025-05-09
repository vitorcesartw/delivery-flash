package com.iff.edu.br.delivery.flash.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iff.edu.br.delivery.flash.model.Cliente;
import com.iff.edu.br.delivery.flash.repository.ClienteRepository;
import com.iff.edu.br.delivery.flash.model.Restaurante;
import com.iff.edu.br.delivery.flash.repository.RestauranteRepository;

@Service
public class RestauranteService {
    @Autowired
    private RestauranteRepository restauranteRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    
    

    public Restaurante cadastrarRestaurante(Restaurante restaurante, Long clienteId) {
        // Buscar o cliente pelo ID
        Cliente cliente = clienteRepository.findById(clienteId)
            .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado!"));

        // Salvar o restaurante
        Restaurante novoRestaurante = restauranteRepository.save(restaurante);

        // Vincular o restaurante ao cliente
        cliente.getRestaurantes().add(novoRestaurante);
        clienteRepository.save(cliente);

        return novoRestaurante;
    }


    public List<Restaurante> listarRestaurantes() {
        return restauranteRepository.findAll();
    }

    public Restaurante buscarRestaurantePorId(Long id) {
        return restauranteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Restaurante não encontrado!"));
    }
    
    public List<Restaurante> listarRestaurantesDoCliente(Long clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId)
            .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado!"));

        return cliente.getRestaurantes();
    }
    public List<Restaurante> buscarPorCliente(Long clienteId) {
        return restauranteRepository.findByClienteId(clienteId);
    }
    
    public List<Restaurante> buscarRestaurantesPorIds(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return Collections.emptyList();
        }
        return restauranteRepository.findAllById(ids);
    }
}