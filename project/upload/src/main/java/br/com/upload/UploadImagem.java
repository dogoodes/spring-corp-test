package br.com.upload;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.StringTokenizer;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import spring.corp.framework.configuracao.ManagerSetting;
import spring.corp.framework.i18n.ManagerMessage;
import spring.corp.framework.io.SerializableInputStream;
import spring.corp.framework.json.Consequence;
import spring.corp.framework.json.JSONFileAttachment;
import spring.corp.framework.json.JSONReturn;
import spring.corp.framework.log.ManagerLog;
import spring.corp.framework.utils.DateUtils;
import spring.corp.framework.utils.StringUtils;
import spring.corp.framework.view.GerenciadorUpload;

public class UploadImagem {

	private final String nomePasta = "pasta-arquivos";
	
	public JSONReturn uploadImagem(ServletRequest request, ServletResponse response) {
		return recuperarArquivo(request, response);
	}
	
	public JSONReturn recuperarArquivo(ServletRequest request, ServletResponse response) {
		String fileName = request.getParameter("fileName");
		ManagerLog.debug(UploadImagem.class, ("Upload de imagem : " + fileName));
		JSONFileAttachment jsonFileAttachment = GerenciadorUpload.recuperarArquivo(request, response, fileName);
		try {
			SerializableInputStream serializableInputStream = (SerializableInputStream) jsonFileAttachment.getFile();
			String pasta = ManagerSetting.getSetting("diretorio.upload");
			pasta = pasta.replaceAll("\\$\\{pastausuario\\}", nomePasta);
			 
			File pastaFisica = new File(pasta);
			boolean pastaFisicaExiste = pastaFisica.exists();
			if (!pastaFisicaExiste) {
				boolean wasCreated = pastaFisica.mkdirs();
				if (!wasCreated) {
					StringTokenizer token = new StringTokenizer(pasta, "/");
					String path = "";
					while (token.hasMoreTokens()) {
						String childFolder = token.nextToken();
						path = path+ "/" + childFolder;
						File newChildFolder = new File(path);
						if (!newChildFolder.exists()) {
							if (!newChildFolder.mkdir()) {
								String message = ManagerMessage.getMessage("upload.diretorio.nao.criado", newChildFolder.getPath());
								ManagerLog.critical(UploadImagem.class, message);
							}
						}
					}
				}
			}
			
			String dataAtual = DateUtils.calendarToInteger(DateUtils.getCalendar()).toString();
			String horaAtual = DateUtils.calendarToIntegerHora(DateUtils.getCalendar()).toString();
			String dataHoraAtual = dataAtual + StringUtils.leftPad(horaAtual, "0", 6);
			String extensao = "";
			boolean temExtensao = fileName.lastIndexOf(".") > -1;
			if (temExtensao) {
				extensao = fileName.substring(fileName.lastIndexOf("."));
			}
			int quantidadeArquivos = pastaFisica.listFiles().length;
			String nomeArquivo = "hora-" + dataHoraAtual + "-arquivo-" + quantidadeArquivos + extensao;
			File arquivo = new File(pasta + nomeArquivo);
			boolean arquivoExiste = arquivo.exists();
			if (!arquivoExiste) {
				arquivo.createNewFile();
			}
			
			FileOutputStream out = new FileOutputStream(arquivo);
			BufferedOutputStream bos = new BufferedOutputStream(out);
			
			int ler = -1;
			while ((ler = serializableInputStream.read()) != -1) {
				bos.write(ler);
			}
			
			bos.flush();
			bos.close();
			serializableInputStream.close();
			return JSONReturn.newInstance(Consequence.SUCESSO).messageKey("view.upload.feita.com.sucesso");
		} catch (IOException e) { 
			e.printStackTrace();
			return JSONReturn.newInstance(Consequence.ERRO, e);
		}
	}
}