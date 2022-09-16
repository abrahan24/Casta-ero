$(function () {
        $("#idSede").change('.form-institution',function(){
         var idSede = this.value;
        //alert("Hoy de yo "+idSede);
            $.ajax({ url: 'lDireccionesArqueoGlobal/'+idSede,
                type: 'GET',
                dataType : 'json',
                contentType: "application/json;charset=utf-8",
                success: function (datos) {
                   $("#dFuncional").html("");
                   var html = $("dFuncional").html();
                  var len = datos.length;
                   for(var i=0; i<len; i++){
                       html += "<option value=\""  + datos[i].idDireccionFuncional +"\">"+datos[i].direccionFuncional+"</option>"
                   }
                  $('#dFuncional').html(html);
                	
                },
            error: function(error,xhr) {
				alert(error+" errorsss "+xhr.status);
				console.log(error);
			}
            
            });
    });
    
});

//========= Listar Unidad 

$(function () {
        $("#dFuncional").change('.form-institution',function(){
         var idDireccionFuncional = this.value;
        //alert("Hoy de yo "+idSede);
            $.ajax({ url: 'lUnidadesArqueoGlobal/'+idDireccionFuncional,
                type: 'GET',
                dataType : 'json',
                contentType: "application/json;charset=utf-8",
                success: function (datos) {
                   $("#uFuncional").html("");
                   var html = $("uFuncional").html();
                  var len = datos.length;
                   for(var i=0; i<len; i++){
                       html += "<option value=\""  + datos[i].idUnidadFuncional +"\">"+datos[i].unidadFuncional+"</option>"
                   }
                  $('#uFuncional').html(html);
                	
                },
            error: function(error,xhr) {
				alert(error+" errorsss "+xhr.status);
				console.log(error);
			}
            
            });
    });
    
});
