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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/participantes", produces = "application/json")
@Tag(name = "Participantes", description = "Participantes são aqueles que compram/pegam "
		+ "ingressos gratuitamente e possui eventos associado ao seu perfil")
public class ParticipanteResource {
	
	@Autowired
	private ParticipanteService service;
	
	
	@Operation(summary = "Cadastra um novo participante que deseja ingressar em algum evento", method = "POST", description = "O cadastro deve ter um "
			+ "CPF e E-mail válido caso contrário apresentará uma exeception, além disso, os campos não devem ser vazio.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "O status HTTP \"201 Created\" é utilizado como resposta de sucesso, indica que a "
					+ "requisição foi bem sucedida e que um novo recurso foi criado. "),
			@ApiResponse(responseCode = "400", description = "O código de status de resposta HTTP 400 Bad Request indica que o servidor não pode "
					+ "ou não irá processar a requisição devido a alguma coisa que foi entendida como um erro do cliente (por exemplo, sintaxe de "
					+ "requisição mal formada, algum campo vazio ou algum campo fora do padrão estabelecido).")
	})
	@PostMapping
	public ResponseEntity<ParticipanteDtoView> save(@RequestBody @Valid ParticipanteDto participanteDto) {
		Participante entityParticipante = participanteDto.toEntity();
		this.service.save(entityParticipante); // contém o retorno da entidade com o respectivo ID
		return ResponseEntity.status(HttpStatus.CREATED).body(new ParticipanteDtoView(entityParticipante));	
	}
	
	
	@Operation(summary = "Exibe todos os participantes", method = "GET", description = "Neste endpoint é exibido "
			+ "todos os participantes uma vez cadastrado. obs: somente usuário root poderá visualizar.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "A solicitação foi bem-sucedida"),
			@ApiResponse(responseCode = "404", description = "O servidor não pode encontrar o recurso solicitado")
	})
	@GetMapping
	public ResponseEntity<List<ParticipanteDtoView>> findAll() {
		List<ParticipanteDtoView> listParticipante = service.findAll().stream().map(participante -> new ParticipanteDtoView(participante))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(listParticipante);
	}
	
	
	@Operation(summary = "Realiza busca baseado no parâmetro ID", method = "GET", description = "O retorno deste endpoint será uma uníca "
			+ "tupla da tabela baseado no ID passado no parâmetro e, caso exista eventos associados, todos serão exibidos.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "A solicitação foi bem-sucedida"),
			@ApiResponse(responseCode = "404", description = "O servidor não pode encontrar o recurso solicitado"),
			@ApiResponse(responseCode = "422", description = "A solicitação não pôde ser atendida devido a erros semânticos."),
			@ApiResponse(responseCode = "500", description = "O servidor encontrou uma situação com a qual não sabe lidar.")
	})
	@GetMapping(value = "/{id}")
	public ResponseEntity<ParticipanteDtoView> findById(@PathVariable Long id) {
		Participante objParticipante = service.findById(id);
		return ResponseEntity.ok().body(new ParticipanteDtoView(objParticipante));
	}
	
	
	@Operation(summary = "Realiza busca baseado no CPF do participante", method = "GET", description = "A busca acontecerá apenas para aqueles "
			+ "CPFs que possuem pelo menos 1(um) evento associado")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "A solicitação foi bem-sucedida"),
			@ApiResponse(responseCode = "404", description = "O servidor não pode encontrar o recurso solicitado"),
			@ApiResponse(responseCode = "422", description = "A solicitação não pôde ser atendida devido a erros semânticos."),
			@ApiResponse(responseCode = "500", description = "O servidor encontrou uma situação com a qual não sabe lidar.")
	})
	@GetMapping(value = "/search/{cpf}")
	public ResponseEntity<ParticipanteDtoView> findByCpf(@PathVariable String cpf) {
		Participante objetoParticipante = this.service.findByCpf(cpf);
		return ResponseEntity.status(HttpStatus.OK).body(new ParticipanteDtoView(objetoParticipante));
	}
	
	
	@Operation(summary = "Atualiza os dados pessoais do participante", method = "PATH", description = "Será realizado uma busca baseado no ID do "
			+ "participante, caso o ID exista no banco, acontecerá a atualização dos dados.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "O status HTTP \"201 Created\" é utilizado como resposta de sucesso, indica que a "
					+ "requisição foi bem sucedida e que um novo recurso foi criado. "),
			@ApiResponse(responseCode = "400", description = "O código de status de resposta HTTP 400 Bad Request indica que o servidor não pode "
					+ "ou não irá processar a requisição devido a alguma coisa que foi entendida como um erro do cliente (por exemplo, sintaxe de "
					+ "requisição mal formada, algum campo vazio ou algum campo fora do padrão estabelecido).")
	})
	@PatchMapping
	public ResponseEntity<ParticipanteDtoView> updateParticipante(@RequestParam(value = "participanteId") Long idParticipante, 
			@RequestBody @Valid ParticipanteUpdateDto participanteUpdateDto) {
		Participante entityParticipante = this.service.findById(idParticipante);
		entityParticipante = participanteUpdateDto.toEntity(entityParticipante);
		entityParticipante = this.service.save(entityParticipante);
		return ResponseEntity.status(HttpStatus.OK).body(new ParticipanteDtoView(entityParticipante));
	}
	
	
	@Operation(summary = "Exclui o participante com ID uma vez cadastrado", method = "DELETE", description = "A exclusão acontecerá nas seguintes "
			+ "condições: 1° - ID deve existir no servidor, 2° - O participante não deve ter eventos associado")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "404", description = "O servidor não pode encontrar o recurso solicitado"),
			@ApiResponse(responseCode = "422", description = "A solicitação não pôde ser atendida devido a erros semânticos."),
			@ApiResponse(responseCode = "500", description = "O servidor encontrou uma situação com a qual não sabe lidar.")
	})
	@DeleteMapping(value = "/{idParticipante}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteParticipanteById(@PathVariable Long idParticipante) {
		this.service.deleteById(idParticipante);
	}

}
