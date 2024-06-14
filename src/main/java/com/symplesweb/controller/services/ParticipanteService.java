package com.symplesweb.controller.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.symplesweb.controller.dto.view.ParticipanteDtoView;
import com.symplesweb.controller.repositories.ParticipanteRepository;
import com.symplesweb.model.entities.Participante;

@Service
public class ParticipanteService {

	@Autowired
	ParticipanteRepository repository;
	
	
	
	public List<ParticipanteDtoView> findAll() {
		return repository.findAll().stream().map(participante -> new ParticipanteDtoView(participante))
				.collect(Collectors.toList());
	}
	
	
	public ParticipanteDtoView findById(Long id) {
		Optional<Participante> objParticipante = repository.findById(id);
		return new ParticipanteDtoView(objParticipante.get());
	}
}
