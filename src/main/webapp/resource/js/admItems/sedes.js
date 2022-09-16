$(function () {
        $("#idInstitucion").change('.form-institution',function(){
        var contexPath = '<%=request.getContextPath() %>'; 
        var idInstitucion = this.value;
        //alert("Hoy de yo "+idInstitucion);
            $.ajax({ url: 'lInsSedes/'+idInstitucion,
                type: 'GET',
                dataType : 'json',
                contentType: "application/json;charset=utf-8",
                success: function (datos) {
                   $("#sedes").html("");
                   var html = $("sedes").html();
                  var len = datos.length;
                   for(var i=0; i<len; i++){
                       html += "<option value=\""  + datos[i].idSede +"\">"+datos[i].sede+"</option>"
                   }
                  $('#sedes').html(html);
                	
                },
            error: function(error,xhr) {
				alert(error+" errorsss "+xhr.status);
				console.log(error);
			}
            
            });
    });
    
    // Listar Unidades
    
     $("#sedes").change('.form-sedes',function(){
        var contexPath = '<%=request.getContextPath() %>'; 
        var idSede = this.value;
        //alert("Sede de yo "+idSede);
            $.ajax({ url: 'lUnidades/'+idSede,
                type: 'GET',
                dataType : 'json',
                contentType: "application/json;charset=utf-8",
                success: function (datos) {
                   $("#unidad").html("");
                   var html = $("unidad").html();
                  var len = datos.length;
                   for(var i=0; i<len; i++){
                       html += "<option value=\""  + datos[i].idUnidadFuncional+"\">"+datos[i].unidadFuncional+"</option>"
                   }
                  $('#unidad').html(html);
                	
                },
            error: function(error,xhr) {
				alert(error+" errorsss "+xhr.status);
				console.log(error);
			}
            
            });
    });
});