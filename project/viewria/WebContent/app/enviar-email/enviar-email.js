/**
 * @author: Alberto Cerqueira
 * @email: alberto.cerqueira1990@gmail.com
 */
jQuery.enviarEmail = function() {
	var enviarEmailClass = function() {
		this.init = (function() {
			$("#enviar").click(function() {
				_self.enviar();
				return false; 
			});
		});
		
		this.enviar = (function() {
			$("#enviarEmail").ajaxSubmit({
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
		
		var _self = this;
	};
	return new enviarEmailClass();
};