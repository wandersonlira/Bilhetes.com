package com.symplesweb.controller.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.symplesweb.controller.dto.ParticipanteEventoDto;
import com.symplesweb.controller.dto.ParticipanteEventoUpdateDto;
import com.symplesweb.controller.dto.view.ParticipanteEventoDtoView;
import com.symplesweb.controller.services.EventoService;
import com.symplesweb.controller.services.ParticipanteEventoService;
import com.symplesweb.controller.services.ParticipanteService;
import com.symplesweb.model.entities.Evento;
import com.symplesweb.model.entities.Participante;
import com.symplesweb.model.entities.ParticipanteEvento;


@RestController
@RequestMapping(value = "/participantes-eventos")
public class ParticipanteEventoResource {
	
	
	@Autowired
	private ParticipanteEventoService service;
	@Autowired
	private EventoService eventoService;
	@Autowired
	private ParticipanteService participanteService;
	
	
	
	@PostMapping
	public ResponseEntity<ParticipanteEventoDtoView> save(@RequestBody ParticipanteEventoDto participanteEventoDto) {
		
		Participante entityParticipante = this.participanteService.findById(participanteEventoDto.getIdParticipante());
		Evento entityEvento = this.eventoService.findById(participanteEventoDto.getIdEvento());
		
		ParticipanteEvento novoParticipanteEvento = new ParticipanteEvento(null, entityParticipante, entityEvento);
		
		novoParticipanteEvento = this.service.save(novoParticipanteEvento);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(new ParticipanteEventoDtoView(novoParticipanteEvento));
	}
	
	
	@GetMapping
	public ResponseEntity<List<ParticipanteEventoDtoView>> findAll() {
		List<ParticipanteEventoDtoView> participanteEventoDtoView = service.findAll()
				.stream().map(participanteEvento -> new ParticipanteEventoDtoView(participanteEvento))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(participanteEventoDtoView);
	}
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ParticipanteEventoDtoView> findById(@PathVariable Long id) {
		ParticipanteEvento participanteEvento = service.findById(id);
		return ResponseEntity.ok().body(new ParticipanteEventoDtoView(participanteEvento));
	}
	
	
	@GetMapping(value = "/reservas/{cpf}")
	public ResponseEntity<List<ParticipanteEventoDtoView>> findReservaByCPF(@PathVariable String cpf) {
		List<ParticipanteEvento> listParticipanteEvento = service.findReservaByCPF(cpf);
		List<ParticipanteEventoDtoView> listParticipanteEventoDtoView = listParticipanteEvento.stream()
				.map(participanteEvento -> new ParticipanteEventoDtoView(participanteEvento))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(listParticipanteEventoDtoView);
	}
	
	
	
	@PatchMapping
	public ResponseEntity<ParticipanteEventoDtoView> updateParticipanteEvento(
			@RequestParam(value = "idParticipanteEvento") Long idParticipanteEvento,
			@RequestBody ParticipanteEventoUpdateDto participanteEventoUpdateDto) {
		
		ParticipanteEvento participantEvent = this.service.findById(idParticipanteEvento);
		
		if (participanteEventoUpdateDto.getIdEvento() != participantEvent.getEvento().getIdEvento()) {
			Evento entityEvent = this.eventoService.findById(
					participanteEventoUpdateDto.getIdEvento());
			participantEvent.setEvento(entityEvent);
		}
		
		ParticipanteEvento participantEventToUpdated = this.service.save(participantEvent);
		
		return ResponseEntity.status(HttpStatus.OK).body(new ParticipanteEventoDtoView(participantEventToUpdated));
	}
	
	
	
	@DeleteMapping(value = "/{idParticipanteEvento}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteParticipanteEvento(@PathVariable Long idParticipanteEvento) {
		this.service.deleteById(idParticipanteEvento);
	}
	
	

}
