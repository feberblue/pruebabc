package com.bancolombia.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bancolombia.models.Tarjeta;

@Repository
public interface TarjetaRepository extends JpaRepository<Tarjeta, Long> {
	Page<Tarjeta> findByClienteId(Long idClient, Pageable pageable);
	List<Tarjeta> findByClienteId(Long idClient);
	Optional<Tarjeta> findByIdAndClienteId(Long id, Long idClient);
}
