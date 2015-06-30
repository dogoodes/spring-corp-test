package br.com.ws.sefaz.conscad;

import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.WebServiceException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import spring.corp.framework.exceptions.CriticalUserException;
import spring.corp.framework.exceptions.UserException;
import spring.corp.framework.exceptions.WSResultException;
import spring.corp.framework.exceptions.WSSendException;
import spring.corp.framework.i18n.ManagerMessage;
import spring.corp.framework.utils.WSUtil;
import br.com.bindings.ws.sefaz.conscad.ObjectFactory;
import br.com.bindings.ws.sefaz.conscad.TConsCad;
import br.com.bindings.ws.sefaz.conscad.TUfCons;
import br.com.orm.ws.sefaz.Parametros;
import br.com.ws.UFWsdlInfo;
import br.com.ws.sefaz.UFInfo;
import br.com.ws.sefaz.security.ExcecaoSeguranca;
import br.com.ws.send.WSCall;
import br.com.ws.send.WSCallReceita;

public class ConsCadTestCase<R> extends WSCall implements WSCallReceita<String, R> {

	private static UFInfo ufEnvio;
	
	public String gerarXMLDistribuicao(int ambiente) {
		try {
			String versao = "2.00";
			String cnpj = "CNPJ aqui";

			ObjectFactory objectFactory = new ObjectFactory();
			TConsCad.InfCons infCons = objectFactory.createTConsCadInfCons();
			infCons.setCNPJ(cnpj);
			infCons.setUF(TUfCons.SP);
			infCons.setXServ("CONS-CAD");
			//infCons.setIE("ISENTO");
			
			TConsCad consCad = new TConsCad();
			consCad.setInfCons(infCons);
			consCad.setVersao(versao);
			
			return String.valueOf(WSUtil.marshall(consCad, "/schemas/consCad/consCad_v2.00.xsd"));
		} catch (Exception e) {
			throw new UserException(e);
		}
	}

	@Override
	public String send(java.lang.String nfeDado, int ambiente) throws WSSendException {
		try {
			Document xmlEnvio = toDOM(nfeDado);
			Parametros waveParametros = Parametros.getWaveParametros(ambiente);
			UFWsdlInfo wsdlInfo = UFWsdlConsCadFactory.getInstance(ufEnvio.getSigla());
			SOAPMessage response = call(xmlEnvio, wsdlInfo, ufEnvio, waveParametros);
			SOAPBody soapBody = WSUtil.getSOAPBody(response);
			Node contentBody = soapBody.getFirstChild().getFirstChild();
			return WSUtil.getSOAPBodyContent(contentBody);
		} catch (Throwable e) {
			throw new WSSendException(ConsCadTestCase.class, e);
		}
	}

	@Override
	protected SOAPMessage call(Document xmlEnvio, UFWsdlInfo wsdlInfo, UFInfo ufEnvio, Parametros waveParametros) {
		try {
			return super.call(xmlEnvio, wsdlInfo, ufEnvio, waveParametros);
		} catch (SOAPException e) {
			String message = ManagerMessage.getMessage("webservice.nferecepcao2.wscall.exception");
			throw new CriticalUserException(ConsCadTestCase.class, message, e);
		} catch (WebServiceException e) {
			String message = ManagerMessage.getMessage("webservice.nferecepcao2.wscall.exception");
			throw new CriticalUserException(ConsCadTestCase.class, message, e);
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public R resultado(java.lang.String resultadoEnvio) throws WSResultException {
		try {
			ObjectFactory bindingsFactory = new ObjectFactory();
			R retConsCad = (R) bindingsFactory.createTRetConsCad();
			return WSUtil.unMarshall(retConsCad, resultadoEnvio, "/schemas/retConsCad_v2.00.xsd");
		} catch (Throwable e) {
			throw new WSResultException(ConsCadTestCase.class, e);
		}
	}
	
	public void addChildHeader(Document xmlEnvio, SOAPHeader header, SOAPFactory soapFactory, UFWsdlInfo wsdlInfo, UFInfo ufEnvio, Parametros waveParametros) throws SOAPException {
		SOAPElement headerNfeCabecMsg = header.addChildElement("nfeCabecMsg", "", wsdlInfo.getUri());
		SOAPElement headerNfeCabecMsgCUF = headerNfeCabecMsg.addChildElement("cUF");
		headerNfeCabecMsgCUF.setTextContent("35");
		SOAPElement headerNfeCabecMsgVersaoDados = headerNfeCabecMsg.addChildElement("versaoDados");
		headerNfeCabecMsgVersaoDados.setTextContent("2.00");
	}
	
	@Override
	public void addChildBody(Document xmlEnvio, SOAPBody body, SOAPFactory soapFactory, UFWsdlInfo wsdlInfo, UFInfo ufEnvio, Parametros waveParametros) throws SOAPException {
		SOAPElement bodyNfeDadosMsg = body.addChildElement("nfeDadosMsg", "", wsdlInfo.getUri());
		bodyNfeDadosMsg.addChildElement(soapFactory.createElement(xmlEnvio.getDocumentElement()));
	}

	@Override
	protected String getVersao() {
		return "1.00";
	}

	public static void main(String argv[]) throws Exception, ExcecaoSeguranca {
		ConsCadTestCase<TConsCad> tcc = new ConsCadTestCase<TConsCad>();
		int ambiente = 1;
		String xml = tcc.gerarXMLDistribuicao(ambiente);
		System.out.println(xml);
		ufEnvio = new UFInfo();
		ufEnvio.setCodIbge(35);
		ufEnvio.setSigla("SP");
		String xml_ = tcc.send(xml, ambiente);
		System.out.println(xml_);
	}
}