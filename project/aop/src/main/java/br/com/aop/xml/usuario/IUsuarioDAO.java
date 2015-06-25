package br.com.aop.xml.usuario;

public interface IUsuarioDAO {

	public void info();
	public void error() throws Throwable;
	public Usuario buscar();	
	public void inserir(Usuario usuario);
	public void atualizar(Usuario usuario);
	public void excluir(String codigo);
}