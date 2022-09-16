$(document).ready(function(){
    $('#vistosResolucion').summernote({
        height: 200,
    });

    $('#considerandosResolucion').summernote({
        height: 200,
    });

    $('#porlotantoResolucion').summernote({
        height: 200,
    });

    $('#resuelveResolucion').summernote({
        height: 200,
    });

    $('#esdadoResolucion').summernote({
        height: 200,
    });

    $('span.note-icon-caret').remove();

    $('.note-editable').css('background', '#fff');
});



$(function() {
    $( "#idAautoridad" ).combobox();
});

$(function() {
    $( "#idPersona" ).combobox();
});

$(function() {
    $( "#idUsuario" ).combobox();
});

function construirNombrePdf(){
       var nroRes = document.getElementById("nroRes").value;
	   var nFolio = document.getElementById("nFolio").value;
	   var sigla = document.getElementById("sigla").value;
	   var nombrePdf = "Resolución de "+sigla+" -"+nroRes+"-"+nFolio+".pdf";
	    document.getElementById('fileName1').value = nombrePdf;   
	}

function construirNombrePdModf(){
    var nroRes = document.getElementById("nroRes2").value;
	   var nFolio = document.getElementById("nFolio2").value;
	   var sigla = document.getElementById("sigla2").value;
	   var nombrePdf = "Resolución de "+sigla+" -"+nroRes+"-"+nFolio+".pdf";
	    document.getElementById('fileName2').value = nombrePdf;   
	}

function construirNombrePdfCnv(){
      var nroRes = document.getElementById("nroCnv").value;
	   var nFolio = document.getElementById("nFolio").value;
	   var nombrePdf = "Convenio -"+nroCnv+"-"+nFolio+".pdf";
	    document.getElementById('fileName1').value = nombrePdf;   
	}

function construirNombrePdfMCnv(){
    var nroRes = document.getElementById("nroCnv2").value;
	   var nFolio = document.getElementById("nFolio2").value;
	   var nombrePdf = "Convenio -"+nroCnv+"-"+nFolio+".pdf";
	    document.getElementById('fileName2').value = nombrePdf;   
	}
  