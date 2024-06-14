package com.symplesweb.controller.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.symplesweb.controller.dto.view.ParticipanteEventoDtoView;
import com.symplesweb.controller.services.ParticipanteEventoService;
import com.symplesweb.model.entities.ParticipanteEvento;


@RestController
@RequestMapping(value = "/participantes-eventos")
public class ParticipanteEventoResource {
	
	
	@Autowired
	ParticipanteEventoService service;
	
	
	
	@GetMapping
	public ResponseEntity<List<ParticipanteEventoDtoView>> findAll() {
		List<ParticipanteEventoDtoView> listParticipanteEvento = service.findAll();
		return ResponseEntity.ok().body(listParticipanteEvento);
	}
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ParticipanteEventoDtoView> findById(@PathVariable Long id) {
		ParticipanteEventoDtoView objParticipanteEvento = service.findById(id);
		return ResponseEntity.ok().body(objParticipanteEvento);
	}
	
	
	@GetMapping(value = "/reservas/{cpf}")
	public ResponseEntity<List<ParticipanteEventoDtoView>> findReservaByCPF(@PathVariable String cpf) {
		List<ParticipanteEventoDtoView> listParticipanteEvento = service.findReservaByCPF(cpf);
		return ResponseEntity.ok().body(listParticipanteEvento);
	}

}
