package br.com.aop.xml;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.com.aop.xml.cliente.Cliente;
import br.com.aop.xml.cliente.IClienteDAO;
import br.com.aop.xml.usuario.IUsuarioDAO;
import br.com.aop.xml.usuario.Usuario;

public class App {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext(
				new String[] {
						"classpath:/META-INF/spring/datasource-context.xml",
						"classpath:/META-INF/spring/aop-context.xml"
					});

		BeanFactory factory = (BeanFactory) appContext;

		IClienteDAO clienteDAO = (IClienteDAO) factory.getBean("clienteDAO");
		clienteDAO.info();
		System.out.println();
		try {
			clienteDAO.error();
			System.out.println();
		} catch (Throwable e) {
			// Este erro foi previsto...
		}
		clienteDAO.buscar();
		System.out.println();
		clienteDAO.inserir(new Cliente("123", "Alberto Cerqueira"));
		System.out.println();
		clienteDAO.atualizar(new Cliente("456", "Alberto Cerqueira"));
		System.out.println();
		clienteDAO.excluir("789");
		System.out.println();
		
		IUsuarioDAO usuarioDAO = (IUsuarioDAO) factory.getBean("usuarioDAO");
		usuarioDAO.info();
		System.out.println();
		try {
			usuarioDAO.error();
			System.out.println();
		} catch (Throwable e) {
			// Este erro foi previsto...
		}
		usuarioDAO.buscar();
		System.out.println();
		usuarioDAO.inserir(new Usuario("123", "Alberto Cerqueira"));
		System.out.println();
		usuarioDAO.atualizar(new Usuario("456", "Alberto Cerqueira"));
		System.out.println();
		usuarioDAO.excluir("789");
		System.out.println();
	}
}