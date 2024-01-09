package com.symplesweb.controller.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.symplesweb.controller.services.EventoService;
import com.symplesweb.model.entities.Evento;

@RestController
@RequestMapping(value = "/eventos")
public class EventoResource {
	
	
	@Autowired
	private EventoService service;
	
	
	@GetMapping
	public ResponseEntity<List<Evento>> findAll() {
		List<Evento> listEventos = service.findAll();
		return ResponseEntity.ok().body(listEventos);
	}
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Evento> findById(@PathVariable Long id) {
		Evento objEventos = service.findById(id);
		return ResponseEntity.ok().body(objEventos);
	}

}
