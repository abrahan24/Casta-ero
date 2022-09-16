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
                <p class="h1"><i class="fa fa-folder-open-o" aria-hidden="true"></i> <spring:message code="contract.people.form.module.title"/> </p>
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
                <form:form modelAttribute="scsPrsContratados" action="${regCont}" enctype="multipart/form-data"  method="post">
                  
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
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="contract.people.form.nit"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <spring:message  code="contract.people.form.nit.msg" var="nit" />
                            <form:input type="text" path="scsNit"  class="form-control form-control-lg"  placeholder="${nit}" onchange="construirNombrePdf();"/> 
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                               <spring:message  code="contract.people.form.nit.msg"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="scsNit" />
                            </small>
                        </div>
                    </div>

                     <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="contract.people.form.nua.cua"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <spring:message  code="contract.people.form.nua.cua.msg" var="nua"/>
                            <form:input type="text" path="scsNuaCua"  class="form-control form-control-lg"  placeholder="${nua}"/> 
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                               <spring:message  code="contract.people.form.nua.cua.msg"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="scsNuaCua" />
                            </small>
                        </div>
                    </div>
                     
                    <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="contract.people.form.user.rupe"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <spring:message  code="contract.people.form.user.rupe.msg" var="urupe"/>
                            <form:input type="text" path="scsUsuarioRupe"  class="form-control form-control-lg"  placeholder="${urupe}"/> 
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                               <spring:message  code="contract.people.form.user.rupe.msg"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="scsUsuarioRupe" />
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="contract.people.form.password.rupe"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <spring:message  code="contract.people.form.password.rupe.msg" var="prupe"/>
                            <form:input type="text" path="scsClaveRupe"  class="form-control form-control-lg"  placeholder="${prupe}"/> 
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                               <spring:message  code="contract.people.form.password.rupe.msg"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="scsClaveRupe" />
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
                            <a href="<spring:url value="/aContratados/inicioContratados"/>" class="btn btn-yvela-green"><i class="fa fa-undo yvela" aria-hidden="true"></i>  <spring:message  code="expenses.btn.cancel"/></a>
                        </div> 
                    </div> 
                </form:form>
                <br><br>
                <hr align="left" noshade="noshade" size="10" width="100%" />
                
            </c:when>
        </c:choose>
        <!-- ---------------- Final registro contratados -->
      
        <!--------------- Inicio Modificar contratados ----------------------------------- -->
        <c:choose>
            <c:when test="${operation eq 'mod' }">
                
                <center><br>
                    <p class="h3"><i class="fa fa-folder-o" aria-hidden="true"></i> <spring:message  code="contracts.form.modification"/></p> 
                    <br>
                </center>
                
                 <form:form modelAttribute="scsPrsContratados" action="${confModCont}" enctype="multipart/form-data"  method="post">
                     <form:input type="hidden" path="ScsArchivosAdjuntos.idScsArchivoAdjunto" value="${scsPrsContratados.scsArchivosAdjuntos.idScsArchivoAdjunto}" />
                     <form:input type="hidden" path="idScsPrsContratado" value="${scsPrsContratados.idScsPrsContratado}" />
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
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="contract.people.form.nit"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <spring:message  code="contract.people.form.nit.msg" var="nit" />
                            <form:input type="text" path="scsNit"  class="form-control form-control-lg"  placeholder="${nit}" onchange="construirNombrePdf();"/> 
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                               <spring:message  code="contract.people.form.nit.msg"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="scsNit" />
                            </small>
                        </div>
                    </div>

                     <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="contract.people.form.nua.cua"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <spring:message  code="contract.people.form.nua.cua.msg" var="nua"/>
                            <form:input type="text" path="scsNuaCua"  class="form-control form-control-lg"  placeholder="${nua}"/> 
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                               <spring:message  code="contract.people.form.nua.cua.msg"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="scsNuaCua" />
                            </small>
                        </div>
                    </div>
                     
                    <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="contract.people.form.user.rupe"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <spring:message  code="contract.people.form.user.rupe.msg" var="urupe"/>
                            <form:input type="text" path="scsUsuarioRupe"  class="form-control form-control-lg"  placeholder="${urupe}"/> 
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                               <spring:message  code="contract.people.form.user.rupe.msg"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="scsUsuarioRupe" />
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="contract.people.form.password.rupe"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <spring:message  code="contract.people.form.password.rupe.msg" var="prupe"/>
                            <form:input type="text" path="scsClaveRupe"  class="form-control form-control-lg"  placeholder="${prupe}"/> 
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                               <spring:message  code="contract.people.form.password.rupe.msg"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="scsClaveRupe" />
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
                            <form:input class="form-control form-control-lg" path="nombreArchivo" placeholder="${adj}"  id="fileName1" value="${scsPrsContratados.scsArchivosAdjuntos.nombreArchivo}" />
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
                            <form:input class="form-control form-control-lg" path="gestion"  placeholder="${year}" />
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
                            <form:input class="form-control form-control-lg" path="periodo"  />
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
                            <a href="<spring:url value="/aContratados/inicioContratados"/>" class="btn btn-yvela-green"><i class="fa fa-undo yvela" aria-hidden="true"></i>  <spring:message  code="expenses.btn.cancel"/></a>
                        </div> 
                    </div>
                
                </form:form>
                <br><br>
                <hr align="left" noshade="noshade" size="10" width="100%" />
             </c:when>
        </c:choose>
        <!------------------------------------ Final Modificar contratados SICOES------------------------------------------- -->
        
        <!------------------------------------ Inicio Eliminar Contratados SICES------------------------------------------- -->
        <c:choose>
            <c:when test="${operation eq 'delet' }">
                <div id="" class="col-lg-12" >
                    <br>
                    <center><h5><i class="fa fa-trash-o" aria-hidden="true"></i>  <spring:message  code="contracts.form.elimination"/></h5></center>
                    <br>
                <div class="form-group row">
                 <div class="col-sm-8" id="combo1"> 
                   <form:form modelAttribute="scsPrsContratados" action="${urlDel}" enctype="multipart/form-data"  method="post">
                     <form:input type="hidden" path="ScsArchivosAdjuntos.idScsArchivoAdjunto" value="${scsPrsContratados.scsArchivosAdjuntos.idScsArchivoAdjunto}" />
                     <form:input type="hidden" path="idScsPrsContratado" value="${scsPrsContratados.idScsPrsContratado}" />
                     <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-4 col-form-label"><spring:message  code="contracts.form.official"/></label>
                        <div class="col-sm-8">
                            <form:select class="form-control form-control-lg" path="Personas.idPersona" id="personas" >
                                <c:forEach var="lp" items="${requestScope.lPersonas}">
                                    <form:option value="${lp.idPersona}">${lp.nombres} ${lp.paterno} ${lp.materno}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                     </div>
                                        
                    <div class="form-group row">
                        <label for="codigo" class="col-sm-4 col-form-label"><spring:message  code="contract.people.form.nit"/></label>
                        <div class="col-sm-8">
                            <spring:message  code="contract.people.form.nit.msg" var="nit" />
                            <form:input type="text" path="scsNit"  class="form-control form-control-lg"  placeholder="${nit}" onchange="construirNombrePdf();"/> 
                        </div>
                     </div>

                     <div class="form-group row">
                        <label for="codigo" class="col-sm-4 col-form-label"><spring:message  code="contract.people.form.nua.cua"/></label>
                        <div class="col-sm-8">
                            <spring:message  code="contract.people.form.nua.cua.msg" var="nua"/>
                            <form:input type="text" path="scsNuaCua"  class="form-control form-control-lg"  placeholder="${nua}"/> 
                        </div>
                    </div>
                     
                    <div class="form-group row">
                        <label for="codigo" class="col-sm-4 col-form-label"><spring:message  code="contract.people.form.user.rupe"/></label>
                        <div class="col-sm-8">
                            <spring:message  code="contract.people.form.user.rupe.msg" var="urupe"/>
                            <form:input type="text" path="scsUsuarioRupe"  class="form-control form-control-lg"  placeholder="${urupe}"/> 
                        </div>
                     </div>
                    <div class="form-group row">
                        <label for="codigo" class="col-sm-4 col-form-label"><spring:message  code="contract.people.form.password.rupe"/></label>
                        <div class="col-sm-8">
                            <spring:message  code="contract.people.form.password.rupe.msg" var="prupe"/>
                            <form:input type="text" path="scsClaveRupe"  class="form-control form-control-lg"  placeholder="${prupe}"/> 
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
                            <form:input class="form-control form-control-lg" path="nombreArchivo" placeholder="${adj}"  id="fileName1" value="${scsPrsContratados.scsArchivosAdjuntos.nombreArchivo}" />
                        </div>
                    </div>
                    
                    <div class="form-group row">
                        <spring:message  code="items.year" var="year"/>
                        <label for="sedes" class="col-sm-4 col-form-label"><spring:message  code="items.year"/></label>
                        <div class="col-sm-8" id="combo1"> 
                            <form:input class="form-control form-control-lg" path="gestion"  placeholder="${year}" />
                        </div>
                    </div>
                    <div class="form-group row">
                        <spring:message  code="items.period.msg" var="period"/>
                        <label for="sedes" class="col-sm-4 col-form-label"><spring:message  code="items.period"/></label>
                        <div class="col-sm-8" id="combo1"> 
                            <form:input class="form-control form-control-lg" path="periodo"  />
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
                          <a href="<spring:url value="/aContratados/inicioContratados"/>" class="btn btn-yvela-green"><i class="fa fa-undo" aria-hidden="true"></i>  <spring:message  code="expenses.btn.cancel"/></a>
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
                    <p class="h3"><i class="fa fa-folder-open-o" aria-hidden="true"></i> <spring:message  code="contract.people.form.list.title"/></p> 
                    <br>
                </center>
                <table  id="tContratos"  class="table" style="width:100%">
                    <thead>
                        <tr>
                            <th><spring:message  code="contracts.form.official"/></th>
                            <th><spring:message  code="contract.people.form.nit"/></th>
                            <th><spring:message  code="contract.people.form.nua.cua"/></th>
                            <th><spring:message  code="contract.people.form.user.rupe"/></th>
                            <th><spring:message  code="contract.people.form.password.rupe"/></th>
                            <th><spring:message  code="expenses.modify"/> </th>
                            <th><spring:message  code="expenses.remove"/></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:set var="cont" value="0" />
                        <c:forEach items="${lScsPrsContratados}" var="lSpr">
                            <c:set var="cont" value="${cont+1}" />
                            <tr>
                                <td>${lSpr.personas.nombres} ${lSpr.personas.paterno} ${lSpr.personas.materno}</td>
                                <td>${lSpr.scsNit} </td>
                                <td>${lSpr.scsNuaCua}</td>
                                <td>${lSpr.scsUsuarioRupe}</td>
                                <td>${lSpr.scsClaveRupe}</td>
                                <td>
                                    <form  method="post" action="${urlModCont}">
                                        <input type="hidden" name="idScsPrsContratado" value="${lSpr.idScsPrsContratado}">
                                        <span class="icon-input-btn"> <i class="fa fa-pencil-square-o yvela" aria-hidden="true"> </i> <input class="btn btn-success" type="submit"  value="<spring:message  code="expenses.modify"/>" > </span>
                                    </form>
                                </td>
                                <td>
                                    <form name="Delet" action="${iniElimCont}" method="post">
                                        <input type="hidden" name="idScsPrsContratado" value="${lSpr.idScsPrsContratado}">
                                        <span class="icon-input-btn"> <i class="fa fa-times yvela" aria-hidden="true"> </i> <input class="btn btn-danger" type="submit"  value="<spring:message  code="expenses.remove"/>"> </span>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                    <tfoot>
                        <tr>
                           <th><spring:message  code="contracts.form.official"/></th>
                            <th><spring:message  code="contract.people.form.nit"/></th>
                            <th><spring:message  code="contract.people.form.nua.cua"/></th>
                            <th><spring:message  code="contract.people.form.user.rupe"/></th>
                            <th><spring:message  code="contract.people.form.password.rupe"/></th>
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
        