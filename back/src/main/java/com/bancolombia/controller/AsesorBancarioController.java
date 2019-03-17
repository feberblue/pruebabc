package com.bancolombia.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bancolombia.models.AsesorBancario;
import com.bancolombia.services.AsesorBancarioService;
import com.bancolombia.utils.RestResponse;

/**
 * Controlador de un Asesor Bancario
 * 
 * @author Luis Beleño
 *
 */
@CrossOrigin
@RestController
@RequestMapping("api/asesores")
public class AsesorBancarioController {

	@Autowired
	AsesorBancarioService asesorService;

	
	/**
	 * Obtiene el listado de todos los Asesores
	 * @return
	 */
	@GetMapping("/asesorbancario")
	public List<AsesorBancario> getAllAsesor() {
		return asesorService.getAllAsesor();
	}

	/**
	 * Obtiene un Asesor por medio de su Identificador
	 * @param id
	 * @return
	 */
	@GetMapping("/asesorbancario/{id}")
	public AsesorBancario getAsesorById(@PathVariable("id") long id) {
		return asesorService.getAsesorById(id);
	}

	/**
	 * Persiste un asesor en Base de datos
	 * @param asesor Objeto a persistir
	 * @return
	 */
	@PostMapping("/asesorbancario")
	public RestResponse saveAsesor(@Valid @RequestBody AsesorBancario asesor) {
		try {
			asesorService.save(asesor);
			return new RestResponse(HttpStatus.OK.value(), "OK", asesor);
		} catch (Exception e) {
			return new RestResponse(HttpStatus.NOT_ACCEPTABLE.value(), "NoOK", "Asesor no pude ser Insertado");
		}

	}

	/**
	 * Permite MOdificar la informacion del Asesor Bancario, por medio de su identificador
	 * @param id Identificador del Asesor
	 * @param asesor Objeto Asesor con los datos a modificar
	 * @return
	 */
	@PutMapping("/asesorbancario/{id}")
	public RestResponse updateAsesor(@PathVariable Long id, @Valid @RequestBody AsesorBancario asesor) {
		try {
			asesorService.update(id, asesor);
			return new RestResponse(HttpStatus.OK.value(), "OK", asesor);
		} catch (Exception e) {
			return new RestResponse(HttpStatus.NOT_ACCEPTABLE.value(), "NoOK", "Asesor No pudo ser Actualizado");
		}
	}

	/**
	 * Permite Borrar un Asesor por medio de su identificador
	 * @param id identificador del asesor
	 * @return
	 */
	@DeleteMapping("/asesorbancario/{id}")
	public RestResponse deleteAsesorBancario(@PathVariable("id") long id) {
		try {
			asesorService.delete(id);
			return new RestResponse(HttpStatus.OK.value(), "OK", "Asesor Eliminado");
		} catch (Exception e) {
			return new RestResponse(HttpStatus.NOT_ACCEPTABLE.value(), "NoOK", "Asesor no pudo ser Eliminado");
		}

	}

}
