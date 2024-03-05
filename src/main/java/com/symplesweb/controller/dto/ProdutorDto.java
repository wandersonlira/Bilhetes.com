package com.symplesweb.controller.dto;

import java.io.Serializable;

import com.symplesweb.model.entities.Produtor;

public class ProdutorDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Long id_evento = null;
	
	
	public ProdutorDto(String firstName, String lastName, String email, String password, Long id_evento) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.id_evento = id_evento;
	}



	public Produtor toEntity() {
		Produtor produtor = new Produtor();
		produtor.setFirstName(this.firstName);
		produtor.setLastName(this.lastName);
		produtor.setEmail(this.email);
		produtor.setPassword(this.password);
		
		return produtor;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public Long getId_evento() {
		return id_evento;
	}



	public void setId_evento(Long id_evento) {
		this.id_evento = id_evento;
	}
	
	
	
	

}
