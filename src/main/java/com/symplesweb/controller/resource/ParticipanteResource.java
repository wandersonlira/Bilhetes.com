package com.symplesweb.controller.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.symplesweb.controller.services.ParticipanteService;
import com.symplesweb.model.entities.Participante;

@RestController
@RequestMapping(value = "/participantes")
public class ParticipanteResource {
	
	@Autowired
	ParticipanteService service;
	
	
	@GetMapping
	public ResponseEntity<List<Participante>> findAll() {
		List<Participante> listParticipante = service.findAll();
		return ResponseEntity.ok().body(listParticipante);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Participante> findById(@PathVariable Long id) {
		Participante objParticipante = service.findById(id);
		return ResponseEntity.ok().body(objParticipante);
				
	}

}
