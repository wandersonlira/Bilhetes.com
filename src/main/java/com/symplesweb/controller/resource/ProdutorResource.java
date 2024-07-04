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
import com.symplesweb.controller.resource.exception.InvalidUpdateException;
import com.symplesweb.controller.services.ProdutorService;
import com.symplesweb.model.entities.Produtor;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping(value = "/produtores", produces = "application/json")
@Tag(name = "Produtor", description = "Produtor é alguém responsável por criar, organizar, gerenciar e disponibiliza "
		+ "eventos para o público.")
public class ProdutorResource {
	
	@Autowired
	private ProdutorService service;
	
	
	@Operation(summary = "Cadastra um novo produtor sem nenhum evento associado", method = "POST", description = "O cadastro deve ter um "
			+ "CNPJ e E-mail válido caso contrário apresentará uma exeception, além disso, os campos não devem ser vazio.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "O status HTTP \"201 Created\" é utilizado como resposta de sucesso, indica que a "
					+ "requisição foi bem sucedida e que um novo produtor foi criado. "),
			@ApiResponse(responseCode = "400", description = "O código de status de resposta HTTP 400 Bad Request indica que o servidor não pode "
					+ "ou não irá processar a requisição devido a alguma coisa que foi entendida como um erro do cliente (por exemplo, sintaxe de "
					+ "requisição mal formada, algum campo vazio ou algum campo fora do padrão estabelecido).")
	})
	@PostMapping
	public ResponseEntity<ProdutorDtoView> save(@RequestBody ProdutorDto produtorDto) {
		Produtor produtorToSave = produtorDto.toEntity();
		Produtor produtorSaved = this.service.save(produtorToSave);
		return ResponseEntity.status(HttpStatus.CREATED).body(new ProdutorDtoView(produtorSaved));
	}
	
	
	@Operation(summary = "Exibe todos os eventos uma vez criado", method = "GET", description = "Neste endpoint é exibido "
			+ "todos os eventos cadastrados onde serão visualizados pelos participantes.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "A solicitação foi bem-sucedida"),
			@ApiResponse(responseCode = "404", description = "O servidor não pode encontrar o recurso solicitado")
	})
	@GetMapping
	public ResponseEntity<List<ProdutorDtoView>> findAll() {
		List<ProdutorDtoView> listProdutor = this.service.findAll().stream().map(produtor -> new ProdutorDtoView(produtor))
				.collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(listProdutor);
	}
	
	
	@Operation(summary = "Busca produtor baseado no parâmetro ID", method = "GET", description = "O retorno deste endpoint será uma uníca "
			+ "tupla da tabela baseado no ID passado no parâmetro e, caso exista eventos associados, todos serão exibidos.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "A solicitação foi bem-sucedida"),
			@ApiResponse(responseCode = "404", description = "O servidor não pode encontrar o recurso solicitado")
	})
	@GetMapping(value = "/{id_produtor}")
	public ResponseEntity<ProdutorDtoView> findById(@PathVariable Long id_produtor) {
		Produtor produtor = service.findById(id_produtor);
		return ResponseEntity.status(HttpStatus.OK).body(new ProdutorDtoView(produtor));
	}
	
	
	@Operation(summary = "Atualiza os dados pessoais do produtor", method = "PATH", description = "Será realizado uma busca baseado no ID "
			+ "do produtor, caso o ID exista no banco e os dados informado no corpo do método seja diferente do armazenado no banco, acontecerá a atualização dos dados.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "O status HTTP \"201 Created\" é utilizado como resposta de sucesso, indica que a "
					+ "requisição foi bem sucedida e que um novo recurso foi criado. "),
			@ApiResponse(responseCode = "400", description = "O código de status de resposta HTTP 400 Bad Request indica que o servidor não pode "
					+ "ou não irá processar a requisição devido a alguma coisa que foi entendida como um erro do cliente (por exemplo, sintaxe de "
					+ "requisição mal formada, algum campo vazio ou algum campo fora do padrão estabelecido).")
	})
	@PatchMapping
	public ResponseEntity<ProdutorDtoView> updateProdutor(
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
			throw new InvalidUpdateException("Update não autorizado! Os dados informados são iguais");
		}
		return ResponseEntity.status(HttpStatus.OK).body(new ProdutorDtoView(produtorSaved));
	}
	
	
	@Operation(summary = "Exclui produtor com ID cadastrado", method = "DELETE", description = "A exclusão acontecerá nas seguintes "
			+ "condições: 1° - ID deve existir no servidor, 2° - O produtor não deve ter eventos associado")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "404", description = "O servidor não pode encontrar o recurso solicitado"),
			@ApiResponse(responseCode = "422", description = "A solicitação não pôde ser atendida devido a erros semânticos."),
			@ApiResponse(responseCode = "500", description = "O servidor encontrou uma situação com a qual não sabe lidar.")
	})
	@DeleteMapping(value = "/{id_produtor}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable Long id_produtor) {
		this.service.deleteById(id_produtor);
	}
	

}
