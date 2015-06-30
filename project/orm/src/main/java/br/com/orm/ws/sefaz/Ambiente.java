package br.com.orm.ws.sefaz;

public enum Ambiente {
	
	PRODUCAO("1"), HOMOLOGACAO("2");
	
	private final String codigo;
	private final String description;
	private final String cnpj = "99999999000191";
	private final String nome = "NF-E EMITIDA EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL";
	
	private Ambiente(String value) {
		this.codigo = value;
		if (codigo.equals("1")) {
			this.description = "PRODUCAO";
		} else {
			this.description = "HOMOLOGACAO";
		}
	}
	
	public static Ambiente getAmbiente(int ambiente) {
		Ambiente amb = null;
		switch (ambiente) {
			case 1:
				amb = Ambiente.PRODUCAO;
				break;
			case 2:
				amb = Ambiente.HOMOLOGACAO;
				break;
		}
		return amb;
	}
	
	public String getCodigo(){
		return codigo;
	}
	
	public String getDescription(){
		return description;
	}

	public String getCnpj() {
		return cnpj;
	}

	public String getNome() {
		return nome;
	}
}