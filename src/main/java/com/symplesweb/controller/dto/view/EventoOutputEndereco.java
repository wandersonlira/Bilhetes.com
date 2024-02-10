package com.symplesweb.controller.dto.view;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.symplesweb.model.entities.Evento;

public class EventoOutputEndereco {

	private Long idEvento;
	private String nomeEvento;
	private LocalDate dataEvento;
	private LocalDateTime horaEvento;


	
	public EventoOutputEndereco() {}
	
	
	public EventoOutputEndereco(Long idEvento, String nomeEvento, LocalDate dataEvento, LocalDateTime horaEvento) {
		super();
		this.idEvento = idEvento;
		this.nomeEvento = nomeEvento;
		this.dataEvento = dataEvento;
		this.horaEvento = horaEvento;
	}


	public EventoOutputEndereco(Evento projection) {
		super();
		idEvento = projection.getIdEvento();
		nomeEvento = projection.getNomeEvento();
		dataEvento = projection.getDataEvento();
		horaEvento = projection.getHoraEvento();

	}
	
	

	public Long getIdEvento() {
		return idEvento;
	}
	
	
	public void setIdEvento(Long idEvento) {
		this.idEvento = idEvento;
	}


	public String getNomeEvento() {
		return nomeEvento;
	}

	public void setNomeEvento(String nomeEvento) {
		this.nomeEvento = nomeEvento;
	}
	
	
	public LocalDate getDataEvento() {
		return dataEvento;
	}

	public void setDataEvento(LocalDate dataEvento) {
		this.dataEvento = dataEvento;
	}

	
	public LocalDateTime getHoraEvento() {
		return horaEvento;
	}

	public void setHoraEvento(LocalDateTime horaEvento) {
		this.horaEvento = horaEvento;
	}


	@Override
	public String toString() {
		return "EventoDTOView [idEvento=" + idEvento + ", nomeEvento=" + nomeEvento + ", dataEvento=" + dataEvento
				+ ", horaEvento=" + horaEvento + "]";
	}

	
	
}
