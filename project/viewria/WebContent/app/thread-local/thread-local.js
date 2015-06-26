/**
 * @author: Alberto Cerqueira
 * @email: alberto.cerqueira1990@gmail.com
 */
jQuery.threadLocal = function() {
	var threadLocalClass = function() {
		this.init = (function(){
			$("#set").click(function(){ 
				_self.set();
				return false; 
			});
			
			$("#verID").click(function(){ 
				_self.getID();
				return false; 
			});
			
			$("#verNome").click(function(){ 
				_self.getNome();
				return false; 
			});
			
			$("#verEmail").click(function(){ 
				_self.getEmail();
				return false; 
			});
		});
		
		this.set = (function() {
			$("#threadLocal").ajaxSubmit({
				url : systemURL,
				dataType : "json",
				success : (function(jsonReturn){
					var consequence = jsonReturn.consequence;
					if (consequence == "ERRO") {
						alert(jsonReturn.message);
					} else if (consequence == "SUCESSO") {
						window.location = "thread-local-get.html";
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
		
		this.getID = (function() {
			$.ajax({
				type : "POST",
				url : systemURL,
				data : ({webClassId : "threadLocal", invoke:"getID"}),
				dataType : "json",
				success : (function(jsonReturn) {
					var consequence = jsonReturn.consequence;
					if (consequence == "ERRO") {
						alert(jsonReturn.message);
						return false;
					} else if (consequence == "SUCESSO" || consequence == "AVISO") {
						$("#id").val(jsonReturn.dado);
					}
				}),
				error: (function(XMLHttpRequest, textStatus, errorThrown){
					alert("Algo aconteceu com sua conex\u00e3o! Verifique se voc\u00ea continua tendo acesso a internet e tente novamente mais tarde.");
				})
			});
		});
		
		this.getNome = (function() {
			$.ajax({
				type : "POST",
				url : systemURL,
				data : ({webClassId : "threadLocal", invoke:"getNome"}),
				dataType : "json",
				success : (function(jsonReturn) {
					var consequence = jsonReturn.consequence;
					if (consequence == "ERRO") {
						alert(jsonReturn.message);
						return false;
					} else if (consequence == "SUCESSO" || consequence == "AVISO") {
						$("#nome").val(jsonReturn.dado);
					}
				})
			});
		});
		
		this.getEmail = (function() {
			$.ajax({
				type : "POST",
				url : systemURL,
				data : ({webClassId : "threadLocal", invoke:"getEmail"}),
				dataType : "json",
				success : (function(jsonReturn) {
					var consequence = jsonReturn.consequence;
					if (consequence == "ERRO") {
						alert(jsonReturn.message);
						return false;
					} else if (consequence == "SUCESSO" || consequence == "AVISO") {
						$("#email").val(jsonReturn.dado);
					}
				})
			});
		});
		
		var _self = this;
	};
	return new threadLocalClass();
};