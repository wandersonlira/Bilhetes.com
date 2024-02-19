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

import com.symplesweb.controller.dto.ParticipanteDto;
import com.symplesweb.controller.dto.ParticipanteUpdateDto;
import com.symplesweb.controller.dto.view.ParticipanteDtoView;
import com.symplesweb.controller.services.ParticipanteService;
import com.symplesweb.model.entities.Participante;

@RestController
@RequestMapping(value = "/participantes")
public class ParticipanteResource {
	
	@Autowired
	private ParticipanteService service;
	
	
	
	@PostMapping
	public ResponseEntity<ParticipanteDtoView> save(@RequestBody ParticipanteDto participanteDto) {
		
		Participante entityParticipante = participanteDto.toEntity();
		
		this.service.save(entityParticipante); // contém o retorno da entidade com o respectivo ID
		
		return ResponseEntity.status(HttpStatus.CREATED).body(new ParticipanteDtoView(entityParticipante));	
	}
	
	
	
	@GetMapping
	public ResponseEntity<List<ParticipanteDtoView>> findAll() {
		List<ParticipanteDtoView> listParticipante = service.findAll().stream().map(participante -> new ParticipanteDtoView(participante))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(listParticipante);
	}
	
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ParticipanteDtoView> findById(@PathVariable Long id) {
		Participante objParticipante = service.findById(id);
		return ResponseEntity.ok().body(new ParticipanteDtoView(objParticipante));
	}
	
	
	@PatchMapping
	public ResponseEntity<ParticipanteDtoView> updateParticipante(@RequestParam(value = "participanteId") Long idParticipante, 
			@RequestBody ParticipanteUpdateDto participanteUpdateDto) {
		
	Participante entityParticipante = this.service.findById(idParticipante);
	entityParticipante = participanteUpdateDto.toEntity(entityParticipante);
	entityParticipante = this.service.save(entityParticipante);
	
	return ResponseEntity.status(HttpStatus.OK).body(new ParticipanteDtoView(entityParticipante));
	}
	
	
	@DeleteMapping(value = "/{idParticipante}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteParticipanteById(@PathVariable Long idParticipante) {
		this.service.deleteById(idParticipante);
	}

}
