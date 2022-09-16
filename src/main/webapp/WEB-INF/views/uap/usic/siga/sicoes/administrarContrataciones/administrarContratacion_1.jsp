<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<head>
    <link rel="stylesheet" type="text/css"  href=" <c:url value="/resource/admPersonas/personas.css"/>" />
    <script src="<spring:url value="/resource/sac/admCarpetas/capetas.js" />" type="text/javascript"></script>
    <style>
    </style>

</head>
<div class="container">    <section class="content-header">
        <div class="row">
            <div class="col-md-10 mb-3">
                <p class="h1"><i class="fa fa-folder-open-o" aria-hidden="true"></i> <spring:message code="contracts.manage.contracts.title"/> </p>
            </div>
            <div class="col-sm-2 text-right" id="reporte">
                <c:choose>
                    <c:when test="${busqueda eq 'find' }">
                        <!-- <form  method="POST" action="${urlNCont}">
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

        <!--------------- Inicio registro nueva Carpeta-->
        <c:choose>
            <c:when test="${operation eq 'reg' }">
                <center><br>
                    <!--
                    <p class="h3"><i class="fa fa-folder-o" aria-hidden="true"></i> <spring:message  code="manage.folders.register"/></p> 
                    <br>
                </center>

                <form:form modelAttribute="scsContrataciones" action="${regCont}" method="post">
                
                    <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-2 col-form-label"><spring:message  code="manage.folders.bookcase"/></label>
                        <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <form:select class="form-control form-control-lg" path="ScsProyectos.idScsProyecto" id=proyecto">
                                <c:forEach var="scp" items="${requestScope.lScsProyectos}">
                                    <form:option value="${scp.idScsProyecto}">${scp.scsProyecto}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <a class="btn add" href="#" data-toggle="modal" data-target="#formEstantes"><i class="fa fa-plus-square-o" aria-hidden="true"></i>  <spring:message  code="people.add"/></a></small>
                            <small id="ci" class="form-text text-muted formFieldError">
                                <form:errors path="ScsProyectos.idScsProyecto" cssClass="alert alert-danger"/>
                            </small>
                        </div>
                    </div>
           
                    <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-2 col-form-label"><spring:message  code="manage.folders.name.folder"/></label>
                        <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <form:select class="form-control form-control-lg" path="SacTiposCarpetas.idSacTipoCarpeta" id="tipoCarpeta">
                                <c:forEach var="tc" items="${requestScope.lTiposCarpetas}">
                                    <form:option value="${tc.idSacTipoCarpeta}">${tc.sacTipoCarpeta}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <a class="btn add" href="#" data-toggle="modal" data-target="#formSacTipoCarpetas"><i class="fa fa-plus-square-o" aria-hidden="true"></i>  <spring:message  code="people.add"/></a></small>
                            <small id="ci" class="form-text text-muted formFieldError">
                                <form:errors path="SacTiposCarpetas.idSacTipoCarpeta" cssClass="alert alert-danger"/>
                            </small>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="manage.folders.code"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <spring:message  code="manage.folders.code.msg" var="nom"/>
                            <form:input type="text" path="sacCodigoCarpeta"  class="form-control form-control-lg"  placeholder="${nom}"/> 
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <spring:message  code="manage.folders.code.msg"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="sacCodigoCarpeta" />
                            </small>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-2 col-form-label"><spring:message  code="manage.folders.month"/></label>
                        <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <form:select class="form-control form-control-lg" path="SisMeses.idMes" >
                                <c:forEach var="m" items="${requestScope.lMeses}">
                                    <form:option value="${m.idMes}">${m.mes}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <spring:message  code="manage.folders.month.msg"/>
                            </small>
                            <small id="ci" class="form-text text-muted formFieldError">
                                <form:errors path="SisMeses.idMes" cssClass="alert alert-danger"/>
                            </small>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="manage.folders.number"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <spring:message  code="manage.folders.number.msg" var="nom"/>
                            <form:input type="number" path="sacNumeroCarpeta"  class="form-control form-control-lg"  placeholder="${nom}"/> 
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <spring:message  code="manage.folders.number.msg"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="sacNumeroCarpeta"  />
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
                            <a href="<spring:url value="/aCarpetas/inicioCarpetas"/>" class="btn btn-yvela-green"><i class="fa fa-undo yvela" aria-hidden="true"></i>  <spring:message  code="expenses.btn.cancel"/></a>
                        </div> 
                    </div> 
                </form:form>
                <br><br>
                <hr align="left" noshade="noshade" size="10" width="100%" />
                -->
            </c:when>
        </c:choose>
        <!-- Final registro carpetas -->

        <!--------------- Inicio Modificar carpetas----------------------------------- -->
        <c:choose>
            <c:when test="${operation eq 'mod' }">
                <!--
                <center><br>
                    <p class="h3"><i class="fa fa-folder-o" aria-hidden="true"></i> <spring:message  code="manage.folders.modify"/></p> 
                    <br>
                </center>

                <form:form modelAttribute="sacCarpetas" action="${conModCar}" method="post">

                    <form:hidden  path="idSacCarpeta" value="${sacCarpetas.idSacCarpeta}" />

                    <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-2 col-form-label"><spring:message  code="manage.folders.bookcase"/></label>
                        <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <form:select class="form-control form-control-lg" path="SacEstantes.idSacEstante" id="estante">
                                <c:forEach var="e" items="${requestScope.lEstantes}">
                                    <form:option value="${e.idSacEstante}">${e.sacNombreEstante}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <a class="btn add" href="#" data-toggle="modal" data-target="#formEstantes"><i class="fa fa-plus-square-o" aria-hidden="true"></i>  <spring:message  code="people.add"/></a></small>
                            <small id="ci" class="form-text text-muted formFieldError">
                                <form:errors path="SacEstantes.idSacEstante" cssClass="alert alert-danger"/>
                            </small>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-2 col-form-label"><spring:message  code="manage.folders.name.folder"/></label>
                        <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <form:select class="form-control form-control-lg" path="SacTiposCarpetas.idSacTipoCarpeta" id="tipoCarpeta">
                                <c:forEach var="tc" items="${requestScope.lTiposCarpetas}">
                                    <form:option value="${tc.idSacTipoCarpeta}">${tc.sacTipoCarpeta}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <a class="btn add" href="#" data-toggle="modal" data-target="#formSacTipoCarpetas"><i class="fa fa-plus-square-o" aria-hidden="true"></i>  <spring:message  code="people.add"/></a></small>
                            <small id="ci" class="form-text text-muted formFieldError">
                                <form:errors path="SacTiposCarpetas.idSacTipoCarpeta" cssClass="alert alert-danger"/>
                            </small>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="manage.folders.code"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <spring:message  code="manage.folders.code.msg" var="nom"/>
                            <form:input type="text" path="sacCodigoCarpeta"  class="form-control form-control-lg"  placeholder="${nom}"/> 
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <spring:message  code="manage.folders.code.msg"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="sacCodigoCarpeta" cssClass="alert alert-danger" />
                            </small>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-2 col-form-label"><spring:message  code="manage.folders.month"/></label>
                        <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <form:select class="form-control form-control-lg" path="SisMeses.idMes" >
                                <c:forEach var="m" items="${requestScope.lMeses}">
                                    <form:option value="${m.idMes}">${m.mes}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <spring:message  code="manage.folders.month.msg"/>
                            </small>
                            <small id="ci" class="form-text text-muted formFieldError">
                                <form:errors path="SisMeses.idMes" cssClass="alert alert-danger"/>
                            </small>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="manage.folders.number"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <spring:message  code="manage.folders.number.msg" var="nom"/>
                            <form:input type="number" path="sacNumeroCarpeta"  class="form-control form-control-lg"  placeholder="${nom}"/> 
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <spring:message  code="manage.folders.number.msg"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="sacNumeroCarpeta" />
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <spring:message  code="items.year" var="year"/>
                        <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="items.year"/></label>
                        <label class="col-sm-1 col-form-label text-right" > </label>
                        <div class="col-sm-6" id="combo1"> 
                            <form:input class="form-control form-control-lg" path="gestion"  placeholder="${year}"  />
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
                            <a href="<spring:url value="/aCarpetas/inicioCarpetas"/>" class="btn btn-yvela-green"><i class="fa fa-undo yvela" aria-hidden="true"></i>  <spring:message  code="expenses.btn.cancel"/></a>
                        </div> 
                    </div>
                </form:form>
                <br><br>
                <hr align="left" noshade="noshade" size="10" width="100%" />
                -->
            </c:when>
        </c:choose>
        <!------------------------------------ Final Modificar carpetas------------------------------------------- -->

        <!--------------- Inicio Eliminar Carpetas----------------------------------- -->
        <c:choose>
            <c:when test="${operation eq 'elimC' }">
               <!--
                <div id="" class="col-lg-12" >
                    <center><br>
                        <p class="h3"><i class="fa fa-folder" aria-hidden="true"></i>  <spring:message  code="manage.folders.remove"/></p> 
                        <br>
                    </center>
                    <div class="form-group row">
                        <div class="col-sm-8" id="combo1">   
                            <spring:url var="formUrl" value="listarPersonas" />

                            <form:form modelAttribute="sacCarpetas" action="${confElimCar}" method="post">

                                <form:hidden  path="idSacCarpeta" value="${sacCarpetas.idSacCarpeta}" />

                                <div class="form-group row">
                                    <label for="ciExpedido" class="col-sm-4 col-form-label"><spring:message  code="manage.folders.bookcase"/></label>
                                    <div class="col-sm-8">
                                        <form:select class="form-control form-control-lg" path="SacEstantes.idSacEstante"  readonly="true">
                                            <c:forEach var="e" items="${requestScope.lEstantes}">
                                                <form:option value="${e.idSacEstante}">${e.sacNombreEstante}</form:option>
                                            </c:forEach>
                                        </form:select>
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <label for="ciExpedido" class="col-sm-4 col-form-label"><spring:message  code="manage.folders.name.folder"/></label>
                                    <div class="col-sm-8">
                                        <form:select class="form-control form-control-lg" path="SacTiposCarpetas.idSacTipoCarpeta" readonly="true">
                                            <c:forEach var="tc" items="${requestScope.lTiposCarpetas}">
                                                <form:option value="${tc.idSacTipoCarpeta}">${tc.sacTipoCarpeta}</form:option>
                                            </c:forEach>
                                        </form:select>
                                    </div>

                                </div>

                                <div class="form-group row">
                                    <label for="codigo" class="col-sm-4 col-form-label"><spring:message  code="manage.folders.code"/></label>
                                    <div class="col-sm-8">
                                        <spring:message  code="manage.folders.code.msg" var="nom"/>
                                        <form:input type="text" path="sacCodigoCarpeta"  class="form-control form-control-lg"  placeholder="${nom}" readonly="true"/> 
                                    </div>

                                </div>
                                <div class="form-group row">
                                    <label for="ciExpedido" class="col-sm-4 col-form-label"><spring:message  code="manage.folders.month"/></label>
                                    <div class="col-sm-8">
                                        <form:select class="form-control form-control-lg" path="SisMeses.idMes" readonly="true">
                                            <c:forEach var="m" items="${requestScope.lMeses}">
                                                <form:option value="${m.idMes}">${m.mes}</form:option>
                                            </c:forEach>
                                        </form:select>
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <label for="codigo" class="col-sm-4 col-form-label"><spring:message  code="manage.folders.number"/></label>
                                    <div class="col-sm-8">
                                        <spring:message  code="manage.folders.number.msg" var="nom"/>
                                        <form:input type="number" path="sacNumeroCarpeta"  class="form-control form-control-lg"  placeholder="${nom}" readonly="true"/> 
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <spring:message  code="items.year" var="year"/>
                                    <label for="sedes" class="col-sm-4 col-form-label"><spring:message  code="items.year"/></label>
                                    <div class="col-sm-8" id="combo1"> 
                                        <form:input class="form-control form-control-lg" path="gestion"  placeholder="${year}"  readonly="true"/>
                                    </div>

                                </div>
                                <div class="form-group row">
                                    <spring:message  code="items.period.msg" var="period"/>
                                    <label for="sedes" class="col-sm-4 col-form-label"><spring:message  code="items.period"/></label>
                                    <div class="col-sm-8" id="combo1"> 
                                        <form:input class="form-control form-control-lg" path="periodo"  value="${periodo}" readonly="true"/>
                                    </div>
                                </div>

                                <hr align="left" noshade="noshade" size="10" width="100%" />
                                <div class="form-group row mt-12">
                                    <div class="col-sm-3" id="combo1"> 
                                    </div>
                                    <div class="col-sm-3" id="combo1">
                                        <span class="icon-input-btn"><i class="fa fa-times yvela" aria-hidden="true"></i> 
                                            <input type="submit"  class="btn btn-danger" value="<spring:message  code="expenses.btn.confirm"/>">
                                        </span>
                                    </div>

                                    <div class="col-sm-3" id="combo1"> 
                                    </div>
                                    <div class="col-sm-3" id="combo1"> 
                                        <a href="<spring:url value="/aCarpetas/inicioCarpetas"/>" class="btn btn-yvela-green"><i class="fa fa-undo" aria-hidden="true"></i>  <spring:message  code="expenses.btn.cancel"/></a>
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
                <br><br>
                <hr align="left" noshade="noshade" size="10" width="100%" />
               -->
            </c:when>
        </c:choose>
        <!------------------------------------ Final Eliminar Personas------------------------------------------- -->
      
        <!--Tabla lista de Todas las Personas Registradas -->
        <c:choose>
            <c:when test="${busqueda eq 'find' }">

                <center><br>
                    <p class="h3"><i class="fa fa-folder-open-o" aria-hidden="true"></i> <spring:message  code="contracts.report.contracts.title"/></p> 
                    <br>
                </center>

             <!--
                <table  id="tCarpetas"  class="table" style="width:100%">
                    <thead>
                        <tr>
                            <th><spring:message  code="manage.folders.code"/></th>
                            <th><spring:message  code="manage.folders.name.folder"/></th>
                            <th><spring:message  code="manage.folders.bookcase"/></th>
                            <th><spring:message  code="manage.folders.month"/></th>
                            <th><spring:message  code="manage.folders.number"/></th>
                            <th><spring:message  code="ingreso.gestion"/></th>
                            <th><spring:message  code="expenses.modify"/> </th>
                            <th><spring:message  code="expenses.remove"/></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:set var="cont" value="0" />
                        <c:forEach items="${lCarpetas}" var="c">
                            <c:set var="cont" value="${cont+1}" />
                            <tr>
                                <td>${c.sacCodigoCarpeta}</td>
                                <td>${c.sacTiposCarpetas.sacTipoCarpeta}</td>
                                <td>${c.sacEstantes.sacNombreEstante} </td>
                                <td>${c.sisMeses.mes}</td>
                                <td>${c.sacNumeroCarpeta}</td>
                                <td>${c.gestion}</td>
                                <td>
                                    <form  method="post" action="${urlModCar}">
                                        <input type="hidden" name="idSacCarpeta" value="${c.idSacCarpeta}">
                                        <span class="icon-input-btn"> <i class="fa fa-pencil-square-o yvela" aria-hidden="true"> </i> <input class="btn btn-success" type="submit"  value="<spring:message  code="expenses.modify"/>" > </span>
                                    </form>
                                </td>
                                <td>
                                    <form name="Delet" action="${iniElimCar}" method="post">
                                        <input type="hidden" name="idSacCarpeta" value="${c.idSacCarpeta}">
                                        <span class="icon-input-btn"> <i class="fa fa-times yvela" aria-hidden="true"> </i> <input class="btn btn-danger" type="submit"  value="<spring:message  code="expenses.remove"/>"> </span>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                    <tfoot>
                        <tr>
                            <th><spring:message  code="manage.folders.code"/></th>
                            <th><spring:message  code="manage.folders.name.folder"/></th>
                            <th><spring:message  code="manage.folders.bookcase"/></th>
                            <th><spring:message  code="manage.folders.month"/></th>
                            <th><spring:message  code="manage.folders.number"/></th>
                            <th><spring:message  code="ingreso.gestion"/></th>
                            <th><spring:message  code="expenses.modify"/> </th>
                            <th><spring:message  code="expenses.remove"/></th>
                        </tr>
                    </tfoot>
                </table>
                <br><br>
                <hr align="left" noshade="noshade" size="10" width="100%" />
              -->
            </c:when>
        </c:choose>
    </section>
</div>
<!--
<!--Modal Estantes  -->
<div id="formEstantes" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form name="sacEstantesForm" action="guardarSacEstantes" role="form" id="sacEstantesForm" method="post">
                <div class="modal-header">
                    <p class="h3"><i class="fa fa-id-card" aria-hidden="true"></i>  <spring:message  code="manage.shelves.register"/></p>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                </div>
                <div class="modal-body">
                    <div class="form-group row">
                        <spring:message  code="manage.shelves.name.msg" var="issued"/>
                        <label for="expedido" class="col-sm-3 col-form-label"><spring:message  code="manage.shelves.name"/></label>
                        <label for="ciExpedido" class="col-sm-1 col-form-label"><i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-8">
                            <input type="text" name="sacNombreEstante" class="form-control form-control-lg"  placeholder="${issued}" />
                        </div>
                    </div>
                    <div class="form-group row">
                        <spring:message  code="manage.shelves.code.msg" var="initials"/>
                        <label for="espedidoSigla" class="col-sm-3 col-form-label"><spring:message  code="manage.shelves.code"/></label>
                        <label for="ciExpedidoSigla" class="col-sm-1 col-form-label"><i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-8">
                            <input type="text" name="sacCodigoEstante" class="form-control form-control-lg"  placeholder="${initials}" />
                        </div>
                    </div>

                    <div class="form-group row">
                        <spring:message  code="manage.shelves.number.msg" var="initials"/>
                        <label for="espedidoSigla" class="col-sm-3 col-form-label"><spring:message  code="manage.shelves.number"/></label>
                        <label for="ciExpedidoSigla" class="col-sm-1 col-form-label"><i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-8">
                            <input type="number" name="sacNumeroEstante" class="form-control form-control-lg"  placeholder="${initials}" />
                        </div>
                    </div>
                </div>
                <div class="modal-footer">  
                    <button class="btn btn-outline-secondary btn-lg" data-dismiss="modal" aria-hidden="true"><i class="fa fa-undo" aria-hidden="true"></i>  <spring:message  code="people.cancel"/></button>
                    <button type="submit" class="btn btn-success btn-lg float-right" id="btnSacEstantes" data-dismiss="modal" aria-hidden="true"><i class="fa fa-plus-square-o" aria-hidden="true"></i>   <spring:message  code="people.add"/></button>
                </div>
            </form>
        </div>
    </div>
</div>

<!--Registrar Tipos Carpetas  -->
<div id="formSacTipoCarpetas" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form name="sacTiposCarpetasForm" action="guardarSacTiposCarpetas" role="form" id="sacTiposCarpetasForm" method="post">
                <div class="modal-header">
                    <p class="h3"><i class="fa fa-id-card" aria-hidden="true"></i>  <spring:message  code="manage.folders.type.register"/></p>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                </div>
                <div class="modal-body">
                    <div class="form-group row">
                        <spring:message  code="manage.folders.name.folder" var="issued"/>
                        <label for="expedido" class="col-sm-3 col-form-label"><spring:message  code="manage.folders.name.folder"/></label>
                        <label for="ciExpedido" class="col-sm-1 col-form-label"><i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-8">
                            <input type="text" name="sacTipoCarpeta" class="form-control form-control-lg"  placeholder="${issued}" />
                        </div>
                    </div>

                </div>
                <div class="modal-footer">  
                    <button class="btn btn-outline-secondary btn-lg" data-dismiss="modal" aria-hidden="true"><i class="fa fa-undo" aria-hidden="true"></i>  <spring:message  code="people.cancel"/></button>
                    <button type="submit" class="btn btn-success btn-lg float-right" id="btnSacTiposCarpetas" data-dismiss="modal" aria-hidden="true"><i class="fa fa-plus-square-o" aria-hidden="true"></i>   <spring:message  code="people.add"/></button>
                </div>
            </form>
        </div>
    </div>
</div>

-->