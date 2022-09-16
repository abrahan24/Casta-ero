<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<head>
    <link rel="stylesheet" type="text/css"  href=" <c:url value="/resource/admIngresos/ingresos.css"/>" />
    <script src="<spring:url value="/resource/gdoc/resolucion.js" />" type="text/javascript"></script>
    <script type="text/javascript">
    </script>
 </head>
<div class="container">      <section class="content-header">
        <div class="row">
            <div class="col-md-10 mb-3">
                 <p class="h1"> <i class="fa fa-database" aria-hidden="true"></i> <spring:message  code="gdoc.funcionesuser.titulo.modulo"/> </p>
            </div>
            <div class="col-sm-2 text-right" id="reporte">
                <c:choose>
                    <c:when test="${busqueda eq 'find' }">
                         <form  method="POST" action="${urlNuevo}">
                            <span class="icon-input-btn"><i class="fa fa-user-plus yvela" aria-hidden="true"></i> <input type="submit" class="btn btn-success btn-lg" value="<spring:message  code="income.btn.new"/>"></span>
                        </form>
                    </c:when>
                </c:choose> 
            </div>
        </div>
        <hr align="left" noshade="noshade" size="10" width="100%" />
    </section>
<section> 
     <c:if test="${not empty error}">
        <div class="alert alert-danger">
            <spring:message  code="AbstractUserDetailsAuthenticationProvider.badCredentials"/>
        </div>
    </c:if>
       
    <!--Tabla lista de Todos los Ingresos Registrados -->

    <c:choose>
        <c:when test="${busqueda eq 'find' }">
            <center><br>
                <p class="h3"><i class="fa fa-list-alt" aria-hidden="true"></i> <spring:message  code="gdoc.funcionesuser.titulo.tabla"/></p> 
             </center>
         <table id="tComprobantes"  class="table" style="width:100%">
                        <thead>
                            <tr>
                                <th><spring:message  code="gdoc.funcionesuser.form.nombre.consejo"/></th>
                                <th><spring:message  code="gdoc.funcionesuser.form.usuario"/></th>
                                <th><spring:message  code="gdoc.funcionesuser.form.mnu.funcion"/></th>
                            </tr>
                        </thead>
                        <tbody>
                        
                        <c:set var="cont" value="0" />
                            <c:forEach items="${lFuncionesUsuarios}" var="lfunser">
                                  <c:set var="cont" value="${cont+1}" />
                                   <tr>
                                    <td>${lfunser.gdocConsejos.consejo}</td>
                                   	<td>${lfunser.usuarios.usuario}</td>
                                   	<td>${lfunser.mnuTiposFunciones.tipoFuncion}</td>
                                    <td>
                                </tr>
                          </c:forEach>
                           
                        </tbody>
                        <tfoot>
                            <tr>
                                 <th><spring:message  code="gdoc.funcionesuser.form.nombre.consejo"/></th>
                                <th><spring:message  code="gdoc.funcionesuser.form.usuario"/></th>
                                <th><spring:message  code="gdoc.funcionesuser.form.mnu.funcion"/></th>
                            </tr>
                        </tfoot>
                    </table>
         
        </c:when>
    </c:choose>
  
          <!----------------Formulario Registro Titulos ======================================================== -->
<br>
      <c:choose>
        <c:when test="${operation eq 'reg' }">
             <center> <p class="h3"><i class="fa fa-th" aria-hidden="true"></i>  <spring:message  code="gdoc.funcionesuser.titulo.form.registro"/></p> </center>
            <br>
            <form:form modelAttribute="gdocUsrTiposFunciones" action="${regFuncionesUsr}" method="post" enctype="multipart/form-data">
               
               
                
                
                    
              <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-2 col-form-label"><spring:message  code="gdoc.funcionesuser.form.msg.nombre.consejo"/></label>
                        <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <form:select class="form-control form-control-lg" path="GdocConsejos.idGdocConsejo" id="idGdocConsejo">
                                <c:forEach var="lgcon" items="${requestScope.lConsejos}">
                                    <form:option value="${lgcon.idGdocConsejo}">${lgcon.consejo}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <a class="btn add" href="#" data-toggle="modal" data-target="#formCiExpedido"><i class="fa fa-plus-square-o" aria-hidden="true"></i>  <spring:message  code="people.add"/></a></small>
                            <small id="ci" class="form-text text-muted formFieldError">
                               <form:errors path="GdocConsejos.idGdocConsejo" cssClass="alert alert-danger"/>
                           </small>
                        </div>
                    </div>
                    
                     <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-2 col-form-label"><spring:message  code="gdoc.funcionesuser.form.msg.usuario"/></label>
                        <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <form:select class="form-control form-control-lg" path="Usuarios.idUsuario" id="idUsuario">
                                <c:forEach var="lgusu" items="${requestScope.lUsuarios}">
                                    <form:option value="${lgusu.idUsuario}">${lgusu.usuario}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <a class="btn add" href="#" data-toggle="modal" data-target="#formCiExpedido"><i class="fa fa-plus-square-o" aria-hidden="true"></i>  <spring:message  code="people.add"/></a></small>
                            <small id="ci" class="form-text text-muted formFieldError">
                               <form:errors path="Usuarios.idUsuario" cssClass="alert alert-danger"/>
                           </small>
                        </div>
                    </div>
                     
                  <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-2 col-form-label"><spring:message  code="gdoc.funcionesuser.form.msg.mnu.funcion"/></label>
                        <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <form:select class="form-control form-control-lg" path="MnuTiposFunciones.idMnuTipoFuncion" id="idMnuTipoFuncion">
                                <c:forEach var="lmtf" items="${requestScope.lMnuTiposFuncion}">
                                    <form:option value="${lmtf.idMnuTipoFuncion}">${lmtf.tipoFuncion}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <a class="btn add" href="#" data-toggle="modal" data-target="#formCiExpedido"><i class="fa fa-plus-square-o" aria-hidden="true"></i>  <spring:message  code="people.add"/></a></small>
                            <small id="ci" class="form-text text-muted formFieldError">
                               <form:errors path="MnuTiposFunciones.idMnuTipoFuncion" cssClass="alert alert-danger"/>
                           </small>
                        </div>
                    </div>
                   
                
              
                
                
					 
					  
					  

                            
                <div class="form-group row">
                        <label for="msg" class="col-sm-2 col-form-label text-right"></label>
                        <label for="msg" class="col-sm-10 col-form-label"><span class="parpadea text"><i class="fa fa-info-circle" aria-hidden="true"></i></span> &nbsp;&nbsp;&nbsp;<spring:message  code="people.warning"/> </label>
                    </div>   

                    <hr align="left" noshade="noshade" size="10" width="100%" />
                    <div class="form-group row mt-12">
                        <div class="col-sm-3" id="combo1">
                        </div> 
                        <div class="col-sm-3" id="combo1">
                            <span class="icon-input-btn"><i class="fa fa-pencil-square-o yvela" aria-hidden="true"></i> <input class="btn btn-success" type="submit" value="<spring:message  code="institution.register"/>"></span>
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                        </div>
                        <div class="col-sm-3" id="combo1">
                            <a href="<spring:url value="/funcionesuser/inicioFuncionesUsuarios"/>" class="btn btn-yvela-green"><i class="fa fa-undo yvela" aria-hidden="true"></i>  <spring:message  code="expenses.btn.cancel"/></a>
                        </div> 
                    </div>
             </form:form>
         </c:when>
    </c:choose>
        <!-----------------------Fin Formulario Registro Ingresosss ======================================================== -->
            
  
               