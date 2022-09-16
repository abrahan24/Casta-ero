
//Modales registrar Personas
$(function () {
    $("#btnPersonas").click(function (e) {
        e.preventDefault();
        //alert("Yes " + res.personas.paterno);
        //$('input').next().remove();
        $.post({
            url: 'guardarPersonasPrestamos',
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
                alert("Agregado en la lista");
            }
        });
    });

});


$(function () {
    $("#lPersona").combobox();
});

$(document).ready(function () {
    $('#tPrestamos').DataTable();
});






