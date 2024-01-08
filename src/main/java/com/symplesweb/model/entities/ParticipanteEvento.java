//package com.symplesweb.model.entities;
//
//import java.io.Serializable;
//import java.util.Objects;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.Table;
//
//
//@Entity
//@Table(name = "tab_participanteEvento")
//public class ParticipanteEvento implements Serializable{
//	private static final long serialVersionUID = 1L;
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id_ParticipanteEvento;
//	
//	@ManyToOne
//	@JoinColumn(name = "codigo_id_participante")
//	private Participante codigo_idParticipante;
//	
//	@ManyToOne
//	@JoinColumn(name = "codigo_id_evento")
//	private Evento codigo_idEvento;
//	
//	
//	@Deprecated
//	public ParticipanteEvento() {}
//	
//	public ParticipanteEvento(Long id_ParticipanteEvento, Participante codigo_idParticipante, Evento codigo_idEvento) {
//		super();
//		this.id_ParticipanteEvento = id_ParticipanteEvento;
//		this.codigo_idParticipante = codigo_idParticipante;
//		this.codigo_idEvento = codigo_idEvento;
//	}
//	
//	
//
//	public Long getId_ParticipanteEvento() {
//		return id_ParticipanteEvento;
//	}
//
//	public void setId_ParticipanteEvento(Long id_ParticipanteEvento) {
//		this.id_ParticipanteEvento = id_ParticipanteEvento;
//	}
//
//	public Participante getCodigo_idParticipante() {
//		return codigo_idParticipante;
//	}
//
//	public void setCodigo_idParticipante(Participante codigo_idParticipante) {
//		this.codigo_idParticipante = codigo_idParticipante;
//	}
//
//	public Evento getCodigo_idEvento() {
//		return codigo_idEvento;
//	}
//	
//	
//
//	@Override
//	public int hashCode() {
//		return Objects.hash(codigo_idEvento, codigo_idParticipante, id_ParticipanteEvento);
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		ParticipanteEvento other = (ParticipanteEvento) obj;
//		return Objects.equals(codigo_idEvento, other.codigo_idEvento)
//				&& Objects.equals(codigo_idParticipante, other.codigo_idParticipante)
//				&& Objects.equals(id_ParticipanteEvento, other.id_ParticipanteEvento);
//	}
//	
//	
//	
//
//}
