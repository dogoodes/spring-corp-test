package br.com.viewria.servlets;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public abstract class AbstractServlet<T> extends spring.corp.framework.view.servlets.AbstractServlet<T> {

	private static final long serialVersionUID = 1L;
	
	protected T executeWebClassSpring(ServletRequest request, ServletResponse response, String webClassId, String invoke) {
		ServletContext servletContext = getServletContext();
		ProcessRequest<T> processRequest = new ProcessRequest<T>(servletContext);
		return processRequest.executeWebClassSpring(request, response, webClassId, invoke);
	}
	
	protected void preExecute(ServletRequest request, ServletResponse response) {
		ProcessRequest<T> processRequest = new ProcessRequest<T>(getServletContext());
		processRequest.preExecute(request, response);
	}

	@Override
	protected void posExecute(ServletRequest request, ServletResponse response) {
		ProcessRequest<T> processRequest = new ProcessRequest<T>(getServletContext());
		processRequest.posExecute(request, response);
	}
}