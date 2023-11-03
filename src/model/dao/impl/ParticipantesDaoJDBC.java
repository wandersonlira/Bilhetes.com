package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import controller.exception.DbException;
import model.connection.DbConexao;
import model.dao.ParticipantesDao;
import model.entidades.TabParticipantes;

public class ParticipantesDaoJDBC implements ParticipantesDao{
	
	
	public Connection conexao;
	
	public ParticipantesDaoJDBC (Connection conexao) {
		this.conexao = conexao;
	}
	
	

	@Override
	public void insert(TabParticipantes objeto) {
		
		PreparedStatement stConsulta = null;
		
		try {
			stConsulta = conexao.prepareStatement(
					"INSERT INTO Participantes (nomeParticipante, cpf, email) "
					+ "VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			
			stConsulta.setString(1, objeto.getNomeParticipante());
			stConsulta.setString(2, objeto.getCpf());
			stConsulta.setString(3, objeto.getEmail());
//			stConsulta.setInt(4, objeto.getCodigoEventos().getIdEvento()); Não existe na Tabela "Participantes" a coluna 'CodigoEvento'
			
			int rowsAffected = stConsulta.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rsResultado = stConsulta.getGeneratedKeys();
				
				if (rsResultado.next()) {
					int idNovo = rsResultado.getInt(1);
					objeto.setIdParticipante(idNovo);
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
	public boolean update(TabParticipantes objeto) {
		
		boolean statusUpdate = true; 
		
		PreparedStatement stConsulta = null;
		
		try {
			stConsulta = conexao.prepareStatement(
					"UPDATE Participantes "
					+ "SET nomeParticipante = ?, cpf = ?, email = ? "
					+ "WHERE idParticipante = ?");
			
			stConsulta.setString(1, objeto.getNomeParticipante());
			stConsulta.setString(2, objeto.getCpf());
			stConsulta.setString(3, objeto.getEmail());
//			stConsulta.setInt(4, objeto.getCodigoEventos().getIdEvento()); Não existe na Tabela "Participantes" a coluna 'CodigoEvento'
			stConsulta.setInt(4, objeto.getIdParticipante());
			
			int rowsAffected = stConsulta.executeUpdate();
			
			if (rowsAffected > 0) {
				System.out.println("Update realizado com sucesso!");
				
			} else {
				System.out.println("Houve um erro inesperado... nenhuma linha afetada!!");
				statusUpdate = false;
			}
		
		} catch (SQLException e) {
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
					"DELETE FROM Participantes "
					+ "WHERE idParticipantes = ?");
			
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
	
	
/*			Os comentários destas linhas era referente ao funcionamento anterior no qual a tabela 'TabParticipantes' 
			tinha um campo "ManToOne" com TabEventos. Portanto reajustamos o código onde as linhas desnecessária
			foi comentado para futura exclusão.
*/
	@Override
	public TabParticipantes findByCPF(String cpf) {
		
		PreparedStatement stConsulta = null;
		ResultSet rsResultado = null;
		
		try {
			stConsulta = conexao.prepareStatement(
					"SELECT * FROM Participantes "
					+ "WHERE cpf = ? ");
			
//					"SELECT * FROM Participantes INNER JOIN Eventos INNER JOIN Endereco "
//					+ "ON Participantes.codigoEvento = Eventos.idEvento "
//					+ "AND Eventos.codigoEndereco = Endereco.idEndereco "
//					+ "WHERE idParticipante = ? ");
			
			stConsulta.setString(1, cpf);
			
			rsResultado = stConsulta.executeQuery();
			
			if (rsResultado.next()) {
				
//				TabEndereco endereco = instanciaEndereco(rsResultado);
				
//				TabEventos evento = instanciaEventos(rsResultado, endereco);
				
				TabParticipantes participante = instanciaParticipantes(rsResultado/*, evento*/);
				return participante;
			}
			
			return null;
			
		}catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DbConexao.closeStatement(stConsulta);
			DbConexao.closeResultSet(rsResultado);	
		}
		
	}
	
	
	
/*			Os comentários destas linhas era referente ao funcionamento anterior no qual a tabela 'TabParticipantes' 
			tinha um campo "ManToOne" com TabEventos. Portanto reajustamos o código onde as linhas desnecessária
			foi comentado para futura exclusão.
*/
	@Override
	public List<TabParticipantes> findAll() {
		
		PreparedStatement stConsulta = null;
		ResultSet rsResultado = null;
		
		try {
			stConsulta = conexao.prepareStatement(
					"SELECT Participantes.* FROM Participantes "
					+ "ORDER BY nomeParticipante");
			
//					"SELECT Participantes.* ,Eventos.* ,Endereco.* FROM Participantes INNER JOIN Eventos INNER JOIN Endereco "
//					+ "ON Participantes.codigoEvento = Eventos.idEvento "
//					+ "AND Eventos.codigoEndereco = Endereco.idEndereco "
//					+ "ORDER BY nomeParticipante");
			
			
			rsResultado = stConsulta.executeQuery();
			
			List<TabParticipantes> listParticipante = new ArrayList<>();
			
//			List<TabEventos> listEvento = new ArrayList<>();
//			Map<Integer, TabEventos> mapEvento = new HashMap<>();
//			Map<Integer, TabEndereco> mapEndereco = new HashMap<>();
			
			while(rsResultado.next()) {
				
//				TabEndereco pegaEndereco = mapEndereco.get(rsResultado.getInt("codigoEndereco"));
//				
//				if (pegaEndereco == null) {
//					pegaEndereco = instanciaEndereco(rsResultado);
//					mapEndereco.put(rsResultado.getInt("codigoEndereco"), pegaEndereco);
//				}
//				
//				TabEventos evento = instanciaEventos(rsResultado, pegaEndereco);
//				listEvento.add(evento);
//				
////		  ------------------ Cada tipo 'Evento' abaixo contém seu respectivo endereco acoplado na lista ----------------------
//				
//				TabEventos pegaEvento = mapEvento.get(rsResultado.getInt("codigoEvento"));
//				
//				if (pegaEvento == null) {
//					pegaEvento = instanciaEventos(rsResultado, pegaEndereco);
//					mapEvento.put(rsResultado.getInt("codigoEvento"), pegaEvento);
//				}
				
				TabParticipantes participante = instanciaParticipantes(rsResultado/*, pegaEvento*/);
				listParticipante.add(participante);
			}
			
			return listParticipante;
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
			
		} finally {
			DbConexao.closeResultSet(rsResultado);
			DbConexao.closeStatement(stConsulta);
		}
	}
	
	
	private TabParticipantes instanciaParticipantes(ResultSet resultadoTab/*, TabEventos eventos*/) throws SQLException {
		
		TabParticipantes novoParticipante = new TabParticipantes();
		
		novoParticipante.setIdParticipante(resultadoTab.getInt("idParticipante"));
		novoParticipante.setNomeParticipante(resultadoTab.getString("nomeParticipante"));
		novoParticipante.setCpf(resultadoTab.getString("cpf"));
		novoParticipante.setEmail(resultadoTab.getString("email")); 
//		novoParticipante.setCodigoEvento(eventos);
		
		return novoParticipante;
		
	}
	

/*			Os comentários destas linhas era referente ao funcionamento anterior no qual a tabela 'TabParticipantes' 
			tinha um campo "ManToOne" com TabEventos. Portanto reajustamos o código onde as linhas desnecessária
			foi comentado para futura exclusão.
*/

/*	
	private TabEventos instanciaEventos(ResultSet resultadoTab, TabEndereco endereco) throws SQLException {
		
		TabEventos novoEvento = new TabEventos();
		
		novoEvento.setIdEvento(resultadoTab.getInt("idEvento"));
		novoEvento.setNomeEvento(resultadoTab.getString("nomeEvento"));
		novoEvento.setDataEvento(resultadoTab.getDate("dataEvento"));
		novoEvento.setHoraEvento(resultadoTab.getTime("horaEvento"));
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
	
*/

	
}
