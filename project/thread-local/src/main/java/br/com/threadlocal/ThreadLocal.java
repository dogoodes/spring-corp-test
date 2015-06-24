package br.com.threadlocal;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import spring.corp.framework.json.Consequence;
import spring.corp.framework.json.JSONReturn;
import spring.corp.framework.log.GerenciadorLog;
import spring.corp.framework.security.EmailHolder;
import spring.corp.framework.security.IdHolder;
import spring.corp.framework.security.NameHolder;

public class ThreadLocal {

	public JSONReturn setDados(ServletRequest request, ServletResponse response) {
		GerenciadorLog.debug(ThreadLocal.class, ("Set dados na Thread Local"));
		String id = request.getParameter("id");
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession(true);
		
		IdHolder.set(Long.valueOf(id));
		session.setAttribute("id", id);
		
		NameHolder.set(nome);
		session.setAttribute("name", nome);
		
		EmailHolder.set(email);
		session.setAttribute("email", email);
		
		return JSONReturn.newInstance(Consequence.SUCESSO);
	}
	
	public JSONReturn getID(ServletRequest request, ServletResponse response) {
		GerenciadorLog.debug(ThreadLocal.class, ("Get dado da Thread Local"));
		return JSONReturn.newInstance(Consequence.SUCESSO, IdHolder.get());
	}
	
	public JSONReturn getNome(ServletRequest request, ServletResponse response) {
		GerenciadorLog.debug(ThreadLocal.class, ("Get dado da Thread Local"));
		return JSONReturn.newInstance(Consequence.SUCESSO, NameHolder.get());
	}
	
	public JSONReturn getEmail(ServletRequest request, ServletResponse response) {
		GerenciadorLog.debug(ThreadLocal.class, ("Get dado da Thread Local"));
		return JSONReturn.newInstance(Consequence.SUCESSO, EmailHolder.get());
	}
}