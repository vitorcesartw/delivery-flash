package com.iff.edu.br.delivery.flash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iff.edu.br.delivery.flash.model.ItemCardapio;
import com.iff.edu.br.delivery.flash.service.ItemCardapioService;

@RestController
@RequestMapping("/cardapio")
public class ItemCardapioController {

    @Autowired
    private ItemCardapioService itemCardapioService;

    @PostMapping("/cadastrar")
    public ResponseEntity<ItemCardapio> cadastrarItem(@ModelAttribute ItemCardapio itemCardapio) {
        ItemCardapio novoItem = itemCardapioService.cadastrarItem(itemCardapio);
        return ResponseEntity.ok(novoItem);
    }
}
