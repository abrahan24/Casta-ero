<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<head>
   <link rel="stylesheet" type="text/css"  href=" <c:url value="/resource/admIngresos/ingresos.css"/>" />
    <script src="<spring:url value="/resource/admIngresos/ingresos.js" />" type="text/javascript"></script>
      <script src="<spring:url value="/resource/js/congres/congreso.js" />" type="text/javascript"></script>
 </head>
<div class="container">      <section class="content-header">
        <div class="row">
            <div class="col-md-10 mb-3">
                 <p class="h1"> <i class="fa fa-money" aria-hidden="true"></i> <spring:message  code="congreso.titulo.congresistas"/> </p>
            </div>
            <div class="col-sm-2 text-right" id="reporte">
                <c:choose>
                    <c:when test="${busqueda eq 'find' }">
                         <form  method="POST" action="${urlform}">
                            <span class="icon-input-btn"><i class="fa fa-user-plus yvela" aria-hidden="true"></i> <input type="submit" class="btn btn-success btn-lg" value="<spring:message  code="income.btn.new"/>"></span>
                        </form>
                    </c:when>
                </c:choose> 
            </div>
        </div>
        <hr align="left" noshade="noshade" size="10" width="100%" />
    </section>
    <!--   ======= Inicio Tabla ===================   -->
    <section>
     <c:choose>
         <c:when test="${operation eq 'table' }">
    	 <center>
        <p class="h3"><i class="fa fa-list-alt" aria-hidden="true"></i> <spring:message  code="congreso.titulo.table.congresistas"/> </p> 
    </center>
    <br>
     <table class="table">
                <thead>
                    <tr class="trIngreso">
                      <th><spring:message  code="sufragio.form.campo.facultad"/></th>
                      <th><spring:message  code="congreso.form.campo.congresista"/></th>
                      <th><spring:message  code="congreso.form.campo.tipo.congresista"/></th>
                      <th><spring:message  code="ingreso.gestion"/></th>
                      <th><spring:message  code="expenses.modify"/></th>
                      <th><spring:message  code="expenses.remove"/></th>
                    </tr>
                  </thead>
                  <tbody>
                  <c:forEach var="lcg" items="${requestScope.lCongresistas}">
                    <tr id="thing">
                      <td>${lcg.personas.nombres} ${lcg.personas.paterno} ${lcg.personas.materno}</td>
                      <td>${lcg.fclCarreras.facultades.facultad}</td>
                      <td>${lcg.cngTiposCongresistas.tipoCongresista}</td>
                       <td>${lcg.gestion}</td>
                      <td>
                            <form action="${urlMod}" method="post" >
                                   <input type="hidden" name="idCngCongresista" value="${lcg.idCngCongresista}">
                                   <button type="submit" class="btn btn-success"><i class="fa fa-pencil-square-o" aria-hidden="true"></i> <spring:message  code="expenses.modify"/></button>
                            </form>
                      </td>
                      <td>
                               <form name="eliminarGasto" action="${urlIE}" method="post" >
                                      <input type="hidden" name="idCngCongresista" value="${lcg.idCngCongresista}">
                                      <button type="submit" class="btn btn-danger"><i class="fa fa-times" aria-hidden="true"></i>   <spring:message  code="expenses.remove"/></button>
                                </form>
                         </td>
                    </tr>
                  </c:forEach>
               </tbody>
               <tfoot>
                   <tr class="trIngreso">
                         <tr class="trIngreso">
                      <th><spring:message  code="sufragio.form.campo.facultad"/></th>
                      <th><spring:message  code="congreso.form.campo.congresista"/></th>
                      <th><spring:message  code="congreso.form.campo.tipo.congresista"/></th>
                      <th><spring:message  code="ingreso.gestion"/></th>
                      <th><spring:message  code="expenses.modify"/></th>
                      <th><spring:message  code="expenses.remove"/></th>
                    </tr>
               </tfoot>
         </table> 
         <br>
           <hr align="left" noshade="noshade" size="10" width="100%" />
         </c:when>
      </c:choose>  
    </section>
    <!--  ========= Final de la Tabla ==================== --> 
      <!--  ========= Inicio de Formulario Administrar Frentes  ==================== -->
      <section>
      <c:choose>
            <c:when test="${operation eq 'reg' }">
                <center><br>
                    <p class="h3"><i class="fa fa-newspaper-o" aria-hidden="true"> </i> <spring:message  code="congreso.titulo.form.congresistas"/> </p> 
                    <br>
                     </center>
                <spring:url var="formUrl" value="signupsss" />
                <form:form  modelAttribute="cngCongresistas" action="${regCng}" method="post" >
                   <div class="form-group row">
                        <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="congreso.form.campo.congresista"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6" id="combo1"> 
                            <form:select class="form-control form-control-lg" path="Personas.idPersona" id="idPersona">
                                <c:forEach var="lpr" items="${requestScope.lPersonas}">
                                    <form:option value="${lpr.idPersona}">${lpr.nombres}  ${lpr.paterno} ${lpr.materno}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                            <small id="emailHelp" class="form-text text-muted">
                             <spring:message  code="congreso.form.campo.congresista.obs"/>
                            </small>
                            <small class="msgError">
                               <form:errors path="Personas.idPersona" />
                            </small>
                        </div>
                    </div>
                     <div class="form-group row">
                        <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="sufragio.form.campo.facultad"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6" id="combo1"> 
                            <select class="form-control form-control-lg" name="idFacultad" id="idFacultad">
                                <c:forEach var="fcl" items="${requestScope.lFacultades}">
                                    <option value="${fcl.idFacultad}">${fcl.facultad} </option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                            <small id="emailHelp" class="form-text text-muted">
                                 <spring:message  code="sufragio.form.facultad.obs"/>
                            </small>
                            <small class="msgError">
                            
                            </small>
                        </div>
                    </div>
                     <div class="form-group row">
                        <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="congreso.form.campo.carrera"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6" id="combo1"> 
                            <form:select class="form-control form-control-lg" path="FclCarreras.idCarrera" id="idCarrera">
                                <c:forEach var="lcr" items="${requestScope.lCarreras}">
                                    <form:option value="${lcr.idCarrera}">${lcr.carrera} </form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                            <small id="emailHelp" class="form-text text-muted">
                                <spring:message  code="congreso.form.campo.carrera.msg"/>
                            </small>
                            <small class="msgError">
                               <form:errors path="FclCarreras.idCarrera" />
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="congreso.form.campo.tipo.congresista"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6" id="combo1"> 
                            <form:select class="form-control form-control-lg" path="CngTiposCongresistas.idTipoCongresista" id="idTipoCongresista">
                                <c:forEach var="ltc" items="${requestScope.lTiposCongresistas}">
                                    <form:option value="${ltc.idTipoCongresista}">${ltc.tipoCongresista} </form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                            <small id="emailHelp" class="form-text text-muted">
                                <spring:message  code="congreso.form.campo.tipo.congresista.obs"/>
                            </small>
                            <small class="msgError">
                               <form:errors path="CngTiposCongresistas.idTipoCongresista" />
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="congreso.form.campo.congresuap"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6" id="combo1"> 
                            <form:select class="form-control form-control-lg" path="CongresoUap.idCongresoUap" id="idCongresoUap">
                                <c:forEach var="lcu" items="${requestScope.lCongresoUap}">
                                    <form:option value="${lcu.idCongresoUap}">${lcu.congreso} </form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                            <small id="emailHelp" class="form-text text-muted">
                                <spring:message  code="congreso.form.campo.congresuap.msg"/>
                            </small>
                            <small class="msgError">
                               <form:errors path="CongresoUap.idCongresoUap" />
                            </small>
                        </div>
                    </div>
                     <div class="form-group row">
                        <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="sufragio.form.campo.frente"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6" id="combo1"> 
                            <form:select class="form-control form-control-lg" path="EscFrentes.idFrente" id="idFrente">
                                <c:forEach var="lfr" items="${requestScope.lFrentes}">
                                    <form:option value="${lfr.idFrente}">${lfr.frente} </form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                            <small id="emailHelp" class="form-text text-muted">
                                <spring:message  code="sufragio.form.campo.frente.obs"/>
                            </small>
                            <small class="msgError">
                               <form:errors path="EscFrentes.idFrente" />
                            </small>
                        </div>
                    </div>
                      <div class="form-group row">
                        <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="sufragio.form.campo.estamento"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6" id="combo1"> 
                            <form:select class="form-control form-control-lg" path="FclEstamentos.idEstamento" id="idEstamento">
                                <c:forEach var="les" items="${requestScope.lEstamentos}">
                                    <form:option value="${les.idEstamento}">${les.estamento} </form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                            <small id="emailHelp" class="form-text text-muted">
                                <spring:message  code="sufragio.from.estamento.obs"/>
                            </small>
                            <small class="msgError">
                               <form:errors path="FclEstamentos.idEstamento" />
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
                            <a href="<spring:url value="/frentes/inicioFrentesEleccion"/>" class="btn btn-yvela-green"><i class="fa fa-undo yvela" aria-hidden="true"></i>  <spring:message  code="expenses.btn.cancel"/></a>
                        </div> 
                    </div> 
                </form:form>
               <hr align="left" noshade="noshade" size="10" width="100%" />
              
             </c:when>
        </c:choose>
      </section>
      <!--  ========= Final de Formulario Escrutinio Actas  ==================== --> 
     <!--  ========= Inicio Formulario Modificar Congresistas  ==================== -->
      <section>
      <c:choose>
            <c:when test="${operation eq 'mod' }">
                <center><br>
                    <p class="h3"><i class="fa fa-newspaper-o" aria-hidden="true"> </i> <spring:message  code="congreso.titulo.form.congresistas"/> </p> 
                    <br>
                     </center>
                <spring:url var="formUrl" value="signupsss" />
                <form:form  modelAttribute="cngCongresistas" action="${regCng}" method="post" >
                     <form:input type="hidden" path="idCngCongresista" value="${cngCongresistas.idCngCongresista}" />
                    <div class="form-group row">
                        <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="congreso.form.campo.congresista"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6" id="combo1"> 
                            <form:select class="form-control form-control-lg" path="Personas.idPersona" id="idPersona">
                                <c:forEach var="lpr" items="${requestScope.lPersonas}">
                                    <form:option value="${lpr.idPersona}">${lpr.nombres}  ${lpr.paterno} ${lpr.materno}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                            <small id="emailHelp" class="form-text text-muted">
                             <spring:message  code="congreso.form.campo.congresista.obs"/>
                            </small>
                            <small class="msgError">
                               <form:errors path="Personas.idPersona" />
                            </small>
                        </div>
                    </div>
                     <div class="form-group row">
                        <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="sufragio.form.campo.facultad"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6" id="combo1"> 
                            <select class="form-control form-control-lg" name="idFacultad" id="idFacultad">
                                <c:forEach var="fcl" items="${requestScope.lFacultades}">
                                    <option value="${fcl.idFacultad}">${fcl.facultad} </option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                            <small id="emailHelp" class="form-text text-muted">
                                 <spring:message  code="sufragio.form.facultad.obs"/>
                            </small>
                            <small class="msgError">
                            
                            </small>
                        </div>
                    </div>
                     <div class="form-group row">
                        <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="congreso.form.campo.carrera"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6" id="combo1"> 
                            <form:select class="form-control form-control-lg" path="FclCarreras.idCarrera" id="idCarrera">
                                <c:forEach var="lcr" items="${requestScope.lCarreras}">
                                    <form:option value="${lcr.idCarrera}">${lcr.carrera} </form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                            <small id="emailHelp" class="form-text text-muted">
                                <spring:message  code="congreso.form.campo.carrera.msg"/>
                            </small>
                            <small class="msgError">
                               <form:errors path="FclCarreras.idCarrera" />
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="congreso.form.campo.tipo.congresista"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6" id="combo1"> 
                            <form:select class="form-control form-control-lg" path="CngTiposCongresistas.idTipoCongresista" id="idTipoCongresista">
                                <c:forEach var="ltc" items="${requestScope.lTiposCongresistas}">
                                    <form:option value="${ltc.idTipoCongresista}">${ltc.tipoCongresista} </form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                            <small id="emailHelp" class="form-text text-muted">
                                <spring:message  code="congreso.form.campo.tipo.congresista.obs"/>
                            </small>
                            <small class="msgError">
                               <form:errors path="CngTiposCongresistas.idTipoCongresista" />
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="congreso.form.campo.congresuap"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6" id="combo1"> 
                            <form:select class="form-control form-control-lg" path="CongresoUap.idCongresoUap" id="idCongresoUap">
                                <c:forEach var="lcu" items="${requestScope.lCongresoUap}">
                                    <form:option value="${lcu.idCongresoUap}">${lcu.congreso} </form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                            <small id="emailHelp" class="form-text text-muted">
                                <spring:message  code="congreso.form.campo.congresuap.msg"/>
                            </small>
                            <small class="msgError">
                               <form:errors path="CongresoUap.idCongresoUap" />
                            </small>
                        </div>
                    </div>
                     <div class="form-group row">
                        <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="sufragio.form.campo.frente"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6" id="combo1"> 
                            <form:select class="form-control form-control-lg" path="EscFrentes.idFrente" id="idFrente">
                                <c:forEach var="lfr" items="${requestScope.lFrentes}">
                                    <form:option value="${lfr.idFrente}">${lfr.frente} </form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                            <small id="emailHelp" class="form-text text-muted">
                                <spring:message  code="sufragio.form.campo.frente.obs"/>
                            </small>
                            <small class="msgError">
                               <form:errors path="EscFrentes.idFrente" />
                            </small>
                        </div>
                    </div>
                      <div class="form-group row">
                        <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="sufragio.form.campo.estamento"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6" id="combo1"> 
                            <form:select class="form-control form-control-lg" path="FclEstamentos.idEstamento" id="idEstamento">
                                <c:forEach var="les" items="${requestScope.lEstamentos}">
                                    <form:option value="${les.idEstamento}">${les.estamento} </form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                            <small id="emailHelp" class="form-text text-muted">
                                <spring:message  code="sufragio.from.estamento.obs"/>
                            </small>
                            <small class="msgError">
                               <form:errors path="FclEstamentos.idEstamento" />
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
                            <a href="<spring:url value="/frentes/inicioFrentesEleccion"/>" class="btn btn-yvela-green"><i class="fa fa-undo yvela" aria-hidden="true"></i>  <spring:message  code="expenses.btn.cancel"/></a>
                        </div> 
                    </div> 
                </form:form>
               <hr align="left" noshade="noshade" size="10" width="100%" />
              
             </c:when>
        </c:choose>
      </section>
      <!--  ========= Final Modificar Congresistas   ==================== --> 
  
    </div>
    
    