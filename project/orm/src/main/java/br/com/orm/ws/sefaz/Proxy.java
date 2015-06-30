package br.com.orm.ws.sefaz;

public class Proxy {

	private final String host;
	private final String port;
	private final String user;
	private final String pass;

	public Proxy(String host, String port) {
		this.host = host;
		this.port = port;
		this.user = null;
		this.pass = null;
	}
	
	public Proxy(String host, String port, String user, String pass) {
		this.host = host;
		this.port = port;
		this.user = user;
		this.pass = pass;
	}
	
	public String getHost() {
		return host;
	}
	public String getPort() {
		return port;
	}

	public String getUser() {
		return user;
	}

	public String getPass() {
		return pass;
	}
}