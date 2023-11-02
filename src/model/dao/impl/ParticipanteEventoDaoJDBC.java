package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controller.exception.DbException;
import model.connection.DbConexao;
import model.dao.ParticipanteEventoDao;
import model.entidades.TabEndereco;
import model.entidades.TabEventos;
import model.entidades.TabParticipanteEvento;
import model.entidades.TabParticipantes;

public class ParticipanteEventoDaoJDBC implements ParticipanteEventoDao{
	
	Connection conexao;
	
	public ParticipanteEventoDaoJDBC (Connection conexao) {
		this.conexao = conexao;
	}
	
	

	@Override
	public void insert(TabParticipanteEvento objeto) {
		
		PreparedStatement stConsulta = null;
		
		try {
			stConsulta = conexao.prepareStatement(
					"INSERT INTO ParticipanteEvento (codigo_idParticipante, codigo_idEvento) "
					+ "VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
			
			stConsulta.setInt(1, objeto.getCodigo_idParticipante().getIdParticipante());
			stConsulta.setInt(2, objeto.getCodigo_idEvento().getIdEvento());
			
			int rowsAffected = stConsulta.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rsResultado = stConsulta.getGeneratedKeys();
				
				if (rsResultado.next()) {
					int idNovo = rsResultado.getInt(1);
					objeto.setId_ParticipanteEvento(idNovo);
				}
				
				DbConexao.closeResultSet(rsResultado);
				
			} else {
				throw new DbException("Aconteceu algum erro... nenhuma linha alterada!!");
				}
		
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		
		} finally {
			DbConexao.closeStatement(stConsulta);
			}
		
	}

	
	
	@Override
	public void update(TabParticipanteEvento objeto) {
		
		PreparedStatement stConsulta = null;
		
		try {
			stConsulta = conexao.prepareStatement(
					"UPDATE ParticipanteEvento "
					+ "SET codigo_idParticipante = ?, codigo_idEvento = ? "
					+ "WHERE id_ParticipanteEvento = ?");
			
			stConsulta.setInt(1, objeto.getCodigo_idParticipante().getIdParticipante());
			stConsulta.setInt(2, objeto.getCodigo_idEvento().getIdEvento());
			stConsulta.setInt(3, objeto.getId_ParticipanteEvento());
			
			int rowsAffected = stConsulta.executeUpdate();
			
			if (rowsAffected > 0) {
				System.out.println("Update realizado com sucesso!");
				
			} else {
				System.out.println("Houve um erro inesperado... nenhuma linha afetada!!");
			}
		
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		
		} finally {
			DbConexao.closeStatement(stConsulta);
			}
		
	}
	
	
	
	@Override
	public void deleteById(Integer id) {
		
		PreparedStatement stConsulta = null;
		
		try {
			stConsulta = conexao.prepareStatement(
					"DELETE FROM ParticipanteEvento "
					+ "WHERE id_ParticipanteEvento = ?");
			
			stConsulta.setInt(1, id);
			
			int rowsAffected = stConsulta.executeUpdate();
			
			if (rowsAffected == 0) {
				System.out.println("Erro inesperado... 'id' informado não existe na tabela");
			}
			
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
			
		} finally {
			DbConexao.closeStatement(stConsulta);
		}
		
	}
	
	

	@Override
	public TabParticipanteEvento findById(Integer id) {
		
		PreparedStatement stConsulta = null;
		ResultSet rsResultado = null;
		
		try {
			
			stConsulta = conexao.prepareStatement(""
				+ "SELECT * FROM ParticipanteEvento pe INNER JOIN Participantes p INNER JOIN Eventos ev INNER JOIN Endereco en "
				+ "	ON pe.codigo_idParticipante = p.idParticipante "
				+ "	AND pe.codigo_idEvento = ev.idEvento "
				+ " AND ev.codigoEndereco = en.idEndereco "
				+ "	WHERE id_ParticipanteEvento = ?");
			
			stConsulta.setInt(1, id);
			
			rsResultado = stConsulta.executeQuery();
			
			if (rsResultado.next()) {
				
				TabEndereco endereco = instanciaEndereco(rsResultado);
				
				TabEventos evento = instanciaEventos(rsResultado, endereco);
				
				TabParticipantes participante = instanciaParticipantes(rsResultado); // retirado o tipo Evento proposital
				
				TabParticipanteEvento participanteEvento = instanciaParticipanteEvento(rsResultado, evento, participante);
				return participanteEvento;
			}
			
			return null;
			
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DbConexao.closeStatement(stConsulta);
			DbConexao.closeResultSet(rsResultado);	
		}
	}
	
	

	@Override
	public List<TabParticipanteEvento> findAll() {
		
		PreparedStatement stConsulta = null;
		ResultSet rsResultado = null;
		
		try {
			stConsulta = conexao.prepareStatement(
					"SELECT * FROM ParticipanteEvento pe INNER JOIN Participantes p INNER JOIN Eventos ev INNER JOIN Endereco en "
					+ "	ON pe.codigo_idParticipante = p.idParticipante "
					+ "	AND pe.codigo_idEvento = ev.idEvento "
					+ "	AND ev.codigoEndereco = en.idEndereco "
					+ "	ORDER BY pe.id_ParticipanteEvento");
			
			rsResultado = stConsulta.executeQuery();
			
			List<TabParticipanteEvento> listParticipanteEvento = new ArrayList<>();
			List<TabParticipantes> listParticipantes = new ArrayList<>();
			List<TabEventos> listEventos = new ArrayList<>();
			Map<Integer, TabParticipantes> mapParticipantes = new HashMap<>();
			Map<Integer, TabEventos> mapEventos = new HashMap<>();
			Map<Integer, TabEndereco> mapEndereco = new HashMap<>();
			
			while (rsResultado.next()) {
				
				TabEndereco pegaEndereco = mapEndereco.get(rsResultado.getInt("codigoEndereco"));
				
				if (pegaEndereco == null) {
					pegaEndereco = instanciaEndereco(rsResultado);
					mapEndereco.put(rsResultado.getInt("codigoEndereco"), pegaEndereco);
				}
				
				TabEventos evento = instanciaEventos(rsResultado, pegaEndereco);
				listEventos.add(evento);
				
//				------------------------ Cada tipo 'Evento' abaixo contém seu respectivo endereco acoplado na lista -------------------
				
				TabEventos pegaEvento = mapEventos.get(rsResultado.getInt("codigo_idEvento"));
				
				if (pegaEvento == null) {
					pegaEvento = instanciaEventos(rsResultado, pegaEndereco);
					mapEventos.put(rsResultado.getInt("codigo_idEvento"), pegaEvento);
				}
				
//				Participantes participante = instanciaParticipantes(rsResultado, pegaEvento);
				TabParticipantes participante = instanciaParticipantes(rsResultado);
				listParticipantes.add(participante);
				
				
				
//				------------------------ Cada tipo 'Participante' abaixo contém seu respectivo Evento acoplado na lista -------------------
				
				TabParticipantes pegaParticipante = mapParticipantes.get(rsResultado.getInt("codigo_idParticipante"));
				
				if (pegaParticipante == null) {
//					pegaParticipante = instanciaParticipantes(rsResultado, pegaEvento);
					pegaParticipante = instanciaParticipantes(rsResultado);
					mapParticipantes.put(rsResultado.getInt("codigo_idParticipante"), pegaParticipante);
				}
				
				
			
//				este seráa última parte 
				TabParticipanteEvento participanteEvento = instanciaParticipanteEvento(rsResultado, pegaEvento, pegaParticipante);
				listParticipanteEvento.add(participanteEvento);
			}
			
			return listParticipanteEvento;
			
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		
		} finally {
			DbConexao.closeResultSet(rsResultado);
			DbConexao.closeStatement(stConsulta);
		}
		
	}
	
	
	
	private TabParticipanteEvento instanciaParticipanteEvento(ResultSet resultadoTab, TabEventos eventos, TabParticipantes participantes) throws SQLException {
		
		TabParticipanteEvento novoParticipanteEvento = new TabParticipanteEvento();
		
		novoParticipanteEvento.setId_ParticipanteEvento(resultadoTab.getInt("id_ParticipanteEvento"));
		novoParticipanteEvento.setCodigo_idParticipante(participantes);
		novoParticipanteEvento.setCodigo_idEvento(eventos);
		
		return novoParticipanteEvento;
	}
	
	
	private TabParticipantes instanciaParticipantes(ResultSet resultadoTab) throws SQLException {
		
		TabParticipantes novoParticipante = new TabParticipantes();
		
		novoParticipante.setIdParticipante(resultadoTab.getInt("idParticipante"));
		novoParticipante.setNomeParticipante(resultadoTab.getString("nomeParticipante"));
		novoParticipante.setCpf(resultadoTab.getString("cpf"));
		novoParticipante.setEmail(resultadoTab.getString("email")); 
//		novoParticipante.setCodigoEvento(eventos);
		
		return novoParticipante;
		
	}
	
	
	private TabEventos instanciaEventos(ResultSet resultadoTab, TabEndereco endereco) throws SQLException {
		
		TabEventos novoEvento = new TabEventos();
		
		novoEvento.setIdEvento(resultadoTab.getInt("idEvento"));
		novoEvento.setNomeEvento(resultadoTab.getString("nomeEvento"));
		novoEvento.setDataEvento(resultadoTab.getDate("dataEvento"));
		novoEvento.setHoraEvento(resultadoTab.getString("horaEvento"));
		novoEvento.setIngressos(resultadoTab.getInt("ingressos"));
		novoEvento.setIngressoComprado(resultadoTab.getInt("ingressoComprado"));
		novoEvento.setCategoria(resultadoTab.getString("categoria"));
		novoEvento.setCodigoEndereco(endereco);
		
		return novoEvento;
	}
	
	
	private TabEndereco instanciaEndereco(ResultSet resultadoTab) throws SQLException {
		
		TabEndereco novoEndereco = new TabEndereco();
		
		novoEndereco.setIdEndereco(resultadoTab.getInt("idEndereco"));
		novoEndereco.setNomeLocal(resultadoTab.getString("nomeLocal"));
		novoEndereco.setLogradouro(resultadoTab.getString("logradouro"));
		novoEndereco.setNumLocal(resultadoTab.getString("numLocal"));
		novoEndereco.setComplemento(resultadoTab.getString("complemento"));
		novoEndereco.setBairro(resultadoTab.getString("bairro"));
		novoEndereco.setLocalidade(resultadoTab.getString("localidade"));
		novoEndereco.setUf(resultadoTab.getString("uf"));
		novoEndereco.setCep(resultadoTab.getString("cep"));
		novoEndereco.setDdd(resultadoTab.getString("ddd"));
		novoEndereco.setIbge(resultadoTab.getString("ibge"));
		novoEndereco.setGia(resultadoTab.getString("gia"));
		novoEndereco.setSiafi(resultadoTab.getString("siafi"));
		
		return novoEndereco;
	}

}
