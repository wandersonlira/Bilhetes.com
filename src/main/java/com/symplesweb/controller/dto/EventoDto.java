package com.symplesweb.controller.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class EventoDto {
	
	private Long idEvento;
	private String nomeEvento;
	private LocalDate dataEvento;
	private LocalDateTime horaEvento;
	private Integer ingressos;
	private Integer ingressoComprado;
	private Long idEndereco;
	
	
	
	public EventoDto(Long idEvento, String nomeEvento, LocalDate dataEvento, LocalDateTime horaEvento,
			Integer ingressos, Integer ingressoComprado, Long idEndereco) {
		super();
		this.idEvento = idEvento;
		this.nomeEvento = nomeEvento;
		this.dataEvento = dataEvento;
		this.horaEvento = horaEvento;
		this.ingressos = ingressos;
		this.ingressoComprado = ingressoComprado;
		this.idEndereco = idEndereco;
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
	
	
	
	
	

	
	
	
	

}
