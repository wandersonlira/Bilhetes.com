package com.symplesweb.controller.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.symplesweb.controller.dto.view.EnderecoDTOView;
import com.symplesweb.controller.services.EnderecoService;
import com.symplesweb.model.entities.Endereco;

@RestController
@RequestMapping(value = "/enderecos")
public class EnderecoResource {
	
	@Autowired
	private EnderecoService service;
	
	
	@GetMapping
	public ResponseEntity<List<EnderecoDTOView>> findAll() {
		List<EnderecoDTOView> listEnderecos = service.findAll();
		return ResponseEntity.ok().body(listEnderecos);
	}
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<EnderecoDTOView> findById(@PathVariable Long id) {
		EnderecoDTOView objEndereco = service.findById(id);
		return ResponseEntity.ok().body(objEndereco);
	}
	
	
	@GetMapping(value = "/logradouro/{logradouro}")
	public ResponseEntity<List<EnderecoDTOView>> findByLogradouro(@PathVariable String logradouro) {
		List<EnderecoDTOView> listEnderecos = service.findByLogradouro(logradouro);
		return ResponseEntity.ok().body(listEnderecos);
	}

}
