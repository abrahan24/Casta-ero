+<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<head>
    <link rel="stylesheet" type="text/css"  href=" <c:url value="/resource/sicoes/admProyectos/proyectos.css"/>" />
    <script src="<spring:url value="/resource/sicoes/admProyectos/proyectos.js" />" type="text/javascript"></script>
    <style>
    </style>

</head>
<div class="container">   
    <section class="content-header">
        <div class="row">
            <div class="col-md-10 mb-3">
                <p class="h1"><i class="fa fa-pinterest-square" aria-hidden="true"></i> <spring:message code="project.form.title"/> </p>
            </div>
            <div class="col-sm-2 text-right" id="reporte">
                <c:choose>
                    <c:when test="${busqueda eq 'find' }">
                         <form  method="POST" action="${urlNProy}">
                            <span class="icon-input-btn"><i class="fa fa-folder yvela" aria-hidden="true"></i> <input type="submit" class="btn btn-success btn-lg" value="<spring:message  code="income.btn.new"/>"></span>
                        </form>
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

        <!--------------- Inicio registro nueva Carpeta-->
        <c:choose>
            <c:when test="${operation eq 'reg' }">
                <center><br>
                    <p class="h3"><i class="fa fa-file" aria-hidden="true"></i> <spring:message  code="project.form.register.project"/></p> 
                    <br>
                </center>
                <form:form modelAttribute="scsProyectos" action="${regProy}" enctype="multipart/form-data"  method="post">
                  
                     <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-2 col-form-label"><spring:message  code="units.unit"/></label>
                        <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <form:select class="form-control form-control-lg" path="InsUnidadesFuncionales.idUnidadFuncional" id="unidad" >
                                <c:forEach var="lu" items="${requestScope.lUnidades}">
                                    <form:option value="${lu.idUnidadFuncional}">${lu.unidadFuncional}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <spring:message  code="items.unit.msg"/>
                           </small>
                            <small id="ci" class="form-text text-muted formFieldError">
                                <form:errors path="InsUnidadesFuncionales.idUnidadFuncional" cssClass="alert alert-danger"/>
                            </small>
                        </div>
                    </div>
                    
                   <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="contracts.form.project"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <spring:message  code="project.form.project.msg" var="project" />
                            <form:input type="text" path="scsProyecto"  class="form-control form-control-lg"  placeholder="${project}" onchange="construirNombrePdf();"/> 
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                               <spring:message  code="project.form.project.msg"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="scsProyecto" />
                            </small>
                        </div>
                    </div>

                     <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="project.form.project.code"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <spring:message  code="project.form.project.code.msg" var="codeP"/>
                            <form:input type="text" path="scsCodigoProyecto"  class="form-control form-control-lg"  placeholder="${codeP}"/> 
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                               <spring:message  code="project.form.project.code.msg"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="scsCodigoProyecto" />
                            </small>
                        </div>
                    </div>
                     
                    <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="project.form.project.number"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <spring:message  code="project.form.project.number.msg" var="nroP"/>
                            <form:input type="Integer" path="scsNroProyecto"  class="form-control form-control-lg"  placeholder="${nroP}"/> 
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                               <spring:message  code="project.form.project.number.msg"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="scsNroProyecto" />
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <spring:message  code="items.year" var="year"/>
                        <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="items.year"/></label>
                        <label class="col-sm-1 col-form-label text-right" > </label>
                        <div class="col-sm-6" id="combo1"> 
                            <form:input class="form-control form-control-lg" path="gestion"  placeholder="${year}" value="${gestion}" />
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                            <small id="emailHelp" class="form-text text-muted">
                                <spring:message  code="items.year.msg"/>
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <spring:message  code="items.period.msg" var="period"/>
                        <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="items.period"/></label>
                        <label class="col-sm-1 col-form-label text-right" > </label>
                        <div class="col-sm-6" id="combo1"> 
                            <form:input class="form-control form-control-lg" path="periodo"  value="${periodo}" />
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                            <small id="emailHelp" class="form-text text-muted">
                                <spring:message  code="items.period.msg" />
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
                            <a href="<spring:url value="/aProyectos/inicioProyectos"/>" class="btn btn-yvela-green"><i class="fa fa-undo yvela" aria-hidden="true"></i>  <spring:message  code="expenses.btn.cancel"/></a>
                        </div> 
                    </div> 
                </form:form>
                <br><br>
                <hr align="left" noshade="noshade" size="10" width="100%" />
                
            </c:when>
        </c:choose>
        <!-- ---------------- Final registro Proyectos -->
      
        <!--------------- Inicio Modificar Proyectos----------------------------------- -->
        <c:choose>
            <c:when test="${operation eq 'mod' }">
                
                <center><br>
                    <p class="h3"><i class="fa fa-folder-o" aria-hidden="true"></i> <spring:message  code="project.form.modification.title"/></p> 
                    <br>
                </center>
                
                 <form:form modelAttribute="scsProyectos" action="${confModProy}" enctype="multipart/form-data"  method="post">
                      <form:input type="hidden" path="idScsProyecto" value="${scsProyectos.idScsProyecto}" />
                     
                      <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-2 col-form-label"><spring:message  code="units.unit"/></label>
                        <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <form:select class="form-control form-control-lg" path="InsUnidadesFuncionales.idUnidadFuncional" id="unidad" >
                                <c:forEach var="lu" items="${requestScope.lUnidades}">
                                    <form:option value="${lu.idUnidadFuncional}">${lu.unidadFuncional}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <spring:message  code="items.unit.msg"/>
                           </small>
                            <small id="ci" class="form-text text-muted formFieldError">
                                <form:errors path="InsUnidadesFuncionales.idUnidadFuncional" cssClass="alert alert-danger"/>
                            </small>
                        </div>
                    </div>
                    
                   <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="contracts.form.project"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <spring:message  code="project.form.project.msg" var="project" />
                            <form:input type="text" path="scsProyecto"  class="form-control form-control-lg"  placeholder="${project}" onchange="construirNombrePdf();"/> 
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                               <spring:message  code="project.form.project.msg"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="scsProyecto" />
                            </small>
                        </div>
                    </div>

                     <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="project.form.project.code"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <spring:message  code="project.form.project.code.msg" var="codeP"/>
                            <form:input type="text" path="scsCodigoProyecto"  class="form-control form-control-lg"  placeholder="${codeP}"/> 
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                               <spring:message  code="project.form.project.code.msg"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="scsCodigoProyecto" />
                            </small>
                        </div>
                    </div>
                     
                    <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="project.form.project.number"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <spring:message  code="project.form.project.number.msg" var="nroP"/>
                            <form:input type="Integer" path="scsNroProyecto"  class="form-control form-control-lg"  placeholder="${nroP}"/> 
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                               <spring:message  code="project.form.project.number.msg"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="scsNroProyecto" />
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <spring:message  code="items.year" var="year"/>
                        <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="items.year"/></label>
                        <label class="col-sm-1 col-form-label text-right" > </label>
                        <div class="col-sm-6" id="combo1"> 
                            <form:input class="form-control form-control-lg" path="gestion"  placeholder="${year}" />
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                            <small id="emailHelp" class="form-text text-muted">
                                <spring:message  code="items.year.msg"/>
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <spring:message  code="items.period.msg" var="period"/>
                        <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="items.period"/></label>
                        <label class="col-sm-1 col-form-label text-right" > </label>
                        <div class="col-sm-6" id="combo1"> 
                            <form:input class="form-control form-control-lg" path="periodo"  value="${periodo}" />
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                            <small id="emailHelp" class="form-text text-muted">
                                <spring:message  code="items.period.msg" />
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
                            <span class="icon-input-btn"><i class="fa fa-pencil-square-o yvela" aria-hidden="true"></i> <input class="btn btn-success" type="submit" value="<spring:message  code="expenses.modify"/>"></span>
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                        </div>
                        <div class="col-sm-3" id="combo1">
                            <a href="<spring:url value="/aProyectos/inicioProyectos"/>" class="btn btn-yvela-green"><i class="fa fa-undo yvela" aria-hidden="true"></i>  <spring:message  code="expenses.btn.cancel"/></a>
                        </div> 
                    </div>
                
                </form:form>
                
                <br><br>
                <hr align="left" noshade="noshade" size="10" width="100%" />
             </c:when>
        </c:choose>
        <!------------------------------------ Final Modificar carpetas------------------------------------------- -->
        
        <!------------------------------------ Inicio Eliminar Personas------------------------------------------- -->
        
        <c:choose>
            <c:when test="${operation eq 'delet' }">
                <div id="" class="col-lg-12" >
                    <br>
                    <center><h5><i class="fa fa-trash-o" aria-hidden="true"></i>  <spring:message  code="project.form.elimination.title"/></h5></center>
                    <br>
                <div class="form-group row">
                 <div class="col-sm-8" id="combo1"> 
                 <form:form modelAttribute="scsProyectos" action="${confElimProy}" enctype="multipart/form-data"  method="post">
                     <form:input type="hidden" path="idScsProyecto" value="${scsProyectos.idScsProyecto}" />
                     
                      <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-4 col-form-label"><spring:message  code="units.unit"/></label>
                         <div class="col-sm-8">
                            <form:select class="form-control form-control-lg" path="InsUnidadesFuncionales.idUnidadFuncional" id="unidad" >
                                <c:forEach var="lu" items="${requestScope.lUnidades}">
                                    <form:option value="${lu.idUnidadFuncional}">${lu.unidadFuncional}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                    </div>
                    
                   <div class="form-group row">
                        <label for="codigo" class="col-sm-4 col-form-label"><spring:message  code="contracts.form.project"/></label>
                        <div class="col-sm-8">
                            <spring:message  code="project.form.project.msg" var="project" />
                            <form:input type="text" path="scsProyecto"  class="form-control form-control-lg"  placeholder="${project}" onchange="construirNombrePdf();"/> 
                        </div>
                    </div>

                     <div class="form-group row">
                        <label for="codigo" class="col-sm-4 col-form-label"><spring:message  code="project.form.project.code"/></label>
                        <div class="col-sm-8">
                            <spring:message  code="project.form.project.code.msg" var="codeP"/>
                            <form:input type="text" path="scsCodigoProyecto"  class="form-control form-control-lg"  placeholder="${codeP}"/> 
                        </div>
                     </div>
                     
                    <div class="form-group row">
                        <label for="codigo" class="col-sm-4 col-form-label"><spring:message  code="project.form.project.number"/></label>
                        <div class="col-sm-8">
                            <spring:message  code="project.form.project.number.msg" var="nroP"/>
                            <form:input type="Integer" path="scsNroProyecto"  class="form-control form-control-lg"  placeholder="${nroP}"/> 
                        </div>
                    </div>
                    <div class="form-group row">
                        <spring:message  code="items.year" var="year"/>
                        <label for="sedes" class="col-sm-4 col-form-label"><spring:message  code="items.year"/></label>
                        <div class="col-sm-8" id="combo1"> 
                            <form:input class="form-control form-control-lg" path="gestion"  placeholder="${year}"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <spring:message  code="items.period.msg" var="period"/>
                        <label for="sedes" class="col-sm-4 col-form-label"><spring:message  code="items.period"/></label>
                        <div class="col-sm-8" id="combo1"> 
                            <form:input class="form-control form-control-lg" path="periodo"  value="${periodo}" />
                        </div>
                    </div> 
                    <hr align="left" noshade="noshade" size="10" width="100%" />
                      <div class="form-group row mt-12">
                       <div class="col-sm-3" id="combo1"> 
                       </div>
                       <div class="col-sm-3" id="combo1">
                            <span class="icon-input-btn"><i class="fa fa-times yvela" aria-hidden="true"></i> 
                               <input type="submit"  class="btn btn-danger" value="<spring:message  code="expenses.btn.confirm"/>">
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                     </div>
                     <div class="col-sm-3" id="combo1"> 
                          <a href="<spring:url value="/aProyectos/inicioProyectos"/>" class="btn btn-yvela-green"><i class="fa fa-undo" aria-hidden="true"></i>  <spring:message  code="expenses.btn.cancel"/></a>
                      </div>
                     </div>
                    </form:form>
                   </div>
                            <div class="col-sm-4" id="combo1"> 
                                 <fieldset class="col-md-12">    	
                                <legend><i class="fa fa-exclamation-triangle" aria-hidden="true"></i>  <spring:message  code="expenses.remove.info.title"/></legend>
                                <br> 
                                <p><spring:message  code="people.remove.msg"/> </p>
                                <br>
                                <c:choose>
                                    <c:when test="${mensage eq 'err' }">
                                        <span class="parpadea text"><p class="h1"><i class="fa fa-exclamation-triangle" aria-hidden="true"></i></p></span>  <spring:message  code="people.warning.remove"/> 
                                            </c:when>
                                        </c:choose>
                                <br>
                            </fieldset>
                            </div>
                        </div>
                    </div>
                </c:when>
            </c:choose>       

        
        
        <!------------------------------------ Final Eliminar Personas------------------------------------------- -->
        
        <!-- ------ Tabla lista de Todas las Contrataciones ------------- -->
        <c:choose>
            <c:when test="${busqueda eq 'find' }">

                <center><br>
                    <p class="h3"><i class="fa fa-table" aria-hidden="true"></i> <spring:message  code="project.form.list.title"/></p> 
                    <br>
                </center>
                   
                <table  id="tCarpetas"  class="table" style="width:100%">
                    <thead>
                        <tr>
                            <th><spring:message  code="project.form.project.code"/></th>
                            <th><spring:message  code="project.form.project.number"/></th>
                            <th><spring:message  code="items.year"/></th>
                            <th><spring:message  code="contracts.form.project"/></th>
                            <th><spring:message  code="units.unit"/></th>
                            <th><spring:message  code="expenses.modify"/> </th>
                            <th><spring:message  code="expenses.remove"/></th>
                        </tr>
                    </thead>
                    
                    <tbody>
                        <c:set var="cont" value="0" />
                        <c:forEach items="${lScsProyectos}" var="lScp">
                            <c:set var="cont" value="${cont+1}" />
                            <tr>
                                <td>${lScp.scsCodigoProyecto}</td>
                                <td>${lScp.scsNroProyecto}</td>
                                <td>${lScp.gestion}</td>
                                <td>${lScp.scsProyecto} </td>
                                <td>${lScp.insUnidadesFuncionales.unidadFuncional}</td>
                                <td>
                                    <form  method="post" action="${urlModProy}">
                                        <input type="hidden" name="idScsProyecto" value="${lScp.idScsProyecto}">
                                        <span class="icon-input-btn"> <i class="fa fa-pencil-square-o yvela" aria-hidden="true"> </i> <input class="btn btn-success" type="submit"  value="<spring:message  code="expenses.modify"/>" > </span>
                                    </form>
                                </td>
                                <td>
                                    <form name="Delet" action="${iniElimProy}" method="post">
                                        <input type="hidden" name="idScsProyecto" value="${lScp.idScsProyecto}">
                                        <span class="icon-input-btn"> <i class="fa fa-times yvela" aria-hidden="true"> </i> <input class="btn btn-danger" type="submit"  value="<spring:message  code="expenses.remove"/>"> </span>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                    <tfoot>
                       <tr>
                           <th><spring:message  code="project.form.project.code"/></th>
                            <th><spring:message  code="project.form.project.number"/></th>
                            <th><spring:message  code="items.year"/></th>
                            <th><spring:message  code="contracts.form.project"/></th>
                            <th><spring:message  code="units.unit"/></th>
                            <th><spring:message  code="expenses.modify"/> </th>
                            <th><spring:message  code="expenses.remove"/></th>
                        </tr>
                    </tfoot>
                </table>
                        
                <br><br>
                <hr align="left" noshade="noshade" size="10" width="100%" />
             </c:when>
        </c:choose>
    </section>
</div>
        