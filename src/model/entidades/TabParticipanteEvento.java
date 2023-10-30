package model.entidades;

import java.io.Serializable;
import java.util.Objects;

public class TabParticipanteEvento implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	
	private Integer id_ParticipanteEvento;
	private TabParticipantes codigo_idParticipante;
	private TabEventos codigo_idEvento;
	
	
	public TabParticipanteEvento() {}
	
	
	public TabParticipanteEvento(Integer id_ParticipanteEvento, TabParticipantes codigo_idParticipante, TabEventos codigo_idEvento) {
		
		super();
		this.id_ParticipanteEvento = id_ParticipanteEvento;
		this.codigo_idParticipante = codigo_idParticipante;
		this.codigo_idEvento = codigo_idEvento;
	}

	

	@Override
	public int hashCode() {
		return Objects.hash(id_ParticipanteEvento);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TabParticipanteEvento other = (TabParticipanteEvento) obj;
		return Objects.equals(id_ParticipanteEvento, other.id_ParticipanteEvento);
	}

	

	public Integer getId_ParticipanteEvento() {
		return id_ParticipanteEvento;
	}


	public void setId_ParticipanteEvento(Integer id_ParticipanteEvento) {
		this.id_ParticipanteEvento = id_ParticipanteEvento;
	}


	public TabParticipantes getCodigo_idParticipante() {
		return codigo_idParticipante;
	}


	public void setCodigo_idParticipante(TabParticipantes codigo_idParticipante) {
		this.codigo_idParticipante = codigo_idParticipante;
	}


	public TabEventos getCodigo_idEvento() {
		return codigo_idEvento;
	}


	public void setCodigo_idEvento(TabEventos codigo_idEvento) {
		this.codigo_idEvento = codigo_idEvento;
	}

	

	@Override
	public String toString() {
		return "ParticipanteEvento [id_ParticipanteEvento=" + id_ParticipanteEvento + ", codigo_idParticipante="
				+ codigo_idParticipante + ", codigo_idEvento=" + codigo_idEvento + "]";
	}
	
	
	
	

}
