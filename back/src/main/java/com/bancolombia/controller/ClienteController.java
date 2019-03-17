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

import com.bancolombia.models.Cliente;
import com.bancolombia.services.ClienteService;
import com.bancolombia.utils.RespuestaOK;
import com.bancolombia.utils.RestResponse;

/**
 * Controlador de funcionalidades para clientes
 * 
 * @author User
 *
 */
@CrossOrigin
@RestController
@RequestMapping("api/clientes")
public class ClienteController {

	@Autowired
	ClienteService servicio;

	/**
	 * Obtiene la informacion de todos los clientes, eventualmente esta consulta
	 * obtiene los datos de las tarjetas de cada cliente junto con su historico
	 * 
	 * @return
	 */
	@GetMapping("/cliente")
	@ResponseBody
	public List<Cliente> getAllCliente() {
		return servicio.getAllCliente();
	}

	/**
	 * Obtiene la informacion de un cliente por medio de su identificador
	 * 
	 * @param id
	 *            Identificador de Usuario
	 * @return
	 */
	@GetMapping("/cliente/{id}")
	@ResponseBody
	public RestResponse getClienteById(@PathVariable("id") long id) {
		Object respuesta = "";
		String strRespuesta = "";
		Cliente cliente;
		try {
			cliente = servicio.getClienteById(id);
		} catch (Exception e) {
			cliente = null;
		}

		if (cliente == null) {
			strRespuesta = RespuestaOK.NOK.getRespuesta();
			respuesta = "Cliente no Encontrado";
		} else {
			strRespuesta = RespuestaOK.OK.getRespuesta();
			respuesta = cliente;
		}
		return new RestResponse(HttpStatus.OK.value(), strRespuesta, respuesta);

	}

	/**
	 * Persiste la informacion de un cliente nuevo
	 * 
	 * @param client
	 *            Objeto con la informacion de Cliente
	 * @return
	 */
	@PostMapping("/cliente")
	public RestResponse save(@Valid @RequestBody Cliente client) {
		try {
			servicio.save(client);
			return new RestResponse(HttpStatus.OK.value(), RespuestaOK.OK.getRespuesta(), client);
		} catch (Exception e) {
			return new RestResponse(HttpStatus.NOT_ACCEPTABLE.value(), RespuestaOK.NOK.getRespuesta(),
					"Cliente no pude ser Insertado");
		}
	}

	/**
	 * Permite Actualizar la informacion de un cliente por medio de su identificador
	 * 
	 * @param id
	 *            Identificador del Cliente
	 * @param cliente
	 *            Datos del cliente a modificar
	 * @return
	 */
	@PutMapping("/cliente/{id}")
	public RestResponse update(@PathVariable("id") long id, @Valid @RequestBody Cliente cliente) {
		try {
			servicio.update(id, cliente);
			return new RestResponse(HttpStatus.OK.value(), RespuestaOK.OK.getRespuesta(), "Cliente Actualizado");
		} catch (Exception e) {
			return new RestResponse(HttpStatus.NOT_ACCEPTABLE.value(), RespuestaOK.NOK.getRespuesta(),
					"Cliente No pudo ser Actualizado");
		}
	}

	/**
	 * Permite Eliminar Un CLiente por medio de su identificador
	 * @param id Identificador del Cliente
	 * @return
	 */
	@DeleteMapping("/cliente/{id}")
	public RestResponse delete(@PathVariable("id") long id) {
		try {
			servicio.delete(id);
			return new RestResponse(HttpStatus.OK.value(), RespuestaOK.OK.getRespuesta(), "Cliente Eliminado");
		} catch (Exception e) {
			return new RestResponse(HttpStatus.NOT_ACCEPTABLE.value(), RespuestaOK.NOK.getRespuesta(),
					"Cliente no pudo ser Eliminado");
		}
	}

}
