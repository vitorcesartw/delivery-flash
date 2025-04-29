package com.iff.edu.br.delivery.flash.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iff.edu.br.delivery.flash.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // Métodos personalizados podem ser adicionados se necessário
    Optional<Cliente> findByCpf(String cpf); // Exemplo de método customizado
    Cliente findByEmail(String email);
}
