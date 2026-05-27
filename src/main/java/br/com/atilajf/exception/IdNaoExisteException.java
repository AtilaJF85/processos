package br.com.atilajf.exception;

public class IdNaoExisteException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public IdNaoExisteException(String mensagem) {
		super(mensagem);
	}

}
