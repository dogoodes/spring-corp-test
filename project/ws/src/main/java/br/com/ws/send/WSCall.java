package br.com.ws.send;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.soap.SOAPBinding;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import spring.corp.framework.exceptions.CriticalUserException;
import spring.corp.framework.i18n.ManagerMessage;
import spring.corp.framework.log.ManagerLog;
import spring.corp.framework.utils.WSUtil;
import br.com.orm.ws.sefaz.Parametros;
import br.com.ws.UFWsdlInfo;
import br.com.ws.sefaz.UFInfo;
import br.com.ws.sefaz.security.CriarComunicacaoSSL;

public abstract class WSCall {

	protected String getVersao() {
		return "2.00";
	}
	
	protected Document toDOM(String xmlAssinado) {
		Document dom;
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setNamespaceAware(true);
			dom = factory.newDocumentBuilder().parse(new ByteArrayInputStream(xmlAssinado.getBytes("UTF-8")));
		} catch (ParserConfigurationException e) {
			String message = ManagerMessage.getMessage("webservice.toDom.exception", xmlAssinado);
			throw new CriticalUserException(WSCall.class, message, e);
		} catch (SAXException e) {
			String message = ManagerMessage.getMessage("webservice.toDom.exception", xmlAssinado);
			throw new CriticalUserException(WSCall.class, message, e);
		} catch (IOException e) {
			String message = ManagerMessage.getMessage("webservice.toDom.exception", xmlAssinado);
			throw new CriticalUserException(WSCall.class, message, e);
		}
		try {
			boolean isValid = WSUtil.validateSign(dom);
			if (ManagerLog.isDebug(WSCall.class)) {
				ManagerLog.debug(WSCall.class, "Assinatura valida: [" + isValid + "]");
			}
		} catch (Exception e) {
			//Nao precisa fazer nada
		}
		return dom;
	}
	
	protected SOAPMessage call(Document xmlEnvio, UFWsdlInfo wsdlInfo, UFInfo unidadeEnvio, Parametros waveParametros) throws SOAPException, WebServiceException{
		if (ManagerLog.isDebug(WSCall.class)) {
			ManagerLog.debug(WSCall.class, " Acessando WebService da UF [" + unidadeEnvio.getSigla() + "] Ambiente [" + waveParametros.getAmbiente().name() + "]");
		}
		String endpointAddress = wsdlInfo.getEndpointAddress();
		QName serviceName = wsdlInfo.getServiceName();
		QName portName = wsdlInfo.getPortName();
		Service service = Service.create(serviceName);
		service.addPort(portName, SOAPBinding.SOAP12HTTP_BINDING, endpointAddress);
		
		Dispatch<SOAPMessage> dispatch = service.createDispatch(portName, SOAPMessage.class, Service.Mode.MESSAGE);
		MessageFactory mf = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);
		SOAPMessage request = mf.createMessage();
		SOAPPart part = request.getSOAPPart();
		SOAPEnvelope env = part.getEnvelope();
		SOAPHeader header = env.getHeader();
		SOAPBody body = env.getBody();
		SOAPFactory soapFactory = SOAPFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);
		
		addChildHeader(xmlEnvio, header, soapFactory, wsdlInfo, unidadeEnvio, waveParametros);
		
		addChildBody(xmlEnvio, body, soapFactory, wsdlInfo, unidadeEnvio, waveParametros);
		
		request.setProperty(SOAPMessage.CHARACTER_SET_ENCODING, "UTF-8");
		dispatch.getRequestContext().put(Dispatch.SOAPACTION_URI_PROPERTY, wsdlInfo.getSoapAction());
		request.saveChanges();

		if (ManagerLog.isInfo(WSCall.class)) {
			try {
				request.writeTo(System.out);
				System.out.println();
			} catch (IOException e) {
				
			}
		}
		CriarComunicacaoSSL.abrirComunicacao(waveParametros);
		SOAPMessage response = dispatch.invoke(request);
		
		if (ManagerLog.isInfo(WSCall.class)) {
			try {
				response.writeTo(System.out);
				System.out.println();
				System.out.println("Resposta conclu\u00eddo");
			} catch (IOException e) {
				
			}
		}
		return response;
	}
	
	public void addChildHeader(Document xmlEnvio, SOAPHeader header, SOAPFactory soapFactory, UFWsdlInfo wsdlInfo, UFInfo unidadeEnvio, Parametros waveParametros) throws SOAPException {
		SOAPElement headerNfeCabecMsg = header.addChildElement("nfeCabecMsg", "", wsdlInfo.getUri());
		SOAPElement headerNfeCabecMsgCUF = headerNfeCabecMsg.addChildElement("cUF");
		headerNfeCabecMsgCUF.setTextContent("" + unidadeEnvio.getCodIbge());
		SOAPElement headerNfeCabecMsgVersaoDados = headerNfeCabecMsg.addChildElement("versaoDados");
		headerNfeCabecMsgVersaoDados.setTextContent(getVersao());
	}
	
	public void addChildBody(Document xmlEnvio, SOAPBody body, SOAPFactory soapFactory, UFWsdlInfo wsdlInfo, UFInfo unidadeEnvio, Parametros waveParametros) throws SOAPException {
		SOAPElement bodyNfeDadosMsg = body.addChildElement("nfeDadosMsg", "", wsdlInfo.getUri());
		bodyNfeDadosMsg.addChildElement(soapFactory.createElement(xmlEnvio.getDocumentElement()));
	}
}