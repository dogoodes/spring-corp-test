package br.com.viewria.servlets;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class IncomingServlet extends spring.corp.framework.view.servlets.IncomingServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected Void executeWebClassSpring(ServletRequest request, ServletResponse response, String webClassId, String invoke) {
		ProcessRequest<Void> processRequest = new ProcessRequest<Void>(servletContext);
		return processRequest.executeWebClassSpring(request, response, webClassId, invoke);
	}

	@Override
	protected void preExecute(ServletRequest request, ServletResponse response) {
		ProcessRequest<Void> processRequest = new ProcessRequest<Void>(servletContext);
		processRequest.preExecute(request, response);
	}

	@Override
	protected void posExecute(ServletRequest request, ServletResponse response) {
		ProcessRequest<Void> processRequest = new ProcessRequest<Void>(servletContext);
		processRequest.posExecute(request, response);
	}
	
	@Override
	protected String getWebClassId(String acao) {
		String webClassId = null;
		if (acao.equals(ACAO_1) || acao.equals(ACAO_1)) {
			webClassId = "manterAcao";
		} else if (acao.equals(ACAO_2)) {
			webClassId = "manterAcao";
		}
		return webClassId;
	}
}