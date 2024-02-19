package com.symplesweb.controller.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.symplesweb.controller.repositories.ParticipanteEventoRepository;
import com.symplesweb.model.entities.ParticipanteEvento;

@Service
public class ParticipanteEventoService {
	
	
	@Autowired
	ParticipanteEventoRepository repository;
	
	
	
	public ParticipanteEvento save(ParticipanteEvento participanteEvento) {
		return this.repository.save(participanteEvento);
	}
	
	
	public List<ParticipanteEvento> findAll(){
		return repository.findAll();
	}
	
	
	public ParticipanteEvento findById(Long id) {
		Optional<ParticipanteEvento> objParticipanteEvento = repository.findById(id);
		return objParticipanteEvento.get();
	}

	
//	@Transactional(readOnly = true)
	public List<ParticipanteEvento> findReservaByCPF(String cpf) {
		Optional<List<ParticipanteEvento>> listParticipanteEvento = repository.findReservaByCPF(cpf);
		return listParticipanteEvento.get();
	}
	
	
	public void deleteById(Long idParticipanteEvento) {
		ParticipanteEvento deleteParticipanteEvento = this.findById(idParticipanteEvento);
		this.repository.delete(deleteParticipanteEvento);
	}
	
	
}
