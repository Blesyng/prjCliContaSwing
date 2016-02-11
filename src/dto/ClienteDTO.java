package dto;

import java.util.ArrayList;

import entity.Conta;
import entity.EstadoCivil;
import entity.Sexo;


/**
 * 
 * @author Danyllo, Felipe e Hugo
 * @version 1.0
 *
 */
public class ClienteDTO {
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
	 * atributo que aponta para enumeration de sexo
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
	 * coleção de contas
	 */
	private ArrayList<Integer> codContas;
	/**
	 * construtor default
	 */
	public ClienteDTO (){
		
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
	public ClienteDTO( long cpf, String nome, String sobrenome, Sexo sexo, EstadoCivil estadoCivil, String cep, String endereco) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.sexo = sexo;
		this.estadoCivil = estadoCivil;
		this.cep = cep;
		this.endereco = endereco;
		this.codContas = new ArrayList<Integer>();
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
	 * @return the codContas
	 */
	public ArrayList<Integer> getCodContas() {
		return codContas;
	}

	/**
	 * @param codContas the codContas to set
	 */
	public void setCodContas(ArrayList<Integer> codContas) {
		this.codContas = codContas;
	}

	public boolean adicionarContaDTO(Integer codConta) {
		if (this.codContas.contains(codConta))
			return false;
		this.codContas.add(codConta);
		return true;
	}
	
	public boolean removerContaDTO(Integer codConta) {
		if (!this.codContas.contains(codConta))
			return false;
		this.codContas.remove(codConta);
		return true;
	}
}
