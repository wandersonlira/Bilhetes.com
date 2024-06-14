package com.symplesweb.controller.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.symplesweb.model.entities.Participante;

public interface ParticipanteRepository extends JpaRepository<Participante, Long>{
	
	
	@Query(value = "SELECT p FROM Participante p JOIN FETCH p.participanteEventos WHERE p.cpf = :cpf")
	Optional<Participante> findByCpf(String cpf);

}
