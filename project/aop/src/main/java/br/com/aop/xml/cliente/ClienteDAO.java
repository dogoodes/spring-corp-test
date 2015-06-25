package br.com.aop.xml.cliente;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import spring.corp.framework.exceptions.UserException;
import spring.corp.framework.i18n.ManagerMessage;

public class ClienteDAO implements IClienteDAO {

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void info() {
		System.out.println("Classe ClienteDAO!!!");
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void error() throws Throwable {
		String mesagem =  ManagerMessage.getMessage(ManagerMessage.ERRO_GERAL);
		throw new UserException(mesagem);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=true)
	public Cliente buscar() {
		System.out.println("Buscando cliente!!!");
		return new Cliente();
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void inserir(Cliente cliente) {
		System.out.println("Inserindo cliente: " + cliente.toString());
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void atualizar(Cliente cliente) {
		System.out.println("Atualizando cliente: " + cliente.toString());
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void excluir(String codigo) {
		System.out.println("Excluindo cliente com c\u00f3digo: " + codigo);
	}
}