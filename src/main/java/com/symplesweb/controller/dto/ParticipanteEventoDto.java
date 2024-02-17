package com.symplesweb.controller.dto;

public class ParticipanteEventoDto {
	
	private Long id_ParticipanteEvento;
	private Long idParticipante;
	private Long idEvento;
	
	
	
	public ParticipanteEventoDto(Long id_ParticipanteEvento, Long idParticipante, Long idEvento) {
		super();
		this.id_ParticipanteEvento = id_ParticipanteEvento;
		this.idParticipante = idParticipante;
		this.idEvento = idEvento;
	}
	
	
	public Long getId_ParticipanteEvento() {
		return id_ParticipanteEvento;
	}
	public void setId_ParticipanteEvento(Long id_ParticipanteEvento) {
		this.id_ParticipanteEvento = id_ParticipanteEvento;
	}
	public Long getIdParticipante() {
		return idParticipante;
	}
	public void setIdParticipante(Long participante) {
		this.idParticipante = participante;
	}
	public Long getIdEvento() {
		return idEvento;
	}
	public void setIdEvento(Long evento) {
		this.idEvento = evento;
	}
	
	
	
	

}
