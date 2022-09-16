$(function () {
        $("#idInstitucion").change('.form-control',function(){
        var contexPath = '<%=request.getContextPath() %>'; 
        var idUnidad = this.value;
       // alert("Algo de yo "+userId);
            $.ajax({ url: 'lsedes/'+idUnidad,
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
});