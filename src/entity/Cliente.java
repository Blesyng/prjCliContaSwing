package entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * @author Danyllo, Felipe e Hugo
 * @version 1.0
 *
 */
public class Cliente implements Serializable {
	/**
	 * cpf do cliente
	 */
	private long cpf;
	/**
	 * nome do cliente
	 */
	private String nome;
	/**
	 * sobrenome do cliente
	 */
	private String sobrenome;
	/**
	 * atributo que aponta para enumeration de sexo
	 */
	private Sexo sexo;
	/**
	 * atributo que aponta para enumeration de estado civil
	 */
	private EstadoCivil estadoCivil;
	/**
	 * cep do cliente
	 */
	private String cep;
	/**
	 * endereco do cliente
	 */
	private String endereco;
	/**
	 * collection de contas
	 */
	private ArrayList<Conta> listaContas;
	
	/**
	 * construtor default
	 */
	public Cliente (){
		
	}

	/**
	 * construtor com dados obrigatórios do cliente
	 * @param cpf
	 * @param nome
	 * @param sobrenome
	 * @param sexo
	 * @param estadoCivil
	 * @param endereco
	 * @param cep
	 */
	public Cliente(long cpf, String nome, String sobrenome, Sexo sexo, EstadoCivil estadoCivil, String cep, String endereco) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.sexo = sexo;
		this.estadoCivil = estadoCivil;
		this.cep = cep;
		this.endereco = endereco;
		this.listaContas = new ArrayList<Conta>();
	}

	/**
	 * pega o cpf
	 * @return 
	 */
	public long getCpf() {
		return cpf;
	}

	/**
	 * altera o cpf
	 * @param cpf 
	 */
	public void setCpf(long cpf) {
		this.cpf = cpf;
	}

	/**
	 * @return pega o nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * altera o nome
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * pega o sobrenome
	 * @return 
	 */
	public String getSobrenome() {
		return sobrenome;
	}

	/**
	 * altera o sobrenome
	 * @param sobrenome 
	 */
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	/**
	 * pega o sexo
	 * @return 
	 */
	public Sexo getSexo() {
		return sexo;
	}

	/**
	 * altera o sexo
	 * @param sexo 
	 */
	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	/**
	 * @return the listaContas
	 */
	public ArrayList<Conta> getListaContas() {
		return listaContas;
	}

	/**
	 * @param listaContas the listaContas to set
	 */
	public void setListaContas(ArrayList<Conta> listaContas) {
		this.listaContas = listaContas;
	}

	/**
	 * @return the estadoCivil
	 */
	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	/**
	 * @param estadoCivil the estadoCivil to set
	 */
	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	/**
	 * @return the endereco
	 */
	public String getEndereco() {
		return endereco;
	}

	/**
	 * @param endereco the endereco to set
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	/**
	 * @return the cep
	 */
	public String getCep() {
		return cep;
	}

	/**
	 * @param cep the cep to set
	 */
	public void setCep(String cep) {
		this.cep = cep;
	}
	
	
	public boolean adicionarConta(Conta conta) {
		if (this.listaContas.contains(conta))
			return false;
		this.listaContas.add(conta);
		conta.setCliente(this);
		return true;
	}
	
	public boolean removerConta(Conta conta) {
		if (!this.listaContas.contains(conta))
			return false;
		this.listaContas.remove(conta);
		conta.setCliente(null);
		return true;
	}
}
