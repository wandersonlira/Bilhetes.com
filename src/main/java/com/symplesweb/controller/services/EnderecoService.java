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
	
	
	public List<Endereco> findByLogradouro(String logradouro) {
		Optional<List<Endereco>> listEndereco = repository.findByLogradouro(logradouro);
		return listEndereco.get();
	}
	
	
	public Endereco save(Endereco endereco) {
		return repository.save(endereco);
	}
	
	
	public void deleteById(Long idEndereco) {
		Endereco deleteEndereco = this.findById(idEndereco);
		this.repository.delete(deleteEndereco);
	}
	

}
