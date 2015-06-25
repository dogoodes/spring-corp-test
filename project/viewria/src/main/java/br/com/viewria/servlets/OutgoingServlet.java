package br.com.viewria.servlets;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class OutgoingServlet extends spring.corp.framework.view.servlets.OutgoingServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected String executeWebClassSpring(ServletRequest request, ServletResponse response, String webClassId, String invoke) {
		ProcessRequest<String> processRequest = new ProcessRequest<String>(servletContext);
		return processRequest.executeWebClassSpring(request, response, webClassId, invoke);
	}

	@Override
	protected void preExecute(ServletRequest request, ServletResponse response) {
		ProcessRequest<String> processRequest = new ProcessRequest<String>(servletContext);
		processRequest.preExecute(request, response);
	}

	@Override
	protected void posExecute(ServletRequest request, ServletResponse response) {
		ProcessRequest<String> processRequest = new ProcessRequest<String>(servletContext);
		processRequest.posExecute(request, response);
	}
}