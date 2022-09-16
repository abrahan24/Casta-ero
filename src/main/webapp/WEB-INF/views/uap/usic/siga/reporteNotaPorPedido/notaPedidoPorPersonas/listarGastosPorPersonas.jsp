<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<head>
    <link rel="stylesheet" type="text/css"  href=" <c:url value="/resource/admReportes/reportes.css"/>" />
    <script src="<spring:url value="/resource/admReportes/reportes.js" />" type="text/javascript"></script>
    <script type="text/javascript">
    </script>
</head>


<div class="container">
    <section class="content-header">

        <div class="row">
            <div c class="col-md-02 mb-3">
                <p><a  class="btn btn-primary" href="formNotaPedidoPorPersona"   action="${cjaIng}" id="show"><i class="fa fa-arrow-left" aria-hidden="true"></i> Volver</a></p>
            </div>
            <div class="col-md-10 mb-3">
                <p class="h1"> <i class="fa fa-sticky-note" aria-hidden="true"></i>  <spring:message code="report.noteOrder.people.title"/> </p>
            </div>

        </div>
        <hr align="left" noshade="noshade" size="10" width="100%" />
    </section>
    <section> 
        <c:if test="${not empty error}">
            <div class="alert alert-danger">
                <spring:message  code="AbstractUserDetailsAuthenticationProvider.badCredentials"/>
                <br/>
            </div>
        </c:if>
        <br>
        <center>
            <p class="h3"><i class="fa fa-list-alt" aria-hidden="true"></i>  <spring:message  code="reporte.noteOrder.people.detail"/></p>
        </center>
        <br>
        <table id="tGastosReporte"  class="table" style="width:100%">
            <thead>
                <tr class="trIngreso">
                    <th>Nro</th>
                    <th><spring:message code="persona.nombres"/></th>
                    <th><spring:message  code="persona.paterno"/></th>
                    <th><spring:message  code="persona.materno"/></th>
                    <th width="100"><center><spring:message  code="expenses.btn.print"/></center></th>   
            </tr>
            </thead>
            <tbody>
                <c:set var="cont" value="0" />
                <c:forEach items="${lGastosP}" var="p">
                    <c:set var="cont" value="${cont+1}" />
                    <tr  id="thing">
                        <td>${cont}</td>
                        <td>${p.nombres}</td>
                        <td>${p.paterno}</td>
                        <td>${p.materno}</td>
                        <td><center>
                    <form action="${imprimirNPP}" method="post"  target="blank">
                        <input type="hidden" name="idPersona" value="${p.idPersona}">
                        <input type="hidden" name="idCjaIngreso" value="${idCjaIngreso}">
                        <button type="submit" class="btn btn-success"><i class="fa fa-print" aria-hidden="true"></i> <spring:message  code="expenses.btn.print"/> </button> 
                    </form>
                </center>
                </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <br>
    </section>
</div>