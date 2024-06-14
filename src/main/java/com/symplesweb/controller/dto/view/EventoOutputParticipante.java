package com.symplesweb.controller.dto.view;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.symplesweb.controller.dto.EnderecoDTO;
import com.symplesweb.model.entities.Evento;

public class EventoOutputParticipante {

	private Long idEvento;
	private String nomeEvento;
	private LocalDate dataEvento;
	private LocalDateTime horaEvento;
	private EnderecoDTO enderecoDTO = new EnderecoDTO();
	


	public EventoOutputParticipante() {}
	
	
	public EventoOutputParticipante(Long idEvento, String nomeEvento, LocalDate dataEvento, LocalDateTime horaEvento) {
		super();
		this.idEvento = idEvento;
		this.nomeEvento = nomeEvento;
		this.dataEvento = dataEvento;
		this.horaEvento = horaEvento;
	}


	public EventoOutputParticipante(Evento projection) {
		super();
		idEvento = projection.getIdEvento();
		nomeEvento = projection.getNomeEvento();
		dataEvento = projection.getDataEvento();
		horaEvento = projection.getHoraEvento();
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

	
	public EnderecoDTO getEnderecoDTO() {
		return enderecoDTO;
	}


	public void setEnderecoDTO(EnderecoDTO enderecoDTO) {
		this.enderecoDTO = enderecoDTO;
	}


	@Override
	public String toString() {
		return "EventoDtoParticipanteView [idEvento=" + idEvento + ", nomeEvento=" + nomeEvento + ", dataEvento="
				+ dataEvento + ", horaEvento=" + horaEvento + ", enderecoDTO=" + enderecoDTO + "]";
	}




	
	
}
