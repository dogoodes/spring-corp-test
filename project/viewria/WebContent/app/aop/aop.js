/**
 * @author: Alberto Cerqueira
 * @email: alberto.cerqueira1990@gmail.com
 */
jQuery.aop = function() {
	var aopClass = function() {
		this.init = (function() {
			$("#infoCliente").click(function() {
				_self.manterCliente("info");
				return false; 
			});
			
			$("#buscarCliente").click(function() {
				_self.manterCliente("buscar");
				return false; 
			});
			
			$("#inserirCliente").click(function() {
				_self.manterCliente("inserir");
				return false; 
			});
			
			$("#atualizarCliente").click(function() {
				_self.manterCliente("atualizar");
				return false; 
			});
			
			$("#excluirCliente").click(function() {
				_self.manterCliente("excluir");
				return false; 
			});
		});
		
		this.manterCliente = (function(acao) {
			var codigo = $("#codigo").val();
			var nome = $("#nome").val();
			if ((acao == "inserir" || acao == "atualizar" || acao == "excluir") && (codigo == null || codigo == "" || nome == null || nome == "")) {
				alert("Preencha os campos acima");
			} else {
				$("#manterCliente").ajaxSubmit({
					url : systemURL,
					dataType : "json",
					data : ({acao : acao}),
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
						alert(errorConexao);
					})
				});
			}
		});
		
		var _self = this;
	};
	return new aopClass();
};