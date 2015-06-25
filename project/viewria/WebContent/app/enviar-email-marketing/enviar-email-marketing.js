/**
 * @author: Alberto Cerqueira
 * @email: alberto.cerqueira1990@gmail.com
 */
jQuery.enviarEmailMarketing = function() {
	var enviarEmailMarketingClass = function() {
		this.init = (function() {
			$("#caminhoArquivo").change(function(){
				var files = this.files;
				if (files[0] != undefined && files[0].name != undefined) {
					file = files[0];
					$("#arquivo").val(files[0].name);
				} else {
					$("#arquivo").val(this.files.name);
				}
			});
			
			$("#enviar").click(function() {
				var html = $("#html").val();
				if (html != null && html != "") {
					_self.enviar();
				} else {
					_self.enviarComHtmlAnexado();
				}
				return false; 
			});
		});
		
		this.enviar = (function() {
			$("#enviarEmailMarketing").ajaxSubmit({
				url : systemURL,
				dataType : "json",
				success : (function(jsonReturn) {
					var consequence = jsonReturn.consequence;
					if (consequence == "ERRO") {
						alert(jsonReturn.message);
					} else if (consequence == "SUCESSO") {
						alert(jsonReturn.message);
					} else if(consequence == "MUITOS_ERROS"){
						var mensagem = [''];
						jQuery.each(jsonReturn.dado, function(i, dado) {
							mensagem.push(dado.localizedMessage + "\n");
						});
						alert(mensagem.join(''));
					}
					location.reload();
				}),
				error: (function(XMLHttpRequest, textStatus, errorThrown){
					alert("Algo aconteceu com sua conex\u00e3o! Verifique se voc\u00ea continua tendo acesso a internet e tente novamente mais tarde.");
				})
			});
		});
		
		this.enviarComHtmlAnexado = (function() {
			function toValidValue(value){
				if (value == null || value == "null"){
					value = "";
				}
				return value;
			}
			
			var caminho = toValidValue($("#caminhoArquivo").val());
			if (caminho != "") {
				var files = $("#caminhoArquivo");
				var file = null;
				if (files.attr("files")) {
					file = files.attr("files")[0];
				} else if (files[0] != undefined && files[0].files != undefined) {
					file = files[0].files[0];
				}
				if (file !== undefined && file !== null) {
					var fileSize = file.size;
					var fileType = file.type;
					var fileName = file.name;
					var vinteKb = 1024 * 20;

					if (fileSize > vinteKb) {
						// inserir validacao de tamanho
					}
					
					if (fileType == "text/html") {
						$("#arquivos").upload(uplURL, function(res) {
							var nome = $("#nome").val();
							var email = $("#email").val();
							var assunto = $("#assunto").val();
							var html = $("#html").val();
							
							$.ajax({
								type : "POST",
								url : systemURL,
								async : false,
								data : ({webClassId : "enviarEmailMarketing", 
										 invoke:"enviarEmailMarketing", 
										 arquivo:fileName, 
										 nome:nome, 
										 email:email, 
										 assunto:assunto,
										 html:html}),
								dataType : "json",
								success : (function(jsonReturn) {
									var consequence = jsonReturn.consequence;
									if (consequence == "ERRO") {
										alert(jsonReturn.message);
									} else if (consequence == "SUCESSO") {
										alert(jsonReturn.message);
									} else if(consequence == "MUITOS_ERROS"){
										var mensagem = [''];
										jQuery.each(jsonReturn.dado, function(i, dado) {
											mensagem.push(dado.localizedMessage + "\n");
										});
										alert(mensagem.join(''));
									}
									location.reload();
								})
							});
						}, 'html');
					} else {
						alert("Arquivo " + fileName + " n\u00e3o \u00e9 do tipo HTML.");
					}
				}
			}
		});
		
		var _self = this;
	};
	return new enviarEmailMarketingClass();
};