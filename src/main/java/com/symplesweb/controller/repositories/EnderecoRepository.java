package com.symplesweb.controller.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.symplesweb.model.entities.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long>{
	
	
	@Query("FROM Endereco WHERE logradouro = :logradouro")
	Optional<List<Endereco>> findByLogradouro(String logradouro);

}
