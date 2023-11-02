package model.connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import controller.exception.DbException;

public class DbConexao {
	
	private static Connection conexao = null;
	

	private static Properties loadPropriedades() {
		
		try (FileInputStream carregaArquivo = new FileInputStream("db.propriedades")) {
			
			Properties pegaArquivo = new Properties();
			pegaArquivo.load(carregaArquivo);
			
			return pegaArquivo;
			
		} catch (IOException e) {
				throw new DbException(e.getMessage());
			}
	
 	}

	
	public static Connection getConexao() {
		
		if (conexao == null) {
			
			Properties carregaArquivo = loadPropriedades();
			String url = carregaArquivo.getProperty("dburl");
			
			try {
				conexao = DriverManager.getConnection(url, carregaArquivo);
				
			} catch (SQLException e) {
					throw new DbException(e.getMessage());
				}
		}
		return conexao;
		
	}
	
	
	public static void closeConexao() {
		
		if (conexao != null) {
			try {
				conexao.close();
				
			} catch (SQLException e) {
					throw new DbException(e.getMessage());
				}
		}
		
	}
	
	
	public static void closeStatement(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
				
			} catch (SQLException e) {
					throw new DbException(e.getMessage());
				}
		}
		
	}
	
	
	public static void closeResultSet(ResultSet resultset) {
		if (resultset != null) {
			try {
				resultset.close();
				
			} catch (SQLException e) {
					throw new DbException(e.getMessage());
				}
		}
		
	}

	
}




