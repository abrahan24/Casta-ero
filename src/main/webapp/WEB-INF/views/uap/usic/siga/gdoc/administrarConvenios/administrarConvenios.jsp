<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<head>
    <link rel="stylesheet" type="text/css"  href=" <c:url value="/resource/admIngresos/ingresos.css"/>" />
    <script src="<spring:url value="/resource/gdoc/resolucion.js" />" type="text/javascript"></script>
    <script type="text/javascript">
    </script>
    <script>
$(document).ready(function(){
    $('#summernote').summernote({
        height: 200,
    });

    $('span.note-icon-caret').remove();

    $('.note-editable').css('background', '#fff');
});
</script>
 </head>
<div class="container">      <section class="content-header">
        <div class="row">
            <div class="col-md-10 mb-3">
                 <p class="h1"> <i class="fa fa-money" aria-hidden="true"></i>   <spring:message  code="gdoc.convenio.titulo.modulo"/> </p>
            </div>
            <div class="col-sm-2 text-right" id="reporte">
                <c:choose>
                    <c:when test="${busqueda eq 'find' }">
                         <form  method="POST" action="${urlRIn}">
                            <span class="icon-input-btn"><i class="fa fa-user-plus yvela" aria-hidden="true"></i> <input type="submit" class="btn btn-success btn-lg" value="<spring:message  code="income.btn.new"/>"></span>
                        </form>
                    </c:when>
                </c:choose> 
            </div>
        </div>
        <hr align="left" noshade="noshade" size="10" width="100%" />
    </section>
<section> 
     <c:if test="${not empty error}">
        <div class="alert alert-danger">
            <spring:message  code="AbstractUserDetailsAuthenticationProvider.badCredentials"/>
        </div>
    </c:if>
       
    <!--Tabla lista de Todos los Ingresos Registrados -->

    <c:choose>
        <c:when test="${busqueda eq 'find' }">
            <center><br>
                <p class="h3"><i class="fa fa-list-alt" aria-hidden="true"></i> <spring:message  code="gdoc.convenio.titulo.tabla"/></p> 
               <br>
             </center>
         <table id="tComprobantes"  class="table" style="width:100%">
                        <thead>
                            <tr>
                                <th><spring:message  code="gdoc.convenio.form.representante"/></th>
                                <th><spring:message  code="gdoc.resol.form.nro.folio"/></th>
                                <th><spring:message  code="gdoc.convenio.form.nro.convenio"/></th>
                                <th><spring:message  code="items.year"/></th>
                                <th><spring:message  code="gdoc.resol.form.autoridad"/></th>
                                <th><spring:message  code="gdoc.convenio.form.objeto.convenio"/></th>
                                <th><spring:message  code="voucher.viem.pdf"/></th>
                                <th><spring:message  code="expenses.modify"/> </th>
                                <th><spring:message  code="expenses.remove"/></th>
                            </tr>
                        </thead>
                        <tbody>
                        
                            <c:set var="cont" value="0" />
                            <c:forEach items="${lConvenios}" var="lcnv">
                                  <c:set var="cont" value="${cont+1}" />
                                   <tr>
                                   <td>${lcnv.gdocRepresentantes.personas.paterno}</td>
                                    <td>${lcnv.nroFolio}</td>
                                    <td>${lcnv.nroConvenio}</td>
                                    <td>${lcnv.gestion}</td>
                                    <td>${lcnv.gdocAutoridades.personas.nombres} ${lres.gdocAutoridades.personas.paterno} ${lres.gdocAutoridades.personas.materno}</td>
                                    <td>${lcnv.objetoConvenio}</td>
                                    <td>
                                        <a class="btn add" href="<spring:url value="/convenios/openFile/${lcnv.idGdocConvenio}"/>" target="blank"><i class="fa fa-plus-square-o" aria-hidden="true"></i>  <spring:message  code="voucher.viem.pdf"/></a>
                                    </td>
                                    <td>
                                        <form  method="post" action="${urlMod}">
                                            <input type="hidden" name="idGdocConvenio" value="${lcnv.idGdocConvenio}">
                                            <span class="icon-input-btn"> <i class="fa fa-pencil-square-o yvela" aria-hidden="true"> </i> <input class="btn btn-success" type="submit"  value="<spring:message  code="expenses.modify"/>" > </span>
                                        </form>
                                    </td>
                                    <td>
                                        <form name="Delet" action="${urlEI}" method="post">
                                            <input type="hidden" name="idGdocConvenio" value="${lcnv.idGdocConvenio}">
                                            <span class="icon-input-btn"> <i class="fa fa-times yvela" aria-hidden="true"> </i> <input class="btn btn-danger" type="submit"  value="<spring:message  code="expenses.remove"/>"> </span>
                                        </form>
                                    </td>
                                </tr>
                          </c:forEach>
                            
                        </tbody>
                        <tfoot>
                            <tr>
                                 <th><spring:message  code="gdoc.convenio.form.representante"/></th>
                                <th><spring:message  code="gdoc.resol.form.nro.folio"/></th>
                                <th><spring:message  code="gdoc.convenio.form.nro.convenio"/></th>
                                <th><spring:message  code="items.year"/></th>
                                <th><spring:message  code="gdoc.resol.form.autoridad"/></th>
                                <th><spring:message  code="gdoc.convenio.form.objeto.convenio"/></th>
                                <th><spring:message  code="voucher.viem.pdf"/></th>
                                <th><spring:message  code="expenses.modify"/> </th>
                                <th><spring:message  code="expenses.remove"/></th>
                            </tr>
                        </tfoot>
                    </table>
         
        </c:when>
    </c:choose>
  
          <!----------------Formulario Registro Convenios ======================================================== -->
    <c:choose>
        <c:when test="${operation eq 'reg' }">
             <center> <br><p class="h3"><i class="fa fa-th" aria-hidden="true"></i> <spring:message  code="gdoc.convenio.titulo.form.registro"/></p> </center>
            <br>
            <form:form modelAttribute="gdocConvenios" action="${nConv}" method="post" enctype="multipart/form-data">
               <form:hidden path="GdocConsejos.idGdocConsejo"  id="idConsejo" value="${bGdocConsejos.idGdocConsejo}" />
               <form:hidden path="GdocArchivosAdjuntos.idGeodcArchivoAdjunto"  id="idConsejo" value="1" />
               <form:hidden path="gestion"  id="gestion" value="${gestion}" />
               
                              
                     <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-2 col-form-label"><spring:message  code="gdoc.resol.form.autoridad"/></label>
                        <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <form:select class="form-control form-control-lg" path="GdocAutoridades.idGdocAutoridad" id="idAautoridad">
                                <c:forEach var="laut" items="${requestScope.lAutoridades}">
                                    <form:option value="${laut.idGdocAutoridad}">${laut.personas.nombres} ${laut.personas.paterno} ${laut.personas.materno}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                  <spring:message  code="gdoc.resol.form.msg.autoridad"/>
                            </small>
                            <small id="ci" class="form-text text-muted formFieldError">
                                <form:errors path="GdocAutoridades.idGdocAutoridad" cssClass="alert alert-danger"/>
                            </small>
                        </div>
                    </div>
                       <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-2 col-form-label"><spring:message  code="gdoc.convenio.form.representante"/></label>
                        <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <form:select class="form-control form-control-lg" path="GdocRepresentantes.idGdocRepresentante" id="idRepresntante">
                                <c:forEach var="lRep" items="${requestScope.lRepresentante}">
                                    <form:option value="${lRep.idGdocRepresentante}">${lRep.personas.nombres} ${lRep.personas.paterno} ${lRep.personas.materno}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <spring:message  code="gdoc.convenio.form.representante.msg"/>
                            </small>
                            <small id="ci" class="form-text text-muted formFieldError">
                                <form:errors path="GdocRepresentantes.idGdocRepresentante" cssClass="alert alert-danger"/>
                            </small>
                        </div>
                    </div>
                     <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-2 col-form-label"><spring:message  code="gdoc.convenio.form.tipo.convenio"/></label>
                        <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <form:select class="form-control form-control-lg" path="GdocTiposConvenios.idGdocTipoConvenio" id="idTipoconvenio">
                                <c:forEach var="ltpc" items="${requestScope.lTiposConvenios}">
                                    <form:option value="${ltpc.idGdocTipoConvenio}">${ltpc.tipoConvenio}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                               <spring:message  code="gdoc.convenio.form.tipo.convenio.msg"/>
                            </small>
                            <small id="ci" class="form-text text-muted formFieldError">
                                <form:errors path="GdocTiposConvenios.idGdocTipoConvenio" cssClass="alert alert-danger"/>
                            </small>
                        </div>
                    </div>
                     <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-2 col-form-label"><spring:message  code="institucion.institucion"/></label>
                        <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <form:select class="form-control form-control-lg" path="Instituciones.idInstitucion" id="idInstitucion">
                                <c:forEach var="lins" items="${requestScope.lInstituciones}">
                                    <form:option value="${lins.idInstitucion}">${lins.institucion}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <spring:message  code="institucion.institucion"/>
                            </small>
                            <small id="ci" class="form-text text-muted formFieldError">
                                <form:errors path="Instituciones.idInstitucion" cssClass="alert alert-danger"/>
                            </small>
                        </div>
                    </div>
              <div class="form-group row">
                    <spring:message  code="gdoc.convenio.form.msg.nro.convenio" var="nCnv"/>
                    <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="gdoc.convenio.form.nro.convenio"/></label>
                    <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                    <div class="col-sm-6" id="combo1"> 
                        <form:input class="form-control form-control-lg" path="nroConvenio"  id="nroCnv" onkeyUp="return ValNumero(this);" placeholder="${nCnv}" />
                     </div>
                    <div class="col-sm-3" id="combo1"> 
                        <small id="emailHelp" class="form-text text-muted">
                            <spring:message  code="gdoc.convenio.form.msg.nro.convenio"/>
                         </small>
                          <small id="ci" class="form-text text-muted formFieldError">
                                <form:errors path="nroConvenio" cssClass="alert alert-danger"/>
                            </small>
                     </div>
                </div>
                  <div class="form-group row">
                    <spring:message  code="gdoc.resol.form.msg.nro.folio" var="nfolio"/>
                    <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="gdoc.resol.form.nro.folio"/></label>
                    <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                    <div class="col-sm-6" id="combo1"> 
                        <form:input class="form-control form-control-lg" path="nroFolio"  id="nFolio" placeholder="${nfolio}"  onchange="construirNombrePdfCnv();"/>
                     </div>
                    <div class="col-sm-3" id="combo1"> 
                        <small id="emailHelp" class="form-text text-muted">
                            <spring:message  code="gdoc.convenio.form.objeto.convenio.msg"/>
                         </small>
                          <small id="ci" class="form-text text-muted formFieldError">
                                <form:errors path="nroFolio" cssClass="alert alert-danger"/>
                            </small>
                     </div>
                </div>
                  <div class="form-group row">
                    <spring:message  code="gdoc.convenio.form.objeto.convenio.msg" var="Obj"/>
                    <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="gdoc.convenio.form.objeto.convenio"/></label>
                    <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                    <div class="col-sm-6" id="combo1"> 
                        <form:input class="form-control form-control-lg" path="objetoConvenio"  id="nroRes" placeholder="${obj}" />
                     </div>
                    <div class="col-sm-3" id="combo1"> 
                        <small id="emailHelp" class="form-text text-muted">
                            <spring:message  code="gdoc.convenio.form.objeto.convenio.msg"/>
                         </small>
                          <small id="ci" class="form-text text-muted formFieldError">
                                <form:errors path="objetoConvenio" cssClass="alert alert-danger"/>
                            </small>
                     </div>
                </div>
                <div class="form-group row">
                    <spring:message  code="report.account.dateStart" var="fini"/>
                    <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="report.account.dateStart"/></label>
                   <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                    <div class="col-sm-6" id="combo1"> 
                          <form:input type="date" path="fecInicio" class="form-control form-control-lg"    placeholder="${fini}"/>
                     </div>
                    <div class="col-sm-3" id="combo1"> 
                        <small id="emailHelp" class="form-text text-muted">
                            <spring:message  code="report.account.dateStart"/>
                         </small>
                          <small id="ci" class="form-text text-muted formFieldError">
                                <form:errors path="fecInicio" cssClass="alert alert-danger"/>
                            </small>
                     </div>
                </div>
                <div class="form-group row">
                    <spring:message  code="report.account.dateFinish" var="fin"/>
                    <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="report.account.dateFinish"/></label>
                   <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                    <div class="col-sm-6" id="combo1"> 
                          <form:input type="date" path="fecFinal" class="form-control form-control-lg"    placeholder="${ingreso}"/>
                     </div>
                    <div class="col-sm-3" id="combo1"> 
                        <small id="emailHelp" class="form-text text-muted">
                            <spring:message  code="report.account.dateFinish"/>
                         </small>
                          <small id="ci" class="form-text text-muted formFieldError">
                                <form:errors path="fecFinal" cssClass="alert alert-danger"/>
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
                            <a href="<spring:url value="/convenios/inicioConvenios"/>" class="btn btn-yvela-green"><i class="fa fa-undo yvela" aria-hidden="true"></i>  <spring:message  code="expenses.btn.cancel"/></a>
                        </div> 
                    </div>
             </form:form>
         </c:when>
    </c:choose>
        <!-----------------------Fin Formulario Registro Ingresosss ======================================================== -->
        
         <!-----------------------Inicio Formulario Modificar Convenios  ======================================================== -->
          <c:choose>
        <c:when test="${operation eq 'mod' }">
             <center><br> <p class="h3"><i class="fa fa-th" aria-hidden="true"></i> <spring:message  code="gdoc.convenio.titulo.form.modificar"/></p> </center>
            <br>
            <form:form modelAttribute="gdocConvenios" action="${nConv}" method="post" enctype="multipart/form-data">
               <form:hidden path="GdocConsejos.idGdocConsejo"  id="idConsejo" value="${bGdocConsejos.idGdocConsejo}" />
               <form:hidden path="GdocArchivosAdjuntos.idGeodcArchivoAdjunto"  id="idAa" value="${gdocResoluciones.gdocArchivosAdjuntos.idGeodcArchivoAdjunto}" />
               <form:hidden path="idGdocConvenio"  id="idConvenio" value="${gdocConvenios.idGdocConvenio}" />
               
               <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-2 col-form-label"><spring:message  code="gdoc.resol.form.autoridad"/></label>
                        <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <form:select class="form-control form-control-lg" path="GdocAutoridades.idGdocAutoridad" id="idAautoridad">
                                <c:forEach var="laut" items="${requestScope.lAutoridades}">
                                    <form:option value="${laut.idGdocAutoridad}">${laut.personas.nombres} ${laut.personas.paterno} ${laut.personas.materno}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                  <spring:message  code="gdoc.resol.form.msg.autoridad"/>
                            </small>
                            <small id="ci" class="form-text text-muted formFieldError">
                                <form:errors path="GdocAutoridades.idGdocAutoridad" cssClass="alert alert-danger"/>
                            </small>
                        </div>
                    </div>
                       <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-2 col-form-label"><spring:message  code="gdoc.convenio.form.representante"/></label>
                        <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <form:select class="form-control form-control-lg" path="GdocRepresentantes.idGdocRepresentante" id="idRepresntante">
                                <c:forEach var="lRep" items="${requestScope.lRepresentante}">
                                    <form:option value="${lRep.idGdocRepresentante}">${lRep.personas.nombres} ${lRep.personas.paterno} ${lRep.personas.materno}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <spring:message  code="gdoc.convenio.form.representante.msg"/>
                            </small>
                            <small id="ci" class="form-text text-muted formFieldError">
                                <form:errors path="GdocRepresentantes.idGdocRepresentante" cssClass="alert alert-danger"/>
                            </small>
                        </div>
                    </div>
                     <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-2 col-form-label"><spring:message  code="gdoc.convenio.form.tipo.convenio"/></label>
                        <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <form:select class="form-control form-control-lg" path="GdocTiposConvenios.idGdocTipoConvenio" id="idTipoconvenio">
                                <c:forEach var="ltpc" items="${requestScope.lTiposConvenios}">
                                    <form:option value="${ltpc.idGdocTipoConvenio}">${ltpc.tipoConvenio}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                               <spring:message  code="gdoc.convenio.form.tipo.convenio.msg"/>
                            </small>
                            <small id="ci" class="form-text text-muted formFieldError">
                                <form:errors path="GdocTiposConvenios.idGdocTipoConvenio" cssClass="alert alert-danger"/>
                            </small>
                        </div>
                    </div>
                     <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-2 col-form-label"><spring:message  code="institucion.institucion"/></label>
                        <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <form:select class="form-control form-control-lg" path="Instituciones.idInstitucion" id="idInstitucion">
                                <c:forEach var="lins" items="${requestScope.lInstituciones}">
                                    <form:option value="${lins.idInstitucion}">${lins.institucion}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <spring:message  code="institucion.institucion"/>
                            </small>
                            <small id="ci" class="form-text text-muted formFieldError">
                                <form:errors path="Instituciones.idInstitucion" cssClass="alert alert-danger"/>
                            </small>
                        </div>
                    </div>
              <div class="form-group row">
                    <spring:message  code="gdoc.convenio.form.msg.nro.convenio" var="nCnv"/>
                    <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="gdoc.convenio.form.nro.convenio"/></label>
                    <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                    <div class="col-sm-6" id="combo1"> 
                        <form:input class="form-control form-control-lg" path="nroConvenio"  id="nroCnv2" onkeyUp="return ValNumero(this);" placeholder="${nCnv}" />
                     </div>
                    <div class="col-sm-3" id="combo1"> 
                        <small id="emailHelp" class="form-text text-muted">
                            <spring:message  code="gdoc.convenio.form.msg.nro.convenio"/>
                         </small>
                          <small id="ci" class="form-text text-muted formFieldError">
                                <form:errors path="nroConvenio" cssClass="alert alert-danger"/>
                            </small>
                     </div>
                </div>
                  <div class="form-group row">
                    <spring:message  code="gdoc.resol.form.msg.nro.folio" var="nfolio"/>
                    <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="gdoc.resol.form.nro.folio"/></label>
                    <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                    <div class="col-sm-6" id="combo1"> 
                        <form:input class="form-control form-control-lg" path="nroFolio"  id="nFolio2" placeholder="${nfolio}"  onchange="construirNombrePdfMCnv();"/>
                     </div>
                    <div class="col-sm-3" id="combo1"> 
                        <small id="emailHelp" class="form-text text-muted">
                            <spring:message  code="gdoc.convenio.form.objeto.convenio.msg"/>
                         </small>
                          <small id="ci" class="form-text text-muted formFieldError">
                                <form:errors path="nroFolio" cssClass="alert alert-danger"/>
                            </small>
                     </div>
                </div>
                  <div class="form-group row">
                    <spring:message  code="gdoc.convenio.form.objeto.convenio.msg" var="Obj"/>
                    <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="gdoc.convenio.form.objeto.convenio"/></label>
                    <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                    <div class="col-sm-6" id="combo1"> 
                        <form:input class="form-control form-control-lg" path="objetoConvenio"  id="nroRes" placeholder="${obj}" />
                     </div>
                    <div class="col-sm-3" id="combo1"> 
                        <small id="emailHelp" class="form-text text-muted">
                            <spring:message  code="gdoc.convenio.form.objeto.convenio.msg"/>
                         </small>
                          <small id="ci" class="form-text text-muted formFieldError">
                                <form:errors path="objetoConvenio" cssClass="alert alert-danger"/>
                            </small>
                     </div>
                </div>
                <div class="form-group row">
                    <spring:message  code="report.account.dateStart" var="fini"/>
                    <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="report.account.dateStart"/></label>
                   <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                    <div class="col-sm-6" id="combo1"> 
                          <form:input type="date" path="fecInicio" class="form-control form-control-lg"    placeholder="${fini}"/>
                     </div>
                    <div class="col-sm-3" id="combo1"> 
                        <small id="emailHelp" class="form-text text-muted">
                            <spring:message  code="report.account.dateStart"/>
                         </small>
                          <small id="ci" class="form-text text-muted formFieldError">
                                <form:errors path="fecInicio" cssClass="alert alert-danger"/>
                            </small>
                     </div>
                </div>
                <div class="form-group row">
                    <spring:message  code="report.account.dateFinish" var="fin"/>
                    <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="report.account.dateFinish"/></label>
                   <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                    <div class="col-sm-6" id="combo1"> 
                          <form:input type="date" path="fecFinal" class="form-control form-control-lg"    placeholder="${ingreso}"/>
                     </div>
                    <div class="col-sm-3" id="combo1"> 
                        <small id="emailHelp" class="form-text text-muted">
                            <spring:message  code="report.account.dateFinish"/>
                         </small>
                          <small id="ci" class="form-text text-muted formFieldError">
                                <form:errors path="fecFinal" cssClass="alert alert-danger"/>
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
                            <form:input class="form-control form-control-lg" path="nombreArchivo" placeholder="${adj}"  id="fileName2"  value="${gdocConvenios.gdocArchivosAdjuntos.nombreArchivo}"/>
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
                            <a href="<spring:url value="/convenios/inicioConvenios"/>" class="btn btn-yvela-green"><i class="fa fa-undo yvela" aria-hidden="true"></i>  <spring:message  code="expenses.btn.cancel"/></a>
                        </div> 
                    </div>
             </form:form>
         </c:when>
    </c:choose>
                  
         <!----------------------- Fin Formulario Modificar Convenios  ======================================================== -->
         
         <!----------------------- Inicio Formulario Eliminar Convenios  ======================================================== -->
           <c:choose>
            <c:when test="${operation eq 'delet' }">
                <div id="" class="col-lg-12" >
                              <center><h5><i class="fa fa-trash-o" aria-hidden="true"></i>  <spring:message  code="gdoc.convenio.titulo.form.eliminar"/></h5></center>
                    <br>
                <div class="form-group row">
                 <div class="col-sm-8" id="combo1"> 
                   <form:form modelAttribute="gdocConvenios" action="${urlDel}" enctype="multipart/form-data"  method="post">
                       <form:hidden path="GdocConsejos.idGdocConsejo"  id="idConsejo" value="${bGdocConsejos.idGdocConsejo}" />
                      <form:hidden path="GdocArchivosAdjuntos.idGeodcArchivoAdjunto"  id="idAa" value="${gdocResoluciones.gdocArchivosAdjuntos.idGeodcArchivoAdjunto}" />
                       <form:hidden path="idGdocConvenio"  id="idConvenio" value="${gdocConvenios.idGdocConvenio}" />
                       <input type="text" name="sigla"  id="sigla" value="${bGdocConsejos.sigla}" />
                       
                 <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-4 col-form-label"> <spring:message  code="gdoc.resol.form.autoridad"/></label>
                        <div class="col-sm-8">
                               <form:select class="form-control form-control-lg" path="GdocAutoridades.idGdocAutoridad" id="idAautoridad">
                                <c:forEach var="laut" items="${requestScope.lAutoridades}">
                                    <form:option value="${laut.idGdocAutoridad}">${laut.personas.nombres} ${laut.personas.paterno} ${laut.personas.materno}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                     </div>
                    <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-4 col-form-label"> <spring:message  code="gdoc.convenio.form.representante"/></label>
                        <div class="col-sm-8">
                               <form:select class="form-control form-control-lg" path="GdocRepresentantes.idGdocRepresentante" id="idRepresntante">
                                <c:forEach var="lRep" items="${requestScope.lRepresentante}">
                                    <form:option value="${lRep.idGdocRepresentante}">${lRep.personas.nombres} ${lRep.personas.paterno} ${lRep.personas.materno}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                     </div>
                     <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-4 col-form-label"> <spring:message  code="gdoc.convenio.form.tipo.convenio"/></label>
                        <div class="col-sm-8">
                               <form:select class="form-control form-control-lg" path="GdocTiposConvenios.idGdocTipoConvenio" id="idTipoconvenio">
                                <c:forEach var="ltpc" items="${requestScope.lTiposConvenios}">
                                    <form:option value="${ltpc.idGdocTipoConvenio}">${ltpc.tipoConvenio}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                     </div>
                     <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-4 col-form-label"> <spring:message  code="institucion.institucion"/></label>
                        <div class="col-sm-8">
                                 <form:select class="form-control form-control-lg" path="Instituciones.idInstitucion" id="idInstitucion">
                                <c:forEach var="lins" items="${requestScope.lInstituciones}">
                                    <form:option value="${lins.idInstitucion}">${lins.institucion}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                     </div>
                  <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-4 col-form-label"> <spring:message  code="gdoc.convenio.form.nro.convenio"/></label>
                        <div class="col-sm-8">
                              <spring:message  code="gdoc.convenio.form.msg.nro.convenio" var="nCnv"/>
                               <form:input class="form-control form-control-lg" path="nroConvenio"  id="nroCnv" onkeyUp="return ValNumero(this);" placeholder="${nCnv}" />
                        </div>
                     </div>    
                  <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-4 col-form-label"> <spring:message  code="gdoc.resol.form.nro.folio"/></label>
                        <div class="col-sm-8">
                               <spring:message  code="gdoc.resol.form.msg.nro.folio" var="nfolio"/>
                                <form:input class="form-control form-control-lg" path="nroFolio"  id="nFolio" placeholder="${nfolio}"  onchange="construirNombrePdf();"/>
                        </div>
                     </div>
                 <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-4 col-form-label"> <spring:message  code="report.account.dateStart"/></label>
                        <div class="col-sm-8">
                               <spring:message  code="report.account.dateStart" var="fini"/>
                                <form:input type="date" path="fecInicio" class="form-control form-control-lg"    placeholder="${fini}"/>
                        </div>
                     </div>                         
              <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-4 col-form-label"> <spring:message  code="report.account.dateFinish"/></label>
                        <div class="col-sm-8">
                                <spring:message  code="report.account.dateFinish" var="fin"/>
                                <form:input type="date" path="fecFinal" class="form-control form-control-lg"    placeholder="${ingreso}"/>
                        </div>
                     </div>
                   <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-4 col-form-label"> <spring:message  code="contracts.form.attached.file"/></label>
                        <div class="col-sm-8">
                                <form:input type="file" path="file" id="file" class="form-control form-control-lg input-sm"/>
                        </div>
                     </div>
                  <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-4 col-form-label"> <spring:message  code="voucher.file.name"/></label>
                        <div class="col-sm-8">
                                 <spring:message  code="voucher.file.name.msg" var="adj"/>
                                  <form:input class="form-control form-control-lg" path="nombreArchivo" placeholder="${adj}"  id="fileName1"  value="${gdocConvenios.gdocArchivosAdjuntos.nombreArchivo}" />
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
                          <a href="<spring:url value="/convenios/inicioConvenios"/>" class="btn btn-yvela-green"><i class="fa fa-undo" aria-hidden="true"></i>  <spring:message  code="expenses.btn.cancel"/></a>
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
                     
         
         <!----------------------- Fin Formulario Eliminar Convenios  ======================================================== -->
              <br><br>
         
             <hr align="left" noshade="noshade" size="10" width="100%" />
   </section>
</div>
