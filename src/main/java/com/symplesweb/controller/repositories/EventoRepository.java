package com.symplesweb.controller.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.symplesweb.model.entities.Evento;


@Repository
public interface EventoRepository extends JpaRepository<Evento, Long>{
	
//	@Query("FROM Evento ev WHERE ev.participanteEvento.participante.nomeParticipante = :participante")
//	Set<Evento> findByParticipanteEvento(String participante);

	@Query("SELECT ev FROM Evento ev JOIN FETCH ev.endereco WHERE ev.endereco.logradouro = :logradouro")
//	@Query(value = "SELECT ev.id_evento, ev.nome_evento, ev.data_evento, ev.hora_evento, ev.ingressos, ev.ingresso_comprado, chave_id_endereco FROM tab_eventos ev INNER JOIN tab_enderecos e ON ev.chave_id_endereco = e.id_endereco WHERE ev.nome_evento = :logradouro", nativeQuery = true)
//	Optional<List<Evento>> procuraPorLogradouro(String logradouro);
	List<Evento> procuraPorLogradouro(String logradouro);
	

//	@Query(value = "SELECT * FROM tab_eventos ev WHERE ev.nome_evento = :nomeEvento", nativeQuery = true)
	@Query("SELECT ev FROM Evento ev JOIN FETCH ev.endereco WHERE ev.nomeEvento = :nomeEvento")
	Optional<List<Evento>> searchByNomeEvento(@Param("nomeEvento") String nomeEvento);
}
