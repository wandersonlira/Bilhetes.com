package com.symplesweb.controller.dto.view;

import java.util.ArrayList;
import java.util.List;

import com.symplesweb.model.entities.Evento;
import com.symplesweb.model.entities.Participante;

public class ParticipanteDtoView {
	
	
	
	private Long idParticipante;
	private String nomeParticipante;
	private String email;
	private List<EventoOutputParticipante> listEventoDto = new ArrayList<EventoOutputParticipante>();
	
	
	
	
	public ParticipanteDtoView() {}
//	
//	
//	private ParticipanteDto(Long idParticipante, String nomeParticipante, String email) {
//		super();
//		this.idParticipante = idParticipante;
//		this.nomeParticipante = nomeParticipante;
//		this.email = email;
//	}
	
	
	
	public ParticipanteDtoView(Participante projection) {
		
		this.idParticipante = projection.getIdParticipante();
		this.nomeParticipante = projection.getNomeParticipante();
		this.email = projection.getEmail();
		
		
		for(Evento e : projection.getEvento()) {
			EventoOutputParticipante novoEventoDto = new EventoOutputParticipante(e);
			
			listEventoDto.add(novoEventoDto);
		}
		
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



	public List<EventoOutputParticipante> getListEventoDto() {
		return listEventoDto;
	}



	public void setListEventoDto(List<EventoOutputParticipante> listEventoDto) {
		this.listEventoDto = listEventoDto;
	}



	@Override
	public String toString() {
		return "ParticipanteDtoView [idParticipante=" + idParticipante + ", nomeParticipante=" + nomeParticipante
				+ ", email=" + email + ", listEventoDto=" + listEventoDto + "]";
	}



}
