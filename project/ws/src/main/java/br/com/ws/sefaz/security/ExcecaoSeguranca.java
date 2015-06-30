package br.com.ws.sefaz.security;

public class ExcecaoSeguranca extends Throwable {

	private static final long serialVersionUID = 1L;

	public ExcecaoSeguranca(String mensagem){
		super(mensagem);
	}
}