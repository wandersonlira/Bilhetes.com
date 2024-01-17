package com.symplesweb.controller.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.symplesweb.controller.services.ParticipanteEventoService;
import com.symplesweb.model.entities.ParticipanteEvento;


@RestController
@RequestMapping(value = "/participantes-eventos")
public class ParticipanteEventoResource {
	
	
	@Autowired
	ParticipanteEventoService service;
	
	
	
	@GetMapping
	public ResponseEntity<List<ParticipanteEvento>> findAll() {
		List<ParticipanteEvento> listParticipanteEvento = service.findAll();
		return ResponseEntity.ok().body(listParticipanteEvento);
	}
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ParticipanteEvento> findById(@PathVariable Long id) {
		ParticipanteEvento objParticipanteEvento = service.findById(id);
		return ResponseEntity.ok().body(objParticipanteEvento);
	}
	
	
	@GetMapping(value = "/reservas/{cpf}")
	public ResponseEntity<List<ParticipanteEvento>> findReservaByCPF(@PathVariable String cpf) {
		List<ParticipanteEvento> listParticipanteEvento = service.findReservaByCPF(cpf);
		return ResponseEntity.ok().body(listParticipanteEvento);
	}

}
