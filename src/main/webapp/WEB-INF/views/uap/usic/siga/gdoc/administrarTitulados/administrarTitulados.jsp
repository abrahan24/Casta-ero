<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<head>
    <link rel="stylesheet" type="text/css"  href=" <c:url value="/resource/admIngresos/ingresos.css"/>" />
    <script src="<spring:url value="/resource/gdoc/resolucion.js" />" type="text/javascript"></script>
    <script type="text/javascript">
    </script>
 </head>
<div class="container">      <section class="content-header">
        <div class="row">
            <div class="col-md-10 mb-3">
                 <p class="h1"> <i class="fa fa-database" aria-hidden="true"></i> <spring:message  code="gdoc.titulado.titulo.modulo"/> </p>
            </div>
            <div class="col-sm-2 text-right" id="reporte">
                <c:choose>
                    <c:when test="${busqueda eq 'find' }">
                         <form  method="POST" action="${urlNuevo}">
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
                <p class="h3"><i class="fa fa-list-alt" aria-hidden="true"></i> <spring:message  code="gdoc.titulado.titulo.tabla"/></p> 
             </center>
         <table id="tComprobantes"  class="table" style="width:100%">
                        <thead>
                            <tr>
                                <th><spring:message  code="gdoc.titulado.form.nombre.titulado"/></th>
                                <th><spring:message  code="gdoc.titulado.form.nro.titulo"/></th>
                                <th><spring:message  code="gdoc.titulado.form.valordep.titulado"/></th>
                                <th><spring:message  code="voucher.viem.pdf"/></th>
                                <th><spring:message  code="expenses.modify"/> </th>
                                <th><spring:message  code="expenses.remove"/></th>
                            </tr>
                        </thead>
                        <tbody>
                        
                            <c:set var="cont" value="0" />
                            <c:forEach items="${lTitulados}" var="ltit">
                                  <c:set var="cont" value="${cont+1}" />
                                   <tr>
                                    <td>${ltit.personas.getNombres()} ${ltit.personas.getPaterno()} ${ltit.personas.getMaterno()}</td>
                                    <td>${ltit.gdocTitulos.nroTitulo}</td>
                                    <td>${ltit.valorDeposito}</td>
                                    
                                    
                                    <td>
                                        <a class="btn add" href="<spring:url value="/titulados/openFile/${ltit.idGdocTitulado}"/>" target="blank"><i class="fa fa-plus-square-o" aria-hidden="true"></i>  <spring:message  code="voucher.viem.pdf"/></a>
                                    </td>
                                    <td>
                                        <form  method="post" action="${urlMod}">
                                            <input type="hidden" name="idGdocTitulado" value="${ltit.idGdocTitulado}">
                                            <span class="icon-input-btn"> <i class="fa fa-pencil-square-o yvela" aria-hidden="true"> </i> <input class="btn btn-success" type="submit"  value="<spring:message  code="expenses.modify"/>" > </span>
                                        </form>
                                    </td>
                                    <td>
                                        <form name="Delet" action="${urlEli}" method="post">
                                            <input type="hidden" name="idGdocTitulado" value="${ltit.idGdocTitulado}">
                                            <span class="icon-input-btn"> <i class="fa fa-times yvela" aria-hidden="true"> </i> <input class="btn btn-danger" type="submit"  value="<spring:message  code="expenses.remove"/>"> </span>
                                        </form>
                                    </td>
                                </tr>
                          </c:forEach>
                            
                        </tbody>
                        <tfoot>
                            <tr>
                                 <th><spring:message code="gdoc.titulado.form.nombre.titulado"/></th>
                                <th><spring:message  code="gdoc.titulado.form.nro.titulo"/></th>
                                <th><spring:message  code="gdoc.titulado.form.valordep.titulado"/></th>
                                <th><spring:message  code="voucher.viem.pdf"/></th>
                                <th><spring:message  code="expenses.modify"/> </th>
                                <th><spring:message  code="expenses.remove"/></th>
                            </tr>
                        </tfoot>
                    </table>
         
        </c:when>
    </c:choose>
  
          <!----------------Formulario Registro Titulados ======================================================== -->
<br>
  
      <c:choose>
        <c:when test="${operation eq 'reg' }">
             <center> <p class="h3"><i class="fa fa-th" aria-hidden="true"></i><spring:message  code="gdoc.titulado.titulo.form.registro"/></p> </center>
            <br>
            <form:form modelAttribute="gdocTitulados" action="${regTitulado}" method="post" enctype="multipart/form-data">
               <form:hidden path="GdocConsejos.idGdocConsejo"  id="idConsejo" value="${bGdocConsejos.idGdocConsejo}" />
               <form:hidden path="GdocArchivosAdjuntos.idGeodcArchivoAdjunto"  id="idConsejo" value="1" />
               
               
				<div class="form-group row">
                        <label for="ciExpedido" class="col-sm-2 col-form-label"><spring:message  code="gdoc.titulado.form.nombre.titulado"/></label>
                        <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <form:select class="form-control form-control-lg" path="Personas.idPersona" id="idPersona">
                                <c:forEach var="lp" items="${requestScope.lPersonas}">
                                    <form:option value="${lp.idPersona}">${lp.getNombres()} ${lp.getPaterno()} ${lp.getMaterno()}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                  <spring:message  code="gdoc.titulado.form.msg.nombre.titulado"/>
                            </small>
                            <small id="ci" class="form-text text-muted formFieldError">
                                <form:errors path="Personas.idPersona" cssClass="alert alert-danger"/>
                            </small>
                        </div>
                    </div>
                 
      
                  
                 <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-2 col-form-label"><spring:message  code="gdoc.titulado.form.nro.titulo"/></label>
                        <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <form:select class="form-control form-control-lg" path="GdocTitulos.idGdocTitulo" id="idTitulos">
                                <c:forEach var="lti" items="${requestScope.lTitulos}">
                                    <form:option value="${lti.idGdocTitulo}">${lti.nroTitulo}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <a class="btn add" href="#" data-toggle="modal" data-target="#formCiExpedido"><i class="fa fa-plus-square-o" aria-hidden="true"></i>  <spring:message  code="people.add"/></a></small>
                                <small id="ci" class="form-text text-muted formFieldError">
                               <form:errors path="GdocTitulos.idGdocTitulo" cssClass="alert alert-danger"/>
                           </small>
                        </div>
                    </div>
                   
                    
                    <div class="form-group row">
                    <spring:message  code="gdoc.titulado.form.msg.valordep.titulado" var="nTitutlos"/>
                    <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="gdoc.titulado.form.valordep.titulado"/></label>
                    <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                    <div class="col-sm-6" id="combo1"> 
                        <form:input class="form-control form-control-lg" path="valorDeposito"  id="nroTls" onkeyUp="return ValNumero(this);" placeholder="${nTitutlos}" />
                     </div>
                    <div class="col-sm-3" id="combo1"> 
                        <small id="emailHelp" class="form-text text-muted">
                            <spring:message  code="gdoc.titulado.form.msg.valordep.titulado"/>
                         </small>
                          <small id="ci" class="form-text text-muted formFieldError">
                                <form:errors path="valorDeposito" cssClass="alert alert-danger"/>
                            </small>
                     </div>
                </div>
                 
                 
                 <div class="form-group row">
                    <spring:message  code="report.account.dateStart" var="fecex"/>
                    <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="report.account.dateStart"/></label>
                   <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                    <div class="col-sm-6" id="combo1"> 
                          <form:input type="date" path="fecExpedido" class="form-control form-control-lg"    placeholder="${fecex}"/>
                     </div>
                    <div class="col-sm-3" id="combo1"> 
                        <small id="emailHelp" class="form-text text-muted">
                            <spring:message  code="report.account.dateStart"/>
                         </small>
                          <small id="ci" class="form-text text-muted formFieldError">
                                <form:errors path="fecExpedido" cssClass="alert alert-danger"/>
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
                            <a href="<spring:url value="/titulados/inicioTitulados"/>" class="btn btn-yvela-green"><i class="fa fa-undo yvela" aria-hidden="true"></i>  <spring:message  code="expenses.btn.cancel"/></a>
                        </div> 
                    </div>
             </form:form>
         </c:when>
    </c:choose>
    
    
        <!-----------------------Fin Formulario Registro Ingresosss ======================================================== -->
            
   <!-----------------------Inicio Formulario Modificar Titulados  ======================================================== -->
  <c:choose>
        <c:when test="${operation eq 'mod' }">
             <center> <p class="h3"><i class="fa fa-th" aria-hidden="true"></i>  <spring:message  code="gdoc.titulado.titulo.form.modificar"/></p> </center>
            <br>
            <form:form modelAttribute="gdocTitulados" action="${urlUp}" method="post" enctype="multipart/form-data">
            
               <form:hidden path="GdocConsejos.idGdocConsejo"  id="idConsejo" value="${bGdocConsejos.idGdocConsejo}" />
               <form:hidden path="idGdocTitulado"  id="idtitulado" value="${gdocTitulados.idGdocTitulado}" />
                 <form:hidden path="GdocArchivosAdjuntos.idGeodcArchivoAdjunto"  id="idAa" value="${gdocTitulados.gdocArchivosAdjuntos.idGeodcArchivoAdjunto}" />
                <input type="text" name="sigla"  id="sigla" value="${bGdocConsejos.sigla}" />
                
                
               <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-2 col-form-label"><spring:message  code="gdoc.titulado.form.nombre.titulado"/></label>
                        <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <form:select class="form-control form-control-lg" path="Personas.idPersona" id="idPersona">
                                <c:forEach var="lp" items="${requestScope.lPersonas}">
                                    <form:option value="${lp.idPersona}">${lp.getNombres()} ${lp.getPaterno()} ${lp.getMaterno()}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                  <spring:message  code="gdoc.titulado.form.msg.nombre.titulado"/>
                            </small>
                            <small id="ci" class="form-text text-muted formFieldError">
                                <form:errors path="Personas.idPersona" cssClass="alert alert-danger"/>
                            </small>
                        </div>
                    </div>
                 
      
                  
                 <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-2 col-form-label"><spring:message  code="gdoc.titulado.form.nro.titulo"/></label>
                        <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <form:select class="form-control form-control-lg" path="GdocTitulos.idGdocTitulo" id="idTitulos">
                                <c:forEach var="lti" items="${requestScope.lTitulos}">
                                    <form:option value="${lti.idGdocTitulo}">${lti.nroTitulo}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <a class="btn add" href="#" data-toggle="modal" data-target="#formCiExpedido"><i class="fa fa-plus-square-o" aria-hidden="true"></i>  <spring:message  code="people.add"/></a></small>
                                <small id="ci" class="form-text text-muted formFieldError">
                               <form:errors path="GdocTitulos.idGdocTitulo" cssClass="alert alert-danger"/>
                           </small>
                        </div>
                    </div>
                   
                    
                    <div class="form-group row">
                    <spring:message  code="gdoc.titulado.form.msg.valordep.titulado" var="nTitutlos"/>
                    <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="gdoc.titulado.form.valordep.titulado"/></label>
                    <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                    <div class="col-sm-6" id="combo1"> 
                        <form:input class="form-control form-control-lg" path="valorDeposito"  id="nroTls" onkeyUp="return ValNumero(this);" placeholder="${nTitutlos}" />
                     </div>
                    <div class="col-sm-3" id="combo1"> 
                        <small id="emailHelp" class="form-text text-muted">
                            <spring:message  code="gdoc.titulado.form.msg.valordep.titulado"/>
                         </small>
                          <small id="ci" class="form-text text-muted formFieldError">
                                <form:errors path="valorDeposito" cssClass="alert alert-danger"/>
                            </small>
                     </div>
                </div>
                 
                 
                 <div class="form-group row">
                    <spring:message  code="report.account.dateStart" var="fecex"/>
                    <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="report.account.dateStart"/></label>
                   <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                    <div class="col-sm-6" id="combo1"> 
                          <form:input type="date" path="fecExpedido" class="form-control form-control-lg"    placeholder="${fecex}"/>
                     </div>
                    <div class="col-sm-3" id="combo1"> 
                        <small id="emailHelp" class="form-text text-muted">
                            <spring:message  code="report.account.dateStart"/>
                         </small>
                          <small id="ci" class="form-text text-muted formFieldError">
                                <form:errors path="fecExpedido" cssClass="alert alert-danger"/>
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
                            <a href="<spring:url value="/titulados/inicioTitulados"/>" class="btn btn-yvela-green"><i class="fa fa-undo yvela" aria-hidden="true"></i>  <spring:message  code="expenses.btn.cancel"/></a>
                        </div> 
                    </div>
             </form:form>
         </c:when>
    </c:choose>
    
     <!----------------------- Inicio Formulario Eliminar Titulos  ======================================================== -->
		<c:choose>
            <c:when test="${operation eq 'del' }">
                <div id="" class="col-lg-12" >
                              <center><h5><i class="fa fa-trash-o" aria-hidden="true"></i>  <spring:message  code="gdoc.titulado.titulo.form.eliminar"/></h5></center>
                    <br>
                <div class="form-group row">
                 <div class="col-sm-8" id="combo1"> 
                   <form:form modelAttribute="gdocTitulados" action="${urlDel}" enctype="multipart/form-data"  method="post">
                       <form:hidden path="GdocConsejos.idGdocConsejo"  id="idConsejo" value="${bGdocConsejos.idGdocConsejo}" />
                      <form:hidden path="GdocArchivosAdjuntos.idGeodcArchivoAdjunto"  id="idAa" value="${gdocResoluciones.gdocArchivosAdjuntos.idGeodcArchivoAdjunto}" />
                       <form:hidden path="idGdocTitulado"  id="idTitulado" value="${gdocTitulados.idGdocTitulado}" />
                       <input type="text" name="sigla"  id="sigla" value="${bGdocConsejos.sigla}" />
                       
                <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-2 col-form-label"><spring:message  code="gdoc.titulado.form.nombre.titulado"/></label>
                        <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <form:select class="form-control form-control-lg" path="Personas.idPersona" id="idPersona">
                                <c:forEach var="lp" items="${requestScope.lPersonas}">
                                    <form:option value="${lp.idPersona}">${lp.getNombres()} ${lp.getPaterno()} ${lp.getMaterno()}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                  <spring:message  code="gdoc.titulado.form.msg.nombre.titulado"/>
                            </small>
                            <small id="ci" class="form-text text-muted formFieldError">
                                <form:errors path="Personas.idPersona" cssClass="alert alert-danger"/>
                            </small>
                        </div>
                    </div>
                 
      
                  
                 <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-2 col-form-label"><spring:message  code="gdoc.titulado.form.nro.titulo"/></label>
                        <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <form:select class="form-control form-control-lg" path="GdocTitulos.idGdocTitulo" id="idTitulos">
                                <c:forEach var="lti" items="${requestScope.lTitulos}">
                                    <form:option value="${lti.idGdocTitulo}">${lti.nroTitulo}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <a class="btn add" href="#" data-toggle="modal" data-target="#formCiExpedido"><i class="fa fa-plus-square-o" aria-hidden="true"></i>  <spring:message  code="people.add"/></a></small>
                                <small id="ci" class="form-text text-muted formFieldError">
                               <form:errors path="GdocTitulos.idGdocTitulo" cssClass="alert alert-danger"/>
                           </small>
                        </div>
                    </div>
                   
                    
                    <div class="form-group row">
                    <spring:message  code="gdoc.titulado.form.msg.valordep.titulado" var="nTitutlos"/>
                    <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="gdoc.titulado.form.valordep.titulado"/></label>
                    <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                    <div class="col-sm-6" id="combo1"> 
                        <form:input class="form-control form-control-lg" path="valorDeposito"  id="nroTls" onkeyUp="return ValNumero(this);" placeholder="${nTitutlos}" />
                     </div>
                    <div class="col-sm-3" id="combo1"> 
                        <small id="emailHelp" class="form-text text-muted">
                            <spring:message  code="gdoc.titulado.form.msg.valordep.titulado"/>
                         </small>
                          <small id="ci" class="form-text text-muted formFieldError">
                                <form:errors path="valorDeposito" cssClass="alert alert-danger"/>
                            </small>
                     </div>
                </div>
                 
                 
                 <div class="form-group row">
                    <spring:message  code="report.account.dateStart" var="fecex"/>
                    <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="report.account.dateStart"/></label>
                   <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                    <div class="col-sm-6" id="combo1"> 
                          <form:input type="date" path="fecExpedido" class="form-control form-control-lg"    placeholder="${fecex}"/>
                     </div>
                    <div class="col-sm-3" id="combo1"> 
                        <small id="emailHelp" class="form-text text-muted">
                            <spring:message  code="report.account.dateStart"/>
                         </small>
                          <small id="ci" class="form-text text-muted formFieldError">
                                <form:errors path="fecExpedido" cssClass="alert alert-danger"/>
                            </small>
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
                                  <form:input class="form-control form-control-lg" path="nombreArchivo" placeholder="${adj}"  id="fileName1"  value="${gdocTitulos.gdocArchivosAdjuntos.nombreArchivo}" />
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
                          <a href="<spring:url value="/titulos/inicioTitulos"/>" class="btn btn-yvela-green"><i class="fa fa-undo" aria-hidden="true"></i>  <spring:message  code="expenses.btn.cancel"/></a>
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