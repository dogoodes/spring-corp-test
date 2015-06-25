package br.com.aop.xml.usuario;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import spring.corp.framework.json.Consequence;
import spring.corp.framework.json.JSONReturn;
import spring.corp.framework.utils.StringUtils;

public class ManterUsuario {

	private IUsuarioDAO usuarioDAO;
	
	public JSONReturn inserir(ServletRequest request, ServletResponse response) {
		String codigo = request.getParameter("codigo");
		String nome = request.getParameter("nome");
		String acao = request.getParameter("acao");
		
		if (!StringUtils.isBlank(acao)) {
			if (acao.equals("info")) {
				usuarioDAO.info();
			} else if (acao.equals("buscar")) {
				usuarioDAO.buscar();
			} else if (acao.equals("inserir")) {
				usuarioDAO.inserir(new Usuario(codigo, nome));
			} else if (acao.equals("atualizar")) {
				usuarioDAO.atualizar(new Usuario(codigo, nome));
			} else if (acao.equals("excluir")) {
				usuarioDAO.excluir(codigo);
			}
		}
		return JSONReturn.newInstance(Consequence.SUCESSO).message("Sucesso - " + acao);
	}

	public IUsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(IUsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}
}