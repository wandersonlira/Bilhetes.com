package com.symplesweb.controller.dto;

import com.symplesweb.model.entities.Participante;

public class ParticipanteUpdateDto {
	
	
	
	private String nomeParticipante;
	private String email;
	
	
	
	public Participante toEntity(Participante participante) {
		participante.setNomeParticipante(this.nomeParticipante);
		participante.setCpf(this.email);
		return participante;
	}
	
	
	
	
	
	
	public String getNomeParticipante() {
		return nomeParticipante;
	}
	public void setNomeParticipante(String nomeParticipante) {
		this.nomeParticipante = nomeParticipante;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	

}
