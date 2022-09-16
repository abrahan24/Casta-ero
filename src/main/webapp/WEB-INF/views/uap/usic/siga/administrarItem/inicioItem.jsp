<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<head>
    <link rel="stylesheet" type="text/css"  href=" <c:url value="/resource/admItems/items.css"/>" />
    <script src="<spring:url value="/resource/admItems/items.js" />" type="text/javascript"></script>
</head>
<div class="container">
    <section class="content-header">
        <div class="row">
            <div class="col-md-10 mb-3">
                <p class="h1"><i class="fa fa-users" aria-hidden="true"></i>  <spring:message code="item.form.item"/> </p>
            </div>
            <div class="col-sm-2 text-right" id="nuevo">
                <c:choose>
                    <c:when test="${busqueda eq 'find' }">
                        <form  method="POST" action="${urlNuevoI}">
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

        <!------------------------- Inicio nuevo ITEM ----------------------------->
        <c:choose>
            <c:when test="${operation eq 'reg' }">
                <center><br>
                    <p class="h3"><i class="fa fa-newspaper-o" aria-hidden="true"> </i> <spring:message  code="items.title"/></p> 
                    <br>
                </center>
                <spring:url var="formUrl" value="signupsss" />
                <form:form  modelAttribute="pnlPersonalAdministrativos" action="${url}" method="post" >
                    <div class="form-group row">
                        <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="login.people"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6" id="combo1"> 
                            <form:select class="form-control form-control-lg" path="Personas.idPersona" id="sPersonas">
                                <c:forEach var="p" items="${requestScope.lPersonas}">
                                    <form:option value="${p.idPersona}">${p.nombres} ${p.paterno} ${p.materno}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                            <small id="emailHelp" class="form-text text-muted">
                                <spring:message  code="info.login.people"/>
                            </small>
                            <small class="msgError">
                                <c:choose>
                                    <c:when test="${mensage eq 'err' }">
                                        <i class="fa fa-exclamation-triangle" aria-hidden="true"></i>  <spring:message  code="items.error.register"/>
                                    </c:when>
                                </c:choose> 
                            </small>

                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="items.charges"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6" id="combod"> 
                            <form:select class="form-control form-control-lg" path="PnlCargos.idPnlCargos" id="sCargos">
                                <option disabled selected value="" hidden>SELECCIONE UNA CARGOS</option>
                                <c:forEach var="c" items="${requestScope.lCargos}">
                                    <form:option value="${c.idPnlCargos}">${c.cargo}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                            <small id="emailHelp" class="form-text text-muted">
                                <a class="btn add" href="#" data-toggle="modal" data-target="#formPnlCargos"><i class="fa fa-plus-square-o" aria-hidden="true"></i>  <spring:message  code="people.add"/></a>
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="items.typemanager"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6" id="combo1"> 
                            <form:select class="form-control form-control-lg" path="PnlTiposAdministrativos.idPnlTipoAdtministrativo" id="tipoAdmin">
                                <option disabled selected value="" hidden>SELECCIONE UN ADMINISTRATIVO</option>
                                <c:forEach var="p" items="${requestScope.lTiposAdministrativoss}">
                                    <form:option value="${p.idPnlTipoAdtministrativo}">${p.tipoAdministrativo}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                            <small id="emailHelp" class="form-text text-muted">
                                <a class="btn add" href="#" data-toggle="modal" data-target="#formPnlTipoAdmin"><i class="fa fa-plus-square-o" aria-hidden="true"></i>  <spring:message  code="people.add"/></a></small>
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="items.item"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6" id="combo1"> 
                            <form:select class="form-control form-control-lg" path="PnlItems.idPnlItem" id="sItems" >
                                <option disabled selected value="" hidden>SELECCIONE UN ITEMS</option>
                                <c:forEach var="p" items="${requestScope.lItemss}">
                                    <form:option value="${p.idPnlItem}">${p.item}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                            <small id="emailHelp" class="form-text text-muted">
                                <a class="btn add" href="#" data-toggle="modal" data-target="#formPnlItems"><i class="fa fa-plus-square-o" aria-hidden="true"></i>  <spring:message  code="people.add"/></a></small>
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="sede.sede"/></label>
                        <label class="col-sm-1 col-form-label text-right" ><i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6" id="combop"> 
                            <select class="form-sedes form-control form-control-lg" name="idSede" id="idSede" >
                                <option disabled selected value="" hidden>SELECCIONE UNA SEDE</option>
                                <c:forEach var="l" items="${requestScope.lSedes}">
                                    <option value="${l.idSede}">${l.sede}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                            <small id="emailHp" class="form-text text-muted">
                                <a class="btn add" href="#" data-toggle="modal" data-target="#formInsSedes"><i class="fa fa-plus-square-o" aria-hidden="true"></i>  <spring:message  code="people.add"/></a>
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="report.cash.box.unity.direction"/></label>
                        <label class="col-sm-1 col-form-label text-right" ><i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6" id="comboDireccion"> 
                            <select class="form-sedes form-control form-control-lg" name="idDireccionFuncional"  id="dFuncional" >
                                <option disabled selected value="" hidden>SELECCIONE UN ITEM</option>
                                <c:forEach var="df" items="${requestScope.lDireccionesItem}">
                                    <option value="${df.idDireccionFuncional}">${df.direccionFuncional}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                            <small id="emailHelp" class="form-text text-muted">
                                <a class="btn add" href="#" data-toggle="modal" data-target="#formInsDireccionesFunc"><i class="fa fa-plus-square-o" aria-hidden="true"></i>  <spring:message  code="people.add"/></a>
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="units.unit"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6" id="combo1"> 
                            <form:select class="form-control form-control-lg" path="InsUnidadesFuncionales.idUnidadFuncional" id="unidad">
                                <option disabled selected value="" hidden>SELECCIONE UNA UNIDAD</option>
                                <c:forEach var="u" items="${requestScope.lUnidadesItem}">
                                    <form:option value="${u.idUnidadFuncional}">${u.unidadFuncional}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                            <small id="emailHelp" class="form-text text-muted">
                                <a class="btn add" href="#" data-toggle="modal" data-target="#formUnidades"><i class="fa fa-plus-square-o" aria-hidden="true"></i>  <spring:message  code="people.add"/></a></small>
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <spring:message  code="items.year" var="year"/>
                        <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="items.year"/></label>
                        <label class="col-sm-1 col-form-label text-right" > </label>
                        <div class="col-sm-6" id="combo1"> 
                            <form:input class="form-control form-control-lg" path="gestion"  id="gestion"  placeholder="${year}" value="${gestion}" />
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                            <small id="emailHelp" class="form-text text-muted">
                                <spring:message  code="items.year.msg"/>
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <spring:message  code="items.period.msg" var="period"/>
                        <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="items.period"/></label>
                        <label class="col-sm-1 col-form-label text-right" > </label>
                        <div class="col-sm-6" id="combo1"> 
                            <form:input class="form-control form-control-lg" path="periodo"  id="periodo"  placeholder="${period}" value="${periodo}" />
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                            <small id="emailHelp" class="form-text text-muted">
                                <spring:message  code="items.period.msg" />
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <spring:message  code="items.start.date" var="startD"/>
                        <label for="sedes" class="col-sm-2 col-form-label"> <spring:message  code="items.start.date"/></label>
                        <label class="col-sm-1 col-form-label text-right" > </label>
                        <div class="col-sm-6" id="combo1"> 
                            <form:input type="Date" class="form-control form-control-lg" path="fecInicio"  id="fInicio"  placeholder="${startD}" />
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                            <small id="emailHelp" class="form-text text-muted">
                                <spring:message  code="items.start.date.msg"/>
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <spring:message  code="items.finish.date" var="finish"/>
                        <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="items.finish.date"/></label>
                        <label class="col-sm-1 col-form-label text-right" > </label>
                        <div class="col-sm-6"> 
                            <form:input type="Date" class="form-control form-control-lg" path="fecFinal"  id="fInicio"  placeholder="${finish}" />
                        </div>
                        <div class="col-sm-3"> 
                            <small id="emailHelp" class="form-text text-muted">
                                <spring:message  code="items.finish.date.msg"/>
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
                            <a href="<spring:url value="/items/inicioItem"/>" class="btn btn-yvela-green"><i class="fa fa-undo" aria-hidden="true"></i>  <spring:message  code="expenses.btn.cancel"/></a>
                        </div>
                    </div>
                </form:form> 
            </c:when>
        </c:choose>
        <!------------------------- Final nuevo ITEM ----------------------------->


        <!------------------------- Inicio Modificacion Item ----------------------------->
        <c:choose>
            <c:when test="${operation eq 'modI' }">
                <center><br>
                    <p class="h3"><i class="fa fa-pencil-square-o" aria-hidden="true"></i> <spring:message  code="item.form.modify"/></p> 
                    <br>
                </center>
                <spring:url var="formUrl" value="signupsss" />
                <form:form  modelAttribute="pnlPersonalAdministrativos" action="${urlConfModItem}" method="post" >

                    <form:hidden  path="idPnlPersonalAdministrativo" value="${pnlPersonalAdministrativos.idPnlPersonalAdministrativo}"/>
                    <div class="form-group row">
                        <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="login.people"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>

                        <div class="col-sm-6" id="combo1"> 
                            <form:select class="form-control form-control-lg" path="Personas.idPersona" id="sPersonas">
                                <c:forEach var="p" items="${requestScope.lPersonas}">
                                    <form:option value="${p.idPersona}">${p.nombres} ${p.paterno} ${p.materno}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>

                        <div class="col-sm-3" id="combo1"> 
                            <small id="emailHelp" class="form-text text-muted">
                                <spring:message  code="info.login.people"/>
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="items.charges"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6" id="combo1"> 
                            <form:select class="form-control form-control-lg" path="PnlCargos.idPnlCargos" id="sCargos">
                                <c:forEach var="c" items="${requestScope.lCargos}">
                                    <form:option value="${c.idPnlCargos}">${c.cargo}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                            <small id="emailHelp" class="form-text text-muted">
                                <a class="btn add" href="#" data-toggle="modal" data-target="#formPnlCargos"><i class="fa fa-plus-square-o" aria-hidden="true"></i>  <spring:message  code="people.add"/></a></small>
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="items.typemanager"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6" id="combo1"> 
                            <form:select class="form-control form-control-lg" path="PnlTiposAdministrativos.idPnlTipoAdtministrativo"  id="tipoAdmin"> 
                                <c:forEach var="p" items="${requestScope.lTiposAdministrativoss}">
                                    <form:option value="${p.idPnlTipoAdtministrativo}">${p.tipoAdministrativo}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                            <small id="emailHelp" class="form-text text-muted">
                                <a class="btn add" href="#" data-toggle="modal" data-target="#formPnlTipoAdmin"><i class="fa fa-plus-square-o" aria-hidden="true"></i>  <spring:message  code="people.add"/></a></small>
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="items.item"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6" id="combo1"> 
                            <form:select class="form-control form-control-lg" path="PnlItems.idPnlItem" id="sItems" >
                                <c:forEach var="p" items="${requestScope.lItemss}">
                                    <form:option value="${p.idPnlItem}">${p.item}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                            <small id="emailHelp" class="form-text text-muted">
                                <a class="btn add" href="#" data-toggle="modal" data-target="#formPnlItems"><i class="fa fa-plus-square-o" aria-hidden="true"></i>  <spring:message  code="people.add"/></a>
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="sede.sede"/></label>
                        <label class="col-sm-1 col-form-label text-right" ></label>
                        <div class="col-sm-6" id="combop"> 
                            <select class="form-sedes form-control form-control-lg" name="idSede" id="idSede" >
                                <c:forEach var="l" items="${requestScope.lSedes}">
                                    <option value="${l.idSede}">${l.sede}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                            <small id="emailHp" class="form-text text-muted">
                                <a class="btn add" href="#" data-toggle="modal" data-target="#formInsSedes"><i class="fa fa-plus-square-o" aria-hidden="true"></i>  <spring:message  code="people.add"/></a>
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="report.cash.box.unity.direction"/></label>
                        <label class="col-sm-1 col-form-label text-right" ></label>
                        <div class="col-sm-6" id="comboDireccion"> 
                            <select class="form-sedes form-control form-control-lg" name="idDireccionFuncional"  id="dFuncional" >
                                <c:forEach var="df" items="${requestScope.lDireccionesItem}">
                                    <option value="${df.idDireccionFuncional}">${df.direccionFuncional}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                            <small id="emailHelp" class="form-text text-muted">
                                <a class="btn add" href="#" data-toggle="modal" data-target="#formUnidades"><i class="fa fa-plus-square-o" aria-hidden="true"></i>  <spring:message  code="people.add"/></a>
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="units.unit"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6" id="combo1"> 
                            <form:select class="form-control form-control-lg" path="InsUnidadesFuncionales.idUnidadFuncional" id="unidad">
                                <c:forEach var="u" items="${requestScope.lUnidadesFunc}">
                                    <form:option value="${u.idUnidadFuncional}">${u.unidadFuncional}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                            <small id="emailHelp" class="form-text text-muted">
                                <a class="btn add" href="#" data-toggle="modal" data-target="#formUnidades"><i class="fa fa-plus-square-o" aria-hidden="true"></i>  <spring:message  code="people.add"/></a></small>
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <spring:message  code="items.year" var="year"/>
                        <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="items.year"/></label>
                        <label class="col-sm-1 col-form-label text-right" > </label>
                        <div class="col-sm-6" id="combo1"> 
                            <form:input class="form-control form-control-lg" path="gestion"  id="gestion"  placeholder="${year}"  />
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                            <small id="emailHelp" class="form-text text-muted">
                                <spring:message  code="items.year.msg"/>
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <spring:message  code="items.period.msg" var="period"/>
                        <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="items.period"/></label>
                        <label class="col-sm-1 col-form-label text-right" > </label>
                        <div class="col-sm-6" id="combo1"> 
                            <form:input class="form-control form-control-lg" path="periodo"  id="periodo"  placeholder="${period}"  />
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                            <small id="emailHelp" class="form-text text-muted">
                                <spring:message  code="items.period.msg" />
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <spring:message  code="items.start.date" var="startD"/>
                        <label for="sedes" class="col-sm-2 col-form-label"> <spring:message  code="items.start.date"/></label>
                        <label class="col-sm-1 col-form-label text-right" > </label>
                        <div class="col-sm-6" id="combo1"> 
                            <form:input type="Date" class="form-control form-control-lg" path="fecInicio"  id="fInicio"  placeholder="${startD}" />
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                            <small id="emailHelp" class="form-text text-muted">
                                <spring:message  code="items.start.date.msg"/>
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <spring:message  code="items.finish.date" var="finish"/>
                        <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="items.finish.date"/></label>
                        <label class="col-sm-1 col-form-label text-right" > </label>
                        <div class="col-sm-6"> 
                            <form:input type="Date" class="form-control form-control-lg" path="fecFinal"  id="fInicio"  placeholder="${finish}" />
                        </div>
                        <div class="col-sm-3"> 
                            <small id="emailHelp" class="form-text text-muted">
                                <spring:message  code="items.finish.date.msg"/>
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
                            <a href="<spring:url value="/items/inicioItem"/>" class="btn btn-yvela-green"><i class="fa fa-undo" aria-hidden="true"></i>  <spring:message  code="expenses.btn.cancel"/></a>
                        </div>
                    </div>

                </form:form>
            </c:when>
        </c:choose>
        <br>
        <div id="result"></div>
        <!---------------------------------- Final Modificacion ITEM ------------------------------------------>

        <!------------------------- Inicio Eliminacion Item ----------------------------->
        <c:choose>
            <c:when test="${operation eq 'eliminarI' }">
                <div id="" class="col-lg-12" >
                    <center><br>
                        <p class="h3"><i class="fa fa-user-times" aria-hidden="true"> </i> <spring:message  code="item.form.delete"/></p> 
                        <br>
                    </center>
                    <div class="form-group row">
                        <div class="col-sm-8" id="combo1">   
                            <spring:url var="formUrl" value="signupsss" />
                            <form:form  modelAttribute="pnlPersonalAdministrativos" action="${urlConfElimI}" method="post" >

                                <form:hidden  path="idPnlPersonalAdministrativo" value="${pnlPersonalAdministrativos.idPnlPersonalAdministrativo}"/>
                                <div class="form-group row">
                                    <label for="sedes" class="col-sm-4 col-form-label"><spring:message  code="login.people"/></label>
                                    <div class="col-sm-8" id="combo1"> 
                                        <form:select class="form-control form-control-lg"  path="Personas.idPersona" readonly="true">
                                            <option disabled selected value="" hidden>SELECCIONE UNA PERSONA</option>
                                            <c:forEach var="p" items="${requestScope.lPersonas}">
                                                <form:option value="${p.idPersona}">${p.nombres} ${p.paterno} ${p.materno}</form:option>
                                            </c:forEach>
                                        </form:select>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="sedes" class="col-sm-4 col-form-label"><spring:message  code="items.charges"/></label>
                                    <div class="col-sm-8" id="combo1"> 
                                        <form:select class="form-control form-control-lg"  path="PnlCargos.idPnlCargos" readonly="true">
                                            <option disabled selected value="" hidden>SELECCIONE UN CARGO</option>
                                            <c:forEach var="c" items="${requestScope.lCargos}">
                                                <form:option value="${c.idPnlCargos}">${c.cargo}</form:option>
                                            </c:forEach>
                                        </form:select>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="sedes" class="col-sm-4 col-form-label"><spring:message  code="items.typemanager"/></label>
                                    <div class="col-sm-8" id="combo1"> 
                                        <form:select class="form-control form-control-lg"  path="PnlTiposAdministrativos.idPnlTipoAdtministrativo" readonly="true" >
                                            <option disabled selected value="" hidden>SELECCIONE UN ADMINISTRATIVO</option>
                                            <c:forEach var="p" items="${requestScope.lTiposAdministrativoss}">
                                                <form:option value="${p.idPnlTipoAdtministrativo}">${p.tipoAdministrativo}</form:option>
                                            </c:forEach>
                                        </form:select>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="sedes" class="col-sm-4 col-form-label"><spring:message  code="items.item"/></label>
                                    <div class="col-sm-8" id="combo1"> 
                                        <form:select class="form-control form-control-lg"  path="PnlItems.idPnlItem" readonly="true">
                                            <option disabled selected value="" hidden>SELECCIONE UNA ITEM</option>
                                            <c:forEach var="p" items="${requestScope.lItemss}">
                                                <form:option value="${p.idPnlItem}">${p.item}</form:option>
                                            </c:forEach>
                                        </form:select>
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <label for="sedes" class="col-sm-4 col-form-label"><spring:message  code="sede.sede"/></label>
                                    <div class="col-sm-8" id="combop"> 
                                        <select class="form-sedes form-control form-control-lg" name="idSede" id="idSede" >
                                            <option disabled selected value="" hidden>SELECCIONE UNA SEDE</option>
                                            <c:forEach var="l" items="${requestScope.lSedes}">
                                                <option value="${l.idSede}">${l.sede}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="sedes" class="col-sm-4 col-form-label"><spring:message  code="report.cash.box.unity.direction"/></label>
                                    <div class="col-sm-8" id="comboDireccion"> 
                                        <select class="form-sedes form-control form-control-lg" name="idDireccionFuncional"  id="dFuncional" >
                                            <option disabled selected value="" hidden>SELECCIONE UNA DIRECCION</option>
                                            <c:forEach var="df" items="${requestScope.lDireccionesItem}">
                                                <option value="${df.idDireccionFuncional}">${df.direccionFuncional}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="sedes" class="col-sm-4 col-form-label"><spring:message  code="units.unit"/></label>
                                    <div class="col-sm-8" id="combo1"> 
                                        <form:select class="form-control form-control-lg" path="InsUnidadesFuncionales.idUnidadFuncional" id="unidad">
                                            <option disabled selected value="" hidden>SELECCIONE UNA UNIDAD</option>
                                            <c:forEach var="u" items="${requestScope.lUnidadesFunc}">
                                                <form:option value="${u.idUnidadFuncional}">${u.unidadFuncional}</form:option>
                                            </c:forEach>
                                        </form:select>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <spring:message  code="items.year" var="year"/>
                                    <label for="sedes" class="col-sm-4 col-form-label"><spring:message  code="items.year"/></label>
                                    <div class="col-sm-8" id="combo1"> 
                                        <form:input class="form-control form-control-lg" readonly="true" path="gestion"  id="gestion"  placeholder="${year}" value="${gestion}" />
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <spring:message  code="items.period.msg" var="period"/>
                                    <label for="sedes" class="col-sm-4 col-form-label"><spring:message  code="items.period"/></label>
                                    <div class="col-sm-8" id="combo1"> 
                                        <form:input class="form-control form-control-lg" readonly="true" path="periodo"  id="periodo"  placeholder="${period}" value="${periodo}" />
                                    </div>

                                </div>
                                <div class="form-group row">
                                    <spring:message  code="items.start.date" var="startD"/>
                                    <label for="sedes" class="col-sm-4 col-form-label"> <spring:message  code="items.start.date"/></label>
                                    <div class="col-sm-8" id="combo1"> 
                                        <form:input type="Date" class="form-control form-control-lg" readonly="true" path="fecInicio"  id="fInicio"  placeholder="${startD}" />
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <spring:message  code="items.finish.date" var="finish"/>
                                    <label for="sedes" class="col-sm-4 col-form-label"><spring:message  code="items.finish.date"/></label>
                                    <div class="col-sm-8"> 
                                        <form:input type="Date" class="form-control form-control-lg" readonly="true" path="fecFinal"  id="fInicio"  placeholder="${finish}" />
                                    </div>
                                </div>
                                <hr align="left" noshade="noshade" size="10" width="100%" />
                                <div class="form-group row mt-12">
                                    <div class="col-sm-3" id="combo1"> 
                                    </div>
                                    <div class="col-sm-3" id="combo1">
                                        <span class="icon-input-btn"><i class="fa fa-times yvela" aria-hidden="true"></i> 
                                            <c:if  test="${banderita == 1}">  
                                                <input type="submit" disabled="true" class="btn btn-danger" value="<spring:message  code="expenses.btn.confirm"/>"> </span>
                                            </c:if>
                                            <c:if  test="${banderita == 0}">  
                                            <input type="submit"  class="btn btn-danger" value="<spring:message  code="expenses.btn.confirm"/>">
                                        </c:if>
                                    </div>
                                    <div class="col-sm-3" id="combo1"> 
                                    </div>
                                    <div class="col-sm-3" id="combo1"> 
                                        <a href="<spring:url value="/items/inicioItem"/>" class="btn btn-yvela-green"><i class="fa fa-undo" aria-hidden="true"></i>  <spring:message  code="expenses.btn.cancel"/></a>
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
                                <p></P>
                            </fieldset>
                        </div>
                    </div>
                </div>
                <hr align="left" noshade="noshade" size="10" width="100%" />
            </c:when>
        </c:choose>

        <!---------------------------------- Final Eliminacion ITEM ------------------------------------------>

        <!--Tabla lista de Todos los registros del Personal Administrativo -->
        <c:choose>
            <c:when test="${busqueda eq 'find' }">
                <center>
                    <p class="h3"><i class="fa fa-list-alt" aria-hidden="true"></i> <spring:message  code="item.list.title"/></p> 
                    <br><br>
                </center>
                <table  id="tItems" class="table" style="width:100%">
                    <thead>
                        <tr>
                            <th><spring:message  code="menu.item"/></th>
                            <th><spring:message  code="cargo.cargo"/></th>
                            <th><spring:message  code="units.unit"/></th>
                            <th><spring:message  code="institution.headquarters"/></th>
                            <th><spring:message  code="ingreso.gestion"/></th>
                            <th><spring:message  code="expenses.modify"/> </th>
                            <th><spring:message  code="expenses.remove"/></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:set var="cont" value="0" />
                        <c:forEach items="${lAdministrativos}" var="pa">
                            <c:set var="cont" value="${cont+1}" />
                            <tr height="40">
                                <td>${pa.personas.nombres} ${pa.personas.paterno} ${pa.personas.materno}</td>
                                <td>${pa.pnlCargos.cargo}</td>
                                <td>${pa.insUnidadesFuncionales.unidadFuncional}</td>
                                <td>${pa.insUnidadesFuncionales.insDireccionesFuncionales.insSedes.sede}</td>
                                <td>${pa.gestion}</td>
                                <td>
                                    <form  method="post" action="${urlInicioModItem}">
                                        <input type="hidden" name="idPnlPersonalAdministrativo" value="${pa.idPnlPersonalAdministrativo}">
                                        <span class="icon-input-btn"> <i class="fa fa-pencil-square-o yvela" aria-hidden="true"> </i> <input class="btn btn-success" type="submit"  value="<spring:message  code="expenses.modify"/>" > </span>
                                    </form>
                                </td>
                                <td>
                                    <form name="Delet" action="${urlInicioElimI}" method="post">
                                        <input type="hidden" name="idPnlPersonalAdministrativo" value="${pa.idPnlPersonalAdministrativo}">
                                        <span class="icon-input-btn"> <i class="fa fa-times yvela" aria-hidden="true"> </i> <input class="btn btn-danger" type="submit"  value="<spring:message  code="expenses.remove"/>"> </span>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                    <tfoot>
                    </tfoot>
                </table>
                <br>
                <hr align="left" noshade="noshade" size="10" width="100%" />
                <br>
            </c:when>
        </c:choose>
        <!--Tabla lista de Todos los registros del Personal Administrativo -->
    </section>
</div>     
<div id="formPnlCargos" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form name="pnlCatgoForm" action="guardarPnlCargo" role="form" id="cargoForm" method="post">
                <div class="modal-header">
                    <p class="h3"><i class="fa fa-user-secret" aria-hidden="true"></i>  <spring:message  code="items.charges.new"/></p>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                </div>
                <div class="modal-body">
                    <div class="form-group row">
                        <spring:message  code="items.charges.msg" var="charge"/>
                        <label for="cargo" class="col-sm-3 col-form-label"><spring:message  code="items.charges"/></label>
                        <label for="tipoSexo" class="col-sm-1 col-form-label"><i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-8">
                            <input type="text" name="cargo" class="form-control form-control-lg"  placeholder="${charge}" />
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-outline-secondary btn-lg" data-dismiss="modal" aria-hidden="true"><i class="fa fa-undo" aria-hidden="true"></i>  <spring:message  code="people.cancel"/></button>
                    <button type="submit" class="btn btn-success btn-lg float-right" id="btnCargo" data-dismiss="modal" aria-hidden="true"><i class="fa fa-plus-square-o" aria-hidden="true"></i>   <spring:message  code="people.add"/></button>
                </div>
            </form>
        </div>
    </div>
</div>


<div id="formPnlTipoAdmin" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form name="pnlTipoAdminForm" action="guardarPnlTipoAdministrativo" role="form" id="tipoAdminForm" method="post">
                <div class="modal-header">
                    <p class="h3"><i class="fa fa-user-secret" aria-hidden="true"></i>  <spring:message  code="items.typemanager.new"/></p>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                </div>
                <div class="modal-body">
                    <div class="form-group row">
                        <spring:message  code="items.typemanager.msg" var="typeAdm"/>
                        <label for="tipoAd" class="col-sm-3 col-form-label"><spring:message  code="items.typemanager"/></label>
                        <label for="tipoAdm" class="col-sm-1 col-form-label"><i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-8">
                            <input type="text" name="tipoAdministrativo" class="form-control form-control-lg"  placeholder="${typeAdm}" />
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-outline-secondary btn-lg" data-dismiss="modal" aria-hidden="true"><i class="fa fa-undo" aria-hidden="true"></i>  <spring:message  code="people.cancel"/></button>
                    <button type="submit" class="btn btn-success btn-lg float-right" id="btnTipoAdmin" data-dismiss="modal" aria-hidden="true"><i class="fa fa-plus-square-o" aria-hidden="true"></i>   <spring:message  code="people.add"/></button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="formPnlItems" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form name="pnlItemsForm" action="guardarPnlItems" role="form" id="itemForm" method="post">
                <div class="modal-header">
                    <p class="h3"><i class="fa fa-user-secret" aria-hidden="true"></i>  <spring:message  code="items.item.new"/></p>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                </div>
                <div class="modal-body">
                    <div class="form-group row">
                        <spring:message  code="items.item.msg" var="items"/>
                        <label for="item" class="col-sm-3 col-form-label"><spring:message  code="items.item"/></label>
                        <label for="items" class="col-sm-1 col-form-label"><i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-8">
                            <input type="text" name="item" class="form-control form-control-lg"  placeholder="${items}" />
                        </div>
                    </div>
                    <div class="form-group row">
                        <spring:message  code="items.item.code.msg" var="sigla"/>
                        <label for="cargo" class="col-sm-3 col-form-label"><spring:message  code="items.item.code"/></label>
                        <label for="tipoSexo" class="col-sm-1 col-form-label"><i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-8">
                            <input type="text" name="codigoItem" class="form-control form-control-lg"  placeholder="${sigla}" />
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-outline-secondary btn-lg" data-dismiss="modal" aria-hidden="true"><i class="fa fa-undo" aria-hidden="true"></i>  <spring:message  code="people.cancel"/></button>
                    <button type="submit" class="btn btn-success btn-lg float-right" id="btnPnlItems" data-dismiss="modal" aria-hidden="true"><i class="fa fa-plus-square-o" aria-hidden="true"></i>   <spring:message  code="people.add"/></button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- registras sedes -->             
<div id="formInsSedes" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form:form name="insSedesForm" modelAttribute="insSedes" action="guardarinsSedes" role="form" id="itemForm" method="post">
                <div class="modal-header">
                    <p class="h3"><i class="fa fa-user-secret" aria-hidden="true"></i>  <spring:message  code="items.headquarters.new"/></p>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                </div>
                <div class="modal-body">
                    <div class="form-group row">
                        <spring:message  code="institution.institution" var="sigla"/>
                        <label for="cItem" class="col-sm-3 col-form-label"><spring:message  code="institution.institution"/></label>
                        <label for="cItems" class="col-sm-1 col-form-label"><i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-8">
                        	<option disabled selected value="" hidden>SELECCIONE UNA SEDE</option>
                            <form:select class="form-institution form-control form-control-lg" path="Instituciones.idInstitucion" id="idInstitucion">
                                <c:forEach var="l" items="${requestScope.lInstituciones}">
                                    <form:option value="${l.idInstitucion}">${l.institucion}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                    </div>
                    <div class="form-group row">
                        <spring:message  code="institution.headquarters" var="sedes"/>
                        <label for="sedes" class="col-sm-3 col-form-label"><spring:message  code="institution.headquarters"/></label>
                        <label for="sdess" class="col-sm-1 col-form-label"><i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-8">
                            <input type="text" name="sede" class="form-control form-control-lg"  placeholder="${sedes}" />
                        </div>
                    </div>
                    <div class="form-group row">
                        <spring:message  code="items.institution.code.msg" var="cmsg"/>
                        <label for="cItem" class="col-sm-3 col-form-label"><spring:message  code="items.institution.code"/></label>
                        <label for="cItems" class="col-sm-1 col-form-label"><i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-8">
                            <input type="text" name="codigo" class="form-control form-control-lg"  placeholder="${cmsg}" />
                        </div>
                    </div>

                </div>
                <div class="modal-footer">
                    <button class="btn btn-outline-secondary btn-lg" data-dismiss="modal" aria-hidden="true"><i class="fa fa-undo" aria-hidden="true"></i>  <spring:message  code="people.cancel"/></button>
                    <button type="submit" class="btn btn-success btn-lg float-right" id="btnSedes" data-dismiss="modal" aria-hidden="true"><i class="fa fa-plus-square-o" aria-hidden="true"></i>   <spring:message  code="people.add"/></button>
                </div>
            </form:form>
        </div>
    </div>
</div>

<!-- registras Direcciones funcionales -->             
<div id="formInsDireccionesFunc" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form:form name="insDireccionesFuncForm" modelAttribute="insDireccionesFuncionales" action="guardarInsDireccionesF" role="form" id="itemForm" method="post">
                <div class="modal-header">
                    <p class="h3"><i class="fa fa-user-secret" aria-hidden="true"></i>  <spring:message  code="departure.expenses.direction.new"/></p>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                </div>
                <div class="modal-body">
                    <div class="form-group row">
                        <spring:message  code="institution.institution" var="sigla"/>
                        <label for="cItem" class="col-sm-3 col-form-label"><spring:message  code="institution.institution"/></label>
                        <label for="cItems" class="col-sm-1 col-form-label"><i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-8">
                            <form:select class="form-institution form-control form-control-lg" path="InsSedes.idSede" id="idSede">
                                <option disabled selected value="" hidden>SELECCIONE UNA SEDE</option>
                                <c:forEach var="s" items="${requestScope.lSedes}">
                                    <form:option value="${s.idSede}">${s.sede}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                    </div>
                    <div class="form-group row">
                        <spring:message  code="people.direction.msg" var="sedes"/>
                        <label for="sedes" class="col-sm-3 col-form-label"><spring:message  code="report.cash.box.unity.direction"/></label>
                        <label for="sdess" class="col-sm-1 col-form-label"><i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-8">
                            <input type="text" name="direccionFuncional" class="form-control form-control-lg"  placeholder="${sedes}" />
                        </div>
                    </div>
                    <div class="form-group row">
                        <spring:message  code="items.institution.code.msg" var="cmsg"/>
                        <label for="cItem" class="col-sm-3 col-form-label"><spring:message  code="items.institution.code"/></label>
                        <label for="cItems" class="col-sm-1 col-form-label"><i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-8">
                            <input type="text" name="sigla" class="form-control form-control-lg"  placeholder="${cmsg}" />
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-outline-secondary btn-lg" data-dismiss="modal" aria-hidden="true"><i class="fa fa-undo" aria-hidden="true"></i>  <spring:message  code="people.cancel"/></button>
                    <button type="submit" class="btn btn-success btn-lg float-right" id="btnDirecciones" data-dismiss="modal" aria-hidden="true"><i class="fa fa-plus-square-o" aria-hidden="true"></i>   <spring:message  code="people.add"/></button>
                </div>
            </form:form>
        </div>
    </div>
</div>
<div id="formUnidades" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form:form name="unidadesForm" modelAttribute="insUnidadesFuncionales" action="guardarUnidadesFuncionales" role="form" id="itemForm" method="post">
                <div class="modal-header">
                    <p class="h3"><i class="fa fa-user-secret" aria-hidden="true"></i>  <spring:message  code="items.unit.new"/></p>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                </div>
                <div class="modal-body">

                    <div class="form-group row">
                        <spring:message  code="institution.headquarters" var="sigla"/>
                        <label for="cItem" class="col-sm-3 col-form-label"><spring:message  code="institution.headquarters"/></label>
                        <label for="cItems" class="col-sm-1 col-form-label"><i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-8">
                            <select class="form-sedes form-control form-control-lg" name="idSede" id="idSedes" >
                                <option disabled selected value="" hidden>SELECCIONE UNA SEDE</option>
                                <c:forEach var="l" items="${requestScope.lSedes}">
                                    <option value="${l.idSede}">${l.sede}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="sedes" class="col-sm-3 col-form-label"><spring:message  code="report.cash.box.unity.direction"/></label>
                        <label class="col-sm-1 col-form-label text-right" ><i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-8" id="comboDireccion"> 
                        	<option disabled selected value="" hidden>SELECCIONE UNA DIRECCION</option>
                            <form:select class="form-sedes form-control form-control-lg" path="InsDireccionesFuncionales.idDireccionFuncional"  id="direccionU" >
                                     <form:option value="">Selection</form:option>
                             </form:select>
                        </div>
                    </div>

                    <div class="form-group row">
                        <spring:message  code="units.unit.msg" var="unit"/>
                        <label for="units" class="col-sm-3 col-form-label"><spring:message  code="units.unit"/></label>
                        <label for="unit" class="col-sm-1 col-form-label"><i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-8">
                            <input type="text" name="unidadFuncional" class="form-control form-control-lg"  placeholder="${unit}" />
                        </div>
                    </div>
                    <div class="form-group row">
                        <spring:message  code="items.institution.code.msg" var="mcode"/>
                        <label for="cItem" class="col-sm-3 col-form-label"><spring:message  code="items.institution.code"/></label>
                        <label for="cItems" class="col-sm-1 col-form-label"><i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-8">
                            <input type="text" name="sigla" class="form-control form-control-lg"  placeholder="${mcode}" />
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-outline-secondary btn-lg" data-dismiss="modal" aria-hidden="true"><i class="fa fa-undo" aria-hidden="true"></i>  <spring:message  code="people.cancel"/></button>
                    <button type="submit" class="btn btn-success btn-lg float-right" id="btnUnidades" data-dismiss="modal" aria-hidden="true"><i class="fa fa-plus-square-o" aria-hidden="true"></i>   <spring:message  code="people.add"/></button>
                </div>
            </form:form>
        </div>
    </div>
</div>



