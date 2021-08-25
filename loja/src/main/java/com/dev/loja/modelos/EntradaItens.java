package com.dev.loja.modelos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="entrada_itens")
public class EntradaItens implements Serializable{
    
    public Produto() {
		super();
	}

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	private EntradaProduto entrada;
	@ManyToOne
	private Produto produto;
	private Double quantidade = 0.;
	private Double valorProduto = 0.;
	private Double valorVenda = 0.;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}

	public Double getValorProduto() {
		return valorProduto;
	}

	public void setValorProduto(Double valorProduto) {
		this.valorProduto = valorProduto;
	}

	public Double getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(Double valorVenda) {
		this.valorVenda = valorVenda;
	}
	
	public EntradaProduto getEntrada() {
		return entrada;
	}

	public void setEntrada(EntradaProduto entrada) {
		this.entrada = entrada;
	}
	public EntradaProduto getEntrada() {
		return entrada;
	}

	public void setEntrada(EntradaProduto entrada) {
		this.entrada = entrada;
	}
	
	
}
