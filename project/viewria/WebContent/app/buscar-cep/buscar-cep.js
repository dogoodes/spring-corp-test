/**
 * @author: Alberto Cerqueira
 * @email: alberto.cerqueira1990@gmail.com
 */
jQuery.buscarCep = function() {
	var buscarCepClass = function() {
		this.init = (function(){
			$("#buscar").click(function(){ 
				_self.manter();
				return false; 
			});
		});
		
		this.manter = (function() {
			$("#buscarCep").ajaxSubmit({
				url : systemURL,
				dataType : "json",
				success : (function(jsonReturn){
					var consequence = jsonReturn.consequence;
					if (consequence == "ERRO") {
						alert(jsonReturn.message);
					} else if (consequence == "SUCESSO") {
						if (jsonReturn.dado.resultado == "1") {							
							var mensagem = [''];
							mensagem.push("Logradouro: " + jsonReturn.dado.tipo_logradouro + " " + jsonReturn.dado.logradouro + "<br />");
							mensagem.push("Bairro: " + jsonReturn.dado.bairro + "<br />");
							mensagem.push("Cidade: " + jsonReturn.dado.cidade + "<br />");
							mensagem.push("UF: " + jsonReturn.dado.uf + "<br />");
							$("#resultado").empty().append(mensagem.join(''));
						} else {							
							$("#resultado").empty().append("Cep n\u00e3o encontrado.");
						}
					} else if(consequence == "MUITOS_ERROS"){
						var mensagem = [''];
						jQuery.each(jsonReturn.dado, function(i, dado) {
							mensagem.push(dado.localizedMessage + "\n");
						});
						alert(mensagem.join(''));
					}
				}),
				error : (function(XMLHttpRequest, textStatus, errorThrown){
					alert(errorConexao);
				})
			});
		});
		
		var _self = this;
	};
	return new buscarCepClass();
};