package com.symplesweb.controller.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.symplesweb.model.entities.Evento;

public class EventoDTO {

	private Long idEvento;
	private String nomeEvento;
	private LocalDate dataEvento;
	private LocalDateTime horaEvento;
	private Integer ingressos;
	private Integer ingressoComprado;
	private EnderecoDTO enderecoDTO = new EnderecoDTO();
	

	
	public EventoDTO() {}
	
	
	public EventoDTO(Long idEvento, String nomeEvento, LocalDate dataEvento, LocalDateTime horaEvento, Integer ingressos,
			Integer ingressoComprado, EnderecoDTO enderecoDTO) {
		super();
		this.idEvento = idEvento;
		this.nomeEvento = nomeEvento;
		this.dataEvento = dataEvento;
		this.horaEvento = horaEvento;
		this.ingressos = ingressos;
		this.ingressoComprado = ingressoComprado;
		this.enderecoDTO = enderecoDTO;
	}


	public EventoDTO(Evento projection) {
		super();
		idEvento = projection.getIdEvento();
		nomeEvento = projection.getNomeEvento();
		dataEvento = projection.getDataEvento();
		horaEvento = projection.getHoraEvento();
		ingressos = projection.getIngressos();
		ingressoComprado = projection.getIngressoComprado();
		enderecoDTO.setIdEndereco(projection.getEndereco().getIdEndereco());
		enderecoDTO.setNomeLocal(projection.getEndereco().getNomeLocal());
		enderecoDTO.setLogradouro(projection.getEndereco().getLogradouro());
		enderecoDTO.setNumLocal(projection.getEndereco().getNumLocal());
		enderecoDTO.setComplemento(projection.getEndereco().getComplemento());
		enderecoDTO.setBairro(projection.getEndereco().getBairro());
		enderecoDTO.setLocalidade(projection.getEndereco().getLocalidade());
		enderecoDTO.setUf(projection.getEndereco().getUf());
		enderecoDTO.setCep(projection.getEndereco().getCep());
	}
	
	

	public Long getIdEvento() {
		return idEvento;
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
	
	
	public EnderecoDTO getEnderecosDTO() {
		return enderecoDTO;
	}
	
	public void setEnderecoDTO(EnderecoDTO enderecoDTO) {
		this.enderecoDTO = enderecoDTO;
	}
	
	

	@Override
	public String toString() {
		return "EventoDTO [nomeEvento=" + nomeEvento + ", dataEvento=" + dataEvento + ", horaEvento=" + horaEvento + ", ingressos="
				+ ingressos + ", ingressoComprado=" + ingressoComprado + ", enderecosDTO=" + enderecoDTO.toString()+ "]";
	}





	
	
	
}
