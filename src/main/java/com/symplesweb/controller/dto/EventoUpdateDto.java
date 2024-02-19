package com.symplesweb.controller.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.symplesweb.model.entities.Evento;

public class EventoUpdateDto {
	
	private String nomeEvento;
	private LocalDate dataEvento;
	private LocalDateTime horaEvento;
	private Integer ingressos;
	private Long idEndereco;
	
	
	
	
	public Evento toEntity(Evento evento) {
		evento.setNomeEvento(this.nomeEvento);
		evento.setDataEvento(this.dataEvento);
		evento.setHoraEvento(this.horaEvento);
		evento.setIngressos(this.ingressos);
		return evento;
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


	public Integer getIngressos() {
		return ingressos;
	}


	public void setIngressos(Integer ingressos) {
		this.ingressos = ingressos;
	}


	public Long getIdEndereco() {
		return idEndereco;
	}


	public void setIdEndereco(Long idEndereco) {
		this.idEndereco = idEndereco;
	}

	

}
