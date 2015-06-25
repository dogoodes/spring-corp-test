package br.com.viewria.servlets;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import spring.corp.framework.json.JSONReturn;
import spring.corp.framework.view.servlets.UploadServlet;

public class UploadFileServlet extends UploadServlet {

	private static final long serialVersionUID = 1L;

	protected JSONReturn executeWebClassSpring(ServletRequest request, ServletResponse response, String webClassId, String invoke) {
		ServletContext servletContext = getServletContext();
		ProcessRequest<JSONReturn> processRequest = new ProcessRequest<JSONReturn>(servletContext);
		return processRequest.executeWebClassSpring(request, response, webClassId, invoke);
	}
	
	protected void preExecute(ServletRequest request, ServletResponse response) {
		ProcessRequest<JSONReturn> processRequest = new ProcessRequest<JSONReturn>(getServletContext());
		processRequest.preExecute(request, response);
	}

	@Override
	protected void posExecute(ServletRequest request, ServletResponse response) {
		ProcessRequest<JSONReturn> processRequest = new ProcessRequest<JSONReturn>(getServletContext());
		processRequest.posExecute(request, response);
	}
}