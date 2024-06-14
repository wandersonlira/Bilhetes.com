package com.symplesweb.controller.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.br.CPF;

import com.symplesweb.model.entities.Participante;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;



public class ParticipanteDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "Você não digitou seu nome!")
	private String nomeParticipante;
	@NotEmpty(message = "Você não digitou seu CPF!")
	@CPF(message = "Digite um CPF válido!")
	private String cpf;
	@NotEmpty(message = "Você não digitou seu E-mail!")
	@Email(message = "Digite um E-mail válido!")
	private String email;



	public ParticipanteDto() {}
	
	
	public Participante toEntity() {
		Participante participant = new Participante(null,
				this.nomeParticipante,
				this.cpf,
				this.email);
		return participant;
	}

	


	public String getNomeParticipante() {
		return nomeParticipante;
	}


	public void setNomeParticipante(String nomeParticipante) {
		this.nomeParticipante = nomeParticipante;
	}
	
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	
	


}
