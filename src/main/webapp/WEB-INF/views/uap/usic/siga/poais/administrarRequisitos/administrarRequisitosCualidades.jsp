<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<head>
    <link rel="stylesheet" type="text/css"  href=" <c:url value="/resource/admPersonas/personas.css"/>" />
    <script src="<spring:url value="/resource/poais/identification/admIdentification.js" />" type="text/javascript"></script>
      
    <style type="text/css">
        #portapdf {
            width: 500px;
            height: 400px;
            border: 1px solid #484848;
            margin: 0 auto;
        }
    </style>
    <script>

    </script>
</head>

<div class="container">    
    <section class="content-header">
        <div class="row">
            <div class="col-md-10 mb-3">
                <p class="h1"><i class="fa fa-file-archive-o" aria-hidden="true"> </i> <spring:message code="poais.requisito.cualidad.titulo.modulo"/> </p>
            </div>
            <div class="col-sm-2 text-right" id="reporte">
                <c:choose>
                    <c:when test="${busqueda eq 'find' }">
                        <form  method="POST" action="${urlN}">
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
        
        <!-- ------------------------ Fin Tabla Listar Poais ------------------------- -->
            <c:choose>
                <c:when test="${busqueda eq 'find' }">
                    <center><br>
                        <p class="h3"><i class="fa fa-folder-open-o" aria-hidden="true"></i> <spring:message  code="poais.requisito.cualidad.titulo.lista"/></p> 
                        <br>
                    </center>
                    <table id="tComprobantes"  class="table" style="width:100%">
                        <thead>
                            <tr>
                                <th>Nro</th>
                                <th><spring:message  code="items.year"/></th>
                                <th><spring:message  code="poais.requisito.cualidad.campo.cualidad"/></th>
                                <!-- 
                                <th><spring:message  code="expenses.modify"/> </th>
                                <th><spring:message  code="expenses.remove"/></th>-->
                            </tr>
                        </thead>
                        <tbody>
                            <c:set var="cont" value="0" />
                            <c:forEach items="${lRCualidades}" var="lPrc">
                                <c:set var="cont" value="${cont+1}" />
                                <tr>
                                    <td>${cont}</td>
                                    <td>${lPrc.poaisRequisitos.poais.gestion}</td>
                                    <td>${lPrc.requisitoCualidad}</td>
                                    <!-- 
                                    <td>
                                        <form  method="post" action="${urlM}">
                                            <input type="hidden" name="idRequisitoCualidad" value="${lPrc.idRequisitoCualidad}">
                                            <span class="icon-input-btn"> <i class="fa fa-pencil-square-o yvela" aria-hidden="true"> </i> <input class="btn btn-success" type="submit"  value="<spring:message  code="expenses.modify"/>" > </span>
                                        </form>
                                    </td>
                                    <td>
                                        <form name="Delet" action="${urlD}" method="post">
                                            <input type="hidden" name="idRequisitoCualidad" value="${lPrc.idRequisitoCualidad}">
                                            <span class="icon-input-btn"> <i class="fa fa-times yvela" aria-hidden="true"> </i> <input class="btn btn-danger" type="submit"  value="<spring:message  code="expenses.remove"/>"> </span>
                                        </form>
                                    </td>-->
                                </tr>
                          </c:forEach>
                        </tbody>
                        <!-- <tfoot>
                            <tr>
                                <th>Nro</th>
                                <th><spring:message  code="items.year"/></th>
                                <th><spring:message  code="poais.requisito.cualidad.campo.cualidad"/></th>
                                
                                <th><spring:message  code="expenses.modify"/> </th>
                                <th><spring:message  code="expenses.remove"/></th>
                            </tr>
                        </tfoot>-->
                    </table>
                    <br>
                    <div class="modal-footer">
	                    <a href="../../../../Castanhero/poaisReqCumple/inicioReqCumple" type="submit" class="btn btn-info btn-lg float-right" aria-hidden="true">
	                    Siguiente
	                    </a>
	                </div>
                    <br>
                    
                    <hr align="left" noshade="noshade" size="10" width="100%" />

                </c:when>
            </c:choose>
    </section>
    <!--  ---------------------- Fin Tabla Listar Poais -----------------  -->

   <!-- --------------------- Inicio registro nueva Poais Requisitos Cualidades  -------------------- -->
   <section>
        <c:choose>
            <c:when test="${operation eq 'reg' }">
                <center><br>
                    <p class="h3"><i class="fa fa-folder-open" aria-hidden="true"> </i> <spring:message  code="poais.requisito.cualidad.titulo.form"/></p> 
                    <br>
                </center>
                <form:form modelAttribute="poaisRequisitosCualidades" action="${regPoais}" enctype="multipart/form-data" method="post">
                   <form;hidden path="PoaisRequisitos.idRequisito" value="1" />              
                    <!-- 
                    <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="poai.form.campo.descripcion"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6" id="combo1"> 
                            <spring:message  code="poais.from.obs.descripcion" var="description"/>
                            <input type="textarea"  name="descripcion" class="form-control form-control-lg" placeholder="${description}" rows="3" cols="20" />
                        </div>
                        
                        <div class="col-sm-3" id="comd"> 
                            <small id="emailHp" class="form-text text-muted">
                                <spring:message  code="poais.from.obs.descripcion"/>
                            </small>
                            <small class="msgError">
                                
                            </small>
                        </div>
                    </div> -->
                      <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="poais.requisito.cualidad.campo.cualidad"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6" id="combo1"> 
                            <spring:message  code="poais.requisito.cualidad.msg.cualidad" var="cualy"/>
                              <form:textarea name="descripcion" path="requisitoCualidad" class="form-control form-control-lg" placeholder="${cualy}" rows="3" cols="20" />
                          </div>
                        <div class="col-sm-3" id="comd"> 
                            <small id="emailHp" class="form-text text-muted">
                                <spring:message  code="poais.requisito.cualidad.msg.cualidad"/>
                            </small>
                            <small class="msgError">
                               <form:errors path="requisitoCualidad" />
                            </small>
                        </div>
                    </div>
                 <div class="form-group row">
                       <label for="msg" class="col-sm-2 col-form-label text-right"></label>
                        <label for="msg" class="col-sm-10 col-form-label"><span class="parpadea text"><i class="fa fa-eye" aria-hidden="true"></i></span> &nbsp;&nbsp;&nbsp;<spring:message  code="people.warning"/> </label>
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
                         <!-- 
                        <div class="col-sm-3" id="combo1">
                            <a href="<spring:url value="/comprobante/inicioComprobantes"/>" class="btn btn-yvela-green"><i class="fa fa-undo yvela" aria-hidden="true"></i>  <spring:message  code="expenses.btn.cancel"/></a>
                        </div>  -->
                    </div>
                </form:form>
                <br><br>
                <hr align="left" noshade="noshade" size="10" width="100%" />
            </c:when>
        </c:choose>
        <!-- -------------------- Final Nuevo Identificaciones ---------------- -->
</section>
</div>