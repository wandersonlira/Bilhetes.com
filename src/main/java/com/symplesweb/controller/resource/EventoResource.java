package com.symplesweb.controller.resource;

import java.util.List;

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
		List<EventoDTOView> listEventos = service.findAll();
		return ResponseEntity.ok().body(listEventos);
	}
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<EventoDTOView> findById(@PathVariable Long id) {
		EventoDTOView objEventos = service.findById(id);
		return ResponseEntity.ok().body(objEventos);
	}
	
	
	@GetMapping(value = "/enderecos/{logradouro}")
	public ResponseEntity<List<EventoDTOView>> procuraPorLogradouro(@PathVariable String logradouro) {
		List<EventoDTOView> objEventos = service.procuraPorLogradouro(logradouro);
		return ResponseEntity.ok().body(objEventos);
	}
	
	
	@GetMapping(value = "/search/{nomeEvento}")
	public ResponseEntity<List<EventoDTOView>> searchByNomeEvento(@PathVariable String nomeEvento) {
		List<EventoDTOView> objetoEvento = service.searchByNomeEvento(nomeEvento);
		return ResponseEntity.ok().body(objetoEvento);
	}
	
	

}
