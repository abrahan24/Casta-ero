$(document).ready(function() {
	var i = 0;

	var aux = 0;
	$("#btnResultado").click(function(e) {
		var resultado = document.getElementById("txtResultado").value;
		var indicador = document.getElementById("txtIndicador").value;
		var ponderacion = document.getElementById("txtPonderacion").value;

		aux = aux + parseInt(ponderacion);

		if (aux <= 70) {
			i++;
			$('#tResultado').append('<tr id="row' + i + '"><td>' + i + '</td><td>' + resultado + '</td> <td>' + indicador + '</td> <td>' + ponderacion + ' </td><input type="hidden" name="objetivoResultado" value="' + resultado + '" id=inp"' + i + '" /> <input type="hidden" name="objetivoIndicador" value="' + indicador + '" id=inp"' + i + '" /> <input type="hidden" name="objetivoPonderacion" value="' + ponderacion + '" id=inp"' + i + '" /> <td><button type="button" name="remove" id="' + i + '" class="btn btn-danger" onclick="deleteRow(this)">X</button></td></tr>');

			document.getElementById("txtResultado").value = "";
			document.getElementById("txtIndicador").value = "";
			document.getElementById("txtPonderacion").value = "";

			document.getElementById("tPonderacion").innerHTML = "Total Ponderacion: " + aux;

		} else {
			aux = aux - parseInt(ponderacion);
			alert("El Total de Ponderacion acumulado no debe ser mas de 70");
		}
	});
});

$(document).ready(function() {
	var i = 0;

	var aux = 0;
	$("#btnResultadoContinuo").click(function(e) {
		var resultado = document.getElementById("txtResultado").value;
		var ponderacion = document.getElementById("txtPonderacion").value;
		var cantidad = parseInt(document.getElementById("aux").value);

		aux = aux + parseInt(ponderacion);

		if (cantidad == 0) {
			if (aux <= 100) {
				i++;
				$('#tResultado').append('<tr id="row' + i + '"><td>' + i + '</td><td>' + resultado + '</td> <td>' + ponderacion + ' </td><input type="hidden" name="objetivoResultado" value="' + resultado + '" id=inp"' + i + '" />  <input type="hidden" name="objetivoPonderacion" value="' + ponderacion + '" id=inp"' + i + '" /> <td><button type="button" name="remove" id="' + i + '" class="btn btn-danger" onclick="deleteRow(this)">X</button></td></tr>');

				document.getElementById("txtResultado").value = "";
				document.getElementById("txtPonderacion").value = "";

				document.getElementById("tPonderacion").innerHTML = "Total Ponderacion: " + aux;
			} else {
				aux = aux - parseInt(ponderacion);
				alert("El Total de Ponderacion acumulado no debe ser mas de 100");
			}
		}

		if (cantidad > 0) {
			if (aux <= 30) {
				i++;
				$('#tResultado').append('<tr id="row' + i + '"><td>' + i + '</td><td>' + resultado + '</td> <td>' + ponderacion + ' </td><input type="hidden" name="objetivoResultado" value="' + resultado + '" id=inp"' + i + '" />  <input type="hidden" name="objetivoPonderacion" value="' + ponderacion + '" id=inp"' + i + '" /> <td><button type="button" name="remove" id="' + i + '" class="btn btn-danger" onclick="deleteRow(this)">X</button></td></tr>');

				document.getElementById("txtResultado").value = "";
				document.getElementById("txtPonderacion").value = "";

				document.getElementById("tPonderacion").innerHTML = "Total Ponderacion: " + aux;
			} else {
				aux = aux - parseInt(ponderacion);
				alert("El Total de Ponderacion acumulado no debe ser mas de 30");
			}
		}

	});
});


$(function() {
	$("#lSupervisor").combobox();
});

$(function() {
	$("#idPnlCargos").combobox();
});
