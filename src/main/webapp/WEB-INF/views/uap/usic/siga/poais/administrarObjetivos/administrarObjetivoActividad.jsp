<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<head>
<link rel="stylesheet" type="text/css"
	href=" <c:url value="/resource/admPersonas/personas.css"/>" />
<script
	src="<spring:url value="/resource/sac/admComprob/admComprob.js" />"
	type="text/javascript"></script>
<script
	src="<spring:url value="/resource/poais/objetivo/admObjetivo.js" />"
	type="text/javascript"></script>
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
					Administrar Objetivo de POAI
				</p>
			</div>
			
			<div class="col-sm-2 text-right" id="reporte">
                <c:choose>
                    <c:when test="${busqueda eq 'find' }">
                        <form  method="POST" action="${urlR}">
                        	<input type="hidden" name="idObjetivo" value="${poaisObjetivos.idObjetivo}">
                            <span class="icon-input-btn"><i class="fa fa-folder yvela" aria-hidden="true"></i> <input type="submit" class="btn btn-success btn-lg" value="<spring:message  code="income.btn.new"/> Resultado"></span>
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
						Resultados de POAI
					</p>
					<br>
				</center>
				<c:set var="aux" value="0" />
                <table id="tComprobantes" class="table" style="width: 100%">
					<thead>
						<tr>
							<th>Nro</th>
							<th>Resultado</th>
							<th>Indicador</th>
							<th>Ponderacion</th>
							<th><spring:message code="expenses.modify" /></th>
							<!-- 
							<th><spring:message code="expenses.remove" /></th>-->
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
								<td>
									<form method="get" action="${urlN}">
										<input type="hidden" name="idResultado"
											value="${pr.idResultado}"> <span
											class="icon-input-btn"> <i
											class="fa fa-pencil-square-o yvela" aria-hidden="true"> </i>
											<input class="btn btn-success" type="submit"
											value="Agregar Actividad">
										</span>
									</form>
								</td>
								<!-- 
								<td>
									<form name="Delet" action="${urlD}" method="post">
										<input type="hidden" name="idSacComprobante"
											value="${pr.idResultado}"> <span
											class="icon-input-btn"> <i class="fa fa-times yvela"
											aria-hidden="true"> </i> <input class="btn btn-danger"
											type="submit"
											value="<spring:message  code="expenses.remove"/>">
										</span>
									</form>
								</td>-->
							</tr>
							</c:if>
						</c:forEach>
					</tbody>
					<tfoot>
						<tr>
							<th>Nro</th>
							<th>Resultado</th>
							<th>Indicador</th>
							<th>Ponderacion</th>
							<th><spring:message code="expenses.modify" /></th>
							<!-- 
							<th><spring:message code="expenses.remove" /></th>-->
						</tr>
					</tfoot>
				</table>
				<br>
                    <div class="row">
			            <div class="col-md-10">
			                <p class="h1">Ponderacion acumulada ${aux} </p>
			            </div>
			        </div>
				<br>
				<div class="modal-footer">
	                    <a href="../../../../Castanhero/poaiObjetivo/AdministrarObjetivoContinuo" type="submit" class="btn btn-info btn-lg float-right" aria-hidden="true">
	                    Siguiente
	                    </a>
	                </div>
	            <br>
				<hr align="left" noshade="noshade" size="10" width="100%" />

			</c:when>
		</c:choose>
	</section>

	<!-- Modal Visualizar PDF -->

	<!-- --------------------- Inicio registro nueva Poais Objetivo -------------------- -->
	<section>
		<c:choose>
			<c:when test="${operation eq 'reg' }">
				<center>
					<br>
					<p class="h4">Resultado Seleccionado</p>
				</center>

				<div>
					<table border="1" style="width: 100%">
						<thead>
							<tr>
								<th class="text-center">Resultado</th>
								<th class="text-center">Indicador</th>
								<th class="text-center">Ponderacion</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td class="text-center"><h6>${poaisResultados.resultado}</h6></td>
								<td class="text-center"><h6>${poaisResultados.indicador}</h6></td>
								<td class="text-center"><h6>${poaisResultados.ponderacion}</h6></td>
							</tr>
						</tbody>
					</table>
				</div>

				<center>
					<br>
					<p class="h4">Lista de Actividades</p>
				</center>

				<div>
					<table border="1" style="width: 100%">
						<thead>
							<tr>
								<th class="text-center">Nro</th>
								<th class="text-center">Actividad</th>
								<th class="text-center">Medio de Verificacion</th>
								<th class="text-center">Ponderacion</th>
								<th class="text-center">Mas Detalles</th>
							</tr>
						</thead>
						<tbody>
							<c:set var="cont" value="0" />
							<c:set var="ponde" value="0" />
							<c:forEach items="${poaisResultados.poaisActividades}" var="pa">
								<c:if test="${pa.idEstado != 'X'}">
									<c:set var="cont" value="${cont+1}" />
									<c:set var="ponde" value="${ponde + pa.ponderacion}" />
									<tr>
										<td class="text-center">${cont}</td>
										<td class="text-center">${pa.actividad}</td>
										<td class="text-center">${pa.medioVerificacion}</td>
										<td class="text-center">${pa.ponderacion}</td>
										<td class="text-center">
											<button type="button" class="btn btn-success" data-toggle="modal" data-target=".bd-example-modal-lg${pa.idActividad}">
												Ver Detalles
											</button> 
											<a href="${'/Castanhero/poaiObjetivo/eliminarActividad?idActividad='}${pa.idActividad}" class="btn btn-danger">Eliminar</a>
										</td>
									</tr>
								</c:if>
							</c:forEach>
						</tbody>
					</table>
					<br>
					
					<div class="row text-center">
			            <div class="col-md-10">
			                <p class="h3">Pondetacion por Actividad acumulada: ${ponde} </p>
			            </div>
			        </div>
			        
					<hr align="left" noshade="noshade" size="10" width="100%" />
				</div>

				<!-- Modal -->
				<c:forEach items="${poaisResultados.poaisActividades}" var="pa">
				<c:if test="${pa.idEstado != 'X'}">
				<div class="modal fade bd-example-modal-lg${pa.idActividad}" tabindex="-1"
					role="dialog" aria-labelledby="myLargeModalLabel"
					aria-hidden="true">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="exampleModalLabel">${pa.actividad}</h5>
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
								<div class="table-responsive">
									<table border="1" style="width: 100%">
										<thead>
											<tr>
												<th class="text-center">Mes</th>
												<th class="text-center">Semana</th>
												<th class="text-center">Verificacion</th>
												<th class="text-center">Estado</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${pa.poaisDetallesActividades}" var="pda">
												<tr>
													<td class="text-center">${pda.poaisMeses.mes}</td>
													<td class="text-center">${pda.poaisSemanas.semana}</td>
													
													<td class="text-center">
														<div class="form-group"><br>
															<input type="file" name="archivo">
														</div>
													</td>
													
													
													<td class="text-center">
														<div class="form-group"><br>
															<input type="button" class="btn btn-warning" value="Entregar">
														</div>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
				</c:if>
				</c:forEach>
				
				<c:if test="${ponde != poaisResultados.ponderacion}">
				<center>
					<br>
					<p class="h3">
						<i class="fa fa-folder-open" aria-hidden="true"> </i> Registrar
						Actividad
					</p>
					<br>
				</center>
				<form:form modelAttribute="poaisActividades" action="${regPoais}"
					enctype="multipart/form-data" method="post">

					<input type="hidden" name="idResultado"
						value="${poaisResultados.idResultado}">
					<input type="hidden" name="idActividad"
						value="${poaisActividades.idActividad}">

					<div class="form-group row">
						<label for="codigo" class="col-sm-2 col-form-label">
							Actividad de Resultado</label> <label
							class="col-sm-1 col-form-label text-right"> <i
							class="fa fa-info-circle" aria-hidden="true"></i>
						</label>

						<div class="col-sm-6" id="combo1">
							<textarea name="actividad" class="form-control form-control-lg"
								rows="3" cols="20" placeholder="Describir Actividad">${poaisActividades.actividad}</textarea>
						</div>

						<div class="col-sm-3" id="comd">
							<small id="emailHp" class="form-text text-muted">Introduzca
								Actividad </small> <small class="msgError"> </small>
						</div>
					</div>

					<div class="form-group row">
						<label for="codigo" class="col-sm-2 col-form-label">Medio
							de Verificacion</label> <label class="col-sm-1 col-form-label text-right">
							<i class="fa fa-info-circle" aria-hidden="true"></i>
						</label>

						<div class="col-sm-6" id="combo1">
							<textarea name="medio" class="form-control form-control-lg"
								rows="3" cols="20" placeholder="Medio de Verificacion">${poaisActividades.medioVerificacion}</textarea>
						</div>
						<div class="col-sm-3" id="comd">
							<small id="emailHp" class="form-text text-muted">
								Introduzca Medio de Verificacion </small> <small class="msgError">
							</small>
						</div>
					</div>
					
					<div class="form-group row">
						<label for="codigo" class="col-sm-2 col-form-label">Ponderacion</label> <label class="col-sm-1 col-form-label text-right">
							<i class="fa fa-info-circle" aria-hidden="true"></i>
						</label>

						<div class="col-sm-6" id="combo1">
							<input type="number" name="ponderacion" class="form-control form-control-lg" placeholder="La ponderacion acumulada debe ser menor igual que ${poaisResultados.ponderacion - ponde}" value="${poaisActividades.ponderacion}"/>
						</div>
						
						<div class="col-sm-3" id="comd">
							<small id="emailHp" class="form-text text-muted">
								Introduzca Ponderacion </small> <small class="msgError">
							</small>
						</div>
					</div>
					<hr align="left" noshade="noshade" size="10" width="100%" />

					<center>
						<br>
						<p class="h4">Cronograma de Planificacion</p>
						<br>
					</center>

					<div>
						<table class="" border="1" style="width: 100%">
							<thead>
								<tr>
									<th class="text-center">Mes</th>
									<th class="text-center">1ra Sem</th>
									<th class="text-center">2da Sem</th>
									<th class="text-center">3ra Sem</th>
									<th class="text-center">4ta Sem</th>
								</tr>
							</thead>
							<tbody>
								<c:set var="cont" value="0" />
								<c:forEach items="${poaisMeses}" var="pm">
									<tr>
										<th class="text-center" scope="row">${pm.mes}</th>
										<td class="text-center"><br>
											<div class="form-group">
												<input type="checkbox" id="switcherySize2" name="semanas"
													value="${pm.idMes}${':'}${poaisSemanas.get(0).idSemana}"
													class="switchery" data-size="sm" />
											</div></td>

										<td class="text-center"><br>
											<div class="form-group">
												<input type="checkbox" id="switcherySize2" name="semanas"
													value="${pm.idMes}${':'}${poaisSemanas.get(1).idSemana}"
													class="switchery" data-size="sm" />
											</div></td>

										<td class="text-center"><br>
											<div class="form-group">
												<input type="checkbox" id="switcherySize2" name="semanas"
													value="${pm.idMes}${':'}${poaisSemanas.get(2).idSemana}"
													class="switchery" data-size="sm" />
											</div></td>

										<td class="text-center"><br>
											<div class="form-group">
												<input type="checkbox" id="switcherySize2" name="semanas"
													value="${pm.idMes}${':'}${poaisSemanas.get(3).idSemana}"
													class="switchery" data-size="sm" />
											</div></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>

					<div class="modal-footer">
						<button type="submit" class="btn btn-success btn-lg float-right"
							aria-hidden="true">
							<i class="fa fa-plus-square-o" aria-hidden="true"></i>
							<spring:message code="people.add" />
						</button>
					</div>
					<hr align="left" noshade="noshade" size="10" width="100%" />
				</form:form>
				<br>
				</c:if>
				<div class="modal-footer">
				    <a href="../../../../Castanhero/poaiObjetivo/AdministrarObjetivoActividad" type="submit" class="btn btn-info btn-lg float-right" aria-hidden="true">
				    Volver
				    </a>
			    </div>
			</c:when>
			
		</c:choose>
		
	</section>
	<!-- -------------------- Final Nuevo Objetivo ---------------- -->
</div>