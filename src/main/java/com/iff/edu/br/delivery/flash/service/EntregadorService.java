package com.iff.edu.br.delivery.flash.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iff.edu.br.delivery.flash.model.Entregador;
import com.iff.edu.br.delivery.flash.repository.EntregadorRepository;

@Service
public class EntregadorService {

    @Autowired
    private EntregadorRepository entregadorRepository;

    public Entregador cadastrarEntregador(Entregador entregador) {
        // Validações básicas
        if (entregador.getNome() == null || entregador.getNome().isEmpty()) {
            throw new IllegalArgumentException("O nome do entregador é obrigatório!");
        }
        if (entregador.getTelefone() == null || entregador.getTelefone().isEmpty()) {
            throw new IllegalArgumentException("O telefone é obrigatório!");
        }
        if (entregador.getAreaAtuacao() == null || entregador.getAreaAtuacao().isEmpty()) {
            throw new IllegalArgumentException("A área de atuação é obrigatória!");
        }
        if (entregador.getMeioTransporte() == null || entregador.getMeioTransporte().isEmpty()) {
            throw new IllegalArgumentException("O meio de transporte é obrigatório!");
        }

        // Gerar atributos automáticos
        entregador.setCnh("CNH-" + System.currentTimeMillis()); // CNH gerada automaticamente
        entregador.setDataCadastro(java.time.LocalDate.now()); // Data atual configurada automaticamente
        entregador.setEntregasRealizadas(0); // Inicialmente nenhuma entrega

        return entregadorRepository.save(entregador); // Salva no banco
    }
}