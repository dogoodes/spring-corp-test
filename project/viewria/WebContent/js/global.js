var context ="/utils-viewria/";
var contextServlet = "/utils-viewria/"; 

var systemURL= contextServlet + "servlet/servlet.app";
var uplURL = contextServlet + "servlet/upload.app";
var attURL = contextServlet + "servlet/attachment.erp";

var errorConexao = "Algo aconteceu com sua conex\u00e3o! Verifique se voc\u00ea continua tendo acesso a internet e tente novamente mais tarde.";

$(document).ready(function() {
	$("form").each(function(i, form){
		$(this).attr("action", systemURL);
	});
	
	addTagsHead();
	ga();
});

function ga() {
	/*
	$.getScript("/viewria/js/ga.js", function(){
		var location = window.location.pathname.split("/");
		var page = location[location.length-1];
		if(page == "home.html"){
			$(document).ready(page.init);
		}
	});
	*/
}

function addTagsHead() {
	
}