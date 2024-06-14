package com.symplesweb.model.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
	@JoinColumn(name = "chave_id_endereco", nullable = true)
	private Endereco endereco;
	@OneToMany(mappedBy = "evento")
	private Set<ParticipanteEvento> participanteEvento = new HashSet<>();
	@ManyToOne
	@JoinColumn(name = "chave_id_produtor", nullable = true)
	private Produtor produtor;



	public Produtor getProdutor() {
		return produtor;
	}

	public void setProdutor(Produtor produtor) {
		this.produtor = produtor;
	}

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
	
//	----------- Get and Set Association -----------
	
	public Endereco getEndereco() {
		return endereco;
	}
	
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@JsonIgnore
	public Set<Participante> getParticipante() {
		Set<Participante> setParticipante = new HashSet<Participante>();
		for (ParticipanteEvento participanteEvento : participanteEvento) {
			setParticipante.add(participanteEvento.getParticipante());
		}
		
		return setParticipante;
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
