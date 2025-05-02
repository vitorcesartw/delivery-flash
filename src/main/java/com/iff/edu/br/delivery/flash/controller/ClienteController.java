package com.iff.edu.br.delivery.flash.controller;

import java.net.URI;
import java.util.List;

import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iff.edu.br.delivery.flash.model.Cliente;
import com.iff.edu.br.delivery.flash.service.ClienteService;

import ch.qos.logback.classic.Logger;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Cliente> cadastrarCliente(@ModelAttribute Cliente cliente) {
        System.out.println("Recebendo cadastro no backend: " + cliente); // ⚠ Logando os dados antes de salvar
        System.out.println("Valor de dtype recebido: " + cliente.getTipo_user());
        if (cliente == null || cliente.getNome() == null) {
            System.out.println("Erro: Cliente recebido como NULL ou sem Nome!");
        }

        Cliente novoCliente = clienteService.cadastrarCliente(cliente);
        return ResponseEntity.ok(novoCliente);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        List<Cliente> clientes = clienteService.listarClientes();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarClientePorId(@PathVariable Long id) {
        Cliente cliente = clienteService.buscarClientePorId(id);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
        clienteService.deletarCliente(id);
        return ResponseEntity.noContent().build();
    }
}