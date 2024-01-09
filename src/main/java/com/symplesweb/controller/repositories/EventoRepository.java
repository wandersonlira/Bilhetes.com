package com.symplesweb.controller.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.symplesweb.model.entities.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long>{

}
