package com.symplesweb.controller.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.symplesweb.controller.repositories.EventoRepository;
import com.symplesweb.model.entities.Evento;


@Service
public class EventoService {
	
	@Autowired
	EventoRepository repository;
	
	
	
	public List<Evento> findAll() {
		return repository.findAll();
	}
	
	
	public Evento findById(Long id) {
		Optional<Evento> objEvento = repository.findById(id);
		return objEvento.get();
	}

}
