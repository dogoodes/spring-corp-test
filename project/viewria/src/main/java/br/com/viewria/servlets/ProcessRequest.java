package br.com.viewria.servlets;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import spring.corp.framework.exceptions.UserException;
import spring.corp.framework.i18n.GerenciadorMensagem;
import spring.corp.framework.security.EmailHolder;
import spring.corp.framework.security.IdHolder;
import spring.corp.framework.security.NameHolder;
import spring.corp.framework.utils.StringUtils;
import spring.corp.framework.view.InputHolder;
import spring.corp.framework.view.ServletContextHolder;

public class ProcessRequest<T> extends AbstractServlet<T> {

	private static final long serialVersionUID = 1L;
	private ServletContext servletContext;
	
	public ProcessRequest(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	
	public T executeWebClassSpring(ServletRequest request, ServletResponse response, String webClassId, String invoke) {
		WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		Object webClass = webApplicationContext.getBean(webClassId);
		if (webClass == null) {
			String message = GerenciadorMensagem.getMessage("view.webclassid.invalido", new Object[] { webClassId });
			throw new UserException(message);
		}
		return executeWebClass(request, response, webClass, invoke);
	}
	
	public void preExecute(ServletRequest request, ServletResponse response) {
		InputHolder.set(new ArrayList<UserException>());
		ServletContextHolder.set(servletContext);
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession(true);
		
		String id = (String) session.getAttribute("id");
		if (!StringUtils.isBlank(id)) {
			IdHolder.set(Long.valueOf(id));
		}
		
		String nome = (String) session.getAttribute("name");
		NameHolder.set(nome);
		
		String email = (String) session.getAttribute("email");
		EmailHolder.set(email);
	}
	
	public void posExecute(ServletRequest request, ServletResponse response) {
		InputHolder.clear();
		ServletContextHolder.clear();
		NameHolder.clear();
		IdHolder.clear();
	}
}