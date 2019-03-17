package com.bancolombia.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bancolombia.exception.ResourceNotFoundException;
import com.bancolombia.models.Cliente;
import com.bancolombia.repository.ClienteRepository;
import com.bancolombia.repository.TarjetaRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository repo;
	
	@Autowired
	TarjetaRepository repoTarjeta;

	/**
	 * Lista todos los clientes
	 * 
	 * @return List de Cliente
	 */
	public List<Cliente> getAllCliente() {
		List<Cliente> clients = new ArrayList<>();
		repo.findAll().forEach(ac -> clients.add(ac));
		return clients;
	}

	/**
	 * Obtiene un cliente por medio de su Identificador
	 * 
	 * @param id
	 *            Identificador del objeto
	 * @return Objeto encontrado
	 */
	public Cliente getClienteById(long id) {
		Cliente objeto;
		Optional<Cliente> find = repo.findById(id);
		if (find.isPresent()) {
			objeto = find.get();			
		} else {
			objeto = null;
		}
		return objeto;
	}

	/**
	 * Persiste la informacion de un cliente
	 * @param cliente
	 */
	public Cliente save(Cliente cliente) {		
		return repo.save(cliente);
	}
	
	/**
	 * Permite Actualizar la informacion de un cliente
	 * @param id
	 * @param cliente
	 */
	public Cliente update(long id, Cliente cliente) {
		return repo.findById(id).map(c->{
			c.setCiudad(cliente.getCiudad());
			c.setDireccion(cliente.getDireccion());
			c.setNombre(cliente.getNombre());
			c.setTelefono(cliente.getTelefono());
			return repo.save(c);
		}).orElseThrow(()->new ResourceNotFoundException("Cliente No existe "));
	}

	/**
	 * Eliminar la informacion de un cliente por medio de su identificador
	 * 
	 * @param id
	 *            Identificador del objeto a eliminar
	 */
	public void delete(long id) {
		repo.deleteById(id);
	}

}
