package com.symples.model.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="tab_eventos")
public class Eventos implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEvento;
	private String nomeEvento;
	private LocalDate dataEvento;
	private LocalDateTime horaEvento;
	private Integer ingressos;
	private Integer ingressoComprado;
//	private String categoria; // Mudar para Enum 
//	private TabEndereco codigoEndereco; CRIAR UMA 'SET' DE ENDERECO
	
	
	
	public Eventos() {}
	
	public Eventos(Long idEvento, String nomeEvento, LocalDate dataEvento, LocalDateTime horaEvento, Integer ingressos,
			Integer ingressoComprado) {
		super();
		this.idEvento = idEvento;
		this.nomeEvento = nomeEvento;
		this.dataEvento = dataEvento;
		this.horaEvento = horaEvento;
		this.ingressos = ingressos;
		this.ingressoComprado = ingressoComprado;
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
	
	

	@Override
	public int hashCode() {
		return Objects.hash(idEvento);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Eventos other = (Eventos) obj;
		return Objects.equals(idEvento, other.idEvento);
	}
	
	

	@Override
	public String toString() {
		return "Eventos [idEvento=" + idEvento + ", nomeEvento=" + nomeEvento + ", dataEvento=" + dataEvento
				+ ", horaEvento=" + horaEvento + ", ingressos=" + ingressos + ", ingressoComprado=" + ingressoComprado
				+ "]";
	}
	
	
	
	

}
