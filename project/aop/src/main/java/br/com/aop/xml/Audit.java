package br.com.aop.xml;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import spring.corp.framework.audit.IAuditable;

public class Audit {

	@Transactional(propagation = Propagation.MANDATORY)
	public void info() {
		System.out.println("Audit-info");
	}
	
	@Transactional(propagation = Propagation.MANDATORY)
	public void error(Throwable error) {
		System.out.println("Audit-error [" + error.getMessage() + "]");
	}
	
	@Transactional(propagation = Propagation.MANDATORY)
	public void buscar(IAuditable orm) {
		System.out.println("Audit-buscar [Class=" + orm.getClass().getName() + ", uuid=" + orm.getUuid() + "]");
	}
	
	@Transactional(propagation = Propagation.MANDATORY)
	public void inserir(IAuditable orm) {
		System.out.println("Audit-inserir [Class=" + orm.getClass().getName() + ", uuid=" + orm.getUuid() + "]");
	}
	
	@Transactional(propagation = Propagation.MANDATORY)
	public void atualizar(IAuditable orm) {
		System.out.println("Audit-atualizar [Class=" + orm.getClass().getName() + ", uuid=" + orm.getUuid() + "]");
	}
	
	@Transactional(propagation = Propagation.MANDATORY)
	public void excluir(String codigo) {
		System.out.println("Audit-excluir [codigo=" + codigo + "]");
	}
}