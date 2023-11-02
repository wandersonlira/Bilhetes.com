package model.dao;

import java.util.List;

import model.entidades.TabEndereco;

	public interface EnderecoDao {
		
	void insert(TabEndereco objeto);
	void update(TabEndereco objeto);
	void deleteById(Integer id);
	TabEndereco findByCep(String cep);
	List<TabEndereco> findAll();
	
}
