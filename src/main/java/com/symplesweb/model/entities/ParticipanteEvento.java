package com.symplesweb.model.entities;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "tab_participante_evento")
public class ParticipanteEvento implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_ParticipanteEvento;
	
	private UUID ticketCode = UUID.randomUUID();

	@ManyToOne
	@JoinColumn(name = "chave_id_participante")
	private Participante participante;

	@ManyToOne
	@JoinColumn(name = "chave_id_evento")
	private Evento evento;
	
	
	@Deprecated
	public ParticipanteEvento() {}
	
	public ParticipanteEvento(Long id_ParticipanteEvento, Participante participante, Evento evento) {
		super();
		this.id_ParticipanteEvento = id_ParticipanteEvento;
		this.participante = participante;
		this.evento = evento;
	}

	

	public Long getId_ParticipanteEvento() {
		return id_ParticipanteEvento;
	}

	public Participante getParticipante() {
		return participante;
	}
	
	public UUID getTicketCode() {
		return ticketCode;
	}

	public void setTicketCode(UUID ticketCode) {
		this.ticketCode = ticketCode;
	}

	public void setParticipante(Participante participante) {
		this.participante = participante;
	}

	public Evento getEvento() {
		return evento;
	}
	
	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	
	
	

	@Override
	public int hashCode() {
		return Objects.hash(evento, participante, id_ParticipanteEvento);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParticipanteEvento other = (ParticipanteEvento) obj;
		return Objects.equals(evento, other.evento)
				&& Objects.equals(participante, other.participante)
				&& Objects.equals(id_ParticipanteEvento, other.id_ParticipanteEvento);
	}
	
	

}
