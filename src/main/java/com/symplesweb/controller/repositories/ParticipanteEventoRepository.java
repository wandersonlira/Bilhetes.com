package com.symplesweb.controller.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.symplesweb.model.entities.ParticipanteEvento;


public interface ParticipanteEventoRepository extends JpaRepository<ParticipanteEvento, Long> {

	
	
//	@Query(value = "SELECT * FROM ParticipanteEvento pe INNER JOIN Participantes p INNER JOIN Eventos ev INNER JOIN Endereco en "
//			+ "	ON pe.codigo_idParticipante = p.idParticipante "
//			+ "	AND pe.codigo_idEvento = ev.idEvento "
//			+ " AND ev.codigoEndereco = en.idEndereco "
//			+ "	WHERE id_ParticipanteEvento = :cpf", nativeQuery = true)
	@Query(value = "SELECT PE FROM ParticipanteEvento PE JOIN FETCH PE.participante JOIN FETCH PE.evento WHERE PE.participante.cpf = :cpf")
	Optional<List<ParticipanteEvento>> findReservaByCPF(String cpf); 
}
