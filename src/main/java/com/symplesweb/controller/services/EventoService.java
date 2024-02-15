package com.symplesweb.controller.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.symplesweb.controller.dto.view.EventoDTOView;
import com.symplesweb.controller.repositories.EventoRepository;
import com.symplesweb.model.entities.Evento;


@Service
public class EventoService {
	
	@Autowired
	private EventoRepository repository;
	
	
	
	public List<Evento> findAll() {
		return repository.findAll();
	}
	
	
	public Evento findById(Long id) {
		Optional<Evento> objEvento = repository.findById(id);
		return objEvento.get();
	}
	
	
	public List<Evento> procuraPorLogradouro(String logradouro) {
//		Optional<List<Evento>> objEvento = repository.procuraPorLogradouro(logradouro);
		List<Evento> objEvento = repository.procuraPorLogradouro(logradouro);
		return objEvento;
	}
	
	
//	@Transactional(readOnly = true)
	public List<Evento> searchByNomeEvento(String nomeEvento) {
		Optional<List<Evento>> objetoEvento = repository.searchByNomeEvento(nomeEvento);
		return objetoEvento.get();
	}
	
	
	
}
