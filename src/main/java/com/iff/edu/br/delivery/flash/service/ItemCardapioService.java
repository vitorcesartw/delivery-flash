package com.iff.edu.br.delivery.flash.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iff.edu.br.delivery.flash.model.ItemCardapio;
import com.iff.edu.br.delivery.flash.repository.ItemCardapioRepository;

@Service
public class ItemCardapioService {
    @Autowired
    private ItemCardapioRepository itemCardapioRepository;

    public ItemCardapio cadastrarItem(ItemCardapio itemCardapio) {
        // Validações antes de salvar
        if (itemCardapio.getNome() == null || itemCardapio.getPreco() <= 0) {
            throw new IllegalArgumentException("Nome e preço do item são obrigatórios e válidos!");
        }
        itemCardapio.setVisivel(true); // Define visibilidade como padrão
        return itemCardapioRepository.save(itemCardapio);
    }
    public List<ItemCardapio> listarPorRestaurante(Long restauranteId) {
        return itemCardapioRepository.findByRestauranteId(restauranteId);
    }
    public ItemCardapio buscarPorId(Long id) {
        return itemCardapioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Item do cardápio não encontrado!"));
    }
}
