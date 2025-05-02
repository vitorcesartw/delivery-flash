package com.iff.edu.br.delivery.flash.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iff.edu.br.delivery.flash.model.Cliente;
import com.iff.edu.br.delivery.flash.model.Restaurante;
import com.iff.edu.br.delivery.flash.repository.ClienteRepository;
import com.iff.edu.br.delivery.flash.repository.RestauranteRepository;


@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private RestauranteRepository restauranteRepository;


    public Cliente cadastrarCliente(Cliente cliente) {
        // Validações
        if (cliente.getNome() == null || cliente.getNome().isEmpty()) {
            throw new IllegalArgumentException("O nome do cliente é obrigatório!");
        }
        if (cliente.getCpf() == null || cliente.getCpf().length() != 11) {
            throw new IllegalArgumentException("O CPF deve ter 11 dígitos!");
        }
        if (cliente.getEmail() == null || !cliente.getEmail().contains("@")) {
            throw new IllegalArgumentException("Email inválido!");
        }
        if (cliente.getTipo_user() == null) {
            throw new IllegalArgumentException("Tipo de Cliente Errado");
        }
        System.out.println("Valor de dtype recebido: " + cliente.getTipo_user());
        return clienteRepository.save(cliente);
    }
    
    public Cliente vincularRestaurantes(Long clienteId, List<Long> restaurantesIds) {
        Cliente cliente = clienteRepository.findById(clienteId)
            .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado!"));

        List<Restaurante> restaurantes = restauranteRepository.findAllById(restaurantesIds);
        
        cliente.setRestaurantes(restaurantes);
        return clienteRepository.save(cliente);
    }

    
    public Cliente atualizarCliente(Cliente cliente) {
        return clienteRepository.save(cliente); // ⚠ Método adicionado para atualizar cliente
    }

    public List<Cliente> listarClientes() {
        // Retorna todos os clientes cadastrados
        return clienteRepository.findAll();
    }

    public Cliente buscarClientePorId(Long id) {
        // Busca um cliente pelo ID e trata caso não exista
        return clienteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));
    }

    public void deletarCliente(Long id) {
        // Verifica se o cliente existe antes de deletar
        if (!clienteRepository.existsById(id)) {
            throw new RuntimeException("Cliente não encontrado!");
        }
        clienteRepository.deleteById(id);
    }
    public boolean validarLogin(String email, String senha) {
        return clienteRepository.findByEmailAndSenha(email, senha).isPresent(); // Alterado para funcionar corretamente!
    }

    public Cliente autenticarCliente(String email, String senha) {
        return clienteRepository.findByEmailAndSenha(email, senha).orElse(null);
    }
}

