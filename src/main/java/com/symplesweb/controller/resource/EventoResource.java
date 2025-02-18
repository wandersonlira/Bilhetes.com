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
import com.symplesweb.controller.services.ProdutorService;
import com.symplesweb.controller.services.exceptions.DatabaseException;
import com.symplesweb.model.entities.Endereco;
import com.symplesweb.model.entities.Evento;
import com.symplesweb.model.entities.Produtor;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/eventos", produces = "application/json")
@Tag(name = "Eventos", description = "Neste endpoint acontece requisitos funcionais de CRUD que "
		+ "são reutilizados por 'produtores' e 'participantes'.")
public class EventoResource {
	
	
	@Autowired
	private EventoService service;
	@Autowired
	private EnderecoService enderecoService;
	@Autowired
	private ProdutorService produtorService;
	
	
	@Operation(summary = "Cadastra um novo Evento", method = "POST", description = "No corpo do cadastro deve ter "
			+ "'id de Endereço' válido, quantidade de ingressos disponíveis maior que zero, caso contrário, apresentará uma exeception, "
			+ "além disso, os campos não devem ser vazio.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "O status HTTP \"201 Created\" é utilizado como resposta de sucesso, indica que a "
					+ "requisição foi bem sucedida e que um novo produtor foi criado. "),
			@ApiResponse(responseCode = "400", description = "O código de status de resposta HTTP 400 Bad Request indica que o servidor não pode "
					+ "ou não irá processar a requisição devido a alguma coisa que foi entendida como um erro do cliente (por exemplo, sintaxe de "
					+ "requisição mal formada, algum campo vazio ou algum campo fora do padrão estabelecido).")
	})
	@PostMapping
	public ResponseEntity<EventoDTOView> save(@RequestBody @Valid EventoDto eventoDto) {
		
		Evento entityEvento = eventoDto.toEntity();
		Endereco entityEndereco = this.enderecoService.findById(eventoDto.getIdEndereco());
		Produtor entityProdutor = this.produtorService.findById(eventoDto.getIdProdutor());
		
		entityEvento.setEndereco(entityEndereco);
		entityEvento.setProdutor(entityProdutor);
		// Refatorar este código para arrumar a inserção do produtor para associar cada evento ao produtor
		
		this.service.save(validaDataEvento(entityEvento));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(new EventoDTOView(entityEvento));
	}
	
	
	@Operation(summary = "Exibe todos os eventos cadastrados", method = "GET", description = "Neste endpoint é exibido "
			+ "todos os eventos cadastrados onde serão visualizados pelos participantes.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "A solicitação foi bem-sucedida"),
			@ApiResponse(responseCode = "404", description = "O servidor não pode encontrar o recurso solicitado")
	})
	@GetMapping
	public ResponseEntity<List<EventoDTOView>> findAll() {
		List<EventoDTOView> listEventos = service.findAll().stream().map(x -> new EventoDTOView(x))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(listEventos);
	}
	
	
	@Operation(summary = "Busca evento baseado no parâmetro 'id'", method = "GET", description = "O retorno deste endpoint será uma uníca "
			+ "tupla da tabela baseado no 'id' passado como parâmetro e, caso exista 'endereços' associados, todos serão exibidos.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "A solicitação foi bem-sucedida"),
			@ApiResponse(responseCode = "404", description = "O servidor não pode encontrar o recurso solicitado")
	})
	@GetMapping(value = "/{id}")
	public ResponseEntity<EventoDTOView> findById(@PathVariable Long id) {
		Evento objEventos = service.findById(id);
		return ResponseEntity.ok().body(new EventoDTOView(objEventos));
	}
	
	
	@Operation(summary = "Busca evento baseado no parâmetro 'logradouro'", method = "GET", description = "O retorno deste endpoint será uma uníca "
			+ "tupla da tabela baseado no 'logradouro' passado como parâmetro e, caso exista 'eventos' associados, todos serão exibidos.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "A solicitação foi bem-sucedida"),
			@ApiResponse(responseCode = "404", description = "O servidor não pode encontrar o recurso solicitado")
	})
	@GetMapping(value = "/enderecos/{logradouro}")
	public ResponseEntity<List<EventoDTOView>> procuraPorLogradouro(@PathVariable String logradouro) {
		List<Evento> objEventos = service.procuraPorLogradouro(logradouro);
		List<EventoDTOView> listEventoDtoView = objEventos.stream().map(x -> new EventoDTOView(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listEventoDtoView);
	}
	
	
	@Operation(summary = "Busca evento baseado no nome", method = "GET", description = "O retorno deste endpoint será uma uníca "
			+ "tupla da tabela baseado no 'nome do evento' passado como parâmetro e, caso exista 'endereços' associados, todos serão exibidos.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "A solicitação foi bem-sucedida"),
			@ApiResponse(responseCode = "404", description = "O servidor não pode encontrar o recurso solicitado")
	})
	@GetMapping(value = "/search/{nomeEvento}")
	public ResponseEntity<List<EventoDTOView>> searchByNomeEvento(@PathVariable String nomeEvento) {
		List<EventoDTOView> listEventoDtoView = service.searchByNomeEvento(nomeEvento)
				.stream().map(x -> new EventoDTOView(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listEventoDtoView);
	}
	
	
	@Operation(summary = "Atualiza os dados do 'evento'", method = "PATH", description = "Será realizado uma busca baseado no 'id' "
			+ "do evento, caso o 'id' exista no banco e os dados informado no corpo do método seja diferente do armazenado no servidor, acontecerá a atualização dos dados.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "O status HTTP \"201 Created\" é utilizado como resposta de sucesso, indica que a "
					+ "requisição foi bem sucedida e que um novo recurso foi criado. "),
			@ApiResponse(responseCode = "400", description = "O código de status de resposta HTTP 400 Bad Request indica que o servidor não pode "
					+ "ou não irá processar a requisição devido a alguma coisa que foi entendida como um erro do cliente (por exemplo, sintaxe de "
					+ "requisição mal formada, algum campo vazio ou algum campo fora do padrão estabelecido).")
	})
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
	
	
	@Operation(summary = "Exclui evento baseado no 'id'", method = "DELETE", description = "A exclusão acontecerá nas seguintes "
			+ "condições: 1° - ID deve existir no servidor, 2° - O evento não deve ter 'endereço' associado")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "404", description = "O servidor não pode encontrar o recurso solicitado"),
			@ApiResponse(responseCode = "422", description = "A solicitação não pôde ser atendida devido a erros semânticos."),
			@ApiResponse(responseCode = "500", description = "O servidor encontrou uma situação com a qual não sabe lidar.")
	})
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
