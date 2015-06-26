/**
 * @author: Alberto Cerqueira
 * @email: alberto.cerqueira1990@gmail.com
 */
jQuery.controller = function() {
	var controllerClass = function() {
		this.init = (function(){
			$("#gravar").click(function(){ 
				_self.gravar();
				return false; 
			});
		});
		
		this.gravar = (function() {
			$("#manterEntidade").ajaxSubmit({
				url : systemURL,
				dataType : "json",
				success : (function(jsonReturn){
					var consequence = jsonReturn.consequence;
					if (consequence == "ERRO") {
						alert(jsonReturn.message);
					} else if (consequence == "SUCESSO") {
						alert(jsonReturn.message + ": " + jsonReturn.dado.toString());
					} else if(consequence == "MUITOS_ERROS"){
						var mensagem = [''];
						jQuery.each(jsonReturn.dado, function(i, dado) {
							mensagem.push(dado.localizedMessage + "\n");
						});
						alert(mensagem.join(''));
					}
					//location.reload();
				}),
				error : (function(XMLHttpRequest, textStatus, errorThrown){
					alert(errorConexao);
				})
			});
		});
		
		var _self = this;
	};
	return new controllerClass();
};