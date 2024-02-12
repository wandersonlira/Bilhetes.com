package com.symplesweb.controller.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.symplesweb.controller.dto.view.ParticipanteDtoView;
import com.symplesweb.controller.services.ParticipanteService;
import com.symplesweb.model.entities.Participante;

@RestController
@RequestMapping(value = "/participantes")
public class ParticipanteResource {
	
	@Autowired
	private ParticipanteService service;
	
	
	
	@GetMapping
	public ResponseEntity<List<ParticipanteDtoView>> findAll() {
		List<ParticipanteDtoView> listParticipante = service.findAll();
		return ResponseEntity.ok().body(listParticipante);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ParticipanteDtoView> findById(@PathVariable Long id) {
		ParticipanteDtoView objParticipante = service.findById(id);
		return ResponseEntity.ok().body(objParticipante);
				
	}
	
	
	
	@PostMapping
	public ResponseEntity<Participante> save(@RequestBody Participante participante) {
		Participante p = service.save(participante);
		return ResponseEntity.status(HttpStatus.CREATED).body(p);
		
	}

}
