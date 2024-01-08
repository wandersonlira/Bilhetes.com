package com.symplesweb.config;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.symplesweb.controller.repositories.EnderecoRepository;
import com.symplesweb.controller.services.EnderecoService;
import com.symplesweb.model.entities.Endereco;
import com.symplesweb.model.entities.Evento;
import com.symplesweb.model.entities.Participante;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	@Autowired
	EnderecoRepository enderecoRepository;

	@Override
	public void run(String... args) throws Exception {
		
//		------------ CRIANDO PARTICIPANTE ---------------
		Participante madona = new Participante(null, "Madona", "00000000000", "madona@pop.com");
		Participante felipeMelo = new Participante(null, "Felipe Melo", "00000000001", "felipe@jogador.com");
		Participante ludmila = new Participante(null, "Ludmila", "00000000002", "ludmila@cantora.com");
		Participante silvioSantos = new Participante(null, "Silvio Santos", "00000000003", "silvio@dinheiro.com");
		Participante tarcilaAmaral = new Participante(null, "Tarcila do Amaral", "00000000004", "tarcila@arte.com");
		
//		----------- CRIANDO EVENTOS ---------------------
		Evento peFestival = new Evento(null, "PE Festival", LocalDate.now(), LocalDateTime.now(), 4, 0, new Endereco());
		Evento recBeat = new Evento(null, "RecBeat", LocalDate.now(), LocalDateTime.now(), 23, 10, new Endereco());
		Evento mimo = new Evento(null, "MIMO", LocalDate.now(), LocalDateTime.now(), 7, 3, new Endereco());
		Evento bregaDelic = new Evento(null, "Brega Delic", LocalDate.now(), LocalDateTime.now(), 2, 1, new Endereco());
		
//		------------- CRIANDO ENDERECO -------------------		
		Endereco marcoZero = new Endereco(null, "Marco Zero", null, "Santo Antônio", "Recife", "PE", null, null, "81", null, null, "Recife Antigo", "s/n");
		Endereco pacoAlfandega = new Endereco(null, "Paço Alfândega", null, "Santo Antônio", "Recife", "PE", null, null, "81", null, null, "Recife Antigo", "s/n");
		Endereco altoDaSe = new Endereco(null, "ALto da Sé", null, "Sitio Histórico Olinda", "Olinda", "PE", null, null, "81", null, null, "Sitio Histórico", "s/n");
		
		enderecoRepository.saveAll(Arrays.asList(marcoZero, pacoAlfandega, altoDaSe));
		
//		=============== MAIN ===================
		
//		--------- LISTANDO PARTICIPANTES ---------
		Set<Participante> participantes = new HashSet<Participante>();
		participantes.add(madona);
		participantes.add(silvioSantos);
		participantes.add(madona);
		
//		-------- LISTANDO EVENTOS -----------
		List<Evento> listEventos = new ArrayList<Evento>();
		listEventos.add(peFestival);
		listEventos.add(recBeat);
		listEventos.add(mimo);
		
//		-----------------
//		marcoZero.setEventos(listEventos);
//		peFestival.setParticipantes(participantes);
//		peFestival.setEndereco(marcoZero);
//		mimo.setEndereco(altoDaSe);
//		recBeat.setEndereco(pacoAlfandega);
//		madona.setEventos(listEventos);
		
//		System.out.println("\n{" + peFestival + "\n{" + peFestival.getEndereco() + "\n{" + peFestival.getParticipantes() + "}\n" + "}\n" + "}\n");
//		System.out.println("\n" + marcoZero + marcoZero.getEventos());
		
		System.out.println("\n" + enderecoRepository.findAll());
		
		
		
		System.out.println("\nPronto!!");
		
	}

}
