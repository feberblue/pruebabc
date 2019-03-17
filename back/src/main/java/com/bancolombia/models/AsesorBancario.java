package com.bancolombia.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "asesorbancario")
public class AsesorBancario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "mySeqGenAsesor", sequenceName = "ASESORSEQ", initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator = "mySeqGenAsesor")
	// @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;

	@JsonProperty("nombre")
	@Column(name = "nombre", nullable = false, length = 50)
	private String nombre;

	@JsonProperty("especialidad")
	@Column(name = "especialidad", nullable = false, length = 50)
	private String especialidad;

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

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	@Override
	public String toString() {
		return "AsesorBancario [id=" + id + ", nombre=" + nombre + ", especialidad=" + especialidad + "]";
	}

	public AsesorBancario(Long id, String nombre, String especialidad) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.especialidad = especialidad;
	}

	public AsesorBancario(String nombre, String especialidad) {
		super();
		this.nombre = nombre;
		this.especialidad = especialidad;
	}

	public AsesorBancario(Long id) {
		super();
		this.id = id;
	}

	public AsesorBancario() {
		super();
	}
	
	

}
