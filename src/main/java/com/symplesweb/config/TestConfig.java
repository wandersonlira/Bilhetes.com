package com.symplesweb.config;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.symplesweb.controller.repositories.EnderecoRepository;
import com.symplesweb.controller.repositories.EventoRepository;
import com.symplesweb.controller.repositories.ParticipanteEventoRepository;
import com.symplesweb.controller.repositories.ParticipanteRepository;
import com.symplesweb.model.entities.Endereco;
import com.symplesweb.model.entities.Evento;
import com.symplesweb.model.entities.Participante;
import com.symplesweb.model.entities.ParticipanteEvento;


@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	
	@Autowired
	EnderecoRepository enderecoRepository;
	@Autowired
	EventoRepository eventoRepository;
	@Autowired
	ParticipanteRepository participanteRepository;
	@Autowired
	ParticipanteEventoRepository participanteEventoRepository;
	
	

	@Override
	public void run(String... args) throws Exception {
		
//		------------- CRIANDO ENDERECO -------------------		
		Endereco marcoZero = new Endereco(null, "Marco Zero", null, "Santo Antônio", "Recife", "PE", null, null, "81", null, null, "Recife Antigo", "s/n");
		Endereco pacoAlfandega = new Endereco(null, "Paço Alfândega", null, "Santo Antônio", "Recife", "PE", null, null, "81", null, null, "Recife Antigo", "s/n");
		Endereco altoDaSe = new Endereco(null, "Alto da Sé", null, "Sitio Histórico Olinda", "Olinda", "PE", null, null, "81", null, null, "Sitio Histórico", "s/n");
		
		enderecoRepository.saveAll(Arrays.asList(marcoZero, pacoAlfandega, altoDaSe));
		
//		----------- CRIANDO EVENTOS ---------------------
		Evento peFestival = new Evento(null, "PE Festival", LocalDate.now(), LocalDateTime.now(), 4, 0, marcoZero);
		Evento recBeat = new Evento(null, "RecBeat", LocalDate.now(), LocalDateTime.now(), 23, 10, pacoAlfandega);
		Evento mimo = new Evento(null, "MIMO", LocalDate.now(), LocalDateTime.now(), 7, 3, altoDaSe);
		Evento bregaDelic = new Evento(null, "Brega Delic", LocalDate.now(), LocalDateTime.now(), 2, 1, altoDaSe);
		
		
		eventoRepository.saveAll(Arrays.asList(peFestival, recBeat, mimo, bregaDelic));
		
//		------------ CRIANDO PARTICIPANTE ---------------
		Participante madona = new Participante(null, "Madona", "00000000000", "madona@pop.com");
		Participante felipeMelo = new Participante(null, "Felipe Melo", "00000000001", "felipe@jogador.com");
		Participante ludmila = new Participante(null, "Ludmila", "00000000002", "ludmila@cantora.com");
		Participante silvioSantos = new Participante(null, "Silvio Santos", "00000000003", "silvio@dinheiro.com");
		Participante tarcilaAmaral = new Participante(null, "Tarcila do Amaral", "00000000004", "tarcila@arte.com");
		
//		madona.getEventos().add(bregaDelic);
//		madona.getEventos().add(mimo);
		
		
		participanteRepository.saveAll(Arrays.asList(madona, felipeMelo, ludmila, silvioSantos, tarcilaAmaral));
		
//		------------ CRIANDO PARTICIPANTE_EVENTO ---------------
		
//		tentar carregar apenas participante ou eventos 
		
		
		ParticipanteEvento participanteEventoMadona = new ParticipanteEvento(null, madona, bregaDelic);
		participanteEventoMadona.getParticipante().getEvento().add(peFestival);
		ParticipanteEvento participanteEventoMadona2 = new ParticipanteEvento(null, madona, peFestival);

		ParticipanteEvento participanteEventoSilvio = new ParticipanteEvento(null, silvioSantos, bregaDelic);
		ParticipanteEvento participanteEventoTarcila = new ParticipanteEvento(null, tarcilaAmaral, mimo);
		
		participanteEventoRepository.saveAll(Arrays.asList(participanteEventoMadona, participanteEventoSilvio, participanteEventoTarcila, participanteEventoMadona2));
		
//		=============== MAIN ===================
		
		
		System.out.println("\nPronto!!\n\n");
		
//		Optional<List<ParticipanteEvento>> list = participanteEventoRepository.buscaReservaPorCPF("00000000004");
//		
//		for (ParticipanteEvento obj : list.get()) {
//			System.out.println(obj.getParticipante());
//		}
//		
//		List<EventoDTO> result = list.stream().map(x -> new EventoDTO(x)).collect(Collectors.toList());
//		
//		for (EventoDTO obj : result) {
//			System.out.println(obj);
//		}	
		
		

		
	}

}
