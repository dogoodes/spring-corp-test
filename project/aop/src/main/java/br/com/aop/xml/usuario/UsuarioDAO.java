package br.com.aop.xml.usuario;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import spring.corp.framework.exceptions.UserException;
import spring.corp.framework.i18n.ManagerMessage;

public class UsuarioDAO implements IUsuarioDAO {

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void info() {
		System.out.println("Classe usuarioDAO!!!");
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=true)
	public void error() throws Throwable {
		String mesagem =  ManagerMessage.getMessage(ManagerMessage.ERRO_GERAL);
		throw new UserException(mesagem);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=true)
	public Usuario buscar() {
		System.out.println("Buscando usu\u00e1rio!!!");
		return new Usuario();
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void inserir(Usuario usuario) {
		System.out.println("Inserindo usu\u00e1rio: " + usuario.toString());
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void atualizar(Usuario usuario) {
		System.out.println("Atualizando usu\u00e1rio: " + usuario.toString());
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void excluir(String codigo) {
		System.out.println("Excluindo usu\u00e1rio com c\u00f3digo: " + codigo);
	}
}