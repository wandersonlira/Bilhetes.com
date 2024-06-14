package com.symplesweb.controller.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.symplesweb.controller.dto.view.EnderecoDTOView;
import com.symplesweb.controller.repositories.EnderecoRepository;
import com.symplesweb.model.entities.Endereco;

@Service
public class EnderecoService {
	
	@Autowired
	private EnderecoRepository repository;
	
	
	
	public List<EnderecoDTOView> findAll() {
		return repository.findAll().stream().map(endereco -> new EnderecoDTOView(endereco)).collect(Collectors.toList());
	}
	
	
	public EnderecoDTOView findById(Long id) {
		Optional<Endereco> objEndereco = repository.findById(id);
		return new EnderecoDTOView(objEndereco.get());
	}
	
	
	public List<EnderecoDTOView> findByLogradouro(String logradouro) {
		Optional<List<Endereco>> listEndereco = repository.findByLogradouro(logradouro);
		return listEndereco.get().stream().map(endereco -> new EnderecoDTOView(endereco)).collect(Collectors.toList());
	}

	

}
