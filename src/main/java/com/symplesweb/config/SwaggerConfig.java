package com.symplesweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {
	
	@Bean
	public OpenAPI customAPI() {
		return new OpenAPI().info(new Info().title("Symples Ingressos Online API").version("1.0.0").description(
				"SIO é um microsserviço de bilheteria que tem o propósito de conectar pessoas "
				+ "por meio de eventos. Seu principal objetivo é simplificar o acesso dando praticidade "
				+ "aos produtores de eventos que buscam mais autonomia na disposição gratuita e gestão "
				+ "do seu evento, e do público, que deseja encontrar experiências diversas na sua cidade.")
				.license(new License().name("Licensa do Sistema").url("www.lira.com.br")));
	}

}
