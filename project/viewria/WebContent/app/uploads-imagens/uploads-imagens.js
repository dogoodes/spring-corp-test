/**
 * @author: Alberto Cerqueira
 * @email: alberto.cerqueira1990@gmail.com
 */
jQuery.uploadsImagens = function() {
	var uploadsImagensClass = function() {
		this.init = (function() {
			$("#filesPath").change(function () {
				_self.carregarImagens(this);
			});
			
			$("#upload").click(function(){ 
				_self.upload();
				return false; 
			});
		});
		
		this.carregarImagens = (function(input) {
			if (input != null && input.files) {
				for (var x = 0, i = input.files.length ; x < i ; x++) {
					if (input.files[x] && (input.files[x].type == "image/jpeg" || input.files[x].type == "image/jpg" || input.files[x].type == "image/png" || input.files[x].type == "image/gif")) {						
						var reader = new FileReader();
						reader.onload = function (e) {
							$('#imagens').append("<img src=" + e.target.result + " width='100' height='100' style='padding: 10px;' />");
						};
						reader.readAsDataURL(input.files[x]);
					} else {
						alert("Arquivo " + input.files[x].name + " n\u00e3o \u00e9 imagem!!!");
					}
				}
			}
		});
		
		this.upload = (function() {
			function toValidValue(value){
				if (value == null || value == "null"){
					value = "";
				}
				return value;
			}
			
			var caminho = toValidValue($("#filesPath").val());
			if (caminho != "") {
				var files = null;
				if ($("#filesPath").attr("files")) {
					files = $("#filesPath").attr("files");
				} else if ($("#filesPath")[0] != undefined && $("#filesPath")[0].files != undefined) {
					files = $("#filesPath")[0].files;
				}
				var isUploadValido = true;
				for (var x = 0, i = files.length ; x < i ; x++) {
					var file = files[x];
					if (file !== undefined && file !== null) {
						var fileSize = file.size;
						var fileType = file.type;
						//var fileName = file.name;
						var vinteKb = 1024 * 20;
						
						if (fileSize > vinteKb) {
							// inserir validacao de tamanho
						}
						
						if (fileType != "image/jpeg" && fileType != "image/jpg" && fileType != "image/png" && fileType != "image/gif") {
							isUploadValido = false;
						}
					}
				}
				
				if (isUploadValido) {							
					$("#fileinputs").upload(uplURL, function(res) {
						$("#uploadImagens").ajaxSubmit({
							url : systemURL,
							dataType:"json",
							data: ({webClassId : "uploadImagens"}),
							beforeSubmit: function(formData, jqForm, options) {
								for (var x = 0, i = files.length ; x < i ; x++) {
									var file = files[x];
									var fileName = file.name;
									formData.push({name : "filesName", value : toValidValue(fileName)});
								}
							},
							success: function(jsonReturn) {
								var consequence = jsonReturn.consequence;
								if (consequence == "ERRO") {
									alert(jsonReturn.message);
									return false;
								} else if (consequence == "SUCESSO" || consequence == "AVISO") {
									alert(jsonReturn.message);
								}
							},
							error: (function(XMLHttpRequest, textStatus, errorThrown){
								alert("Algo aconteceu com sua conex\u00e3o! Verifique se voc\u00ea continua tendo acesso a internet e tente novamente mais tarde.");
							})
						});
					}, 'html');
				} else {
					alert("Um arquivo n\u00e3o \u00e9 imagem.");
				}
			}
		});
		
		var _self = this;
	};
	return new uploadsImagensClass();
};