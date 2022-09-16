
  $(document).ready(function($)
  {
   // trigger event cuando el boton es cliqueado
   $("button").click(function()
   {
    // añadir nueva fila usando la funcion addTableRow
    addTableRow($("table"));
    // prevenir que el boton redireccione a una nueva pagina
    return false;
   });
  
   // funcion que añade una nueva fila a la tabla clonando la ultima fila e 
   // incrementando los nombres y los ids en 1 para hacerlos unicos en el documento
   function addTableRow(table)
   {
    // clonar la ultima fila de la tabla
    var $tr = $(table).find("tbody tr:last").clone();
    // obtener el atributo name para los inputs y selects
 	$tr.find("input:text").val("");
    $tr.find("input,select").attr("name", function()
    {
     //  separar el campo name y su numero en dos partes
     var parts = this.id.match(/(\D+)(\d+)$/);
     // crear un nombre nuevo para el nuevo campo incrementando el numero para los previos campos en 1
     return parts[1] + ++parts[2];
    // repetir los atributos ids
    }).attr("id", function(){
     var parts = this.id.match(/(\D+)(\d+)$/);
     return parts[1] + ++parts[2];
    });
    // añadir la nueva fila a la tabla
    $(table).find("tbody tr:last").after($tr);

};
  });  
  
  //Copia Modal personas comprobantes
  
  $(function () {
    $("#btnPersonas").click(function (e) {
        e.preventDefault();
        //$('input').next().remove();
        $.post({
            url: 'guardarPersonas',
            data: $('form[name=personasForm').serialize(),
            success: function (res) {
                $("#lPersona").html("");
                alert("Yo "+res.lPersonas.nombres);
                var html = $("lPersona").html();
                var len = res.lPersonas.length;
                for (var i = 0; i < len; i++) {
                    html += "<option value=\"" + res.lPersonas[i].idPersona + "\">" + res.lPersonas[i].nombres + +"\">" + res[i].paterno + +"\">" + res[i].materno + "</option>";
                }
                $('#lPersona').html(html);
                alert("aqui");
                $("#cantBenif").val(i) + 1;
                $('#tPersona').append('<tr id="row' + i + '"><td>' + i + '</td><td>' + res.personas.ci + '</td><td><input type="hidden" name="idPersona2' + i + '" value="' + res.idPersona + '" id=inp"' + i + '" />' + res.nombres + ' ' + datos.paterno + ' ' + res.materno + '</td><td><button type="button" name="remove" id="' + i + '" class="btn btn-danger btn_remove">X</button></td></tr>');
            }
        });
    });

});



