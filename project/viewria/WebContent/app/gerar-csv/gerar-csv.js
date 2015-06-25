/**
 * @author: Alberto Cerqueira
 * @email: alberto.cerqueira1990@gmail.com
 */
jQuery.gerarCSV = function() {
	var gerarCSVClass = function() {
		this.init = (function() {
			$("#gerar").click(function() {
				_self.gerar();
				return false; 
			});
		});
		
		this.gerar = (function() {
			$("#gerarCSV").ajaxSubmit({
				url : systemURL,
				dataType : "json",
				success : (function(jsonReturn) {
					var consequence = jsonReturn.consequence;
					if (consequence == "ERRO") {
						alert(jsonReturn.message);
					} else if (consequence == "SUCESSO") {
						if (jsonReturn.dado) {
							var jsonConstant = jsonReturn.dado;
							$("#invoke").val("downloadFile");
							var action = $("#gerarCSV").attr("action");
							$("#gerarCSV").attr("action", (attURL + "?webClassId=gerarCSV&invoke=downloadFile&fileName=" + jsonConstant.fileName));
							$("#gerarCSV").submit();
							$("#gerarCSV").attr("action", action);
						}
						//alert(jsonReturn.message);
						//return false;
					} else if(consequence == "MUITOS_ERROS"){
						var mensagem = [''];
						jQuery.each(jsonReturn.dado, function(i, dado) {
							mensagem.push(dado.localizedMessage + "\n");
						});
						alert(mensagem.join(''));
					}
				}),
				error: (function(XMLHttpRequest, textStatus, errorThrown){
					alert("Algo aconteceu com sua conex\u00e3o! Verifique se voc\u00ea continua tendo acesso a internet e tente novamente mais tarde.");
				})
			});
		});
		
		var _self = this;
	};
	return new gerarCSVClass();
};