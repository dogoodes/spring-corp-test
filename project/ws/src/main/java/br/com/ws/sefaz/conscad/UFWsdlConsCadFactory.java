package br.com.ws.sefaz.conscad;

import java.util.HashMap;
import java.util.Map;

import spring.corp.framework.exceptions.CriticalUserException;
import spring.corp.framework.exceptions.UserException;
import spring.corp.framework.i18n.ManagerMessage;
import br.com.ws.UFWsdlInfo;

public class UFWsdlConsCadFactory {

	public static final String ambiente_nacional = "AN";
	
	private static Map<String, Class<? extends UFWsdlInfo> > ufClassProducao = new HashMap<String, Class<? extends UFWsdlInfo>>();
	static {
		ufClassProducao.put(ambiente_nacional, ANProducao.class);
		ufClassProducao.put("SP", SPProducao.class);
	}
	
	public static UFWsdlInfo getInstance(String uf) throws UserException {
		try {
			Class<? extends UFWsdlInfo> clazz = ufClassProducao.get(uf);
			return clazz.newInstance();
		} catch (Exception e) {
			String message = ManagerMessage.getMessage("webservice.wsdlinfo.location.error", uf);
			throw new CriticalUserException(UFWsdlConsCadFactory.class, message, e);
		}
	}
}