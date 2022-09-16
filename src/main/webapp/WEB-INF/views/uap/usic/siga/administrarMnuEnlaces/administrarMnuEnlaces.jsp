<%-- 
    Document   : administrarMnuEnlaces
    Created on : 16-07-2019, 15:44:44 AM
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
<div class="container">        <section class="content-header">
            <div class="row">
                <div class="col-md-10 mb-3">
                    <p class="h1"><i class="fa fa-users" aria-hidden="true"></i>  <spring:message code="links.title"/> </p>
                </div>
                <div class="col-sm-2 text-right" id="reporte">
                    <c:choose>
                        <c:when test="${busqueda eq 'find' }">
                            <form  method="POST" action="${urlNEnl}">
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
            <!--------------- Inicio registro nuevo enlace-->
            <c:choose>
                <c:when test="${operation eq 'reg' }">
                    <center><br>
                        <p class="h3"><i class="fa fa-newspaper-o" aria-hidden="true"> </i> <spring:message  code="links.register"/></p> 
                        <br>
                    </center>

                    <form:form modelAttribute="mnuEnlaces" action="${regEnlaces}" method="post">


                        <div class="form-group row">
                            <label for="sexo" class="col-sm-2 col-form-label"><spring:message  code="links.type.links"/></label>
                            <label class="col-sm-1 col-form-label text-right"><i class="fa fa-info-circle" aria-hidden="true"></i> </label>
                            <div class="col-sm-6">
                                <form:select class="form-control form-control-lg" path="MnuTiposEnlaces.idMnuTipoEnlace" id="tipoEnlaces">
                                    <c:forEach var="ts" items="${requestScope.lTipoEnlaces}">
                                        <form:option value="${ts.idMnuTipoEnlace}"><spring:message  code="${ts.mnuTipoEnlace}"/></form:option>
                                    </c:forEach>
                                </form:select>
                            </div>
                            <div class="col-sm-3" id="comd">
                                <small class="form-text text-muted">
                                    <a class="btn add" href="#" data-toggle="modal" data-target="#formMnuTiposEnlaces"><i class="fa fa-plus-square-o" aria-hidden="true"></i>  <spring:message  code="people.add"/></a></small>
                                <small id="emailHp" class="form-text text-muted formFieldError">
                                    <form:errors path="MnuTiposEnlaces.idMnuTipoEnlace" cssClass="alert alert-danger"/>
                                </small>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="nombres" class="col-sm-2 col-form-label"><spring:message  code="links.name"/></label>
                            <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                            <div class="col-sm-6">
                                <spring:message  code="links.name.msg" var="nom"/>
                                <form:input type="text" path="mnuEnlace"  class="form-control form-control-lg"  placeholder="${nom}"/> 
                            </div>
                            <div class="col-sm-3" id="comd">
                                <small class="form-text text-muted">
                                    <spring:message  code="links.name.msg"/>
                                </small>
                                <small class="msgError">
                                    <form:errors path="mnuEnlace" />
                                </small>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="nombres" class="col-sm-2 col-form-label"><spring:message  code="links.route"/></label>
                            <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                            <div class="col-sm-6">
                                <spring:message  code="links.route.msg" var="nom"/>
                                <form:input type="text" path="mnuRuta"  class="form-control form-control-lg"  placeholder="${nom}"/> 
                            </div>
                            <div class="col-sm-3" id="comd">
                                <small class="form-text text-muted">
                                    <spring:message  code="links.route.msg"/>
                                </small>
                                <small class="msgError">
                                    <form:errors path="mnuRuta" />
                                </small>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="nombres" class="col-sm-2 col-form-label"><spring:message  code="links.level"/></label>
                            <label class="col-sm-1 col-form-label text-right" > </label>
                            <div class="col-sm-6">
                                <spring:message  code="links.level.msg" var="nom"/>
                                <form:input type="text" path="mnuNivel"  class="form-control form-control-lg"  placeholder="${nom}"/> 
                            </div>
                            <div class="col-sm-3" id="comd">
                                <small class="form-text text-muted">
                                    <spring:message  code="links.level.msg"/>
                                </small>
                                <small class="msgError">
                                    <form:errors path="mnuNivel" />
                                </small>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="nombres" class="col-sm-2 col-form-label"><spring:message  code="links.order"/></label>
                            <label class="col-sm-1 col-form-label text-right" > </label>
                            <div class="col-sm-6">
                                <spring:message  code="links.order.msg" var="nom"/>
                                <form:input type="text" path="mnuEnlaceOrden"  class="form-control form-control-lg"  placeholder="${nom}"/> 
                            </div>
                            <div class="col-sm-3" id="comd">
                                <small class="form-text text-muted">
                                    <spring:message  code="links.order.msg"/>
                                </small>
                                <small class="msgError">
                                    <form:errors path="mnuEnlaceOrden" />
                                </small>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="nombres" class="col-sm-2 col-form-label"><spring:message  code="links.image"/></label>
                            <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                            <div class="col-sm-6">
                                <spring:message  code="links.image.msg" var="nom"/>
                                <form:input type="text" path="mnuImagen"  class="form-control form-control-lg"  placeholder="${nom}"/> 
                            </div>
                            <div class="col-sm-3" id="comd">
                                <small class="form-text text-muted">
                                    <spring:message  code="links.image.msg"/>
                                </small>
                                <small class="msgError">
                                    <form:errors path="mnuImagen" />
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
                                <a href="<spring:url value="/mnuEnlaces/formMnuEnlaces"/>" class="btn btn-yvela-green"><i class="fa fa-undo yvela" aria-hidden="true"></i>  <spring:message  code="expenses.btn.cancel"/></a>
                            </div> 
                        </div>
                    </form:form>
                    <br><br>
                    <hr align="left" noshade="noshade" size="10" width="100%" />
                </c:when>
            </c:choose>
            <!-- Final registro enlaces -->
            
            <!--------------- Inicio Modificar  enlace-->
            <c:choose>
                <c:when test="${operation eq 'mod' }">
                    <center><br>
                        <p class="h3"><i class="fa fa-newspaper-o" aria-hidden="true"> </i> <spring:message  code="links.modify"/></p> 
                        <br>
                    </center>
                    <form:form modelAttribute="mnuEnlaces" action="${urlConfMod}" method="post">
                        <form:hidden  path="idMnuEnlace" value="${mnuEnlaces.idMnuEnlace}" />

                        <div class="form-group row">
                            <label for="sexo" class="col-sm-2 col-form-label"><spring:message  code="links.type.links"/></label>
                            <label class="col-sm-1 col-form-label text-right"><i class="fa fa-info-circle" aria-hidden="true"></i> </label>
                            <div class="col-sm-6">
                                <form:select class="form-control form-control-lg" path="MnuTiposEnlaces.idMnuTipoEnlace" id="tipoEnlaces">
                                    <c:forEach var="ts" items="${requestScope.lTipoEnlaces}">
                                        <form:option value="${ts.idMnuTipoEnlace}">${ts.mnuTipoEnlace}</form:option>
                                    </c:forEach>
                                </form:select>
                            </div>
                            <div class="col-sm-3" id="comd">
                                <small class="form-text text-muted">
                                   <a class="btn add" href="#" data-toggle="modal" data-target="#formMnuTiposEnlaces"><i class="fa fa-plus-square-o" aria-hidden="true"></i>  <spring:message  code="people.add"/></a></small>
                                </small>
                                <small id="emailHp" class="form-text text-muted formFieldError">
                                    <form:errors path="MnuTiposEnlaces.idMnuTipoEnlace" cssClass="alert alert-danger"/>
                                </small>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="nombres" class="col-sm-2 col-form-label"><spring:message  code="links.name"/></label>
                            <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                            <div class="col-sm-6">
                                <spring:message  code="links.name.msg" var="nom"/>
                                <form:input type="text" path="mnuEnlace"  class="form-control form-control-lg"  placeholder="${nom}"/> 
                            </div>
                            <div class="col-sm-3" id="comd">
                                <small class="form-text text-muted">
                                    <spring:message  code="links.name.msg"/>
                                </small>
                                <small class="msgError">
                                    <form:errors path="mnuEnlace" />
                                </small>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="nombres" class="col-sm-2 col-form-label"><spring:message  code="links.route"/></label>
                            <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                            <div class="col-sm-6">
                                <spring:message  code="links.route.msg" var="nom"/>
                                <form:input type="text" path="mnuRuta"  class="form-control form-control-lg"  placeholder="${nom}"/> 
                            </div>
                            <div class="col-sm-3" id="comd">
                                <small class="form-text text-muted">
                                    <spring:message  code="links.route.msg"/>
                                </small>
                                <small class="msgError">
                                    <form:errors path="mnuRuta" />
                                </small>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="nombres" class="col-sm-2 col-form-label"><spring:message  code="links.level"/></label>
                            <label class="col-sm-1 col-form-label text-right" > </label>
                            <div class="col-sm-6">
                                <spring:message  code="links.level.msg" var="nom"/>
                                <form:input type="text" path="mnuNivel"  class="form-control form-control-lg"  placeholder="${nom}"/> 
                            </div>
                            <div class="col-sm-3" id="comd">
                                <small class="form-text text-muted">
                                    <spring:message  code="links.level.msg"/>
                                </small>
                                <small class="msgError">
                                    <form:errors path="mnuNivel" />
                                </small>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="nombres" class="col-sm-2 col-form-label"><spring:message  code="links.order"/></label>
                            <label class="col-sm-1 col-form-label text-right" > </label>
                            <div class="col-sm-6">
                                <spring:message  code="links.order.msg" var="nom"/>
                                <form:input type="text" path="mnuEnlaceOrden"  class="form-control form-control-lg"  placeholder="${nom}"/> 
                            </div>
                            <div class="col-sm-3" id="comd">
                                <small class="form-text text-muted">
                                    <spring:message  code="links.order.msg"/>
                                </small>
                                <small class="msgError">
                                    <form:errors path="mnuEnlaceOrden" />
                                </small>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="nombres" class="col-sm-2 col-form-label"><spring:message  code="links.image"/></label>
                            <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                            <div class="col-sm-6">
                                <spring:message  code="links.image.msg" var="nom"/>
                                <form:input type="text" path="mnuImagen"  class="form-control form-control-lg"  placeholder="${nom}"/> 
                            </div>
                            <div class="col-sm-3" id="comd">
                                <small class="form-text text-muted">
                                    <spring:message  code="links.image.msg"/>
                                </small>
                                <small class="msgError">
                                    <form:errors path="mnuImagen" />
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
                                <a href="<spring:url value="/mnuEnlaces/formMnuEnlaces"/>" class="btn btn-yvela-green"><i class="fa fa-undo yvela" aria-hidden="true"></i>  <spring:message  code="expenses.btn.cancel"/></a>
                            </div> 
                        </div>
                    </form:form>
                    <br><br>
                    <hr align="left" noshade="noshade" size="10" width="100%" />
                </c:when>
            </c:choose>
            <!-- Final modificar enlaces -->
            
            
                 
            <!--------------- Inicio Eliminar  enlace-->
            <c:choose>
                <c:when test="${operation eq 'elim' }">
                 <div id="" class="col-lg-12" >
                    <center><br>
                        <p class="h3"><i class="fa fa-newspaper-o" aria-hidden="true"> </i> <spring:message  code="links.remove"/></p> 
                        <br>
                    </center>
                 <div class="form-group row">
                        <div class="col-sm-8" id="combo1">   
                    <form:form modelAttribute="mnuEnlaces" action="${urlConfElimE}" method="post">
                        <form:hidden  path="idMnuEnlace" value="${mnuEnlaces.idMnuEnlace}" />
                       
                          <div class="form-group row">  
                            <label for="sexo" class="col-sm-4 col-form-label"><spring:message  code="links.type.links"/></label>
                            <div class="col-sm-8">
                                <form:select class="form-control form-control-lg" path="MnuTiposEnlaces.idMnuTipoEnlace" id="tipoEnlaces" readonly="true">
                                    <c:forEach var="ts" items="${requestScope.lTipoEnlaces}">
                                        <form:option value="${ts.idMnuTipoEnlace}">${ts.mnuTipoEnlace}</form:option>
                                    </c:forEach>
                                </form:select>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="nombres" class="col-sm-4 col-form-label"><spring:message  code="links.name"/></label>
                            <div class="col-sm-8">
                                <spring:message  code="links.name.msg" var="nom"/>
                                <form:input type="text" path="mnuEnlace"  class="form-control form-control-lg"  placeholder="${nom}" readonly="true"/> 
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="nombres" class="col-sm-4 col-form-label"><spring:message  code="links.route"/></label>
                            <div class="col-sm-8">
                                <spring:message  code="links.route.msg" var="nom"/>
                                <form:input type="text" path="mnuRuta"  class="form-control form-control-lg"  placeholder="${nom}" readonly="true"/> 
                            </div>
                           
                        </div>
                        <div class="form-group row">
                            <label for="nombres" class="col-sm-4 col-form-label"><spring:message  code="links.level"/></label>
                            <div class="col-sm-8">
                                <spring:message  code="links.level.msg" var="nom"/>
                                <form:input type="text" path="mnuNivel"  class="form-control form-control-lg"  placeholder="${nom}" readonly="true"/> 
                            </div>
                           
                        </div>
                        <div class="form-group row">
                            <label for="nombres" class="col-sm-4 col-form-label"><spring:message  code="links.order"/></label>
                            <div class="col-sm-8">
                                <spring:message  code="links.order.msg" var="nom"/>
                                <form:input type="text" path="mnuEnlaceOrden"  class="form-control form-control-lg"  placeholder="${nom}" readonly="true"/> 
                            </div>
                           
                        </div>
                        <div class="form-group row">
                            <label for="nombres" class="col-sm-4 col-form-label"><spring:message  code="links.image"/></label>
                            <div class="col-sm-8">
                                <spring:message  code="links.image.msg" var="nom"/>
                                <form:input type="text" path="mnuImagen"  class="form-control form-control-lg"  placeholder="${nom}" readonly="true"/> 
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
                                <a href="<spring:url value="/mnuEnlaces/formMnuEnlaces"/>" class="btn btn-yvela-green"><i class="fa fa-undo yvela" aria-hidden="true"></i>  <spring:message  code="expenses.btn.cancel"/></a>
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

            <!--Tabla lista de Todas las Personas Registradas -->
            <c:choose>
                <c:when test="${busqueda eq 'find' }">
                    <center><br>
                        <p class="h3"><i class="fa fa-list-alt" aria-hidden="true"></i> <spring:message  code="links.details"/></p> 
                        <br>
                    </center>
                    <table id="tEnlaces"  class="table" style="width:100%">
                        <thead>
                            <tr>
                                <th><spring:message  code="links.name"/></th>
                                <th><spring:message  code="links.route"/></th>
                                <th><spring:message  code="links.level"/></th>
                                <th><spring:message  code="links.order"/></th>
                                <th><spring:message  code="links.image"/></th>
                                <th><spring:message  code="expenses.modify"/> </th>
                                <th><spring:message  code="expenses.remove"/></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:set var="cont" value="0" />
                            <c:forEach items="${lMnuEnlaces}" var="e">
                                <c:set var="cont" value="${cont+1}" />
                                <tr>
                                    <td><spring:message  code="${e.mnuEnlace}"/></td>
                                    <td>${e.mnuRuta}</td>
                                    <td>${e.mnuNivel}</td>
                                    <td>${e.mnuEnlaceOrden}</td>
                                    <td>${e.mnuImagen}</td>
                                    <td>
                                        <form  method="post" action="${urlModEnl}">
                                            <input type="hidden" name="idMnuEnlace" value="${e.idMnuEnlace}">
                                            <span class="icon-input-btn"> <i class="fa fa-pencil-square-o yvela" aria-hidden="true"> </i> <input class="btn btn-success" type="submit"  value="<spring:message  code="expenses.modify"/>" > </span>
                                        </form>
                                    </td>
                                    <td>
                                        <form name="Delet" action="${urlElimEnl}" method="post">
                                            <input type="hidden" name="idMnuEnlace" value="${e.idMnuEnlace}">
                                            <span class="icon-input-btn"> <i class="fa fa-times yvela" aria-hidden="true"> </i> <input class="btn btn-danger" type="submit"  value="<spring:message  code="expenses.remove"/>"> </span>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                        <tfoot>
                            <tr>
                                <th><spring:message  code="links.name"/></th>
                                <th><spring:message  code="links.route"/></th>
                                <th><spring:message  code="links.level"/></th>
                                <th><spring:message  code="links.order"/></th>
                                <th><spring:message  code="links.image"/></th>
                                <th><spring:message  code="expenses.modify"/> </th>
                                <th><spring:message  code="expenses.remove"/></th>
                            </tr>
                        </tfoot>
                    </table>
                    <br><br>
                    <hr align="left" noshade="noshade" size="10" width="100%" />
                </c:when>
            </c:choose>
            <!-- Final Tabla Lista Enlaces-->
        </section>
    </div>
             <!-- Final Tabla Lista Enlaces-->
             
 <!-- Inicio modal registrar tipos enlaces-->           
<div id="formMnuTiposEnlaces" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form name="mnuTiposEnlaces" action="guardarMnuTiposEnlaces" role="form" id="mnuTiposEnlacesForm" method="post">
                <div class="modal-header">
                    <p class="h3"><i class="fa fa-id-card" aria-hidden="true"></i>  <spring:message  code="people.new.issued"/></p>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                </div>
                <div class="modal-body">
                    <div class="form-group row">
                        <spring:message  code="links.type.links" var="issued"/>
                        <label for="expedido" class="col-sm-3 col-form-label"><spring:message  code="links.type.links"/></label>
                        <label for="mnuTipoEnlace" class="col-sm-1 col-form-label"><i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-8">
                            <input type="text" name="mnuTipoEnlace" class="form-control form-control-lg"  placeholder="${issued}" />
                        </div>
                    </div>
                    <div class="form-group row">
                        <spring:message  code="links.order" var="initials"/>
                        <label for="espedidoSigla" class="col-sm-3 col-form-label"><spring:message  code="links.order"/></label>
                        <label for="ciExpedidoSigla" class="col-sm-1 col-form-label"><i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-8">
                            <input type="text" name="mnuTipoEnlaceOrden" class="form-control form-control-lg"  placeholder="${initials}" />
                        </div>
                    </div>
                      <div class="form-group row">
                        <spring:message  code="links.image" var="initials"/>
                        <label for="espedidoSigla" class="col-sm-3 col-form-label"><spring:message  code="links.image"/></label>
                        <label for="ciExpedidoSigla" class="col-sm-1 col-form-label"><i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-8">
                            <input type="text" name="mnuImagen" class="form-control form-control-lg"  placeholder="${initials}" />
                        </div>
                    </div>
                </div>
                <div class="modal-footer">  
                    <button class="btn btn-outline-secondary btn-lg" data-dismiss="modal" aria-hidden="true"><i class="fa fa-undo" aria-hidden="true"></i>  <spring:message  code="people.cancel"/></button>
                    <button type="submit" class="btn btn-success btn-lg float-right" id="btnMnuTiposEnlaces" data-dismiss="modal" aria-hidden="true"><i class="fa fa-plus-square-o" aria-hidden="true"></i>   <spring:message  code="people.add"/></button>
                </div>
            </form>
        </div>
    </div>
</div>

