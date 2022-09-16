+<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<head>
    <link rel="stylesheet" type="text/css"  href=" <c:url value="/resource/sicoes/admContratos/contratos.css"/>" />
    <script src="<spring:url value="/resource/sicoes/admContratos/contratos.js" />" type="text/javascript"></script>
    <style>
    </style>

</head>
<div class="container">    <section class="content-header">
        <div class="row">
            <div class="col-md-10 mb-3">
                <p class="h1"><i class="fa fa-folder-open-o" aria-hidden="true"></i> <spring:message code="contracts.manage.contracts.title"/> </p>
            </div>
            <div class="col-sm-2 text-right" id="reporte">
                <c:choose>
                    <c:when test="${busqueda eq 'find' }">
                         <form  method="POST" action="${urlNCont}">
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

        <!--------------- Inicio registro nueva Carpeta-->
        <c:choose>
            <c:when test="${operation eq 'reg' }">
                <center><br>
                    <p class="h3"><i class="fa fa-folder-o" aria-hidden="true"></i> <spring:message  code="contracts.form.title"/></p> 
                    <br>
                </center>
                <form:form modelAttribute="scsContrataciones" action="${regCont}" enctype="multipart/form-data"  method="post">
                  
                     <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="contracts.form.contract.code"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <spring:message  code="contracts.form.contract.code.msg" var="code"/>
                            <form:input type="text" path="scsCodigoContratacion"  class="form-control form-control-lg"  placeholder="${code}" id="codigoCont" value="AMPE-${idScs}/${gestion}"/> 
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                               <spring:message  code="contracts.form.contract.code.msg"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="scsCodigoContratacion" />
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="contracts.form.service.charge"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <spring:message  code="contracts.form.service.charge.msg" var="charge"/>
                            <form:textarea  path="scsCargoServicio" class="form-control form-control-lg" placeholder="${charge}" rows="3" cols="20" /> 
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <spring:message  code="contracts.form.service.charge.msg"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="scsCargoServicio" />
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-2 col-form-label"><spring:message  code="contracts.form.official"/></label>
                        <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <form:select class="form-control form-control-lg" path="Personas.idPersona" id="personas" >
                                <c:forEach var="lp" items="${requestScope.lPersonas}">
                                    <form:option value="${lp.idPersona}">${lp.nombres} ${lp.paterno} ${lp.materno}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <spring:message  code="contracts.form.official.msg"/>
                           </small>
                            <small id="ci" class="form-text text-muted formFieldError">
                                <form:errors path="Personas.idPersona" cssClass="alert alert-danger"/>
                            </small>
                        </div>
                    </div>
                    
                    <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-2 col-form-label"><spring:message  code="contracts.form.project"/></label>
                        <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <form:select class="form-control form-control-lg" path="ScsProyectos.idScsProyecto" id="proyecto">
                                <c:forEach var="lcp" items="${requestScope.lScsProyectos}">
                                    <form:option value="${lcp.idScsProyecto}">${lcp.scsProyecto}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <spring:message  code="contracts.form.project.msg"/>
                            </small>
                            <small id="ci" class="form-text text-muted formFieldError">
                                <form:errors path="ScsProyectos.idScsProyecto" cssClass="alert alert-danger"/>
                            </small>
                        </div>
                    </div>
           
                    <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-2 col-form-label"><spring:message  code="contracts.form.type.contract"/></label>
                        <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <form:select class="form-control form-control-lg" path="ScsTiposContratos.idScsTipoContrato" id="tContrato">
                                <c:forEach var="ltc" items="${requestScope.lScsTiposContratos}">
                                    <form:option value="${ltc.idScsTipoContrato}">${ltc.scsTipoContrato}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <spring:message  code="contracts.form.type.contract.msg"/>
                            </small>
                            <small id="ci" class="form-text text-muted formFieldError">
                                <form:errors path="ScsTiposContratos.idScsTipoContrato" cssClass="alert alert-danger"/>
                            </small>
                        </div>
                    </div>
                    
                    <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-2 col-form-label"><spring:message  code="contracts.form.contract.modality"/></label>
                        <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <form:select class="form-control form-control-lg" path="ScsModalidades.idScsModalidad" id="modalidad">
                                <c:forEach var="lmd" items="${requestScope.lScsModalidades}">
                                    <form:option value="${lmd.idScsModalidad}">${lmd.scsModalidad}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <spring:message  code="contracts.form.contract.modality.msg"/>
                            </small>
                            <small id="ci" class="form-text text-muted formFieldError">
                                <form:errors path="ScsModalidades.idScsModalidad" cssClass="alert alert-danger"/>
                            </small>
                        </div>
                    </div>
                            
                    <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-2 col-form-label"><spring:message  code="contracts.form.departure"/></label>
                        <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <form:select class="form-control form-control-lg" path="CjaTiposGastos.idCjaTipoGasto" id="partida">
                                <c:forEach var="ltg" items="${requestScope.lCjaTiposGastos}">
                                    <form:option value="${ltg.idCjaTipoGasto}">${ltg.tipoGasto}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <spring:message  code="contracts.form.departure.msg"/>
                            </small>
                            <small id="ci" class="form-text text-muted formFieldError">
                                <form:errors path="CjaTiposGastos.idCjaTipoGasto" cssClass="alert alert-danger"/>
                            </small>
                        </div>
                    </div>
                    
                    <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-2 col-form-label"><spring:message  code="contracts.form.forms"/></label>
                        <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <form:select class="form-control form-control-lg" path="ScsFormularios.idScsFormulario" id="formularios">
                                <c:forEach var="lsf" items="${requestScope.lScsFormularios}">
                                    <form:option value="${lsf.idScsFormulario}">${lsf.scsFormulario}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <spring:message  code="contracts.form.forms.msg"/>
                            </small>
                            <small id="ci" class="form-text text-muted formFieldError">
                                <form:errors path="ScsFormularios.idScsFormulario" cssClass="alert alert-danger"/>
                            </small>
                        </div>
                    </div>
                      <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-2 col-form-label"><spring:message  code="contracts.form.backup.ballots"/></label>
                        <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <form:select class="form-control form-control-lg" path="ScsBoletasRespaldatorias.idScsBoletaRespaldatoria" id="boletas">
                                <c:forEach var="lbr" items="${requestScope.lScsBoletasRespaldatorioas}">
                                    <form:option value="${lbr.idScsBoletaRespaldatoria}">${lbr.scsBoletaRespaldatoria}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <spring:message  code="contracts.form.backup.ballots.msg"/>
                           </small>
                            <small id="ci" class="form-text text-muted formFieldError">
                                <form:errors path="ScsBoletasRespaldatorias.idScsBoletaRespaldatoria" cssClass="alert alert-danger"/>
                            </small>
                        </div>
                    </div>
                   
                    <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="contracts.form.monthly.amount"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <spring:message  code="contracts.form.monthly.amount.msg" var="tmonth" />
                            <form:input type="text" path="montoMensual"  class="form-control form-control-lg"  placeholder="${tmonth}" onchange="construirNombrePdf();"/> 
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                               <spring:message  code="contracts.form.monthly.amount.msg"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="montoMensual" />
                            </small>
                        </div>
                    </div>

                     <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="contracts.form.total.amount"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <spring:message  code="contracts.form.total.amount.msg" var="total"/>
                            <form:input type="text" path="montoTotal"  class="form-control form-control-lg"  placeholder="${total}"/> 
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                               <spring:message  code="contracts.form.total.amount.msg"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="montoTotal" />
                            </small>
                        </div>
                    </div>
                     
                    <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="contracts.form.date.awarded"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <spring:message  code="contracts.form.date.awarded.msg" var="award"/>
                            <form:input type="Date" path="fecInicio"  class="form-control form-control-lg"  placeholder="${award}"/> 
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                               <spring:message  code="contracts.form.date.awarded.msg"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="fecInicio" />
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="contracts.form.final.date"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <spring:message  code="contracts.form.final.date.msg" var="fina"/>
                            <form:input type="Date" path="fecFinal"  class="form-control form-control-lg"  placeholder="${fina}"/> 
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                               <spring:message  code="contracts.form.final.date.msg"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="fecFinal" />
                            </small>
                        </div>
                    </div>
                   
                   <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="contracts.form.fojas"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <spring:message  code="contracts.form.fojas.msg" var="fojas"/>
                            <form:input type="text" path="fojas"  class="form-control form-control-lg"  placeholder="${fojas}"/> 
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                               <spring:message  code="contracts.form.fojas.msg"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="fojas" />
                            </small>
                        </div>
                    </div>
                    
                    <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="contracts.form.days"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <spring:message  code="contracts.form.days.msg" var="days"/>
                            <form:input type="text" path="dias"  class="form-control form-control-lg"  placeholder="${days}"/> 
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                               <spring:message  code="contracts.form.days.msg"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="dias" />
                            </small>
                        </div>
                    </div>
                    
                    <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="contracts.form.contract.number"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <spring:message  code="contracts.form.contract.number.msg" var="nroc"/>
                            <form:input type="text" path="nroContratacion"  class="form-control form-control-lg"  placeholder="${nroc}"/> 
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                               <spring:message  code="contracts.form.contract.number.msg"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="nroContratacion" />
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <spring:message  code="contracts.form.attached.file.msg" var="nexo"/>
                        <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="contracts.form.attached.file"/></label>
                        <label class="col-sm-1 col-form-label text-right" > <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6" id="combo1"> 
                            <form:input type="file" path="file" id="file" class="form-control form-control-lg input-sm"/>
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                            <small id="emailHelp" class="form-text text-muted">
                                <spring:message  code="contracts.form.attached.file.msg"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="file" />
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <spring:message  code="voucher.file.name.msg" var="adj"/>
                        <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="voucher.file.name"/></label>
                        <label class="col-sm-1 col-form-label text-right" > <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6" id="combo1"> 
                            <form:input class="form-control form-control-lg" path="nombreArchivo" placeholder="${adj}"  id="fileName1" />
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                            <small id="emailHelp" class="form-text text-muted">
                                <spring:message  code="voucher.file.name.msg"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="nombreArchivo" />
                            </small>
                        </div>
                    </div>
                    
                    <div class="form-group row">
                        <spring:message  code="items.year" var="year"/>
                        <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="items.year"/></label>
                        <label class="col-sm-1 col-form-label text-right" > </label>
                        <div class="col-sm-6" id="combo1"> 
                            <form:input class="form-control form-control-lg" path="gestion"  placeholder="${year}" value="${gestion}" />
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
                            <form:input class="form-control form-control-lg" path="periodo"  value="${periodo}" />
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                            <small id="emailHelp" class="form-text text-muted">
                                <spring:message  code="items.period.msg" />
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
                            <a href="<spring:url value="/aContrataciones/inicioContratacion"/>" class="btn btn-yvela-green"><i class="fa fa-undo yvela" aria-hidden="true"></i>  <spring:message  code="expenses.btn.cancel"/></a>
                        </div> 
                    </div> 
                </form:form>
                <br><br>
                <hr align="left" noshade="noshade" size="10" width="100%" />
                
            </c:when>
        </c:choose>
        <!-- ---------------- Final registro carpetas -->
      
        <!--------------- Inicio Modificar carpetas----------------------------------- -->
        <c:choose>
            <c:when test="${operation eq 'mod' }">
                
                <center><br>
                    <p class="h3"><i class="fa fa-folder-o" aria-hidden="true"></i> <spring:message  code="contracts.form.modification"/></p> 
                    <br>
                </center>
                
                 <form:form modelAttribute="scsContrataciones" action="${urlUp}" enctype="multipart/form-data"  method="post">
                     <form:input type="hidden" path="ScsArchivosAdjuntos.idScsArchivoAdjunto" value="${scsContrataciones.scsArchivosAdjuntos.idScsArchivoAdjunto}" />
                     <form:input type="hidden" path="idScsContratacion" value="${scsContrataciones.idScsContratacion}" />
                     <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="contracts.form.contract.code"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <spring:message  code="contracts.form.contract.code.msg" var="code"/>
                            <form:input type="text" path="scsCodigoContratacion"  class="form-control form-control-lg"  placeholder="${code}"/> 
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                               <spring:message  code="contracts.form.contract.code.msg"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="scsCodigoContratacion" />
                            </small>
                        </div>
                    </div>
                      <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="contracts.form.service.charge"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <spring:message  code="contracts.form.service.charge.msg" var="charge"/>
                            <form:textarea  path="scsCargoServicio" class="form-control form-control-lg" placeholder="${charge}" rows="3" cols="20" /> 
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <spring:message  code="contracts.form.service.charge.msg"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="scsCargoServicio" />
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-2 col-form-label"><spring:message  code="contracts.form.official"/></label>
                        <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <form:select class="form-control form-control-lg" path="Personas.idPersona" id="personas">
                                <c:forEach var="lp" items="${requestScope.lPersonas}">
                                    <form:option value="${lp.idPersona}">${lp.nombres} ${lp.paterno} ${lp.materno}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <spring:message  code="contracts.form.official.msg"/> 
                            </small>
                            <small id="ci" class="form-text text-muted formFieldError">
                                <form:errors path="Personas.idPersona" cssClass="alert alert-danger"/>
                            </small>
                        </div>
                    </div>
                    
                    <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-2 col-form-label"><spring:message  code="contracts.form.project"/></label>
                        <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <form:select class="form-control form-control-lg" path="ScsProyectos.idScsProyecto" id="proyecto">
                                <c:forEach var="lcp" items="${requestScope.lScsProyectos}">
                                    <form:option value="${lcp.idScsProyecto}">${lcp.scsProyecto}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <spring:message  code="contracts.form.project.msg"/> 
                            </small>
                            <small id="ci" class="form-text text-muted formFieldError">
                                <form:errors path="ScsProyectos.idScsProyecto" cssClass="alert alert-danger"/>
                            </small>
                        </div>
                    </div>
           
                    <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-2 col-form-label"><spring:message  code="contracts.form.type.contract"/></label>
                        <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <form:select class="form-control form-control-lg" path="ScsTiposContratos.idScsTipoContrato" id="tContrato">
                                <c:forEach var="ltc" items="${requestScope.lScsTiposContratos}">
                                    <form:option value="${ltc.idScsTipoContrato}">${ltc.scsTipoContrato}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <spring:message  code="contracts.form.type.contract.msg"/> 
                            </small>
                            <small id="ci" class="form-text text-muted formFieldError">
                                <form:errors path="ScsTiposContratos.idScsTipoContrato" cssClass="alert alert-danger"/>
                            </small>
                        </div>
                    </div>
                    
                    <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-2 col-form-label"><spring:message  code="contracts.form.contract.modality"/></label>
                        <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <form:select class="form-control form-control-lg" path="ScsModalidades.idScsModalidad" id="modalidad">
                                <c:forEach var="lmd" items="${requestScope.lScsModalidades}">
                                    <form:option value="${lmd.idScsModalidad}">${lmd.scsModalidad}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <spring:message  code="contracts.form.contract.modality.msg"/> 
                            </small>
                            <small id="ci" class="form-text text-muted formFieldError">
                                <form:errors path="ScsModalidades.idScsModalidad" cssClass="alert alert-danger"/>
                            </small>
                        </div>
                    </div>
                            
                    <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-2 col-form-label"><spring:message  code="contracts.form.departure"/></label>
                        <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <form:select class="form-control form-control-lg" path="CjaTiposGastos.idCjaTipoGasto" id="partida">
                                <c:forEach var="ltg" items="${requestScope.lCjaTiposGastos}">
                                    <form:option value="${ltg.idCjaTipoGasto}">${ltg.tipoGasto}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <spring:message  code="contracts.form.departure.msg"/> 
                            </small>
                            <small id="ci" class="form-text text-muted formFieldError">
                                <form:errors path="CjaTiposGastos.idCjaTipoGasto" cssClass="alert alert-danger"/>
                            </small>
                        </div>
                    </div>
                    
                    <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-2 col-form-label"><spring:message  code="contracts.form.forms"/></label>
                        <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <form:select class="form-control form-control-lg" path="ScsFormularios.idScsFormulario" id="formularios">
                                <c:forEach var="lsf" items="${requestScope.lScsFormularios}">
                                    <form:option value="${lsf.idScsFormulario}">${lsf.scsFormulario}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <spring:message  code="contracts.form.forms.msg"/> 
                            </small>
                            <small id="ci" class="form-text text-muted formFieldError">
                                <form:errors path="ScsFormularios.idScsFormulario" cssClass="alert alert-danger"/>
                            </small>
                        </div>
                    </div>
                      <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-2 col-form-label"><spring:message  code="contracts.form.backup.ballots"/></label>
                        <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <form:select class="form-control form-control-lg" path="ScsBoletasRespaldatorias.idScsBoletaRespaldatoria" id="boletas">
                                <c:forEach var="lbr" items="${requestScope.lScsBoletasRespaldatorioas}">
                                    <form:option value="${lbr.idScsBoletaRespaldatoria}">${lbr.scsBoletaRespaldatoria}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                 <spring:message  code="contracts.form.backup.ballots.msg"/> 
                            </small>
                            <small id="ci" class="form-text text-muted formFieldError">
                                <form:errors path="ScsBoletasRespaldatorias.idScsBoletaRespaldatoria" cssClass="alert alert-danger"/>
                            </small>
                        </div>
                    </div>
                   
                    <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="contracts.form.monthly.amount"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <spring:message  code="contracts.form.monthly.amount.msg" var="tmonth"/>
                            <form:input type="text" path="montoMensual"  class="form-control form-control-lg"  placeholder="${tmonth}"/> 
                        </div>
                        <div class="col-sm-3" id="com5">
                            <small class="form-text text-muted">
                               <spring:message  code="contracts.form.monthly.amount.msg"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="montoMensual" />
                            </small>
                        </div>
                    </div>

                     <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="contracts.form.total.amount"/></label>
                        <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <spring:message  code="contracts.form.total.amount.msg" var="total"/>
                            <form:input type="text" path="montoTotal"  class="form-control form-control-lg"  placeholder="${total}"/> 
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                               <spring:message  code="contracts.form.total.amount.msg"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="montoTotal" />
                            </small>
                        </div>
                    </div>
                     
                    <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="contracts.form.date.awarded"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <spring:message  code="contracts.form.date.awarded.msg" var="award"/>
                            <form:input type="Date" path="fecInicio"  class="form-control form-control-lg"  placeholder="${award}"/> 
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                               <spring:message  code="contracts.form.date.awarded.msg"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="fecInicio" />
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="contracts.form.final.date"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <spring:message  code="contracts.form.final.date.msg" var="fina"/>
                            <form:input type="Date" path="fecFinal"  class="form-control form-control-lg"  placeholder="${fina}"/> 
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                               <spring:message  code="contracts.form.final.date.msg"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="fecFinal" />
                            </small>
                        </div>
                    </div>
                   
                   <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="contracts.form.fojas"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <spring:message  code="contracts.form.fojas.msg" var="fojas"/>
                            <form:input type="text" path="fojas"  class="form-control form-control-lg"  placeholder="${fojas}"/> 
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                               <spring:message  code="contracts.form.fojas.msg"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="fojas" />
                            </small>
                        </div>
                    </div>
                    
                    <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="contracts.form.days"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <spring:message  code="contracts.form.days.msg" var="days"/>
                            <form:input type="text" path="dias"  class="form-control form-control-lg"  placeholder="${days}"/> 
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                               <spring:message  code="contracts.form.days.msg"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="dias" />
                            </small>
                        </div>
                    </div>
                    
                    <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="contracts.form.contract.number"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <spring:message  code="contracts.form.contract.number.msg" var="nroc"/>
                            <form:input type="text" path="nroContratacion"  class="form-control form-control-lg"  placeholder="${nroc}"/> 
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                               <spring:message  code="contracts.form.contract.number.msg"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="nroContratacion" />
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <spring:message  code="contracts.form.attached.file.msg" var="nexo"/>
                        <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="contracts.form.attached.file"/></label>
                        <label class="col-sm-1 col-form-label text-right" > <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6" id="combo1"> 
                            <form:input type="file" path="file" id="file" class="form-control form-control-lg input-sm"/>
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                            <small id="emailHelp" class="form-text text-muted">
                                <spring:message  code="contracts.form.attached.file.msg"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="file" />
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <spring:message  code="voucher.file.name.msg" var="adj"/>
                        <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="voucher.file.name"/></label>
                        <label class="col-sm-1 col-form-label text-right" > <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6" id="combo1"> 
                            <form:input class="form-control form-control-lg" path="nombreArchivo"  id="fileName" placeholder="" value="${scsContrataciones.scsArchivosAdjuntos.nombreArchivo}" />
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                            <small id="emailHelp" class="form-text text-muted">
                                <a class="btn add" href="<spring:url value="/aContrataciones/openFile/${scsContrataciones.scsArchivosAdjuntos.idScsArchivoAdjunto}"/>" target="blank"><i class="fa fa-plus-square-o" aria-hidden="true"></i>  <spring:message  code="voucher.viem.pdf"/></a>
                            </small>
                            <small class="msgError">
                                
                            </small>
                        </div>
                    </div>
                    
                    <div class="form-group row">
                        <spring:message  code="items.year" var="year"/>
                        <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="items.year"/></label>
                        <label class="col-sm-1 col-form-label text-right" > </label>
                        <div class="col-sm-6" id="combo1"> 
                            <form:input class="form-control form-control-lg" path="gestion"  placeholder="${year}" value="${gestion}" />
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
                            <form:input class="form-control form-control-lg" path="periodo"  value="${periodo}" />
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                            <small id="emailHelp" class="form-text text-muted">
                                <spring:message  code="items.period.msg" />
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
                            <a href="<spring:url value="/aContrataciones/inicioContratacions"/>" class="btn btn-yvela-green"><i class="fa fa-undo yvela" aria-hidden="true"></i>  <spring:message  code="expenses.btn.cancel"/></a>
                        </div> 
                    </div>
                
                </form:form>
                <br><br>
                <hr align="left" noshade="noshade" size="10" width="100%" />
             </c:when>
        </c:choose>
        <!------------------------------------ Final Modificar carpetas------------------------------------------- -->
        
        <!------------------------------------ Inicio Eliminar Personas------------------------------------------- -->
        <c:choose>
            <c:when test="${operation eq 'delet' }">
                <div id="" class="col-lg-12" >
                    <br>
                    <center><h5><i class="fa fa-trash-o" aria-hidden="true"></i>  <spring:message  code="contracts.form.elimination"/></h5></center>
                    <br>
                <div class="form-group row">
                 <div class="col-sm-8" id="combo1"> 
                   <form:form modelAttribute="scsContrataciones" action="${urlDel}" enctype="multipart/form-data"  method="post">
                     <form:input type="hidden" path="ScsArchivosAdjuntos.idScsArchivoAdjunto" value="${scsContrataciones.scsArchivosAdjuntos.idScsArchivoAdjunto}" />
                     <form:input type="hidden" path="idScsContratacion" value="${scsContrataciones.idScsContratacion}" />
                     <div class="form-group row">
                        <label for="codigo" class="col-sm-4 col-form-label"><spring:message  code="contracts.form.contract.code"/></label>
                        <div class="col-sm-8">
                            <spring:message  code="contracts.form.contract.code.msg" var="code"/>
                            <form:input type="text" path="scsCodigoContratacion"  class="form-control form-control-lg"  placeholder="${code}"/> 
                        </div>
                    </div>
                      <div class="form-group row">
                        <label for="codigo" class="col-sm-4 col-form-label"><spring:message  code="contracts.form.service.charge"/></label>
                        <div class="col-sm-8">
                            <spring:message  code="contracts.form.service.charge.msg" var="charge"/>
                            <form:textarea  path="scsCargoServicio" class="form-control form-control-lg" placeholder="${charge}" rows="3" cols="20" /> 
                        </div>
                     </div>
                    <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-4 col-form-label"><spring:message  code="contracts.form.official"/></label>
                         <div class="col-sm-8">
                            <form:select class="form-control form-control-lg" path="Personas.idPersona" id="proyecto">
                                <c:forEach var="lp" items="${requestScope.lPersonas}">
                                    <form:option value="${lp.idPersona}">${lp.nombres} ${lp.paterno} ${lp.materno}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                    </div>
                    
                    <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-4 col-form-label"><spring:message  code="contracts.form.project"/></label>
                        <div class="col-sm-8">
                            <form:select class="form-control form-control-lg" path="ScsProyectos.idScsProyecto" id="proyecto">
                                <c:forEach var="lcp" items="${requestScope.lScsProyectos}">
                                    <form:option value="${lcp.idScsProyecto}">${lcp.scsProyecto}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                    </div>
           
                    <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-4 col-form-label"><spring:message  code="contracts.form.type.contract"/></label>
                        <div class="col-sm-8">
                            <form:select class="form-control form-control-lg" path="ScsTiposContratos.idScsTipoContrato" id="proyecto">
                                <c:forEach var="ltc" items="${requestScope.lScsTiposContratos}">
                                    <form:option value="${ltc.idScsTipoContrato}">${ltc.scsTipoContrato}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                     </div>
                    
                    <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-4 col-form-label"><spring:message  code="contracts.form.contract.modality"/></label>
                         <div class="col-sm-8">
                            <form:select class="form-control form-control-lg" path="ScsModalidades.idScsModalidad" id="proyecto">
                                <c:forEach var="lmd" items="${requestScope.lScsModalidades}">
                                    <form:option value="${lmd.idScsModalidad}">${lmd.scsModalidad}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                     </div>
                            
                    <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-4 col-form-label"><spring:message  code="contracts.form.departure"/></label>
                        <div class="col-sm-8">
                            <form:select class="form-control form-control-lg" path="CjaTiposGastos.idCjaTipoGasto" id="proyecto">
                                <c:forEach var="ltg" items="${requestScope.lCjaTiposGastos}">
                                    <form:option value="${ltg.idCjaTipoGasto}">${ltg.tipoGasto}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                     </div>
                    
                    <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-4 col-form-label"><spring:message  code="contracts.form.forms"/></label>
                        <div class="col-sm-8">
                            <form:select class="form-control form-control-lg" path="ScsFormularios.idScsFormulario" id="proyecto">
                                <c:forEach var="lsf" items="${requestScope.lScsFormularios}">
                                    <form:option value="${lsf.idScsFormulario}">${lsf.scsFormulario}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                     </div>
                      <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-4 col-form-label"><spring:message  code="contracts.form.backup.ballots"/></label>
                        <div class="col-sm-8">
                            <form:select class="form-control form-control-lg" path="ScsBoletasRespaldatorias.idScsBoletaRespaldatoria" id="proyecto">
                                <c:forEach var="lbr" items="${requestScope.lScsBoletasRespaldatorioas}">
                                    <form:option value="${lbr.idScsBoletaRespaldatoria}">${lbr.scsBoletaRespaldatoria}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                     </div>
                   
                    <div class="form-group row">
                        <label for="codigo" class="col-sm-4 col-form-label"><spring:message  code="contracts.form.monthly.amount"/></label>
                        <div class="col-sm-8">
                            <spring:message  code="contracts.form.monthly.amount.msg" var="tmonth"/>
                            <form:input type="text" path="montoMensual"  class="form-control form-control-lg"  placeholder="${tmonth}"/> 
                        </div>
                     </div>

                     <div class="form-group row">
                        <label for="codigo" class="col-sm-4 col-form-label"><spring:message  code="contracts.form.total.amount"/></label>
                        <div class="col-sm-8">
                            <spring:message  code="contracts.form.total.amount.msg" var="total"/>
                            <form:input type="text" path="montoTotal"  class="form-control form-control-lg"  placeholder="${total}"/> 
                        </div>
                     </div>
                     
                    <div class="form-group row">
                        <label for="codigo" class="col-sm-4 col-form-label"><spring:message  code="contracts.form.date.awarded"/></label>
                        <div class="col-sm-8">
                            <spring:message  code="contracts.form.date.awarded.msg" var="award"/>
                            <form:input type="Date" path="fecInicio"  class="form-control form-control-lg"  placeholder="${award}"/> 
                        </div>
                     </div>
                    <div class="form-group row">
                        <label for="codigo" class="col-sm-4 col-form-label"><spring:message  code="contracts.form.final.date"/></label>
                        <div class="col-sm-8">
                            <spring:message  code="contracts.form.final.date.msg" var="fina"/>
                            <form:input type="Date" path="fecFinal"  class="form-control form-control-lg"  placeholder="${fina}"/> 
                        </div>
                     </div>
                   
                   <div class="form-group row">
                        <label for="codigo" class="col-sm-4 col-form-label"><spring:message  code="contracts.form.fojas"/></label>
                        <div class="col-sm-8">
                            <spring:message  code="contracts.form.fojas.msg" var="fojas"/>
                            <form:input type="text" path="fojas"  class="form-control form-control-lg"  placeholder="${fojas}"/> 
                        </div>
                     </div>
                    
                    <div class="form-group row">
                        <label for="codigo" class="col-sm-4 col-form-label"><spring:message  code="contracts.form.days"/></label>
                        <div class="col-sm-8">
                            <spring:message  code="contracts.form.days.msg" var="days"/>
                            <form:input type="text" path="dias"  class="form-control form-control-lg"  placeholder="${days}"/> 
                        </div>
                     </div>
                    
                    <div class="form-group row">
                        <label for="codigo" class="col-sm-4 col-form-label"><spring:message  code="contracts.form.contract.number"/></label>
                        <div class="col-sm-8">
                            <spring:message  code="contracts.form.contract.number.msg" var="nroc"/>
                            <form:input type="text" path="nroContratacion"  class="form-control form-control-lg"  placeholder="${nroc}"/> 
                        </div>
                    </div>
                    <div class="form-group row">
                        <spring:message  code="contracts.form.attached.file.msg" var="nexo"/>
                        <label for="sedes" class="col-sm-4 col-form-label"><spring:message  code="contracts.form.attached.file"/></label>
                        <div class="col-sm-8" id="combo1"> 
                            <form:input type="file" path="file" id="file" class="form-control form-control-lg input-sm"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <spring:message  code="voucher.file.name.msg" var="adj"/>
                        <label for="sedes" class="col-sm-4 col-form-label"><spring:message  code="voucher.file.name"/></label>
                        <div class="col-sm-8" id="combo1"> 
                            <form:input class="form-control form-control-lg" path="nombreArchivo"  id="fileName" placeholder="" value="${scsContrataciones.scsArchivosAdjuntos.nombreArchivo}" />
                        </div>
                      </div>
                    
                    <div class="form-group row">
                        <spring:message  code="items.year" var="year"/>
                        <label for="sedes" class="col-sm-4 col-form-label"><spring:message  code="items.year"/></label>
                        <div class="col-sm-8" id="combo1"> 
                            <form:input class="form-control form-control-lg" path="gestion"  placeholder="${year}" value="${gestion}" />
                        </div>
                     </div>
                    <div class="form-group row">
                        <spring:message  code="items.period.msg" var="period"/>
                        <label for="sedes" class="col-sm-4 col-form-label"><spring:message  code="items.period"/></label>
                        <div class="col-sm-8" id="combo1"> 
                            <form:input class="form-control form-control-lg" path="periodo"  value="${periodo}" />
                        </div>
                     </div>    
                    
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
                          <a href="<spring:url value="/aContrataciones/inicioContratacion"/>" class="btn btn-yvela-green"><i class="fa fa-undo" aria-hidden="true"></i>  <spring:message  code="expenses.btn.cancel"/></a>
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

        
        <!------------------------------------ Final Eliminar Personas------------------------------------------- -->
        
        <!-- ------ Tabla lista de Todas las Contrataciones ------------- -->
        <c:choose>
            <c:when test="${busqueda eq 'find' }">

                <center><br>${hola}
                    <p class="h3"><i class="fa fa-folder-open-o" aria-hidden="true"></i> <spring:message  code="contracts.report.contracts.title"/></p> 
                    <br>
                </center>
                <table  id="tContratos"  class="table" style="width:100%">
                    <thead>
                        <tr>
                            <th><spring:message  code="contracts.form.contract.code"/></th>
                            <th><spring:message  code="contracts.form.official"/></th>
                            <th><spring:message  code="contracts.form.project"/></th>
                            <th><spring:message  code="contracts.form.contract.modality"/></th>
                            <th><spring:message  code="contracts.form.total.amount"/></th>
                            <th><spring:message  code="voucher.viem.pdf"/></th>
                            <th><spring:message  code="expenses.modify"/> </th>
                            <th><spring:message  code="expenses.remove"/></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:set var="cont" value="0" />
                        <c:forEach items="${lScsContrataciones}" var="lScs">
                            <c:set var="cont" value="${cont+1}" />
                            <tr>
                                <td>${lScs.scsCodigoContratacion}</td>
                                <td>${lScs.personas.nombres} ${lScs.personas.paterno} ${lScs.personas.materno}</td>
                                <td>${lScs.scsProyectos.scsProyecto} </td>
                                <td>${lScs.scsModalidades.scsModalidad}</td>
                                <td>${lScs.montoTotal}</td>
                                <td>
                                    <a class="btn add" href="<spring:url value="/aContrataciones/openFile/${lScs.scsArchivosAdjuntos.idScsArchivoAdjunto}"/>" target="blank"><i class="fa fa-plus-square-o" aria-hidden="true"></i>  <spring:message  code="voucher.viem.pdf"/></a>
                                </td>
                                <td>
                                    <form  method="post" action="${urlModCont}">
                                        <input type="hidden" name="idScsContratacion" value="${lScs.idScsContratacion}">
                                        <span class="icon-input-btn"> <i class="fa fa-pencil-square-o yvela" aria-hidden="true"> </i> <input class="btn btn-success" type="submit"  value="<spring:message  code="expenses.modify"/>" > </span>
                                    </form>
                                </td>
                                <td>
                                    <form name="Delet" action="${iniElimCont}" method="post">
                                        <input type="hidden" name="idScsContratacion" value="${lScs.idScsContratacion}">
                                        <span class="icon-input-btn"> <i class="fa fa-times yvela" aria-hidden="true"> </i> <input class="btn btn-danger" type="submit"  value="<spring:message  code="expenses.remove"/>"> </span>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                    <tfoot>
                        <tr>
                            <th><spring:message  code="contracts.form.contract.code"/></th>
                            <th><spring:message  code="contracts.form.official"/></th>
                            <th><spring:message  code="contracts.form.project"/></th>
                            <th><spring:message  code="contracts.form.contract.modality"/></th>
                            <th><spring:message  code="contracts.form.total.amount"/></th>
                            <th><spring:message  code="voucher.viem.pdf"/></th>
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
        