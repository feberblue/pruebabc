package com.bancolombia.repository;

import org.springframework.data.repository.CrudRepository;

import com.bancolombia.models.HistoricoTarjetas;

public interface HistoricoRepository extends CrudRepository<HistoricoTarjetas, Long> {

}
