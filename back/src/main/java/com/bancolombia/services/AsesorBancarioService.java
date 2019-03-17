package com.bancolombia.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bancolombia.exception.ResourceNotFoundException;
import com.bancolombia.models.AsesorBancario;
import com.bancolombia.repository.AsesorBancarioRepository;

@Service
public class AsesorBancarioService {

	@Autowired
	AsesorBancarioRepository repo;

	/**
	 * Lista todos los asesores comerciales
	 * @return List<AsesorBancario>
	 */
	public List<AsesorBancario> getAllAsesor() {
		List<AsesorBancario> asesors = new ArrayList<>();
		repo.findAll().forEach(ac -> asesors.add(ac));
		return asesors;
	}
	
	/**
	 * Obtiene un asesor bancario por medio de su Identificador
	 * @param id Identificador del objeto
	 * @return Objeto encontrado
	 */
	public AsesorBancario getAsesorById(long id) {
		return repo.findById(id).get();
	}
	
	/**
	 * Persiste la informacion de un asesor
	 * @param asesor Objeto Asesor
	 */
	public AsesorBancario save(AsesorBancario asesor) {
		return repo.save(asesor);
	}
	

	/**
	 * Permite Actualizar la informacion de un Asesor Bancario
	 * @param id identificador del Asesor
	 * @param asesor datos del asesor
	 * @return
	 */
	public AsesorBancario update(long id, AsesorBancario asesor) {
		return repo.findById(id).map(c->{
			c.setEspecialidad(asesor.getEspecialidad());
			c.setNombre(asesor.getNombre());
			return repo.save(c);
		}).orElseThrow(()->new ResourceNotFoundException("Asesor No existe"));		
	}
	
	/**
	 * Eliminar la informacion de un asesor bancario por medio de su identificador
	 * @param id Identificador del objeto a eliminar
	 */
	public void delete(long id) {
		repo.deleteById(id);
	}

}
