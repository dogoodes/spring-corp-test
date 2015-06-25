package br.com.aop.xml.cliente;

public interface IClienteDAO {

	public void info();
	public void error() throws Throwable;
	public Cliente buscar();	
	public void inserir(Cliente cliente);
	public void atualizar(Cliente cliente);
	public void excluir(String codigo);
}