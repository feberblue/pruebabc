package com.bancolombia.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "historicotarjeta")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class HistoricoTarjetas implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "mySeqGenHistorico", sequenceName = "HISTORICOSEQ", initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator = "mySeqGenHistorico")
	// @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;

	@JsonProperty("fechaconsumo")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	@Temporal(TemporalType.DATE)
	@Column(name = "fechaconsumo", nullable = false)
	private Date fechaconsumo;

	@JsonProperty("descripcion")
	@Column(name = "descripcion", length = 100, nullable = false)
	private String descripcion;

	@JsonProperty("monto")
	@Column(name = "monto", precision = 10, scale = 2, nullable = false)
	private Double monto;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FK_ID_TARJETA")
	@JsonIgnoreProperties("tarjeta")
	private Tarjeta tarjeta;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFechaconsumo() {
		return fechaconsumo;
	}

	public void setFechaconsumo(Date fechaconsumo) {
		this.fechaconsumo = fechaconsumo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public Tarjeta getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(Tarjeta tarjeta) {
		this.tarjeta = tarjeta;
	}

	@Override
	public String toString() {
		return "HistoricoTarjetas [id=" + id + ", fechaconsumo=" + fechaconsumo + ", descripcion=" + descripcion
				+ ", monto=" + monto + ", tarjeta=" + tarjeta + "]";
	}

	public HistoricoTarjetas() {
		super();
	}

	public HistoricoTarjetas(Long id) {
		super();
		this.id = id;
	}

	public HistoricoTarjetas(Date fechaconsumo, String descripcion, Double monto, Tarjeta tarjeta) {
		super();
		this.fechaconsumo = fechaconsumo;
		this.descripcion = descripcion;
		this.monto = monto;
		this.tarjeta = tarjeta;
	}

}
