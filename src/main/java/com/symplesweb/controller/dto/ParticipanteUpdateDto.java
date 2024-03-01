package com.symplesweb.controller.dto;

import com.symplesweb.model.entities.Participante;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class ParticipanteUpdateDto {
	
	
	@NotEmpty(message = "You didn't write your name!")
	private String nomeParticipante;
	@NotEmpty(message = "Yout didn't write your e-mail")
	@Email(message = "You entered an invalid e-mail")
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
