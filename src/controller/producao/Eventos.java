package controller.producao;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import org.apache.http.client.ClientProtocolException;

import controller.service.ViacepService;
import model.dao.DaoFactory;
import model.dao.EnderecoDao;
import model.dao.EventosDao;
import model.entidades.TabEndereco;
import model.entidades.TabEventos;
import model.entidades.TabParticipantes;


public class Eventos {
	
	private TabEndereco enderecoEvento;
	private TabEventos tabEventos;
	



	private void insereEvento(TabEventos objetoEvento) {
		
		this.tabEventos = new TabEventos();
		
		this.tabEventos.setNomeEvento(objetoEvento.getNomeEvento());
		this.tabEventos.setDataEvento(objetoEvento.getDataEvento());
		this.tabEventos.setHoraEvento(objetoEvento.getHoraEvento());
		this.tabEventos.setIngressos(objetoEvento.getIngressos());
		this.tabEventos.setCategoria(objetoEvento.getCategoria());
		
	}
	
	
	
	private void insereEndereco(String[] novoEndereco) {
		
		EnderecoDao novoEnderecoDao = DaoFactory.createEndereco();
		TabEndereco findEndereco = novoEnderecoDao.findByCep(novoEndereco[2]); // 'novoEndereco[2]' contém a String com o Cep
		
		if (findEndereco != null) {
			System.out.println("Diferente de NULL");
			this.enderecoEvento = findEndereco;
			System.out.println("Existe Endereco: " + this.enderecoEvento);
			
		} else {
			System.out.println("Igual a NULL");
			
			try {
				ViacepService findCep = new ViacepService();
	
				this.enderecoEvento = findCep.getEndereco(novoEndereco[2]);
				this.enderecoEvento.setNomeLocal(novoEndereco[0]);
				this.enderecoEvento.setNumLocal(novoEndereco[1]);
	
				novoEnderecoDao.insert(this.enderecoEvento);
			
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			
			} catch (IOException e) {
				e.printStackTrace();
			
			}
		}
			
	}
	
	
	private void cadastraEvento(TabEventos objetoEvento, String[] novoEndereco) {
		
		
		insereEvento(objetoEvento);
		insereEndereco(novoEndereco);
		
		this.tabEventos.setCodigoEndereco(this.enderecoEvento);
		
		EventosDao eventosDao = DaoFactory.createEventos();
		eventosDao.insert(this.tabEventos);
		
	}
	
	
	public void criarEvento(TabEventos objetoEvento, String[] novoEndereco) {

		cadastraEvento(objetoEvento, novoEndereco);	
	}


	public String exibirDataHoraLocal(Instant dataHora) {
		LocalDateTime dataHoraMundial = LocalDateTime.ofInstant(dataHora, ZoneId.systemDefault());
		DateTimeFormatter formatoDataHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").withZone(ZoneId.systemDefault());
		return formatoDataHora.format(dataHoraMundial);
	}
	
	
	public String converteDateTime(LocalDate dataHora) {
		DateTimeFormatter formatoDataHora = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return dataHora.format(formatoDataHora);
	}
	
	
	
	public static void exibirEventos(DefaultTableModel dtmEventos) {
		
		EventosDao eventosDao = DaoFactory.createEventos();
		
		List<TabEventos> listTabEventos = eventosDao.findAll();
		
		for (TabEventos evento : listTabEventos) {
				
//				System.out.println(
//						"Nº evento: " + evento.getIdEvento()
//						+ "\nNome: " + evento.getNomeEvento()
//						+ "\nData: " + evento.getDataEvento()
//						+ "\nHora: " + evento.getHoraEvento()
//						+ "\nLocal: " + evento.getCodigoEndereco().getLocalidade() + "/" + evento.getCodigoEndereco().getUf());
//						System.out.println("-----------");
			
			Object[] dados = {evento.getIdEvento(), evento.getNomeEvento(), 
					evento.getDataEvento(), evento.getHoraEvento(), 
					evento.getCodigoEndereco().getLocalidade() + "/" + evento.getCodigoEndereco().getUf()
					};
			dtmEventos.addRow(dados);
		
		}
		
	}

	
	
	public static void exibirEventoComprado(Integer idTabela) {
		
		EventosDao eventoDao = DaoFactory.createEventos();
		TabEventos tabEvento = new TabEventos(); 
		
		tabEvento = eventoDao.findById(idTabela);
		
		if (tabEvento.getIngressoComprado() > 0) {
			
			System.out.println(
					"Nº evento: " + tabEvento.getIdEvento()
					+ "\nNome: " + tabEvento.getNomeEvento()
					+ "\nData: " + tabEvento.getDataEvento()
					+ "\nLocal: " + tabEvento.getCodigoEndereco().getLocalidade() + "/" + tabEvento.getCodigoEndereco().getUf()
					+ "\nIngressos Vendidos: " + tabEvento.getIngressoComprado());
					System.out.println("-----------");
			
		} else {
			System.out.println(" --- NÃO HÁ INGRESSOS COMPRADOS PARA ESTE EVENTO! --- ");
		}
				
	}
	
	
	
	public boolean comprarIngresso(Integer codEvento, TabParticipantes novoParticipante) {
		
		boolean statusCompra = true;
		
		EventosDao eventoDao = DaoFactory.createEventos();
		TabEventos tabEventos = new TabEventos();
		
		tabEventos = eventoDao.findById(codEvento);
		
		if (tabEventos.getIngressoComprado() < tabEventos.getIngressos()) {
			
			Ingressos ingresso = new Ingressos();
			ingresso.cadastrarIngresso(tabEventos, novoParticipante);
			
			int novoIngresso = (tabEventos.getIngressoComprado() + 1);
			
			tabEventos.setIngressoComprado(novoIngresso);
			tabEventos.setIdEvento(codEvento);
			
			eventoDao.update(tabEventos);
			
			System.out.println("Qtd disponível: " + (tabEventos.getIngressos() - tabEventos.getIngressoComprado()));
			
		} else {
			statusCompra = false;
			System.out.println(" --- INGRESSO ESGOTADO! --- ");	
			}
		
		return statusCompra;	

	}

	
}
