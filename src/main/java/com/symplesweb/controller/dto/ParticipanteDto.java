package com.symplesweb.controller.dto;

import java.io.Serializable;

import com.symplesweb.model.entities.Participante;



public class ParticipanteDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	private String nomeParticipante;
	private String cpf;
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
