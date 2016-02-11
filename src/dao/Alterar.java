package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import entity.Cliente;
import entity.Conta;

public class Alterar {

	String urlDeConexao, usuario, senha = null;
	
	public Alterar(String urlDeConexao, String usuario, String senha) {
		this.urlDeConexao = urlDeConexao;
		this.usuario = usuario;
		this.senha = senha;
	}
	
	public void updateCliente(Cliente clienteNovo) {
		try{
			Connection conexao = DriverManager.getConnection(urlDeConexao, usuario, senha);
			String textoDoComando = "UPDATE Cliente set nome = '" + clienteNovo.getNome() + "', sobrenome = '" + clienteNovo.getSobrenome() + "', sexo = '" + clienteNovo.getSexo() + "', estadoCivil = '" + clienteNovo.getEstadoCivil() 
					+ "', cep = '" + clienteNovo.getCep() + "', endereco = '" + clienteNovo.getEndereco() + "' where cpf = '" + clienteNovo.getCpf() + "'";
			
			System.out.println(textoDoComando);
			
			PreparedStatement comando = conexao.prepareStatement(textoDoComando);
			comando.execute();
			conexao.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateConta(Conta contaNovo) {
		try{
			Connection conexao = DriverManager.getConnection(urlDeConexao, usuario, senha);
			String textoDoComando = " UPDATE Conta set agencia = '" + contaNovo.getAgencia() + "', saldo = '" + contaNovo.getSaldo() + "', tipo = '" + contaNovo.getTipo() + "', cpf = '" + contaNovo.getCliente().getCpf() 
					+ "' where codigo = '" + contaNovo.getCodigo() + "'";
			
			System.out.println(textoDoComando);
			
			PreparedStatement comando = conexao.prepareStatement(textoDoComando);
			comando.execute();
			conexao.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
