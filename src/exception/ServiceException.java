package exception;

import java.util.ArrayList;

import systemUI.JanelaAviso;

@SuppressWarnings("serial")
public class ServiceException extends Exception {
	private String message;
	private ServiceExceptionEnum tipo;
	private JanelaAviso javiso = new JanelaAviso();

	public ServiceException() {

	}

	public ServiceException(String msg) {
		super(msg);
		this.message = msg;
	}

	public ServiceException(ServiceExceptionEnum tipo) {
		this.javiso.exibirMensagem(tipo.getValor());
		this.tipo = tipo;
		return;
	}

	public ServiceException(ArrayList listaErrosBanco) {
		// TODO Auto-generated constructor stub
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String msg) {
		this.message = msg;
	}

	public ServiceExceptionEnum getTipo() {
		return tipo;
	}

	public void setTipo(ServiceExceptionEnum tipo) {
		this.tipo = tipo;
	}

}
