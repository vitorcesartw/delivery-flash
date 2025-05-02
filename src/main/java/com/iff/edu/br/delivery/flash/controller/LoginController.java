package com.iff.edu.br.delivery.flash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iff.edu.br.delivery.flash.model.Cliente;
import com.iff.edu.br.delivery.flash.service.ClienteService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/clientes")
public class LoginController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String senha, HttpSession session) {
        Cliente cliente = clienteService.autenticarCliente(email, senha);

        if (cliente != null) {
            session.setAttribute("clienteId", cliente.getId()); // ⚠ Garanta que está salvando o ID corretamente
            return ResponseEntity.ok("/tela-inicial.html");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("E-mail ou senha inválidos.");
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate(); // Limpa a sessão
        return ResponseEntity.ok("Logout realizado com sucesso!");
    }
}
