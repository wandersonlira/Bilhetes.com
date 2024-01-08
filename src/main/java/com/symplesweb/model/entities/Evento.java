package com.symplesweb.model.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="tab_eventos")
public class Evento implements Serializable{
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
	
	@ManyToOne
	@JoinColumn(name = "codigo_id_endereco", nullable = true)
	private Endereco endereco;
	
//	@OneToMany(mappedBy = "codigo_idEvento")
//	private Set<ParticipanteEvento> eventoParticipantes = new HashSet<>();
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tab_eventos_participantes",
			joinColumns = @JoinColumn(name = "codigo_id_evento"),
			inverseJoinColumns = @JoinColumn(name = "codigo_id_participante"))
	private Set<Participante> participantes = new HashSet<>();
	
	
	
	@Deprecated
	public Evento() {}
	
	public Evento(Long idEvento, String nomeEvento, LocalDate dataEvento, LocalDateTime horaEvento, Integer ingressos,
			Integer ingressoComprado, Endereco endereco) {
		super();
		this.idEvento = idEvento;
		this.nomeEvento = nomeEvento;
		this.dataEvento = dataEvento;
		this.horaEvento = horaEvento;
		this.ingressos = ingressos;
		this.ingressoComprado = ingressoComprado;
		this.endereco = endereco;
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
	
//	-----------
	
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Set<Participante> getParticipantes() {
		return participantes;
	}

	public void setParticipantes(Set<Participante> participantes) {
		this.participantes = participantes;
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
		Evento other = (Evento) obj;
		return Objects.equals(idEvento, other.idEvento);
	}
	
	

	@Override
	public String toString() {
		return "Eventos [idEvento=" + idEvento + ", nomeEvento=" + nomeEvento + ", dataEvento=" + dataEvento
				+ ", horaEvento=" + horaEvento + ", ingressos=" + ingressos + ", ingressoComprado=" + ingressoComprado
				+ "]";
	}
	
	
	
	

}
