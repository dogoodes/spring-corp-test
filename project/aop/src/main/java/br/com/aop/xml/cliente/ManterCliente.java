package br.com.aop.xml.cliente;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import spring.corp.framework.json.Consequence;
import spring.corp.framework.json.JSONReturn;
import spring.corp.framework.utils.StringUtils;

public class ManterCliente {

	private IClienteDAO clienteDAO;
	
	public JSONReturn inserir(ServletRequest request, ServletResponse response) {
		String codigo = request.getParameter("codigo");
		String nome = request.getParameter("nome");
		String acao = request.getParameter("acao");
		
		if (!StringUtils.isBlank(acao)) {
			if (acao.equals("info")) {
				clienteDAO.info();
			} else if (acao.equals("buscar")) {
				clienteDAO.buscar();
			} else if (acao.equals("inserir")) {
				clienteDAO.inserir(new Cliente(codigo, nome));
			} else if (acao.equals("atualizar")) {
				clienteDAO.atualizar(new Cliente(codigo, nome));
			} else if (acao.equals("excluir")) {
				clienteDAO.excluir(codigo);
			}
		}
		return JSONReturn.newInstance(Consequence.SUCESSO).message("Sucesso - " + acao);
	}

	public IClienteDAO getClienteDAO() {
		return clienteDAO;
	}

	public void setClienteDAO(IClienteDAO clienteDAO) {
		this.clienteDAO = clienteDAO;
	}
}