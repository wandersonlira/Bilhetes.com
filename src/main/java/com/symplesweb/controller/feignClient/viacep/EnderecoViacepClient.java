package com.symplesweb.controller.feignClient.viacep;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.symplesweb.model.entities.Endereco;

@FeignClient(url = "https://viacep.com.br/ws/", name = "apiViacep")
public interface EnderecoViacepClient {
	
	@GetMapping(value = "/{cep}/json/")
	Endereco apiEndereco(@PathVariable String cep);
	
	

}
