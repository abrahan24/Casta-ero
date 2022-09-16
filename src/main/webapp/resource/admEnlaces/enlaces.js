// Llamada a Tablas dinámicas 

// === Cargar tipos
$(document).ready(function(){
 $("#btnMnuTiposEnlaces").click(function cargarMnuTiposEnlaces(e) {
 		e.preventDefault();
 		//$('input').next().remove();
		$.post({
			url : 'guardarMnuTiposEnlaces',
			data : $('form[name=mnuTiposEnlaces').serialize(),
			success : function(res) {
                                 $("#tipoEnlaces").html("");
                                var html = $("tipoEnlaces").html();
                                var len = res.length;
                                for(var i=0; i<len; i++){
                                    html += "<option value=\""  + res[i].idMnuTipoEnlace +"\">"+res[i].mnuTipoEnlace+"</option>";
                                }
                               $('#tipoEnlaces').html(html);
                                
 

   			}
		});
	});
       
       $("#btnMnuTiposFunciones").click(function cargarMnuTiposFunciones(e) {
 		e.preventDefault();
 		//$('input').next().remove();
		$.post({
			url : 'guardarMnuTiposFuncion',
			data : $('form[name=mnuTiposFunciones').serialize(),
			success : function(res) {
                                 $("#tipoFunciones").html("");
                                var html = $("tipoFunciones").html();
                                var len = res.length;
                                for(var i=0; i<len; i++){
                                    html += "<option value=\""  + res[i].idMnuTipoFuncion +"\">"+res[i].tipoFuncion+"</option>";
                                }
                               $('#tipoFunciones').html(html);
                                
 

   			}
		});
	});
        
         $("#btnSisAdministrador").click(function cargarSisAdministrador(e) {
 		e.preventDefault();
 		//$('input').next().remove();
		$.post({
			url : 'guardarSisAdministrador',
			data : $('form[name=mnuSisAdministrador').serialize(),
			success : function(res) {
                                 $("#sisAdministrador").html("");
                                var html = $("sisAdministrador").html();
                                var len = res.length;
                                for(var i=0; i<len; i++){
                                    html += "<option value=\""  + res[i].idSisAdministrador +"\">"+res[i].nombreSis+"-"+res[i].gestion+"</option>";
                           }
                               $('#sisAdministrador').html(html);
                                
 

   			}
		});
	});
        
        $("#btnSisNivelesAccesos").click(function cargarSisNivelesAccesos(e) {
 		e.preventDefault();
 		//$('input').next().remove();
		$.post({
			url : 'guardarSisNivelesAccesos',
			data : $('form[name=mnuSisNivelesAccesos').serialize(),
			success : function(res) {
                                 $("#sisNivelesAccesos").html("");
                                var html = $("sisNivelesAccesos").html();
                                var len = res.length;
                                for(var i=0; i<len; i++){
                                    html += "<option value=\""  + res[i].idNivelAcceso +"\">"+res[i].nivelAcceso +"</option>";
                                }
                               $('#sisNivelesAccesos').html(html);
                                
 

   			}
		});
	});
        
   });


$(document).ready(function() {
    $('#tEnlaces').DataTable();
} );

$(function() {
    $( "#tipoEnlaces" ).combobox();
});

$(function() {
    $( "#tipoFunciones" ).combobox();
});

$(function() {
    $( "#personas" ).combobox();
});

$(function() {
    $( "#sisAdministrador" ).combobox();
});

$(function() {
    $( "#sisNivelesAccesos" ).combobox();
});

