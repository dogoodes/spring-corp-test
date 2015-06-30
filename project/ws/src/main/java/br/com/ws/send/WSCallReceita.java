package br.com.ws.send;

import spring.corp.framework.exceptions.WSResultException;
import spring.corp.framework.exceptions.WSSendException;

public interface WSCallReceita<T, R> {

	public T send(String nfeDado, int ambiente) throws WSSendException;
	
	public R resultado(String resultadoEnvio) throws WSResultException;
}
