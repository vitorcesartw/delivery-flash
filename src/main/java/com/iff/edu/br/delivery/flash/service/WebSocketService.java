package com.iff.edu.br.delivery.flash.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.iff.edu.br.delivery.flash.dto.NotificacaoDTO;

@Service
public class WebSocketService {
    
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    
    public void enviarNotificacao(Long restauranteId, String mensagem) {
        messagingTemplate.convertAndSend(
            "/topic/restaurante." + restauranteId,
            new NotificacaoDTO(mensagem, LocalDateTime.now())
        );
    }
}