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
	
	
	
	public List<EventoDTOView> findAll() {
		return repository.findAll().stream().map(x -> new EventoDTOView(x)).collect(Collectors.toList());
	}
	
	
	public EventoDTOView findById(Long id) {
		Optional<Evento> objEvento = repository.findById(id);
		return new EventoDTOView(objEvento.get());
	}
	
	
	public List<EventoDTOView> procuraPorLogradouro(String logradouro) {
//		Optional<List<Evento>> objEvento = repository.procuraPorLogradouro(logradouro);
		List<Evento> objEvento = repository.procuraPorLogradouro(logradouro);
		return objEvento.stream().map(x -> new EventoDTOView(x)).collect(Collectors.toList());
	}
	
	
//	@Transactional(readOnly = true)
	public List<EventoDTOView> searchByNomeEvento(String nomeEvento) {
		Optional<List<Evento>> objetoEvento = repository.searchByNomeEvento(nomeEvento);
		return objetoEvento.get().stream().map(x -> new EventoDTOView(x)).collect(Collectors.toList());
	}
	
	
	
}
