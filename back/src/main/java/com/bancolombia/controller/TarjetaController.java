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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bancolombia.models.HistoricoTarjetas;
import com.bancolombia.models.Tarjeta;
import com.bancolombia.services.TarjetaService;
import com.bancolombia.utils.RestResponse;

/**
 * Esta clase contiene los metodos de desarrollo de una Tarjeta y todo su
 * historico
 * 
 * @author Luis Beleño
 *
 */
@CrossOrigin
@RestController
@RequestMapping("api/tarjetas")
public class TarjetaController {

	@Autowired
	TarjetaService service;

	/**
	 * Obtiene todas las tarjetas con sus respectivos historicos
	 * 
	 * @return
	 */
	@GetMapping("/tarjeta")
	public List<Tarjeta> getAllTarjetas() {
		return service.getAllTarjetas();
	}

	/**
	 * Obtiene una tarjeta con su historico y cliente al cual pertenece
	 * 
	 * @param id
	 *            Identificador de la tarjeta
	 * @return
	 */
	@GetMapping("/tarjeta/{id}")
	@ResponseBody
	public RestResponse getTarjetaById(@PathVariable("id") long id) {
		Object respuesta = "";
		String strRespuesta = "";
		Tarjeta tarjeta;
		try {
			tarjeta = service.getTarjetaById(id);
		} catch (Exception e) {
			tarjeta = null;
		}

		if (tarjeta == null) {
			strRespuesta = "NoOK";
		} else {
			strRespuesta = "OK";
			respuesta = tarjeta;
		}
		return new RestResponse(HttpStatus.OK.value(), strRespuesta, respuesta);
	}

	/**
	 * Obtiene los datos de las tarjetas por medio del identificador del cliente.
	 * 
	 * @param idcliente
	 *            Identificador del cliente
	 * @return
	 */
	@GetMapping("/tarjeta/cliente/{idcliente}")
	@ResponseBody
	public RestResponse getTarjetaByIdCliente(@PathVariable("idcliente") long idcliente) {
		Object respuesta = "";
		String strRespuesta = "";
		List<Tarjeta> tarjetas;
		try {
			tarjetas = service.getTarjetaByIdCliente(idcliente);
		} catch (Exception e) {
			tarjetas = null;
		}

		if (tarjetas == null) {
			strRespuesta = "NoOK";
		} else {
			strRespuesta = "OK";
			respuesta = tarjetas;
		}
		return new RestResponse(HttpStatus.OK.value(), strRespuesta, respuesta);
	}

	/**
	 * Persiste una nueva tarjeta
	 * 
	 * @param tarjeta
	 *            objeto a persistir
	 * @return
	 */
	@PostMapping("/tarjeta")
	public RestResponse saveTarjeta(@Valid @RequestBody Tarjeta tarjeta) {
		try {
			service.saveTarjeta(tarjeta);
			return new RestResponse(HttpStatus.OK.value(), "OK", tarjeta);
		} catch (Exception e) {
			return new RestResponse(HttpStatus.NOT_ACCEPTABLE.value(), "NoOK", "Objeto no pude ser Insertado");
		}
	}

	/**
	 * Actualiza la informacion de una tarjeta
	 * 
	 * @param id
	 *            identificador de la tarjeta
	 * @param tarjeta
	 *            Objeto tarjeta con la informacion a modificar
	 * @return
	 */
	@PutMapping("/tarjeta/{id}")
	public RestResponse updateTarjeta(@PathVariable("id") long id, @Valid @RequestBody Tarjeta tarjeta) {
		try {
			service.saveorUpdateTarjeta(id, tarjeta);
			return new RestResponse(HttpStatus.OK.value(), "OK", tarjeta);
		} catch (Exception e) {
			return new RestResponse(HttpStatus.NOT_ACCEPTABLE.value(), "NoOK", "Objeto no pude ser Actualizado");
		}
	}

	/**
	 * Permite Borrar una tarjeta por medio de su identificador
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/tarjeta/{id}")
	public RestResponse delete(@PathVariable("id") long id) {
		try {
			service.deleteTarjeta(id);
			return new RestResponse(HttpStatus.OK.value(), "OK", "Objeto Eliminado");
		} catch (Exception e) {
			return new RestResponse(HttpStatus.NOT_ACCEPTABLE.value(), "NoOK", "Objeto no pudo ser Eliminado");
		}
	}

	/**
	 * HISTORICOS
	 */

	/**
	 * Persiste la informacion de un Historico para una tarjeta
	 * @param htarjeta
	 * @return
	 */
	@PostMapping("/historico")
	public RestResponse saveHistorico(@Valid @RequestBody HistoricoTarjetas htarjeta) {
		try {
			service.saveHistorico(htarjeta);
			return new RestResponse(HttpStatus.OK.value(), "OK", htarjeta);
		} catch (Exception e) {
			return new RestResponse(HttpStatus.NOT_ACCEPTABLE.value(), "NoOK", "Objeto no pude ser Insertado");
		}
	}
	
	/**
	 * Permite Actualizar un Historico de una Tarjeta por medio de su identificador
	 * @param id Identificador del Historico
	 * @param htarjeta Informacion a Actualizar
	 * @return
	 */
	@PutMapping("/historico/{id}")
	public RestResponse updateHistorico(@PathVariable("id") long id, @Valid @RequestBody HistoricoTarjetas htarjeta) {
		try {
			service.updateHistorico(id, htarjeta);
			return new RestResponse(HttpStatus.OK.value(), "OK", htarjeta);
		} catch (Exception e) {
			return new RestResponse(HttpStatus.NOT_ACCEPTABLE.value(), "NoOK", "Objeto no pude ser Actualizado");
		}
	}
	
	/**
	 * Permite Eliminar el historico de una tarjeta por medio de su Identificador
	 * @param id Idenficador del Historico a Elimminar
	 * @return
	 */
	@DeleteMapping("/historico/{id}")
	public RestResponse deleteHistorico(@PathVariable("id") long id) {
		try {
			service.deleteHistorico(id);
			return new RestResponse(HttpStatus.OK.value(), "OK", "Objeto Eliminado");
		} catch (Exception e) {
			return new RestResponse(HttpStatus.NOT_ACCEPTABLE.value(), "NoOK", "Objeto no pude ser Eliminado");
		}
	}
		
}
