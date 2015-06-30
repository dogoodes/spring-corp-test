package br.com.orm.ws.sefaz;

import spring.corp.framework.configuracao.ManagerSetting;

public class Parametros {

	private Ambiente ambiente;
	private Proxy proxy;
	private String caminhoCertificado;
	private String caminhoCertificadoReceita;

	public static Parametros getWaveParametros(int ambiente) {
		Parametros parametros = new Parametros();
		String caminhoCertificadoReceita = ManagerSetting.getSetting("certificadoreceita");

		if (ambiente == 1) {
			parametros.setAmbiente(Ambiente.PRODUCAO);
		} else if (ambiente == 2) {
			parametros.setAmbiente(Ambiente.HOMOLOGACAO);
		}
		parametros.setCaminhoCertificadoReceita(caminhoCertificadoReceita);
		parametros.setCaminhoCertificado(ManagerSetting.getSetting("certificado"));
		String proxyHost = ManagerSetting.getSetting("proxyHost");
		if (proxyHost != null) {
			String proxyPort = ManagerSetting.getSetting("proxyPort");
			String proxyUser = ManagerSetting.getSetting("proxyUser");
			String proxyPass = ManagerSetting.getSetting("proxyPass");
			Proxy proxy = null;
			if (proxyUser != null) {
				proxy = new Proxy(proxyHost, proxyPort, proxyUser, proxyPass);
			} else {
				proxy = new Proxy(proxyHost, proxyPort);
			}
			parametros.setProxy(proxy);
		}
		return parametros;
	}

	public String getCaminhoCertificadoReceita() {
		StringBuilder path = new StringBuilder();
		path.append(caminhoCertificadoReceita);
		path.append(getAmbiente().name().toUpperCase());
		//path.append("/");
		//path.append(UFEmpresaHolder.get().getSigla().toUpperCase());
		path.append("/jssecacerts");
		return path.toString();
	}

	private void setCaminhoCertificadoReceita(String caminhoCertificadoReceita) {
		this.caminhoCertificadoReceita = caminhoCertificadoReceita;
	}

	public Ambiente getAmbiente() {
		return ambiente;
	}

	public void setAmbiente(Ambiente ambiente) {
		this.ambiente = ambiente;
	}

	public Proxy getProxy() {
		return proxy;
	}

	public void setProxy(Proxy proxy) {
		this.proxy = proxy;
	}

	public String getCaminhoCertificado() {
		return caminhoCertificado;
	}

	private void setCaminhoCertificado(String caminho) {
		this.caminhoCertificado = caminho;
	}
}