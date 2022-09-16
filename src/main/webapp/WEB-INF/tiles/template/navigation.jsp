<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
           
 <head>
    <script>
        $('#userModald').modal({
            backdrop: 'static'
        })
        $(document).ready(function ()
        {
            $("#userModald").modal("show");
        });
        /*
         $(function() {
         $( "#dialog" ).dialog();
         });
         */
    </script>
</head>
<div class="nav-menus">
    <div class="container-fluid fixeds menu-sup">
        <a class="menu-top flag-icon-us mr-1" href="?lang=en" ><spring:message code="menu.language.english"/></a>
        <a class="menu-top flag-icon-fr ml-1" href="?lang=fr" ><spring:message code="menu.language.french"/></a>
        <a class="menu-top flag-icon-fr ml-1" href="?lang=es" ><spring:message code="menu.language.spanish"/></a>
        <a class="menu-top flag-icon-fr ml-1" href="?lang=pt" ><spring:message code="menu.language.portuguese"/></a>
        <nav class="navbar navbar-expand-lg navbar-light align">
            <a class="menu-social" href="https://twitter.com/login?lang=es" target="_blank"><span class="ti-twitter-alt"></span></a>
            <a class="menu-social" href="https://www.facebook.com/" target="_blank"><span class="ti-facebook"></span></a>
            <a class="menu-social" href="https://www.instagram.com/?hl=es-la" target="_blank"><span class="ti-instagram"></span></a>
        </nav>
    </div>
</div> 
<div contaier class="nav-menu bg-dark">
    <div class="bg transition">
        <div class="container-fluid fixed">
            <nav class="navbar navbar-expand-lg navbar-light">
                
               <a class="navbar-brand" href="../../Castanhero/?lang=es"><img src="<c:url value="/resource/images/logs/logo_barra.png" />"/><font face="Lucida Calligraphy"> <spring:message  code="System.menu.title"/> </font> </a>

                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="icon-menu"></span>
                </button>
                <div class="collapse navbar-collapse align" id="navbarNavDropdown">
                    <ul class="navbar-nav">
                        <c:forEach var="lte" items="${lTiposEnlaces}">
                            <li class="nav-item dropdown freddy"><a class="nav-link" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" ><span class="${lte.mnuImagen}"></span> <spring:message code="${lte.mnuTipoEnlace}"/> <span class="icon-arrow-down"></span> </a>
                                <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                                    <c:forEach var="elc" items="${lEnlaces}">
                                        <c:if  test="${elc.mnuTiposEnlaces.idMnuTipoEnlace == lte.idMnuTipoEnlace}">  
                                            <a class="dropdown-item" href="<spring:url value="${elc.mnuRuta}"/> "><span class="${elc.mnuImagen}"></span>  <spring:message code="${elc.mnuEnlace}"/></a>
                                        </c:if>
                                    </c:forEach>
                                </div>
                            </c:forEach>
                        </li>
                    </ul>
                </div>
                <security:authorize access="isAnonymous()">   
                    <div class="navbar-nav">
                        <a class="nav-item nav-link" id="login" href="<spring:url value="/login"/>"><spring:message code="menu.login"/></a>
                    </div>
                </security:authorize>
                <security:authorize access="isAuthenticated()">
                    <div class="navbar-nav">
                        <a class="nav-item nav-link" href="#" data-toggle="modal" data-target="#formProfile"><i class="fa fa-user-circle-o" aria-hidden="true"></i>  ${sNombres} ${sPaterno}</a>
                    </div>
                </security:authorize>
            </nav>
        </div>
    </div>
</div>
<c:choose>
    <c:when test="${userModal eq 'verd' }">             
        <div class="modal left fade" id="userModald" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form name="functionForm" action="<spring:url value="/listarMenues"/>" role="form" id="form" method="post">
                        <div class="modal-header">
                            <p class="h4 modal-title" id="myModalLabel"><spring:message code="sistem.motal.title"/> </p>
                        </div>
                        <div class="modal-body">
                            <div class="form-group row">
                                <div class="col-md-3">
                                    <div class="login100-form-avatar">
                                        <img src="<c:url value="/resource/images/user.png" />"/>
                                    </div>
                                </div>
                                <div class="col-md-9">
                                    <div class="row">
                                        <label class="col-sm-3 col-form-label"><spring:message  code="reporte.usuario"/></label>
                                        <label class="col-sm-9 col-form-label"> ${sNombres} ${sPaterno} ${sMaterno}  </label>
                                    </div>
                                    <div class="row">
                                        <label class="col-sm-3 col-form-label"><spring:message  code="login.rol"/></label>
                                        <security:authorize access="hasRole('ADMIN')">
                                            <label class="col-sm-9 col-form-label"><spring:message  code="login.admin"/></lable>
                                            </security:authorize>
                                            <security:authorize access="hasRole('USER')">
                                                <label class="col-sm-9 col-form-label"> <spring:message  code="login.user"/></lable>
                                                </security:authorize>
                                                </div>
                                                <div class="form-group row">
                                                    <label class="col-sm-3 col-form-label"><spring:message  code="menues.type.functions"/></label>
                                                    <div class="col-md-9">
                                                        <select class="form-control form-control" name="idMnuTipoFuncion" onchange="this.form.submit()">
                                                            <option value="">Seleccion Funcion </option>
                                                            <c:forEach var="l" items="${lTiposFunciones}">
                                                                <option value="${l.idMnuTipoFuncion}">${l.tipoFuncion}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                                </div>
                                                </div>
                                                </div>
                                                <div class="modal-footer">              
                                                </div>
                                                </form>
                                                </div>
                                                </div>
                                                </div>
                                            </c:when>
                                        </c:choose> 
 <div id="formProfile" class="modal left fade" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">
   <div class="modal-dialog">
        <div class="modal-content">
              <form name="functionForm" action="<spring:url value="/listarMenues"/>" role="form" id="form" method="post">
                <div class="modal-header">
                   <p class="h3"><i class="fa fa-user-secret" aria-hidden="true"></i>  <spring:message  code="sistem.motal.title"/></p>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                </div>
        <div class="modal-body">
   <div class="form-group row">
   <div class="col-md-3">
         <div class="login100-form-avatar">
                <img src="<c:url value="/resource/images/user.png" />"/>
        </div>
    </div>
        <div class="col-md-9">
            <div class="row">
                    <label class="col-sm-3 col-form-label"><spring:message  code="reporte.usuario"/></label>
                    <label class="col-sm-9 col-form-label"> ${sNombres} ${sPaterno} ${sMaterno}  </label>
            </div>
            <div class="row">
                <label class="col-sm-3 col-form-label"><spring:message  code="login.rol"/></label>
                <security:authorize access="hasRole('ADMIN')">
                    <label class="col-sm-9 col-form-label"><spring:message  code="login.admin"/></label>
                     </security:authorize>
                <security:authorize access="hasRole('USER')">
                   <label class="col-sm-9 col-form-label"> <spring:message  code="login.user"/></label>
                </security:authorize>
            </div>
            <div class="form-group row">
            <label class="col-sm-3 col-form-label"><spring:message  code="menues.type.functions"/></label>
                <div class="col-md-9">
            <select class="form-control form-control" name="idMnuTipoFuncion" onchange="this.form.submit()">
                    <option value="">Seleccion Funcion </option>
                <c:forEach var="l" items="${lTiposFunciones}">
                    <option value="${l.idMnuTipoFuncion}">${l.tipoFuncion}</option>
                </c:forEach>
           </select>
            </div>
                 </div>
    </div>
   </div>
 </div>                                                            
        <div class="modal-footer">
              <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            <a class="btn btn-danger" href="<spring:url value="/doLogout"/>" id="show"><i class="fa fa-times" aria-hidden="true"></i>  <spring:message  code="menu.logout"/></a></p>
        </div>
</form>
</div>
</div>
</div>

 
 
 