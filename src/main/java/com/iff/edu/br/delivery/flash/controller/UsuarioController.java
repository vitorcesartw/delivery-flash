package com.iff.edu.br.delivery.flash.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iff.edu.br.delivery.flash.model.Cliente;
import com.iff.edu.br.delivery.flash.service.ClienteService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/alterar-cadastro")
    public ResponseEntity<Cliente> getClienteLogado(HttpSession session) {
        Object clienteIdObj = session.getAttribute("clienteId");

        if (clienteIdObj == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        Long clienteId = Long.parseLong(clienteIdObj.toString()); // ⚠ Conversão correta para Long
        Cliente cliente = clienteService.buscarClientePorId(clienteId);

        if (cliente != null) {
            return ResponseEntity.ok(cliente);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/alterar-cadastro")
    public ResponseEntity<String> atualizarCadastro(@RequestBody Cliente cliente, HttpSession session) {
        Long clienteId = (Long) session.getAttribute("clienteId");

        if (clienteId != null) {
            Cliente clienteExistente = clienteService.buscarClientePorId(clienteId);

            clienteExistente.setNome(cliente.getNome());
            clienteExistente.setEmail(cliente.getEmail());
            clienteExistente.setTelefone(cliente.getTelefone());
            clienteExistente.setEndereco(cliente.getEndereco());
            clienteExistente.setSenha(cliente.getSenha()); // ⚠ Aqui é importante criptografar antes de salvar

            clienteService.atualizarCliente(clienteExistente);
            return ResponseEntity.ok("Cadastro atualizado com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não autenticado.");
        }
    }
    
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Cliente cliente, HttpSession session) {
        Cliente autenticado = clienteService.autenticarCliente(cliente.getEmail(), cliente.getSenha());

        if (autenticado == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("mensagem", "Credenciais inválidas"));
        }

        String tipoUsuario = autenticado.getTipo_user(); // ⚠ Pegamos `dtype` diretamente do banco

        session.setAttribute("clienteId", autenticado.getId()); 
        session.setAttribute("tipoUsuario", tipoUsuario); 

        Map<String, String> response = new HashMap<>();
        response.put("mensagem", "Login realizado com sucesso!");
        response.put("clienteId", autenticado.getId().toString());
        response.put("tipoUsuario", tipoUsuario); 

        return ResponseEntity.ok(response);
    }
    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate(); // ⚠ Remove todos os dados da sessão
        return ResponseEntity.ok("Logout realizado com sucesso!");
    }
}
