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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/participantes-eventos", produces = "application/json")
@Tag(name = "Participante-Evento", description = "Neste endpoint acontece requisitos funcionais de CRUD relacionado a compra/escolha de eventos.")
public class ParticipanteEventoResource {

	@Autowired
	private ParticipanteEventoService service;
	@Autowired
	private EventoService eventoService;
	@Autowired
	private ParticipanteService participanteService;

	@Operation(summary = "Realiza a junção do participante ao evento", method = "POST", description = "Mediante a compra/escolha do determinado evento pelo participante "
			+ "é gerado um 'tickecode' identificando cada ingresso.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "O status HTTP \"201 Created\" é utilizado como resposta de sucesso, indica que a "
					+ "requisição foi bem sucedida e que um novo produtor foi criado. "),
			@ApiResponse(responseCode = "400", description = "O código de status de resposta HTTP 400 Bad Request indica que o servidor não pode "
					+ "ou não irá processar a requisição devido a alguma coisa que foi entendida como um erro do cliente (por exemplo, sintaxe de "
					+ "requisição mal formada, algum campo vazio ou algum campo fora do padrão estabelecido).") })
	@PostMapping
	public ResponseEntity<ParticipanteEventoDtoView> save(@RequestBody ParticipanteEventoDto participanteEventoDto) {

		Participante entityParticipante = this.participanteService.findById(participanteEventoDto.getIdParticipante());
		Evento entityEvento = this.eventoService.findById(participanteEventoDto.getIdEvento());

		ParticipanteEvento novoParticipanteEvento = new ParticipanteEvento(null, entityParticipante, entityEvento);

		novoParticipanteEvento = this.service.save(novoParticipanteEvento);

		return ResponseEntity.status(HttpStatus.CREATED).body(new ParticipanteEventoDtoView(novoParticipanteEvento));
	}

	
	@Operation(summary = "Exibe todos os participantes que possui evento comprado/escolhido.", method = "GET", description = "Este endpoint retorna o 'ticketCode' "
			+ "correspondente ao ingresso, o participante e o evento.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "A solicitação foi bem-sucedida"),
			@ApiResponse(responseCode = "404", description = "O servidor não pode encontrar o recurso solicitado")
	})
	@GetMapping
	public ResponseEntity<List<ParticipanteEventoDtoView>> findAll() {
		List<ParticipanteEventoDtoView> participanteEventoDtoView = service.findAll().stream()
				.map(participanteEvento -> new ParticipanteEventoDtoView(participanteEvento))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(participanteEventoDtoView);
	}

	
	@Operation(summary = "Baseado no 'id', exibe o participante que possui evento comprado/escolhido.", method = "GET", description = "Este endpoint retorna o 'ticketCode' "
			+ "correspondente ao ingresso, o participante e seu respectivo evento.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "A solicitação foi bem-sucedida"),
			@ApiResponse(responseCode = "404", description = "O servidor não pode encontrar o recurso solicitado")
	})
	@GetMapping(value = "/{id}")
	public ResponseEntity<ParticipanteEventoDtoView> findById(@PathVariable Long id) {
		ParticipanteEvento participanteEvento = service.findById(id);
		return ResponseEntity.ok().body(new ParticipanteEventoDtoView(participanteEvento));
	}

	
	@Operation(summary = "Baseado no 'CPF', exibe o participante que possui evento comprado/escolhido.", method = "GET", description = "Este endpoint retorna o 'ticketCode' "
			+ "correspondente ao ingresso, o participante e seu respectivo evento.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "A solicitação foi bem-sucedida"),
			@ApiResponse(responseCode = "404", description = "O servidor não pode encontrar o recurso solicitado")
	})
	@GetMapping(value = "/reservas/{cpf}")
	public ResponseEntity<List<ParticipanteEventoDtoView>> findReservaByCPF(@PathVariable String cpf) {
		List<ParticipanteEvento> listParticipanteEvento = service.findReservaByCPF(cpf);
		List<ParticipanteEventoDtoView> listParticipanteEventoDtoView = listParticipanteEvento.stream()
				.map(participanteEvento -> new ParticipanteEventoDtoView(participanteEvento))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(listParticipanteEventoDtoView);
	}

	
	@Operation(summary = "Atualiza para outro 'evento'", method = "PATH", description = "Será realizado uma busca baseado no 'id' "
			+ "do Participante-Evento, caso o 'id' exista no banco e o 'id' do Evento informado no corpo do método seja diferente do armazenado no servidor, acontecerá a atualização para outro Evento.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "O status HTTP \"201 Created\" é utilizado como resposta de sucesso, indica que a "
					+ "requisição foi bem sucedida e que um novo recurso foi criado. "),
			@ApiResponse(responseCode = "400", description = "O código de status de resposta HTTP 400 Bad Request indica que o servidor não pode "
					+ "ou não irá processar a requisição devido a alguma coisa que foi entendida como um erro do cliente (por exemplo, sintaxe de "
					+ "requisição mal formada, algum campo vazio ou algum campo fora do padrão estabelecido).")
	})
	@PatchMapping
	public ResponseEntity<ParticipanteEventoDtoView> updateParticipanteEvento(
			@RequestParam(value = "idParticipanteEvento") Long idParticipanteEvento,
			@RequestBody ParticipanteEventoUpdateDto participanteEventoUpdateDto) {

		ParticipanteEvento participantEvent = this.service.findById(idParticipanteEvento);

		if (participanteEventoUpdateDto.getIdEvento() != participantEvent.getEvento().getIdEvento()) {
			Evento entityEvent = this.eventoService.findById(participanteEventoUpdateDto.getIdEvento());
			participantEvent.setEvento(entityEvent);
		}

		ParticipanteEvento participantEventToUpdated = this.service.save(participantEvent);

		return ResponseEntity.status(HttpStatus.OK).body(new ParticipanteEventoDtoView(participantEventToUpdated));
	}

	
	@Operation(summary = "Exclui o 'ingresso' baseado no 'id'", method = "DELETE", description = "A exclusão acontecerá nas seguintes "
			+ "condições: 1° - ID deve existir no servidor.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "404", description = "O servidor não pode encontrar o recurso solicitado"),
			@ApiResponse(responseCode = "422", description = "A solicitação não pôde ser atendida devido a erros semânticos."),
			@ApiResponse(responseCode = "500", description = "O servidor encontrou uma situação com a qual não sabe lidar.")
	})
	@DeleteMapping(value = "/{idParticipanteEvento}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteParticipanteEvento(@PathVariable Long idParticipanteEvento) {
		this.service.deleteById(idParticipanteEvento);
	}

}
