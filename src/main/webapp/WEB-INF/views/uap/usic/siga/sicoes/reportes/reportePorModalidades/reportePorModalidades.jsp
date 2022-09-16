+<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<head>
    <link rel="stylesheet" type="text/css"  href=" <c:url value="/resource/sicoes/admProyectos/proyectos.css"/>" />
    <script src="<spring:url value="/resource/sicoes/reportes/reportes.js" />" type="text/javascript"></script>
    <style>
    </style>

</head>
<div class="container">   
    <section class="content-header">
        <div class="row">
            <div class="col-md-10 mb-3">
                <p class="h1"><i class="fa fa-pinterest-square" aria-hidden="true"></i> <spring:message code="report.contracts.module.title"/> </p>
            </div>
            <div class="col-sm-2 text-right" id="reporte">
                <c:choose>
                    <c:when test="${busqueda eq 'find' }">
                       <!--  <form  method="POST" action="${urlNProy}">
                            <span class="icon-input-btn"><i class="fa fa-folder yvela" aria-hidden="true"></i> <input type="submit" class="btn btn-success btn-lg" value="<spring:message  code="income.btn.new"/>"></span>
                        </form> -->
                    </c:when>
                </c:choose> 
            </div>
        </div>
        <hr align="left" noshade="noshade" size="10" width="100%" />
    </section>

    <section class="content-body">
        <c:if test="${not empty error}">
            <div class="alert alert-danger">
                <spring:message  code="AbstractUserDetailsAuthenticationProvider.badCredentials"/>
                <br/>
            </div>
        </c:if>

        <!--------------- Inicio Formulario de Reporte Por Modalidaes -->
        <c:choose>
            <c:when test="${operation eq 'form' }">
                <center><br>
                    <p class="h3"><i class="fa fa-file" aria-hidden="true"></i> <spring:message  code="report.contracts.form.title"/></p> 
                    <br>
                </center>
                <form action="${urlList}" enctype="multipart/form-data"  method="post">
                  
                    <div class="form-group row">
                        <spring:message  code="items.year" var="year"/>
                        <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="items.year"/></label>
                        <label class="col-sm-1 col-form-label text-right" > <i class="fa fa-info-circle" aria-hidden="true"></i> </label>
                        <div class="col-sm-6" id="combo1"> 
                            <input type="text" class="form-control form-control-lg" name="gestion"  placeholder="${year}" value="${gestion}" />
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                            <small id="emailHelp" class="form-text text-muted">
                                <spring:message  code="items.year.msg"/>
                            </small>
                        </div>
                    </div>
                      <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-2 col-form-label"><spring:message  code="contracts.form.contract.modality"/></label>
                        <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <select class="form-control form-control-lg" name="idScsModalidad" id="modalidad">
                                <c:forEach var="lmd" items="${requestScope.lScsModalidades}">
                                    <option value="${lmd.idScsModalidad}">${lmd.scsModalidad}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <spring:message  code="contracts.form.contract.modality.msg"/>
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
                            <a href="<spring:url value="/aCarpetas/inicioCarpetas"/>" class="btn btn-yvela-green"><i class="fa fa-undo yvela" aria-hidden="true"></i>  <spring:message  code="expenses.btn.cancel"/></a>
                        </div> 
                    </div> 
                </form>
                <br><br>
                <hr align="left" noshade="noshade" size="10" width="100%" />
                
            </c:when>
        </c:choose>
        <!-- ---------------- Final Formulario Reprote Por Modalidad -------- -->
      
        
        <!-- ------ Tabla lista de Contrataciones por Modalidad ------------- -->
        <c:choose>
            <c:when test="${operation eq 'list' }">

                <center><br>
                    <p class="h3"><i class="fa fa-table" aria-hidden="true"></i> <spring:message  code="report.contracts.title.list.preview"/></p> 
                    <br>
                </center>
              <table  id="tReporteM"  class="table" style="width:100%">
                    <thead>
                        <tr>
                            <th><spring:message  code="contracts.form.contract.modality"/></th>
                            <th><spring:message  code="contracts.form.official"/></th>
                            <th><spring:message  code="contracts.form.service.charge"/></th>
                            <th><spring:message  code="contracts.form.date.awarded"/> - <spring:message  code="contracts.form.final.date"/></th>
                            <th><spring:message  code="contracts.form.total.amount"/></th>
                            <th><spring:message  code="contracts.form.project"/></th>
                         </tr>
                    </thead>
                    
                    <tbody>
                        <c:set var="cont" value="0" />
                        <c:forEach items="${lContratosModalidades}" var="lCm">
                            <c:set var="cont" value="${cont+1}" />
                            <tr>
                                <td>${lCm.scsModalidades.scsModalidad}</td>
                                <td>${lCm.personas.nombres} ${lCm.personas.paterno} ${lCm.personas.materno}</td>
                                <td>${lCm.scsCargoServicio}</td>
                                <td>${lCm.fecInicio} - ${lCm.fecFinal}</td>
                                <td>${lCm.montoTotal}</td>
                               <td>${lCm.scsProyectos.scsProyecto}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                    <tfoot>
                       <tr>
                           <tr>
                            <th><spring:message  code="contracts.form.contract.modality"/></th>
                            <th><spring:message  code="contracts.form.official"/></th>
                            <th><spring:message  code="contracts.form.service.charge"/></th>
                            <th><spring:message  code="contracts.form.date.awarded"/> - <spring:message  code="contracts.form.final.date"/></th>
                            <th><spring:message  code="contracts.form.total.amount"/></th>
                            <th><spring:message  code="contracts.form.project"/></th>
                         </tr>
                        </tr>
                    </tfoot>
                </table>
               <form action="${urlImp}" enctype="multipart/form-data"  method="post"  target="blank">
                  <input type="hidden" name="gestion" value="${gestionR}">
                  <input type="hidden" name="idScsModalidad" value="${idScsModalidad}">
              
                    

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
                            <a href="<spring:url value="/aCarpetas/inicioCarpetas"/>" class="btn btn-yvela-green"><i class="fa fa-undo yvela" aria-hidden="true"></i>  <spring:message  code="expenses.btn.cancel"/></a>
                        </div> 
                    </div> 
                 </form>
                <br><br>
                <hr align="left" noshade="noshade" size="10" width="100%" />
                
             </c:when>
        </c:choose>
    </section>
</div>
        