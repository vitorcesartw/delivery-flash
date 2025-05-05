package com.iff.edu.br.delivery.flash.dto;
import java.time.LocalDateTime;

public class NotificacaoDTO {
    private String mensagem;
    private LocalDateTime data;
    
    public NotificacaoDTO(String mensagem, LocalDateTime data) {
        this.mensagem = mensagem;
        this.data = data;
    }
    
    // Getters necessários para a serialização JSON
    public String getMensagem() {
        return mensagem;
    }
    
    public LocalDateTime getData() {
        return data;
    }
    
    // Setters (opcional, se precisar)
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    
    public void setData(LocalDateTime data) {
        this.data = data;
    }
}