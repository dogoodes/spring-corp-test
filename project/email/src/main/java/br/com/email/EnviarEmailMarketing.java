package br.com.email;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import spring.corp.framework.configuracao.ManagerSetting;
import spring.corp.framework.email.ManagerEmail;
import spring.corp.framework.io.SerializableInputStream;
import spring.corp.framework.json.Consequence;
import spring.corp.framework.json.JSONFileAttachment;
import spring.corp.framework.json.JSONReturn;
import spring.corp.framework.log.ManagerLog;
import spring.corp.framework.utils.StringUtils;
import spring.corp.framework.view.GerenciadorUpload;

public class EnviarEmailMarketing {

	public JSONReturn enviarEmailMarketing(ServletRequest request, ServletResponse response) {
		ManagerLog.debug(EnviarEmailMarketing.class, ("Enviando Email Marketing"));
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String assunto = request.getParameter("assunto");
		String html = request.getParameter("html");
		String file = request.getParameter("arquivo");
		
		try {
			JSONFileAttachment attachment = null;
			SerializableInputStream s = null;
			if (StringUtils.isBlank(html) && !StringUtils.isBlank(file)) {
				attachment = GerenciadorUpload.recuperarArquivo(request, response, file);
				s = (SerializableInputStream) attachment.getFile();
			} else if (StringUtils.isBlank(html)) {
				html = "<html><body><h2>Teste Email Marketing</h2><p>Sem HTML.</p></body></html>";
			}
			
			String htmlSend = "";
			if (StringUtils.isBlank(html)) {
				htmlSend = new String(s.getByte()).replaceAll("\n", "");
			} else {
				htmlSend = html.replaceAll("\n", "");
			}
			
			String nameSender = ManagerSetting.getSetting("name.user");
			String sender = ManagerSetting.getSetting("mail.user");
			Map<String, String> recipients = new HashMap<String, String>();
			recipients.put(email, nome);
			ManagerEmail ge = ManagerEmail.builderInstance()
				.recipients(recipients)
				.subject(assunto)
				.html(htmlSend)
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