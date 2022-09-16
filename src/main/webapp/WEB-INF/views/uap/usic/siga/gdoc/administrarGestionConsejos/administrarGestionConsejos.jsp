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
                 <p class="h1"> <i class="fa fa-database" aria-hidden="true"></i> <spring:message  code="gdoc.gconsejo.titulo.modulo"/> </p>
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
                <p class="h3"><i class="fa fa-list-alt" aria-hidden="true"></i> <spring:message  code="gdoc.gconsejo.titulo.tabla"/></p> 
             </center>
         <table id="tComprobantes"  class="table" style="width:100%">
                        <thead>
                            <tr>
                                <th><spring:message  code="gdoc.gconsejo.form.gestionConsejo"/></th>
                                <th><spring:message  code="gdoc.gconsejo.form.fechaini"/></th>
                                <th><spring:message  code="gdoc.gconsejo.form.fechafinal"/></th>
                            </tr>
                        </thead>
                        <tbody>
                        
                            <c:set var="cont" value="0" />
                            <c:forEach items="${lGestionConsejos}" var="lconse">
                                  <c:set var="cont" value="${cont+1}" />
                                   <tr>
                                    <td>${lconse.gestionConsejo}</td>
                                    <td>${lconse.fecInicio}</td>
                                    <td>${lconse.fecFinal}</td>
                                </tr>
                          </c:forEach>
                            
                        </tbody>
                        <tfoot>
                            <tr>
                                 <th><spring:message  code="gdoc.gconsejo.form.gestionConsejo"/></th>
                                <th><spring:message  code="gdoc.gconsejo.form.fechaini"/></th>
                                <th><spring:message  code="gdoc.gconsejo.form.fechafinal"/></th>
                            </tr>
                        </tfoot>
                    </table>
         
        </c:when>
    </c:choose>
  
          <!----------------Formulario Registro Titulos ======================================================== -->
<br>
      <c:choose>
        <c:when test="${operation eq 'reg' }">
             <center> <p class="h3"><i class="fa fa-th" aria-hidden="true"></i>  <spring:message  code="gdoc.gconsejo.titulo.form.registro"/></p> </center>
            <br>
            <form:form modelAttribute="gdocGestionConsejos" action="${regGestionConsejo}" method="post" enctype="multipart/form-data">
               
               
                
                <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-2 col-form-label"><spring:message  code="gdoc.gconsejo.form.Consejo"/></label>
                        <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <form:select class="form-control form-control-lg" path="GdocConsejos.idGdocConsejo" id="idGdocConsejo">
                                <c:forEach var="lti" items="${requestScope.lConsejos}">
                                    <form:option value="${lti.idGdocConsejo}">${lti.consejo}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                        <small id="emailHelp" class="form-text text-muted">
                            <spring:message  code="gdoc.gconsejo.form.msg.Consejo"/>
                         </small>
                          <small id="ci" class="form-text text-muted formFieldError">
                                <form:errors path="fecFinal" cssClass="alert alert-danger"/>
                            </small>
                     </div>
                    </div>
                
                
                <div class="form-group row">
                    <spring:message  code="gdoc.gconsejo.form.msg.gestionConsejo" var="obj"/>
                    <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="gdoc.gconsejo.form.gestionConsejo"/></label>
                    <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                    <div class="col-sm-6" id="combo1"> 
                        <form:input class="form-control form-control-lg" path="gestionConsejo"  id="objRes"  placeholder="${obj}"  />
                     </div>
                    <div class="col-sm-3" id="combo1"> 
                        <small id="emailHelp" class="form-text text-muted">
                            <spring:message  code="gdoc.gconsejo.form.msg.gestionConsejo"/>
                         </small>
                          <small id="ci" class="form-text text-muted formFieldError">
                                <form:errors path="gestionConsejo" cssClass="alert alert-danger"/>
                            </small>
                     </div>
                </div>
                
                <div class="form-group row">
                    <spring:message  code="report.account.dateStart" var="fecini"/>
                    <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="gdoc.gconsejo.form.fechaini"/></label>
                   <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                    <div class="col-sm-6" id="combo1"> 
                          <form:input type="date" path="fecInicio" class="form-control form-control-lg"    placeholder="${fecini}"/>
                     </div>
                    <div class="col-sm-3" id="combo1"> 
                        <small id="emailHelp" class="form-text text-muted">
                            <spring:message  code="gdoc.gconsejo.form.fechaini.msg"/>
                         </small>
                          <small id="ci" class="form-text text-muted formFieldError">
                                <form:errors path="fecInicio" cssClass="alert alert-danger"/>
                            </small>
                     </div>
                </div>   
                
                <div class="form-group row">
                    <spring:message  code="report.account.dateStart" var="fecfinal"/>
                    <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="gdoc.gconsejo.form.fechafinal"/></label>
                   <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                    <div class="col-sm-6" id="combo1"> 
                          <form:input type="date" path="fecFinal" class="form-control form-control-lg"    placeholder="${fecfinal}"/>
                     </div>
                    <div class="col-sm-3" id="combo1"> 
                        <small id="emailHelp" class="form-text text-muted">
                            <spring:message  code="gdoc.gconsejo.form.fechafinal.msg"/>
                         </small>
                          <small id="ci" class="form-text text-muted formFieldError">
                                <form:errors path="fecFinal" cssClass="alert alert-danger"/>
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
                            <a href="<spring:url value="/gestionconsejos/inicioGestionConsejos"/>" class="btn btn-yvela-green"><i class="fa fa-undo yvela" aria-hidden="true"></i>  <spring:message  code="expenses.btn.cancel"/></a>
                        </div> 
                    </div>
             </form:form>
         </c:when>
    </c:choose>
        <!-----------------------Fin Formulario Registro Ingresosss ======================================================== -->
            
  
               