package com.symplesweb.controller.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.symplesweb.controller.repositories.ProdutorRepository;
import com.symplesweb.model.entities.Produtor;

@Service
public class ProdutorService {
	
	@Autowired
	private ProdutorRepository repository;
	
	
	
	public Produtor save(Produtor produtor) {
		return this.repository.save(produtor);
	}
	
	
	
	public List<Produtor> findAll() {
		return this.repository.findAll();
	}
	
	
	public Produtor findById(Long id_produtor) {
		return this.repository.findById(id_produtor).get();
	}
	
	
	public void deleteById(Long id_produtor) {
		Produtor deleteProdutor = this.findById(id_produtor);
		this.repository.delete(deleteProdutor);
	}

}
