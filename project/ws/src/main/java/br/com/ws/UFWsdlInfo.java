package br.com.ws;

import javax.xml.namespace.QName;

public interface UFWsdlInfo {

	public QName getPortName();
	public QName getServiceName();
	public String getEndpointAddress();
	public String getUri();
	public String getSoapAction();
}