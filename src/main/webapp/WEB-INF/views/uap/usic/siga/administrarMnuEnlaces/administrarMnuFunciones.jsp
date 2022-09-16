<%-- 
    Document   : administrarMnuFunciones
    Created on : 21-07-2019, 05:53:08 PM
    Author     : Yessenia
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <link rel="stylesheet" type="text/css"  href=" <c:url value="/resource/admPersonas/personas.css"/>" />
    <script src="<spring:url value="/resource/admPersonas/personas.js" />" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css"  href=" <c:url value="/resource/admEnlaces/enlaces.css"/>" />
    <script src="<spring:url value="/resource/admEnlaces/enlaces.js" />" type="text/javascript"></script>
</head>

<div class="container"> 
    <section class="content-header">
        <div class="row">
            <div class="col-md-10 mb-3">
                <p class="h1"><i class="fa fa-users" aria-hidden="true"></i>  <spring:message code="functions.title"/> </p>
            </div>
            <div class="col-sm-2 text-right" id="reporte">
                <c:choose>
                    <c:when test="${busqueda eq 'find' }">
                        <form  method="POST" action="${urlNMnuFuncion}">
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
        <!--------------- Inicio registro nueva funcion-->
        <c:choose>
            <c:when test="${operation eq 'reg' }">
                <center><br>
                    <p class="h3"><i class="fa fa-newspaper-o" aria-hidden="true"> </i> <spring:message  code="functions.register"/></p> 
                    <br>
                </center>

                <form:form modelAttribute="mnuFunciones" action="${regMnuFuncion}" method="post">

                    <div class="form-group row">
                        <label for="nombres" class="col-sm-2 col-form-label"><spring:message  code="login.people"/></label>
                        <label class="col-sm-1 col-form-label text-right" ><i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <form:select class="form-control form-control-lg" path="Personas.idPersona" id="personas" >
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
                        <label for="sexo" class="col-sm-2 col-form-label"><spring:message  code="menues.type.functions"/></label>
                        <label class="col-sm-1 col-form-label text-right"><i class="fa fa-info-circle" aria-hidden="true"></i> </label>
                        <div class="col-sm-6">
                            <form:select class="form-control form-control-lg" path="MnuTiposFunciones.idMnuTipoFuncion" id="tipoFunciones">
                                <c:forEach var="ts" items="${requestScope.lTiposFunciones}">
                                    <form:option value="${ts.idMnuTipoFuncion}">${ts.tipoFuncion}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <a class="btn add" href="#" data-toggle="modal" data-target="#formMnuTiposFunciones"><i class="fa fa-plus-square-o" aria-hidden="true"></i>  <spring:message  code="people.add"/></a></small>
                            <small id="emailHp" class="form-text text-muted formFieldError">
                                <form:errors path="MnuTiposFunciones.idMnuTipoFuncion" cssClass="alert alert-danger"/>
                            </small>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="sexo" class="col-sm-2 col-form-label"><spring:message  code="functions.system"/></label>
                        <label class="col-sm-1 col-form-label text-right"><i class="fa fa-info-circle" aria-hidden="true"></i> </label>
                        <div class="col-sm-6">
                            <form:select class="form-control form-control-lg" path="SisAdministrador.idSisAdministrador" id="sisAdministrador">
                                <c:forEach var="sa" items="${requestScope.lSisAdministrador}">
                                    <form:option value="${sa.idSisAdministrador}">${sa.nombreSis}-${sa.gestion}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <a class="btn add" href="#" data-toggle="modal" data-target="#formSisAdministrador"><i class="fa fa-plus-square-o" aria-hidden="true"></i>  <spring:message  code="people.add"/></a></small>
                            <small id="emailHp" class="form-text text-muted formFieldError">
                                <form:errors path="SisAdministrador.idSisAdministrador" cssClass="alert alert-danger"/>
                            </small>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="sexo" class="col-sm-2 col-form-label"><spring:message  code="functions.level.access"/></label>
                        <label class="col-sm-1 col-form-label text-right"><i class="fa fa-info-circle" aria-hidden="true"></i> </label>
                        <div class="col-sm-6">
                            <form:select class="form-control form-control-lg" path="SisNivelesAccesos.idNivelAcceso" id="sisNivelesAccesos">
                                <c:forEach var="na" items="${requestScope.lNivelesAccesos}">
                                    <form:option value="${na.idNivelAcceso}">${na.nivelAcceso}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <a class="btn add" href="#" data-toggle="modal" data-target="#formSisNivelesAccesos"><i class="fa fa-plus-square-o" aria-hidden="true"></i>  <spring:message  code="people.add"/></a></small>
                            <small id="emailHp" class="form-text text-muted formFieldError">
                                <form:errors path="SisNivelesAccesos.idNivelAcceso" cssClass="alert alert-danger"/>
                            </small>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="functions.value"/></label>
                        <label class="col-sm-1 col-form-label text-right" > <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6" id="combo1"> 
                            <spring:message  code="functions.value.msg" var="document"/>
                            <form:input path="valor"  class="form-control form-control-lg" placeholder="${document}" /> 
                        </div>
                        <div class="col-sm-3" id="comd"> 
                            <small id="emailHp" class="form-text text-muted">
                                <spring:message  code="functions.value.msg"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="valor" />
                            </small>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="functions.expiration.date"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6" id="combo1"> 
                            <spring:message  code="functions.expiration.date.msg" var="date"/>
                            <form:input type="date" path="fecExpiracion" class="form-control form-control-lg" /> 
                        </div>
                        <div class="col-sm-3" id="comd"> 
                            <small id="emailHp" class="form-text text-muted">
                                <spring:message  code="functions.expiration.date.msg"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="fecExpiracion" />
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
                            <a href="<spring:url value="/mnuFunciones/formMnuFunciones"/>" class="btn btn-yvela-green"><i class="fa fa-undo yvela" aria-hidden="true"></i>  <spring:message  code="expenses.btn.cancel"/></a>
                        </div> 
                    </div>
                </form:form>
                <br><br>
                <hr align="left" noshade="noshade" size="10" width="100%" />
            </c:when>
        </c:choose>
        <!-- Final registro Menues -->

        
         <!--------------- Inicio modificar nueva funcion-->
        <c:choose>
            <c:when test="${operation eq 'mod' }">
                <center><br>
                    <p class="h3"><i class="fa fa-newspaper-o" aria-hidden="true"> </i> <spring:message  code="functions.modify"/></p> 
                    <br>
                </center>

                <form:form modelAttribute="mnuFunciones" action="${urlConfModF}" method="post">

                    <form:hidden  path="idMnuFuncion" value="${mnuFunciones.idMnuFuncion}" />
                    
                    <div class="form-group row">
                        <label for="nombres" class="col-sm-2 col-form-label"><spring:message  code="login.people"/></label>
                        <label class="col-sm-1 col-form-label text-right" ><i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <form:select class="form-control form-control-lg" path="Personas.idPersona" id="personas" >
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
                        <label for="sexo" class="col-sm-2 col-form-label"><spring:message  code="menues.type.functions"/></label>
                        <label class="col-sm-1 col-form-label text-right"><i class="fa fa-info-circle" aria-hidden="true"></i> </label>
                        <div class="col-sm-6">
                            <form:select class="form-control form-control-lg" path="MnuTiposFunciones.idMnuTipoFuncion" id="tipoFunciones">
                                <c:forEach var="ts" items="${requestScope.lTiposFunciones}">
                                    <form:option value="${ts.idMnuTipoFuncion}">${ts.tipoFuncion}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <a class="btn add" href="#" data-toggle="modal" data-target="#formMnuTiposFunciones"><i class="fa fa-plus-square-o" aria-hidden="true"></i>  <spring:message  code="people.add"/></a></small>
                            </small>
                            <small id="emailHp" class="form-text text-muted formFieldError">
                                <form:errors path="MnuTiposFunciones.idMnuTipoFuncion" cssClass="alert alert-danger"/>
                            </small>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="sexo" class="col-sm-2 col-form-label"><spring:message  code="functions.system"/></label>
                        <label class="col-sm-1 col-form-label text-right"><i class="fa fa-info-circle" aria-hidden="true"></i> </label>
                        <div class="col-sm-6">
                            <form:select class="form-control form-control-lg" path="SisAdministrador.idSisAdministrador" id="sisAdministrador">
                                <c:forEach var="sa" items="${requestScope.lSisAdministrador}">
                                    <form:option value="${sa.idSisAdministrador}">${sa.nombreSis}-${sa.gestion}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <a class="btn add" href="#" data-toggle="modal" data-target="#formSisAdministrador"><i class="fa fa-plus-square-o" aria-hidden="true"></i>  <spring:message  code="people.add"/></a></small>
                            </small>
                            <small id="emailHp" class="form-text text-muted formFieldError">
                                <form:errors path="SisAdministrador.idSisAdministrador" cssClass="alert alert-danger"/>
                            </small>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="sexo" class="col-sm-2 col-form-label"><spring:message  code="functions.level.access"/></label>
                        <label class="col-sm-1 col-form-label text-right"><i class="fa fa-info-circle" aria-hidden="true"></i> </label>
                        <div class="col-sm-6">
                            <form:select class="form-control form-control-lg" path="SisNivelesAccesos.idNivelAcceso" id="sisNivelesAccesos">
                                <c:forEach var="na" items="${requestScope.lNivelesAccesos}">
                                    <form:option value="${na.idNivelAcceso}">${na.nivelAcceso}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <a class="btn add" href="#" data-toggle="modal" data-target="#formSisNivelesAccesos"><i class="fa fa-plus-square-o" aria-hidden="true"></i>  <spring:message  code="people.add"/></a></small>
                            </small>
                            <small id="emailHp" class="form-text text-muted formFieldError">
                                <form:errors path="SisNivelesAccesos.idNivelAcceso" cssClass="alert alert-danger"/>
                            </small>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="functions.value"/></label>
                        <label class="col-sm-1 col-form-label text-right" > <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6" id="combo1"> 
                            <spring:message  code="functions.value.msg" var="document"/>
                            <form:input path="valor"  class="form-control form-control-lg" placeholder="${document}" /> 
                        </div>
                        <div class="col-sm-3" id="comd"> 
                            <small id="emailHp" class="form-text text-muted">
                                <spring:message  code="functions.value.msg"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="valor" />
                            </small>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="functions.expiration.date"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6" id="combo1"> 
                            <spring:message  code="functions.expiration.date.msg" var="date"/>
                            <form:input type="date" path="fecExpiracion" class="form-control form-control-lg" /> 
                        </div>
                        <div class="col-sm-3" id="comd"> 
                            <small id="emailHp" class="form-text text-muted">
                                <spring:message  code="functions.expiration.date.msg"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="fecExpiracion" />
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
                            <a href="<spring:url value="/mnuFunciones/formMnuFunciones"/>" class="btn btn-yvela-green"><i class="fa fa-undo yvela" aria-hidden="true"></i>  <spring:message  code="expenses.btn.cancel"/></a>
                        </div> 
                    </div>
                </form:form>
                <br><br>
                <hr align="left" noshade="noshade" size="10" width="100%" />
            </c:when>
        </c:choose>
        <!-- Final registro Menues -->

           <!--------------- Inicio Eliminar  funciones-->
            <c:choose>
                <c:when test="${operation eq 'elim' }">
                 <div id="" class="col-lg-12" >
                    <center><br>
                        <p class="h3"><i class="fa fa-newspaper-o" aria-hidden="true"> </i> <spring:message  code="functions.remove"/></p> 
                        <br>
                    </center>
                 <div class="form-group row">
                        <div class="col-sm-8" id="combo1">   
                    <form:form modelAttribute="mnuFunciones" action="${urlConfElimF}" method="post">
                        <form:hidden  path="idMnuFuncion" value="${mnuFunciones.idMnuFuncion}" />
                       
                       <div class="form-group row">
                        <label for="nombres" class="col-sm-4 col-form-label"><spring:message  code="login.people"/></label>
                        <div class="col-sm-8">
                            <form:select class="form-control form-control-lg" path="Personas.idPersona" id="personas" readonly="true" >
                                <c:forEach var="p" items="${requestScope.lPersonas}">
                                    <form:option value="${p.personas.idPersona}">${p.personas.nombres} ${p.personas.paterno} ${p.personas.materno} </form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        
                    </div>
                    <div class="form-group row">
                        <label for="sexo" class="col-sm-4 col-form-label"><spring:message  code="menues.type.functions"/></label>
                        <div class="col-sm-8">
                            <form:select class="form-control form-control-lg" path="MnuTiposFunciones.idMnuTipoFuncion" id="tipoFunciones" readonly="true">
                                <c:forEach var="ts" items="${requestScope.lTiposFunciones}">
                                    <form:option value="${ts.idMnuTipoFuncion}">${ts.tipoFuncion}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        
                    </div>
                    <div class="form-group row">
                        <label for="sexo" class="col-sm-4 col-form-label"><spring:message  code="functions.system"/></label>
                        <div class="col-sm-8">
                            <form:select class="form-control form-control-lg" path="SisAdministrador.idSisAdministrador" id="sisAdministrador" readonly="true">
                                <c:forEach var="sa" items="${requestScope.lSisAdministrador}">
                                    <form:option value="${sa.idSisAdministrador}">${sa.nombreSis}-${sa.gestion}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="sexo" class="col-sm-4 col-form-label"><spring:message  code="functions.level.access"/></label>
                        <div class="col-sm-8">
                            <form:select class="form-control form-control-lg" path="SisNivelesAccesos.idNivelAcceso" id="tipoFuncioness" readonly="true">
                                <c:forEach var="na" items="${requestScope.lNivelesAccesos}">
                                    <form:option value="${na.idNivelAcceso}">${na.nivelAcceso}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="codigo" class="col-sm-4 col-form-label"><spring:message  code="functions.value"/></label>
                        <div class="col-sm-8" id="combo1"> 
                            <spring:message  code="functions.value.msg" var="document"/>
                            <form:input path="valor"  class="form-control form-control-lg" placeholder="${document}" readonly="true"/> 
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="codigo" class="col-sm-4 col-form-label"><spring:message  code="functions.expiration.date" /></label>
                        <div class="col-sm-8" id="combo1"> 
                            <spring:message  code="functions.expiration.date.msg" var="date" />
                            <form:input type="date" path="fecExpiracion" class="form-control form-control-lg" readonly="true"/> 
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
                                
                               <input type="submit"  class="btn btn-danger" value="<spring:message  code="expenses.btn.confirm"/>">
                            </div>
                            <div class="col-sm-3" id="combo1"> 
                            </div>
                            <div class="col-sm-3" id="combo1">
                                <a href="<spring:url value="/mnuFunciones/formMnuFunciones"/>" class="btn btn-yvela-green"><i class="fa fa-undo yvela" aria-hidden="true"></i>  <spring:message  code="expenses.btn.cancel"/></a>
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
                    <br><br>
                    <hr align="left" noshade="noshade" size="10" width="100%" />
                </c:when>
            </c:choose>
            <!-- Final Eliminar enlaces -->
        
        
        <!--Tabla lista de todas las funciones registrados-->
        <c:choose>
            <c:when test="${busqueda eq 'find' }">
                <center><br>
                    <p class="h3"><i class="fa fa-list-alt" aria-hidden="true"></i> <spring:message  code="functions.details"/></p> 
                    <br>
                </center>
                <table id="tEnlaces"  class="table" style="width:100%">
                    <thead>
                        <tr>
                            <th><spring:message  code="login.people"/></th>
                            <th><spring:message  code="menues.type.functions"/></th>
                            <th><spring:message  code="functions.system"/>
                            <th><spring:message  code="functions.value"/>
                            <th><spring:message  code="functions.expiration.date"/>
                            <th><spring:message  code="expenses.modify"/> </th>
                            <th><spring:message  code="expenses.remove"/></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:set var="cont" value="0" />
                        <c:forEach items="${lMnuFunciones}" var="f">
                            <c:set var="cont" value="${cont+1}" />
                            <tr>
                                <td>${f.personas.nombres} ${f.personas.paterno}</td>
                                <td>${f.mnuTiposFunciones.tipoFuncion}</td>
                                <td>${f.sisAdministrador.nombreSis}</td>
                                <td>${f.valor}</td>
                                 <td>${f.fecExpiracion}</td>
                                <td><form  method="post" action="${urlModMnuFuncion}">
                                        <input type="hidden" name="idMnuFuncion" value="${f.idMnuFuncion}">
                                        <span class="icon-input-btn"> <i class="fa fa-pencil-square-o yvela" aria-hidden="true"> </i> <input class="btn btn-success" type="submit"  value="<spring:message  code="expenses.modify"/>" > </span>
                                    </form>
                                </td>
                                <td>
                                    <form name="Delet" action="${urlElimMnuFuncion}" method="post">
                                        <input type="hidden" name="idMnuFuncion" value="${f.idMnuFuncion}">
                                        <span class="icon-input-btn"> <i class="fa fa-times yvela" aria-hidden="true"> </i> <input class="btn btn-danger" type="submit"  value="<spring:message  code="expenses.remove"/>"> </span>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                    <tfoot>
                        <tr>
                           <th><spring:message  code="login.people"/></th>
                            <th><spring:message  code="menues.type.functions"/></th>
                            <th><spring:message  code="functions.system"/>
                            <th><spring:message  code="functions.value"/>
                            <th><spring:message  code="functions.expiration.date"/>
                            <th><spring:message  code="expenses.modify"/> </th>
                            <th><spring:message  code="expenses.remove"/></th>
                        </tr>
                    </tfoot>
                </table>
                <br><br>
                <hr align="left" noshade="noshade" size="10" width="100%" />
            </c:when>
        </c:choose>
        <!-- Final Tabla Lista menues-->

    </section>
</div>
<!-- Inicio modal registrar tipos Funciones-->           
<div id="formMnuTiposFunciones" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form name="mnuTiposFunciones" action="guardarMnuTiposFuncion" role="form" id="mnuTiposFuncionesForm" method="post">
                <div class="modal-header">
                    <p class="h3"><i class="fa fa-id-card" aria-hidden="true"></i>  <spring:message  code="menues.register.type.funtions"/></p>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                </div>
                <div class="modal-body">
                    <div class="form-group row">
                        <spring:message  code="menues.type.functions" var="issued"/>
                        <label for="expedido" class="col-sm-3 col-form-label"><spring:message  code="menues.type.functions"/></label>
                        <label for="mnuTipoEnlace" class="col-sm-1 col-form-label"><i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-8">
                            <input type="text" name="tipoFuncion" class="form-control form-control-lg"  placeholder="${issued}" />
                        </div>
                    </div>
                    <div class="form-group row">
                        <spring:message  code="items.institution.code" var="initials"/>
                        <label for="espedidoSigla" class="col-sm-3 col-form-label"><spring:message  code="items.institution.code"/></label>
                        <label for="ciExpedidoSigla" class="col-sm-1 col-form-label"><i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-8">
                            <input type="text" name="sigla" class="form-control form-control-lg"  placeholder="${initials}" />
                        </div>
                    </div>

                </div>
                <div class="modal-footer">  
                    <button class="btn btn-outline-secondary btn-lg" data-dismiss="modal" aria-hidden="true"><i class="fa fa-undo" aria-hidden="true"></i>  <spring:message  code="people.cancel"/></button>
                    <button type="submit" class="btn btn-success btn-lg float-right" id="btnMnuTiposFunciones" data-dismiss="modal" aria-hidden="true"><i class="fa fa-plus-square-o" aria-hidden="true"></i>   <spring:message  code="people.add"/></button>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- final modal registrar tipos Funciones-->           

<!-- Inicio modal registrar Sis Administrador-->           
<div id="formSisAdministrador" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form name="mnuSisAdministrador" action="guardarSisAdministrador" role="form" id="mnuSisAdministradorForm" method="post">
                <div class="modal-header">
                    <p class="h3"><i class="fa fa-id-card" aria-hidden="true"></i>  <spring:message  code="functions.register.system"/></p>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                </div>
                <div class="modal-body">
                    <div class="form-group row">
                        <spring:message  code="people.name.msg" var="issued"/>
                        <label for="expedido" class="col-sm-3 col-form-label"><spring:message  code="people.name.msg"/></label>
                        <label for="mnuTipoEnlace" class="col-sm-1 col-form-label"><i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-8">
                            <input type="text" name="nombreSis" class="form-control form-control-lg"  placeholder="${issued}" />
                        </div>
                    </div>
                    <div class="form-group row">
                        <spring:message  code="ingreso.descripcion" var="initials"/>
                        <label for="espedidoSigla" class="col-sm-3 col-form-label"><spring:message  code="ingreso.descripcion"/></label>
                        <label for="ciExpedidoSigla" class="col-sm-1 col-form-label"><i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-8">
                            <input type="text" name="descripcion" class="form-control form-control-lg"  placeholder="${initials}" />
                        </div>
                    </div>
                    <div class="form-group row">
                        <spring:message  code="items.year" var="initials"/>
                        <label for="espedidoSigla" class="col-sm-3 col-form-label"><spring:message  code="items.year"/></label>
                        <label for="ciExpedidoSigla" class="col-sm-1 col-form-label"><i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-8">
                            <input type="text" name="gestion" class="form-control form-control-lg"  placeholder="${initials}" />
                        </div>
                    </div>
                    <div class="form-group row">
                        <spring:message  code="items.period" var="initials"/>
                        <label for="espedidoSigla" class="col-sm-3 col-form-label"><spring:message  code="items.period"/></label>
                        <label for="ciExpedidoSigla" class="col-sm-1 col-form-label"><i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-8">
                            <input type="text" name="periodo" class="form-control form-control-lg"  placeholder="${initials}" />
                        </div>
                    </div>
                    <div class="form-group row">
                        <spring:message  code="functions.value" var="initials"/>
                        <label for="espedidoSigla" class="col-sm-3 col-form-label"><spring:message  code="functions.value"/></label>
                        <label for="ciExpedidoSigla" class="col-sm-1 col-form-label"><i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-8">
                            <input type="text" name="valor" class="form-control form-control-lg"  placeholder="${initials}" />
                        </div>
                    </div>
                    <div class="form-group row">
                        <spring:message  code="functions.observation" var="initials"/>
                        <label for="espedidoSigla" class="col-sm-3 col-form-label"><spring:message  code="functions.observation"/></label>
                        <label for="ciExpedidoSigla" class="col-sm-1 col-form-label"><i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-8">
                            <input type="text" name="obs" class="form-control form-control-lg"  placeholder="${initials}" />
                        </div>
                    </div>  

                </div>
                <div class="modal-footer">  
                    <button class="btn btn-outline-secondary btn-lg" data-dismiss="modal" aria-hidden="true"><i class="fa fa-undo" aria-hidden="true"></i>  <spring:message  code="people.cancel"/></button>
                    <button type="submit" class="btn btn-success btn-lg float-right" id="btnSisAdministrador" data-dismiss="modal" aria-hidden="true"><i class="fa fa-plus-square-o" aria-hidden="true"></i>   <spring:message  code="people.add"/></button>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- final modal regtrar Sis Administrador--> 

<!-- Inicio modal registrar Sis Niveles Accesoss-->           
<div id="formSisNivelesAccesos" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form name="mnuSisNivelesAccesos" action="guardarSisNivelesAccesos" role="form" id="mnuSisNivelesAccesosForm" method="post">
                <div class="modal-header">
                    <p class="h3"><i class="fa fa-id-card" aria-hidden="true"></i>  <spring:message  code="functions.register.level.access"/></p>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                </div>
                <div class="modal-body">
                    <div class="form-group row">
                        <spring:message  code="functions.level.access" var="issued"/>
                        <label for="expedido" class="col-sm-3 col-form-label"><spring:message  code="functions.level.access"/></label>
                        <label for="mnuTipoEnlace" class="col-sm-1 col-form-label"><i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-8">
                            <input type="text" name="nivelAcceso" class="form-control form-control-lg"  placeholder="${issued}" />
                        </div>
                    </div>
                </div>
                <div class="modal-footer">  
                    <button class="btn btn-outline-secondary btn-lg" data-dismiss="modal" aria-hidden="true"><i class="fa fa-undo" aria-hidden="true"></i>  <spring:message  code="people.cancel"/></button>
                    <button type="submit" class="btn btn-success btn-lg float-right" id="btnSisNivelesAccesos" data-dismiss="modal" aria-hidden="true"><i class="fa fa-plus-square-o" aria-hidden="true"></i>   <spring:message  code="people.add"/></button>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- final modal regtrar Sis Administrador--> 