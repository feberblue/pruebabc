package com.bancolombia.repository;

import org.springframework.data.repository.CrudRepository;

import com.bancolombia.models.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {

}
