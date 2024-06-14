package com.symplesweb.controller.dto;

import java.io.Serializable;

import com.symplesweb.model.entities.Endereco;

import jakarta.validation.constraints.NotEmpty;

public class EnderecoUpdateDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	@NotEmpty(message = "You didn't enter your Local!") private String nomeLocal;
//	private String logradouro;
	@NotEmpty(message = "You didn't enter your Numero Local!") private String numLocal;
//	private String complemento;
//	private String bairro;
//	private String localidade;
//	private String uf;
	@NotEmpty(message = "You didn't enter your CEP!") private String cep;
	
	
	
	public Endereco toEntity(Endereco address) {
		address.setNomeLocal(this.nomeLocal);
//		address.setLogradouro(this.logradouro);
		address.setNumLocal(this.numLocal);
//		address.setComplemento(this.complemento);
//		address.setBairro(this.bairro);
//		address.setLocalidade(this.localidade);
//		address.setUf(this.uf);
		address.setCep(this.cep);
		return address;
	}



	public String getNomeLocal() {
		return nomeLocal;
	}

	public void setNomeLocal(String nomeLocal) {
		this.nomeLocal = nomeLocal;
	}



//	public String getLogradouro() {
//		return logradouro;
//	}
//
//	public void setLogradouro(String logradouro) {
//		this.logradouro = logradouro;
//	}



	public String getNumLocal() {
		return numLocal;
	}

	public void setNumLocal(String numLocal) {
		this.numLocal = numLocal;
	}



//	public String getComplemento() {
//		return complemento;
//	}
//
//	public void setComplemento(String complemento) {
//		this.complemento = complemento;
//	}
//
//
//
//	public String getBairro() {
//		return bairro;
//	}
//
//	public void setBairro(String bairro) {
//		this.bairro = bairro;
//	}
//
//
//
//	public String getLocalidade() {
//		return localidade;
//	}
//
//	public void setLocalidade(String localidade) {
//		this.localidade = localidade;
//	}
//
//
//
//	public String getUf() {
//		return uf;
//	}
//
//	public void setUf(String uf) {
//		this.uf = uf;
//	}



	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}
	
	
	
	
	
	

}
