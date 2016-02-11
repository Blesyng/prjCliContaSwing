package exception;

public enum ServiceExceptionEnum {
	CLIENTE_CPF_INVALIDO("CPF do cliente inválido"), CLIENTE_NOME_INVALIDO("Nome do cliente inválido"),
	CLIENTE_SOBRENOME_INVALIDO("Sobrenome do cliente inválido"), CLIENTE_ESTADOCIVIL_INVALIDO ("Estado civil do cliente inválido"),
	CLIENTE_SEXO_INVALIDO ("Sexo do cliente inválido"),CLIENTE_CEP_INVALIDO("CEP do cliente inválido"), 
	CLIENTE_ENDERECO_INVALIDO("Endereço do cliente inválido"),
	CONTA_CODIGO_INVALIDO("Código da conta inválida"), CONTA_AGENCIA_INVALIDA("Agencia da conta inválida"),
	CONTA_SALDO_INVALIDO("Saldo da conta inválido"), CONTA_TIPO_INVALIDA("Tipo de conta inválida");

	private String valor;
	
	private ServiceExceptionEnum() {
		// TODO Auto-generated constructor stub
	}

	private ServiceExceptionEnum(String valor) {
		this.valor = valor;
	}
	
	public String getValor(){
		return valor;
	}

}
