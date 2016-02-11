package dto;

import entity.TipoConta;

/**
 * @author Danyllo, Felipe e Hugo
 * @version 1.0
 */
public class ContaDTO {
	/**
	 * código da conta
	 */
	private int codigo;
	/**
	 * código alfa-numérico da agência
	 */
	private String agencia;
	/**
	 * valor em R$ do saldo
	 */
	private float saldo;
	/**
	 * atributo que aponta para enumeration do tipo da conta
	 */
	private TipoConta tipoConta;
	
	private long clienteCpf;
	/**
	 * construtor default
	 */
	public ContaDTO(){}
	/**
	 * construtor da conta com seus dados obrigatórios
	 * @param codigo da conta
	 * @param agencia do cliente
	 * @param saldo2 da conta
	 * @param tipo2 da conta
	 */
	public ContaDTO(int codigo, String agencia, float saldo, TipoConta tipoConta, long clienteCpf) {
		super();
		this.codigo = codigo;
		this.agencia = agencia;
		this.saldo = saldo;
		this.tipoConta = tipoConta;
		this.clienteCpf = clienteCpf;
	}
	/**
	 * pegar o código da conta
	 * @return
	 */
	public int getCodigo() {
		return codigo;
	}
	/**
	 * alterar o código da conta
	 * @param codigo
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	/**
	 * pegar o identificador da agência
	 * @return
	 */
	public String getAgencia() {
		return agencia;
	}
	/**
	 * alterar o identificador da agência
	 * @param agencia
	 */
	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}
	/**
	 * pegar o saldo da conta
	 * @return
	 */
	public float getSaldo() {
		return saldo;
	}
	/**
	 * alterar o saldo da conta
	 * @param saldo
	 */
	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}
	/**
	 * pegar o tipo da conta
	 * @return
	 */
	public TipoConta getTipoConta() {
		return tipoConta;
	}
	/**
	 * alterar o tipo da conta
	 * @param tipo
	 */
	public void setTipoConta(TipoConta tipo) {
		this.tipoConta = tipo;
	}
	public long getClienteCpf() {
		return clienteCpf;
	}
	public void setClienteCpf(long clienteCpf) {
		this.clienteCpf = clienteCpf;
	}
	
	

}
