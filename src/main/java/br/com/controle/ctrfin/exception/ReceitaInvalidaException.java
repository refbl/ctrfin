package br.com.controle.ctrfin.exception;

public class ReceitaInvalidaException extends Exception{

	private static final long serialVersionUID = 1L;

	public ReceitaInvalidaException(String mensagem) {
		super(mensagem);
	}
}
