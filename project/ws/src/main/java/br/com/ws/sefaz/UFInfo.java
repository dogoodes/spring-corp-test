package br.com.ws.sefaz;

import java.io.Serializable;

public class UFInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	private String sigla;
	private Integer codIbge;

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public Integer getCodIbge() {
		return codIbge;
	}

	public void setCodIbge(Integer codIbge) {
		this.codIbge = codIbge;
	}
}