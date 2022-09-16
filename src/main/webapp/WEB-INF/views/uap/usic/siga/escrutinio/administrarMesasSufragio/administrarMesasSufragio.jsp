<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<head>
   <link rel="stylesheet" type="text/css"  href=" <c:url value="/resource/admIngresos/ingresos.css"/>" />
    <script src="<spring:url value="/resource/admIngresos/ingresos.js" />" type="text/javascript"></script>
      <script src="<spring:url value="/resource/js/escrutinio/escrutinio.js" />" type="text/javascript"></script>
 </head>
<div class="container">      <section class="content-header">
        <div class="row">
            <div class="col-md-10 mb-3">
                 <p class="h1"> <i class="fa fa-money" aria-hidden="true"></i>  <spring:message  code="sufragio.titulo.modulo.mesas"/> </p>
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
        <p class="h3"><i class="fa fa-list-alt" aria-hidden="true"></i> <spring:message  code="sufragio.titulo.tabla.resumen.mesas"/></p> 
    </center>
    <br>
     <table class="table">
                <thead>
                    <tr class="trIngreso">
                       <th><spring:message  code="sufragio.form.campo.facultad"/></th>
                      <th><spring:message  code="sufragio.form.campo.eleccion"/></th>
                      <th><spring:message  code="sufragio.form.campo.estamento"/></th>
                      <th><spring:message  code="ingreso.gestion"/></th>
                      <th><spring:message  code="sugragio.menu.enlace.mesa"/></th>
                      <th><spring:message  code="sufragio.form.campo.nro.inscrito"/></th>
                    </tr>
                  </thead>
                  <tbody>
                  <c:forEach var="lmh" items="${requestScope.lMesasHabilitadas}">
                    <tr id="thing">
                      <td>${lmh.facultades.facultad}</td>
                      <td>${lmh.escElecciones.eleccion}</td>
                      <td>${lmh.fclEstamentos.estamento}</td>
                      <td>${lmh.gestion}</td>
                      <td >${lmh.mesa}</td>
                      <td >${lmh.nroInscritos}</td>
                    </tr>
                  </c:forEach>
               </tbody>
               <tfoot>
                   <tr class="trIngreso">
                      <th><spring:message  code="sufragio.form.campo.facultad"/></th>
                      <th><spring:message  code="sufragio.form.campo.eleccion"/></th>
                      <th><spring:message  code="sufragio.form.campo.estamento"/></th>
                      <th><spring:message  code="ingreso.gestion"/></th>
                      <th><spring:message  code="sugragio.menu.enlace.mesa"/></th>
                      <th><spring:message  code="sufragio.form.campo.nro.inscrito"/></th>
                    </tr>
               </tfoot>
         </table> 
         <br>
           <hr align="left" noshade="noshade" size="10" width="100%" />
         </c:when>
      </c:choose>  
    </section>
    <!--  ========= Final de la Tabla ==================== --> 
     <!--  ========= Inicio de Formulario Escrutinio Actas ==================== -->
      <section>
      <c:choose>
            <c:when test="${operation eq 'reg' }"> 
                <center><br>
                    <p class="h3"><i class="fa fa-newspaper-o" aria-hidden="true"> </i> <spring:message  code="sufragio.titulo.form.seleccion"/></p> 
                    <br>
                </center>
               <spring:url var="formUrl" value="signupsss" />
                <form:form  modelAttribute="escMesasHabilitadas" action="${urlRegM}" method="post" >
                    <form:input type="hidden" path="FclCarreras.idCarrera" value="1" />
                     <form:input type="hidden" path="gestion" value="${gestion}" />
                      <form:input type="hidden" path="periodo" value="${periodo}" />
  <div class="form-group row">
                        <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="sufragio.form.campo.facultad"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6" id="combo1"> 
                            <form:select class="form-control form-control-lg" path="Facultades.idFacultad" id="idFacultad">
                                <c:forEach var="fcl" items="${requestScope.lFacultades}">
                                    <form:option value="${fcl.idFacultad}">${fcl.facultad} </form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                            <small id="emailHelp" class="form-text text-muted">
                             <spring:message  code="sufragio.form.facultad.obs"/>
                            </small>
                            <small class="msgError">
                               <form:errors path="Facultades.idFacultad" />
                            </small>
                        </div>
                    </div>
                     <div class="form-group row">
                        <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="sufragio.form.campo.eleccion"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6" id="combo1"> 
                            <form:select class="form-control form-control-lg" path="EscElecciones.idEleccion" id="idEleccion">
                                <c:forEach var="ele" items="${requestScope.lElecciones}">
                                    <form:option value="${ele.idEleccion}">${ele.eleccion} </form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                            <small id="emailHelp" class="form-text text-muted">
                                 <spring:message  code="sufragio.form.eleccion.obs"/>
                            </small>
                            <small class="msgError">
                               <form:errors path="EscElecciones.idEleccion" />
                            </small>
                        </div>
                    </div>
                     <div class="form-group row">
                        <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="sufragio.form.campo.estamento"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6" id="combo1"> 
                            <form:select class="form-control form-control-lg" path="FclEstamentos.idEstamento" id="idEstamento">
                                <c:forEach var="est" items="${requestScope.lEstamentos}">
                                    <form:option value="${est.idEstamento}">${est.estamento} </form:option>
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
                        <label for="codigo" class="col-sm-2 col-form-label"> <spring:message  code="sugragio.menu.enlace.mesa"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <spring:message  code="sufragio.form.mesa.obs" var="mobs"/>
                              <form:input  path="mesa"  class="form-control form-control-lg"  placeholder="${mobs}"/>
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                 <spring:message  code="sufragio.form.mesa.obs"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="mesa" />
                            </small>
                        </div>
                    </div>
                      <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"> <spring:message  code="sufragio.form.campo.nro.inscrito"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <spring:message  code="sufragio.form.nro.inscrito.obs" var="gloss"/>
                              <form:input  path="nroInscritos"  class="form-control form-control-lg"  placeholder="${mobs}"/>
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                 <spring:message  code="sufragio.form.nro.inscrito.obs"/> 
                            </small>
                            <small class="msgError">
                                <form:errors path="nroInscritos" />
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
                            <a href="<spring:url value="/mesas/inicioMesasSufragio"/>" class="btn btn-yvela-green"><i class="fa fa-undo yvela" aria-hidden="true"></i>  <spring:message  code="expenses.btn.cancel"/></a>
                        </div> 
                    </div> 
                </form:form>
               <hr align="left" noshade="noshade" size="10" width="100%" />
              
             </c:when>
        </c:choose>
      </section>
      <!--  ========= Final de Formulario Escrutinio Actas  ==================== --> 
    </div>
    
    