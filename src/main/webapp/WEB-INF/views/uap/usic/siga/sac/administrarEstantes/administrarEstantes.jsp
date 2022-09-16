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
                <p class="h1"><i class="fa fa-archive" aria-hidden="true"></i>  <spring:message code="manage.shelves.title"/> </p>
            </div>
            <div class="col-sm-2 text-right" id="reporte">
                <c:choose>
                    <c:when test="${busqueda eq 'find' }">
                        <form  method="POST" action="${urlNEst}">
                            <span class="icon-input-btn"><i class="fa fa-archive yvela" aria-hidden="true"></i> <input type="submit" class="btn btn-success btn-lg" value="<spring:message  code="income.btn.new"/>"></span>
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

        <!--------------- Inicio registro nuevo estante-->
        <c:choose>
            <c:when test="${operation eq 'reg' }">
                <center><br>
                    <p class="h3"><i class="fa fa-archive" aria-hidden="true"></i>  <spring:message  code="manage.shelves.register"/></p> 
                    <br>
                </center>

                <form:form modelAttribute="sacEstantes" action="${regEstantes}" method="post">

                    <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="manage.shelves.name"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <spring:message  code="manage.shelves.name.msg" var="nom"/>
                            <form:input type="text" path="sacNombreEstante"  class="form-control form-control-lg"  placeholder="${nom}"/> 
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <spring:message  code="manage.shelves.name.msg"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="sacNombreEstante" />
                            </small>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="manage.shelves.code"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <spring:message  code="manage.shelves.code.msg" var="nom"/>
                            <form:input type="text" path="sacCodigoEstante"  class="form-control form-control-lg"  placeholder="${nom}"/> 
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <spring:message  code="manage.shelves.code.msg"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="sacCodigoEstante" />
                            </small>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="manage.shelves.number"/></label>
                        <label class="col-sm-1 col-form-label text-right" > </label>
                        <div class="col-sm-6">
                            <spring:message  code="manage.shelves.number.msg" var="nom"/>
                            <form:input type="number" path="sacNumeroEstante"  class="form-control form-control-lg"  placeholder="${nom}"/> 
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <spring:message  code="manage.shelves.number.msg"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="sacNumeroEstante"  />
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
                            <a href="<spring:url value="/aEstantes/inicioEstantes"/>" class="btn btn-yvela-green"><i class="fa fa-undo yvela" aria-hidden="true"></i>  <spring:message  code="expenses.btn.cancel"/></a>
                        </div> 
                    </div>
                </form:form>
                <br><br>
                <hr align="left" noshade="noshade" size="10" width="100%" />

            </c:when>
        </c:choose>
        <!-- Final registro estantes -->

        <!--------------- Inicio Modificar carpetas----------------------------------- -->
        <c:choose>
            <c:when test="${operation eq 'mod' }">

                <center><br>
                    <p class="h3"><i class="fa fa-archive" aria-hidden="true"></i> <spring:message  code="manage.shelves.modify"/></p> 
                    <br>
                </center>

                <form:form modelAttribute="sacEstantes" action="${conModEst}" method="post">
                    <form:hidden  path="idSacEstante" value="${sacEstantes.idSacEstante}" />

                    <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="manage.shelves.name"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <spring:message  code="manage.shelves.name.msg" var="nom"/>
                            <form:input type="text" path="sacNombreEstante"  class="form-control form-control-lg"  placeholder="${nom}"/> 
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <spring:message  code="manage.shelves.name.msg"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="sacNombreEstante" />
                            </small>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="manage.shelves.code"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <spring:message  code="manage.shelves.code.msg" var="nom"/>
                            <form:input type="text" path="sacCodigoEstante"  class="form-control form-control-lg"  placeholder="${nom}"/> 
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <spring:message  code="manage.shelves.code.msg"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="sacCodigoEstante" />
                            </small>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="manage.shelves.number"/></label>
                        <label class="col-sm-1 col-form-label text-right" > </label>
                        <div class="col-sm-6">
                            <spring:message  code="manage.shelves.number.msg" var="nom"/>
                            <form:input type="number" path="sacNumeroEstante"  class="form-control form-control-lg"  placeholder="${nom}"/> 
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <spring:message  code="manage.shelves.number.msg"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="sacNumeroEstante"  />
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
                            <a href="<spring:url value="/aEstantes/inicioEstantes"/>" class="btn btn-yvela-green"><i class="fa fa-undo yvela" aria-hidden="true"></i>  <spring:message  code="expenses.btn.cancel"/></a>
                        </div> 
                    </div>
                </form:form>
                <br><br>
                <hr align="left" noshade="noshade" size="10" width="100%" />

            </c:when>
        </c:choose>
        <!------------------------------------ Final Modificar estantes------------------------------------------- -->

        <!--------------- Inicio Eliminar Estantes----------------------------------- -->
        <c:choose>
            <c:when test="${operation eq 'elimEst' }">
                <div id="" class="col-lg-12" >
                    <center><br>
                        <p class="h3"><i class="fa fa-folder" aria-hidden="true"></i>  <spring:message  code="manage.shelves.remove"/></p> 
                        <br>
                    </center>
                    <div class="form-group row">
                        <div class="col-sm-8" id="combo1">   
                            <spring:url var="formUrl" value="listarPersonas" />
                            <form:form modelAttribute="sacEstantes" action="${confElimEst}" method="post">
                                <form:hidden  path="idSacEstante" value="${sacEstantes.idSacEstante}" />

                                <div class="form-group row">
                                    <label for="codigo" class="col-sm-4 col-form-label"><spring:message  code="manage.shelves.name"/></label>
                                    <div class="col-sm-8">
                                        <spring:message  code="manage.shelves.name.msg" var="nom"/>
                                        <form:input type="text" path="sacNombreEstante"  class="form-control form-control-lg"  placeholder="${nom}"  readonly="true"/> 
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <label for="codigo" class="col-sm-4 col-form-label"><spring:message  code="manage.shelves.code"/></label>
                                    <div class="col-sm-8">
                                        <spring:message  code="manage.shelves.code.msg" var="nom"/>
                                        <form:input type="text" path="sacCodigoEstante"  class="form-control form-control-lg"  placeholder="${nom}" readonly="true"/> 
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <label for="codigo" class="col-sm-4 col-form-label"><spring:message  code="manage.shelves.number"/></label>
                                    <div class="col-sm-8">
                                        <spring:message  code="manage.shelves.number.msg" var="nom"/>
                                        <form:input type="number" path="sacNumeroEstante"  class="form-control form-control-lg"  placeholder="${nom}" readonly="true"/> 
                                    </div>
                                </div>

                                <hr align="left" noshade="noshade" size="10" width="100%" />
                                <div class="form-group row mt-12">
                                    <div class="col-sm-3" id="combo1">
                                    </div> 
                                    <div class="col-sm-3" id="combo1">
                                    </div>
                                    <div class="col-sm-3" id="combo1"> 
                                        <span class="icon-input-btn"><i class="fa fa-times yvela" aria-hidden="true"></i> 
                                            <input type="submit"  class="btn btn-danger" value="<spring:message  code="expenses.btn.confirm"/>">
                                        </span>
                                    </div>
                                    <div class="col-sm-3" id="combo1">
                                        <a href="<spring:url value="/aEstantes/inicioEstantes"/>" class="btn btn-yvela-green"><i class="fa fa-undo yvela" aria-hidden="true"></i>  <spring:message  code="expenses.btn.cancel"/></a>
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
            </c:when>
        </c:choose>
        <!------------------------------------ Final Eliminar estantes------------------------------------------- -->
        <!--Tabla lista de Todas los estantes Registrados -->
        <c:choose>
            <c:when test="${busqueda eq 'find' }">

                <center><br>
                    <p class="h3"><i class="fa fa-file-archive-o" aria-hidden="true"></i> <spring:message  code="manage.shelves.detail"/></p> 
                    <br>
                </center>


                <table  id="tCarpetas"  class="table" style="width:100%">
                    <thead>
                        <tr>
                            <th><spring:message  code="manage.shelves.name"/></th>
                            <th><spring:message  code="manage.shelves.code"/></th>
                            <th><spring:message  code="manage.shelves.number"/></th>
                            <th><spring:message  code="expenses.modify"/> </th>
                            <th><spring:message  code="expenses.remove"/></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:set var="cont" value="0" />
                        <c:forEach items="${lEstantes}" var="e">
                            <c:set var="cont" value="${cont+1}" />
                            <tr>
                                <td>${e.sacNombreEstante}</td>
                                <td>${e.sacCodigoEstante}</td>
                                <td>${e.sacNumeroEstante} </td>

                                <td>
                                    <form  method="post" action="${urlModEst}">
                                        <input type="hidden" name="idSacEstante" value="${e.idSacEstante}">
                                        <span class="icon-input-btn"> <i class="fa fa-pencil-square-o yvela" aria-hidden="true"> </i> <input class="btn btn-success" type="submit"  value="<spring:message  code="expenses.modify"/>" > </span>
                                    </form>
                                </td>
                                <td>
                                    <form name="Delet" action="${iniElimEst}" method="post">
                                        <input type="hidden" name="idSacEstante" value="${e.idSacEstante}">
                                        <span class="icon-input-btn"> <i class="fa fa-times yvela" aria-hidden="true"> </i> <input class="btn btn-danger" type="submit"  value="<spring:message  code="expenses.remove"/>"> </span>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                    <tfoot>
                        <tr>
                            <th><spring:message  code="manage.shelves.name"/></th>
                            <th><spring:message  code="manage.shelves.code"/></th>
                            <th><spring:message  code="manage.shelves.number"/></th>
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

