package com.symplesweb.controller.DTO;

import com.symplesweb.model.entities.Participante;

public class ParticipanteDto {
	
	
	
	private Long idParticipante;
	private String nomeParticipante;
	private String email;
	
	
	
	
	public ParticipanteDto() {}
//	
//	
//	private ParticipanteDto(Long idParticipante, String nomeParticipante, String email) {
//		super();
//		this.idParticipante = idParticipante;
//		this.nomeParticipante = nomeParticipante;
//		this.email = email;
//	}
	
	
	
	public ParticipanteDto(Participante projection) {
		
		this.idParticipante = projection.getIdParticipante();
		this.nomeParticipante = projection.getNomeParticipante();
		this.email = projection.getEmail();
		
	}


	public Long getIdParticipante() {
		return idParticipante;
	}


	public void setIdParticipante(Long idParticipante) {
		this.idParticipante = idParticipante;
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


	@Override
	public String toString() {
		return "ParticipanteDto [idParticipante=" + idParticipante + ", nomeParticipante=" + nomeParticipante
				+ ", email=" + email + "]";
	}
	
	
	
	
	
	
	


}
