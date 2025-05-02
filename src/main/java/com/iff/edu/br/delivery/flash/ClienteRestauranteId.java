package com.iff.edu.br.delivery.flash;

import java.io.Serializable;
import java.util.Objects;

public class ClienteRestauranteId implements Serializable {
    private Long cliente;
    private Long restaurante;

    public ClienteRestauranteId() {}

    public ClienteRestauranteId(Long cliente, Long restaurante) {
        this.cliente = cliente;
        this.restaurante = restaurante;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClienteRestauranteId)) return false;
        ClienteRestauranteId that = (ClienteRestauranteId) o;
        return Objects.equals(cliente, that.cliente) &&
               Objects.equals(restaurante, that.restaurante);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cliente, restaurante);
    }
}
