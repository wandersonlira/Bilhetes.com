package com.symplesweb.controller.resource;

import java.time.LocalDate;
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

import com.symplesweb.controller.dto.EventoDto;
import com.symplesweb.controller.dto.EventoUpdateDto;
import com.symplesweb.controller.dto.view.EventoDTOView;
import com.symplesweb.controller.services.EnderecoService;
import com.symplesweb.controller.services.EventoService;
import com.symplesweb.controller.services.exceptions.DatabaseException;
import com.symplesweb.model.entities.Endereco;
import com.symplesweb.model.entities.Evento;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/eventos")
public class EventoResource {
	
	
	@Autowired
	private EventoService service;
	@Autowired
	private EnderecoService enderecoService;
	
	
	
	@PostMapping
	public ResponseEntity<EventoDTOView> save(@RequestBody @Valid EventoDto eventoDto) {
		
		Evento entityEvento = eventoDto.toEntity();
		Endereco entityEndereco = this.enderecoService.findById(eventoDto.getIdEndereco());
		
		entityEvento.setEndereco(entityEndereco);
		
		this.service.save(validaDataEvento(entityEvento));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(new EventoDTOView(entityEvento));
	}
	
	
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
	
	
	@PatchMapping
	public ResponseEntity<EventoDTOView> updateEvento(@RequestParam(value = "eventoId") Long IdEvento,
			@RequestBody @Valid EventoUpdateDto eventoUpdateDto) {
		
		Evento entityEvento = this.service.findById(IdEvento);
		
		if (eventoUpdateDto.getIdEndereco() != entityEvento.getEndereco().getIdEndereco()) {
			
			entityEvento.setEndereco(
					this.enderecoService.findById(eventoUpdateDto.getIdEndereco())
					);
		}
		
		Evento eventoToUpdate = eventoUpdateDto.toEntity(entityEvento);
		comparaDataAtual(eventoToUpdate);
		Evento eventoUpdated = this.service.save(eventoToUpdate);
		
		return ResponseEntity.status(HttpStatus.OK).body(new EventoDTOView(eventoUpdated));
	}
	
	
	@DeleteMapping(value = "/{idEvento}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable Long idEvento) {
			this.service.deleteById(idEvento);
	}
	
	
	
	private Evento validaDataEvento(Evento evento) {
		Endereco endereco = this.enderecoService.findById(evento.getEndereco().getIdEndereco());
		
		if (evento.getDataEvento().getDayOfYear() >= LocalDate.now().getDayOfYear() 
				&& evento.getDataEvento().getMonthValue() >= LocalDate.now().getMonthValue()
				&& evento.getDataEvento().getYear() >= LocalDate.now().getYear()) {
			for (Evento listEvento : endereco.getEventos()) {
				if (evento.getDataEvento().equals(listEvento.getDataEvento())) {
					throw new DatabaseException("Já existe evento nesta data!");
				}
			}
			return evento;
			
		} else {
			throw new DatabaseException("Data informada é menor que a data atual!");
		}
	}
	
	
	private void comparaDataAtual(Evento evento) {
		if (evento.getDataEvento().getDayOfYear() >= LocalDate.now().getDayOfYear() 
				&& evento.getDataEvento().getMonthValue() >= LocalDate.now().getMonthValue()
				&& evento.getDataEvento().getYear() >= LocalDate.now().getYear()) {
		} else {
			throw new DatabaseException("Data informada é menor que a data atual!");
		}
	}
	

}
