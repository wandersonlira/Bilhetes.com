package model.entidades;


import java.io.Serializable;
import java.util.Objects;

public class TabParticipantes implements Serializable{
	
	private static final long serialVersionUID = 1L;
	

	private Integer idParticipante;
	private String nomeParticipante;
	private String cpf;
	private String email;
//	private TabEventos codigoEvento;
	
	
	
	public TabParticipantes() {}
	
	
	public TabParticipantes(Integer idParticipante, String nomeParticipante, String cpf, String email/*, TabEventos codigoEvento*/) {
		super();
		this.idParticipante = idParticipante;
		this.nomeParticipante = nomeParticipante;
		this.cpf = cpf;
		this.email = email;
//		this.codigoEvento = codigoEvento;
	}

	

	@Override
	public int hashCode() {
		return Objects.hash(idParticipante);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TabParticipantes other = (TabParticipantes) obj;
		return Objects.equals(idParticipante, other.idParticipante);
	}
	


	public Integer getIdParticipante() {
		return idParticipante;
	}


	public void setIdParticipante(Integer idParticipante) {
		this.idParticipante = idParticipante;
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


//	public TabEventos getCodigoEventos() {
//		return codigoEvento;
//	}
//
//
//	public void setCodigoEvento(TabEventos evento) {
//		this.codigoEvento = evento;
//	}

	
	
	@Override
	public String toString() {
		return "Participantes [idParticipante=" + idParticipante + ", nomeParticipante=" + nomeParticipante + ", cpf="
				+ cpf + ", email=" + email + /* ", codigoEevento=" + codigoEvento  + */ "]";
	}
	
	
}

