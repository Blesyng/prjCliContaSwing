package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import entity.Cliente;
import entity.Conta;
import exception.DaoException;

public class Cadastrar {

	String urlDeConexao, usuario, senha = null;
	
	public Cadastrar(String urlDeConexao, String usuario, String senha) {
		this.urlDeConexao = urlDeConexao;
		this.usuario = usuario;
		this.senha = senha;
	}

	public void addCliente(Cliente cli) throws DaoException {
			try{
			Connection conexao = DriverManager.getConnection(urlDeConexao, usuario, senha);
			String textoDoComando = "INSERT INTO Cliente (cpf, nome, sobrenome, sexo, estadoCivil, cep, endereco) " +
					"VALUES ('" + cli.getCpf() + "', '" + cli.getNome() + "', '" + cli.getSobrenome() + "', '" + cli.getSexo() + "', '" + cli.getEstadoCivil() + "', '" + cli.getCep() + "', '" + cli.getEndereco() + "')";
			
			PreparedStatement comando = conexao.prepareStatement(textoDoComando);
			comando.execute();
			conexao.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}
	
	public void addConta(Conta con) {
		try{
			Connection conexao = DriverManager.getConnection(urlDeConexao, usuario, senha);
			String textoDoComando = "INSERT INTO Conta (codigo, agencia, saldo, tipo, cpf) " +
					"VALUES ('" + con.getCodigo() + "', '" + con.getAgencia() + "', '" + con.getSaldo() + "', '" + con.getTipo() + "', '" + con.getCliente().getCpf() + "')";
			
			PreparedStatement comando = conexao.prepareStatement(textoDoComando);
			comando.execute();
			conexao.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
