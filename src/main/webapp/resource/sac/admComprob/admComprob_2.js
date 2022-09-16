
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
                i++;
                $("#cantBenif").val(i);
                $('#tPersona').append('<tr id="row' + i + '"><td>' + i + '</td><td>' + datos.ci + '</td><td><input type="hidden" name="idPersona2' + i + '" value="' + datos.idPersona + '" id=inp"' + i + '" />' + datos.nombres + ' ' + datos.paterno + ' ' + datos.materno + '</td><td><button type="button" name="remove" id="' + i + '" class="btn btn-danger btn_remove">X</button></td></tr>');

            },
            error: function (error, xhr) {
                alert(error + " errorsss " + xhr.status);
                console.log(error);
            }

        });
    });

});

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
                    html += "<option value=\"" + res[i].idPersona + "\">"+res[i].nombres+"&nbsp;"+res[i].paterno +" "+ res[i].materno + "</option>";
                }
                $('#lPersona').html(html);
                alert("Agregado en la lista");
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
 

function checkdate(input){
    var nroComprobante = document.getElementById("nroComprobante").value;
    var nroCheque = document.getElementById("nroCheque").value;
    
    
    var cadena = "cadena de texto",
    ini = 0;
    fin    = 4;
    year = input.substring(ini, fin);
    
    inim = 5;
    finm   = 7;
    month = input.substring(inim, finm);
    
    inid = 8;
    find   = 10;
    day = input.substring(inid, find);

  //   alert(day+"dia"+ month+" mes"+year+" hola"+input);
    
    var unionC= nroComprobante+"-"+nroCheque+"-"+day+""+month+""+year +".pdf";
  //  alert("hi "+unionC);
   document.getElementById('fileName').value = unionC;
}





$(function () {
    $("#tComprobante").combobox();
});

$(function () {
    $("#tPagos").combobox();
});
$(document).ready(function() {
    $('#tComprobantes').DataTable();
} );


