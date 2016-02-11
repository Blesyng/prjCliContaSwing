package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import exception.DaoException;

public class Excluir {

	String urlDeConexao, usuario, senha = null;
	
	public Excluir(String urlDeConexao, String usuario, String senha) {
		this.urlDeConexao = urlDeConexao;
		this.usuario = usuario;
		this.senha = senha;
	}
	
	public void removeCliente(long cpfCliente) throws DaoException {
			try{
			Connection conexao = DriverManager.getConnection(urlDeConexao, usuario, senha);
			String textoDoComando = "DELETE FROM Cliente WHERE cpf = '" + cpfCliente + "'";
			
			PreparedStatement comando = conexao.prepareStatement(textoDoComando);
			comando.execute();
			conexao.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}
	
	public void removeConta(int codConta) {
		try{
			Connection conexao = DriverManager.getConnection(urlDeConexao, usuario, senha);
			String textoDoComando = "DELETE FROM Conta WHERE codigo = '" + codConta + "'";
					
			PreparedStatement comando = conexao.prepareStatement(textoDoComando);
			comando.execute();
			conexao.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
