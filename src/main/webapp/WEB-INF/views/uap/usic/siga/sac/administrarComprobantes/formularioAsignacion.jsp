<%-- 
    Document   :formularioAsignacion
    Created on : 14-may-2017, 16:08:41
    Author     : Freddy Morales
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Asignacion Docente </title>
        <link href="<c:url value='/static/pin_style/css/style.css' />" rel="stylesheet"></link>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.3/jquery.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.0/jquery.min.js"></script>
        <script src="static/js/clon.js"></script>


        <script type="text/javascript">
            $(document).ready(function () {
                $(".btnAdd").click(function () {

                    var valores = "";
                    var num = 1;//$('.clonedInput').length; // how many "duplicatable" input fields we currently have
                    var idPersona = 0;
                    var nombres = 0;
                    var boton = 0;// $('.clonedInput').length; // how many "duplicatable" input fields we currently have

                    $(this).parents("tr").find("#idPersona").each(function () {
                        idPersona = $(this).html();
                    });

                    $(this).parents("tr").find("#cont").each(function () {
                        num = $(this).html();
                    });

                    $(this).parents("tr").find("#nombres").each(function () {
                        nombres = $(this).html();
                    });

                    var mensaje = confirm("¿Desea Agregar el Docente " + nombres + "  con ID " + idPersona + " para Asignarle Otra Materia?");
                    //Detectamos si el usuario acepto el mensaje
                    if (mensaje) {

                        var newNum = new Number(num + 1);      // the numeric ID of the new input field being added
                        console.log(newNum);
                        // alert(idPersona);

                        var newNumn = new Number(num + 1);      // the numeric ID of the new input field being added
                        console.log(newNumn);
                        //alert(boton);
                        // create the new element via clone(), and manipulate it's ID using newNum value
                        var newElem = $('#input' + num).clone();

                        // manipulate the name/id values of the input inside the new element
                        $('input', newElem).eq(0).attr('name', 'idPersona2').attr('value', idPersona);
                        $('input', newElem).eq(1).attr('id', 'btnDel').attr('class', 'eliminar').attr('value', 'Delete');
                        $('input', newElem).eq(2).attr('name', 'idPersona2').attr('value', nombres);


                        $('select', newElem).eq(0).attr('name', 'materia' + idPersona);
                        $('select', newElem).eq(1).attr('name', 'tipoCategoria' + idPersona);
                        $('select', newElem).eq(2).attr('name', 'tipoAsignacion' + idPersona);
                        $('select', newElem).eq(3).attr('name', 'tipoDocente' + idPersona);
                        $('select', newElem).eq(4).attr('name', 'responsabilidad' + idPersona);
                        $('input', newElem).eq(1).attr('id', 'btnAdd').attr('class', 'btnAdd').attr('value', 'Agregar');
                        $('input', newElem).eq(2).attr('id', 'btnDel').attr('class', 'eliminar').attr('value', 'Eliminar');


                        // insert the new element after the last "duplicatable" input field
                        $('#input' + num).after(newElem);
                        $('#idPrueba').attr('disabled', 'disabled');
                        // enable the "remove" button
                        $('#btnDel').attr('enabled', '');

                        // business rule: you can only add 5 names
                        if (newNum === 30)
                            $('#btnAdd').attr('disabled', 'disabled');
                        // alert("Agregado!!!");
                    } else {
                        // alert("!Cancelado!");
                    }
                });



                $('.btnDel').click(function () {
                    var num = $('.clonedInput').length; // how many "duplicatable" input fields we currently have
                    $(this).parents("tr").find("#cont").each(function () {
                        num = $(this).html();
                    });
                    alert(num);

                    $('#input' + num).remove();     // remove the last element

                    // enable the "add" button
                    $('#btnAdd').attr('enabled', '');

                    // if only one element remains, disable the "remove" button
                    if (num - 1 === 1)
                        $('#btnDel').attr('disabled', 'disabled');
                });

                $('#btnDel').attr('enabled', 'enabled');

            });


            $(".eliminar").live('click', function () {
                var idPersona = 0;
                var nombres = 0;
                $(this).parents("tr").find("#idPersona").each(function () {
                    idPersona = $(this).html();
                });

                $(this).parents("tr").find("#nombres").each(function () {
                    nombres = $(this).html();
                });

                var mensaje = confirm("¿Esta seguro de Eliminar al Docente " + nombres + "  con ID " + idPersona);
                 
                if (mensaje) {
                    $(this).closest('tr').remove();
                    //alert("Eliminado!!");
                }
            });

            function validar(obj) {
                if (obj.checked == true) {
                      $('#idPersonaDoc').attr('disabled', 'disabled');
                  //  alert("si");
                } else {
                   // alert("no");
                }
            }
        </script>

    

</head>
<body>
    <div class="content box-body">
        <div class="row">      
            <div class="col-md-1 mb-3">
                <section class="content-header"><H1>
                        <form method="POST" name="volver" action="listarCrPlanesMultiple.da">
                            <input type="hidden" name="idCarrera" value="${carrera.idCarrera}" />
                            <input type="hidden" name="idFacultad" value="${facultad.idFacultad}" />
                            <input type="hidden" name="periodo" value="${periodo}" />
                            <input type="hidden" name="gestion" value="${gestion}" />
                            <input type="hidden" name="idCrPlan" value="${idCrPlan}" />
                            <input type="hidden" name="idTipoGrado" value="${idTipoGrado}" />
                            <button type="submit" class="btn btn-success"><i class="glyphicon glyphicon-arrow-left"></i> </button> 
                        </form> 
                    </h1>
                </section>
            </div>
            <div class="col-md-11 mb-3">
                <section class="content-header"><h1><i class="glyphicon glyphicon-pencil"></i>Asignación de Docentes Multiples </h1></section>
            </div>
        </div>
        <section class="content">
            <div class="col-md-12">
                <div class="box box-success">
                    <div class="box-body">


                        <form  id="myForm" method="POST"  action="registrarrAsignacionMultiple.da" class="form-horizontal">
                            <input type="hidden" name="gestion" value="${gestion}" />
                            <input type="hidden" name="periodo" value="${periodo}" />
                            <input type="hidden" name="idTipoEvaluacion" value="${idTipoEvaluacion}" />
                            <input type="hidden" name="idTipoGrado" value="${idTipoGrado}" />
                            <input type="hidden" name="grupo" value="${grupo}" />
                            <input type="hidden" name="idCarrera" value="${carrera.idCarrera}" />
                            <input type="hidden" name="idFacultad" value="${facultad.idFacultad}" />
                            <input type="hidden" name="fecInicio" value="${fecInicio}" />
                            <input type="hidden" name="fecFin" value="${fecFin}" />
                            <input type="hidden" name="nroResolucion" value="${nroResolucion}" />


                            <div class="row">
                                <div class="col-xs-12">
                                    <div class="row">
                                        <div class="col-sm-6 col-xs-12">
                                            <i class="fa fa-university"></i>Facultad
                                        </div>
                                        ${mensaje}
                                        <div class="col-sm-6 col-xs-12">
                                            <small class="pull-right"><strong>${facultad.facultad}</strong></small>
                                        </div>

                                    </div>

                                    <div class="row">
                                        <div class="col-sm-6 col-xs-12">
                                            <i class="glyphicon glyphicon-blackboard"></i> Carrera
                                        </div>
                                        <div class="col-sm-6 col-xs-12">
                                            <small class="pull-right"><strong>${carrera.carrera}</strong></small>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-sm-6 col-xs-12">
                                            <i class="glyphicon glyphicon-list-alt"></i> Plan
                                        </div>
                                        <div class="col-sm-6 col-xs-12">
                                            <small class="pull-right"><strong>${plan.plan}</strong></small>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-6 col-xs-12">
                                            <i class="glyphicon glyphicon-list-alt"></i> Tipo Evaluacion
                                        </div>
                                        <div class="col-sm-6 col-xs-12">
                                            <small class="pull-right"><strong>${tipoEvaluacion.tipoEvaluacion}</strong></small>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-6 col-xs-12">
                                            <i class="glyphicon glyphicon-time"></i> Gestión 
                                        </div>
                                        <div class="col-sm-6 col-xs-12">
                                            <small class="pull-right"><strong>${gestion}</strong></small>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-6 col-xs-12">
                                            <i class="glyphicon glyphicon-list-alt"></i> Periodo 
                                        </div>
                                        <div class="col-sm-6 col-xs-12">
                                            <small class="pull-right"><strong>${periodo}</strong></small>
                                        </div>
                                    </div>
                                </div>

                            </div>
                            <br>
                            <!-- --------Inicio----------Listaaaaaaaaaaaaaaaaa Asignacion Docente Titular y Merito ------------------- -->
                            <br><br>
                            <div class="col-xs-12">

                                <table class="tabla"  width="100%">
                                    <tr><th>Nro</th>
                                        <th>?</th>
                                        <th>ID PERSONA</th>
                                        <th>DOCENTE</th>
                                        <th>ASIGNATURA</th>
                                        <th>TIPO CATEGORIA</th>
                                        <th>TIPO ASIGNACION</th>
                                        <th>TIPO DOCENTE</th>
                                        <th>RESPONSABILIDAD</th>

                                    </tr>
                                    <c:set var="cont" value="0" />
                                    <c:forEach var="ad" items="${requestScope.lAsignaciones}">
                                        <c:set var="cont" value="${cont + 1}" />
                                        <tr style="margin-bottom:4px;">
                                            <td>${cont}</td>
                                            <td><input  type="checkbox" name="idPersona3" value="${ad[0]}" onclick="javascript:validar(this);"></td>
                                            <td>${ad[0]}</td>
                                            <td>${ad[4]} ${ad[2]} ${ad[3]}</td>
                                            <td><input type="hidden" name="idMateria3${ad[0]}" value="${ad[8]}">${ad[6]}</td>

                                            <td>
                                                <select name="idTipoCategoria3${ad[0]}" class="form-control input-sm">
                                                    <c:forEach var="p" items="${requestScope.dcttiposcategorias}">
                                                        <option value="${p.idTipoCategoria}">${p.tipoCategoria}</option>
                                                    </c:forEach>
                                                </select>
                                            </td>
                                            <td>
                                                <select name="idTipoAsignacion3${ad[0]}" class="form-control input-sm"> 
                                                    <c:forEach var="p" items="${requestScope.lAsignacion}">
                                                        <option value="${p.idTipoAsignacion}">${p.tipoAsignacion}</option>
                                                    </c:forEach>
                                                </select>
                                            </td>
                                            <td><input type="hidden" name="idTipoDocente3${ad[0]}" value="${ad[11]}">${ad[7]}</td>       
                                            <td>
                                                <select name="idResponsabilidad3${ad[0]}" class="form-control input-sm"> 
                                                    <c:forEach var="r" items="${requestScope.lresponsabilidad}">
                                                        <option value="${r.idResponsabilidad}">${r.responsabilidad}</option>
                                                    </c:forEach>
                                                </select>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                        <input id="idPersonaDoc" type="hidden" name="idPersona3" value="" />
                                </table>


                                <!-- ------------------Listaaaaaaaaaaaaaaaaa fin asignacion Titular y Merito------------------- -->
                                <br> 
                                <br>
                                <table class="tabla"  width="100%">
                                    <tr><th>Nro</th>
                                        <th>?</th>
                                        <th>ID PERSONA</th>
                                        <th>DOCENTE</th>
                                        <th>ASIGNATURA</th>
                                        <th>TIPO CATEGORIA</th>
                                        <th>TIPO ASIGNACION</th>
                                        <th>TIPO DOCENTE</th>
                                        <th>RESPONSABILIDAD</th>
                                        <th>AGREGAR</th>
                                        <th>ELIMINAR</th>
                                    </tr>
                                    <c:set var="cont" value="0" />
                                    <c:forEach var="doc" items="${requestScope.lDocente}">
                                        <c:set var="cont" value="${cont + 1}" />
                                        <tr id="input${cont}" style="margin-bottom:4px;">
                                            <td id="cont">${cont}</td>
                                            <td><input type="checkbox" name="idPersona1" value="${doc[0]}"></td>
                                            <td id="idPersona">${doc[0]}</td>
                                            <td id="nombres">${doc[4]} ${doc[2]} ${doc[3]}</td>
                                            <td>
                                                <select name="idMateria${doc[0]}" class="form-control input-sm">
                                                    <c:forEach var="mat" items="${requestScope.lMateriasN}">
                                                        <option value="${mat.idMateria}">${mat.materia}</option>
                                                    </c:forEach>
                                                </select>
                                            </td>
                                            <td>
                                                <select name="idTipoCategoria${doc[0]}" class="form-control input-sm">
                                                    <c:forEach var="p" items="${requestScope.dcttiposcategorias}">
                                                        <option value="${p.idTipoCategoria}">${p.tipoCategoria}</option>
                                                    </c:forEach>
                                                </select>
                                            </td>
                                            <td>
                                                <select name="idTipoAsignacion${doc[0]}" class="form-control input-sm"> 
                                                    <c:forEach var="p" items="${requestScope.lAsignacion}">
                                                        <option value="${p.idTipoAsignacion}">${p.tipoAsignacion}</option>
                                                    </c:forEach>
                                                </select>
                                            </td>
                                            <td>
                                                <select name="idTipoDocente${doc[0]}" class="form-control input-sm"> 
                                                    <c:forEach var="r" items="${requestScope.lTiposDocente}">
                                                        <option value="${r.idTipoDocente}">${r.tipoDocente}</option>
                                                    </c:forEach>
                                                </select>
                                            </td>
                                            <td>
                                                <select name="idResponsabilidad${doc[0]}" class="form-control input-sm"> 
                                                    <c:forEach var="r" items="${requestScope.lresponsabilidad}">
                                                        <option value="${r.idResponsabilidad}">${r.responsabilidad}</option>
                                                    </c:forEach>
                                                </select>
                                            </td>

                                            <td> <input type="button" class="btnAdd" value="Agregar" /></th>
                                            <td><input type="button"  value="Eliminar" /></td>
                                        </tr>

                                    </c:forEach>
                                    <input type="hidden" id="idPrueba" name="idPersona2" value="" />

                                </table>
                            </div>
                    </div>
                    <br>
                    <div class="box-footer">
                        <div class="pull-right">
                            <button type="submit" class="btn btn-success"><i class="fa fa-save"></i> Registrar</button> 
                        </div>
                    </div>

                    </form>
                </div>
            </div>

        </section>
    </div>
</body>
</html>
 
