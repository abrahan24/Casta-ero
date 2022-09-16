$(document).ready(function () {
     document.getElementById("costoM").value = "";
  });
 
$(document).ready(function () {
     
    $("#btnCjaProveedores").click(function (e) {
        //Prevent default submission of form
        alert("algo aqui");
        e.preventDefault();
        //Remove all errors
        //$('input').next().remove();
        $.post({
            url: 'guardarCjaProveedores',
            data: $('form[name=cjaProveedoresForm').serialize(),
            success: function (res) {
                $("#sProveedores").html("");
                var html = $("sProveedores").html();
                var len = res.length;
                for (var i = 0; i < len; i++) {
                    html += "<option value=\"" + res[i].idCjaProveedor + "\">" + res[i].proveedor + "</option>";
                }
                $('#sProveedores').html(html);

            }
        });
    });

    $("#btnCjaTiposGastos").click(function (e) {
        //Prevent default submission of form
        e.preventDefault();
        //Remove all errors
        //$('input').next().remove();
        $.post({
            url: 'guardarCjaTiposGastos',
            data: $('form[name=cjaTiposGastosForm').serialize(),
            success: function (res) {
                $("#gastos").html("");
                var html = $("gastos").html();
                var len = res.length;
                for (var i = 0; i < len; i++) {
                    html += "<option value=\"" + res[i].idCjaTipoGasto + "\">" + res[i].nroTipoGasto +"-" + res[i].tipoGasto + "</option>";
                }
                $('#gastos').html(html);

            }
        });
    });

});

// =========== Registro Retencion
function retenImp(ret) {
  var rete = parseFloat(document.getElementById('costoM').value);
  rete = rete * 0.13;
  document.getElementById('reten').value = rete;
  document.getElementById('factura').value = 0;
}
function noRetenImp(ret) {
  document.getElementById('reten').value = 0;
  document.getElementById('factura').value = 0;
}

// =========== Modificar Retencion
function retenImpM(ret) {
  var rete = parseFloat(document.getElementById('mCosto').value);
  rete = rete * 0.13;
  document.getElementById('retenM').value = rete;
  document.getElementById('facturaM').value = 0;
}
function noRetenImpM(ret) {
  var fact = parseFloat(document.getElementById('fOriginal').value);
  document.getElementById('retenM').value = 0;
  document.getElementById('facturaM').value = fact;
}

// =============== calcular total por monto registro
function montoM(costo) {
    costo = parseFloat(costo);
    var saldo = parseFloat(document.getElementById('saldoOriginal').value);
    var porcentajeSaldo = parseFloat(document.getElementById('porcentajeSaldo').value);
    var porcentajeGasto = parseFloat(document.getElementById('porcentajeGasto').value);
    var montoGastar = 0;
     
   if ((costo > porcentajeGasto) || (costo > porcentajeGasto)) {
        alert("Aviso: El monto ingresado es superior al permitdo ");
         document.getElementById('costoM').value = "";
     }
    
    montoGastar = parseFloat(saldo - porcentajeSaldo);
    if ((costo > montoGastar) || (costo > montoGastar)) {
        alert("Aviso: El monto ingresado es superior al saldo permitdo ");
        document.getElementById('costoM').value = "";
     }    
    else{
        document.getElementById('saldoPrincipal').value = (saldo - costo);
        $("#thing td.name").html(saldo - costo);
     }  
}
// =========== fin calcular  monto 

 // ============ Calcular total por monto - modificar
function mMontoMod(valor) {
    var total = 1;
    var sAux = 0;
    var porcentajeSaldo = parseFloat(document.getElementById('pGastoModificar').value);
    var saldo = parseFloat(document.getElementById('saldoOrigin').value);
    var mTotal = parseFloat(document.getElementById('mTotalModificad').value);
    var mCostoAux = parseFloat(document.getElementById('costoOriginal').value);
    var porcentajeGasto = parseFloat(document.getElementById('pGastoModificar').value);
 
    costo = parseFloat(valor);
    //total = (parseFloat(cantidad) * parseFloat(costo));
        
    if (costo > mTotal) {
       //alert(costo+" fff "+mTotal);
        var Taux = costo - mTotal;
        var sAux = saldo - Taux;
       if ((sAux >= porcentajeSaldo) && (costo <= porcentajeGasto)) {
            $("#thing td.name").html(sAux);
            document.getElementById('saldoModificar').value = sAux;
           } else {
            alert("El monto ingresado es mayor al porcentaje saldo permitido: Revise los montos");
             document.getElementById('mCosto').value = mCostoAux;
          }
     }

    if (costo < mTotal) {
        var Taux = mTotal - costo;
        var sAux = saldo + Taux;
         if ((sAux >= porcentajeSaldo) && (costo <= porcentajeGasto)) {
            $("#thing td.name").html(sAux);
            document.getElementById('saldoModificar').value = sAux;
         } else {
            alert("El monto ingresado es mayor al porcentaje saldo permitido: Revise los montos");
         }
    }

    if (costo == mTotal) {
       $("#thing td.name").html(saldo);
       document.getElementById('saldoModificar').value = saldo;
    }

    if (costo == 0 ) {
        $("#thing td.name").html(saldo);
    }
   
 }
// ============= fin monto - modificar
 
 
// ================= fin cantidad - modificar

// Llamada a comobo dinámico 

$(function() {
    $( "#personaS" ).combobox();
});

$(function() {
    $( "#sProveedores" ).combobox();
});

$(document).ready(function() {
    $('#tGastos').DataTable();
} );

$(function() {
    $( "#gastos" ).combobox();
});




