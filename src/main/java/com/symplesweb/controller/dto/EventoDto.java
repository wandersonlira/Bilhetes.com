package com.symplesweb.controller.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.symplesweb.model.entities.Evento;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class EventoDto {
	
	@NotEmpty(message = "You didn't enter your Name!")
	private String nomeEvento;
	@NotNull(message = "You didn't enter your Data!")
	private LocalDate dataEvento;
	@NotNull(message = "You didn't enter your Time!")
	private LocalDateTime horaEvento;
	@NotNull(message = "The value 'ingresso' cannot be null!")
	private Integer ingressos;
	private Integer ingressoComprado; // será excluido
	@NotNull(message = "The value 'endereco' cannot be null!")
	private Long idEndereco;
	private Long idProdutor;
	
	
	
	public EventoDto(String nomeEvento, LocalDate dataEvento, LocalDateTime horaEvento,
			Integer ingressos, Integer ingressoComprado, Long idEndereco) {
		super();
		this.nomeEvento = nomeEvento;
		this.dataEvento = dataEvento;
		this.horaEvento = horaEvento;
		this.ingressos = ingressos;
		this.ingressoComprado = ingressoComprado;
		this.idEndereco = idEndereco;
	}
	
	
	
	public Evento toEntity() {
		Evento evento = new Evento(null,
				this.nomeEvento, 
				this.dataEvento, 
				this.horaEvento, 
				this.ingressos, 
				this.ingressoComprado, 
				null);
		
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



	public Integer getIngressoComprado() {
		return ingressoComprado;
	}



	public void setIngressoComprado(Integer ingressoComprado) {
		this.ingressoComprado = ingressoComprado;
	}



	public Long getIdEndereco() {
		return idEndereco;
	}



	public void setIdEndereco(Long idEndereco) {
		this.idEndereco = idEndereco;
	}
	
	public Long getIdProdutor() {
		return idProdutor;
	}
	
	public void setIdProdutor(Long idProdutor) {
		this.idProdutor = idProdutor;
	}
	
	

}
