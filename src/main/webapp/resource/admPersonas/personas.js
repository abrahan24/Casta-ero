$(document).ready(function(){
	/*  Submit form using Ajax */
    $("#btnPais").click(function(e) {
 		//Prevent default submission of form
		e.preventDefault();
 		//Remove all errors
		//$('input').next().remove();
		$.post({
			url : 'guardarPais',
			data : $('form[name=paisForm').serialize(),
			success : function(res) {
                                 $("#pais").html("");
                                var html = $("pais").html();
                                var len = res.length;
                                for(var i=0; i<len; i++){
                                    html += "<option value=\""  + res[i].idPais +"\">"+res[i].pais+"</option>";
                                }
                               $('#pais').html(html);

 			}
		});
	});

// === cargar tipo sexo
   $("#btnSexo").click(function(e) {
 		e.preventDefault();
 		//$('input').next().remove();
		$.post({
			url : 'guardarTipoSexo',
			data : $('form[name=tipoSexoForm').serialize(),
			success : function(res) {
                                 $("#tipoSexo").html("");
                                var html = $("tipoSexo").html();
                                var len = res.length;
                                for(var i=0; i<len; i++){
                                    html += "<option value=\""  + res[i].idPrsTipoSexo +"\">"+res[i].prsTipoSexo+"</option>";
                                }
                               $('#tipoSexo').html(html);

 			}
		});
	});


// === Cargar Expedidos Ci
 $("#btnExpedido").click(function cargarExpedido(e) {
 		e.preventDefault();
 		//$('input').next().remove();
		$.post({
			url : 'guardarCiExpedido',
			data : $('form[name=prsCiExpedidos').serialize(),
			success : function(res) {
                                 $("#ciExpedido").html("");
                                var html = $("ciExpedido").html();
                                var len = res.length;
                                for(var i=0; i<len; i++){
                                    html += "<option value=\""  + res[i].idPrsCiExpedido +"\">"+res[i].prsCiExpedido+"</option>";
                                }
                               $('#ciExpedido').html(html);
                                
 

   			}
		});
	});
});

function nameFunction() {
  // Declare variables
  var input, filter, table, tr, td, i, txtValue;
  input = document.getElementById("name");
  filter = input.value.toUpperCase();
  table = document.getElementById("myTable");
  tr = table.getElementsByTagName("tr");

  // Loop through all table rows, and hide those who don't match the search query
  for (i = 0; i < tr.length; i++) {
    td = tr[i].getElementsByTagName("td")[2];
    if (td) {
      txtValue = td.textContent || td.innerText;
      if (txtValue.toUpperCase().indexOf(filter) > -1) {
        tr[i].style.display = "";
      } else {
        tr[i].style.display = "none";
      }
    }
  }
}

function paternalFunction() {
  // Declare variables
  var input, filter, table, tr, td, i, txtValue;
  input = document.getElementById("paternal");
  filter = input.value.toUpperCase();
  table = document.getElementById("myTable");
  tr = table.getElementsByTagName("tr");

  // Loop through all table rows, and hide those who don't match the search query
  for (i = 0; i < tr.length; i++) {
    td = tr[i].getElementsByTagName("td")[3];
    if (td) {
      txtValue = td.textContent || td.innerText;
      if (txtValue.toUpperCase().indexOf(filter) > -1) {
        tr[i].style.display = "";
      } else {
        tr[i].style.display = "none";
      }
    }
  }
}

function maternalFunction() {
  // Declare variables
  var input, filter, table, tr, td, i, txtValue;
  input = document.getElementById("maternal");
  filter = input.value.toUpperCase();
  table = document.getElementById("myTable");
  tr = table.getElementsByTagName("tr");

  // Loop through all table rows, and hide those who don't match the search query
  for (i = 0; i < tr.length; i++) {
    td = tr[i].getElementsByTagName("td")[4];
    if (td) {
      txtValue = td.textContent || td.innerText;
      if (txtValue.toUpperCase().indexOf(filter) > -1) {
        tr[i].style.display = "";
      } else {
        tr[i].style.display = "none";
      }
    }
  }
}

function ciFunction() {
  // Declare variables
  var input, filter, table, tr, td, i, txtValue;
  input = document.getElementById("ci");
  filter = input.value.toUpperCase();
  table = document.getElementById("myTable");
  tr = table.getElementsByTagName("tr");

  // Loop through all table rows, and hide those who don't match the search query
  for (i = 0; i < tr.length; i++) {
    td = tr[i].getElementsByTagName("td")[1];
    if (td) {
      txtValue = td.textContent || td.innerText;
      if (txtValue.toUpperCase().indexOf(filter) > -1) {
        tr[i].style.display = "";
      } else {
        tr[i].style.display = "none";
      }
    }
  }
 
}

$(document).ready( function () {
 	 var table = $('#employeesTable').DataTable({
			"sAjaxSource": "listarPersonasDT",
			"sAjaxDataProp": "",
			"order": [[ 0, "asc" ]],
			"aoColumns": [
			      { "mData": "idPersona"},
		              { "mData": "nombres" },
			      { "mData": "paterno" },
			      { "mData": "materno" },
			      { "mData": "pais" },
			      { "mData": "telefono" },
                              { "mData": null,
                                    targets:0,
                                    render: function ( data, type, row, meta ) {
                                        if(type === 'display'){
                                            data = '<a href="inicioModificarPersonas/' + row.idPersona + '" class="btn btn-yvela-green" /><i class="fa fa-pencil-square-o icono" aria-hidden="true"></i>  Edit<spring:message  code="expenses.modify"/></a>';
                                        }
                                        return data;
                                    }
                                },
                              { "mData": null,
                                    targets:1,
                                    render: function ( data, type, row, meta ) {
                                        if(type === 'display'){
                                            data = '<a href="inicioEliminarPersonas/' + row.idPersona + '" class="btn btn-yvela-red"><i class="fa fa-times icono" aria-hidden="true"></i>  Delet<spring:message  code="expenses.modify"/></a>';
                                        }
                                        return data;
                                    }
                                }
			]
	 })
});
 
function editar() {
	$('#bEditar').click(function(idPersona) {
            alert("algo");
		window.location.replace("inicioModificarPersonas"+this.value);
	});
}


  
$(function() {
    $( "#ciExpedido" ).combobox();
});

$(function() {
    $( "#ciExpedido" ).combobox();
});

$(function() {
    $( "#tipoSexo" ).combobox();
});

$(function() {
    $( "#pais" ).combobox();
});
  
 $(document).ready(function() {
    $('#tPersonas').DataTable();
} );

