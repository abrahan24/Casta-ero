<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div>
    <div class="panel-body">
        <p class="h1"><spring:message  code="cargo.registrarCargo"/></p>
        <hr align="left" noshade="noshade" size="10" width="100%" />
        <c:if test="${not empty error}">
            <div class="alert alert-danger">
                <spring:message  code="AbstractUserDetailsAuthenticationProvider.badCredentials"/>
                <br/>
            </div>
        </c:if>
        <h1>Registrado</h1>
            
       
    </div>
</div>