package model.dao;

import java.util.List;

import model.entidades.TabParticipantes;

public interface ParticipantesDao {
	
	void insert(TabParticipantes objeto);
	boolean update(TabParticipantes objeto);
	void deleteById(Integer id);
	TabParticipantes findByCPF(String cpf);
	List<TabParticipantes> findAll();

}
