<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<section class="higher">
     <div class="panel-body">
        <div class="form-group row">
                 <div class="col-sm-3" id="combo2"> 
                   
                 </div>
                <div class="col-sm-6" id="combo1"> 
                     <form action="doLogin" method="post" class="login100-form">
                         <div class="login100-form-avatar">
                            <img src="<c:url value="/resource/images/user.png" />"/>
                         </div>
                         <c:if test="${not empty error}">
                                <div class="alert alert-danger">
                                    <spring:message code="AbstractUserDetailsAuthenticationProvider.badCredentials"/>
                                 </div>
                            </c:if>
                         <span class="login100-form-title p-t-20 p-b-45"><spring:message code="login.entry"/></span>
 		         <div class="wrap-input100 validate-input m-b-10" data-validate = "Username is required">
		            <input class="input100" type="text" name="usuario" placeholder="<spring:message code="login.user"/>" required>
		            <span class="focus-input100"></span>
                            <span class="symbol-input100"><i class="fa fa-user"></i></span>
                         </div>
                          <div class="wrap-input100 validate-input m-b-10" data-validate = "Password is required">
                              <input class="input100" type="password" name="password" placeholder="<spring:message code="login.password"/>" required>
                              <span class="focus-input100"></span>
                              <span class="symbol-input100"><i class="fa fa-lock"></i></span>
                          </div>
                          <div class="container-login100-form-btn p-t-10">
                               <button class="login100-form-btn" type="submit" autofocus value="<spring:message code="login.sign_in"/>"></button>
                          </div>
                      </form>
                  </div>
                  <div class="col-sm-3" id="combo1"> 
                   
                </div>
            </div>
       </div>   
</section>
 