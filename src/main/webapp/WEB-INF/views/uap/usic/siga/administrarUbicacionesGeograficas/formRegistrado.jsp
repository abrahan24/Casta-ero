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
        <h1>Registrado</h1>
            
</section>