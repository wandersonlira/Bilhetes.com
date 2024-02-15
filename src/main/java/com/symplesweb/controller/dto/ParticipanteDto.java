package com.symplesweb.controller.dto;

import com.symplesweb.model.entities.Participante;

public class ParticipanteDto {
	
	
	
	private Long idParticipante;
	private String nomeParticipante;
	private String cpf;
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
		this.cpf = projection.getCpf();
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


	@Override
	public String toString() {
		return "ParticipanteDto [idParticipante=" + idParticipante + ", nomeParticipante=" + nomeParticipante + 
				", cpf=" + cpf + ", email=" + email + "]";
	}
	
	
	
	
	
	
	


}
