package br.com.ws.sefaz.conscad;

import javax.xml.namespace.QName;

import br.com.ws.UFWsdlInfo;

public class SPProducao implements UFWsdlInfo {

	public QName getPortName() {
		return new QName("http://www.portalfiscal.inf.br/nfe/wsdl/CadConsultaCadastro2", "consultaCadastro2Soap12");
	}

	public QName getServiceName() {
		return new QName("http://www.portalfiscal.inf.br/nfe/wsdl/CadConsultaCadastro2", "consultaCadastro2");
	}

	public String getEndpointAddress() {
		return "https://nfe.fazenda.sp.gov.br/ws/cadconsultacadastro2.asmx";
	}

	public String getUri() {
		return "http://www.portalfiscal.inf.br/nfe/wsdl/CadConsultaCadastro2";
	}

	@Override
	public String getSoapAction() {
		return "http://www.portalfiscal.inf.br/nfe/wsdl/CadConsultaCadastro2/consultaCadastro2";
	}
}