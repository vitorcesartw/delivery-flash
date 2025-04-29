package com.iff.edu.br.delivery.flash.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iff.edu.br.delivery.flash.model.Avaliacao;
import com.iff.edu.br.delivery.flash.repository.AvaliacaoRepository;

@Service
public class AvaliacaoService {
    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    public Avaliacao salvarAvaliacao(Avaliacao avaliacao) {
        // Validações antes de salvar
        if (avaliacao.getNota() < 1 || avaliacao.getNota() > 5) {
            throw new IllegalArgumentException("A nota deve estar entre 1 e 5!");
        }
        return avaliacaoRepository.save(avaliacao);
    }

    public List<Avaliacao> listarAvaliacoes() {
        return avaliacaoRepository.findAll();
    }

    public List<Avaliacao> listarAvaliacoesPorRestaurante(Long restauranteId) {
        // Método customizado para filtrar por restaurante
        return avaliacaoRepository.findByRestauranteId(restauranteId);
    }
}