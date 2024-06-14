package com.symplesweb.controller.dto;

import com.symplesweb.model.entities.Endereco;

public class EnderecoDTO {
	
	private Long idEndereco;
	private String nomeLocal;
	private String logradouro;
	private String numLocal;
	private String complemento;
	private String bairro;
	private String localidade;
	private String uf;
	private String cep;
	
	
	public EnderecoDTO() {}
	

	public EnderecoDTO(Long idEndereco, String nomeLocal, String logradouro, String numLocal, String complemento,
			String bairro, String localidade, String uf, String cep) {
		super();
		this.idEndereco = idEndereco;
		this.nomeLocal = nomeLocal;
		this.logradouro = logradouro;
		this.numLocal = numLocal;
		this.complemento = complemento;
		this.bairro = bairro;
		this.localidade = localidade;
		this.uf = uf;
		this.cep = cep;
	}

	
	public EnderecoDTO(Endereco projection) {
		idEndereco = projection.getIdEndereco();
		nomeLocal = projection.getNomeLocal();
		logradouro = projection.getLogradouro();
		numLocal = projection.getNumLocal();
		complemento = projection.getComplemento();
		bairro = projection.getBairro();
		localidade = projection.getLocalidade();
		uf = projection.getUf();
		cep = projection.getCep();
	}

	

	public Long getIdEndereco() {
		return idEndereco;
	}

	public void setIdEndereco(Long idEndereco) {
		this.idEndereco = idEndereco;
	}

	
	public String getNomeLocal() {
		return nomeLocal;
	}

	public void setNomeLocal(String nomeLocal) {
		this.nomeLocal = nomeLocal;
	}


	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}


	public String getNumLocal() {
		return numLocal;
	}

	public void setNumLocal(String numLocal) {
		this.numLocal = numLocal;
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


	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}


	@Override
	public String toString() {
		return "EnderecoDTO [idEndereco=" + idEndereco + ", nomeLocal=" + nomeLocal + ", logradouro=" + logradouro
				+ ", numLocal=" + numLocal + ", complemento=" + complemento + ", bairro=" + bairro + ", localidade="
				+ localidade + ", uf=" + uf + ", cep=" + cep + "]";
	}
	
	
	
	
	

}
