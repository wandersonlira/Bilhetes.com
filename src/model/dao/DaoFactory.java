package model.dao;

import model.connection.DbConexao;
import model.dao.impl.EnderecoDaoJDBC;
import model.dao.impl.EventosDaoJDBC;
import model.dao.impl.ParticipanteEventoDaoJDBC;
import model.dao.impl.ParticipantesDaoJDBC;

public class DaoFactory {
	
	public static ParticipantesDao createParticipantes() {
		return new ParticipantesDaoJDBC(DbConexao.getConexao());
	}
	
	
	public static EventosDao createEventos() {
		return new EventosDaoJDBC(DbConexao.getConexao());
	}
	
	public static EnderecoDao createEndereco() {
		return new EnderecoDaoJDBC(DbConexao.getConexao());
	}
	
	public static ParticipanteEventoDao createParticipanteEvento() {
		return new ParticipanteEventoDaoJDBC(DbConexao.getConexao());
	}

}
