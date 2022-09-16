$(document).ready(function () {
   
   $("#hide").on('click', function () {
       $("#element").hide();
          return false;
    });

  $("#show").on('click', function () {
     $("#element").show();
        return false;
     });
 });
 
function ojo (valor) {
    alert("Holaa hi");
}

 ///<!===============Script de Actualizar saldo==============  -->
 function mSumar (valor) {

  //  var total = 1;    
    valor = parseFloat(valor);
    var montoO = parseFloat(document.getElementById('montoOriginal').value);
    var saldoO = parseFloat(document.getElementById('saldoOriginal').value);
    
  //  var monto = parseFloat(document.getElementById('monto').value);
   // var saldo = parseFloat(document.getElementById('saldo').value);
    
  
   //  alert(valor+" algoO "+montoO);
     if(valor > montoO){
        var mAux = valor - montoO;
        var sAux = saldoO + mAux;  
      //  alert("suma "+sAux);
        $("#thing td.monto").html(valor);
         $("#thing td.saldo").html(sAux);
        document.getElementById('saldo').value = sAux;
    }
    if(valor < montoO){
        var mAux = montoO - valor;
        var sAux = saldoO - mAux;        
      //  alert("resta "+sAux);
        $("#thing td.saldo").html(sAux);
        $("#thing td.monto").html(valor);
        document.getElementById('saldo').value = sAux;
    }
 }
 
 function maximo (valor) {
    valor = parseFloat(valor);
    var montoMax = parseFloat(document.getElementById('montoMax').value);
     
       if(valor > 2000){     
        alert(valor+" El valor maximo de ingreso de fondo es 2000 "+montoMax);
        valor=0;
    }
 }
 
 
 $(document).ready(function() {
    $('#tIngresos').DataTable();
} );

$(document).ready(function() {
     $("#btnTipoIngreso").click(function(e) {
 		//Prevent default submission of form
		e.preventDefault();
 		//Remove all errors
		//$('input').next().remove();
		$.post({
			url : 'guardarTipoIngreso',
			data : $('form[name=tipoIngresoForm').serialize(),
			success : function(res) {
                                 $("#tipoIngreso").html("");
                                var html = $("tipoIngreso").html();
                                var len = res.length;
                                for(var i=0; i<len; i++){
                                    html += "<option value=\""  + res[i].idCjaTipoIngreso +"\">"+res[i].tipoIngreso+"</option>";
                                }
                               $('#tipoIngreso').html(html);

 			}
		});
	});

 } );
 /*
 // ========== Validar Registro Ingresos
 function porcentaje(valor) {
    valor = parseFloat(valor);
    montoX = parseFloat($("#montoX").val());
    //alert("Hola yo soy"+montoX);
     if(valor <= montoX){
       $("#montoMax").val(valor);
       $("#saldo").val(valor);
       $("#porcGasto").val((valor*10)/100);
       $("#porcSaldo").val((valor*10)/100);
    }else{
        alert("He aqui yo soy");
    }
   }
 */
 // ============  Validar Campo 
 
 function SetFloatEntry(fieldName) {
    $("#" + fieldName).keydown(function (event) {

        return NumericField($("#" + fieldName).val(), event, true);

    });
}   


/* Auxiliar */
var strUserAgent = navigator.userAgent.toLowerCase();
var isIE = strUserAgent.indexOf('msie') > -1;

var reKeyboardChars = /[\x03\x08\x09\x0D\x16\x18\x1A\x2E\x23\x24\x25\x26\x27\x28\x2D]/;
var reNumber = /^((\d{1,3}\.)*\d{3}|\d*,{0,1}\d+|(\d{1,3}\.)*\d{3},{0,1}\d+|\d*)$/;

function NumericField(str, objEvent, isFloat) {

    oldValue = str;
    strKey = GetChar(objEvent);

    if (((objEvent.which) ? objEvent.which : event.keyCode) == 13
        || (((objEvent.which) ? objEvent.which : event.keyCode) == 190 && isFloat))
        return true;

    if (!KeyNumber(objEvent) 
            && !reKeyboardChars.test(strKey)
            && !(objEvent.ctrlKey 
                && reClipboard.test(strKey))) 
        return false;

    return true;
}

function KeyNumber(objEvent) {
    return reNumber.test(GetChar(objEvent));
}

function GetChar(objEvent) {
    var arrKeys = new Array();
    arrKeys[96] = '0';
    arrKeys[97] = '1';
    arrKeys[98] = '2';
    arrKeys[99] = '3';
    arrKeys[100] = '4';
    arrKeys[101] = '5';
    arrKeys[102] = '6';
    arrKeys[103] = '7';
    arrKeys[104] = '8';
    arrKeys[105] = '9';

    arrKeys[111] = '/';
    arrKeys[193] = '/';

    iKeyCode = GetKeyCode(objEvent);

    if (arrKeys[iKeyCode] != null)
        return arrKeys[iKeyCode];

    return String.fromCharCode(iKeyCode);
}

function GetKeyCode(objEvent) {
    if (isIE)
        return objEvent.keyCode;
    return objEvent.which;
}

$(document).ready(function () {
    SetFloatEntry("montoReg");
});

function validar(obj) {
  txt = obj.value;
  if(parseInt(txt) != parseFloat(txt)) {
    alert('Sólo números enteros');
    obj.focus();
  }
}

// ============ valida n
 function Solo_Numerico(variable){
        Numer=parseInt(variable);
        if (isNaN(Numer)){
            return "";
        }
        return Numer;
    }
    function ValNumero(Control){
        Control.value=Solo_Numerico(Control.value);
    }

function max_porcentaje(valor) {
    valor = parseFloat(valor);
    montoX = parseFloat($("#montoX").val());
    //alert(valor+" Hola yo soy"+montoX);
     if(valor <= montoX){
        $("#montoMax").val(valor);
       $("#saldo").val(valor);
       $("#porcGasto").val((valor*10)/100);
       $("#porcSaldo").val((valor*10)/100);
       return valor;
    }else{
         alert("Avico: El mono no puede superar de "+montoX);
         return "";
    }
   }
  function valMaximo(monto){
        monto.value=max_porcentaje(monto.value);
    }

$(function() {
    $( "#tipoIngreso" ).combobox();
});

function controlarMonto() {
  var saldo = parseFloat($( "#saldoReg" ).val());
  var montoMax = parseFloat($( "#montoMax" ).val());
  var monto = parseFloat($( "#montoReg" ).val());
  if ((monto+saldo) > montoMax) {
      alert("Aviso: El monto ingresdo más el saldo, supera lo permitido");
      $("#montoReg").val(montoMax-saldo);
    }
  }
    function controlarMontoMod() {
  var saldo = parseFloat($( "#saldoReg" ).val());
  var montoMax = parseFloat($( "#montoMax" ).val());
  var monto = parseFloat($( "#montoMod" ).val());
  if ((monto+saldo) > montoMax) {
      alert("Aviso: El monto ingresdo más el saldo, supera lo permitido");
      //$("#montoReg").val(montoMax-saldo);
  }

 }