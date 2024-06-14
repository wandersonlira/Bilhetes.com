package com.symplesweb.controller.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.symplesweb.model.entities.Produtor;

@Repository
public interface ProdutorRepository extends JpaRepository<Produtor, Long>{

}
