package br.com.email;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import spring.corp.framework.configuracao.ManagerSetting;
import spring.corp.framework.email.ManagerEmail;
import spring.corp.framework.json.Consequence;
import spring.corp.framework.json.JSONFileAttachment;
import spring.corp.framework.json.JSONReturn;
import spring.corp.framework.log.ManagerLog;
import spring.corp.framework.utils.StringUtils;
import spring.corp.framework.view.GerenciadorUpload;

public class EnviarEmailComAnexo {

	public JSONReturn enviarEmailComAnexo(ServletRequest request, ServletResponse response) {
		ManagerLog.debug(EnviarEmailComAnexo.class, ("Enviando Email com Anexo"));
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String assunto = request.getParameter("assunto");
		String mensagem = request.getParameter("mensagem");
		String file = request.getParameter("arquivo");
		
		try {
			JSONFileAttachment attachment = null;
			if (!StringUtils.isBlank(file)) {
				attachment = GerenciadorUpload.recuperarArquivo(request, response, file);
			}
			String nameSender = ManagerSetting.getSetting("name.user");
			String sender = ManagerSetting.getSetting("mail.user");
			Map<String, String> recipients = new HashMap<String, String>();
			recipients.put(email, nome);
			ManagerEmail ge = ManagerEmail.builderInstance()
				.recipients(recipients)
				.subject(assunto)
				.attach(attachment == null ? null : (new JSONFileAttachment[]{attachment}))
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