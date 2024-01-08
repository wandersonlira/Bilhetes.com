package com.symplesweb.model.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "tab_participantes")
public class Participante implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idParticipante;
	private String nomeParticipante;
	private String cpf;
	private String email;
	
//	@OneToMany(mappedBy = "codigo_idParticipante")
//	private List<ParticipanteEvento> participanteEventos = new ArrayList<>();
	@ManyToMany(mappedBy = "participantes", fetch = FetchType.LAZY	)
	private List<Evento> eventos = new ArrayList<Evento>();
	
	
	@Deprecated
	public Participante() {}
	
	public Participante(Long idParticipante, String nomeParticipante, String cpf, String email) {
		super();
		this.idParticipante = idParticipante;
		this.nomeParticipante = nomeParticipante;
		this.cpf = cpf;
		this.email = email;
	}
	
	

	public Long getIdParticipante() {
		return idParticipante;
	}

	public String getNomeParticipante() {
		return nomeParticipante;
	}

	public void setNomeParticipante(String nomeParticipante) {
		this.nomeParticipante = nomeParticipante;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public List<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}
	
	

	@Override
	public int hashCode() {
		return Objects.hash(cpf, idParticipante);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Participante other = (Participante) obj;
		return Objects.equals(cpf, other.cpf) && Objects.equals(idParticipante, other.idParticipante);
	}
	
	

	@Override
	public String toString() {
		return "Participante [idParticipante=" + idParticipante + ", nomeParticipante=" + nomeParticipante + ", cpf="
				+ cpf + ", email=" + email + "]";
	}
	
	
	

}
