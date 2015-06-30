package br.com.ws.sefaz.security;

import spring.corp.framework.configuracao.ManagerSetting;
import spring.corp.framework.exceptions.CriticalUserException;
import spring.corp.framework.i18n.ManagerMessage;
import spring.corp.framework.log.ManagerLog;
import br.com.orm.ws.sefaz.Parametros;

public class CriarComunicacaoSSL {

	private transient boolean isAutenticado = true;
	private Parametros parametros = null;
	
	private CriarComunicacaoSSL(Parametros waveParametros) {
		this.parametros = waveParametros;
		conexaoHttps();
	}
	
	public static CriarComunicacaoSSL abrirComunicacao(Parametros waveParametros) {
		try {
			CriarComunicacaoSSL c = new CriarComunicacaoSSL(waveParametros);
			String password = ManagerSetting.getSetting("senhacertificado");
			c.usandoPin(password.toCharArray());
			return c;
		} catch (Exception e) {
			String message = ManagerMessage.getMessage("system.transmissao.nao.possivel");
			throw new CriticalUserException(CriarComunicacaoSSL.class, message, e);
		}
	}

	public CriarComunicacaoSSL conexaoHttps() {
		if (parametros.getProxy() != null) {
			System.setProperty("http.proxyHost", parametros.getProxy().getHost());
			System.setProperty("http.proxyPort", parametros.getProxy().getPort());
		}
		System.setProperty("java.protocol.handler.pkgs", "com.sun.net.ssl.internal.www.protocol");
		return this;
	}

		
	public void usandoPin(char[] pin) {
		registrarProvider();
		
		// Informacoes do Certificado usado para criar a conexao.
		System.setProperty("javax.net.ssl.keyStoreType", "PKCS12");
		System.setProperty("javax.net.ssl.keyStore", parametros.getCaminhoCertificado());
		System.setProperty("javax.net.ssl.keyStorePassword", String.valueOf(pin));

		// Informacoes do Certificado Publico, neste caso e o certificado da SEFAZ de SP, necessario apenas para a transmissao.
		String caminhoCertificadoReceita = parametros.getCaminhoCertificadoReceita();
		ManagerLog.debug(CriarComunicacaoSSL.class, "Caminho do Certificado Receita: " + caminhoCertificadoReceita);
		System.setProperty("javax.net.ssl.trustStoreType", "JKS");
		System.setProperty("javax.net.ssl.trustStore", caminhoCertificadoReceita);
		System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
	}

	public boolean isAutenticado() {
		return isAutenticado;
	}

	public void desRegistrarProvider() {}

	@SuppressWarnings("restriction")
	private void registrarProvider() {
		try {
			java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
		} catch (Exception e) {
			isAutenticado = false;
			e.printStackTrace();
		}
	}
}