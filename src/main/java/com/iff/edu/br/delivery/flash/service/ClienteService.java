package com.iff.edu.br.delivery.flash.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iff.edu.br.delivery.flash.model.Cliente;
import com.iff.edu.br.delivery.flash.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente cadastrarCliente(Cliente cliente) {
        // Validação básica antes de salvar
        if (cliente.getNome() == null || cliente.getNome().isEmpty()) {
            throw new IllegalArgumentException("O nome do cliente é obrigatório!");
        }
        if (cliente.getCpf() == null || cliente.getCpf().length() != 11) {
            throw new IllegalArgumentException("O CPF deve ter 11 dígitos!");
        }
        if (cliente.getEmail() == null || !cliente.getEmail().contains("@")) {
            throw new IllegalArgumentException("Email inválido!");
        }

        return clienteRepository.save(cliente); // Salva no banco de dados
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
        Cliente cliente = clienteRepository.findByEmail(email);
        if (cliente != null && cliente.getSenha().equals(senha)) {
            return true;
        }
        return false;
    }
}
