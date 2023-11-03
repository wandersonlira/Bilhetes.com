package controller.producao;

import java.time.LocalDateTime;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import model.dao.DaoFactory;
import model.dao.ParticipanteEventoDao;
import model.dao.ParticipantesDao;
import model.entidades.TabEventos;
import model.entidades.TabParticipanteEvento;
import model.entidades.TabParticipantes;

public class Ingressos{	
	
	private LocalDateTime timeLocalIngresso;
	private TabParticipantes objetoParticipante;
	private TabParticipanteEvento objetoParticipanteEvento;

	
	private void inserirParticipante(TabParticipantes novoParticipante) {
		
		ParticipantesDao participantesDao = DaoFactory.createParticipantes();
		TabParticipantes findParticipante = participantesDao.findByCPF(novoParticipante.getCpf());
		this.objetoParticipante = new TabParticipantes();
		
		if (findParticipante != null) {
			
			System.out.println("CPF Existeee!!");
			
			if (findParticipante.getNomeParticipante().equals(novoParticipante.getNomeParticipante()) == false
					|| findParticipante.getEmail().equals(novoParticipante.getEmail()) == false) {
				
				System.out.println("Os nomes são Diferentes!!");
				
				this.objetoParticipante.setNomeParticipante(novoParticipante.getNomeParticipante());
				this.objetoParticipante.setEmail(novoParticipante.getEmail());
				this.objetoParticipante.setCpf(novoParticipante.getCpf());
				this.objetoParticipante.setIdParticipante(findParticipante.getIdParticipante());
				
				participantesDao.update(this.objetoParticipante);
				
			} else {
				System.out.println("Os nomes são Iguais!!");
				
				this.objetoParticipante = findParticipante;
				System.out.println("Exibindo os nome: " + this.objetoParticipante);
			}
		} else {
			System.out.println("CPF é NULL (não existe!!)");
			
			this.objetoParticipante.setNomeParticipante(novoParticipante.getNomeParticipante());
			this.objetoParticipante.setCpf(novoParticipante.getCpf());
			this.objetoParticipante.setEmail(novoParticipante.getEmail());
			participantesDao.insert(this.objetoParticipante);
		}
			
	}
	


	
	private void inserirParticipanteEvento(TabParticipantes objetoParticipante, TabEventos objetoEvento) {
		
		ParticipanteEventoDao participanteEventoDao = DaoFactory.createParticipanteEvento();
		this.objetoParticipanteEvento = new TabParticipanteEvento();
		
		this.objetoParticipanteEvento.setCodigo_idParticipante(objetoParticipante);
		this.objetoParticipanteEvento.setCodigo_idEvento(objetoEvento);
		
		participanteEventoDao.insert(this.objetoParticipanteEvento);
		
	}


	
	
	public void cadastrarIngresso(TabEventos objetoEvento, TabParticipantes novoParticipante) {
		
		inserirParticipante(novoParticipante);
		System.out.println("");
		inserirParticipanteEvento(this.objetoParticipante, objetoEvento);
		
		Integer codParticipanteEvento = this.objetoParticipanteEvento.getId_ParticipanteEvento();
		ingressoComprado(codParticipanteEvento);
		
	}	
	
	
	
	private void ingressoComprado(Integer codParticipanteEvento) {
		
		ParticipanteEventoDao participanteEventoDao = DaoFactory.createParticipanteEvento();
		
		this.objetoParticipanteEvento = participanteEventoDao.findById(codParticipanteEvento);
		
		System.out.println(
				"Evento: " + this.objetoParticipanteEvento.getCodigo_idEvento().getNomeEvento()
			+	"\nRua: " + this.objetoParticipanteEvento.getCodigo_idEvento().getCodigoEndereco().getLogradouro()
			+	", " + this.objetoParticipanteEvento.getCodigo_idEvento().getCodigoEndereco().getNumLocal()
			+	"\nBairro: " + this.objetoParticipanteEvento.getCodigo_idEvento().getCodigoEndereco().getBairro()
			+	"\nCidade: " + this.objetoParticipanteEvento.getCodigo_idEvento().getCodigoEndereco().getLocalidade()
			+	"/" + this.objetoParticipanteEvento.getCodigo_idEvento().getCodigoEndereco().getUf()
			+	"\nParticipante: " + this.objetoParticipanteEvento.getCodigo_idParticipante().getNomeParticipante()
			
			);
	}
	
	
	
	public static List<TabParticipanteEvento> selectParticipanteEvento() {
		
		ParticipanteEventoDao participanteEventoDao = DaoFactory.createParticipanteEvento();
		List<TabParticipanteEvento> listParticipanteEvento = participanteEventoDao.findAll();
		
		return listParticipanteEvento;
		
	}
	
	
	public boolean buscaParticipantePorEvento(String cpf, DefaultTableModel dtmEventos) {

		boolean valorBoleano = false;
		List<TabParticipanteEvento> listParticipanteEvento = selectParticipanteEvento();
		
		for (TabParticipanteEvento tabParticipanteEvento : listParticipanteEvento) {
			
			if (tabParticipanteEvento.getCodigo_idParticipante().getCpf().equals(cpf) == true) {
				
				valorBoleano = true;
				
				Object[] dados = {tabParticipanteEvento.getId_ParticipanteEvento(), tabParticipanteEvento.getCodigo_idParticipante().getNomeParticipante(), tabParticipanteEvento.getCodigo_idParticipante().getCpf(), 
						tabParticipanteEvento.getCodigo_idParticipante().getEmail(), tabParticipanteEvento.getCodigo_idEvento().getNomeEvento(),
						tabParticipanteEvento.getCodigo_idEvento().getCodigoEndereco().getLogradouro(), tabParticipanteEvento.getCodigo_idEvento().getCodigoEndereco().getNumLocal(),
						tabParticipanteEvento.getCodigo_idEvento().getCodigoEndereco().getBairro(), tabParticipanteEvento.getCodigo_idEvento().getCodigoEndereco().getLocalidade() +
						"/" + tabParticipanteEvento.getCodigo_idEvento().getCodigoEndereco().getUf()
						};
				dtmEventos.addRow(dados);
			}
				
		}
			
		return valorBoleano;
		
	}
	
	
	public boolean buscaEventoPorParticipante(Integer idEvento,  DefaultTableModel dtmEventos) {
		
		boolean valorBoleano = false;
		
		List<TabParticipanteEvento> listParticipanteEvento = selectParticipanteEvento();
		
		for (TabParticipanteEvento tabParticipanteEvento : listParticipanteEvento) {
			
			if (tabParticipanteEvento.getCodigo_idEvento().getIdEvento() == idEvento) {
				
				valorBoleano = true;
				
//				System.out.println(
//						 "Nome: " + tabParticipanteEvento.getCodigo_idParticipante().getNomeParticipante()
//						+ "\nCPF: " + tabParticipanteEvento.getCodigo_idParticipante().getCpf()
//						+ "\nE-mail: " + tabParticipanteEvento.getCodigo_idParticipante().getEmail()
//						+ "\n\nEvento: " + tabParticipanteEvento.getCodigo_idEvento().getNomeEvento()
//						+ "\nRua: " + tabParticipanteEvento.getCodigo_idEvento().getCodigoEndereco().getLogradouro()
//						+ ", " + tabParticipanteEvento.getCodigo_idEvento().getCodigoEndereco().getNumLocal()
//						+ "\nBairro: " + tabParticipanteEvento.getCodigo_idEvento().getCodigoEndereco().getBairro()
//						+ "\nCidade: " + tabParticipanteEvento.getCodigo_idEvento().getCodigoEndereco().getLocalidade()
//						+ "/" + tabParticipanteEvento.getCodigo_idEvento().getCodigoEndereco().getUf()
//						+ "\n---------------"
//						
//						);
				
				
				
				Object[] dados = {tabParticipanteEvento.getCodigo_idParticipante().getNomeParticipante(), tabParticipanteEvento.getCodigo_idParticipante().getCpf(), 
						tabParticipanteEvento.getCodigo_idParticipante().getEmail(), tabParticipanteEvento.getCodigo_idEvento().getIdEvento(), 
						tabParticipanteEvento.getCodigo_idEvento().getNomeEvento()
						};
				dtmEventos.addRow(dados);
				
			}
				
		}
		
//		if (valorBoleano != true) {
//			System.out.println("Nenhum participante encontrado para este Evento!");
//			return valorBoleano;
//		}
		
		return valorBoleano;

	}
	
	

	public LocalDateTime registroCadastro() {
//		return getParticipante().getTimeLocalIngresso();
		return null;
	}

	
	
	public LocalDateTime getTimeLocalIngresso() {
		return timeLocalIngresso;
	}

	public void setTimeLocalIngresso(LocalDateTime timeLocalIngresso) {
		this.timeLocalIngresso = timeLocalIngresso;
	}
	
}
