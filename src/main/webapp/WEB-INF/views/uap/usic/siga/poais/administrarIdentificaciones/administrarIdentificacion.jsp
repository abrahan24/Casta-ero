<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<head>
    <link rel="stylesheet" type="text/css"  href=" <c:url value="/resource/admPersonas/personas.css"/>" />
    <script src="<spring:url value="/resource/poais/identification/admIdentification.js" />" type="text/javascript"></script>
      
    <style type="text/css">
        #portapdf {
            width: 500px;
            height: 400px;
            border: 1px solid #484848;
            margin: 0 auto;
        }
    </style>
    <script>

    </script>
</head>

<div class="container">    
    <section class="content-header">
        <div class="row">
            <div class="col-md-10 mb-3">
                <p class="h1"><i class="fa fa-file-archive-o" aria-hidden="true"> </i> <spring:message code="poais.titulo.modulo"/> </p>
            </div>
            <div class="col-sm-2 text-right" id="reporte">
                <c:choose>
                    <c:when test="${busqueda eq 'find' }">
                        <form  method="POST" action="${urlN}">
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
        
        <!-- ------------------------ Fin Tabla Listar Poais ------------------------- -->
            <c:choose>
                <c:when test="${busqueda eq 'find' }">
                    <center><br>
                        <p class="h3"><i class="fa fa-folder-open-o" aria-hidden="true"></i> <spring:message  code="poais.titulo.form.lista"/></p> 
                        <br>
                    </center>
                    <table id="tComprobantes"  class="table" style="width:100%">
                        <thead>
                            <tr>
                                <th><spring:message  code="poai.form.campo.descripcion"/></th>
                                <th><spring:message  code="items.year"/></th>
                                <th><spring:message  code="units.unit"/></th>
                                <!-- 
                                <th><spring:message  code="expenses.modify"/> </th>
                                <th><spring:message  code="expenses.remove"/></th> -->
                            </tr>
                        </thead>
                        <tbody>
                            <c:set var="cont" value="0" />
                            <c:forEach items="${lPoais}" var="lPoa">
                                <c:set var="cont" value="${cont+1}" />
                                <tr>
                                    <td>${lPoa.descripcion}</td>
                                    <td>${lPoa.gestion}</td>
                                    <td>${lPoa.pnlPersonalAdministrativos.insUnidadesFuncionales.unidadFuncional}</td>
                                    <!-- 
                                    <td>
                                        <form  method="post" action="${urlM}">
                                            <input type="hidden" name="idSacComprobante" value="${lPoa.idPoai}">
                                            <span class="icon-input-btn"> <i class="fa fa-pencil-square-o yvela" aria-hidden="true"> </i> <input class="btn btn-success" type="submit"  value="<spring:message  code="expenses.modify"/>" > </span>
                                        </form>
                                    </td>
                                    <td>
                                        <form name="Delet" action="${urlD}" method="post">
                                            <input type="hidden" name="idSacComprobante" value="${lPoa.idPoai}">
                                            <span class="icon-input-btn"> <i class="fa fa-times yvela" aria-hidden="true"> </i> <input class="btn btn-danger" type="submit"  value="<spring:message  code="expenses.remove"/>"> </span>
                                        </form>
                                    </td>-->
                                    
                                </tr>
                          </c:forEach>
                        </tbody>
                        <!--<tfoot>
                            <tr>
                                <th><spring:message  code="poai.form.campo.descripcion"/></th>
                                <th><spring:message  code="items.year"/></th>
                                <th><spring:message  code="units.unit"/></th>
                                 
                                <th><spring:message  code="expenses.modify"/> </th>
                                <th><spring:message  code="expenses.remove"/></th>
                                
                            </tr>
                        </tfoot>-->
                    </table>
                    <br>
                    <div class="modal-footer">
	                    <a href="../../../../Castanhero/poaiObjetivo/AdministrarObjetivoResultado" type="submit" class="btn btn-info btn-lg float-right" aria-hidden="true">
	                    Siguiente
	                    </a>
	                </div>
                    <br>
                    <hr align="left" noshade="noshade" size="10" width="100%" />

                </c:when>
            </c:choose>
    </section>
    <!--  ---------------------- Fin Tabla Listar Poais -----------------  -->

        <!-- --------------------- Inicio registro nueva Poais Identificacion -------------------- -->
       <section>
        <c:choose>
            <c:when test="${operation eq 'reg' }">
                <center><br>
                    <p class="h3"><i class="fa fa-folder-open" aria-hidden="true"> </i> <spring:message  code="poais.titulo.form"/></p> 
                    <br>
                </center>
                <form:form modelAttribute="poaisIdentificaciones" action="${regPoais}" enctype="multipart/form-data" method="post">
                         <form;hidden path="Poais.idPoai" value="1" />              
              
                 <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="poais.form.campo.supervisor"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  </label>
                        <div class="col-sm-6">
                            <form:select class="form-control form-control-lg" path="PoaisSupervisores.idSupervisor" id="lSupervisor">
                                <option value="0" hidden="" selected="selected">SELECCIONE SUPERVISOR</option>
                            	<c:forEach var="lsup" items="${requestScope.lSupervisores}">
                                    <form:option value="${lsup.idSupervisor}">${lsup.personas.nombres}  ${lsup.personas.paterno}  ${lsup.personas.materno} </form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <!-- 
                        <small class="form-text text-muted">
                               <a class="btn add" href="#" data-toggle="modal" data-target="#formSupervisor"><i class="fa fa-plus-square-o" aria-hidden="true"></i>  <spring:message  code="people.add"/></a>&nbsp&nbsp&nbsp&nbsp
                         </small>
                          -->
                        <small class="msgError">
                                <form:errors path="PoaisSupervisores.idSupervisor" />
                        </small>

                    </div>
                    
                    
                    <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="poais.from.campo.cargo"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  </label>
                        <div class="col-sm-6">
                            <form:select class="form-control form-control-lg" path="PnlCargos.idPnlCargos" id="idPnlCargos">
                                <option value="0" hidden="" selected="selected">SELECCIONE SU CARGO</option>
                            	<c:forEach var="lcar" items="${requestScope.lCargos}">
                                    <form:option value="${lcar.idPnlCargos}">${lcar.cargo}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <small class="form-text text-muted">
                            <spring:message  code="poais.form.obs.cargo"/>
                        </small>
 						<small class="msgError">
                               <form:errors path="PnlCargos.idPnlCargos" />
                            </small>
                    </div>
                    <!-- 
                      <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="poai.form.campo.descripcion"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6" id="combo1"> 
                            <spring:message  code="poais.from.obs.descripcion" var="description"/>
                            <input type="textarea"  name="descripcion" class="form-control form-control-lg" placeholder="${description}" rows="3" cols="20" />
                        </div>
                        <div class="col-sm-3" id="comd"> 
                            <small id="emailHp" class="form-text text-muted">
                                <spring:message  code="poais.from.obs.descripcion"/>
                            </small>
                            <small class="msgError">
                                
                            </small>
                        </div>
                    </div> -->
                      <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="poais.form.campo.relacion.inter"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6" id="combo1"> 
                            <spring:message  code="poais.form.obs.relacion.inter" var="inter"/>
                              <form:textarea  path="relacionInterinstitucional" class="form-control form-control-lg" placeholder="Registrar las instituciones u organismos (especificando cargos si es posible) con los que se relaciona o coordina el puesto" rows="5" cols="20" />
                          </div>
                        <div class="col-sm-3" id="comd"> 
                            <small id="emailHp" class="form-text text-muted">
                                <spring:message  code="poais.form.obs.relacion.inter"/>
                            </small>
                            <small class="msgError">
                               <form:errors path="relacionInterinstitucional" />
                            </small>
                        </div>
                    </div>
                      <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="poais.form.campo.relacion.intra"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6" id="combo1"> 
                            <spring:message  code="poais.form.obs.relacion.intra" var="intra"/>
                            <form:textarea path="relacionIntrainstitucional" class="form-control form-control-lg" placeholder="Registrar los puestos internos y/o unidades organizacionales con los que se relaciona el puesto. Especificar los puestos cuando la relacion es clara e inequivoca" rows="5" cols="20" />
                          </div>
                        <div class="col-sm-3" id="comd"> 
                            <small id="emailHp" class="form-text text-muted">
                                <spring:message  code="poais.form.obs.relacion.intra"/>
                            </small>
                            <small class="msgError">
                                 <form:errors path="relacionIntrainstitucional" />
                            </small>
                        </div>
                    </div>
                    
                    <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="items.year"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <spring:message  code="items.year" var="year"/>
                            <input  name="gestion"  class="form-control form-control-lg"  placeholder="${year}" id="nroComprobante"/> 
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <spring:message  code="items.year"/>
                            </small>
                            <small class="msgError">
                                    
                            </small>
                        </div>
                    </div>
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
                            <a href="<spring:url value="/comprobante/inicioComprobantes"/>" class="btn btn-yvela-green"><i class="fa fa-undo yvela" aria-hidden="true"></i>  <spring:message  code="expenses.btn.cancel"/></a>
                        </div> 
                    </div>
                </form:form>
                <br><br>
                <hr align="left" noshade="noshade" size="10" width="100%" />
            </c:when>
        </c:choose>
        <!-- -------------------- Final Nuevo Identificaciones ---------------- -->
</section>
</div>

<!--  ============== modal registrar supervisor ========================== -->
<c:if test="${modal != null}">
<div id="formSupervisor" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form:form name="supervisoreForm" modelAttribute="poaisSupervisores" action="guardarSupervisor" role="form" id="itemForm" method="post">
                <div class="modal-header">
                    <p class="h3">  <spring:message  code="poais.titulo.form.modal"/></p>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">�</button>
                </div>
                <div class="modal-body">
                    <div class="form-group row">
                        <label for="sexo" class="col-sm-3 col-form-label"><spring:message  code="poais.form.modal.personal"/></label>
                        <label class="col-sm-1 col-form-label text-right"><i class="fa fa-info-circle" aria-hidden="true"></i> </label>
                        <div class="col-sm-8">
                            <form:select class="form-control form-control-lg" path="Personas.idPersona" id="idPersona">
                                <c:forEach var="lPrs" items="${requestScope.lPersonal}">
                                    <form:option value="${lPrs.idPersona}">${lPrs.nombres} ${lPrs.paterno} ${lPrs.materno}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                    </div>
                       <div class="form-group row">
                        <label for="sexo" class="col-sm-3 col-form-label"><spring:message  code="poais.from.campo.cargo"/></label>
                        <label class="col-sm-1 col-form-label text-right"><i class="fa fa-info-circle" aria-hidden="true"></i> </label>
                        <div class="col-sm-8">
                            <form:select class="form-control form-control-lg" path="PnlCargos.idPnlCargos" id="idPnlCargos">
                                <c:forEach var="lPnc" items="${requestScope.lCargos}">
                                    <form:option value="${lPnc.idPnlCargos}">${lPnc.cargo}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                    </div>
                   <div class="form-group row">
                        <spring:message  code="poais.from.obs.descripcion" var="desc"/>
                        <label for="expedido" class="col-sm-3 col-form-label"><spring:message  code="poai.form.campo.descripcion"/></label>
                        <label for="ciExpedido" class="col-sm-1 col-form-label"><i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-8">
                            <form:input path="descripcion" class="form-control form-control-lg"  placeholder="${desc}" />
                        </div>
                    </div>
                    <div class="form-group row">
                        <spring:message  code="items.year" var="year1"/>
                        <label for="expedido" class="col-sm-3 col-form-label"><spring:message  code="items.year"/></label>
                        <label for="ciExpedido" class="col-sm-1 col-form-label"></label>
                        <div class="col-sm-8">
                            <form:input path="gestion" class="form-control form-control-lg"  placeholder="${year1}" />
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-outline-secondary btn-lg" data-dismiss="modal" aria-hidden="true"><i class="fa fa-undo" aria-hidden="true"></i>  <spring:message  code="people.cancel"/></button>
                    <button type="submit" class="btn btn-success btn-lg float-right" id="btnSupervisor" data-dismiss="modal" aria-hidden="true"><i class="fa fa-plus-square-o" aria-hidden="true"></i>   <spring:message  code="people.add"/></button>
                    
                </div>
            </form:form>
        </div>
    </div>
</div>
</c:if>
<!-- final Modal registro de personas  -->
