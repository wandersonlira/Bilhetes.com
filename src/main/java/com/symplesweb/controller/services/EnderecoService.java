package com.symplesweb.controller.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.symplesweb.controller.repositories.EnderecoRepository;
import com.symplesweb.model.entities.Endereco;

@Service
public class EnderecoService {
	
	@Autowired
	private EnderecoRepository repository;
	
	
	
	public List<Endereco> findAll() {
		return repository.findAll();
	}
	
	
	public Endereco findById(Long id) {
		Optional<Endereco> objEndereco = repository.findById(id);
		return objEndereco.get();
	}

	

}
