package model.entidades;


import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class TabEventos implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	private Integer idEvento;
	private String nomeEvento;
	private Date dataEvento;
	private String horaEvento;
	private Integer ingressos;
	private Integer ingressoComprado;
	private String categoria;
	private TabEndereco codigoEndereco;
	
	
	public TabEventos() {}
	
	
	public TabEventos(Integer idEvento, String nomeEvento, Date dataEvento, String horaEvento, Integer ingressos,
			Integer ingressoComprado, String categoria, TabEndereco codigoEndereco) {
		super();
		this.idEvento = idEvento;
		this.nomeEvento = nomeEvento;
		this.dataEvento = dataEvento;
		this.horaEvento = horaEvento;
		this.ingressos = ingressos;
		this.ingressoComprado = ingressoComprado;
		this.categoria = categoria;
		this.codigoEndereco = codigoEndereco;
	
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
		TabEventos other = (TabEventos) obj;
		return Objects.equals(idEvento, other.idEvento);
	}

	
	
	public Integer getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(Integer idEvento) {
		this.idEvento = idEvento;
	}

	public String getNomeEvento() {
		return nomeEvento;
	}

	public void setNomeEvento(String nomeEvento) {
		this.nomeEvento = nomeEvento;
	}

	public Date getDataEvento() {
		return dataEvento;
	}

	public void setDataEvento(Date dataEvento) {
		this.dataEvento = dataEvento;
	}

	public String getHoraEvento() {
		return horaEvento;
	}

	public void setHoraEvento(String horaEvento) {
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

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public TabEndereco getCodigoEndereco() {
		return codigoEndereco;
	}

	public void setCodigoEndereco(TabEndereco codigoEndereco) {
		this.codigoEndereco = codigoEndereco;
	}

	

	@Override
	public String toString() {
		return "Eventos [idEvento=" + idEvento + ", nomeEvento=" + nomeEvento + ", dataEvento=" + dataEvento
				+ ", horaEvento=" + horaEvento + ", ingressos=" + ingressos + ", ingressoComprado=" + ingressoComprado
				+ ", categoria=" + categoria + ", codigoEndereco=" + codigoEndereco + "]";
	}	
	

}

