<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<head>
<link rel="stylesheet" type="text/css"
	href=" <c:url value="/resource/admPersonas/personas.css"/>" />
<script src="<spring:url value="/resource/admPersonas/personas.js" />"
	type="text/javascript"></script>
<style>
</style>

</head>
<div class="container">
	<section class="content-header">
		<div class="row">
			<div class="col-md-10 mb-3">
				<p class="h1">
					<i class="fa fa-users" aria-hidden="true"></i>
					<spring:message code="people.form.title" />
				</p>
			</div>
			<div class="col-sm-2 text-right" id="reporte">
				<c:choose>
					<c:when test="${busqueda eq 'find' }">
						<form method="POST" action="${urlN}">
							<span class="icon-input-btn"><i
								class="fa fa-user-plus yvela" aria-hidden="true"></i> <input
								type="submit" class="btn btn-success btn-lg"
								value="<spring:message  code="income.btn.new"/>"></span>
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
				<spring:message
					code="AbstractUserDetailsAuthenticationProvider.badCredentials" />
				<br />
			</div>
		</c:if>

		<!--------------- Inicio registro nueva persona-->
		<c:choose>
			<c:when test="${operation eq 'reg' }">
				<center>
					<br>
					<p class="h3">
						<i class="fa fa-newspaper-o" aria-hidden="true"> </i>
						<spring:message code="persona.crearPersona" />
					</p>
					<br>
				</center>

				<form:form modelAttribute="personas" action="${pers}" method="post">

					<div class="form-group row">
						<label for="nombres" class="col-sm-2 col-form-label"><spring:message
								code="persona.nombres" /></label> <label
							class="col-sm-1 col-form-label text-right"> <i
							class="fa fa-info-circle" aria-hidden="true"></i></label>
						<div class="col-sm-6">
							<spring:message code="people.name.msg" var="nom" />
							<form:input type="text" path="nombres"
								class="form-control form-control-lg" placeholder="${nom}" />
						</div>
						<div class="col-sm-3" id="comd">
							<small class="form-text text-muted"> <spring:message
									code="people.name.msg" />
							</small> <small class="msgError"> <form:errors path="nombres" />
							</small>
						</div>
					</div>
					<div class="form-group row">
						<label for="paterno" class="col-sm-2 col-form-label"><spring:message
								code="persona.paterno" /></label> <label
							class="col-sm-1 col-form-label text-right"></label>
						<div class="col-sm-6">
							<spring:message code="people.paternal.msg" var="pat" />
							<form:input type="text" path="paterno"
								class="form-control form-control-lg" placeholder="${pat}" />
						</div>
						<div class="col-sm-3" id="comd">
							<small class="form-text text-muted"> <spring:message
									code="people.paternal.msg" />
							</small> <small id="emailHp" class="form-text text-muted formFieldError">
								<form:errors path="paterno" cssClass="alert alert-danger" />
							</small>
						</div>
					</div>
					<div class="form-group row">
						<label for="materno" class="col-sm-2 col-form-label"><spring:message
								code="persona.materno" /></label> <label
							class="col-sm-1 col-form-label text-right"></label>
						<div class="col-sm-6">
							<spring:message code="people.maternal.msg" var="mat" />
							<form:input type="text" path="materno"
								class="form-control form-control-lg" placeholder="${mat}" />
						</div>
						<div class="col-sm-3" id="comd">
							<small class="form-text text-muted"> <spring:message
									code="people.maternal.msg" />
							</small> <small id="emailHp" class="form-text text-muted formFieldError">
								<form:errors path="materno" cssClass="alert alert-danger" />
							</small>
						</div>
					</div>
					<div class="form-group row">
						<label for="cedula" class="col-sm-2 col-form-label"><spring:message
								code="persona.cedula" /></label> <label
							class="col-sm-1 col-form-label text-right"> <i
							class="fa fa-info-circle" aria-hidden="true"></i></label>
						<div class="col-sm-6">
							<spring:message code="people.identity.msg" var="ced" />
							<form:input type="text" path="ci"
								class="form-control form-control-lg" placeholder="${ced}" />
						</div>
						<div class="col-sm-3" id="comd">
							<small class="form-text text-muted"> <spring:message
									code="people.identity.msg" />
							</small> <small class="msgError"> <form:errors path="ci" />
							</small> <small class="msgError"> <c:choose>
									<c:when test="${mensage eq 'err' }">
										<i class="fa fa-exclamation-triangle" aria-hidden="true"></i>
										<spring:message code="people.inf.ci" />
									</c:when>
								</c:choose>
							</small>
						</div>
					</div>

					<div class="form-group row">
						<label for="ciExpedido" class="col-sm-2 col-form-label"><spring:message
								code="people.issued" /></label> <label
							class="col-sm-1 col-form-label text-right"> <i
							class="fa fa-info-circle" aria-hidden="true"></i></label>
						<div class="col-sm-6">
							<form:select class="form-control form-control-lg"
								path="PrsCiExpedidos.idPrsCiExpedido" id="ciExpedido">
								<c:forEach var="cix" items="${requestScope.lExpedidos}">
									<form:option value="${cix.idPrsCiExpedido}">${cix.prsCiExpedido}</form:option>
								</c:forEach>
							</form:select>
						</div>
						<div class="col-sm-3" id="comd">
							<small class="form-text text-muted"> <a class="btn add"
								href="#" data-toggle="modal" data-target="#formCiExpedido"><i
									class="fa fa-plus-square-o" aria-hidden="true"></i> <spring:message
										code="people.add" /></a></small> <small id="ci"
								class="form-text text-muted formFieldError"> <form:errors
									path="PrsCiExpedidos.idPrsCiExpedido"
									cssClass="alert alert-danger" />
							</small>
						</div>
					</div>
					<div class="form-group row">
						<label for="ciExpedido" class="col-sm-2 col-form-label"><spring:message
								code="people.academic.degree" /></label> <label
							class="col-sm-1 col-form-label text-right"> <i
							class="fa fa-info-circle" aria-hidden="true"></i></label>
						<div class="col-sm-6">
							<form:select class="form-control form-control-lg"
								path="PrsGradosAcademicos.idGradoAcademico" id="gradosA">
								<c:forEach var="ga" items="${requestScope.lGradosAcademicos}">
									<form:option value="${ga.idGradoAcademico}">${ga.descripcion}</form:option>
								</c:forEach>
							</form:select>
						</div>
						<div class="col-sm-3" id="comd">
							<small class="form-text text-muted"> <a class="btn add"
								href="#" data-toggle="modal" data-target="#formGradosAcademicos"><i
									class="fa fa-plus-square-o" aria-hidden="true"></i> <spring:message
										code="people.add" /></a></small> <small id="ci"
								class="form-text text-muted formFieldError"> <form:errors
									path="PrsGradosAcademicos.idGradoAcademico"
									cssClass="alert alert-danger" />
							</small>
						</div>
					</div>


					<div class="form-group row">
						<label for="paterno" class="col-sm-2 col-form-label"><spring:message
								code="login.email" /></label> <label
							class="col-sm-1 col-form-label text-right"> <i
							class="fa fa-info-circle" aria-hidden="true"></i>
						</label>
						<div class="col-sm-6">
							<spring:message code="info.login.email" var="pat" />
							<form:input type="text" path="email"
								class="form-control form-control-lg" placeholder="${pat}" />
						</div>
						<div class="col-sm-3" id="comd">
							<small class="form-text text-muted"> <spring:message
									code="info.login.email" />
							</small> <small class="msgError"> <form:errors path="email" />
							</small>
						</div>
					</div>

					<div class="form-group row">
						<label for="ciExpedido" class="col-sm-2 col-form-label"><spring:message
								code="persona.fechaNac" /></label> <label
							class="col-sm-1 col-form-label text-right"></label>
						<div class="col-sm-6">
							<spring:message code="people.date.msg" var="fecN" />
							<form:input type="date" path="fecNacimiento"
								class="form-control form-control-lg" placeholder="${fecN}" />
						</div>
						<div class="col-sm-3" id="comd">
							<small class="form-text text-muted"> <spring:message
									code="people.date.msg" />
							</small> <small id="emailHp" class="form-text text-muted formFieldError">
								<form:errors path="fecNacimiento" cssClass="alert alert-danger" />
							</small>
						</div>
					</div>
					<div class="form-group row">
						<label for="direccion" class="col-sm-2 col-form-label"><spring:message
								code="persona.direccion" /></label> <label
							class="col-sm-1 col-form-label text-right"></label>
						<div class="col-sm-6">
							<spring:message code="people.direction.msg" var="dir" />
							<form:input type="text" path="direccion"
								class="form-control form-control-lg" placeholder="${dir}" />
						</div>
						<div class="col-sm-3" id="comd">
							<small class="form-text text-muted"> <spring:message
									code="people.direction.msg" />
							</small> <small id="emailHp" class="form-text text-muted formFieldError">
								<form:errors path="direccion" cssClass="alert alert-danger" />
							</small>
						</div>
					</div>
					<div class="form-group row">
						<label for="telefono" class="col-sm-2 col-form-label"><spring:message
								code="persona.telefono" /></label> <label
							class="col-sm-1 col-form-label text-right"> </label>
						<div class="col-sm-6">
							<spring:message code="people.telephone.msg" var="tel" />
							<form:input type="number" path="telefono"
								class="form-control form-control-lg" placeholder="${tel}" />
						</div>
						<div class="col-sm-3" id="comd">
							<small class="form-text text-muted"> <spring:message
									code="people.telephone.msg" />
							</small> <small id="emailHp" class="form-text text-muted formFieldError">
								<form:errors path="telefono" cssClass="alert alert-danger" />
							</small>
						</div>
					</div>
					<div class="form-group row">
						<label for="tipoSanguineo" class="col-sm-2 col-form-label"><spring:message
								code="persona.tipoSangre" /></label> <label
							class="col-sm-1 col-form-label text-right"> </label>
						<div class="col-sm-6">
							<spring:message code="people.blood.msg" var="ts" />
							<form:input type="text" path="tipoSanguineo"
								class="form-control form-control-lg" placeholder="${ts}" />
						</div>
						<div class="col-sm-3" id="comd">
							<small class="form-text text-muted"> <spring:message
									code="people.blood.msg" />
							</small> <small id="emailHp" class="form-text text-muted formFieldError">
								<form:errors path="tipoSanguineo" cssClass="alert alert-danger" />
							</small>
						</div>
					</div>
					<div class="form-group row">
						<label for="sexo" class="col-sm-2 col-form-label"><spring:message
								code="persona.sexo" /></label> <label
							class="col-sm-1 col-form-label text-right"> </label>
						<div class="col-sm-6">
							<form:select class="form-control form-control-lg"
								path="PrsTiposSexos.idPrsTipoSexo" id="tipoSexo">
								<c:forEach var="ts" items="${requestScope.lPrsTiposSexos}">
									<form:option value="${ts.idPrsTipoSexo}">${ts.prsTipoSexo}</form:option>
								</c:forEach>
							</form:select>
						</div>
						<div class="col-sm-3" id="comd">
							<small class="form-text text-muted"> <a class="btn add"
								href="#" data-toggle="modal" data-target="#formTipoSexo"><i
									class="fa fa-plus-square-o" aria-hidden="true"></i> <spring:message
										code="people.add" /></a></small> <small id="emailHp"
								class="form-text text-muted formFieldError"> <form:errors
									path="PrsTiposSexos.idPrsTipoSexo"
									cssClass="alert alert-danger" />
							</small>
						</div>
					</div>
					<div class="form-group row">
						<label for="pais" class="col-sm-2 col-form-label"><spring:message
								code="persona.pais" /></label> <label
							class="col-sm-1 col-form-label text-right"> </label>
						<div class="col-sm-6">
							<form:select class="form-control form-control-lg"
								path="PrsPaises.idPais" id="pais">
								<c:forEach var="p" items="${requestScope.lPaises}">
									<form:option value="${p.idPais}">${p.pais}</form:option>
								</c:forEach>
							</form:select>
						</div>
						<div class="col-sm-3" id="comd">
							<small class="form-text text-muted"> <a class="btn add"
								href="#" data-toggle="modal" data-target="#formPais"><i
									class="fa fa-plus-square-o" aria-hidden="true"></i> <spring:message
										code="people.add" /></a></small> </small> <small id="emailHp"
								class="form-text text-muted formFieldError"> <form:errors
									path="PrsPaises.idPais" cssClass="alert alert-danger" />
							</small>
						</div>
					</div>
					<div class="form-group row">
						<label for="msg" class="col-sm-2 col-form-label text-right"></label>
						<label for="msg" class="col-sm-10 col-form-label"><span
							class="parpadea text"><i class="fa fa-info-circle"
								aria-hidden="true"></i></span> &nbsp;&nbsp;&nbsp;<spring:message
								code="people.warning" /> </label>
					</div>

					<hr align="left" noshade="noshade" size="10" width="100%" />
					<div class="form-group row mt-12">
						<div class="col-sm-3" id="combo1"></div>
						<div class="col-sm-3" id="combo1">
							<span class="icon-input-btn"><i
								class="fa fa-pencil-square-o yvela" aria-hidden="true"></i> <input
								class="btn btn-success" type="submit"
								value="<spring:message  code="institution.register"/>"></span>
						</div>
						<div class="col-sm-3" id="combo1"></div>
						<div class="col-sm-3" id="combo1">
							<a href="<spring:url value="/personas/formPersonas"/>"
								class="btn btn-yvela-green"><i class="fa fa-undo yvela"
								aria-hidden="true"></i> <spring:message
									code="expenses.btn.cancel" /></a>
						</div>
					</div>
				</form:form>
				<br>
				<br>
				<hr align="left" noshade="noshade" size="10" width="100%" />

			</c:when>
		</c:choose>
		<!-- Final registro persona -->

		<!--------------- Inicio Modificar Personas----------------------------------- -->

		<c:choose>
			<c:when test="${operation eq 'mod' }">

				<center>
					<br>
					<p class="h3">
						<i class="fa fa-pencil-square-o" aria-hidden="true"></i>
						<spring:message code="people.modify.title" />
					</p>
					<br>
				</center>

				<form:form modelAttribute="personas" action="${urlModPerConf}"
					method="POST">
					<form:hidden path="idPersona" value="${personas.idPersona}" />

					<div class="form-group row">
						<label for="nombres" class="col-sm-2 col-form-label"><spring:message
								code="persona.nombres" /></label> <label
							class="col-sm-1 col-form-label text-right"> <i
							class="fa fa-info-circle" aria-hidden="true"></i></label>
						<div class="col-sm-6">
							<spring:message code="people.name.msg" var="nom" />
							<form:input type="text" path="nombres"
								class="form-control form-control-lg" placeholder="${nom}" />
						</div>
						<div class="col-sm-3" id="comd">
							<small class="form-text text-muted"> <spring:message
									code="people.name.msg" />
							</small> <small class="msgError"> <form:errors path="nombres" />
							</small>
						</div>
					</div>
					<div class="form-group row">
						<label for="paterno" class="col-sm-2 col-form-label"><spring:message
								code="persona.paterno" /></label> <label
							class="col-sm-1 col-form-label text-right"></label>
						<div class="col-sm-6">
							<spring:message code="people.paternal.msg" var="pat" />
							<form:input type="text" path="paterno"
								class="form-control form-control-lg" placeholder="${pat}" />
						</div>
						<div class="col-sm-3" id="comd">
							<small class="form-text text-muted"> <spring:message
									code="people.paternal.msg" />
							</small> <small class="msgError"> <form:errors path="paterno"
									cssClass="alert alert-danger" />
							</small>
						</div>
					</div>
					<div class="form-group row">
						<label for="materno" class="col-sm-2 col-form-label"><spring:message
								code="persona.materno" /></label> <label
							class="col-sm-1 col-form-label text-right"></label>
						<div class="col-sm-6">
							<spring:message code="people.maternal.msg" var="mat" />
							<form:input type="text" path="materno"
								class="form-control form-control-lg" placeholder="${mat}" />
						</div>
						<div class="col-sm-3" id="comd">
							<small class="form-text text-muted"> <spring:message
									code="people.maternal.msg" />
							</small> <small class="msgError"> <form:errors path="materno"
									cssClass="alert alert-danger" />
							</small>
						</div>
					</div>
					<div class="form-group row">
						<label for="cedula" class="col-sm-2 col-form-label"><spring:message
								code="persona.cedula" /></label> <label
							class="col-sm-1 col-form-label text-right"> <i
							class="fa fa-info-circle" aria-hidden="true"></i></label>
						<div class="col-sm-6">
							<spring:message code="people.identity.msg" var="ced" />
							<form:input type="text" path="ci"
								class="form-control form-control-lg" placeholder="${ced}" />
						</div>
						<div class="col-sm-3" id="comd">
							<small class="form-text text-muted"> <spring:message
									code="people.identity.msg" />
							</small> <small class="msgError"> <form:errors path="ci" />
							</small>

						</div>
					</div>

					<div class="form-group row">
						<label for="ciExpedido" class="col-sm-2 col-form-label"><spring:message
								code="people.issued" /></label> <label
							class="col-sm-1 col-form-label text-right"> <i
							class="fa fa-info-circle" aria-hidden="true"></i></label>
						<div class="col-sm-6">
							<form:select class="form-control form-control-lg"
								path="PrsCiExpedidos.idPrsCiExpedido" id="ciExpedido">
								<c:forEach var="cix" items="${requestScope.lExpedidos}">
									<form:option value="${cix.idPrsCiExpedido}">${cix.prsCiExpedido}</form:option>
								</c:forEach>
							</form:select>
						</div>
						<div class="col-sm-3" id="comd">
							<small class="form-text text-muted"> <a class="btn add"
								href="#" data-toggle="modal" data-target="#formCiExpedido"><i
									class="fa fa-plus-square-o" aria-hidden="true"></i> <spring:message
										code="people.add" /></a></small> </small> <small class="msgError"> <form:errors
									path="PrsCiExpedidos.idPrsCiExpedido"
									cssClass="alert alert-danger" />
							</small>
						</div>
					</div>

					<div class="form-group row">
						<label for="ciExpedido" class="col-sm-2 col-form-label"><spring:message
								code="people.academic.degree" /></label> <label
							class="col-sm-1 col-form-label text-right"> <i
							class="fa fa-info-circle" aria-hidden="true"></i></label>
						<div class="col-sm-6">
							<form:select class="form-control form-control-lg"
								path="PrsGradosAcademicos.idGradoAcademico" id="gradosA">
								<c:forEach var="ga" items="${requestScope.lGradosAcademicos}">
									<form:option value="${ga.idGradoAcademico}">${ga.descripcion}</form:option>
								</c:forEach>
							</form:select>
						</div>
						<div class="col-sm-3" id="comd">
							<small class="form-text text-muted"> <a class="btn add"
								href="#" data-toggle="modal" data-target="#formGradosAcademicos"><i
									class="fa fa-plus-square-o" aria-hidden="true"></i> <spring:message
										code="people.add" /></a></small> <small id="ci"
								class="form-text text-muted formFieldError"> <form:errors
									path="PrsGradosAcademicos.idGradoAcademico"
									cssClass="alert alert-danger" />
							</small>
						</div>
					</div>

					<div class="form-group row">
						<label for="paterno" class="col-sm-2 col-form-label"><spring:message
								code="login.email" /></label> <label
							class="col-sm-1 col-form-label text-right"> <i
							class="fa fa-info-circle" aria-hidden="true"></i></label>
						<div class="col-sm-6">
							<spring:message code="info.login.email" var="pat" />
							<form:input type="text" path="email"
								class="form-control form-control-lg" placeholder="${pat}" />
						</div>
						<div class="col-sm-3" id="comd">
							<small class="form-text text-muted"> <spring:message
									code="info.login.email" />
							</small> <small class="msgError"> <form:errors path="email" />
							</small>
						</div>
					</div>
					<div class="form-group row">
						<label for="ciExpedido" class="col-sm-2 col-form-label"><spring:message
								code="persona.fechaNac" /></label> <label
							class="col-sm-1 col-form-label text-right"></label>
						<div class="col-sm-6">
							<spring:message code="people.date.msg" var="fecN" />
							<form:input type="date" path="fecNacimiento"
								class="form-control form-control-lg" placeholder="${fecN}" />
						</div>
						<div class="col-sm-3" id="comd">
							<small class="form-text text-muted"> <spring:message
									code="people.date.msg" />
							</small> <small class="msgError"> <form:errors
									path="fecNacimiento" cssClass="alert alert-danger" />
							</small>
						</div>
					</div>
					<div class="form-group row">
						<label for="direccion" class="col-sm-2 col-form-label"><spring:message
								code="persona.direccion" /></label> <label
							class="col-sm-1 col-form-label text-right"></label>
						<div class="col-sm-6">
							<spring:message code="people.direction.msg" var="dir" />
							<form:input type="text" path="direccion"
								class="form-control form-control-lg" placeholder="${dir}" />
						</div>
						<div class="col-sm-3" id="comd">
							<small class="form-text text-muted"> <spring:message
									code="people.direction.msg" />
							</small> <small class="msgError"> <form:errors path="direccion"
									cssClass="alert alert-danger" />
							</small>
						</div>
					</div>
					<div class="form-group row">
						<label for="telefono" class="col-sm-2 col-form-label"><spring:message
								code="persona.telefono" /></label> <label
							class="col-sm-1 col-form-label text-right"> </label>
						<div class="col-sm-6">
							<spring:message code="people.telephone.msg" var="tel" />
							<form:input type="number" path="telefono"
								class="form-control form-control-lg" placeholder="${tel}" />
						</div>
						<div class="col-sm-3" id="comd">
							<small class="form-text text-muted"> <spring:message
									code="people.telephone.msg" />
							</small> <small class="msgError"> <form:errors path="telefono"
									cssClass="alert alert-danger" />
							</small>
						</div>
					</div>
					<div class="form-group row">
						<label for="tipoSanguineo" class="col-sm-2 col-form-label"><spring:message
								code="persona.tipoSangre" /></label> <label
							class="col-sm-1 col-form-label text-right"> </label>
						<div class="col-sm-6">
							<spring:message code="people.blood.msg" var="ts" />
							<form:input type="text" path="tipoSanguineo"
								class="form-control form-control-lg" placeholder="${ts}" />
						</div>
						<div class="col-sm-3" id="comd">
							<small class="form-text text-muted"> <spring:message
									code="people.blood.msg" />
							</small> <small class="msgError"> <form:errors
									path="tipoSanguineo" cssClass="alert alert-danger" />
							</small>
						</div>
					</div>
					<div class="form-group row">
						<label for="sexo" class="col-sm-2 col-form-label"><spring:message
								code="persona.sexo" /></label> <label
							class="col-sm-1 col-form-label text-right"> </label>
						<div class="col-sm-6">
							<form:select class="form-control form-control-lg"
								path="PrsTiposSexos.idPrsTipoSexo" id="tipoSexo">
								<c:forEach var="ts" items="${requestScope.lPrsTiposSexos}">
									<form:option value="${ts.idPrsTipoSexo}">${ts.prsTipoSexo}</form:option>
								</c:forEach>
							</form:select>
						</div>
						<div class="col-sm-3" id="comd">
							<small class="form-text text-muted"> <a class="btn add"
								href="#" data-toggle="modal" data-target="#formTipoSexo"><i
									class="fa fa-plus-square-o" aria-hidden="true"></i> <spring:message
										code="people.add" /></a></small> </small> <small id="emailHp"
								class="form-text text-muted formFieldError"> <form:errors
									path="PrsTiposSexos.idPrsTipoSexo"
									cssClass="alert alert-danger" />
							</small>
						</div>
					</div>
					<div class="form-group row">
						<label for="pais" class="col-sm-2 col-form-label"><spring:message
								code="persona.pais" /></label> <label
							class="col-sm-1 col-form-label text-right"> </label>
						<div class="col-sm-6">
							<form:select class="form-control form-control-lg"
								path="PrsPaises.idPais" id="pais">
								<c:forEach var="p" items="${requestScope.lPaises}">
									<form:option value="${p.idPais}">${p.pais}</form:option>
								</c:forEach>
							</form:select>
						</div>
						<div class="col-sm-3" id="comd">
							<small class="form-text text-muted"> <a class="btn add"
								href="#" data-toggle="modal" data-target="#formPais"><i
									class="fa fa-plus-square-o" aria-hidden="true"></i> <spring:message
										code="people.add" /></a></small> </small> <small id="emailHp"
								class="form-text text-muted formFieldError"> <form:errors
									path="PrsPaises.idPais" cssClass="alert alert-danger" />
							</small>
						</div>
					</div>

					<hr align="left" noshade="noshade" size="10" width="100%" />

					<div class="form-group row">
						<label for="msg" class="col-sm-2 col-form-label text-right"></label>
						<label for="msg" class="col-sm-10 col-form-label"><span
							class="parpadea text"> <i class="fa fa-info-circle"
								aria-hidden="true"></i></span> &nbsp;&nbsp;&nbsp;<spring:message
								code="people.warning" /> </label>
					</div>


					<div class="form-group row mt-12">
						<div class="col-sm-3" id="combo1"></div>
						<div class="col-sm-3" id="combo1">
							<span class="icon-input-btn"><i
								class="fa fa-pencil-square-o yvela" aria-hidden="true"></i> <input
								class="btn btn-success" type="submit"
								value="<spring:message  code="expenses.modify"/>"></span>
						</div>
						<div class="col-sm-3" id="combo1"></div>
						<div class="col-sm-3" id="combo1">
							<a href="<spring:url value="/personas/formPersonas"/>"
								class="btn btn-yvela-green"><i class="fa fa-undo"
								aria-hidden="true"></i> <spring:message
									code="expenses.btn.cancel" /></a>
						</div>
					</div>
				</form:form>
				<br>
				<br>
				<hr align="left" noshade="noshade" size="10" width="100%" />

			</c:when>
		</c:choose>
		<!------------------------------------ Final Modificar Personas------------------------------------------- -->

		<!--------------- Inicio Eliminar Personas----------------------------------- -->

		<c:choose>
			<c:when test="${operation eq 'elimP' }">
				<div id="" class="col-lg-12">
					<center>
						<br>
						<p class="h3">
							<i class="fa fa-user-times" aria-hidden="true"></i>
							<spring:message code="people.remove.title" />
						</p>
						<br>
					</center>
					<div class="form-group row">
						<div class="col-sm-8" id="combo1">
							<spring:url var="formUrl" value="listarPersonas" />
							<form:form modelAttribute="personas" action="${urlElimPerConf}"
								method="post">
								<form:input type="hidden" path="idEstado" />
								<form:input type="hidden" path="registro" />
								<form:input type="hidden" path="modificacion" />
								<form:input type="hidden" path="idPersona" />

								<div class="form-group row">
									<spring:message code="persona.nombres" var="nom" />
									<label for="nombres" class="col-sm-4 col-form-label"><spring:message
											code="persona.nombres" /></label>
									<div class="col-sm-8">
										<form:input type="text" path="nombres"
											class="form-control form-control-lg" placeholder="${nom}"
											readonly="true" />
									</div>
								</div>
								<div class="form-group row">
									<spring:message code="persona.paterno" var="pat" />
									<label for="paterno" class="col-sm-4 col-form-label"><spring:message
											code="persona.paterno" /></label>
									<div class="col-sm-8">
										<form:input type="text" path="paterno"
											class="form-control form-control-lg" placeholder="${pat}"
											readonly="true" />
									</div>
								</div>
								<div class="form-group row">
									<spring:message code="persona.materno" var="mat" />
									<label for="materno" class="col-sm-4 col-form-label"><spring:message
											code="persona.materno" /></label>
									<div class="col-sm-8">
										<form:input type="text" path="materno"
											class="form-control form-control-lg" placeholder="${mat}"
											readonly="true" />
									</div>
								</div>
								<div class="form-group row">
									<spring:message code="persona.cedula" var="ced" />
									<label for="cedula" class="col-sm-4 col-form-label"><spring:message
											code="persona.cedula" /></label>
									<div class="col-sm-8">
										<form:input type="text" path="ci"
											class="form-control form-control-lg" placeholder="${ced}"
											readonly="true" />
									</div>
								</div>

								<div class="form-group row">
									<label for="ciExpedido" class="col-sm-4 col-form-label"><spring:message
											code="people.issued" /></label>
									<div class="col-sm-8">
										<form:select class="form-control form-control-lg"
											readonly="true" path="PrsCiExpedidos.idPrsCiExpedido"
											id="tipoSexo">
											<c:forEach var="cix" items="${requestScope.lExpedidos}">
												<form:option value="${cix.idPrsCiExpedido}">${cix.prsCiExpedido} </form:option>
											</c:forEach>
										</form:select>
									</div>
								</div>
								<div class="form-group row">
									<spring:message code="login.email" var="email" />
									<label for="paterno" class="col-sm-4 col-form-label"><spring:message
											code="login.email" /></label>
									<div class="col-sm-8">
										<form:input type="text" path="email"
											class="form-control form-control-lg" placeholder="${email}"
											readonly="true" />
										<form:errors path="email" cssClass="alert alert-danger" />
									</div>

								</div>
								<div class="form-group row">
									<spring:message code="persona.fechaNac" var="fecN" />
									<label for="fechaNac" class="col-sm-4 col-form-label"><spring:message
											code="persona.fechaNac" /></label>
									<div class="col-sm-8">
										<form:input type="date" path="fecNacimiento"
											class="form-control form-control-lg" placeholder="${fecN}"
											readonly="true" />
									</div>
								</div>
								<div class="form-group row">
									<spring:message code="persona.direccion" var="dir" />
									<label for="direccion" class="col-sm-4 col-form-label"><spring:message
											code="persona.direccion" /></label>
									<div class="col-sm-8">
										<form:input type="text" path="direccion"
											class="form-control form-control-lg" placeholder="${dir}"
											readonly="true" />
									</div>
								</div>
								<div class="form-group row">
									<spring:message code="persona.telefono" var="tel" />
									<label for="telefono" class="col-sm-4 col-form-label"><spring:message
											code="persona.telefono" /></label>
									<div class="col-sm-8">
										<form:input type="number" path="telefono"
											class="form-control form-control-lg" placeholder="${tel}"
											readonly="true" />
									</div>
								</div>
								<div class="form-group row">
									<spring:message code="persona.tipoSangre" var="ts" />
									<label for="tipoSanguineo" class="col-sm-4 col-form-label"><spring:message
											code="persona.tipoSangre" /></label>
									<div class="col-sm-8">
										<form:input type="text" path="tipoSanguineo"
											class="form-control form-control-lg" placeholder="${ts}"
											readonly="true" />
									</div>
								</div>
								<div class="form-group row">
									<label for="sexo" class="col-sm-4 col-form-label"><spring:message
											code="persona.sexo" /></label>
									<div class="col-sm-8">
										<form:select class="form-control form-control-lg"
											readonly="true" path="PrsTiposSexos.idPrsTipoSexo"
											id="tipoSexo">
											<c:forEach var="ts" items="${requestScope.lPrsTiposSexos}">
												<form:option value="${ts.idPrsTipoSexo}">${ts.prsTipoSexo} </form:option>
											</c:forEach>
										</form:select>
									</div>
								</div>
								<div class="form-group row">
									<label for="pais" class="col-sm-4 col-form-label"><spring:message
											code="persona.pais" /></label>
									<div class="col-sm-8">
										<form:select class="form-control form-control-lg"
											readonly="true" path="PrsPaises.idPais" id="pais">
											<c:forEach var="p" items="${requestScope.lPaises}">
												<form:option value="${p.idPais}">${p.pais}</form:option>
											</c:forEach>
										</form:select>
									</div>
								</div>
								<hr align="left" noshade="noshade" size="10" width="100%" />
								<div class="form-group row mt-12">
									<div class="col-sm-3" id="combo1"></div>
									<div class="col-sm-3" id="combo1">
										<span class="icon-input-btn"><i
											class="fa fa-times yvela" aria-hidden="true"></i> <c:if
												test="${banderita == 1}">
												<input type="submit" disabled="true" class="btn btn-danger"
													value="<spring:message  code="expenses.btn.confirm"/>"></span>
										</c:if>
										<c:if test="${banderita == 0}">
											<input type="submit" class="btn btn-danger"
												value="<spring:message  code="expenses.btn.confirm"/>">
										</c:if>
									</div>
									<div class="col-sm-3" id="combo1"></div>
									<div class="col-sm-3" id="combo1">
										<a href="<spring:url value="/personas/formPersonas"/>"
											class="btn btn-yvela-green"><i class="fa fa-undo"
											aria-hidden="true"></i> <spring:message
												code="expenses.btn.cancel" /></a>
									</div>
								</div>
							</form:form>
						</div>
						<div class="col-sm-4" id="combo1">
							<fieldset class="col-md-12">
								<legend>
									<i class="fa fa-exclamation-triangle" aria-hidden="true"></i>
									<spring:message code="expenses.remove.info.title" />
								</legend>
								<br>
								<p>
									<spring:message code="people.remove.msg" />
								</p>
								<br>
								<c:choose>
									<c:when test="${mensage eq 'err' }">
										<span class="parpadea text"><p class="h1">
												<i class="fa fa-exclamation-triangle" aria-hidden="true"></i>
											</p></span>
										<spring:message code="people.warning.remove" />
									</c:when>
								</c:choose>
								<br>
							</fieldset>
						</div>
					</div>
				</div>
				<br>
				<br>
				<hr align="left" noshade="noshade" size="10" width="100%" />
			</c:when>
		</c:choose>
		<!------------------------------------ Final Eliminar Personas------------------------------------------- -->
		<!--Tabla lista de Todas las Personas Registradas -->
		<c:choose>

			

			<c:when test="${busqueda eq 'find' }">

				<center>
					<br>
					<p class="h3">
						<i class="fa fa-list-alt" aria-hidden="true"></i>
						<spring:message code="people.form.detail" />
					</p>
					<br>
				</center>
				
				<table id="tPersonas"  class="table" style="width:100%">
                    <thead>
                        <tr>
                            <th><spring:message  code="persona.cedula"/></th>
                            <th><spring:message  code="persona.nombres"/></th>
                            <th><spring:message  code="persona.apellidos"/></th>
                            <th><spring:message  code="persona.pais"/></th>
                            <th><spring:message  code="persona.telefono"/></th>
                            <th><spring:message  code="expenses.modify"/> </th>
                            <th><spring:message  code="expenses.remove"/></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:set var="cont" value="0" />
                        <c:forEach items="${lPersonas}" var="li">
                            <c:set var="cont" value="${cont+1}" />
                            <tr>
                                <td>${li.ci}</td>
                                <td>${li.nombres}</td>
                                <td>${li.paterno} ${li.materno} </td>
                                <td>${li.prsPaises.pais}</td>
                                <td>${li.telefono}</td>
                                <td>
                                    <form  method="post" action="${urlModPer}">
                                        <input type="hidden" name="idPersona" value="${li.idPersona}">
                                        <span class="icon-input-btn"> <i class="fa fa-pencil-square-o yvela" aria-hidden="true"> </i> <input class="btn btn-success" type="submit"  value="<spring:message  code="expenses.modify"/>" > </span>
                                    </form>
                                </td>
                                <td>
                                    <form name="Delet" action="${urlIniE}" method="post">
                                        <input type="hidden" name="idPersona" value="${li.idPersona}">
                                        <span class="icon-input-btn"> <i class="fa fa-times yvela" aria-hidden="true"> </i> <input class="btn btn-danger" type="submit"  value="<spring:message  code="expenses.remove"/>"> </span>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                    <tfoot>
                        <tr>
                            <th><spring:message  code="persona.cedula"/></th>
                            <th><spring:message  code="persona.nombres"/></th>
                            <th><spring:message  code="persona.apellidos"/></th>
                            <th><spring:message  code="persona.pais"/></th>
                            <th><spring:message  code="persona.telefono"/></th>
                            <th><spring:message  code="expenses.modify"/> </th>
                            <th><spring:message  code="expenses.remove"/></th>
                        </tr>
                    </tfoot>
                </table>
				
				<br>
				<hr align="left" noshade="noshade" size="10" width="100%" />
				<h5>Cargar CSV</h5>
                                        
				<form action="../../../../Castanhero/personas/csv" method="post" enctype="multipart/form-data">
	
					<div class="form-row">
						<div class="col-md-12 mb-3">
							<br> <input type="file" class="form-control" name="archivo" required accept=".csv">
						</div>
					</div>
	
					<button class="btn btn-primary mt-2" type="submit">Enviar</button>
				</form>
				
				<br>
				<hr align="left" noshade="noshade" size="10" width="100%" />
			</c:when>
		</c:choose>
	</section>
</div>
<div id="formPais" class="modal fade" tabindex="-1" role="dialog"
	aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<form name="paisForm" action="guardarPais" role="form" id="paisForm"
				method="post">
				<div class="modal-header">
					<p class="h3">
						<i class="fa fa-flag" aria-hidden="true"></i>
						<spring:message code="people.new.country" />
					</p>
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
				</div>
				<div class="modal-body">
					<div class="form-group row">
						<spring:message code="persona.pais" var="pais" />
						<label for="pais" class="col-sm-3 col-form-label"><spring:message
								code="persona.pais" /></label> <label for="pais"
							class="col-sm-1 col-form-label"><i
							class="fa fa-info-circle" aria-hidden="true"></i></label>
						<div class="col-sm-8">
							<input type="text" name="pais"
								class="form-control form-control-lg" placeholder="${pais}" />
						</div>
					</div>
					<div class="form-group row">
						<spring:message code="people.nationality" var="nat" />
						<label for="nacionalidad" class="col-sm-3 col-form-label"><spring:message
								code="people.nationality" /></label> <label for="nacionalidad"
							class="col-sm-1 col-form-label"><i
							class="fa fa-info-circle" aria-hidden="true"></i></label>
						<div class="col-sm-8">
							<input type="text" name="nacionalidad"
								class="form-control form-control-lg" placeholder="${nat}" />
						</div>

					</div>
					<div class="modal-footer">
						<button class="btn btn-outline-secondary btn-lg"
							data-dismiss="modal" aria-hidden="true">
							<i class="fa fa-undo" aria-hidden="true"></i>
							<spring:message code="people.cancel" />
						</button>
						<button type="submit" class="btn btn-success btn-lg float-right"
							id="btnPais" data-dismiss="modal" aria-hidden="true">
							<i class="fa fa-plus-square-o" aria-hidden="true"></i>
							<spring:message code="people.add" />
						</button>
					</div>
			</form>
		</div>
	</div>
</div>
</div>
<div id="formTipoSexo" class="modal fade" tabindex="-1" role="dialog"
	aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<form name="tipoSexoForm" action="guardarTipoSexo" role="form"
				id="paisForm" method="post">
				<div class="modal-header">
					<p class="h3">
						<i class="fa fa-user-secret" aria-hidden="true"></i>
						<spring:message code="people.new.sex" />
					</p>
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
				</div>
				<div class="modal-body">
					<div class="form-group row">
						<spring:message code="persona.sexo" var="sexo" />
						<label for="pais" class="col-sm-3 col-form-label"><spring:message
								code="persona.sexo" /></label> <label for="tipoSexo"
							class="col-sm-1 col-form-label"><i
							class="fa fa-info-circle" aria-hidden="true"></i></label>
						<div class="col-sm-8">
							<input type="text" name="prsTipoSexo"
								class="form-control form-control-lg" placeholder="${sexo}" />
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button class="btn btn-outline-secondary btn-lg"
						data-dismiss="modal" aria-hidden="true">
						<i class="fa fa-undo" aria-hidden="true"></i>
						<spring:message code="people.cancel" />
					</button>
					<button type="submit" class="btn btn-success btn-lg float-right"
						id="btnSexo" data-dismiss="modal" aria-hidden="true">
						<i class="fa fa-plus-square-o" aria-hidden="true"></i>
						<spring:message code="people.add" />
					</button>
				</div>
			</form>
		</div>
	</div>
</div>
<div id="formCiExpedido" class="modal fade" tabindex="-1" role="dialog"
	aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<form name="prsCiExpedidos" action="guardarCiExpedido" role="form"
				id="paisForm" method="post">
				<div class="modal-header">
					<p class="h3">
						<i class="fa fa-id-card" aria-hidden="true"></i>
						<spring:message code="people.new.issued" />
					</p>
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
				</div>
				<div class="modal-body">
					<div class="form-group row">
						<spring:message code="people.issued" var="issued" />
						<label for="expedido" class="col-sm-3 col-form-label"><spring:message
								code="people.issued" /></label> <label for="ciExpedido"
							class="col-sm-1 col-form-label"><i
							class="fa fa-info-circle" aria-hidden="true"></i></label>
						<div class="col-sm-8">
							<input type="text" name="prsCiExpedido"
								class="form-control form-control-lg" placeholder="${issued}" />
						</div>
					</div>
					<div class="form-group row">
						<spring:message code="people.issued.initials" var="initials" />
						<label for="espedidoSigla" class="col-sm-3 col-form-label"><spring:message
								code="people.issued.initials" /></label> <label for="ciExpedidoSigla"
							class="col-sm-1 col-form-label"><i
							class="fa fa-info-circle" aria-hidden="true"></i></label>
						<div class="col-sm-8">
							<input type="text" name="prsCiExpedidoSigla"
								class="form-control form-control-lg" placeholder="${initials}" />
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button class="btn btn-outline-secondary btn-lg"
						data-dismiss="modal" aria-hidden="true">
						<i class="fa fa-undo" aria-hidden="true"></i>
						<spring:message code="people.cancel" />
					</button>
					<button type="submit" class="btn btn-success btn-lg float-right"
						id="btnExpedido" data-dismiss="modal" aria-hidden="true">
						<i class="fa fa-plus-square-o" aria-hidden="true"></i>
						<spring:message code="people.add" />
					</button>
				</div>
			</form>
		</div>
	</div>
</div>

<div id="formGradosAcademicos" class="modal fade" tabindex="-1"
	role="dialog" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<form name="prsGradosAcademicos" action="guardarGradosAcademicos"
				role="form" id="gradosAcademicosForm" method="post">
				<div class="modal-header">
					<p class="h3">
						<i class="fa fa-graduation-cap" aria-hidden="true"></i>
						<spring:message code="people.academic.degree.new" />
					</p>
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
				</div>
				<div class="modal-body">
					<div class="form-group row">
						<spring:message code="people.academic.degree" var="issued" />
						<label for="expedido" class="col-sm-3 col-form-label"><spring:message
								code="people.academic.degree" /></label> <label for="ciExpedido"
							class="col-sm-1 col-form-label"><i
							class="fa fa-info-circle" aria-hidden="true"></i></label>
						<div class="col-sm-8">
							<input type="text" name="gradoAcademico"
								class="form-control form-control-lg" placeholder="${issued}" />
						</div>
					</div>
					<div class="form-group row">
						<spring:message code="people.academic.degree.variable"
							var="initials" />
						<label for="espedidoSigla" class="col-sm-3 col-form-label"><spring:message
								code="ingreso.descripcion" /></label> <label for="ciExpedidoSigla"
							class="col-sm-1 col-form-label"><i
							class="fa fa-info-circle" aria-hidden="true"></i></label>
						<div class="col-sm-8">
							<input type="text" name="descripcion"
								class="form-control form-control-lg" placeholder="${initials}" />
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button class="btn btn-outline-secondary btn-lg"
						data-dismiss="modal" aria-hidden="true">
						<i class="fa fa-undo" aria-hidden="true"></i>
						<spring:message code="people.cancel" />
					</button>
					<button type="submit" class="btn btn-success btn-lg float-right"
						id="btnGradoAcademico" data-dismiss="modal" aria-hidden="true">
						<i class="fa fa-plus-square-o" aria-hidden="true"></i>
						<spring:message code="people.add" />
					</button>
				</div>
			</form>
		</div>
	</div>
</div>

