<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<head>
   
 <script
      src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<link rel="stylesheet"
      href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

<script src="<spring:url value="/resource/js/sedes.js" />" type="text/javascript"></script>
</head>

<div class="container">
<p class="h1"><spring:message  code="units.title"/></p>
       <hr align="left" noshade="noshade" size="10" width="100%" />
        <c:if test="${not empty error}">
            <div class="alert alert-danger">
                <spring:message  code="AbstractUserDetailsAuthenticationProvider.badCredentials"/>
                <br/>
            </div>
        </c:if>
         <spring:url var="formUrl" value="signupsss" />
        <form:form  modelAttribute="insUnidadesFuncionales" action="${url}" method="post" >
             <div class="form-group row">
                <label for="instituciones" class="col-sm-2 col-form-label"><spring:message  code="institution.institution"/></label>
                <div class="col-sm-6" id="combo1"> 
                   <select class="form-control form-control-lg" name="idInstitucion" id="idInstitucion">
                     <c:forEach var="l" items="${requestScope.lInstituciones}">
                         <option value="${l.idInstitucion}">${l.institucion}</option>
                     </c:forEach>
                   </select>
                 </div>
                <div class="col-sm-4" id="combo1"> 
                  <small id="emailHp" class="form-text text-muted">
                       <spring:message  code="institution.institution.msg"/> -- ${saludo}
                  </small>
                </div>
              </div>
      
      <div class="form-group row">
                <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="institution.headquarters"/></label>
                <div class="col-sm-6" id="combop"> 
                     <form:select class="form-control form-control-lg" path="InsSedes.idSede" id="sedes" >
                           <form:options items="${lSedes}" itemValue="idSede" itemLabel="sede"></form:options>
                    </form:select>
                 </div>
                <div class="col-sm-4" id="combo1"> 
                  <small id="emailHp" class="form-text text-muted">
                       <spring:message  code="institution.headquarters.msg"/>
                  </small>
                   <small id="emailHp" class="form-text text-muted">
                    <form:errors path="InsSedes.idSede" />
                   </small>
                </div>
              </div>
 
              <div class="form-group row">
                <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="units.unit"/></label>
                <div class="col-sm-6" id="combo1"> 
                    <spring:message  code="units.unit.msg" var="unit"/>
                    <form:input type="text" path="unidadFuncional" class="form-control form-control-lg" placeholder="${unit}"/> 
                </div>
                <div class="col-sm-4" id="comd"> 
                  <small id="emailHp" class="form-text text-muted">
                     <spring:message  code="units.unit.msg"/>
                  </small>
                   <small id="emailHp" class="form-text text-muted formFieldError">
                    <form:errors path="unidadFuncional" />
                   </small>
                </div>
              </div>
             <hr align="left" noshade="noshade" size="10" width="100%" />
              <div class="form-group row mt-3">
                <div class="col-10 offset-2">
                  <input class="btn" type="submit" value="<spring:message  code="institution.register"/>">
                </div>
              </div>
        </form:form>
</div>
 
