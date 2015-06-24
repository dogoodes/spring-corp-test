package br.com.email;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import spring.corp.framework.configuracao.GerenciadorConfiguracao;
import spring.corp.framework.email.GerenciadorEmail;
import spring.corp.framework.json.Consequence;
import spring.corp.framework.json.JSONReturn;
import spring.corp.framework.log.GerenciadorLog;
import spring.corp.framework.utils.StringUtils;

public class EnviarEmail {

	public JSONReturn enviarEmail(ServletRequest request, ServletResponse response) {
		GerenciadorLog.debug(EnviarEmail.class, ("Enviando Email"));
		String name = request.getParameter("nome");
		String email = request.getParameter("email");
		String assunto = request.getParameter("assunto");
		String mensagem = request.getParameter("mensagem");
		
		try {
			String nameSender = GerenciadorConfiguracao.getConfiguracao("name.user");
			String sender = GerenciadorConfiguracao.getConfiguracao("mail.user");
			Map<String, String> recipients = new HashMap<String, String>();
			recipients.put(email, name);
			GerenciadorEmail ge = GerenciadorEmail.builderInstance()
				.recipients(recipients)
				.subject(assunto)
				.message(StringUtils.isBlank(mensagem) ? "Sem mensagem" : mensagem)
				.name(nameSender)
				.from(sender)
				.build();
			Thread t = new Thread(ge);
			t.start();
			return JSONReturn.newInstance(Consequence.SUCESSO).messageKey("view.mensagem.enviada.com.sucesso");
		} catch (Exception e) {
			e.printStackTrace();
			return JSONReturn.newInstance(Consequence.ERRO).messageKey("web.execution.erro.geral");
		}
	}
}