package com.symplesweb.controller.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.symplesweb.controller.repositories.EventoRepository;
import com.symplesweb.controller.services.exceptions.DatabaseException;
import com.symplesweb.controller.services.exceptions.ResourceNotFoundException;
import com.symplesweb.model.entities.Evento;


@Service
public class EventoService {
	
	@Autowired
	private EventoRepository repository;

	
	
	public Evento save(Evento evento) {
			return this.repository.save(evento);
	}
	
	
	public List<Evento> findAll() {
		return repository.findAll();
	}
	
	
	public Evento findById(Long id) {
		Optional<Evento> objetoEvento = repository.findById(id);
		return objetoEvento.orElseThrow(() ->  new ResourceNotFoundException(id));
	}
	
	
	public List<Evento> procuraPorLogradouro(String logradouro) {
		List<Evento> objEvento = repository.procuraPorLogradouro(logradouro);
		return objEvento;
	}
	
	
//	@Transactional(readOnly = true)
	public List<Evento> searchByNomeEvento(String nomeEvento) {
		Optional<List<Evento>> objetoEvento = repository.searchByNomeEvento(nomeEvento);
		return objetoEvento.get();
	}
	
	
	public void deleteById(Long idEvento) {
		Evento deleteEvento = this.findById(idEvento);
		try {
			this.repository.delete(deleteEvento);
		} catch (DataIntegrityViolationException exception) {
			throw new DatabaseException(exception.getMessage());
			}
	}
	
	
	
}
