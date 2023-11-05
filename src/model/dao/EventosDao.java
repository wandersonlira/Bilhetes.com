package model.dao;

import java.util.List;

import model.entidades.TabEventos;


public interface EventosDao {
	
	Integer insert(TabEventos objeto);
	boolean update(TabEventos objeto);
	void deleteById(Integer id);
	TabEventos findById(Integer id);
	List<TabEventos> findAll();

}
