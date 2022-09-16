$(function () {
    var i = 0;
    $("#btnAddRazonSoc").click('.form-sedes', function () {
        var idPersona = $("#lPersona").val();
         $.ajax({url: 'findPeople/' + idPersona,
            type: 'GET',
            dataType: 'json',
            contentType: "application/json;charset=utf-8",
            success: function (datos) {
                i++;
                $('#tPersona').append('<tr id="row' + i + '"><td>' + i + '</td><td>' + datos.ci + '</td><td><input type="hidden" name="idPersona2" value="' + datos.idPersona + '" id=inp"' + i + '" />' + datos.nombres + ' ' + datos.paterno + ' ' + datos.materno + '</td><td><button type="button" name="remove" id="' + i + '" class="btn btn-danger" onclick="deleteRow(this)">X</button></td></tr>');
            },
            error: function (error, xhr) {
                alert(error + " errorsss " + xhr.status);
                console.log(error);
            }
         });
    });

});

// ---------------- ojito 

$(function () {
    $("#btnSupervisor").click(function (e) {
        e.preventDefault();
        alert("Coloca");
        //alert("Yes " + res.poaisSupervisores.descripcion);
        //$('input').next().remove();
        $.post({
            url: 'guardarSupervisor',
            data: $('form[name=supervisoreForm').serialize(),
            success: function (res) {
                $("#lSupervisor").html("");
                //  alert("Yo " + res.personas.nombres);
                var html = $("lSupervisor").html();
                var len = res.length;
                for (var i = 0; i < len; i++) {
                    html += "<option value=\"" + res[i].idSupervisor + "\">" + res[i].personas.nombres + "&nbsp;" + res[i].personas.paterno + " " + res[i].personas.materno + "</option>";
                }
                $('#lSupervisor').html(html);
                //alert("Agregado en la lista");
            }
        });
    });

});


/*
$(function () {
    var i = 0;
    $("#lPersona").change('.form-sedes', function () {
        var idPersona = this.value;
        //alert("yo soy "+idPersona);
        $.ajax({url: 'findPeople/' + idPersona,
            type: 'GET',
            dataType: 'json',
            contentType: "application/json;charset=utf-8",
            success: function (datos) {
                  $('#tPersona').append('<tr id="row' + i + '"><td>' + i + '</td><td>' + datos.ci + '</td><td><input type="hidden" name="idPersona2" value="' + datos.idPersona + '" id=inp"' + i + '" />' + datos.nombres + ' ' + datos.paterno + ' ' + datos.materno + '</td><td><button type="button" name="remove" id="' + i + '" class="btn btn-danger" onclick="deleteRow(this)">X</button></td></tr>');
            },
            error: function (error, xhr) {
                alert(error + " errorsss " + xhr.status);
                console.log(error);
            }

        });
    });

});
*/

//-------------Modal listar estantes y carpetas
$(function () {
    $("#idSacEstante").change('.form-idSacEstante', function () {
        var idSacEstante = this.value;
        //alert("Hoy de yo "+idSede);
        $.ajax({url: 'lCarpetas/' + idSacEstante,
            type: 'GET',
            dataType: 'json',
            contentType: "application/json;charset=utf-8",
            success: function (datos) {
                $("#carpeta").html("");
                var html = $("carpeta").html();
                var len = datos.length;
                for (var i = 0; i < len; i++) {
                    html += "<option value=\"" + datos[i].idSacCarpeta + "\">" + datos[i].sacCodigoCarpeta + "</option>"
                }
                $('#carpeta').html(html);

            },
            error: function (error, xhr) {
                alert(error + " errorsss " + xhr.status);
                console.log(error);
            }

        });
    });

});
$(function () {
//Modales registrar Tipos Comprobantes
    $("#btnSacTiposComprobantes").click(function (e) {
        e.preventDefault();
        //$('input').next().remove();
        $.post({
            url: 'guardarSacTiposComprobantes',
            data: $('form[name=sacTiposComprobantesForm').serialize(),
            success: function (res) {
                $("#tComprobante").html("");
                var html = $("tComprobante").html();
                var len = res.length;
                for (var i = 0; i < len; i++) {
                    html += "<option value=\"" + res[i].idSacTipoComprobante + "\">" + res[i].sacTipoComprobante + "</option>";
                }
                $('#tComprobante').html(html);

            }
        });
    });

});

	$(function () {
	//Modales registrar Tipos Pagos
	    $("#btnSacTiposPagos").click(function (e) {
	        e.preventDefault();
	        alert("Tao");
	        //$('input').next().remove();
	        $.post({
	            url: 'guardarSacTiposPagos',
	            data: $('form[name=sacTiposPagosForm').serialize(),
	            success: function (res) {
	                $("#tPagos").html("");
	                var html = $("tPagos").html();
	                var len = res.length;
	                for (var i = 0; i < len; i++) {
	                    html += "<option value=\"" + res[i].idSacTipoPago + "\">" + res[i].sacTipoPago + "</option>";
	                }
	                $('#tPagos').html(html);
	
	
	
	            }
	        });
	    });
	
	});

//Modales registrar Personas
$(function () {
    $("#btnPersonas").click(function (e) {
        e.preventDefault();
        //alert("Yes " + res.personas.paterno);
        //$('input').next().remove();
        $.post({
            url: 'guardarPersonas',
            data: $('form[name=personasForm').serialize(),
            success: function (res) {
                $("#lPersona").html("");
                //  alert("Yo " + res.personas.nombres);
                var html = $("lPersona").html();
                var len = res.length;
                for (var i = 0; i < len; i++) {
                    html += "<option value=\"" + res[i].idPersona + "\">" + res[i].nombres + "&nbsp;" + res[i].paterno + " " + res[i].materno + "</option>";
                }
                $('#lPersona').html(html);
                //alert("Agregado en la lista");
            }
        });
    });

});

/*
 function montoM(valor) {
 var date day = -document.getElementById("fecha").value;
 alert("hola "+day.getDay();
 }
 */


function checkdate(input) {
    var nroComprobante = document.getElementById("nroComprobante").value;
    var nroCheque = document.getElementById("nroCheque").value;


    var cadena = "cadena de texto",
            ini = 0;
    fin = 4;
    year = input.substring(ini, fin);

    inim = 5;
    finm = 7;
    month = input.substring(inim, finm);

    inid = 8;
    find = 10;
    day = input.substring(inid, find);

    //   alert(day+"dia"+ month+" mes"+year+" hola"+input);

    var unionC = nroComprobante + "-" + nroCheque + "-" + day + "" + month + "" + year + ".pdf";
    //  alert("hi "+unionC);
    document.getElementById('fileName').value = unionC;
}


$(function () {
    $("#tComprobante").combobox();
});

$(function () {
    $("#tPagos").combobox();
});
$(document).ready(function () {
    $('#tComprobantes').DataTable();
});


//Agregar input
$(document).ready(function () {
    var maxField = 10; //Input fields increment limitation
    var addButton = $('.add_button'); //Add button selector
    var wrapper = $('.field_wrapper'); //Input field wrapper
    var fieldHTML = '<div><input type="text" name="field_name[]" value=""/><a href="javascript:void(0);" class="remove_button" title="Remove field"><img src="remove-icon.png"/></a></div>'; //New input field html 
    var x = 1; //Initial field counter is 1
    $(addButton).click(function () { //Once add button is clicked
        if (x < maxField) { //Check maximum number of input fields
            x++; //Increment field counter
            $(wrapper).append(fieldHTML); // Add field html
        }
    });
    $(wrapper).on('click', '.remove_button', function (e) { //Once remove button is clicked
        e.preventDefault();
        $(this).parent('div').remove(); //Remove field html
        x--; //Decrement field counter
    });
});

$(document).ready(function () {
    var i = 0;
    $("#btnNroCheque").click(function (e) {
        var nroCheque = document.getElementById("nroCheque").value;
        i++;
        $('#tNroCh').append('<tr id="row' + i + '"><td>' + i + '</td><td>' + nroCheque + '</td><input type="hidden" name="nroCheque2" value="' + nroCheque + '" id=inp"' + i + '" /><td><button type="button" name="remove" id="' + i + '" class="btn btn-danger" onclick="deleteRow(this)">X</button></td></tr>');
      });
});

$(document).ready(function () {
    var i = 0;
    $("#btnNroComprobante").click(function (e) {
         var nroComprobante = document.getElementById("nroComprobante").value;
         i++;
         $('#tNroComp').append('<tr id="row' + i + '"><td>' + i + '</td><td>' + nroComprobante + '</td><input type="hidden" name="nroComprobante2" value="' + nroComprobante + '" id=inp"' + i + '" /><td><button type="button" name="remove" id="' + i + '" class="btn btn-danger" onclick="deleteRow(this)">X</button></td></tr>');
    });
});


//Funcion modificar numero comprobante
$(document).ready(function () {
    $(document).on('click', '.edit', function () {
        var id = $(this).val();
        var idNroComp = $('#idNroComp' + id).text();
        var nroComp = $('#nroComp' + id).text();
        var idSacComp = document.getElementById('idSacComprobante').value;
        //  alert("hola " + idSacComp);
        $('#formNroComprobante').modal('show');
        $('#idSacNroComp').val(idNroComp);
        $('#sacNroComp').val(nroComp);
        $('#idSacComprob').val(idSacComp);
    });
    
    // ======== elimianr nro comprobante
     $(document).on('click', '.deletNroCheckee', function () {
        var id = $(this).val();
             event.preventDefault();
             $(this).closest('tr').remove();
            //alert("oooo "+id);
            $.ajax({url: 'deletNroChecke/'+id,
            type: 'GET',
            dataType: 'json',
            contentType: "application/json;charset=utf-8",
            success: function (datos) {
                 //alert("hola");
            },
            error: function (error, xhr) {
                alert(error + " errorsss " + xhr.status);
                console.log(error);
            }
         });
     });
     
     $(document).on('click', '.deletNroCom', function () {
        var id = $(this).val();
             event.preventDefault();
                    $(this).closest('tr').remove();
            $.ajax({url: 'deletNroVoucher/' + id,
            type: 'GET',
            dataType: 'json',
            contentType: "application/json;charset=utf-8",
            success: function (datos) {
                 
              },
            error: function (error, xhr) {
                alert(error + " errorsss " + xhr.status);
                console.log(error);
            }
         });
     }); 
    
 // ================ Fin eliminar nro comprobante ====== 
 
     $(document).on('click', '.deletRazonSocial', function () {
        var id = $(this).val();
            event.preventDefault();
            $(this).closest('tr').remove();
           // alert("Delet RS "+id);
            $.ajax({url: 'deletRazonSocial/' + id,
            type: 'GET',
            dataType: 'json',
            contentType: "application/json;charset=utf-8",
            success: function (datos) {
                 alert("Se elimino correctamente");
              },
            error: function (error, xhr) {
                alert(error + " errorsss " + xhr.status);
                console.log(error);
            }
         });
     }); 
    
 // ======== Elimianr Razon Social =============
   
 // ================ Fin eliminar nro cheque ====== 
});
//final boton modificar
$(function () {
//Modales actualizar Numeros Comprobantes
    $("#btnSacNumeroComprobantes").click(function (e) {
        //    alert("Paso el numero comprobante");
        e.preventDefault();
        //$('input').next().remove();
        $.post({
            url: 'actualizarNroComprobantes',
            data: $('form[name=sacNroComprobantesForm').serialize(),
            success: function (res) {
                //$("#idSacCompNroComprobante").html("");
                //   alert("algo"+res.sacNroComprobante);
                $("#nroComp" + res.idSacCompNroComprobante).text(res.sacNroComprobante);
            }
        });
    });

});


//Funcion modificar numero chequeee
$(document).ready(function () {
    $(document).on('click', '.editCh', function () {
        var id = $(this).val();
        var idNroCheque = $('#idNroCheque' + id).text();
        var nroCheque = $('#nroCheques' + id).text();
        var idSacCompChe = document.getElementById('idSacComprobante').value;
        //  alert("hola " + idSacComp);
        $('#formNroChequeEditar').modal('show');
        $('#idSacNroCheque').val(idNroCheque);
        $('#sacNroCheque').val(nroCheque);
        $('#idSacCompCheque').val(idSacCompChe);

    });
});
//final boton modificar cheque
$(function () {
//Modales actualizar Numeros chequuuueee
    $("#btnSacNumeroCheques").click(function (e) {
        //     alert("Paso el numero cheque");
        e.preventDefault();
        //$('input').next().remove();

        $.post({
            url: 'actualizarNroCheques',
            data: $('form[name=sacNroChequesForm').serialize(),
            success: function (res) {
                //$("#idSacCompNroComprobante").html("");
                //  alert("algo"+res.sacNroCheque);
                $("#nroCheques" + res.idSacCompNroCheque).text(res.sacNroCheque);
            }
        });
    });

});


$(function () {
//Modales actualizar personas
    $("#btnEditarPersonas").click(function (e) {
        //  alert("Paso la persona por aqui");
        e.preventDefault();
        //$('input').next().remove();
        $.post({
            url: 'actualizarDatosPersonasRazonSocial',
            data: $('form[name=personasEditarForm').serialize(),
            success: function (res) {
                $("#idPrs" + res.idPersona).text(res.idPersona);
                $("#ci" + res.idPersona).text(res.ci);
                $("#nombres" + res.idPersona).text(res.nombres +' '+res.paterno +' '+res.materno);
             }
        });
    });

});

// === Agregar nro comprobante / modulo modificar ====
$(function () {
    var i = 0;
    $("#btnNroComprobanteEdit").click('.form-sedes', function () {
        var nro = document.getElementById("nroCompEdit").value;
        var id = document.getElementById("idSacComprobante").value;
         // var idPersona = this.value;
        $.ajax({url: "agregarNroComp/" + nro + "/" + id,
            type: 'POST',
            dataType: 'json',
            contentType: "application/json;charset=utf-8",
            success: function (datos) {
                i++;
                $('#tNroCompEdit').append('<tr><td><span id="idNroComp' + datos.idSacCompNroComprobante + '">'+ datos.idSacCompNroComprobante +'</span></td><td><span id="nroComp' + datos.idSacCompNroComprobante + '">'+ datos.sacNroComprobante +'</span></td><td><button type="button" class="btn btn-success edit" value="'+ datos.idSacCompNroComprobante +'"><span class="glyphicon glyphicon-edit"></span>M</button></td><td><button type="button" class="btn btn-danger deletNroCom" value="'+ datos.idSacCompNroComprobante +'"><span class="glyphicon glyphicon-edit"></span>X</button></td></tr>');
            },
            error: function (error, xhr) {
                alert(error + " errorsss " + xhr.status);
                console.log(error);
            }

        });
    });

});
//======== Fin agregar nro comprobante - modulo modificar
//======== Agregar nro Cheque - modulo modificar  ==
$(function () {
    var i = 0;
    $("#btnNroChequeAgregarEdit").click('.form-sedes', function () {
        var nro = document.getElementById("nroChequesEdit").value;
        var id = document.getElementById("idSacComprobante").value;
        //alert(nro + " hola -f" + id);
        // var idPersona = this.value;
        $.ajax({url: "agregarNroChequeEdit/" + nro + "/" + id,
            type: 'POST',
            dataType: 'json',
            contentType: "application/json;charset=utf-8",
            success: function (datos) {
                i++;
                alert("Agregado a la lista de Numero de Cheque ");
                $('#tNroCh').append('<tr><td><span id="idNroCheque' + datos.idSacCompNroCheque + '">'+ datos.idSacCompNroCheque +'</span></td><td><span id="nroCheques' + datos.idSacCompNroCheque + '">'+ datos.sacNroCheque +'</span></td><td><button type="button" class="btn btn-success editCh" value="'+ datos.idSacCompNroCheque +'"><span class="glyphicon glyphicon-edit"></span>M</button></td><td><button type="button" class="btn btn-danger deletNroCheckee" value="'+ datos.idSacCompNroCheque +'"><span class="glyphicon glyphicon-edit"></span>X</button></td></tr>');                
            },
            error: function (error, xhr) {
                alert(error + " errorsss " + xhr.status);
                console.log(error);
            }

        });
    });

});

function dNroComp(btn) {
    var row = btn.parentNode.parentNode;
    var id = $("#cantNroComp").val();
    $("#cantNroComp").val(id - 1);
    row.parentNode.removeChild(row);
    id = $("#cantNroComp").val();
}


function deleteRow(btn) {
    var row = btn.parentNode.parentNode;
    row.parentNode.removeChild(row);
}
//=================
/*
 $(function () {
 $("#btnEditarPrs").click(function (e) {
 
 var idPersona = $(this).val();
 alert("Hoy dd "+idPersona);
 $.ajax({url: 'buscarPersonasPorIdPersona/' + idPersona,
 type: 'GET',
 dataType: 'json',
 contentType: "application/json;charset=utf-8",
 success: function (datos) {
 alert(" cedula " + datos.ci + datos.nombres);
 $("#RasonSCi").val(datos.ci);
 $("#nombreRs").val(datos.nombres);
 // $("#idPrsCiExpedidoRazonS").val(datos.prsCiExpedidos.idCiPrsExpedido);
 $('#formPersonasEditar').modal('show');
 },
 error: function (error, xhr) {
 alert(error + " errorsss " + xhr.status);
 console.log(error);
 }
 
 });
 });
 
 });
 */

$(function() {
    $( "#lPersona" ).combobox();
});

//======== Agregar nro Cheque - modulo modificar  ==
$(function () {
    var i = 0;
    $("#btnAgregarPersonaEdit").click('.form-sedes', function () {
        var idPersona = document.getElementById("lPersona").value;
        var idSacComp = document.getElementById("idSacComprobante").value;
        //alert("yooooo");
         // var idPersona = this.value;
        $.ajax({url: "agregarRazonSocialEdit/" + idPersona + "/" + idSacComp,
            type: 'POST',
            dataType: 'json',
            contentType: "application/json;charset=utf-8",
            success: function (datos) {
                i++;
                //alert("raza" + datos.idSacRazonSocial);
                $('#tPersona').append('<tr><td><span id="idPrs' + datos.idPersona + '">'+ datos.idPersona +'</span></td><td><span id="ci' + datos.idPersona + '">'+ datos.ci +'</span></td><td><span id="nombres' + datos.idPersona + '">'+ datos.nombres +' '+ datos.paterno + ' '+ datos.materno +'</span></td><td><button type="button" class="btn btn-success editPrs" value="'+ datos.idPersona +'"><span class="glyphicon glyphicon-edit"></span>M</button></td><td><button type="button" class="btn btn-danger deletRazonSocial" value="'+ datos.idSacRazonSocial +'"><span class="glyphicon glyphicon-edit"></span>X</button></td></tr>');                
            },
            error: function (error, xhr) {
                //alert(error + " errorsss " + xhr.status);
                console.log(error);
            }
         });
    });

});

//Funcion modificar personasss
$(document).ready(function () {
    $(document).on('click', '.editPrs', function () {
        var idPersona = $(this).val();
        //alert("he aqui"+idPersona);
        $.ajax({url: "buscarPersonaResponse/" + idPersona,
            type: 'POST',
            dataType: 'json',
            contentType: "application/json;charset=utf-8",
            success: function (datos) {
                    $('#formPersonasEditar').modal('show');
                    $('#idPersonaFrm').val(datos.idPersona);
                    $('#ciFrm').val(datos.ci);
                    $('#nombresFrm').val(datos.nombres);
                    $('#paternoFrm').val(datos.paterno);
                    $('#maternoFrm').val(datos.materno);
                    $('#idPaisFrm').val(datos.idPais);
                    $('#idPrsTipoSexoFrm').val(datos.idPrsTipoSexo);
                    $('#idPrsCiExpedidoFrm').val(datos.idPrsCiExpedido);
             },
            error: function (error, xhr) {
                alert(error + " errorsss " + xhr.status);
                console.log(error);
            }
         });
      
    });
});
//final boton modificar personasssssssss

