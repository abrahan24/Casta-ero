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


$(document).ready(function() {
    $('#tItems').DataTable();
} )

$(function() {
    $( "#sPersonas" ).combobox();
});

$(function() {
    $( "#sCargos" ).combobox();
});

$(function() {
    $( "#tipoAdmin" ).combobox();
});

$(function() {
    $( "#sItems" ).combobox();
});
/*
$(function() {
    $( "#idInstitucion" ).combobox();
});

$(function() {
    $( "#sedes" ).combobox();
});
*/
$(document).ready(function(){
	/*  Submit form using Ajax */
    $("#btnCargo").click(function(e) {
 		//Prevent default submission of form
		e.preventDefault();
 		//Remove all errors
		//$('input').next().remove();
		$.post({
			url : 'guardarPnlCargos',
			data : $('form[name=pnlCatgoForm').serialize(),
			success : function(res) {
                                 $("#sCargos").html("");
                                var html = $("sCargos").html();
                                var len = res.length;
                                for(var i=0; i<len; i++){
                                    html += "<option value=\""  + res[i].idPnlCargos +"\">"+res[i].cargo+"</option>";
                                }
                               $('#sCargos').html(html);

 			}
		});
	});

 $("#btnTipoAdmin").click(function(e) {
 		//Prevent default submission of form
		e.preventDefault();
 		//Remove all errors
		//$('input').next().remove();
		$.post({
			url : 'guardarPnlTipoAdministrativo',
			data : $('form[name=pnlTipoAdminForm').serialize(),
			success : function(res) {
                                 $("#tipoAdmin").html("")
                                var html = $("tipoAdmin").html();
                                var len = res.length;
                                for(var i=0; i<len; i++){
                                    html += "<option value=\""  + res[i].idPnlTipoAdministrativo +"\">"+res[i].tipoAdministrativo+"</option>";
                                }
                               $('#tipoAdmin').html(html);

 			}
		});
	});


 $("#btnPnlItems").click(function(e) {
 		//Prevent default submission of form
		e.preventDefault();
 		//Remove all errors
		//$('input').next().remove();
		$.post({
			url : 'guardarPnlItems',
			data : $('form[name=pnlItemsForm').serialize(),
			success : function(res) {
                                 $("#sItems").html("");
                                var html = $("sItems").html();
                                var len = res.length;
                                for(var i=0; i<len; i++){
                                    html += "<option value=\""  + res[i].idPnlItem +"\">"+res[i].item+"</option>";
                                }
                               $('#sItems').html(html);

 			}
		});
	});
        
$("#btnInstitucion").click(function(e) {
 		//Prevent default submission of form
		e.preventDefault();
 		//Remove all errors
		//$('input').next().remove();
		$.post({
			url : 'guardarInstituciones',
			data : $('form[name=institucionesForm').serialize(),
			success : function(res) {
                                 $("#idInstitucion").html("");
                                var html = $("idInstitucion").html();
                                var len = res.length;
                                for(var i=0; i<len; i++){
                                    html += "<option value=\""  + res[i].idInstitucion +"\">"+res[i].institucion+"</option>";
                                }
                               $('#idInstitucion').html(html);

 			}
		});
	});
        
// ============= Registrar Sedes ===========
$("#btnSedes").click(function(e) {
 		//Prevent default submission of form
		e.preventDefault();
 		//Remove all errors
		//$('input').next().remove();
		$.post({
			url : 'guardarInsSedes',
			data : $('form[name=insSedesForm').serialize(),
			success : function(res) {
                                 $("#sedes").html("");
                                var html = $("sedes").html();
                                var len = res.length;
                                for(var i=0; i<len; i++){
                                    html += "<option value=\""  + res[i].idSede +"\">"+res[i].sede+"</option>";
                                }
                               $('#sedes').html(html);

 			}
		});
	});

$("#btnUnidades").click(function(e) {
 		//Prevent default submission of form
		e.preventDefault();
 		//Remove all errors
		//$('input').next().remove();
		$.post({
			url : 'guardarUnidadesFuncionales',
			data : $('form[name=unidadesForm').serialize(),
			success : function(res) {
                                 $("#unidad").html("");
                                var html = $("unidad").html();
                                var len = res.length;
                                for(var i=0; i<len; i++){
                                    html += "<option value=\""  + res[i].idUnidadFuncional +"\">"+res[i].unidadFuncional+"</option>";
                                }
                               $('#unidad').html(html);

 			}
		});
	});

    $("#dFecInicio").hide();
    $("#dFecFinal").hide();
    
    $("#tipoAdmin").change(function(){
        var id = parseInt(this.value);
        if(id != 1){
            $("#dFecInicio").show();
            $("#dFecFinal").show();
           // document.getElementById('dFecInicio').style.display = 'block';
           // document.getElementById('dFecFinal').style.display = 'block';
        }else{
            $("#dFecInicio").hide();
            $("#dFecFinal").hide();
         }
     });
     
      $("#idInstitution").change('.form-institution',function(){
        var contexPath = '<%=request.getContextPath() %>'; 
        var idInstitucion = this.value;
        alert("Hoy de yo "+idInstitucion);
            $.ajax({ url: 'lInsSedes/'+idInstitucion,
                type: 'GET',
                dataType : 'json',
                contentType: "application/json;charset=utf-8",
                success: function (datos) {
                   $("#sedeU").html("");
                   var html = $("sedeU").html();
                  var len = datos.length;
                   for(var i=0; i<len; i++){
                       html += "<option value=\""  + datos[i].idSede +"\">"+datos[i].sede+"</option>"
                   }
                  $('#sedeU').html(html);
                	
                },
            error: function(error,xhr) {
				alert(error+" errorsss "+xhr.status);
				console.log(error);
			}
            
            });
    });
});

//$("#miModal).modal("show");


