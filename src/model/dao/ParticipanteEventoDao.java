package model.dao;

import java.util.List;

import model.entidades.TabParticipanteEvento;


public interface ParticipanteEventoDao {
	
	void insert(TabParticipanteEvento objeto);
	void update(TabParticipanteEvento objeto);
	void deleteById(Integer id);
	TabParticipanteEvento findById(Integer id);
	List<TabParticipanteEvento> findAll();

}
