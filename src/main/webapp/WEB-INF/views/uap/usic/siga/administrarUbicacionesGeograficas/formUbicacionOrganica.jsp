<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<section class="higher">

        <div class="row">
            <div class="form-group row">
                <div class="col-sm-2" id="reporte">
                    <p class="h1"></p>
                </div>
                <div class="col-sm-2" id="reporte">

                </div>
            </div>

            <div class="col-md-10 mb-3">
                <section class="content-header">
                    <p class="h1">  <spring:message code="institucion.ubicacionR"/> </p>
                </section>
            </div>
        </div>
        <hr align="left" noshade="noshade" size="10" width="100%" />
        <c:if test="${not empty error}">
            <div class="alert alert-danger">
                <spring:message  code="AbstractUserDetailsAuthenticationProvider.badCredentials"/>
                <br/>
            </div>
        </c:if>
        <spring:url var="formUrl" value="listarUbicaciones" />
        <form:form modelAttribute="insedes" action="${regUG}" method="post">
         <div class="form-group row">
                <label for="instituciones" class="col-sm-2 col-form-label"><spring:message  code="institucion.institucion"/></label>
                <div class="col-sm-6">
                    <form:select class="form-control form-control-lg" path="Instituciones.idInstitucion" >
                        <c:forEach var="p" items="${requestScope.lInstituciones}">
                            <form:option value="${p.idInstitucion}">${p.institucion}</form:option>
                            </c:forEach>
                    </form:select>
                </div>
                 <div class="col-sm-4" id="comd">
                        <small class="form-text text-muted">
                            <spring:message  code="institucion.institucionS"/>
                        </small>
                        <small id="emailHp" class="form-text text-muted formFieldError">
                            <form:errors path="" cssClass="alert alert-danger"/>
                        </small>
                </div>
            </div>
                
            <div class="form-group row">
              <spring:message  code="institucion.codigo" var="cod"/>
               <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="institucion.codigo"/></label>
                <div class="col-sm-6">
                    <form:input type="text" path="codigo" class="form-control form-control-lg" placeholder="${cod}" />
                </div>
                 <div class="col-sm-4" id="comd">
                        <small class="form-text text-muted">
                            <spring:message  code="institucion.codigo.msg"/>
                        </small>
                        <small id="emailHp" class="form-text text-muted formFieldError">
                           <form:errors path="codigo" cssClass="alert alert-danger"/>
                        </small>
                </div>
            </div>   
            <div class="form-group row">
                 <spring:message  code="institucion.ubicacionG" var="uge"/>
                <label for="sede" class="col-sm-2 col-form-label"><spring:message  code="institucion.ubicacionG"/></label>
                <div class="col-sm-6">
                    <form:input type="text"  path="sede" class="form-control form-control-lg" placeholder="${uge}" />
                </div>
                <div class="col-sm-4" id="comd">
                        <small class="form-text text-muted">
                          <spring:message  code="institucion.ubicacionG.msg"/>
                        </small>
                        <small id="emailHp" class="form-text text-muted formFieldError">
                           <form:errors path="sede" cssClass="alert alert-danger"/>
                        </small>
                </div>
            </div>
            <hr align="left" noshade="noshade" size="10" width="100%" />
            <div class="form-group row mt-3">
                <div class="col-10 offset-2">
                    <input class="btn" type="submit" value="<spring:message  code="cargo.registrarC"/>">
                </div>
            </div>
        </form:form>
</section> 