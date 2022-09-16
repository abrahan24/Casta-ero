<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%@
taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> <%@ taglib
prefix="form" uri="http://www.springframework.org/tags/form" %> <%@
taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<head>
<link rel="stylesheet" type="text/css" href=" <c:url value=" /resource/admPersonas/personas.css"/>
" />
<script src="<spring:url value=" /resource/sac/admComprob/admComprob.js" />
" type="text/javascript">
</script>
<script src="<spring:url value=" /resource/poais/objetivo/admObjetivo.js" />
" type="text/javascript">
</script>
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
				<p class="h1">
					<i class="fa fa-file-archive-o" aria-hidden="true"> </i>
					Supervision POAI
				</p>
			</div>

		</div>
		<hr align="left" noshade="noshade" size="10" width="100%" />
	</section>

	<section class="content-body">
		<c:if test="${not empty error}">
			<div class="alert alert-danger">
				Hola
				<spring:message
					code="AbstractUserDetailsAuthenticationProvider.badCredentials" />
				<br />
			</div>
		</c:if>

		<!--Tabla lista de Todos los Comprobantes Egreso Registrados -->
		<c:choose>
			<c:when test="${busqueda eq 'find' }">
				<center>
					<br>
					<p class="h3">
						<i class="fa fa-folder-open-o" aria-hidden="true"></i> Lista
						Supervision de POAI
					</p>
					<br>
				</center>
				<table id="tComprobantes" class="table" style="width: 100%">
					<thead>
						<tr>
							<th>Nro</th>
							<th>Unidad Funcional</th>
							<th>Cargo Administrativo</th>
							<th>Personal</th>
							<th>Gestion</th>
							<th>Operaciones</th>
							<th>POAI</th>
							<!-- 
							<th><spring:message code="expenses.remove" /></th>-->
						</tr>
					</thead>
					<tbody>
						<c:set var="cont" value="0" />
						<c:forEach items="${identificaciones}" var="sup">
							<c:set var="cont" value="${cont+1}" />
							<tr>
								<td>${cont}</td>
								<td>${sup.poais.pnlPersonalAdministrativos.insUnidadesFuncionales.unidadFuncional}</td>
								<td>${sup.poais.pnlPersonalAdministrativos.pnlCargos.cargo}</td>
								<td>${sup.poais.pnlPersonalAdministrativos.personas.nombres}
									${sup.poais.pnlPersonalAdministrativos.personas.paterno}
									${sup.poais.pnlPersonalAdministrativos.personas.materno}</td>
								<td>${sup.poais.pnlPersonalAdministrativos.gestion}</td>
								<td>
									<button type="button" class="btn btn-success"
										data-toggle="modal"
										data-target="#exampleModal${sup.idIdentificacion}">
										<i class="fa fa-pencil-square-o yvela" aria-hidden="true"></i>
										Evaluar Personal
									</button>
								</td>
								<td>
									<a class="btn btn-success" target="_blank" href="../../../../Castanhero/poaiFormulario/generar/${sup.poais.idPoai}"> Generar POAI</a>
								</td>

							</tr>


						</c:forEach>
					</tbody>
					<tfoot>
						<tr>
							<th>Nro</th>
							<th>Unidad Funcional</th>
							<th>Cargo Administrativo</th>
							<th>Personal</th>
							<th>Gestion</th>
							<th>Operaciones</th>
							<!-- 
							<th><spring:message code="expenses.remove" /></th>-->
						</tr>
					</tfoot>
				</table>
				<br>
				<hr align="left" noshade="noshade" size="10" width="100%" />

				<c:forEach items="${identificaciones}" var="sup">
				<!-- Modal -->
				<div class="modal fade bd-example-modal-lg" id="exampleModal${sup.idIdentificacion}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
					<div class="modal-dialog modal-lg" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="exampleModalLabel">
									${sup.poais.pnlPersonalAdministrativos.personas.nombres}
									${sup.poais.pnlPersonalAdministrativos.personas.paterno}
									${sup.poais.pnlPersonalAdministrativos.personas.materno}</h5>
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
								
									<c:set var="aux" value="0" />
									<c:forEach items="${sup.poais.poaisObjetivos}" var="ob">
										
										<c:if test="${ob.poais.idPoai == sup.poais.idPoai && aux == 0}">
											<c:set var="aux" value="1" />
											<c:set var="cont" value="0" />
											<c:forEach items="${ob.poaisResultados}" var="pr">
												<c:if test="${pr.idEstado != 'X' && pr.tipo == 'A'}">
													<c:set var="cont" value="${cont+1}" />
													
													<table width="100%" class="table table-sm table-bordered border-dark">
													
													<tr>
														<th>#</th>
														<th>RESULTADOS ESPECIFICOS</th>
														<th>PONDERACION</th>
														<th>INDICADORES</th>
													</tr>
													
													<tr>
														<td>${cont}</td>
														<td style="text-align: justify;" width="65%">${pr.resultado}</td>
														<td class="text-center">${pr.ponderacion} %</td>
														<td style="text-align: justify;" width="7%">${pr.indicador}</td>
														
													</tr>
													
													<tr>
														<td colspan="4">
															<br>
															<table width="100%"
																class="table table-sm table-bordered border-dark">
																<tr>
																	<th  width="25%">ACTIVIDAD</th>
																	<th  width="25%">MEDIO DE VERIFICACION</th>
																	<th  width="40%">% DE EJECUCION</th>
																	<th  width="10%">EVALUAR</th>
																</tr>
																<c:forEach items="${pr.poaisActividades}" var="act">
																	<tr>
																		<form target="_blank" action="../../../../Castanhero/poaiSupervision/" method="post">
																			<td>${act.actividad}</td>
																			<td>${act.medioVerificacion}</td>
																			
																			<c:if test="${act.puntaje != null}">
																			<td class="text-center">${act.porcentaje} %</td>
																			<td class="text-center">${act.puntaje}</td>
																			</c:if>
																			
																			<c:if test="${act.puntaje == null}">
																			<td>																			
																				<input type="hidden" name="id_actividad" value="${act.idActividad}">
																				<input type="number" name="ponderacion" class="form-control" placeholder="0% - 100%">
																			</td>
																			<td>
																			<button type="submit" class="btn btn-primary" >																			
																				Evaluar
																			</button>
																			</td>																			
																			</c:if>
																			
																		</form>
																	</tr>
																</c:forEach>
															</table>
														</td>
													</tr>
													
													<tr>
														<th colspan="4" class="text-center">${pr.puntaje} % Evaluado</th>
													</tr>
													
													</table>
												</c:if>
											</c:forEach>
										</c:if>
									</c:forEach>
								
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary"
									data-dismiss="modal">Cerrar</button>
							</div>
						</div>
					</div>
				</div>
				</c:forEach>


			</c:when>
		</c:choose>
	</section>

</div>
<script>
function realizaProceso(valorCaja1, valorCaja2){
        var parametros = {
                "valorCaja1" : valorCaja1,
                "valorCaja2" : valorCaja2
        };
        $.ajax({
                data:  parametros, //datos que se envian a traves de ajax
                url:   'ejemplo_ajax_proceso.php', //archivo que recibe la peticion
                type:  'post', //método de envio
                beforeSend: function () {
                        $("#resultado").html("Procesando, espere por favor...");
                },
                success:  function (response) { //una vez que el archivo recibe el request lo procesa y lo devuelve
                        $("#resultado").html(response);
                }
        });
}
</script>
