package com.iff.edu.br.delivery.flash.model;

import java.sql.Date;
import java.util.List;

import com.iff.edu.br.delivery.flash.patterns.state.AguardandoPagamento;
import com.iff.edu.br.delivery.flash.patterns.state.EstadoPedido;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    private Cliente cliente;

    private Date data;

    @ManyToOne
    private Restaurante restaurante;

    @ManyToOne
    private Entregador entregador;
    
    @OneToOne(mappedBy = "pedido")
    private Pagamento pagamento;

    @OneToMany
    private List<ItemPedido> itens;

    private float valorTotal;

    @Transient // O estado não será salvo diretamente no banco
    private EstadoPedido estado;

    @Column(name = "estado_pedido") // Campo no banco para armazenar o nome do estado
    private String estadoNome;

    public Pedido() {
        this.estado = new AguardandoPagamento(); // Estado inicial
        this.estadoNome = estado.getNomeEstado();
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
        this.estadoNome = estado.getNomeEstado(); // Atualiza o nome salvo no banco
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void avancarEstado() {
        estado.proximoEstado(this);
    }

    public void cancelarPedido() {
        estado.cancelarPedido(this);
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

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
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

	public float getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(float valorTotal) {
		this.valorTotal = valorTotal;
	}



    // Getters e Setters
}