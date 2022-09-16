$(function () {
	//Modales registrar Tipos Pagos
	    $("#btnSupervisor").click(function (e) {
	        e.preventDefault();
	         //$('input').next().remove();
	        $.post({
	            url: 'guardarSupervisor',
	            data: $('form[name=supervisoreForm').serialize(),
	            success: function (res) {
	                $("#lSupervisor").html("");
	                var html = $("lSupervisor").html();
	                var len = res.length;
	                for (var i = 0; i < len; i++) {
	                	  html += "<option value=\"" + res[i].idSupervisor + "\">" + res[i].nombres + "&nbsp;" + res[i].paterno + " " + res[i].materno + "</option>";
	                 }
	                $('#lSupervisor').html(html);
	
	            }
	        });
	    });
	
	});

$(function() {
    $( "#lSupervisor" ).combobox();
});

$(function() {
    $( "#idPnlCargos" ).combobox();
});