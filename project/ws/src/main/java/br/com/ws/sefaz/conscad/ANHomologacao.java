package br.com.ws.sefaz.conscad;

import javax.xml.namespace.QName;

import br.com.ws.UFWsdlInfo;

public class ANHomologacao implements UFWsdlInfo {

	public QName getPortName() {
		return new QName("", "");
	}

	public QName getServiceName() {
		return new QName("", "");
	}

	public String getEndpointAddress() {
		return "";
	}

	public String getUri() {
		return "";
	}

	@Override
	public String getSoapAction() {
		return "";
	}
}