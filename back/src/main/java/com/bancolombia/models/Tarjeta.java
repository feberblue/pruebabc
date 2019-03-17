package com.bancolombia.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tarjetas")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Tarjeta implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "mySeqGenTarjeta", sequenceName = "TARJETASEQ", initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator = "mySeqGenTarjeta")
	// @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "numero", length = 19, unique = true)
	private String numero;

	@Column(name = "ccv", length = 4)
	private Integer ccv;

	@Column(name = "tipotarjeta", length = 50)
	private String tipoTarjeta;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FK_ID_CLIENTE")
	private Cliente cliente;

	@OneToMany(mappedBy = "tarjeta", fetch = FetchType.LAZY)
	@JsonIgnoreProperties("tarjeta")
	private List<HistoricoTarjetas> historico = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Integer getCcv() {
		return ccv;
	}

	public void setCcv(Integer ccv) {
		this.ccv = ccv;
	}

	public String getTipoTarjeta() {
		return tipoTarjeta;
	}

	public void setTipoTarjeta(String tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<HistoricoTarjetas> getHistorico() {
		return historico;
	}

	public void setHistorico(List<HistoricoTarjetas> historico) {
		this.historico = historico;
	}

	@Override
	public String toString() {
		return "Tarjeta [id=" + id + ", numero=" + numero + ", ccv=" + ccv + ", tipoTarjeta=" + tipoTarjeta
				+ ", cliente=" + cliente + ", historico=" + historico + "]";
	}

	public Tarjeta() {
		super();
	}

	public Tarjeta(String numero, Integer ccv, String tipoTarjeta, Cliente cliente) {
		super();
		this.numero = numero;
		this.ccv = ccv;
		this.tipoTarjeta = tipoTarjeta;
		this.cliente = cliente;
	}

}
