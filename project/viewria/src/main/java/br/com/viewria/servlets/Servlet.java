package br.com.viewria.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import spring.corp.framework.CacheadorSessao;
import spring.corp.framework.Screen;
import spring.corp.framework.exceptions.UserException;
import spring.corp.framework.exceptions.UserLinkException;
import spring.corp.framework.i18n.GerenciadorMensagem;
import spring.corp.framework.i18n.GerenciadorTradutor;
import spring.corp.framework.json.Consequence;
import spring.corp.framework.json.JSONReturn;
import spring.corp.framework.log.GerenciadorLog;
import spring.corp.framework.utils.ScreenCollabUtil;
import spring.corp.framework.view.ClearCacheManager;
import spring.corp.framework.view.InputHolder;

public class Servlet extends AbstractServlet<JSONReturn> {
	private static final long serialVersionUID = 7069986813582011692L;
	
	@Override
	@SuppressWarnings("unchecked")
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String webClassId = request.getParameter("webClassId");
		String invoke = request.getParameter("invoke");
		String sendScreen = request.getParameter("sendScreen");
		if (ScreenCollabUtil.isSendScreen(sendScreen)) {
			invoke = "sendScreen";
		}
		
		//TODO: Colocar em um arquivo txt, pois nao precisa dar reflesh no projeto.
		//((HttpServletResponse )response).setHeader("Access-Control-Allow-Origin", "*");
		
		//Nao inicializar o objeto pois internamente o relatorio podera ser chamado e com isso o response ser usado.
		PrintWriter out = null; 
		if (webClassId == null) {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json;charset=UTF-8");
			JSONReturn jsonReturn = JSONReturn.newInstance(Consequence.ERRO);
			out = response.getWriter();
			out.print(jsonReturn.serialize());
			out.flush();
		} else if (webClassId.equals("ping")) {
			HttpServletRequest httpServletRequest = (HttpServletRequest)request;
			try {
				String locals = request.getLocale().getLanguage();
				GerenciadorTradutor gerencializadorTradutor = new GerenciadorTradutor(request.getLocale().getLanguage());
				Map<String, String> labels = (Map<String, String>) httpServletRequest.getSession(true).getAttribute(GerenciadorTradutor.LABEL_TRANSLATE);
				if (labels == null && !locals.equals("pt")) {
					labels = gerencializadorTradutor.externalize();
					httpServletRequest.getSession(true).setAttribute(GerenciadorTradutor.LABEL_TRANSLATE, labels);
				}
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/json;charset=UTF-8");
				Object[] dado = {labels, locals};
				JSONReturn jsonReturn = JSONReturn.newInstance(Consequence.SUCESSO, dado);
				out = response.getWriter();
				out.print(jsonReturn.serialize());
				out.flush();
			} catch (UserException e) {
				if (GerenciadorLog.isWarning(Servlet.class)) {
					GerenciadorLog.warn(Servlet.class, e.getLocalizedMessage());
				}
			}
		} else {
			try {
				preExecute(request, response);
				JSONReturn jsonReturn = executeWebClassSpring(request, response, webClassId, invoke);
				if (!jsonReturn.getConsequence().equals(Consequence.RELATORIO)) {
					response.setCharacterEncoding("UTF-8");
					response.setContentType("application/json;charset=UTF-8");
					out = getWriter(out, response);
					if (ScreenCollabUtil.isRecoverScreen(invoke) && jsonReturn.getConsequence().equals(Consequence.SUCESSO)) {
						out.print(((Screen)jsonReturn.getDado()).getJsonData());
					} else {
						out.print(jsonReturn.serialize());
					}
				}
			} catch (UserException e) {
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/json;charset=UTF-8");
				JSONReturn jsonReturn;
				out = getWriter(out, response);
				if (e instanceof UserLinkException) {
					jsonReturn = JSONReturn.newInstance(Consequence.MUITOS_ERROS, InputHolder.get());
				} else {
					jsonReturn = JSONReturn.newInstance(Consequence.ERRO).message(e.getMessage());
				}	
				out.print(jsonReturn.serialize());	
			}catch(Exception e){
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/json;charset=UTF-8");
				String message = GerenciadorMensagem.getMessage(GerenciadorMensagem.ERRO_GERAL);
				GerenciadorLog.critical(Servlet.class, e, message);
				out = getWriter(out, response);
				out.print(JSONReturn.newInstance(Consequence.ERRO).message(message).serialize());
			} finally {
				posExecute(request, response);
			}
		}
		if (out != null) {
			out.flush();
		}
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		GerenciadorLog.addLoggerDebug(Servlet.class, true);

		ClearCacheManager.addCache(CacheadorSessao.class);
		if (GerenciadorLog.isDebug(Servlet.class)) {
			String message = GerenciadorMensagem.getMessage("initial.application.server", "Servlet");
			GerenciadorLog.debug(Servlet.class, message);
		}
	}
}