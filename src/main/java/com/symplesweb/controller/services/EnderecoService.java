package com.symplesweb.controller.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.symplesweb.controller.repositories.EnderecoRepository;
import com.symplesweb.controller.services.exceptions.DatabaseException;
import com.symplesweb.controller.services.exceptions.ResourceNotFoundException;
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
		return objEndereco.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	
	public List<Endereco> findByLogradouro(String logradouro) {
		Optional<List<Endereco>> listEndereco = repository.findByLogradouro(logradouro);
		return listEndereco.get();
	}
	
	
	public Endereco findByCEP(String cep) {
		Optional<Endereco> objetoEndereco = this.repository.findByCEP(cep);
		return objetoEndereco.get();
	}
	
	
	public Endereco save(Endereco endereco) {
		Endereco address = null;
		try {
			Endereco entity = this.findByCEP(endereco.getCep());
			if (endereco.getCep().equals(entity.getCep()) && endereco.getNomeLocal().equals(entity.getNomeLocal())
					&& endereco.getNumLocal().equals(entity.getNumLocal())) {
				
				address = entity;
			}
		} catch (NoSuchElementException e) {
			address = repository.save(endereco);
		}
		
		return address;
		
	}
	
	
	public void deleteById(Long idEndereco) {
		Endereco deleteEndereco = this.findById(idEndereco);
		try {
			this.repository.delete(deleteEndereco);
		} catch (DataIntegrityViolationException exception) {
			throw new DatabaseException(exception.getMessage());
		}
		
	}
	

}
