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
import model.dao.EventosDao;
import model.entidades.TabEndereco;
import model.entidades.TabEventos;

public class EventosDaoJDBC implements EventosDao{
	
	
	Connection conexao;
	
	public EventosDaoJDBC (Connection conexao) {
		this.conexao = conexao;
	}
	
	

	@Override
	public Integer insert(TabEventos objeto) {
		
		int novoIdTabela;
		
		PreparedStatement stConsulta = null;
		
		try {
			stConsulta = conexao.prepareStatement(
					"INSERT INTO Eventos "
					+ "(nomeEvento, dataEvento, horaEvento, ingressos, categoria, codigoEndereco) "
					+ "VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			
			stConsulta.setString(1, objeto.getNomeEvento());
			stConsulta.setDate(2, new java.sql.Date(objeto.getDataEvento().getTime()));
			stConsulta.setString(3, objeto.getHoraEvento());
			stConsulta.setInt(4, objeto.getIngressos());
//			stConsulta.setInt(5, objeto.getIngressoComprado()); Coluna não usada no campo pois foi definido defult
			stConsulta.setString(5, objeto.getCategoria());
			stConsulta.setInt(6, objeto.getCodigoEndereco().getIdEndereco());
			
			int rowsAffected = stConsulta.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rsResultado = stConsulta.getGeneratedKeys();
				
				if (rsResultado.next()) {
					novoIdTabela = rsResultado.getInt(1);
					objeto.setIdEvento(novoIdTabela);
					return novoIdTabela;
				}
				
				DbConexao.closeResultSet(rsResultado);
				
			} else {
				System.out.println("Houve um erro inesperado... nenhuma linha afetada!!");
				
			}
			
			return null;
			
		}catch (SQLException e) {
			throw new DbException(e.getMessage());
			
		} finally {
			DbConexao.closeStatement(stConsulta);
		}
		
	}
	
	

	@Override
	public boolean update(TabEventos objeto) {
		
		boolean statusUpdate = true;
		
		PreparedStatement stConsulta = null;
		
		try {
			stConsulta = conexao.prepareStatement(
					"UPDATE Eventos "
					+ "SET nomeEvento = ?, dataEvento = ?, horaEvento = ?, ingressos = ?, "
					+ /*"ingressoComprado = ?,*/ "categoria = ?, codigoEndereco = ? "
					+ "WHERE idEvento = ?");
			
			stConsulta.setString(1, objeto.getNomeEvento());
			stConsulta.setDate(2, new java.sql.Date(objeto.getDataEvento().getTime()));
			stConsulta.setString(3, objeto.getHoraEvento());
			stConsulta.setInt(4, objeto.getIngressos());
//			stConsulta.setInt(5, objeto.getIngressoComprado());
			stConsulta.setString(5, objeto.getCategoria());
			stConsulta.setInt(6, objeto.getCodigoEndereco().getIdEndereco());
			stConsulta.setInt(7, objeto.getIdEvento());
			
			int rowsAffected = stConsulta.executeUpdate();
			
			if (rowsAffected > 0) {
				System.out.println("Update realizado com sucesso!");
				
			} else {
				System.out.println("Houve um erro inesperado... nenhuma linha afetada!!");
				statusUpdate = false;
			}
		}catch (SQLException e) {
			throw new DbException(e.getMessage());
			
		} finally {
			DbConexao.closeStatement(stConsulta);
		}
		
		return statusUpdate;
		
	}
	
	

	@Override
	public void deleteById(Integer id) {
		
		PreparedStatement stConsulta = null;
		
		try {
			stConsulta = conexao.prepareStatement(
					"DELETE FROM Eventos "
					+ "WHERE idEvento = ?");
			
			stConsulta.setInt(1, id);
			
			int rowsAffected = stConsulta.executeUpdate();
			
			if (rowsAffected == 0) {
				System.out.println("Erro inesperado... 'id' informado não existe na tabela");
			}
			
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
			
		}finally {
			DbConexao.closeStatement(stConsulta);
		}
		
	}
	
	

	@Override
	public TabEventos findById(Integer id) {
		
		PreparedStatement stConsulta = null;
		ResultSet rsResultado = null;
		
		try {
			stConsulta = conexao.prepareStatement(
					"SELECT * FROM Eventos INNER JOIN Endereco "
					+ "ON Eventos.codigoEndereco = Endereco.idEndereco "
					+ "WHERE idEvento = ? ");
			
			stConsulta.setInt(1, id);
			
			rsResultado = stConsulta.executeQuery();
			
			if (rsResultado.next()) {
				
				TabEndereco endereco = instanciaEndereco(rsResultado);
				
				TabEventos evento = instanciaEventos(rsResultado, endereco);
				return evento;
			}
			
			return null;
			
		}catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DbConexao.closeStatement(stConsulta);
			DbConexao.closeResultSet(rsResultado);	
		}
	}
	
	

	@Override
	public List<TabEventos> findAll() {
		
		PreparedStatement stConsulta = null;
		ResultSet rsResultado = null;
		
		try {
			stConsulta = conexao.prepareStatement(
					"SELECT Eventos.* ,Endereco.* FROM Eventos INNER JOIN Endereco "
					+ "ON Eventos.codigoEndereco = Endereco.idEndereco "
					+ "ORDER BY nomeEvento");
			
			
			rsResultado = stConsulta.executeQuery();
			
			List<TabEventos> listEvento = new ArrayList<>();
			Map<Integer, TabEndereco> map = new HashMap<>();
			
			while(rsResultado.next()) {
				
				TabEndereco pegaEndereco = map.get(rsResultado.getInt("codigoEndereco"));
				
				if (pegaEndereco == null) {
					pegaEndereco = instanciaEndereco(rsResultado);
					map.put(rsResultado.getInt("codigoEndereco"), pegaEndereco);
				}
				
				TabEventos evento = instanciaEventos(rsResultado, pegaEndereco);
				listEvento.add(evento);
			}
			
			return listEvento;
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
			
		} finally {
			DbConexao.closeResultSet(rsResultado);
			DbConexao.closeStatement(stConsulta);
		}
		
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
