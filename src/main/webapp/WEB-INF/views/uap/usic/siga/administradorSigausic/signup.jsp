<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<style type="text/css">
  span.error{
    color: red;
    margin-left: 5px;
  }
  
 .icon-input-btn{
        display: inline-block;
        position: relative;
  }

 .icon-input-btn input[type="submit"]{
        padding-left: 2em;
  }

 .icon-input-btn .yvela{
     display: inline-block;
     position: absolute;
     font-size: 20px;
     left: 0.65em;
     top: 30%;
    }
 </style>
<section class="higher">
    <div class="panel-body">
       <p class="h1"><i class="fa fa-user-circle-o" aria-hidden="true"></i>  <spring:message  code="login.sign_up"/></p>
       <hr align="left" noshade="noshade" size="10" width="100%" />
        <c:if test="${not empty error}">
            <div class="alert alert-danger">
                <spring:message  code="AbstractUserDetailsAuthenticationProvider.badCredentials"/>
                <br/>
            </div>
        </c:if>
         <spring:url var="formUrl" value="signup" />
        <form:form modelAttribute="usuarios" action="${url}" method="post">
            <div class="form-group row">
                <label for="nombres" class="col-sm-2 col-form-label"><spring:message  code="login.people"/></label>
                <label class="col-sm-1 col-form-label text-right" ><i class="fa fa-info-circle" aria-hidden="true"></i></label>
                <div class="col-sm-6">
                    <form:select class="form-control form-control-lg" path="Personas.idPersona" >
                      <c:forEach var="p" items="${requestScope.lPersonas}">
                         <form:option value="${p.idPersona}">${p.nombres} ${p.paterno} ${p.materno} </form:option>
                      </c:forEach>
                    </form:select>
                </div>
                <div class="col-sm-3" id="comd">
                        <small class="form-text text-muted">
                            <spring:message  code="info.login.people"/>
                        </small>
                        <small id="emailHp" class="form-text text-muted formFieldError">
                            <form:errors path="Personas.idPersona" cssClass="alert alert-danger"/>
                        </small>
                </div>
            </div>
            <div class="form-group row">
                <label for="nombres" class="col-sm-2 col-form-label"><spring:message  code="login.user"/></label>
                <label class="col-sm-1 col-form-label text-right" ><i class="fa fa-info-circle" aria-hidden="true"></i></label>
                <div class="col-sm-6">
                    <input type="text" class="form-control form-control-lg"
                         name="usuario" aria-describedby="emailHelp" placeholder="<spring:message  code="login.user.info"/>" />
                </div>
                <div class="col-sm-3" id="comd">
                        <small class="form-text text-muted">
                            <spring:message  code="login.user.info"/>
                        </small>
                        <small id="emailHp" class="form-text text-muted formFieldError">
                            <form:errors path="usuario" cssClass="alert alert-danger"/>
                        </small>
                </div>
            </div>
         <div class="form-group row">
             <label for="nombres" class="col-sm-2 col-form-label"><spring:message  code="login.password"/></label>
                <label class="col-sm-1 col-form-label text-right" ><i class="fa fa-info-circle" aria-hidden="true"></i></label>
                <div class="col-sm-6">
                    <input type="password" class="form-control form-control-lg"
                         name="password" aria-describedby="emailHelp" placeholder="<spring:message  code="login.password.info"/>" />
                </div>
                <div class="col-sm-3" id="comd">
                        <small class="form-text text-muted">
                            <spring:message  code="login.password.info"/>
                        </small>
                        <small id="emailHp" class="form-text text-muted formFieldError">
                            <form:errors path="password" cssClass="alert alert-danger"/>
                        </small>
                </div>
            </div>
              
          <hr align="left" noshade="noshade" size="10" width="100%" />
          <div class="form-group row mt-3">
                <div class="col-10 offset-2">
                    <span class="icon-input-btn"><i class="fa fa-floppy-o yvela" aria-hidden="true"></i> <input type="submit" class="btn btn-success btn-lg" value="<spring:message  code="login.sign_up"/>"/></span>
                 </div>
            </div>   
         </form:form>
    </div>
</section>