package com.symplesweb.controller.dto.view;

import java.util.ArrayList;
import java.util.List;

import com.symplesweb.controller.dto.ParticipanteDto;
import com.symplesweb.model.entities.ParticipanteEvento;

public class ParticipanteEventoDtoView {
	
	
	private Long participanteEventoDtoView;
	private ParticipanteDto participanteDto = new ParticipanteDto();
	private List<EventoOutputParticipante> listEventoDto = new ArrayList<EventoOutputParticipante>();
	
	
	
	
	public ParticipanteEventoDtoView() {}
	
	
	public ParticipanteEventoDtoView(ParticipanteEvento projection) {
		
		participanteEventoDtoView = projection.getId_ParticipanteEvento();
		
		participanteDto.setIdParticipante(projection.getParticipante().getIdParticipante());
		participanteDto.setNomeParticipante(projection.getParticipante().getNomeParticipante());
		participanteDto.setEmail(projection.getParticipante().getEmail());
		
		EventoOutputParticipante eventoDto = new EventoOutputParticipante(projection.getEvento());
		
		listEventoDto.add(eventoDto);
		
	}

	
	

	public Long getParticipanteEventoDtoView() {
		return participanteEventoDtoView;
	}


	public void setParticipanteEventoDtoView(Long participanteEventoDtoView) {
		this.participanteEventoDtoView = participanteEventoDtoView;
	}


	public ParticipanteDto getParticipanteDto() {
		return participanteDto;
	}


	public void setParticipanteDto(ParticipanteDto participanteDto) {
		this.participanteDto = participanteDto;
	}


	public List<EventoOutputParticipante> getListEventoDto() {
		return listEventoDto;
	}


	public void setListEventoDto(List<EventoOutputParticipante> listEventoDto) {
		this.listEventoDto = listEventoDto;
	}
	
	
	
	
	

}
