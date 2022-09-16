<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<head>
    <link rel="stylesheet" type="text/css"  href=" <c:url value="/resource/admPersonas/personas.css"/>" />
    <script src="<spring:url value="/resource/admPersonas/personas.js" />" type="text/javascript"></script>
    <style>
    </style>
</head>

<div class="container">
    <section class="content-header">
        <div class="row">
            <div class="col-md-10 mb-3">
                <p class="h1"><i class="fa fa-users" aria-hidden="true"></i>   <spring:message  code="manage.user.title"/></p>
            </div>
            <div class="col-sm-2 text-right" id="reporte">
                <c:choose>
                    <c:when test="${busqueda eq 'find' }">
                        <form  method="POST" action="${urlNuevoU}">
                            <span class="icon-input-btn"><i class="fa fa-user-plus yvela" aria-hidden="true"></i> <input type="submit" class="btn btn-success btn-lg" value="<spring:message  code="income.btn.new"/>"></span>
                        </form>
                    </c:when>
                </c:choose>
                <c:choose>
                    <c:when test="${busquedaAdm eq 'findAdm' }">
                        <form  method="POST" action="${urlNuevoUAdm}">
                            <span class="icon-input-btn"><i class="fa fa-user-plus yvela" aria-hidden="true"></i> <input type="submit" class="btn btn-success btn-lg" value="<spring:message  code="income.btn.new"/>"></span>
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
                <spring:message  code="AbstractUserDetailsAuthenticationProvider.badCredentials"/>
                <br/>
            </div>
        </c:if>
        <c:choose>
            <c:when test="${operation eq 'reg' }">
                <center><br>
                    <p class="h3"><i class="fa fa-address-card-o" aria-hidden="true"></i> </i> <spring:message  code="menu.register"/></p> 
                    <br>
                </center>

                <form:form modelAttribute="usuarios" action="${url}" method="post">
                    <div class="form-group row">
                        <label for="nombres" class="col-sm-2 col-form-label"><spring:message  code="login.people"/></label>
                        <label class="col-sm-1 col-form-label text-right" ><i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <form:select class="form-control form-control-lg" path="Personas.idPersona" id="signup">
                                <c:forEach var="p" items="${requestScope.lPersonas}">
                                    <form:option value="${p.personas.idPersona}">${p.personas.nombres} ${p.personas.paterno} ${p.personas.materno} </form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <spring:message  code="info.login.people"/>
                            </small>
                            <small id="emailHp" class="form-text text-muted formFieldError">
                                <form:errors path="Personas.idPersona" cssClass="alert alert-danger"/>
                            </small>
                            <small class="msgError" >
                                <c:choose>
                                    <c:when test="${mensage eq 'err' }">
                                        <i class="fa fa-exclamation-triangle" aria-hidden="true"></i>  <spring:message  code="manage.user.person.validation"/>
                                    </c:when>
                                </c:choose> 
                            </small>   
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="nombres" class="col-sm-2 col-form-label"><spring:message  code="login.user"/></label>
                        <label class="col-sm-1 col-form-label text-right" ><i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <spring:message  code="login.user.info" var="usuario"/>
                            <form:input type="text" class="form-control form-control-lg" path="usuario"  placeholder="${usuario}" />
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <spring:message  code="login.user.info"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="usuario" />
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="nombres" class="col-sm-2 col-form-label"><spring:message  code="login.password"/></label>
                        <label class="col-sm-1 col-form-label text-right" ><i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <spring:message  code="login.password.info" var="password"/>
                            <form:input type="password" class="form-control form-control-lg" path="password" placeholder="${password}" />
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <spring:message  code="login.password.info"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="password" />
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="msg" class="col-sm-2 col-form-label text-right"></label>
                        <label for="msg" class="col-sm-10 col-form-label"><span class="parpadea text"><i class="fa fa-info-circle" aria-hidden="true"></i></span> &nbsp;&nbsp;&nbsp;<spring:message  code="people.warning"/> </label>
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
                            <a href="<spring:url value="/users/signupForm"/>" class="btn btn-yvela-green"><i class="fa fa-undo yvela" aria-hidden="true"></i>  <spring:message  code="expenses.btn.cancel"/></a>
                        </div> 
                    </div>
                </form:form>
            </c:when>
        </c:choose>

        <!-- Modificar usuarios -->
        <c:choose>
            <c:when test="${operation eq 'mod' }">
                <center><br>
                    <p class="h3"><i class="fa fa-address-card-o" aria-hidden="true"></i> </i> <spring:message  code="manage.user.modify"/></p> 
                    <br>
                </center>

                <form:form modelAttribute="usuarios" action="${modUsuario}" method="post">
                    <form:hidden  path="idUsuario" value="${usuarios.idUsuario}" />

                    <div class="form-group row">
                        <label for="nombres" class="col-sm-2 col-form-label"><spring:message  code="login.people"/></label>
                        <label class="col-sm-1 col-form-label text-right" ><i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <form:select class="form-control form-control-lg" path="Personas.idPersona" id="signup">
                                <c:forEach var="p" items="${requestScope.lPersonas}">
                                    <form:option value="${p.personas.idPersona}">${p.personas.nombres} ${p.personas.paterno} ${p.personas.materno} </form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <spring:message  code="info.login.people"/>
                            </small>
                            <small id="emailHp" class="form-text text-muted formFieldError">
                                <form:errors path="Personas.idPersona" cssClass="alert alert-danger"/>
                            </small>

                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="nombres" class="col-sm-2 col-form-label"><spring:message  code="login.user"/></label>
                        <label class="col-sm-1 col-form-label text-right" ><i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <spring:message  code="login.user.info" var="usuario"/>
                            <form:input type="text" class="form-control form-control-lg" path="usuario"  placeholder="${usuario}" />
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <spring:message  code="login.user.info"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="usuario" />
                            </small>
                            <small class="msgError" >
                                <c:choose>
                                    <c:when test="${mensage eq 'err' }">
                                        <i class="fa fa-exclamation-triangle" aria-hidden="true"></i>  <spring:message  code="manage.user.user.validation"/>
                                    </c:when>
                                </c:choose> 
                            </small>   
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="nombres" class="col-sm-2 col-form-label"><spring:message  code="login.password"/></label>
                        <label class="col-sm-1 col-form-label text-right" ><i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <spring:message  code="login.password.info" var="password"/>
                            <input type="password" class="form-control form-control-lg" name="password" placeholder="${password}" />
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <spring:message  code="login.password.info"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="password" />
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="msg" class="col-sm-2 col-form-label text-right"></label>
                        <label for="msg" class="col-sm-10 col-form-label"><span class="parpadea text"><i class="fa fa-info-circle" aria-hidden="true"></i></span> &nbsp;&nbsp;&nbsp;<spring:message  code="people.warning"/> </label>
                    </div>   
                    <hr align="left" noshade="noshade" size="10" width="100%" />
                    <div class="form-group row mt-12">
                        <div class="col-sm-3" id="combo1">
                        </div> 
                        <div class="col-sm-3" id="combo1">
                            <span class="icon-input-btn"><i class="fa fa-pencil-square-o yvela" aria-hidden="true"></i> <input class="btn btn-success" type="submit" value="<spring:message  code="expenses.modify"/>"></span>
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                        </div>
                        <div class="col-sm-3" id="combo1">
                            <a href="<spring:url value="/users/signupForm"/>" class="btn btn-yvela-green"><i class="fa fa-undo yvela" aria-hidden="true"></i>  <spring:message  code="expenses.btn.cancel"/></a>
                        </div> 
                    </div>
                </form:form>
            </c:when>
        </c:choose>
        <!--fin modificar usuarios -->

        <!-- Eliminar usuarios -->
        <c:choose>
            <c:when test="${operation eq 'elim' }">
                <div id="" class="col-lg-12" >
                    <center><br>
                        <p class="h3"><i class="fa fa-address-card-o" aria-hidden="true"></i> </i> <spring:message  code="manage.user.remove"/></p> 
                        <br>
                    </center>
                    <div class="form-group row">
                        <div class="col-sm-8" id="combo1">  
                            <form:form modelAttribute="usuarios" action="${elimUsuario}" method="post">
                                <form:hidden  path="idUsuario" value="${usuarios.idUsuario}" />

                                <div class="form-group row">
                                    <label for="nombres" class="col-sm-4 col-form-label"><spring:message  code="login.people"/></label>
                                    <div class="col-sm-8">
                                        <form:select class="form-control form-control-lg" path="Personas.idPersona" readonly="true">
                                            <c:forEach var="p" items="${requestScope.lPersonas}">
                                                <form:option value="${p.personas.idPersona}">${p.personas.nombres} ${p.personas.paterno} ${p.personas.materno} </form:option>
                                            </c:forEach>
                                        </form:select>
                                    </div>

                                </div>
                                <div class="form-group row">
                                    <label for="nombres" class="col-sm-4 col-form-label"><spring:message  code="login.user"/></label>
                                    <div class="col-sm-8">
                                        <spring:message  code="login.user.info" var="usuario"/>
                                        <form:input type="text" class="form-control form-control-lg" path="usuario"  placeholder="${usuario}" readonly="true"/>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="nombres" class="col-sm-4 col-form-label"><spring:message  code="login.password"/></label>
                                    <div class="col-sm-8">
                                        <spring:message  code="login.password.info" var="password"/>
                                        <form:input type="password" class="form-control form-control-lg" path="password" placeholder="${password}" readonly="true"/>
                                    </div>
                                </div>
                                <hr align="left" noshade="noshade" size="10" width="100%" />
                                <div class="form-group row mt-12">
                                    <div class="col-sm-3" id="combo1">
                                    </div> 
                                    <div class="col-sm-3" id="combo1">
                                        <span class="icon-input-btn"><i class="fa fa-pencil-square-o yvela" aria-hidden="true"></i> <input class="btn btn-success" type="submit" value="<spring:message  code="expenses.btn.confirm"/>"></span>
                                    </div>
                                    <div class="col-sm-3" id="combo1"> 
                                    </div>
                                    <div class="col-sm-3" id="combo1">
                                        <a href="<spring:url value="/users/signupForm"/>" class="btn btn-yvela-green"><i class="fa fa-undo yvela" aria-hidden="true"></i>  <spring:message  code="expenses.btn.cancel"/></a>
                                    </div> 
                                </div>
                            </form:form>
                        </div>
                        <div class="col-sm-4" id="combo1"> 
                            <fieldset class="col-md-12">    	
                                <legend><i class="fa fa-exclamation-triangle" aria-hidden="true"></i>  <spring:message  code="expenses.remove.info.title"/></legend>
                                <br> 
                                <p><spring:message  code="people.remove.msg"/> </p>
                                <br>
                                <c:choose>
                                    <c:when test="${mensage eq 'err' }">
                                        <span class="parpadea text"><p class="h1"><i class="fa fa-exclamation-triangle" aria-hidden="true"></i></p></span>  <spring:message  code="people.warning.remove"/> 
                                            </c:when>
                                        </c:choose>
                                <br>
                            </fieldset>
                        </div>
                    </div>
                </div>
            </c:when>
        </c:choose>

        <!-- fin eliminar usuarios -->


        <!-- Nuevo usuaio Administrador -->

        <c:choose>
            <c:when test="${operationAdm eq 'regAdm' }">
                <center><br>
                    <p class="h3"><i class="fa fa-address-card-o" aria-hidden="true"></i> </i> <spring:message  code="menu.add.admin"/></p> 
                    <br>
                </center>

                <form:form modelAttribute="usuarios" action="${registrarUAdm}" method="post">
                    <div class="form-group row">
                        <label for="nombres" class="col-sm-2 col-form-label"><spring:message  code="login.people"/></label>
                        <label class="col-sm-1 col-form-label text-right" ><i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <form:select class="form-control form-control-lg" path="Personas.idPersona" id="signup">
                                <c:forEach var="p" items="${requestScope.lPersonas}">
                                    <form:option value="${p.personas.idPersona}">${p.personas.nombres} ${p.personas.paterno} ${p.personas.materno} </form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <spring:message  code="info.login.people"/>
                            </small>
                            <small id="emailHp" class="form-text text-muted formFieldError">
                                <form:errors path="Personas.idPersona" cssClass="alert alert-danger"/>
                            </small>
                            <small class="msgError" >
                                <c:choose>
                                    <c:when test="${mensage eq 'err' }">
                                        <i class="fa fa-exclamation-triangle" aria-hidden="true"></i>  <spring:message  code="manage.user.person.validation"/>
                                    </c:when>
                                </c:choose> 
                            </small>   
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="nombres" class="col-sm-2 col-form-label"><spring:message  code="login.user"/></label>
                        <label class="col-sm-1 col-form-label text-right" ><i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <spring:message  code="login.user.info" var="usuario"/>
                            <form:input type="text" class="form-control form-control-lg" path="usuario"  placeholder="${usuario}" />
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <spring:message  code="login.user.info"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="usuario" />
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="nombres" class="col-sm-2 col-form-label"><spring:message  code="login.password"/></label>
                        <label class="col-sm-1 col-form-label text-right" ><i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <spring:message  code="login.password.info" var="password"/>
                            <form:input type="password" class="form-control form-control-lg" path="password" placeholder="${password}" />
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <spring:message  code="login.password.info"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="password" />
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="msg" class="col-sm-2 col-form-label text-right"></label>
                        <label for="msg" class="col-sm-10 col-form-label"><span class="parpadea text"><i class="fa fa-info-circle" aria-hidden="true"></i></span> &nbsp;&nbsp;&nbsp;<spring:message  code="people.warning"/> </label>
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
                            <a href="<spring:url value="/users/addAdminForm"/>" class="btn btn-yvela-green"><i class="fa fa-undo yvela" aria-hidden="true"></i>  <spring:message  code="expenses.btn.cancel"/></a>
                        </div> 
                    </div>
                </form:form>
            </c:when>
        </c:choose>

        <!--fin usuario administrador -->

        <c:choose>
            <c:when test="${busqueda eq 'find' }">
                <center><br>
                    <p class="h3"><i class="fa fa-list-alt" aria-hidden="true"></i> <spring:message  code="manage.user.details"/></p> 
                    <br>
                </center>
                <table id="tPersonas"  class="table" style="width:100%">
                    <thead>
                        <tr>
                            <th><spring:message  code="persona.cedula"/></th>
                            <th><spring:message  code="persona.nombres"/></th>
                            <th><spring:message  code="reporte.usuario"/></th>
                            <th><spring:message  code="expenses.modify"/> </th>
                            <th><spring:message  code="expenses.remove"/></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:set var="cont" value="0" />
                        <c:forEach items="${lUsuarios}" var="u">
                            <c:set var="cont" value="${cont+1}" />
                            <tr>
                                <td>${u.personas.ci}</td>
                                <td>${u.personas.nombres} ${u.personas.paterno} ${u.personas.materno}</td>
                                <td> ${u.usuario} </td>
                                <td>
                                    <form  method="post" action="${inicioModU}">
                                        <input type="hidden" name="idUsuario" value="${u.idUsuario}">
                                        <span class="icon-input-btn"> <i class="fa fa-pencil-square-o yvela" aria-hidden="true"> </i> <input class="btn btn-success" type="submit"  value="<spring:message  code="expenses.modify"/>" > </span>
                                    </form>
                                </td>
                                <td>
                                    <form name="Delet" action="${inicElimU}" method="post">
                                        <input type="hidden" name="idUsuario" value="${u.idUsuario}">
                                        <span class="icon-input-btn"> <i class="fa fa-times yvela" aria-hidden="true"> </i> <input class="btn btn-danger" type="submit"  value="<spring:message  code="expenses.remove"/>"> </span>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                    <tfoot>
                        <tr>
                            <th><spring:message  code="persona.cedula"/></th>
                            <th><spring:message  code="persona.nombres"/></th>
                            <th><spring:message  code="reporte.usuario"/></th>
                            <th><spring:message  code="expenses.modify"/> </th>
                            <th><spring:message  code="expenses.remove"/></th>
                        </tr>
                    </tfoot>
                </table>
                <br><br>
                <hr align="left" noshade="noshade" size="10" width="100%" />
            </c:when>
        </c:choose>

        <!---------------------- Lista Administrador de Sistemas------------------------>
        <c:choose>
            <c:when test="${busquedaAdm eq 'findAdm' }">
                <center><br>
                    <p class="h3"><i class="fa fa-list-alt" aria-hidden="true"></i> <spring:message  code="manage.user.details"/></p> 
                    <br>
                </center>
                <table id="tPersonas"  class="table" style="width:100%">
                    <thead>
                        <tr>
                            <th><spring:message  code="persona.cedula"/></th>
                            <th><spring:message  code="persona.nombres"/></th>
                            <th><spring:message  code="reporte.usuario"/></th>
                            <th><spring:message  code="expenses.modify"/> </th>
                            <th><spring:message  code="expenses.remove"/></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:set var="cont" value="0" />
                        <c:forEach items="${lUsuarios}" var="u">
                            <c:set var="cont" value="${cont+1}" />
                            <tr>
                                <td>${u.personas.ci}</td>
                                <td>${u.personas.nombres} ${u.personas.paterno} ${u.personas.materno}</td>
                                <td> ${u.usuario} </td>


                                <td>
                                    <form  method="post" action="${inicioModU}">
                                        <input type="hidden" name="idUsuario" value="${u.idUsuario}">
                                        <span class="icon-input-btn"> <i class="fa fa-pencil-square-o yvela" aria-hidden="true"> </i> <input class="btn btn-success" type="submit"  value="<spring:message  code="expenses.modify"/>" > </span>
                                    </form>
                                </td>
                                <td>
                                    <form name="Delet" action="${inicElimU}" method="post">
                                        <input type="hidden" name="idUsuario" value="${u.idUsuario}">
                                        <span class="icon-input-btn"> <i class="fa fa-times yvela" aria-hidden="true"> </i> <input class="btn btn-danger" type="submit"  value="<spring:message  code="expenses.remove"/>"> </span>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                    <tfoot>
                        <tr>
                            <th><spring:message  code="persona.cedula"/></th>
                            <th><spring:message  code="persona.nombres"/></th>
                            <th><spring:message  code="reporte.usuario"/></th>
                            <th><spring:message  code="expenses.modify"/> </th>
                            <th><spring:message  code="expenses.remove"/></th>
                        </tr>
                    </tfoot>
                </table>
                <br><br>
                <hr align="left" noshade="noshade" size="10" width="100%" />
            </c:when>
        </c:choose>
    </section>
</div>


<!---------------------- fin  --Lista Administrador de Sistemas------------------------>
