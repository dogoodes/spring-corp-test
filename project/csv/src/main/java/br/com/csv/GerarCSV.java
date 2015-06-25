package br.com.csv;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import spring.corp.framework.i18n.ManagerMessage;
import spring.corp.framework.json.Consequence;
import spring.corp.framework.json.JSONFileAttachment;
import spring.corp.framework.json.JSONReturn;
import spring.corp.framework.metadatabean.DelimiterMetaDataManager;
import spring.corp.framework.utils.DateUtils;

public class GerarCSV {

	public static void main(String[] args) {
		CSV csv = new CSV();
		csv.setValorString1("alberto");
		csv.setValorString1("cerqueira");
		csv.setValorInteger(1990);
		csv.setValorDate(new Date());
		csv.setValorBigDecimal(new BigDecimal(1000.0000));
		csv.setValorBoolean(false);
		csv.setValorLong(new Long(1000));
		
		CSVMetaDataBean smdb = CSVMetaDataBean.newInstance(csv, ';');
		DelimiterMetaDataManager manager = new DelimiterMetaDataManager(smdb, ';');
		System.out.println(manager.deMaterialize());
	}
	
	public JSONReturn gerarCSV(ServletRequest request, ServletResponse response) {
		String string1 = request.getParameter("string1");
		String string2 = request.getParameter("string2");
		String integer = request.getParameter("integer");
		String date = request.getParameter("date");
		String bigdecimal = request.getParameter("bigdecimal");
		String _long = request.getParameter("long");
		String _boolean = request.getParameter("boolean");
		
		CSV csv = new CSV();
		csv.setValorString1(string1);
		csv.setValorString2(string2);
		csv.setValorInteger(Integer.parseInt(integer));
		try {
			csv.setValorDate(DateUtils.stringToDate(date, "dd/MM/yyyy"));
		} catch (ParseException e) {
			csv.setValorDate(new Date());
		}
		csv.setValorBigDecimal(new BigDecimal(bigdecimal));
		csv.setValorLong(new Long(_long));
		csv.setValorBoolean(Boolean.valueOf(_boolean));
		
		CSVMetaDataBean smdb = CSVMetaDataBean.newInstance(csv, ';');
		DelimiterMetaDataManager manager = new DelimiterMetaDataManager(smdb, ';');
		String arquivo = manager.deMaterialize();
		
		JSONFileAttachment jsonFileAttachment = new JSONFileAttachment();
		jsonFileAttachment.setContentType(JSONFileAttachment.TEXT_ATTACHMENT);
		jsonFileAttachment.setFileName("csv.txt");
		jsonFileAttachment.setFile(arquivo);

		HttpSession session = ((HttpServletRequest) request).getSession(true);
		session.setAttribute(jsonFileAttachment.getFileName(), jsonFileAttachment);
		
		String message = ManagerMessage.getMessage("arquivo.criar", "csv");
		return JSONReturn.newInstance(Consequence.SUCESSO, jsonFileAttachment).message(message);
	}
}