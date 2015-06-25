package br.com.viewria.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import spring.corp.framework.configuracao.GerenciadorConfiguracao;
import spring.corp.framework.i18n.GerenciadorMensagem;
import spring.corp.framework.json.Consequence;
import spring.corp.framework.json.JSONReturn;
import spring.corp.framework.security.SecurityConstants;

public class SecurityFilter implements Filter {

	private static List<String> noSecurityActions = new ArrayList<String>();
	static {
		noSecurityActions.add(SecurityConstants.ACAO_LOGAR);
		noSecurityActions.add(SecurityConstants.ACAO_REGISTRAR);
	}
	private static List<String> offSecurityActions = new ArrayList<String>();
	static {
		noSecurityActions.add(SecurityConstants.ACAO_DESLOGAR);
		noSecurityActions.add(SecurityConstants.ACAO_REDIRECT);
	}
	private static List<String> firstAccessActions = new ArrayList<String>();
	static {
		firstAccessActions.add(SecurityConstants.ACAO_MONITOR_STATUS);
	}
	
	private List<SmartPixelAccess> smartPixelAccess = new ArrayList<SmartPixelAccess>();
	
	public SecurityFilter() {
		smartPixelAccess.add(new SmartPixelAccess("pagina1", "pagina2"));//TODO: Entender arquitetura...
	}

	final class SmartPixelAccess {
		private final String webClassId;
		private final String invoke;
		
		public SmartPixelAccess(String webClassId, String invoke) {
			this.webClassId = webClassId;
			this.invoke = invoke;
		}

		public String getWebClassId() {
			return webClassId;
		}

		public String getInvoke() {
			return invoke;
		}
	}
	
	public void destroy() {}

	public void init(FilterConfig config) throws ServletException {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if (isSmartPixel(request, response)){
			chain.doFilter(request, response);
			return;
		}
			
		String webClassId = request.getParameter("webClassId");
		if (webClassId == null) {
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			String message = GerenciadorMensagem.getMessage("view.webclassid.nao.informado");
			httpResponse.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, message);
			return;
		}

		if (estaLogandoOuRegistrando(webClassId) || estaDeslogando(webClassId)) {
			chain.doFilter(request, response);
			return;
		} else {
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			HttpSession session = httpRequest.getSession(true);
			/*
			Usuario usuario = (Usuario) session.getAttribute(SecurityConstants.USER_KEY);
			if (estaAcessandoSemUsuarioLogado(usuario)) {
				semAccesso(request, response);
				return;
			} else {
				if (!cadastroEmDia(httpRequest, usuario)) {
					acessoRestrito(httpRequest, response);
					return;
				}
			}
			*/
		}
		chain.doFilter(request, response);
	}
	/*
	private boolean cadastroEmDia(ServletRequest request, Usuario usuario){
		return (usuario != null);//TODO: Adicionar tambem verificacao de usuario ativo.
	}
	*/
	private boolean isSmartPixel(ServletRequest request, ServletResponse response) {
		String webClassId = request.getParameter("webClassId");
		String invoke = request.getParameter("invoke");
		boolean isSmartPixel = false;
		for (SmartPixelAccess sp : smartPixelAccess) {
			if (sp.getWebClassId().equals(webClassId) && sp.getInvoke().equals(invoke)) {
				isSmartPixel = true;
				break;
			}
		}
		return isSmartPixel;
	}
	
	private String getUrl(ServletRequest request){
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String pathBrowser = httpRequest.getHeader("referer");
		String url = pathBrowser.substring(pathBrowser.lastIndexOf("/")+1);
		return url;
	}

	private boolean estaLogandoOuRegistrando(String webClassId) {
		return noSecurityActions.contains(webClassId);
	}
	
	private boolean estaDeslogando(String webClassId) {
		return offSecurityActions.contains(webClassId);
	}
/*
	private boolean estaAcessandoSemUsuarioLogado(Usuario usuario) {
		return usuario == null;
	}
	*/
	private void semAccesso(ServletRequest request, ServletResponse response) throws IOException{
		String message = GerenciadorMensagem.getMessage("view.security.login.null");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String context = GerenciadorConfiguracao.getConfiguracao("contexto");
		JSONReturn jsonReturn = JSONReturn.newInstance(Consequence.SEM_ACESSO).message(message).page(context + "/" + "index.html");
		out.print(jsonReturn.serialize());
	}
	
	private void acessoRestrito(ServletRequest request, ServletResponse response) throws IOException {
		String message = GerenciadorMensagem.getMessage("view.screen.screen.nao.autorizado");
		writeError(request, response, message, GerenciadorConfiguracao.getConfiguracao("mainUrl"));
	}
	
	private void writeError(ServletRequest request, ServletResponse response, String message, String redirectPath) throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String contexto = GerenciadorConfiguracao.getConfiguracao("contexto");
		JSONReturn jsonReturn = JSONReturn.newInstance(Consequence.ERRO).message(message).page(contexto + "/" + redirectPath);
		out.print(jsonReturn.serialize());
	}
}