package com.bancolombia.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "cliente")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Cliente implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "mySeqGenCliente", sequenceName = "CLIENTESEQ", initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator = "mySeqGenCliente")
	// @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "nombre", length = 50, nullable = false)
	private String nombre;

	@Column(name = "direccion", length = 100)
	private String direccion;

	@Column(name = "ciudad", length = 30, nullable = false)
	private String ciudad;

	@Column(name = "telefono", length = 20)
	private Long telefono;

	@OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
	@JsonIgnoreProperties("cliente")
	private List<Tarjeta> tarjetas = new ArrayList<>();

	public List<Tarjeta> getTarjetas() {
		return tarjetas;
	}

	public void setTarjetas(List<Tarjeta> tarjetas) {
		this.tarjetas = tarjetas;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public Long getTelefono() {
		return telefono;
	}

	public void setTelefono(Long telefono) {
		this.telefono = telefono;
	}

	public Cliente(Long id) {
		super();
		this.id = id;
	}

	public Cliente() {
		super();
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nombre=" + nombre + ", direccion=" + direccion + ", ciudad=" + ciudad
				+ ", telefono=" + telefono + "]";
	}

	public Cliente(String nombre, String direccion, String ciudad, Long telefono, List<Tarjeta> tarjetas) {
		super();
		this.nombre = nombre;
		this.direccion = direccion;
		this.ciudad = ciudad;
		this.telefono = telefono;
		this.tarjetas = tarjetas;
	}

	public Cliente(String nombre, String direccion, String ciudad, Long telefono) {
		super();
		this.nombre = nombre;
		this.direccion = direccion;
		this.ciudad = ciudad;
		this.telefono = telefono;
	}

}
