package com.iff.edu.br.delivery.flash.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import com.iff.edu.br.delivery.flash.pagamento.Pagamento;
import com.iff.edu.br.delivery.flash.patterns.state.AguardandoPagamento;
import com.iff.edu.br.delivery.flash.patterns.state.EmPreparo;
import com.iff.edu.br.delivery.flash.patterns.state.EstadoPedido;
import com.iff.edu.br.delivery.flash.patterns.state.ProntoParaEntrega;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Transient;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Column(name = "data_pedido")
    private LocalDateTime data;

    @ManyToOne
    @JoinColumn(name = "restaurante_id")
    private Restaurante restaurante;

    @ManyToOne
    private Entregador entregador;
    
    @OneToOne(mappedBy = "pedido")
    private Pagamento pagamento;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> itens;

    @Column(name = "valor_total", precision = 10, scale = 2)
    private BigDecimal valorTotal;

    @Transient // O estado não será salvo diretamente no banco
    private EstadoPedido estado;

    @Column(name = "estado_pedido", insertable = false, updatable = false)
    private String estadoNome;

 
    @Column(name = "estado_pedido")
    private String estadoPersistido;



    // Delegações para o estado atual
    public void pagar() {
        estado.pagar();
    }

    public EstadoPedido getEstado() {
        return estado;
    }
    
    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }
    

    public String getEstadoNome() { // ⚠️ Esse método estava faltando!
        return estadoNome;
    }
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}

	public Entregador getEntregador() {
		return entregador;
	}

	public void setEntregador(Entregador entregador) {
		this.entregador = entregador;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public List<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(List<ItemPedido> itens) {
		this.itens = itens;
	}

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }
    
    



	public String getEstadoPersistido() {
		return estadoPersistido;
	}

	public void setEstadoPersistido(String estadoPersistido) {
		this.estadoPersistido = estadoPersistido;
	}

	public void setEstadoNome(String estadoNome) {
		this.estadoNome = estadoNome;
	}



    // Getters e Setters
}