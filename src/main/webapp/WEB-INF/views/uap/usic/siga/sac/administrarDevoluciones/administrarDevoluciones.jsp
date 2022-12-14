<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<head>
    <link rel="stylesheet" type="text/css"  href=" <c:url value="/resource/admPersonas/personas.css"/>" />
    <script src="<spring:url value="/resource/sac/admPrestamos/admPrestamosComp.js" />" type="text/javascript"></script>
    <style>
    </style>
</head>
<div class="container">    
    <section class="content-header">
        <div class="row">
            <div class="col-md-10 mb-3">
                <p class="h1"><i class="fa fa-file-archive-o" aria-hidden="true"> </i> <spring:message code="menu.manage.return"/> </p>
            </div>
            <div class="col-sm-2 text-right" id="reporte">
                <c:choose>
                    <c:when test="${busqueda eq 'find' }">
                        <form  method="POST" action="${urlNuevDev}">
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
                <spring:message  code="AbstractUserDetailsAuthenticationProvider.badCredentials"/>
                <br/>
            </div>
        </c:if>
        <!--------------- Inicio registro Devoluciones -->
        <c:choose>
            <c:when test="${operation eq 'reg' }">
                <center><br>
                    <p class="h3"><i class="fa fa-folder-open" aria-hidden="true"> </i> <spring:message  code="returns.register"/></p> 
                    <br>
                </center>
                <form:form modelAttribute="sacDevolucionesComprobantes" action="${urlRegDevComp}" enctype="multipart/form-data" method="post">
                    <form:input type="hidden" path="Usuarios.idUsuario" value="${currentUserId}" />
                    <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="returns.date"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <spring:message  code="loans.date.loans" var="nom"/>
                            <form:input type="date" path="fecDevolucion" class="form-control form-control-lg"  placeholder="${nom}" id="fecha" onchange="checkdate(this.value);"/> 
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <spring:message  code="returns.date.msg"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="fecDevolucion" />
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="returns.time"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <spring:message  code="loans.time.loans" var="nom"/>
                            <form:input type="time" path="horaDevolucion" class="form-control form-control-lg"  placeholder="${nom}" id="fecha" onchange="checkdate(this.value);"/> 
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <spring:message  code="returns.time.msg"/>
                            </small>
                            <small class="msgError">

                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <spring:message  code="items.year" var="year"/>
                        <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="items.year"/></label>
                        <label class="col-sm-1 col-form-label text-right" > <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6" id="combo1"> 
                            <form:input class="form-control form-control-lg" path="gestionDevolucion"  id="gestion" onkeyUp="return ValNumero(this);" placeholder="${year}" value="${gestion}" />
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                            <small id="emailHelp" class="form-text text-muted">
                                <spring:message  code="items.year.msg"/>
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="login.people"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <form:select class="form-control form-control-lg" path="Personas.idPersona" id="lPersona">
                                <c:forEach var="p" items="${requestScope.lPersonas}">
                                    <form:option value="${p.idPersona}">${p.nombres} ${p.paterno} ${p.materno} </form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <a class="btn add" href="#" data-toggle="modal" data-target="#formPersonas"><i class="fa fa-plus-square-o" aria-hidden="true"></i>  <spring:message  code="people.add"/></a>
                            </small>
                            <small id="emailHelp" class="form-text text-muted">
                                <spring:message  code="returns.person.msg"/>
                            </small>
                        </div>
                    </div>
                    <hr align="left" noshade="noshade" size="10" width="100%" />
                    <div class="form-group row">
                        <label for="msg" class="col-sm-2 col-form-label text-right"></label>
                        <label for="msg" class="col-sm-10 col-form-label"><span class="parpadea text"><i class="fa fa-check-square-o" aria-hidden="true"></i></span> &nbsp;&nbsp;&nbsp;<spring:message  code="returns.chekbook"/> </label>
                    </div>   
                    <table id="tPrestamos" class="table" style="width:100%">
                        <thead>
                            <tr>
                                <th>Nro</th>
                                <th>?</th>
                                <th><spring:message  code="voucher.number"/></th>
                                <th><spring:message  code="voucher.date.elaboration"/></th>
                                <th><spring:message  code="voucher.Name.CorporateName"/></th>
                                <th><spring:message  code="loans.table.detail"/></th>
                                <th><spring:message  code="items.year"/></th>
                                <th>Estado de Prestamo</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:set var="cont" value="0" />
                            <c:set var="banderita" value="0" />
                            <c:forEach items="${lPrescDet}" var="lSac">
                                <c:if  test="${lSac.prestamo == true && lSac.idEstado == 'A' }">
                                    <c:set var="cont" value="${cont+1}" />
                                    <tr>
                                        <td>${cont}</td>
                                <input type="hidden" name="idSacPrestamoComprobante${lSac.sacComprobantes.idSacComprobante}" value="${lSac.sacPrestamosComprobantes.idSacPrestamoComprobante}">
                                <td><input type="checkbox"  name="idSacComprobante"  value="${lSac.sacComprobantes.idSacComprobante}" /></td>
                                <td>
                                    <c:forEach var="nc" items="${requestScope.lSacNumComp}">
                                        <c:if  test="${lSac.sacComprobantes.idSacComprobante == nc.sacComprobantes.idSacComprobante}">
                                            - ${nc.sacNroComprobante} 
                                        </c:if>
                                    </c:forEach>
                                </td>
                                <td>${lSac.sacComprobantes.fecElaboracion}</td>
                                <td>
                                    <c:forEach var="rs" items="${requestScope.lRazonSocialP}">
                                        <c:if  test="${lSac.sacComprobantes.idSacComprobante == rs.sacComprobantes.idSacComprobante}">
                                            - ${rs.personas.nombres} ${rs.personas.paterno} 
                                        </c:if>
                                    </c:forEach>
                                </td>
                                <td>${lSac.sacComprobantes.glosa}</td>
                                <td>${lSac.sacComprobantes.gestion}</td>
                                <td>
                                    <select class="form-control form-control-lg" name="idSacEstadoComprobante${lSac.sacComprobantes.idSacComprobante}">
                                        <c:forEach var="e" items="${requestScope.lEstados}">
                                            <option value="${e.idSacEstadoComprobante}">${e.sacNombreEstado}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                </tr>
                            </c:if>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div class="form-group row">
                        <label for="msg" class="col-sm-2 col-form-label text-right"></label>
                        <label for="msg" class="col-sm-10 col-form-label"><span class="parpadea text"><i class="fa fa-eye" aria-hidden="true"></i></span> &nbsp;&nbsp;&nbsp;<spring:message  code="people.warning"/> </label>
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
                            <a href="<spring:url value="/devoluciones/inicioDevoluciones"/>" class="btn btn-yvela-green"><i class="fa fa-undo yvela" aria-hidden="true"></i>  <spring:message  code="expenses.btn.cancel"/></a>
                        </div> 
                    </div>
                </form:form>
                <br><br>
                <hr align="left" noshade="noshade" size="10" width="100%" />
            </c:when>
        </c:choose>
        <!-- Final registro comprobantes -->
        <!--Vista Previa Acta de devolucion -->  
        <c:choose>
            <c:when test="${vistaPrevia eq 'vista' }">
                <center><br>
                    <p class="h3"><i class="fa fa-folder-open" aria-hidden="true"> </i> <spring:message  code="returns.register"/></p> 
                    <br>
                </center>
                <div class="form-group row">
                    En la ciudad de Cobija Capital del Departamento de Pando, en Fecha ${fechaDevolucion} a Hrs. ${bDevolucionComp.horaDevolucion} 
                    se recibio <c:if test="${bDevolucionComp.personas.prsTiposSexos.idPrsTipoSexo == 1}">de la </c:if> <c:if test="${bDevolucionComp.personas.prsTiposSexos.idPrsTipoSexo == 2}">del </c:if> 
                    ${bDevolucionComp.personas.prsGradosAcademicos.descripcion} ${bDevolucionComp.personas.nombres} ${bDevolucionComp.personas.paterno} ${bDevolucionComp.personas.materno},
                    los siguientes comprobantes contables, pertenecientes a esta oficina de  ${bPersonalAdministrativo.insUnidadesFuncionales.unidadFuncional}
                    el cual se hizo entrega 
                    <c:if test="${bPersona.prsTiposSexos.idPrsTipoSexo == 1}"> a la Sra. </c:if> <c:if test="${bPersona.prsTiposSexos.idPrsTipoSexo == 2}"> al Sr. </c:if> 
                    ${bPersona.nombres} ${bPersona.paterno} ${bPersona.materno} ${bPersonalAdministrativo.pnlCargos.cargo}.
                </div>
                <table border="1"  style="width:100%">
                    <thead>
                        <tr>
                            <th><center>Nro</th>
                        <th><spring:message  code="voucher.number"/></th>
                        <th><spring:message  code="voucher.date.elaboration"/></th>
                        <th><spring:message  code="voucher.Name.CorporateName"/></th>
                        <th><spring:message  code="loans.table.detail"/></th>
                        <th><spring:message  code="manage.folders.number"/></th>
                    </center>
                    </tr>
                    </thead>
                    <tbody>
                        <c:set var="cont" value="0" />
                        <c:forEach items="${lDevCompDetalles}" var="lSac">
                            <c:set var="cont" value="${cont+1}" />
                            <tr>
                                <td>${cont}</td>
                                <td>
                                    <c:forEach var="nc" items="${requestScope.lSacNumComp}">
                                        <c:if  test="${lSac.idSacComprobante == nc.sacComprobantes.idSacComprobante}">
                                            - ${nc.sacNroComprobante} 
                                        </c:if>
                                    </c:forEach>
                                </td>
                                <td>${lSac.fecElaboracion}</td>
                                <td>
                                    <c:forEach var="rs" items="${requestScope.lRazonSocialP}">
                                        <c:if  test="${lSac.idSacComprobante == rs.sacComprobantes.idSacComprobante}">
                                            - ${rs.personas.nombres} ${rs.personas.paterno} ${rs.personas.materno} 
                                        </c:if>
                                    </c:forEach>
                                </td>
                                <td>${lSac.glosa}</td>
                                <td>${lSac.sacCarpetas.sacCodigoCarpeta}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <hr align="left" noshade="noshade" size="10" width="100%" />
                <div class="form-group row mt-12">
                    <div class="col-sm-3" id="combo1">
                    </div> 
                    <div class="col-sm-3" id="combo1">
                        <form  method="post" action="${urlImpActDev}" target="blank">
                            <input type="hidden" name="idSacDevolucionComprobante" value="${bDevolucionComp.idSacDevolucionComprobante}">
                            <span class="icon-input-btn"> <i class="fa fa-print yvela" aria-hidden="true"> </i> <input class="btn btn-success" type="submit"  value="<spring:message  code="expenses.btn.print"/>" > </span>
                        </form>
                    </div>
                    <div class="col-sm-3" id="combo1"> 
                    </div>
                    <div class="col-sm-3" id="combo1">
                        <a href="<spring:url value="/devoluciones/inicioDevoluciones"/>" class="btn btn-yvela-green"><i class="fa fa-undo yvela" aria-hidden="true"></i>  <spring:message  code="expenses.btn.cancel"/></a>
                    </div> 
                </div>
                <br><br>
                <hr align="left" noshade="noshade" size="10" width="100%" />
            </c:when>
        </c:choose>
        <!-- Final vista previa Acta de  --> 
        <!--------------- Inicio modificar Devolucion-->
        <c:choose>
            <c:when test="${operationMod eq 'mod' }">
                <center><br>
                    <p class="h3"><i class="fa fa-folder-open" aria-hidden="true"> </i> <spring:message  code="returns.modify"/></p> 
                    <br>
                </center>
                <form:form modelAttribute="sacDevolucionesComprobantes" action="${confModiDev}" enctype="multipart/form-data" method="post">
                    <form:input type="hidden" path="Usuarios.idUsuario" value="${currentUserId}" />
                    <form:hidden  path="idSacDevolucionComprobante" value="${sacDevolucionesComprobantes.idSacDevolucionComprobante}" />

                    <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="returns.date"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <spring:message  code="loans.date.loans" var="nom"/>
                            <form:input type="date" path="fecDevolucion" class="form-control form-control-lg"  placeholder="${nom}" id="fecha" onchange="checkdate(this.value);"/> 
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <spring:message  code="returns.date.msg"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="fecDevolucion" />
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="returns.time"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <spring:message  code="loans.time.loans" var="nom"/>
                            <form:input type="time" path="horaDevolucion" class="form-control form-control-lg"  placeholder="${nom}" id="fecha" onchange="checkdate(this.value);"/> 
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <spring:message  code="returns.time.msg"/>
                            </small>
                            <small class="msgError">

                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <spring:message  code="items.year" var="year"/>
                        <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="items.year"/></label>
                        <label class="col-sm-1 col-form-label text-right" > <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6" id="combo1"> 
                            <form:input class="form-control form-control-lg" path="gestionDevolucion"  id="gestion" onkeyUp="return ValNumero(this);" placeholder="${year}" value="${gestion}" />
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                            <small id="emailHelp" class="form-text text-muted">
                                <spring:message  code="items.year.msg"/>
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="login.people"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <form:select class="form-control form-control-lg" path="Personas.idPersona" id="lPersona">
                                <c:forEach var="p" items="${requestScope.lPersonas}">
                                    <form:option value="${p.idPersona}">${p.nombres} ${p.paterno} ${p.materno} </form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <a class="btn add" href="#" data-toggle="modal" data-target="#formPersonas"><i class="fa fa-plus-square-o" aria-hidden="true"></i>  <spring:message  code="people.add"/></a>
                            </small>
                            <small id="emailHelp" class="form-text text-muted">
                                <spring:message  code="returns.person.msg"/>
                            </small>
                        </div>
                    </div>
                    <hr align="left" noshade="noshade" size="10" width="100%" />

                    <table  border="1" >
                        <thead>
                            <tr>
                                <th>Nro</th>
                                <th><spring:message  code="voucher.number"/></th>
                                <th><spring:message  code="voucher.date.elaboration"/></th>
                                <th><spring:message  code="voucher.Name.CorporateName"/></th>
                                <th><spring:message  code="loans.table.detail"/></th>
                                <th><spring:message  code="manage.folders.number"/></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:set var="cont" value="0" />
                            <c:forEach items="${lDevCompDetalles}" var="lSac">
                                <c:set var="cont" value="${cont+1}" />
                                <tr>
                                    <td>${cont}</td>
                                    <td>
                                        <c:forEach var="nc" items="${requestScope.lSacNumComp}">
                                            <c:if  test="${lSac.idSacComprobante == nc.sacComprobantes.idSacComprobante}">
                                                - ${nc.sacNroComprobante} 
                                            </c:if>
                                        </c:forEach>
                                    </td>
                                    <td>${lSac.fecElaboracion}</td>
                                    <td>
                                        <c:forEach var="rs" items="${requestScope.lRazonSocialP}">
                                            <c:if  test="${lSac.idSacComprobante == rs.sacComprobantes.idSacComprobante}">
                                                - ${rs.personas.nombres} ${rs.personas.paterno} ${rs.personas.materno} 
                                            </c:if>
                                        </c:forEach>
                                    </td>
                                    <td>${lSac.glosa}</td>
                                    <td>${lSac.sacCarpetas.sacCodigoCarpeta}</td>
                                    <td>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <div class="form-group row">
                        <label for="msg" class="col-sm-2 col-form-label text-right"></label>
                        <label for="msg" class="col-sm-10 col-form-label"><span class="parpadea text"><i class="fa fa-eye" aria-hidden="true"></i></span> &nbsp;&nbsp;&nbsp;<spring:message  code="people.warning"/> </label>
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
                            <a href="<spring:url value="/devoluciones/inicioDevoluciones"/>" class="btn btn-yvela-green"><i class="fa fa-undo yvela" aria-hidden="true"></i>  <spring:message  code="expenses.btn.cancel"/></a>
                        </div> 
                    </div>
                </form:form>
                <br><br>
                <hr align="left" noshade="noshade" size="10" width="100%" />
            </c:when>
        </c:choose>
        <!-- Final modificar Devolucion de Comprobantes -->
        <!--------------- Inicio Eliminar Devolucion-->
        <c:choose>
            <c:when test="${operationElim eq 'Elim' }">
                <div id="" class="col-lg-12" >
                    <center><br>
                        <p class="h3"><i class="fa fa-folder-open" aria-hidden="true"> </i> <spring:message  code="returns.remove"/></p> 
                        <br>
                    </center>
                    <div class="form-group row">
                        <div class="col-sm-8" id="combo1"> 
                            <form:form modelAttribute="sacDevolucionesComprobantes" action="${confElimDevoPrest}" enctype="multipart/form-data" method="post">
                                <form:input type="hidden" path="Usuarios.idUsuario" value="${currentUserId}" />
                                <form:input type="hidden"  path="idSacDevolucionComprobante" value="${sacDevolucionesComprobantes.idSacDevolucionComprobante}" />
                                
                                <div class="form-group row">
                                    <label for="codigo" class="col-sm-4 col-form-label"><spring:message  code="returns.date"/></label>
                                    <div class="col-sm-8">
                                        <spring:message  code="loans.date.loans" var="nom"/>
                                        <form:input type="date" path="fecDevolucion" class="form-control form-control-lg"  placeholder="${nom}" id="fecha" onchange="checkdate(this.value);"/> 
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="codigo" class="col-sm-4 col-form-label"><spring:message  code="returns.time"/></label>
                                    <div class="col-sm-8">
                                        <spring:message  code="loans.time.loans" var="nom"/>
                                        <form:input type="time" path="horaDevolucion" class="form-control form-control-lg"  placeholder="${nom}" id="fecha" onchange="checkdate(this.value);"/> 
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <spring:message  code="items.year" var="year"/>
                                    <label for="sedes" class="col-sm-4 col-form-label"><spring:message  code="items.year"/></label>
                                    <div class="col-sm-8" id="combo1"> 
                                        <form:input class="form-control form-control-lg" path="gestionDevolucion"  id="gestion" onkeyUp="return ValNumero(this);" placeholder="${year}" />
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="sedes" class="col-sm-4 col-form-label"><spring:message  code="login.people"/></label>
                                    <div class="col-sm-8">
                                        <form:select class="form-control form-control-lg" path="Personas.idPersona" id="lPersona">
                                            <c:forEach var="p" items="${requestScope.lPersonas}">
                                                <form:option value="${p.idPersona}">${p.nombres} ${p.paterno} ${p.materno} </form:option>
                                            </c:forEach>
                                        </form:select>
                                    </div>
                                </div>
                                <table  border="1" >
                                    <thead>
                                        <tr>
                                            <th>Nro</th>
                                            <th><spring:message  code="voucher.number"/></th>
                                            <th><spring:message  code="voucher.date.elaboration"/></th>
                                            <th><spring:message  code="voucher.Name.CorporateName"/></th>
                                            <th><spring:message  code="loans.table.detail"/></th>
                                            <th><spring:message  code="manage.folders.number"/></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:set var="cont" value="0" />
                                        <c:forEach items="${lDevCompDetalles}" var="lSac">
                                            <c:set var="cont" value="${cont+1}" />
                                            <tr>
                                                <td>${cont}</td>
                                                <td>
                                                    <c:forEach var="nc" items="${requestScope.lSacNumComp}">
                                                        <c:if  test="${lSac.idSacComprobante == nc.sacComprobantes.idSacComprobante}">
                                                            - ${nc.sacNroComprobante} 
                                                        </c:if>
                                                    </c:forEach>
                                                </td>
                                                <td>${lSac.fecElaboracion}</td>
                                                <td>
                                                    <c:forEach var="rs" items="${requestScope.lRazonSocialP}">
                                                        <c:if  test="${lSac.idSacComprobante == rs.sacComprobantes.idSacComprobante}">
                                                            - ${rs.personas.nombres} ${rs.personas.paterno} ${rs.personas.materno} 
                                                        </c:if>
                                                    </c:forEach>
                                                </td>
                                                <td>${lSac.glosa}</td>
                                                <td>${lSac.sacCarpetas.sacCodigoCarpeta}</td>
                                               
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>

                                <hr align="left" noshade="noshade" size="10" width="100%" />
                                <div class="form-group row mt-12">
                                    <div class="col-sm-3" id="combo1"> 
                                    </div>

                                    <div class="col-sm-3" id="combo1">
                                        <span class="icon-input-btn"><i class="fa fa-times yvela" aria-hidden="true"></i> 
                                            <input type="submit"  class="btn btn-danger" value="<spring:message  code="expenses.btn.confirm"/>">
                                            </div>
                                            <div class="col-sm-3" id="combo1"> 
                                            </div>
                                            <div class="col-sm-3" id="combo1"> 
                                                <a href="<spring:url value="/devoluciones/inicioDevoluciones"/>" class="btn btn-yvela-green"><i class="fa fa-undo" aria-hidden="true"></i>  <spring:message  code="expenses.btn.cancel"/></a>
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
            <!-- Final eliminar Devolucion -->
            <!--Tabla lista de Todass las Devoluciones  Registradas -->
            <c:choose>
                <c:when test="${busqueda eq 'find' }">
                    <center><br>
                        <p class="h3"><i class="fa fa-folder-open-o" aria-hidden="true"></i> <spring:message  code="returns.list.details"/></p> 
                        <br>
                    </center>
                    <table id="tPrestamos"  class="table" style="width:100%">
                        <thead>
                            <tr>
                                <th><spring:message  code="returns.date"/></th>
                                <th><spring:message  code="returns.time"/></th>
                                <th>Entregado por:</th>
                                <th>Recibido por:</th>
                                <th><spring:message  code="expenses.btn.print"/></th>
                                <th><spring:message  code="expenses.modify"/> </th>
                                <th><spring:message  code="expenses.remove"/></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:set var="cont" value="0" />
                            <c:forEach items="${lDevolucionesGeneral}" var="lDcom">
                                <c:set var="cont" value="${cont+1}" />
                                <tr>
                                    <td>${lDcom.fecDevolucion}</td>
                                    <td>${lDcom.horaDevolucion}</td>
                                    <td>${lDcom.personas.nombres} ${lDcom.personas.paterno} ${lDcom.personas.materno}</td>
                                    <td>${lDcom.usuarios.personas.nombres} ${lDcom.usuarios.personas.paterno}</td>
                                    <td>
                                        <form  method="post" action="${urlImpActDev}" target="blank">
                                            <input type="hidden" name="idSacDevolucionComprobante" value="${lDcom.idSacDevolucionComprobante}">
                                            <span class="icon-input-btn"> <i class="fa fa-print yvela" aria-hidden="true"> </i> <input class="btn btn-primary btn-sm" type="submit"  value="<spring:message  code="expenses.btn.print"/>" > </span>
                                        </form>
                                    </td>
                                    <td>
                                        <form  method="post" action="${urlIniModDev}">
                                            <input type="hidden" name="idSacDevolucionComprobante" value="${lDcom.idSacDevolucionComprobante}">
                                            <span class="icon-input-btn"> <i class="fa fa-pencil-square-o yvela" aria-hidden="true"> </i> <input class="btn btn-success" type="submit"  value="<spring:message  code="expenses.modify"/>" ></span>
                                        </form>
                                    </td>
                                    <td>
                                        <form name="Delet" action="${iniElimDevPres}" method="post">
                                            <input type="hidden" name="idSacDevolucionComprobante" value="${lDcom.idSacDevolucionComprobante}">
                                            <span class="icon-input-btn"> <i class="fa fa-times yvela" aria-hidden="true"> </i> <input class="btn btn-danger" type="submit"  value="<spring:message  code="expenses.remove"/>"> </span>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                        <tfoot>
                            <tr>
                                <th><spring:message  code="returns.date"/></th>
                                <th><spring:message  code="returns.time"/></th>
                                <th>Entregado por:</th>
                                <th>Recibido por:</th>
                                <th><spring:message  code="expenses.btn.print"/></th>
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
<!-- modal registrar Personas-->
<div id="formPersonas" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form:form name="personasForm" modelAttribute="personas" action="guardarPersonasPrestamos" role="form" id="itemForm" method="post">
                <div class="modal-header">
                    <p class="h3">  <spring:message  code="persona.crearPersona"/></p>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">?</button>
                </div>
                <div class="modal-body">
                    <div class="form-group row">
                        <spring:message  code="people.name.msg" var="issued"/>
                        <label for="expedido" class="col-sm-3 col-form-label"><spring:message  code="persona.nombres"/></label>
                        <label for="ciExpedido" class="col-sm-1 col-form-label"><i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-8">
                            <input type="text" name="nombres" class="form-control form-control-lg"  placeholder="${issued}" />
                        </div>
                    </div>
                    <div class="form-group row">
                        <spring:message  code="people.paternal.msg" var="issued"/>
                        <label for="expedido" class="col-sm-3 col-form-label"><spring:message  code="persona.paterno"/></label>
                        <label for="ciExpedido" class="col-sm-1 col-form-label"></label>
                        <div class="col-sm-8">
                            <input type="text" name="paterno" class="form-control form-control-lg"  placeholder="${issued}" />
                        </div>
                    </div>
                    <div class="form-group row">
                        <spring:message  code="people.paternal.msg" var="issued"/>
                        <label for="expedido" class="col-sm-3 col-form-label"><spring:message  code="persona.paterno"/></label>
                        <label for="ciExpedido" class="col-sm-1 col-form-label"> </label>
                        <div class="col-sm-8">
                            <input type="text" name="materno" class="form-control form-control-lg"  placeholder="${issued}" />
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="cedula" class="col-sm-3 col-form-label"><spring:message  code="persona.cedula"/></label>
                        <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-8">
                            <spring:message  code="people.identity.msg" var="ced"/>
                            <input type="text" name="ci" class="form-control form-control-lg"  placeholder="${ced}" />
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="sexo" class="col-sm-3 col-form-label"><spring:message  code="people.academic.degree"/></label>
                        <label class="col-sm-1 col-form-label text-right"><i class="fa fa-info-circle" aria-hidden="true"></i> </label>
                        <div class="col-sm-8">
                            <form:select class="form-control form-control-lg" path="PrsGradosAcademicos.idGradoAcademico" id="tipoSexo">
                                <c:forEach var="ga" items="${requestScope.lGradosAcademicos}">
                                    <form:option value="${ga.idGradoAcademico}">${ga.descripcion}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="sexo" class="col-sm-3 col-form-label"><spring:message  code="persona.sexo"/></label>
                        <label class="col-sm-1 col-form-label text-right"><i class="fa fa-info-circle" aria-hidden="true"></i> </label>
                        <div class="col-sm-8">
                            <form:select class="form-control form-control-lg" path="PrsTiposSexos.idPrsTipoSexo" id="tipoSexo">
                                <c:forEach var="ts" items="${requestScope.lPrsTiposSexos}">
                                    <form:option value="${ts.idPrsTipoSexo}">${ts.prsTipoSexo}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                    </div>  
                    <div class="form-group row">
                        <label for="pais" class="col-sm-3 col-form-label"><spring:message  code="persona.pais"/></label>
                        <label class="col-sm-1 col-form-label text-right"><i class="fa fa-info-circle" aria-hidden="true"></i> </label>
                        <div class="col-sm-8">
                            <form:select class="form-control form-control-lg" path="PrsPaises.idPais" id="pais">
                                <c:forEach var="p" items="${requestScope.lPaises}">
                                    <form:option value="${p.idPais}">${p.pais}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-3 col-form-label"><spring:message  code="people.issued"/></label>
                        <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-8">
                            <form:select class="form-control form-control-lg" path="PrsCiExpedidos.idPrsCiExpedido" id="ciExpedido">
                                <c:forEach var="cix" items="${requestScope.lExpedidos}">
                                    <form:option value="${cix.idPrsCiExpedido}">${cix.prsCiExpedido}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                    </div>     
                </div>
                <div class="modal-footer">
                    <button class="btn btn-outline-secondary btn-lg" data-dismiss="modal" aria-hidden="true"><i class="fa fa-undo" aria-hidden="true"></i>  <spring:message  code="people.cancel"/></button>
                    <button type="submit" class="btn btn-success btn-lg float-right" id="btnPersonasPrestamos" data-dismiss="modal" aria-hidden="true"><i class="fa fa-plus-square-o" aria-hidden="true"></i>   <spring:message  code="people.add"/></button>
                </div>
            </form:form>
        </div>
    </div>
</div>
<!-- final Modal registro de personas  -->