<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<head>
    <link rel="stylesheet" type="text/css"  href=" <c:url value="/resource/admPersonas/personas.css"/>" />
    <script src="<spring:url value="/resource/sac/admComprob/admComprob.js" />" type="text/javascript"></script>
    <script src="<spring:url value="/resource/poais/objetivo/admObjetivo.js" />" type="text/javascript"></script>
    <style type="text/css">
        #portapdf {
            width: 500px;
            height: 400px;
            border: 1px solid #484848;
            margin: 0 auto;
        }
    </style>
    <script>
    
    </script>
</head>

<div class="container">    
    <section class="content-header">
        <div class="row">
            <div class="col-md-10 mb-3">
                <p class="h1"><i class="fa fa-file-archive-o" aria-hidden="true"> </i> Administrar Objetivo de POAI </p>
            </div>
            <div class="col-sm-2 text-right" id="reporte">
                <c:choose>
                    <c:when test="${busqueda eq 'find' }">
                        <form  method="POST" action="${urlN}">
                        	<input type="hidden" name="idObjetivo" value="${poaisObjetivos.idObjetivo}">
                            <span class="icon-input-btn"><i class="fa fa-folder yvela" aria-hidden="true"></i> <input type="submit" class="btn btn-success btn-lg" value="<spring:message  code="income.btn.new"/>"></span>
                        </form>
                    </c:when>
                </c:choose> 
            </div>
        </div>
        <hr align="left" noshade="noshade" size="10" width="100%" />
    </section>

    <section class="content-body">
        <c:if test="${not empty error}">
            <div class="alert alert-danger">
             Hola   <spring:message  code="AbstractUserDetailsAuthenticationProvider.badCredentials"/>
                <br/>
            </div>
        </c:if>
        
            <!--Tabla lista de Todos los Comprobantes Egreso Registrados -->
            <c:choose>
                <c:when test="${busqueda eq 'find' }">
                    <center><br>
                        <p class="h3"><i class="fa fa-folder-open-o" aria-hidden="true"></i> Lista Resultados de POAI</p> 
                        <br>
                    </center>
                    <c:set var="aux" value="0" />
                    <table id="tComprobantes"  class="table" style="width:100%">
                        <thead>
                            <tr>
                                <th>NOMBRE ADMINISTRATIVO</th>
                                <th>CARGO</th>
                                <th>GESTION </th>
                                <th>OPERACIONES </th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:set var="cont" value="0" />
	                        <c:forEach items="${lAdministrativos}" var="pa">
                            <c:set var="cont" value="${cont+1}" />
                            <tr height="40">
                                <td>${pa.personas.nombres} ${pa.personas.paterno} ${pa.personas.materno}</td>
                                <td>${pa.pnlCargos.cargo}</td>
                                <td>${pa.gestion}</td>
                                <td>
                                    <a href="../../../../Castanhero/reporte-poai/personal/${pa.idPnlPersonalAdministrativo}" class="btn btn-success">
	                                		<i class="fa fa-pencil-square-o yvela" aria-hidden="true"></i> Ver POAIs
	                                </a>
                                </td>
                            </tr>
                        </c:forEach>
	                        </tbody>
                        
                    </table>
                    
                    <!-- Button trigger modal -->
					<br>
                    <hr align="left" noshade="noshade" size="10" width="100%" />

                </c:when>
            </c:choose>
    </section>

<!-- Modal Visualizar PDF -->

		
<!-- -------------------- Final Nuevo Objetivo ---------------- -->
</div>
