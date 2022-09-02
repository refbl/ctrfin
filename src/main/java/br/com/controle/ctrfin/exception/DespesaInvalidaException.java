package br.com.controle.ctrfin.exception;

public class DespesaInvalidaException extends Exception{

	private static final long serialVersionUID = 1L;

	public DespesaInvalidaException(String mensagem) {
		super(mensagem);
	}
}
