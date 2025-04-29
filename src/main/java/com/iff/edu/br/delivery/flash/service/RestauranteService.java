package com.iff.edu.br.delivery.flash.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iff.edu.br.delivery.flash.model.Restaurante;
import com.iff.edu.br.delivery.flash.repository.RestauranteRepository;

@Service
public class RestauranteService {
    @Autowired
    private RestauranteRepository restauranteRepository;
    
    

    public Restaurante cadastrarRestaurante(Restaurante restaurante) {
        // Validações básicas antes de salvar
        if (restaurante.getNome() == null || restaurante.getNome().isEmpty()) {
            throw new IllegalArgumentException("O nome do restaurante é obrigatório!");
        }
        if (restaurante.getEndereco() == null || restaurante.getEndereco().isEmpty()) {
            throw new IllegalArgumentException("O endereço do restaurante é obrigatório!");
        }

        return restauranteRepository.save(restaurante); // Salva no banco de dados
    }


    public List<Restaurante> listarRestaurantes() {
        return restauranteRepository.findAll();
    }

    public Restaurante buscarRestaurantePorId(Long id) {
        return restauranteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Restaurante não encontrado!"));
    }
}