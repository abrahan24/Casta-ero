$(document).ready(function(){
	
// === cargar Estantes
   $("#btnSacEstantes").click(function(e) {
 		e.preventDefault();
 		//$('input').next().remove();
		$.post({
			url : 'guardarSacEstantes',
			data : $('form[name=sacEstantesForm').serialize(),
			success : function(res) {
                                 $("#estante").html("");
                                var html = $("estante").html();
                                var len = res.length;
                                for(var i=0; i<len; i++){
                                    html += "<option value=\""  + res[i].idSacEstante +"\">"+res[i].sacNombreEstante+"</option>";
                                }
                               $('#estante').html(html);

 			}
		});
	});

// === Cargar Expedidos Ci
 $("#btnSacTiposCarpetas").click(function(e) {
 		e.preventDefault();
 		//$('input').next().remove();
		$.post({
			url : 'guardarSacTiposCarpetas',
			data : $('form[name=sacTiposCarpetasForm').serialize(),
			success : function(res) {
                                 $("#tipoCarpeta").html("");
                                var html = $("tipoCarpeta").html();
                                var len = res.length;
                                for(var i=0; i<len; i++){
                                    html += "<option value=\""  + res[i].idSacTipoCarpeta +"\">"+res[i].sacTipoCarpeta+"</option>";
                                }
                               $('#tipoCarpeta').html(html);
                                
 

   			}
		});
	});
});



  
$(function() {
    $( "#estante" ).combobox();
});

$(function() {
    $( "#tipoCarpeta" ).combobox();
});

$(document).ready(function() {
    $('#tCarpetas').DataTable();
} );

