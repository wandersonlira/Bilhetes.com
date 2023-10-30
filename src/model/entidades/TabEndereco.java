package model.entidades;

import java.io.Serializable;
import java.util.Objects;

public class TabEndereco implements Serializable{
	
	  private static final long serialVersionUID = 1L;
	  
	  
	  private String cep;
	  private String logradouro;
	  private String complemento;
	  private String bairro;
	  private String localidade;
	  private String uf;
	  private String ibge;
	  private String gia;
	  private String ddd;
	  private String siafi;
	  
	  private Integer idEndereco;
	  private String nomeLocal;
	  private String numLocal;
	  

	  public TabEndereco() {}

	  public TabEndereco(String cep, String logradouro, String complemento, String bairro, String localidade, String uf,
			  			String ibge, String gia, String ddd, String siafi, Integer idEndereco, String nomeLocal, String numLocal) {
		super();
		this.cep = cep;
		this.logradouro = logradouro;
		this.complemento = complemento;
		this.bairro = bairro;
		this.localidade = localidade;
		this.uf = uf;
		this.ibge = ibge;
		this.gia = gia;
		this.ddd = ddd;
		this.siafi = siafi;
		this.idEndereco = idEndereco;
		this.nomeLocal = nomeLocal;
		this.numLocal = numLocal;
	  }


	@Override
	public int hashCode() {
		return Objects.hash(idEndereco);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TabEndereco other = (TabEndereco) obj;
		return Objects.equals(idEndereco, other.idEndereco);
	}



	public Integer getIdEndereco() {
		return this.idEndereco;
	}
	
	public void setIdEndereco(Integer idEndereco) {
		this.idEndereco = idEndereco;
	}
	
	  public String getNomeLocal() {
		return nomeLocal;
	}


	public void setNomeLocal(String nomeLocal) {
		this.nomeLocal = nomeLocal;
	}


	public String getNumLocal() {
		return numLocal;
	}


	public void setNumLocal(String numLocal) {
		this.numLocal = numLocal;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getIbge() {
		return ibge;
	}

	public void setIbge(String ibge) {
		this.ibge = ibge;
	}

	public String getGia() {
		return gia;
	}

	public void setGia(String gia) {
		this.gia = gia;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getSiafi() {
		return siafi;
	}

	public void setSiafi(String siafi) {
		this.siafi = siafi;
	}


	@Override
	public String toString() {
		return "Endereco [idEndereco=" + idEndereco +  ", nomeLocal=" + nomeLocal + ", logradouro=" + logradouro + ", numLocal=" + numLocal + ", complemento=" + complemento + ", bairro="
				+ bairro + ", localidade=" + localidade + ", uf=" + uf + ", cep=" + cep + ", ddd="
				+ ddd + ", ibge=" + ibge + ", gia=" + gia +  ", siafi=" + siafi +  "]";
	}	 
	  
}
