<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<head>
    <link rel="stylesheet" type="text/css"  href=" <c:url value="/resource/admPersonas/personas.css"/>" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

</head>
<section class="higher">
    <div class="row">
        <div class="col-md-10 mb-3">
            <section class="content-header">
                <p class="h1">  <spring:message code="cargo.registrarCargo"/> </p>
            </section>
        </div>

        <div class="form-group row">
            <div class="col-sm-2" id="reporte">
                <c:choose>
                    <c:when test="${busqueda eq 'find' }">
                        <form  method="POST" action="${urlNuevoC}">
                            <span class="icon-input-btn"><i class="fa fa-user-plus yvela" aria-hidden="true"></i> <input type="submit" class="btn btn-success btn-lg" value="<spring:message  code="income.btn.new"/>"></span>
                        </form>
                    </c:when>
                </c:choose> 
            </div>
        </div>
    </div>

    <hr align="left" noshade="noshade" size="10" width="100%" />
    <c:if test="${not empty error}">
        <div class="alert alert-danger">
            <spring:message  code="AbstractUserDetailsAuthenticationProvider.badCredentials"/>
            <br/>
        </div>
    </c:if>
    <!-- ------------------------Inicio Registro Cargo----------------------------------------->

    <c:choose>
        <c:when test="${operation eq 'reg' }">
            <center><br>
                <p class="h3"><i class="fa fa-newspaper-o" aria-hidden="true"> </i> <spring:message  code="cargo.registrarCargo"/></p> 
                <br>
            </center>

            <form:form modelAttribute="pnlcargos" action="${regCargos}" method="post">
                <div class="form-group row">

                    <label for="cargo" class="col-sm-2 col-form-label"><spring:message  code="cargo.cargo" /></label>
                    <div class="col-sm-6">
                        <spring:message  code="cargo.cargo.msg" var="car" />
                        <form:input type="text" path="cargo"  class="form-control form-control-lg"  placeholder="${car}"/> <form:errors path="cargo" cssClass="alert alert-danger"/>
                    </div>
                    <div class="col-sm-4" id="comd">
                        <small class="form-text text-muted">
                            <spring:message  code="cargo.cargo.msg" />
                        </small>
                        <small id="emailHp" class="form-text text-muted formFieldError">

                        </small>
                    </div>
                </div>
                <hr align="left" noshade="noshade" size="10" width="100%" />

                <div class="form-group row mt-12">
                    <div class="col-sm-3" id="combo1">
                    </div> 
                    <div class="col-sm-3" id="combo1">
                        <span class="icon-input-btn"><i class="fa fa-pencil-square-o yvela" aria-hidden="true"></i> <input class="btn btn-success" type="submit" value="<spring:message  code="institution.register"/>"></span>
                    </div>
                    <div class="col-sm-3" id="combo1"> 
                    </div>
                    <div class="col-sm-3" id="combo1">
                        <a href="<spring:url value="/cargos/mostrarFormCargos"/>" class="btn btn-success yvela"><i class="fa fa-undo yvela" aria-hidden="true"></i>  <spring:message  code="expenses.btn.cancel"/></a>
                    </div> 
                </div>
            </form:form>
        </c:when>
    </c:choose>
    <br>
    <!---------------Fin Registro nuevo cargo------------- -->

    <!-- ------------------------Inicio Modificacion Cargo----------------------------------------->
    <c:choose>
        <c:when test="${operation eq 'mod' }">
            <center><br>
                <p class="h3"><i class="fa fa-newspaper-o" aria-hidden="true"> </i> <spring:message  code="position.modify.title"/></p> 
                <br>
            </center>
            <spring:url var="formUrl" value="modify" />
            <form:form  modelAttribute="pnlcargos" action="${urlConfC}" method="post">
               
                <form:hidden path="idPnlCargos" value="${pnlcargos.idPnlCargos}" />

                <div class="form-group row">
                    <label for="cargo" class="col-sm-2 col-form-label"><spring:message  code="cargo.cargo" /></label>
                    <div class="col-sm-6">
                        <spring:message  code="cargo.cargo.msg" var="car" />
                        <form:input type="text" path="cargo"  class="form-control form-control-lg" placeholder="${car}" /> <form:errors path="cargo" cssClass="alert alert-danger"/>
                    </div>
                    <div class="col-sm-4" id="comd">
                        <small class="form-text text-muted">
                            <spring:message  code="cargo.cargo.msg" />
                        </small>
                        <small id="emailHp" class="form-text text-muted formFieldError">

                        </small>
                    </div>
                </div>
                <hr align="left" noshade="noshade" size="10" width="100%" />

                <div class="form-group row mt-12">
                    <div class="col-sm-3" id="combo1">
                    </div> 
                    <div class="col-sm-3" id="combo1">
                        <span class="icon-input-btn"><i class="fa fa-pencil-square-o yvela" aria-hidden="true"></i> <input class="btn btn-success" type="submit" value="<spring:message  code="institution.register"/>"></span>
                    </div>
                    <div class="col-sm-3" id="combo1"> 
                    </div>
                    <div class="col-sm-3" id="combo1">
                        <a href="<spring:url value="/cargos/mostrarFormCargos"/>" class="btn btn-success yvela"><i class="fa fa-undo yvela" aria-hidden="true"></i>  <spring:message  code="expenses.btn.cancel"/></a>
                    </div> 
                </div>
            </form:form>
        </c:when>
    </c:choose>
    <br>
    <!---------------Fin Modificacion  cargo------------- -->


    <!-- Listar Cargos Registrados -->
    <c:choose>
        <c:when test="${busqueda eq 'find' }">
            <center><br>
                <p class="h3"><i class="fa fa-list-alt" aria-hidden="true"></i> <spring:message  code="position.list.title"/></p> 
                <br>
            </center>
            <table width="100%" id="myTable">
                <tr class="header">
                    <th align="center">Nro</th>
                    <th align="center">Id</th>
                    <th align="center"><spring:message  code="cargo.cargo"/></th>
                    <th> <spring:message  code="expenses.modify"/> </th>
                    <th><spring:message  code="expenses.remove"/></th>
                </tr>
                <c:set var="cont" value="0" />
                <c:forEach items="${lPnlCargos}" var="c">
                    <c:set var="cont" value="${cont+1}" />
                    <tr height="40">
                        <td><center>${cont}</td>
                        <td>${c.idPnlCargos}</td>
                        <td>${c.cargo}</td>
                        <td>
                        <center>
                            <form  method="post" action="${urlModC}">
                                <input type="hidden" name="idPnlCargos" value="${c.idPnlCargos}">
                                <span class="icon-input-btn"> <i class="fa fa-pencil-square-o yvela" aria-hidden="true"> </i> <input class="btn btn-success" type="submit"  value="<spring:message  code="expenses.modify"/>" > </span>
                            </form>
                        </center>
                        </td>
                        <td>
                        <center>
                            <form name="Delet" action="${urlIniE}" method="post">
                                <input type="hidden" name="idPnlCargos" value="${c.idPnlCargos}">
                                <span class="icon-input-btn"> <i class="fa fa-times yvela" aria-hidden="true"> </i> <input class="btn btn-danger" type="submit"  value="<spring:message  code="expenses.remove"/>"> </span>
                            </form>
                        </center>
                        </td>
                        </tr>
                    </c:forEach>
            </table>
            <br>
            <hr align="left" noshade="noshade" size="10" width="100%" />
            <br>
        </c:when>
    </c:choose>

</section>