package com.symplesweb.controller.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.symplesweb.controller.dto.view.EventoDTOView;
import com.symplesweb.controller.services.EventoService;
import com.symplesweb.model.entities.Evento;

@RestController
@RequestMapping(value = "/eventos")
public class EventoResource {
	
	
	@Autowired
	private EventoService service;
	
	
	@GetMapping
	public ResponseEntity<List<EventoDTOView>> findAll() {
		List<EventoDTOView> listEventos = service.findAll().stream().map(x -> new EventoDTOView(x))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(listEventos);
	}
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<EventoDTOView> findById(@PathVariable Long id) {
		Evento objEventos = service.findById(id);
		return ResponseEntity.ok().body(new EventoDTOView(objEventos));
	}
	
	
	@GetMapping(value = "/enderecos/{logradouro}")
	public ResponseEntity<List<EventoDTOView>> procuraPorLogradouro(@PathVariable String logradouro) {
		List<Evento> objEventos = service.procuraPorLogradouro(logradouro);
		List<EventoDTOView> listEventoDtoView = objEventos.stream().map(x -> new EventoDTOView(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listEventoDtoView);
	}
	
	
	@GetMapping(value = "/search/{nomeEvento}")
	public ResponseEntity<List<EventoDTOView>> searchByNomeEvento(@PathVariable String nomeEvento) {
		List<EventoDTOView> listEventoDtoView = service.searchByNomeEvento(nomeEvento)
				.stream().map(x -> new EventoDTOView(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listEventoDtoView);
	}
	
	

}
