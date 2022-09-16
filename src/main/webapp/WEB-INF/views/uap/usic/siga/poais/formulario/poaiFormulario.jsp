<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<head>
<link rel="stylesheet" type="text/css"
	href=" <c:url value="/resource/admPersonas/personas.css"/>" />
<script
	src="<spring:url value="/resource/poais/identification/admIdentification.js" />"
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
					Formulario POAI
				</p>
			</div>
			
		</div>
		<hr align="left" noshade="noshade" size="10" width="100%" />
	</section>

	<section class="content-body">
		<c:if test="${not empty error}">
			<div class="alert alert-danger">
				<spring:message
					code="AbstractUserDetailsAuthenticationProvider.badCredentials" />
				<br />
			</div>
		</c:if>

		<!-- ------------------------ Fin Tabla Listar Poais ------------------------- -->
		<c:choose>
			<c:when test="${busqueda eq 'find' }">
				<center>
					<br>
					<p class="h3">
						<i class="fa fa-folder-open-o" aria-hidden="true"></i>
						Lista de POAIs generados
					</p>
					<br>
				</center>
				<table id="tComprobantes" class="table" style="width: 100%">
					<thead>
						<tr>
							<th>Nombre Completo</th>
							<th><spring:message code="items.year" /></th>
							<th><spring:message code="units.unit" /></th>
							<th>Operaciones</th>
						</tr>
					</thead>
					<tbody>
						<c:set var="cont" value="0" />
						<c:forEach items="${lPoais}" var="lPoa">
							<c:set var="cont" value="${cont+1}" />
							<tr>
								<td>${persona.nombres} ${persona.paterno} ${persona.materno}</td>
								<td>${lPoa.gestion}</td>
								<td>${lPoa.pnlPersonalAdministrativos.insUnidadesFuncionales.unidadFuncional}</td>
								<td>
									<a class="btn btn-success" target="_blank" href="../../../../Castanhero/poaiFormulario/generar/${lPoa.idPoai}"> Generar POAI</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
					<tfoot>
						<tr>
							<th>Nombre Completo</th>
							<th><spring:message code="items.year" /></th>
							<th><spring:message code="units.unit" /></th>
							<th>Operaciones</th>
						</tr>
					</tfoot>
				</table>
				<br>
				<br>
				<hr align="left" noshade="noshade" size="10" width="100%" />

			</c:when>
		</c:choose>
	</section>
	<!--  ---------------------- Fin Tabla Listar Poais -----------------  -->
</div>