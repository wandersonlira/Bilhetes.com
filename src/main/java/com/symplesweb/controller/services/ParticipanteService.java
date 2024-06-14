package com.symplesweb.controller.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.symplesweb.controller.repositories.ParticipanteRepository;
import com.symplesweb.controller.services.exceptions.DatabaseException;
import com.symplesweb.controller.services.exceptions.ResourceNotFoundException;
import com.symplesweb.model.entities.Participante;

@Service
public class ParticipanteService {

	@Autowired
	ParticipanteRepository repository;
	
	
	
	public List<Participante> findAll() {
		return repository.findAll();
	}
	
	
	
	public Participante findById(Long id) {
		Optional<Participante> objParticipante = repository.findById(id);
		return objParticipante.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	
	
	public Participante findByCpf(String cpf) {
		Optional<Participante> objetoParticipante = this.repository.findByCpf(cpf);
		return objetoParticipante.orElseThrow(() -> new ResourceNotFoundException(cpf));
	}
	
	
	
	public Participante save(Participante participante) {
		return repository.save(participante);
	}
	

	
	public void deleteById(Long idParticipante) {
		Participante deleteParticipante = this.findById(idParticipante);
		try {
			this.repository.delete(deleteParticipante);
			
		} catch (DataIntegrityViolationException e){
			throw new DatabaseException(e.getMessage());
			
		}
		
	}
	
	

}
