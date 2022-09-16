<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<head>
    <link rel="stylesheet" type="text/css"  href=" <c:url value="/resource/admPersonas/personas.css"/>" />
    <script src="<spring:url value="/resource/sac/admComprob/admComprob.js" />" type="text/javascript"></script>
    <script src="<spring:url value="/resource/poais/objetivo/admObjetivo.js" />" type="text/javascript"></script>
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
                <p class="h1"><i class="fa fa-file-archive-o" aria-hidden="true"> </i> Administrar Objetivo de POAI </p>
            </div>
            <div class="col-sm-2 text-right" id="reporte">
                <c:choose>
                    <c:when test="${busqueda eq 'find' }">
                        <form  method="POST" action="${urlN}">
                        	<input type="hidden" name="idObjetivo" value="${poaisObjetivos.idObjetivo}">
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
             Hola   <spring:message  code="AbstractUserDetailsAuthenticationProvider.badCredentials"/>
                <br/>
            </div>
        </c:if>
        
            <!--Tabla lista de Todos los Comprobantes Egreso Registrados -->
            <c:choose>
                <c:when test="${busqueda eq 'find' }">
                    <center><br>
                        <p class="h3"><i class="fa fa-folder-open-o" aria-hidden="true"></i> Lista Resultados de POAI</p> 
                        <br>
                    </center>
                    <c:set var="aux" value="0" />
                    <table id="tComprobantes"  class="table" style="width:100%">
                        <thead>
                            <tr>
                                <th>Nro</th>
                                <th>Resultado</th>
                                <th>Indicador</th>
                                <th>Ponderacion</th>
                                <th>Gestion</th>
                                <!--  
                                <th><spring:message  code="expenses.modify"/> </th>-->
                                <th><spring:message  code="expenses.remove"/></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:set var="cont" value="0" />
                            <c:forEach items="${poaisObjetivos.poaisResultados}" var="pr">
                            	<c:if test="${pr.idEstado != 'X' 	&& pr.tipo == 'A'}">
                                <c:set var="cont" value="${cont+1}" />
                                <c:set var="aux" value="${aux + pr.ponderacion}" />
                                <tr>
                                    <td>${cont}</td>
                                    <td>${pr.resultado}</td>
                                    <td>${pr.indicador}</td>
                                    <td>${pr.ponderacion}</td>
                                    <th><fmt:formatDate pattern = "yyyy" value = "${pr.registro}"/></th>
                                    <!-- <td>
                                        <form  method="post" action="${urlM}">
                                            <input type="hidden" name="idResultado" value="${pr.idResultado}">
                                            <span class="icon-input-btn"> <i class="fa fa-pencil-square-o yvela" aria-hidden="true"> </i> <input class="btn btn-success" type="submit"  value="<spring:message  code="expenses.modify"/>" > </span>
                                        </form>
                                    </td>-->
                                    <td>
                                    	<button type="button" class="btn btn-warning" data-toggle="modal" data-target="#exampleModal${pr.idResultado}">
											 Editar
										</button>
											
                                        <form name="Delet" action="${urlD}" method="post">
                                            <input type="hidden" name="idResultado" value="${pr.idResultado}">
                                            <input class="btn btn-danger" type="submit"  value="<spring:message  code="expenses.remove"/>"> </span>
                                        </form>
                                        
                                    </td>
                                </tr>
                                </c:if>
                          </c:forEach>
                        </tbody>
                        <!--<tfoot>
                            <tr>
                                <th>Nro</th>
                                <th>Resultado</th>
                                <th>Indicador</th>
                                <th>Ponderacion</th>
                                <th>Gestion</th>
                                 
                                <th><spring:message  code="expenses.modify"/> </th>
                                <th><spring:message  code="expenses.remove"/></th>
                            </tr>
                        </tfoot>-->
                    </table>
                    
                    <!-- Button trigger modal -->
					<c:forEach items="${poaisObjetivos.poaisResultados}" var="pr">
					<!-- Modal -->
					<div class="modal fade bd-example-modal-lg" id="exampleModal${pr.idResultado}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
					  <div class="modal-dialog modal-lg" role="document">
					    <div class="modal-content">
					      <div class="modal-header">
					        <h5 class="modal-title" id="exampleModalLabel">Editar Objetivo POAI</h5>
					        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
					          <span aria-hidden="true">&times;</span>
					        </button>
					      </div>
					      <div class="modal-body">
					      
						      <form:form modelAttribute="poaisObjetivos" action="../../../../Castanhero/poaiObjetivo/editarResultado" enctype="multipart/form-data" method="post">
			                    
			                    <input type="hidden" name="idResultado" value="${pr.idResultado}">
			                    
			              		<div class="form-group row">
			                        <div class="col-sm-12">
			                            
			                            <input  name="resultado"  class="form-control form-control"  placeholder="Resultado" value="${pr.resultado}"/> 
			                            <br>
			                            <input  name="indicador"  class="form-control form-control"  placeholder="Indicador" value="${pr.indicador}"/> 
			                            <br>
			                            <input  name="ponderacion"  class="form-control form-control"  placeholder="Ponderacion" value="${pr.ponderacion}"/> 
			                        </div>
			                    </div>
			                 
				                <button type="submit" class="btn btn-success float-right" aria-hidden="true">Editar</button>
				            
				            </form:form>
					      </div>
					    </div>
					  </div>
					</div>
                    </c:forEach>
                    <br>
                    <div class="row">
			            <div class="col-md-10">
			                <p class="h1">Ponderacion acumulada ${aux} </p>
			            </div>
			        </div>
                    
                    <br>               
                    <div class="modal-footer">
	                    <a href="../../../../Castanhero/poaiObjetivo/AdministrarObjetivoActividad" type="submit" class="btn btn-info btn-lg float-right" aria-hidden="true">
	                    Siguiente
	                    </a>
	            	</div>
	            	<br>
                    <!-- <table id="tComprobantes"  class="table" style="width:100%">
                        <thead>
                            <tr>
                                <th><spring:message  code="poai.form.campo.descripcion"/></th>
                                <th><spring:message  code="items.year"/></th>
                                <th><spring:message  code="units.unit"/></th>
                                <th><spring:message  code="expenses.modify"/> </th>
                                <th><spring:message  code="expenses.remove"/></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:set var="cont" value="0" />
                            <c:forEach items="${lPoaisObjetivos}" var="po">
                                <c:set var="cont" value="${cont+1}" />
                                <tr>
                                    <td>${po.poais.descripcion}</td>
                                    <td>${po.poais.gestion}</td>
                                    <td>${po.poais.pnlPersonalAdministrativos.insUnidadesFuncionales.unidadFuncional}</td>
                                    <td>
                                        <form  method="post" action="${urlM}">
                                            <input type="hidden" name="idSacComprobante" value="${po.idObjetivo}">
                                            <span class="icon-input-btn"> <i class="fa fa-pencil-square-o yvela" aria-hidden="true"> </i> <input class="btn btn-success" type="submit"  value="<spring:message  code="expenses.modify"/>" > </span>
                                        </form>
                                    </td>
                                    <td>
                                        <form name="Delet" action="${urlD}" method="post">
                                            <input type="hidden" name="idSacComprobante" value="${lPoa.idPoai}">
                                            <span class="icon-input-btn"> <i class="fa fa-times yvela" aria-hidden="true"> </i> <input class="btn btn-danger" type="submit"  value="<spring:message  code="expenses.remove"/>"> </span>
                                        </form>
                                    </td>
                                </tr>
                          </c:forEach>
                        </tbody>
                        <tfoot>
                            <tr>
                                <th><spring:message  code="poai.form.campo.descripcion"/></th>
                                <th><spring:message  code="items.year"/></th>
                                <th><spring:message  code="units.unit"/></th>
                                <th><spring:message  code="expenses.modify"/> </th>
                                <th><spring:message  code="expenses.remove"/></th>
                            </tr>
                        </tfoot>
                    </table> -->
                    <hr align="left" noshade="noshade" size="10" width="100%" />

                </c:when>
            </c:choose>
    </section>

<!-- Modal Visualizar PDF -->

		<!-- --------------------- Inicio registro nueva Poais Objetivo -------------------- -->
       <section>
        <c:choose>
            <c:when test="${operation eq 'reg' }">
                <center><br>
                    <p class="h3"><i class="fa fa-folder-open" aria-hidden="true"> </i> <spring:message  code="poais.titulo.form"/></p> 
                    <br>
                </center>
                <form:form modelAttribute="poaisObjetivos" action="${regPoais}" enctype="multipart/form-data" method="post">
                    <input type="hidden" name="idObjetivo" value="${poaisObjetivos.idObjetivo}">
                    
              		<div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label">Objetivo de Cargo</label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6" id="combo1">
                            <textarea name="objetivoCargo" class="form-control form-control-lg" rows="3" cols="20" >${poaisObjetivos.poais.pnlPersonalAdministrativos.pnlCargos.objetivoCargo}</textarea>
                        </div>
                        <div class="col-sm-3" id="comd"> 
                            <small id="emailHp" class="form-text text-muted">
                                <spring:message  code="poais.from.obs.descripcion"/>
                            </small>
                            <small class="msgError">
                                
                            </small>
                        </div>
                    </div>
                    
                    <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label">Registro Resultados</label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            
                            <input  name="txtResultado"  class="form-control form-control-lg"  placeholder="Resultado" id="txtResultado"/> 
                            <br>
                            <input  name="txtIndicador"  class="form-control form-control-lg"  placeholder="Indicador" id="txtIndicador"/> 
                            <br>
                            <input  name="txtPonderacion"  class="form-control form-control-lg"  placeholder="Ponderacion" id="txtPonderacion"/> 
                            
                        </div>
                        
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <button type="button" class="btn btn-success" id="btnResultado" aria-hidden="true"><i class="fa fa-plus-square-o" aria-hidden="true"></i> <spring:message  code="people.add"/> Resultado</button>
                            </small>
                            <small class="msgError">
                            </small>
                        </div>
                    </div>
                    
                    <div class="form-group row">
                        <div class="col-sm-12" id="combo1"> 
                            <table id="tResultado" class="table table-striped">
                                <thead>
                                    <tr> 
                                        <th>Nro.</th> 
                                        <th>Resultado</th> 
                                        <th>Indicador</th> 
                                        <th>Ponderacion</th>
                                        <th><spring:message  code="expenses.remove"/></th>
                                    </tr> 
                                </thead> 
                                <tbody>
                                </tbody>
                            </table>
                        </div>
                        <br>
                        <div class="col-md-12 mb-5">
			                <p class="h3 text-center" id="tPonderacion">Total Ponderaci�n:</p>
			            </div>
                    </div>
                 
                <div class="modal-footer">
                    <button class="btn btn-outline-secondary btn-lg" data-dismiss="modal" aria-hidden="true"><i class="fa fa-undo" aria-hidden="true"></i>  <spring:message  code="people.cancel"/></button>
                    <button type="submit" class="btn btn-success btn-lg float-right" aria-hidden="true"><i class="fa fa-plus-square-o" aria-hidden="true"></i>   <spring:message  code="people.add"/></button>
                    
                </div>
                 	
                </form:form>
                <br><br>
                <hr align="left" noshade="noshade" size="10" width="100%" />
            </c:when>
        </c:choose>
</section>
<!-- -------------------- Final Nuevo Objetivo ---------------- -->
</div>

