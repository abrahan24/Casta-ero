// Llamada a comobo dinámico 

$(function() {
    $( "#personas" ).combobox();
});

$(function() {
    $( "#proyecto" ).combobox();
});

$(function() {
    $( "#tContrato" ).combobox();
});

$(function() {
    $( "#modalidad" ).combobox();
});

$(function() {
    $( "#partida" ).combobox();
});


$(function() {
    $( "#formularios" ).combobox();
});

$(function() {
    $( "#boletas" ).combobox();
});

function mMontoMod(valor) {
    var total = 1;
    var sAux = 0;
    var personas = parseFloat(document.getElementById('personas').value);
    alert(personas);

}

$(document).ready(function() {
    $('#tGastos').DataTable();
} );

function construirNombrePdf(){
    
   var combo = document.getElementById("personas");
   var persona = combo.options[combo.selectedIndex].text;
   var codigo = document.getElementById("codigoCont").value;
  
   var codigoR = codigo.replace("/", "-");
   var personaR = persona.replace(/ /g, "");
    
   var nombrePdf = codigoR+""+personaR+".pdf";

    document.getElementById('fileName1').value = nombrePdf;
   
}

$(document).ready(function() {
    $('#tContratos').DataTable();
} );
