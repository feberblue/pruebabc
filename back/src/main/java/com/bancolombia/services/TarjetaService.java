package com.bancolombia.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bancolombia.exception.ResourceNotFoundException;
import com.bancolombia.models.HistoricoTarjetas;
import com.bancolombia.models.Tarjeta;
import com.bancolombia.repository.HistoricoRepository;
import com.bancolombia.repository.TarjetaRepository;

/**
 * Servicio encargado de gestionar Tarjetas e Historico de las mismas
 * 
 * @author Luis Beleño
 *
 */
@Service
public class TarjetaService {

	@Autowired
	TarjetaRepository tarjetaRepo;

	@Autowired
	HistoricoRepository historicoRepo;

	/**
	 * Permite la insercion de una Nueva Tarjeta
	 * 
	 * @param tarjeta:
	 *            Objeto tarjeta a insertar
	 */
	public void saveTarjeta(Tarjeta tarjeta) {
		tarjetaRepo.save(tarjeta);
	}

	/**
	 * Permite actualizar la informacion de una tarjeta
	 * 
	 * 1: para actualizar información de una tarjeta se debe inhabilitar la existen 
	 * 2. Se crea una nueva tarjeta, y se activa para el cliente
	 * 3. un Cliente puede dar por terminado el uso de una tarjeta, siempre y cuando 
	 * 		este al dia con sus obligaciones.
	 * 
	 * @param id
	 *            Identificador de la Tarjeta
	 * @param tarjeta
	 *            Informacion de la tarjeta a actualizar
	 */
	public void saveorUpdateTarjeta(Long id, Tarjeta tarjeta) {
		tarjetaRepo.findById(id).map(tj -> {
			tj.setCcv(tarjeta.getCcv());
			tj.setCliente(tarjeta.getCliente());
			tj.setNumero(tarjeta.getNumero());
			tj.setTipoTarjeta(tarjeta.getTipoTarjeta());
			return tarjetaRepo.save(tj);
		}).orElseThrow(() -> new ResourceNotFoundException("Tarjeta No existe "));
	}

	/**
	 * Permite Eliminar una Tarjeta por medio de su Identificador
	 * 
	 * @param id
	 *            Identificador de la tarjeta
	 */
	public void deleteTarjeta(long id) {
		tarjetaRepo.deleteById(id);
	}

	/**
	 * Obtiene un objeto Tarjeta por medio de su Identificador
	 * 
	 * @param id
	 * @return
	 */
	public Tarjeta getTarjetaById(long id) {
		return tarjetaRepo.findById(id).get();
	}

	/**
	 * Obtiene el LIstado de tarjetas que pertenecen a un Cliente, con su debido
	 * historial de cada tarjeta
	 * 
	 * @param idCliente
	 * @return
	 */
	public List<Tarjeta> getTarjetaByIdCliente(Long idCliente) {
		return tarjetaRepo.findByClienteId(idCliente);
	}

	/**
	 * Obtiene el listado de todas las tarjetas del sistema
	 * 
	 * @return
	 */
	public List<Tarjeta> getAllTarjetas() {
		return tarjetaRepo.findAll();
	}

	/**
	 * Permite Guardar Información de un Historico de una Tarjeta
	 * 
	 * @param historico
	 */
	public void saveHistorico(HistoricoTarjetas historico) {
		historicoRepo.save(historico);
	}

	/**
	 * Permite Realizar la actualización de un Historico de Tarjeta
	 * 
	 * LOs historicos no se actualizan en su totalidad, ni su descripcion,
	 * monto o fecha de la transacción
	 * 
	 * @param id
	 * @param historico
	 */
	public void updateHistorico(long id, HistoricoTarjetas historico) {
		historicoRepo.findById(id).map(ht -> {
			ht.setDescripcion(historico.getDescripcion());
			ht.setFechaconsumo(historico.getFechaconsumo());
			ht.setMonto(historico.getMonto());
			return historicoRepo.save(ht);
		}).orElseThrow(() -> new ResourceNotFoundException("Historico de la Tarjeta No existe "));
	}

	/**
	 * Permite Eliminar el historico de una Tarjeta
	 * 
	 * Se debe tener presente que en un Banco No se elimina Información
	 * financiera de los clientes o historicos de sus tarjetas, simplemente
	 * si no usa el producto se inhabilita el producto junto con el historial
	 * del mismo producto
	 * 
	 * @param id
	 *            Identificador del Historico
	 */
	public void deleteHistorico(long id) {
		historicoRepo.deleteById(id);
	}

}
