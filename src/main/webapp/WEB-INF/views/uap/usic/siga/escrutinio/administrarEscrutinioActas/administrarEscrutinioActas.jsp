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
                 <p class="h1"> <i class="fa fa-money" aria-hidden="true"></i> <spring:message  code="sufragio.titulo.modulos"/> </p>
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
        <p class="h3"><i class="fa fa-list-alt" aria-hidden="true"></i> <spring:message  code="sufragio.titulo.resumen.form"/></p> 
    </center>
    <br>
    
     <table class="table">
                <thead>
                    <tr class="trIngreso">
                      <th><spring:message  code="sufragio.form.campo.facultad"/></th>
                      <th><spring:message  code="sufragio.form.campo.estamento"/></th>
                      <th><spring:message  code="sufragio.form.campo.eleccion"/></th>
                      <th><spring:message  code="ingreso.gestion"/></th>
                      <th><spring:message  code="sugragio.menu.enlace.mesa"/></th>
                      <th><spring:message  code="expenses.modify"/> </th>
                      <th><spring:message  code="expenses.remove"/></th>
                    </tr>
                  </thead>
                  <tbody>
                   <c:forEach var="les" items="${requestScope.lEscrutinios}">                  
                     <tr id="thing">
                      <td>${les.escMesasHabilitadas.facultades.facultad}</td>
                      <td>${les.escMesasHabilitadas.fclEstamentos.estamento}</td>
                      <td>${les.escMesasHabilitadas.escElecciones.eleccion}</td>
                      <td>${les.gestion}</td>
                      <td>${les.escMesasHabilitadas.mesa}</td>
                          <td>
                                    <form  method="post" action="${urlInicioModItem}">
                                        <input type="hidden" name="idPnlPersonalAdministrativo" value="${les.idEscrutinioActa}">
                                        <span class="icon-input-btn"> <i class="fa fa-pencil-square-o yvela" aria-hidden="true"> </i> <input class="btn btn-success" type="submit"  value="<spring:message  code="expenses.modify"/>" > </span>
                                    </form>
                                </td>
                                <td>
                                    <form name="Delet" action="${urlInicioElimI}" method="post">
                                        <input type="hidden" name="idPnlPersonalAdministrativo" value="${les.idEscrutinioActa}">
                                        <span class="icon-input-btn"> <i class="fa fa-times yvela" aria-hidden="true"> </i> <input class="btn btn-danger" type="submit"  value="<spring:message  code="expenses.remove"/>"> </span>
                                    </form>
                                </td>
                   </tr>
                   </c:forEach>
               </tbody>
               <tfoot>
                  <tr class="trIngreso">
                   <th><spring:message  code="sufragio.form.campo.facultad"/></th>
                      <th><spring:message  code="sufragio.form.campo.estamento"/></th>
                      <th><spring:message  code="sufragio.form.campo.eleccion"/></th>
                      <th><spring:message  code="ingreso.gestion"/></th>
                      <th><spring:message  code="sugragio.menu.enlace.mesa"/></th>
                      <th><spring:message  code="expenses.modify"/> </th>
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
    <!--  ========= Inicio Formulario Listar Combos  ==================== --> 
    <section>
      <c:choose>
         <c:when test="${operation eq 'listfcl' }">
             <center> <p class="h3"><i class="fa fa-th" aria-hidden="true"></i><spring:message  code="sufragio.titulo.form.escrutinio"/> </p> </center>
            <br>
            <form name="form-escrutinio"  action="${urlforms}" method="post">
                <div class="form-group row">
                    <label for="ingreso" class="col-sm-2 col-form-label"><spring:message  code="sufragio.form.campo.facultad"/></label> 
                    <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                    <div class="col-sm-6">
                        <select class="form-control form-control-lg" name="idFacultad"  id="idFacultad">
                             <c:forEach var="fcl" items="${requestScope.lFacultades}">
                                     <option value="${fcl.idFacultad}">${fcl.facultad}</option>
                             </c:forEach>
                        </select>
                    </div>
                    <div class="col-sm-3" id="comd">
                        <small class="form-text text-muted">
                           <spring:message  code="sufragio.form.facultad.obs"/>
                         </small>
                         <small class="msgError" >
                            
                            </small>   
                    </div>
                </div>
                <div class="form-group row">
                    <label for="ingreso" class="col-sm-2 col-form-label"><spring:message  code="sufragio.form.campo.eleccion"/></label> 
                    <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                    <div class="col-sm-6">
                        <select class="form-control form-control-lg" name="idEleccion"  id="idEleccion">
                            <c:forEach var="p" items="${requestScope.lElecciones}">
                                     <option value="${p.idEleccion}">${p.eleccion}</option>
                             </c:forEach>
                        </select>
                    </div>
                    <div class="col-sm-3" id="comd">
                        <small class="form-text text-muted">
                           <spring:message  code="sufragio.form.eleccion.obs"/>
                         </small>
                         <small class="msgError" >
                            
                            </small>   
                    </div>
                </div>
                <div class="form-group row">
                    <label for="ingreso" class="col-sm-2 col-form-label"><spring:message  code="sufragio.form.campo.estamento"/></label> 
                    <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                    <div class="col-sm-6">
                        <select class="form-estam form-control form-control-lg" name="idEstamento"  id="idEstamento">
                              <c:forEach var="fet" items="${requestScope.lEstamentos}">
                                     <option value="${fet.idEstamento}">${fet.estamento}</option>
                             </c:forEach>
                        </select>
                    </div>
                    <div class="col-sm-3" id="comd">
                        <small class="form-text text-muted">
                          <spring:message  code="sufragio.from.estamento.obs"/>
                         </small>
                         <small class="msgError" >
                            
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
                            <a href="<spring:url value="/escrutinio/inicioEscrutinioActas"/>" class="btn btn-yvela-green"><i class="fa fa-undo yvela" aria-hidden="true"></i>  <spring:message  code="expenses.btn.cancel"/></a>
                        </div> 
                    </div>
             </form>
         </c:when>
       </c:choose>  
    </section>
      <!--  ========= Final de Formulario Listar Combos ==================== -->
      <!--  ========= Inicio de Formulario Escrutinio Actas ==================== -->
      <section>
      <c:choose>
            <c:when test="${operation eq 'reg' }">
                <center><br>
                    <p class="h3"><i class="fa fa-newspaper-o" aria-hidden="true"> </i>  <spring:message  code="sufragio.titulo.resumen.form"/> </p> 
                    <br>
                </center>
                <spring:url var="formUrl" value="signupsss" />
                <form:form  modelAttribute="escrutinioActas" action="${regEstr}" method="post" >
              		<form:input type="hidden" path="gestion" value="${gestion}" />
 					<form:input type="hidden" path="periodo" value="${periodo}" />
 					  <input type="hidden" name="idFacultad" value="${idFacultad}" />
 					  <input type="hidden" name="idEleccion" value="${idEleccion}" />    
 					  <input type="hidden" name="idEstamento" value="${idEstamento}" />          
                    <div class="form-group row">
                        <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="sugragio.menu.enlace.mesa"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6" id="combo1"> 
                            <form:select class="form-control form-control-lg" path="EscMesasHabilitadas.idMesaHabilitada" id="idMesa">
                                <c:forEach var="lmh" items="${requestScope.lMesasHabilitadas}">
                                    <form:option value="${lmh.idMesaHabilitada}">${lmh.mesa} </form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                            <small id="emailHelp" class="form-text text-muted">
                             <spring:message  code="sufragio.form.campo.mesas.obs"/>
                            </small>
                            <small class="msgError">
                                 <form:errors path="EscMesasHabilitadas.idMesaHabilitada" />
                            </small>

                        </div>
                    </div>
                     <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"> <spring:message  code="sufragio.form.camp.nro.acta"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <spring:message  code="sufragio.form.nro.acta.obs" var="nacta"/>
                              <form:input  path="nroActa"  class="form-control form-control-lg"  placeholder="´${nacta}"/>
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                             <spring:message  code="sufragio.form.nro.acta.obs"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="nroActa" />
                            </small>
                        </div>
                    </div>
                      <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label">  <spring:message  code="sufragio.form.campo.vvalidos"/> </label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <spring:message  code="sufragio.form.vvalidos.obs" var="vval"/>
                              <form:input  path="votosValidos"  class="form-control form-control-lg"  placeholder="${vval}"/>
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                               <spring:message  code="sufragio.form.vvalidos.obs"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="votosValidos" />
                            </small>
                        </div>
                    </div>
                      <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label">  <spring:message  code="sufragio.form.campo.vnulos"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <spring:message  code="sufragio.form.vnulos.obs" var="vnull"/>
                              <form:input  path="votosNulos"  class="form-control form-control-lg"  placeholder="${vnull}"/>
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <spring:message  code="sufragio.form.vnulos.obs"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="votosNulos" />
                            </small>
                        </div>
                    </div>
                      <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label">  <spring:message  code="sufragio.form.campo.vblancos"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <spring:message  code="sufragio.form.vblancos.obs" var="vblan"/>
                              <form:input  path="votosBlancos"  class="form-control form-control-lg"  placeholder="${vblan}"/>
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                              <spring:message  code="sufragio.form.vblancos.obs"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="votosBlancos" />
                            </small>
                        </div>
                    </div>
                      <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label">  <spring:message  code="sufragio.form.campo.vemitidos"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <spring:message  code="sufragio.from.vemitodos.obs" var="vemit"/>
                              <form:input  path="votosEmitidos"  class="form-control form-control-lg"  placeholder="${vemit}"/>
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                               <spring:message  code="sufragio.from.vemitodos.obs"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="votosEmitidos" />
                            </small>
                        </div>
                    </div>
                     <hr align="left" noshade="noshade" size="10" width="100%" />
                     <center><br>
                		 	   <p class="h3"><i class="fa fa-newspaper-o" aria-hidden="true"> </i> <spring:message  code="sufragio.form.vfrentes.obs"/></p> 
                 			   <br>
               		 </center>
                     <div class="form-group row">
				             <table class="table">
				                <thead>
				                    <tr class="trIngreso">
				                      <th width="15%">Nro</th>
				                       <th width="5%">?</th>
				                      <th width="30%"> <spring:message  code="sufragio.form.campo.frente"/></th>
				                      <th width="20%"> <spring:message  code="sufragio.form.campo.sigla"/></th>
				                       <th width="30%"> <spring:message  code="sufragio.form.vfrentes.obs"/></th>
				                    </tr>
				                  </thead>
				                  <tbody>
				                      <c:forEach var="lfrn" items="${requestScope.lFrentes}">
							                    <tr id="thing">
							                      <td width="15%">${lfrn.idFrente}</td>
							                      <td width="5%"><input type="checkbox"  name="idFrente"  value="${lfrn.idFrente}" /></td>
							                      <td width="30%">${lfrn.frente }</td>
							                       <td width="20%">${lfrn.sigla}</td>
							                      <td width="30%"><input type="text" name="votosFrente${lfrn.idFrente }"  class="form-control form-control-lg"  placeholder=" <spring:message  code="sufragio.form.vfrentes.obs"/>"/></td>
							                   </tr>
						                </c:forEach>
				               </tbody>
				               <tfoot>
				                   <tr class="trIngreso">
				                    <th width="15%">Nro</th>
				                       <th width="5%">?</th>
				                      <th width="30%"> <spring:message  code="sufragio.form.campo.frente"/></th>
				                      <th width="20%"> <spring:message  code="sufragio.form.campo.sigla"/></th>
				                       <th width="30%"> <spring:message  code="sufragio.form.vfrentes.obs"/></th>
				                    </tr>
				               </tfoot>
				         </table>                      
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
     
      </section>
      <!--  ========= Final de Formulario Escrutinio Actas  ==================== --> 
    </div>
    
    