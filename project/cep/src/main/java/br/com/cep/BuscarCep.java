package br.com.cep;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import spring.corp.framework.json.Consequence;
import spring.corp.framework.json.JSONReturn;
import spring.corp.framework.log.GerenciadorLog;
import spring.corp.framework.ws.Cep;
import spring.corp.framework.ws.Webservicecep;

public class BuscarCep {

	public JSONReturn buscarCep(ServletRequest request, ServletResponse response) {
		try {
			String cep = request.getParameter("cep");
			GerenciadorLog.debug(BuscarCep.class, ("Buscando CEP : " + cep));
			Webservicecep cepResult = Cep.getEndereco(cep);
			return JSONReturn.newInstance(Consequence.SUCESSO, cepResult);
		} catch (Exception e) {
			return JSONReturn.newInstance(Consequence.ERRO).messageKey("view.buscarcep.cep.nao.encontrado");
		}
	}
}