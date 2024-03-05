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

import com.symplesweb.controller.dto.ProdutorDto;
import com.symplesweb.controller.dto.ProdutorUpdateDto;
import com.symplesweb.controller.dto.view.ProdutorDtoView;
import com.symplesweb.controller.services.EventoService;
import com.symplesweb.controller.services.ProdutorService;
import com.symplesweb.model.entities.Evento;
import com.symplesweb.model.entities.Produtor;


@RestController
@RequestMapping(value = "/produtores")
public class ProdutorResource {
	
	@Autowired
	private ProdutorService service;
	@Autowired
	private EventoService eventoService;
	
	
	
	@PostMapping
	public ResponseEntity<ProdutorDtoView> save(@RequestBody ProdutorDto produtorDto) {
		Produtor produtorToSave = null;
		if (produtorDto.getId_evento() != null) {
			Evento eventoToSave = this.eventoService.findById(produtorDto.getId_evento()); // usca o evento no banco
			produtorToSave = produtorDto.toEntity();
			produtorToSave.getListEventos().add(eventoToSave); // adiciona um evento na lista de produtor
		} else {
			produtorToSave = produtorDto.toEntity();
		}
		Produtor produtorSaved = this.service.save(produtorToSave);
		return ResponseEntity.status(HttpStatus.CREATED).body(new ProdutorDtoView(produtorSaved));
	}
	
	
	
	@GetMapping
	public ResponseEntity<List<ProdutorDtoView>> findAll() {
		List<ProdutorDtoView> listProdutor = this.service.findAll().stream()
				.map(produtor -> new ProdutorDtoView(produtor))
				.collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(listProdutor);
	}
	
	
	@GetMapping(value = "/{id_produtor}")
	public ResponseEntity<ProdutorDtoView> findById(@PathVariable Long id_produtor) {
		Produtor produtor = service.findById(id_produtor);
		return ResponseEntity.status(HttpStatus.OK).body(new ProdutorDtoView(produtor));
	}
	
	
	
	@PatchMapping
	public ResponseEntity<Produtor> updateProdutor(
			@RequestParam(value = "id_produtor") Long id_produtor,
			@RequestBody ProdutorUpdateDto produtorUpdateDto) {
		
		Produtor produtorSaved = null;
		Produtor produtorToUpdate = this.service.findById(id_produtor);
		if (!produtorToUpdate.getFirstName().equals(produtorUpdateDto.getFirstName())
				|| !produtorToUpdate.getLastName().equals(produtorUpdateDto.getLastName())
				|| !produtorToUpdate.getEmail().equals(produtorUpdateDto.getEmail())
				|| !produtorToUpdate.getPassword().equals(produtorUpdateDto.getPassword())) {
			Produtor produtorToSave = produtorUpdateDto.toEntity(produtorToUpdate);
			produtorSaved = this.service.save(produtorToSave);
		} else {
			System.err.println("Os dados são iguais!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(produtorSaved);
	}
	
	
	
	@DeleteMapping(value = "/{id_produtor}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable Long id_produtor) {
		this.service.deleteById(id_produtor);
	}
	

}
