package model.dao;

import java.util.List;

import model.entidades.TabParticipantes;

public interface ParticipantesDao {
	
	void insert(TabParticipantes objeto);
	void update(TabParticipantes objeto);
	void deleteById(Integer id);
	TabParticipantes findByCPF(String cpf);
	List<TabParticipantes> findAll();

}
