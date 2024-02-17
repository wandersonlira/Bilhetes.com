package com.symplesweb.controller.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.symplesweb.controller.dto.EventoDto;
import com.symplesweb.controller.dto.view.EventoDTOView;
import com.symplesweb.controller.services.EnderecoService;
import com.symplesweb.controller.services.EventoService;
import com.symplesweb.model.entities.Endereco;
import com.symplesweb.model.entities.Evento;

@RestController
@RequestMapping(value = "/eventos")
public class EventoResource {
	
	
	@Autowired
	private EventoService service;
	@Autowired
	private EnderecoService enderecoService;
	
	
	
	@PostMapping
	public ResponseEntity<EventoDTOView> save(@RequestBody EventoDto eventoDto) {
		
		Endereco entityEndereco = this.enderecoService.findById(eventoDto.getIdEndereco());
		
		Evento entityEvento = new Evento(null, 
				eventoDto.getNomeEvento(), 
				eventoDto.getDataEvento(), 
				eventoDto.getHoraEvento(), 
				eventoDto.getIngressos(), 
				eventoDto.getIngressoComprado(),
				entityEndereco);
		
		entityEvento = this.service.save(entityEvento);
		
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
	
	
//	@PutMapping
//	public ResponseEntity<EventoDtoView> updateEvento(
//			@RequestParam(value = "eventoId") Long IdEvento,
//			@RequestBody EventoDto eventoDto) {
//		// IMPLEMENTAR O CORPO DO MÉTODO
//	}
	
	
	@DeleteMapping(value = "/{idEvento}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable Long idEvento) {
		this.service.deleteById(idEvento);
	}
	
	

}
