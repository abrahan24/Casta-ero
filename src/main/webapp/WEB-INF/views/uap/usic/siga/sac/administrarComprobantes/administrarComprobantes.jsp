<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<head>
    <link rel="stylesheet" type="text/css"  href=" <c:url value="/resource/admPersonas/personas.css"/>" />
    <script src="<spring:url value="/resource/sac/admComprob/admComprobss.js" />" type="text/javascript"></script>
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
                <p class="h1"><i class="fa fa-file-archive-o" aria-hidden="true"> </i> <spring:message code="voucher.title"/> </p>
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

        <!--------------- Inicio registro nueva persona-->
        <c:choose>
            <c:when test="${operation eq 'reg' }">
                <center><br>
                    <p class="h3"><i class="fa fa-folder-open" aria-hidden="true"> </i> <spring:message  code="voucher.new"/></p> 
                    <br>
                </center>
                <form:form modelAttribute="sacComprobantes" action="${urlReg}" enctype="multipart/form-data" method="post">
                    <input type="hidden" name="cantBenificiarios" id="cantBenif">
                    <input type="hidden" name="cantNroCheque" id="cantNroCh">
                    <input type="hidden" name="cantNroComprobante" id="cantNroComp">

                    <form:input type="hidden" path="Usuarios.idUsuario" value="${currentUserId}" />
                    <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="manage.folders.bookcase"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  </label>
                        <div class="col-sm-6">
                            <select class="form-control form-control-lg" name="idSacEstante" id="idSacEstante">
                                <c:forEach var="ste" items="${requestScope.lEstantes}">
                                    <option value="${ste.idSacEstante}">${ste.sacNombreEstante}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <small class="form-text text-muted">
                            <spring:message  code="voucher.register.shelves.msg"/>
                        </small>

                    </div>
                    <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-2 col-form-label"><spring:message  code="voucher.folders"/></label>
                        <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <form:select class="form-control form-control-lg" path="SacCarpetas.idSacCarpeta" id="carpeta">
                                <c:forEach var="lcp" items="${requestScope.lCarpetas}">

                                </c:forEach>
                            </form:select>
                        </div>
                        <small class="form-text text-muted">
                            <spring:message  code="voucher.folder.register.msg"/>
                        </small>
                        <small class="msgError">
                            <form:errors path="SacCarpetas.idSacCarpeta" />
                        </small>
                    </div>
                    <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-2 col-form-label"><spring:message  code="voucher.type"/></label>
                        <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <form:select class="form-control form-control-lg" path="SacTiposComprobantes.idSacTipoComprobante" id="tComprobante">
                                <c:forEach var="ltc" items="${requestScope.lTiposComprobantes}">
                                     <c:if  test="${ltc.idSacTipoComprobante == 2}">
                                    <form:option value="${ltc.idSacTipoComprobante}">${ltc.sacTipoComprobante}</form:option>
                                     </c:if>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <a class="btn add" href="#" data-toggle="modal" data-target="#formSacTiposComprobantes"><i class="fa fa-plus-square-o" aria-hidden="true"></i>  <spring:message  code="people.add"/></a></small>
                            <small id="ci" class="form-text text-muted formFieldError">
                                <form:errors path="SacTiposComprobantes.idSacTipoComprobante" cssClass="alert alert-danger"/>
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-2 col-form-label"><spring:message  code="voucher.type.pay"/></label>
                        <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <form:select class="form-control form-control-lg" path="SacTiposPagos.idSacTipoPago" id="tPagos">
                                <c:forEach var="ltp" items="${requestScope.lTiposPagos}">
                                    <form:option value="${ltp.idSacTipoPago}">${ltp.sacTipoPago}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <a class="btn add" href="#" data-toggle="modal" data-target="#formSacTiposPagos"><i class="fa fa-plus-square-o" aria-hidden="true"></i>  <spring:message  code="people.add"/></a>
                            </small>
                            <small id="ci" class="form-text text-muted formFieldError">
                                <form:errors path="SacTiposPagos.idSacTipoPago" cssClass="alert alert-danger"/>
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="voucher.number"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <spring:message  code="voucher.number.msg" var="nom"/>
                            <input  name="sacNroComprobante"  class="form-control form-control-lg"  placeholder="${nom}" id="nroComprobante"/> 
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <button type="button" class="btn add" id="btnNroComprobante" aria-hidden="true"><i class="fa fa-plus-square-o" aria-hidden="true"></i><spring:message  code="people.add"/></button>
                            </small>
                            <small class="msgError">
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="sedes" class="col-sm-2 col-form-label"></label>
                        <label class="col-sm-1 col-form-label text-right" > </label>
                        <div class="col-sm-6" id="combo1"> 
                            <table id="tNroComp" class="table table-striped">
                                <thead>
                                    <tr> 
                                        <th>Nro.</th> 
                                        <th><spring:message  code="voucher.number"/></th>
                                        <th><spring:message  code="expenses.remove"/></th>
                                    </tr> 
                                </thead> 
                                <tbody>
                                </tbody>
                            </table>
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                            <small id="emailHelp" class="form-text text-muted">

                            </small>
                            <small class="msgError">
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <spring:message  code="voucher.check.number.msg" var="check"/>
                        <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="voucher.check.number"/></label>
                        <label class="col-sm-1 col-form-label text-right" > <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6" id="combo1"> 
                            <input type="text" class="form-control form-control-lg" name="sacNroCheque"  id="nroCheque" onkeyUp="return ValNumero(this);" placeholder="${check}" value="" />
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                            <small id="emailHelp" class="form-text text-muted">
                                <button type="button" class="btn add" id="btnNroCheque" aria-hidden="true"><i class="fa fa-plus-square-o" aria-hidden="true"></i><spring:message  code="people.add"/></button>
                            </small>
                            <small class="msgError">

                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="sedes" class="col-sm-2 col-form-label"></label>
                        <label class="col-sm-1 col-form-label text-right" > </label>
                        <div class="col-sm-6" id="combo1"> 
                            <table id="tNroCh" class="table table-striped">
                                <thead>
                                    <tr> 
                                        <th>Nro.</th> 
                                        <th><spring:message  code="voucher.check.number"/></th>
                                        <th><spring:message  code="expenses.remove"/></th>
                                    </tr> 
                                </thead> 
                                <tbody>
                                </tbody>
                            </table>
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                            <small id="emailHelp" class="form-text text-muted">

                            </small>
                            <small class="msgError">
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="voucher.date.elaboration"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <spring:message  code="voucher.date.elaboration.msg" var="nom"/>
                            <form:input type="date" path="fecElaboracion" class="form-control form-control-lg"  placeholder="${nom}" id="fecha" onchange="checkdate(this.value);"/> 
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <spring:message  code="voucher.date.elaboration.msg"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="fecElaboracion" />
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="voucher.roadmap"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <spring:message  code="voucher.roadmap.msg" var="road"/>
                            <form:input type="number" path="hojaRuta"  class="form-control form-control-lg" onkeyUp="return ValNumero(this);" placeholder="${road}"/> 
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <spring:message  code="voucher.roadmap.msg"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="hojaRuta" />
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="voucher.gloss"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <spring:message  code="voucher.gloss.msg" var="gloss"/>
                            <form:textarea  path="glosa" class="form-control form-control-lg" placeholder="${gloss}" rows="3" cols="20" /> 
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <spring:message  code="voucher.gloss.msg"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="glosa" />
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="voucher.rode"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <spring:message  code="voucher.rode.msg" var="rode"/>
                            <form:input  path="sacMonto"  class="form-control form-control-lg"  placeholder="${rode}"/> 
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <spring:message  code="voucher.rode.msg"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="sacMonto" />
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <spring:message  code="items.year" var="year"/>
                        <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="items.year"/></label>
                        <label class="col-sm-1 col-form-label text-right" > <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6" id="combo1"> 
                            <form:input class="form-control form-control-lg" path="gestion"  id="gestion" onkeyUp="return ValNumero(this);" placeholder="${year}" value="${gestion}" />
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                            <small id="emailHelp" class="form-text text-muted">
                                <spring:message  code="items.year.msg"/>
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="msg" class="col-sm-2 col-form-label text-right"></label>
                        <label for="msg" class="col-sm-10 col-form-label"><span class="parpadea text"><i class="fa fa-eye" aria-hidden="true"></i></span> &nbsp;&nbsp;&nbsp;<spring:message  code="people.warning"/> </label>
                    </div>         
                    <div class="form-group row">
                        <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="voucher.Name.CorporateName"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6" id="combo1"> 
                            <select class="form-control form-control-lg" name="idPersona" id="lPersona">
                                <c:forEach var="p" items="${requestScope.lPersonas}">
                                    <option value="${p.idPersona}">${p.nombres} ${p.paterno} ${p.materno}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <a class="btn add" href="#" data-toggle="modal" data-target="#formPersonas"><i class="fa fa-plus-square-o" aria-hidden="true"></i>  <spring:message  code="people.add"/></a>&nbsp&nbsp&nbsp&nbsp
                                <button type="button" class="btn add" id="btnAddRazonSoc" aria-hidden="true"><i class="fa fa-plus-square-o" aria-hidden="true"></i>  <spring:message  code="voucher.btn.add.person"/></button>
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="voucher.Name.CorporateName"/></label>
                        <label class="col-sm-1 col-form-label text-right" > </label>
                        <div class="col-sm-6" id="combo1"> 
                            <table id="tPersona" class="table table-striped">
                                <thead>
                                    <tr> 
                                        <th>Nro.</th> 
                                        <th><spring:message  code="persona.cedula"/></th>
                                        <th><spring:message  code="persona.nombres"/></th>
                                        <th><spring:message  code="expenses.remove"/></th>
                                    </tr> 
                                </thead> 
                                <tbody>

                                </tbody>
                            </table>
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                            <small id="emailHelp" class="form-text text-muted">
                                <spring:message  code="info.login.people"/>
                            </small>
                            <small class="msgError">
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="msg" class="col-sm-2 col-form-label text-right"></label>
                        <label for="msg" class="col-sm-10 col-form-label"><span class="parpadea text"><i class="fa fa-file-pdf-o" aria-hidden="true"></i></span> &nbsp;&nbsp;&nbsp;<spring:message  code="voucher.file.field"/> </label>
                    </div>   
                    <div class="form-group row">
                        <spring:message  code="voucher.attached.file.msg" var="nexo"/>
                        <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="voucher.attached.file"/></label>
                        <label class="col-sm-1 col-form-label text-right" > <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6" id="combo1"> 
                            <form:input type="file" path="file" id="file" class="form-control form-control-lg input-sm"/>
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                            <small id="emailHelp" class="form-text text-muted">
                                <spring:message  code="voucher.attached.file.msg"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="file" />
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <spring:message  code="voucher.file.name.msg" var="adj"/>
                        <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="voucher.file.name"/></label>
                        <label class="col-sm-1 col-form-label text-right" > <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6" id="combo1"> 
                            <form:input class="form-control form-control-lg" path="nombreArchivo"  id="fileName" placeholder="${adj}" value="" />
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                            <small id="emailHelp" class="form-text text-muted">
                                <spring:message  code="voucher.file.name.msg"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="nombreArchivo" />
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
                        <div class="col-sm-3" id="combo1">
                            <a href="<spring:url value="/comprobante/inicioComprobantes"/>" class="btn btn-yvela-green"><i class="fa fa-undo yvela" aria-hidden="true"></i>  <spring:message  code="expenses.btn.cancel"/></a>
                        </div> 
                    </div>
                </form:form>
                <br><br>
                <hr align="left" noshade="noshade" size="10" width="100%" />
            </c:when>
        </c:choose>
        <!-- Final registro comprobantes -->

        <!--Inicio modificar Comprobantes -->
        <c:choose>
            <c:when test="${operation eq 'edit' }">
                <center><br>
                    <p class="h3"><i class="fa fa-pencil-square-o" aria-hidden="true"></i>  <spring:message  code="voucher.modify.form"/></p> 
                    <br>
                </center>
                <form:form modelAttribute="sacComprobantes" action="${urlUp}" enctype="multipart/form-data" method="post">
                    <input type="hidden" name="cantBenificiarios" id="cantBenif">
                    <form:input type="hidden" path="Usuarios.idUsuario" value="${currentUserId}" />
                    <form:input type="hidden" path="idSacComprobante" value="${sacComprobantes.idSacComprobante}" />
                    <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="manage.folders.bookcase"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  </label>
                        <div class="col-sm-6">
                            <select class="form-control form-control-lg" name="idSacEstante" id="idSacEstante">
                                <c:forEach var="ste" items="${requestScope.lEstantes}">
                                    <option value="${ste.idSacEstante}">${ste.sacNombreEstante}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-2 col-form-label"><spring:message  code="voucher.folders"/></label>
                        <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <form:select class="form-control form-control-lg" path="SacCarpetas.idSacCarpeta" id="carpeta">
                                <c:forEach var="lcp" items="${requestScope.lCarpetas}">
                                    <form:option value="${lcp.idSacCarpeta}">${lcp.sacCodigoCarpeta}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-2 col-form-label"><spring:message  code="voucher.type"/></label>
                        <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <form:select class="form-control form-control-lg" path="SacTiposComprobantes.idSacTipoComprobante" id="tComprobante">
                                <c:forEach var="ltc" items="${requestScope.lTiposComprobantes}">
                                    <form:option value="${ltc.idSacTipoComprobante}">${ltc.sacTipoComprobante}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <a class="btn add" href="#" data-toggle="modal" data-target="#formSacTiposComprobantes"><i class="fa fa-plus-square-o" aria-hidden="true"></i>  <spring:message  code="people.add"/></a></small>
                            <small id="ci" class="form-text text-muted formFieldError">
                                <form:errors path="SacTiposComprobantes.idSacTipoComprobante" cssClass="alert alert-danger"/>
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-2 col-form-label"><spring:message  code="voucher.type.pay"/></label>
                        <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <form:select class="form-control form-control-lg" path="SacTiposPagos.idSacTipoPago" id="tPagos">
                                <c:forEach var="ltp" items="${requestScope.lTiposPagos}">
                                    <form:option value="${ltp.idSacTipoPago}">${ltp.sacTipoPago}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <a class="btn add" href="#" data-toggle="modal" data-target="#formSacTiposPagos"><i class="fa fa-plus-square-o" aria-hidden="true"></i>  <spring:message  code="people.add"/></a></small>
                            </small>
                            <small id="ci" class="form-text text-muted formFieldError">
                                <form:errors path="SacTiposPagos.idSacTipoPago" cssClass="alert alert-danger"/>
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="voucher.number"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <spring:message  code="voucher.number.msg" var="nom"/>
                            <input name="sacNroComprobante"  class="form-control form-control-lg"  placeholder="${nom}" id="nroCompEdit"/> 
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <button type="button" class="btn add" id="btnNroComprobanteEdit" aria-hidden="true"><i class="fa fa-plus-square-o" aria-hidden="true"></i><spring:message  code="people.add"/></button>
                            </small>
                            <small class="msgError">
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="voucher.number"/></label>
                        <label class="col-sm-1 col-form-label text-right" ></label>
                        <div class="col-sm-6" id="combo1"> 
                            <table id="tNroCompEdit" class="table table-striped">
                                <thead>
                                    <tr> 
                                        <th>Id</th>
                                        <th><spring:message  code="voucher.number"/></th>
                                        <th><spring:message  code="expenses.modify"/></th>
                                        <th><spring:message  code="expenses.remove"/></th>

                                    </tr> 
                                </thead> 
                                <c:forEach var="lnroComp" items="${requestScope.lNroComprobantes}"  varStatus="status">
                                    <input type="hidden" name="idSacComprobante" value="${lnroComp.sacComprobantes.idSacComprobante}" id="idSacComprobante">
                                    <tr class="nroCompTr${lnroComp.idSacCompNroComprobante}"> 
                                        <td><span id="idNroComp${lnroComp.idSacCompNroComprobante}">${lnroComp.idSacCompNroComprobante}</span></td>
                                        <td><span id="nroComp${lnroComp.idSacCompNroComprobante}">${lnroComp.sacNroComprobante}</span></td>
                                        <td>
                                            <button type="button" class="btn btn-success edit" value="${lnroComp.idSacCompNroComprobante}"><span class="glyphicon glyphicon-edit"></span>M</button>
                                        </td>
                                        <td>
                                            <button type="button" class="btn btn-danger deletNroCom" value="${lnroComp.idSacCompNroComprobante}"><span class="glyphicon glyphicon-edit"></span>X</button>
                                        </td>
                                    </tr> 
                                </c:forEach>

                            </table>
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                            <small id="emailHelp" class="form-text text-muted">
                            </small>
                            <small class="msgError">
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <spring:message  code="voucher.check.number.msg" var="check"/>
                        <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="voucher.check.number"/></label>
                        <label class="col-sm-1 col-form-label text-right" > <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6" id="combo1"> 
                            <input class="form-control form-control-lg" name="sacNumeroCheque"  id="nroChequesEdit" onkeyUp="return ValNumero(this);" placeholder="${check}" value="" />
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <button type="button" class="btn add" id="btnNroChequeAgregarEdit" aria-hidden="true"><i class="fa fa-plus-square-o" aria-hidden="true"></i><spring:message  code="people.add"/></button>
                            </small>
                            <small class="msgError">
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="sedes" class="col-sm-2 col-form-label"></label>
                        <label class="col-sm-1 col-form-label text-right" ></label>
                        <div class="col-sm-6" id="combo1"> 
                            <table id="tNroCh" class="table table-striped">
                                <thead>
                                    <tr> 
                                        <th>id</th>
                                        <th><spring:message  code="voucher.check.number"/></th>
                                        <th><spring:message  code="expenses.modify"/></th>
                                        <th><spring:message  code="expenses.remove"/></th>
                                    </tr> 
                                </thead> 
                                <tbody>
                                    <c:forEach var="lnroCh" items="${requestScope.lNroCheques}"  varStatus="status">
                                    <input type="hidden" name="idSacComprobante" value="${lnroComp.sacComprobantes.idSacComprobante}" id="idSacComprobante">
                                    <tr> 
                                        <td><span id="idNroCheque${lnroCh.idSacCompNroCheque}">${lnroCh.idSacCompNroCheque}</span></td>
                                        <td><span id="nroCheques${lnroCh.idSacCompNroCheque}">${lnroCh.sacNroCheque}</span></td>
                                        <td>
                                            <button type="button" class="btn btn-success editCh" value="${lnroCh.idSacCompNroCheque}"><span class="glyphicon glyphicon-edit"></span> M</button>
                                        </td>
                                        <td>
                                            <button type="button" class="btn btn-danger deletNroCheckee" value="${lnroCh.idSacCompNroCheque}"><span class="glyphicon glyphicon-edit"></span>X</button>
                                        </td>
                                    </tr> 
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                            <small id="emailHelp" class="form-text text-muted">

                            </small>
                            <small class="msgError">
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="voucher.date.elaboration"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <spring:message  code="voucher.date.elaboration.msg" var="nom"/>
                            <form:input type="date" path="fecElaboracion"  class="form-control form-control-lg"  placeholder="${nom}"/> 
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <spring:message  code="voucher.date.elaboration.msg"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="fecElaboracion" />
                            </small>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="voucher.roadmap"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <spring:message  code="voucher.roadmap.msg" var="road"/>
                            <form:input type="number" path="hojaRuta"  class="form-control form-control-lg"  placeholder="${road}"/> 
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <spring:message  code="voucher.roadmap.msg"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="hojaRuta" />
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="voucher.gloss"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <spring:message  code="voucher.gloss.msg" var="gloss"/>
                            <form:textarea  path="glosa" class="form-control form-control-lg" placeholder="${gloss}" rows="3" cols="20" /> 
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <spring:message  code="voucher.gloss.msg"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="glosa" />
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="voucher.rode"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <spring:message  code="voucher.rode.msg" var="rode"/>
                            <form:input  path="sacMonto"  class="form-control form-control-lg"  placeholder="${rode}"/> 
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <spring:message  code="voucher.rode.msg"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="sacMonto" />
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <spring:message  code="items.year" var="year"/>
                        <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="items.year"/></label>
                        <label class="col-sm-1 col-form-label text-right" > <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6" id="combo1"> 
                            <form:input class="form-control form-control-lg" path="gestion"  id="gestion" onkeyUp="return ValNumero(this);" placeholder="${year}" />
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                            <small id="emailHelp" class="form-text text-muted">
                                <spring:message  code="items.year.msg"/>
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="msg" class="col-sm-2 col-form-label text-right"></label>
                        <label for="msg" class="col-sm-10 col-form-label"><span class="parpadea text"><i class="fa fa-eye" aria-hidden="true"></i></span> &nbsp;&nbsp;&nbsp;<spring:message  code="people.warning"/> </label>
                    </div>         
                    <div class="form-group row">
                        <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="voucher.Name.CorporateName"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6" id="combo1"> 
                            <select class="form-control form-control-lg" path="idPersona" id="lPersona">
                                <c:forEach var="p" items="${requestScope.lPersonas}">
                                    <option value="${p.idPersona}">${p.nombres} ${p.paterno} ${p.materno}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                            <small id="emailHelp" class="form-text text-muted">
                                <a class="btn add" href="#" data-toggle="modal" data-target="#formPersonas"><i class="fa fa-plus-square-o" aria-hidden="true"></i>  <spring:message  code="people.add"/></a>&nbsp&nbsp&nbsp&nbsp
                                <button type="button" class="btn add" id="btnAgregarPersonaEdit" aria-hidden="true"><i class="fa fa-plus-square-o" aria-hidden="true"></i>  <spring:message  code="voucher.btn.add.person"/></button>                            </small>
                            <small class="msgError">

                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="voucher.Name.CorporateName"/></label>
                        <label class="col-sm-1 col-form-label text-right" > </label>
                        <div class="col-sm-6" id="combo1"> 
                            <table id="tPersona" class="table table-striped">
                                <thead>
                                    <tr> 
                                        <th>Id</th>
                                        <th><spring:message  code="persona.cedula"/></th>
                                        <th><spring:message  code="persona.nombres"/></th>
                                        <th><spring:message  code="expenses.modify"/></th>
                                        <th><spring:message  code="expenses.remove"/></th>
                                    </tr> 
                                </thead> 
                                <tbody>
                                    <c:forEach var="lrs" items="${requestScope.lRazonS}"  varStatus="status">
                                        <tr>
                                            <td><span id="idPrs${lrs.personas.idPersona}">${lrs.personas.idPersona}</span></td>
                                            <td><span id="ci${lrs.personas.idPersona}">${lrs.personas.ci}</span></td>
                                            <td><span id="nombres${lrs.personas.idPersona}">${lrs.personas.nombres} ${lrs.personas.paterno} ${lrs.personas.materno}</span></td>
                                            <td>
                                                <button type="button" class="btn btn-success editPrs" value="${lrs.personas.idPersona}"><span class="glyphicon glyphicon-edit"></span>M</button>
                                            </td>
                                            <td>
                                                <button type="button" class="btn btn-danger deletRazonSocial" value="${lrs.idSacRazonSocial}"><span class="glyphicon glyphicon-edit"></span>X</button>
                                            </td>
                                        </tr> 
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="msg" class="col-sm-2 col-form-label text-right"></label>
                        <label for="msg" class="col-sm-10 col-form-label"><span class="parpadea text"><i class="fa fa-file-pdf-o" aria-hidden="true"></i></span> &nbsp;&nbsp;&nbsp;<spring:message  code="voucher.file.field"/> </label>
                    </div>   
                    <div class="form-group row">
                        <spring:message  code="voucher.attached.file.msg" var="nexo"/>
                        <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="voucher.attached.file"/></label>
                        <label class="col-sm-1 col-form-label text-right" > <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6" id="combo1"> 
                            <input type="file" name="file" id="file" class="form-control form-control-lg input-sm"/>
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                            <small id="emailHelp" class="form-text text-muted">
                                <spring:message  code="voucher.attached.file.msg"/>
                            </small>
                            <small class="msgError">

                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <spring:message  code="voucher.file.name.msg" var="adj"/>
                        <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="voucher.file.name"/></label>
                        <label class="col-sm-1 col-form-label text-right" > <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6" id="combo1"> 
                            <input class="form-control form-control-lg" name="nombreArchivo"  id="fileName" placeholder="${adj}" value="${arch.nombreArchivo}" />
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                            <small id="emailHelp" class="form-text text-muted">
                            </small>
                            <small class="msgError">
                                <a class="btn add" href="<spring:url value="/comprobante/openFile/${sacComprobantes.idSacComprobante}"/>" target="blank"><i class="fa fa-plus-square-o" aria-hidden="true"></i>  <spring:message  code="voucher.viem.pdf"/></a>
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
                            <span class="icon-input-btn"><i class="fa fa-pencil-square-o yvela" aria-hidden="true"></i> <input class="btn btn-success" type="submit" value="<spring:message  code="expenses.modify"/>"></span>
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                        </div>
                        <div class="col-sm-3" id="combo1">
                            <a href="<spring:url value="/comprobante/inicioComprobantes"/>" class="btn btn-yvela-green"><i class="fa fa-undo yvela" aria-hidden="true"></i>  <spring:message  code="expenses.btn.cancel"/></a>
                        </div> 
                    </div>
                </form:form>
                <br><br>    
                <hr align="left" noshade="noshade" size="10" width="100%" />
            </c:when>
        </c:choose>

        <!------------------------------------ Final Modificar Personas------------------------------------------- -->

        <!------------------------------------ Inicio Eliminar Personas------------------------------------------- -->
        <c:choose>
            <c:when test="${operation eq 'deletVoucher' }">
                <div id="" class="col-lg-12" >
                    <br>
                    <center><h5><i class="fa fa-trash-o" aria-hidden="true"></i>  <spring:message  code="voucher.title.remove.form"/></h5></center>
                    <br>
                    <div class="form-group row">
                        <div class="col-sm-8" id="combo1"> 
                            <form:form modelAttribute="sacComprobantes" action="${urlDel}" enctype="multipart/form-data" method="post">
                                <form:input type="hidden" path="Usuarios.idUsuario" value="${currentUserId}" />
                                <form:input type="hidden" path="idSacComprobante" value="${sacComprobantes.idSacComprobante}" />
                                <div class="form-group row">
                                    <label for="codigo" class="col-sm-4 col-form-label"><spring:message  code="manage.folders.bookcase"/></label>
                                    <div class="col-sm-8">
                                        <select class="form-control form-control-lg" name="idSacEstante" readonly="true">
                                            <c:forEach var="ste" items="${requestScope.lEstantes}">
                                                <option value="${ste.idSacEstante}">${ste.sacNombreEstante}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="ciExpedido" class="col-sm-4 col-form-label"><spring:message  code="voucher.folders"/></label>
                                    <div class="col-sm-8">
                                        <form:select class="form-control form-control-lg" path="SacCarpetas.idSacCarpeta" readonly="true">
                                            <c:forEach var="lcp" items="${requestScope.lCarpetas}">
                                                <form:option value="${lcp.idSacCarpeta}">${lcp.sacCodigoCarpeta}</form:option>
                                            </c:forEach>
                                        </form:select>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="ciExpedido" class="col-sm-4 col-form-label"><spring:message  code="voucher.type"/></label>
                                    <div class="col-sm-8">
                                        <form:select class="form-control form-control-lg" path="SacTiposComprobantes.idSacTipoComprobante" readonly="true">
                                            <c:forEach var="ltc" items="${requestScope.lTiposComprobantes}" >
                                                <form:option value="${ltc.idSacTipoComprobante}">${ltc.sacTipoComprobante}</form:option>
                                            </c:forEach>
                                        </form:select>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="ciExpedido" class="col-sm-4 col-form-label"><spring:message  code="voucher.type.pay"/></label>
                                    <div class="col-sm-8">
                                        <form:select class="form-control form-control-lg" path="SacTiposPagos.idSacTipoPago" readonly="true">
                                            <c:forEach var="ltp" items="${requestScope.lTiposPagos}">
                                                <form:option value="${ltp.idSacTipoPago}">${ltp.sacTipoPago}</form:option>
                                            </c:forEach>
                                        </form:select>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="codigo" class="col-sm-4 col-form-label"><spring:message  code="voucher.number"/></label>
                                    <div class="col-sm-8">
                                        <spring:message  code="voucher.number.msg" var="nom"/>
                                        <input name="sacNroComprobante"  class="form-control form-control-lg"  placeholder="${nom}" id="nroCompEdit" readonly="true"/> 
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="sedes" class="col-sm-4 col-form-label"><spring:message  code="voucher.number"/></label>
                                    <div class="col-sm-8" id="combo1"> 
                                        <table id="tNroCompEdit" class="table table-striped">
                                            <thead>
                                                <tr> 
                                                    <th>Id</th>
                                                    <th><spring:message  code="voucher.number"/></th>
                                                    <th><spring:message  code="expenses.modify"/></th>
                                                    <th><spring:message  code="expenses.remove"/></th>

                                                </tr> 
                                            </thead> 
                                            <c:forEach var="lnroComp" items="${requestScope.lNroComprobantes}"  varStatus="status">
                                                <input type="hidden" name="idSacComprobante" value="${lnroComp.sacComprobantes.idSacComprobante}" id="idSacComprobante">
                                                <tr class="nroCompTr${lnroComp.idSacCompNroComprobante}"> 
                                                    <td><span id="idNroComp${lnroComp.idSacCompNroComprobante}">${lnroComp.idSacCompNroComprobante}</span></td>
                                                    <td><span id="nroComp${lnroComp.idSacCompNroComprobante}">${lnroComp.sacNroComprobante}</span></td>
                                                    <td>
                                                        <!-- <button type="button" class="btn btn-success edit" value="${lnroComp.idSacCompNroComprobante}"><span class="glyphicon glyphicon-edit"></span>M</button> -->
                                                    </td>
                                                    <td>
                                                        <!-- <button type="button" class="btn btn-danger deletNroCom" value="${lnroComp.idSacCompNroComprobante}"><span class="glyphicon glyphicon-edit"></span>X</button> -->
                                                    </td>
                                                </tr> 
                                            </c:forEach>

                                        </table>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <spring:message  code="voucher.check.number.msg" var="check"/>
                                    <label for="sedes" class="col-sm-4 col-form-label"><spring:message  code="voucher.check.number"/></label>
                                    <div class="col-sm-8" id="combo1"> 
                                        <input class="form-control form-control-lg" name="sacNumeroCheque"  id="nroChequesEdit" onkeyUp="return ValNumero(this);" placeholder="${check}" value="" readonly="true"/>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="sedes" class="col-sm-4 col-form-label"></label>
                                    <div class="col-sm-8" id="combo1"> 
                                        <table id="tNroCh" class="table table-striped">
                                            <thead>
                                                <tr> 
                                                    <th>id</th>
                                                    <th><spring:message  code="voucher.check.number"/></th>
                                                    <th><spring:message  code="expenses.modify"/></th>
                                                    <th><spring:message  code="expenses.remove"/></th>
                                                </tr> 
                                            </thead> 
                                            <tbody>
                                                <c:forEach var="lnroCh" items="${requestScope.lNroCheques}"  varStatus="status">
                                                <input type="hidden" name="idSacComprobante" value="${lnroComp.sacComprobantes.idSacComprobante}" id="idSacComprobante">
                                                <tr> 
                                                    <td><span id="idNroCheque${lnroCh.idSacCompNroCheque}">${lnroCh.idSacCompNroCheque}</span></td>
                                                    <td><span id="nroCheques${lnroCh.idSacCompNroCheque}">${lnroCh.sacNroCheque}</span></td>
                                                    <td>
                                                       <!-- <button type="button" class="btn btn-success editCh" value="${lnroCh.idSacCompNroCheque}"><span class="glyphicon glyphicon-edit"></span> M</button> -->
                                                    </td>
                                                    <td>
                                                       <!-- <button type="button" class="btn btn-danger deletNroCheckee" value="${lnroCh.idSacCompNroCheque}"><span class="glyphicon glyphicon-edit"></span>X</button> -->
                                                    </td>
                                                </tr> 
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="codigo" class="col-sm-4 col-form-label"><spring:message  code="voucher.date.elaboration"/></label>
                                    <div class="col-sm-8">
                                        <spring:message  code="voucher.date.elaboration.msg" var="nom"/>
                                        <form:input type="date" path="fecElaboracion"  class="form-control form-control-lg"  placeholder="${nom}" readonly="true"/> 
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <label for="codigo" class="col-sm-4 col-form-label"><spring:message  code="voucher.roadmap"/></label>
                                    <div class="col-sm-8">
                                        <spring:message  code="voucher.roadmap.msg" var="road"/>
                                        <form:input type="number" path="hojaRuta"  class="form-control form-control-lg"  placeholder="${road}" readonly="true"/> 
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="codigo" class="col-sm-4 col-form-label"><spring:message  code="voucher.gloss"/></label>
                                    <div class="col-sm-8">
                                        <spring:message  code="voucher.gloss.msg" var="gloss"/>
                                        <form:textarea  path="glosa" class="form-control form-control-lg" placeholder="${gloss}" rows="3" cols="20" readonly="true"/> 
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="codigo" class="col-sm-4 col-form-label"><spring:message  code="voucher.rode"/></label>
                                    <div class="col-sm-8">
                                        <spring:message  code="voucher.rode.msg" var="rode"/>
                                        <form:input  path="sacMonto"  class="form-control form-control-lg"  placeholder="${rode}" readonly="true"/> 
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <spring:message  code="items.year" var="year"/>
                                    <label for="sedes" class="col-sm-4 col-form-label"><spring:message  code="items.year"/></label>
                                    <div class="col-sm-8" id="combo1"> 
                                        <form:input class="form-control form-control-lg" path="gestion"  id="gestion" onkeyUp="return ValNumero(this);" placeholder="${year}" value="${gestion}" readonly="true"/>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="msg" class="col-sm-2 col-form-label text-right"></label>
                                    <label for="msg" class="col-sm-10 col-form-label"><span class="parpadea text"><i class="fa fa-eye" aria-hidden="true"></i></span> &nbsp;&nbsp;&nbsp;<spring:message  code="people.warning"/> </label>
                                </div>         
                                <div class="form-group row">
                                    <label for="sedes" class="col-sm-4 col-form-label"><spring:message  code="voucher.Name.CorporateName"/></label>
                                    <div class="col-sm-8" id="combo1"> 
                                        <select class="form-control form-control-lg" path="idPersona" readonly="true">
                                            <c:forEach var="p" items="${requestScope.lPersonas}">
                                                <option value="${p.idPersona}">${p.nombres} ${p.paterno} ${p.materno}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="sedes" class="col-sm-4 col-form-label"><spring:message  code="voucher.Name.CorporateName"/></label>
                                    <div class="col-sm-8" id="combo1"> 
                                        <table id="tPersona" class="table table-striped">
                                            <thead>
                                                <tr> 
                                                    <th>Id</th>
                                                    <th><spring:message  code="persona.cedula"/></th>
                                                    <th><spring:message  code="persona.nombres"/></th>
                                                    <th><spring:message  code="expenses.modify"/></th>
                                                    <th><spring:message  code="expenses.remove"/></th>
                                                </tr> 
                                            </thead> 
                                            <tbody>
                                                <c:forEach var="lrs" items="${requestScope.lRazonS}"  varStatus="status">
                                                    <tr>
                                                        <td><span id="idPrs${lrs.personas.idPersona}">${lrs.personas.idPersona}</span></td>
                                                        <td><span id="ci${lrs.personas.idPersona}">${lrs.personas.ci}-${lrs.idSacRazonSocial}</span></td>
                                                        <td><span id="nombres${lrs.personas.idPersona}">${lrs.personas.nombres} ${lrs.personas.paterno} ${lrs.personas.materno}</span></td>
                                                        <td>
                                                            <!-- <button type="button" class="btn btn-success editPrs" value="${lrs.personas.idPersona}"><span class="glyphicon glyphicon-edit"></span>M</button> -->
                                                        </td>
                                                        <td>
                                                             <!-- <button type="button" class="btn btn-danger deletRazonSocial" value="${lrs.idSacRazonSocial}"><span class="glyphicon glyphicon-edit"></span>X</button> -->
                                                        </td>
                                                    </tr> 
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="msg" class="col-sm-2 col-form-label text-right"></label>
                                    <label for="msg" class="col-sm-10 col-form-label"><span class="parpadea text"><i class="fa fa-file-pdf-o" aria-hidden="true"></i></span> &nbsp;&nbsp;&nbsp;<spring:message  code="voucher.file.field"/> </label>
                                </div>   
                                <div class="form-group row">
                                    <spring:message  code="voucher.attached.file.msg" var="nexo"/>
                                    <label for="sedes" class="col-sm-4 col-form-label"><spring:message  code="voucher.attached.file"/></label>
                                    <div class="col-sm-8" id="combo1"> 
                                        <input type="file" name="file" id="file" class="form-control form-control-lg input-sm" readonly="true"/>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <spring:message  code="voucher.file.name.msg" var="adj"/>
                                    <label for="sedes" class="col-sm-4 col-form-label"><spring:message  code="voucher.file.name"/></label>
                                    <div class="col-sm-8" id="combo1"> 
                                        <input class="form-control form-control-lg" name="nombreArchivo"  id="fileName" placeholder="${adj}" value="${arch.nombreArchivo}" readonly="true" />
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
                                                <a href="<spring:url value="/comprobante/inicioComprobantes"/>" class="btn btn-yvela-green"><i class="fa fa-undo" aria-hidden="true"></i>  <spring:message  code="expenses.btn.cancel"/></a>
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

            <!--Tabla lista de Todos los Comprobantes Egreso Registrados -->
            <c:choose>
                <c:when test="${busqueda eq 'find' }">
                    <center><br>
                        <p class="h3"><i class="fa fa-folder-open-o" aria-hidden="true"></i> <spring:message  code="voucher.title.details.list"/></p> 
                        <br>
                    </center>
                    <table id="tComprobantes"  class="table" style="width:100%">
                        <thead>
                            <tr>
                                <th><spring:message  code="voucher.number"/></th>
                                <th><spring:message  code="voucher.check.number"/></th>
                                <th><spring:message  code="voucher.Name.CorporateName"/></th>
                                <th><spring:message  code="voucher.gloss"/></th>
                                <th><spring:message  code="voucher.viem.pdf"/></th>
                                <th><spring:message  code="expenses.modify"/> </th>
                                <th><spring:message  code="expenses.remove"/></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:set var="cont" value="0" />
                            <c:forEach items="${lComprobantes}" var="lSac">
                                 <c:if  test="${lSac.sacTiposComprobantes.idSacTipoComprobante == 2}">
                                <c:set var="cont" value="${cont+1}" />
                                <tr>
                                    <td>
                                        <c:forEach var="nc" items="${requestScope.lSacNumComp}">
                                            <c:if  test="${lSac.idSacComprobante == nc.sacComprobantes.idSacComprobante}">
                                                 ${nc.sacNroComprobante} -
                                            </c:if>
                                        </c:forEach>
                                    </td>
                                    <td>
                                        <c:forEach items="${lNroCheque}" var="lNch">
                                            <c:if test="${lNch.sacComprobantes.idSacComprobante == lSac.idSacComprobante}">
                                                ${lNch.sacNroCheque} - 
                                            </c:if>
                                        </c:forEach>
                                    </td>
                                    <td>
                                        <c:forEach items="${lRazonSocial}" var="lRsoc">
                                            <c:if test="${lRsoc.sacComprobantes.idSacComprobante == lSac.idSacComprobante}">
                                                ${lRsoc.personas.nombres} ${lRsoc.personas.paterno} ${lRsoc.personas.materno}  - 
                                            </c:if>
                                        </c:forEach>
                                    </td>
                                    <td>${lSac.glosa}</td>
                                    <td>
                                        <a class="btn add" href="<spring:url value="/comprobante/openFile/${lSac.idSacComprobante}"/>" target="blank"><i class="fa fa-plus-square-o" aria-hidden="true"></i>  <spring:message  code="voucher.viem.pdf"/></a>
                                    </td>
                                    <td>
                                        <form  method="post" action="${urlM}">
                                            <input type="hidden" name="idSacComprobante" value="${lSac.idSacComprobante}">
                                            <span class="icon-input-btn"> <i class="fa fa-pencil-square-o yvela" aria-hidden="true"> </i> <input class="btn btn-success" type="submit"  value="<spring:message  code="expenses.modify"/>" > </span>
                                        </form>
                                    </td>
                                    <td>
                                        <form name="Delet" action="${urlD}" method="post">
                                            <input type="hidden" name="idSacComprobante" value="${lSac.idSacComprobante}">
                                            <span class="icon-input-btn"> <i class="fa fa-times yvela" aria-hidden="true"> </i> <input class="btn btn-danger" type="submit"  value="<spring:message  code="expenses.remove"/>"> </span>
                                        </form>
                                    </td>
                                </tr>
                                 </c:if>
                            </c:forEach>
                        </tbody>
                        <tfoot>
                            <tr>
                                <th><spring:message  code="voucher.number"/></th>
                                <th><spring:message  code="voucher.check.number"/></th>
                                <th><spring:message  code="voucher.Name.CorporateName"/></th>
                                <th><spring:message  code="voucher.gloss"/></th>
                                <th><spring:message  code="voucher.viem.pdf"/></th>
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
<!-- Modal Visualizar PDF -->
<div id="modalPdf" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <p class="h3"><i class="fa fa-user-plus yvela" aria-hidden="true"></i>  <spring:message  code="voucher.register.payment.type"/></p>
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            </div>
            <div class="modal-body">
                <div id="portapdf">
                    <object data="data:application/pdf;base64,${pdf}" width="100%" height="100%" type="application/pdf"></object>
                </div>
            </div>
            <div class="modal-footer">  
                <button class="btn btn-outline-secondary btn-lg" data-dismiss="modal" aria-hidden="true"><i class="fa fa-undo" aria-hidden="true"></i>  <spring:message  code="people.cancel"/></button>
            </div>
        </div>
    </div>
</div>
<!-- final Modal display pdf   -->
<!-- Edit Modal-->
<div class="modal fade" id="edit" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <center><h4 class="modal-title" id="myModalLabel">Editar usuario</h4></center>
            </div>
            <div class="modal-body">
                <div class="container-fluid">
                    <div class="form-group input-group">
                        <span class="input-group-addon" style="width:150px;">Nombres:</span>
                        <input type="text" style="width:350px;" class="form-control" id="eNroComp">
                    </div>

                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> Cancelar</button>
                <button type="button" class="btn btn-success" id="btnSacTiposComprobantes"><span class="glyphicon glyphicon-edit"></span> </i> Actualizar</button>
            </div>
        </div>
    </div>
</div>
<!-- /.modal -->

<!-- modal registrar Personas-->
<div id="formPersonas" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form:form name="personasForm" modelAttribute="personas" action="guardarPersonas" role="form" id="itemForm" method="post">
                <div class="modal-header">
                    <p class="h3">  <spring:message  code="persona.crearPersona"/></p>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                </div>
                <div class="modal-body">
                    <div class="form-group row">
                        <spring:message  code="people.name.msg" var="issued"/>
                        <label for="expedido" class="col-sm-3 col-form-label"><spring:message  code="persona.nombres"/></label>
                        <label for="ciExpedido" class="col-sm-1 col-form-label"><i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-8">
                            <input type="text" name="nombres" class="form-control form-control-lg"  placeholder="${issued}" />
                        </div>
                    </div>
                    <div class="form-group row">
                        <spring:message  code="people.paternal.msg" var="issued"/>
                        <label for="expedido" class="col-sm-3 col-form-label"><spring:message  code="persona.paterno"/></label>
                        <label for="ciExpedido" class="col-sm-1 col-form-label"></label>
                        <div class="col-sm-8">
                            <input type="text" name="paterno" class="form-control form-control-lg"  placeholder="${issued}" />
                        </div>
                    </div>
                    <div class="form-group row">
                        <spring:message  code="people.paternal.msg" var="issued"/>
                        <label for="expedido" class="col-sm-3 col-form-label"><spring:message  code="persona.paterno"/></label>
                        <label for="ciExpedido" class="col-sm-1 col-form-label"> </label>
                        <div class="col-sm-8">
                            <input type="text" name="materno" class="form-control form-control-lg"  placeholder="${issued}" />
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="cedula" class="col-sm-3 col-form-label"><spring:message  code="persona.cedula"/></label>
                        <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-8">
                            <spring:message  code="people.identity.msg" var="ced"/>
                            <input type="text" name="ci" class="form-control form-control-lg"  placeholder="${ced}" />
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="sexo" class="col-sm-3 col-form-label"><spring:message  code="people.academic.degree"/></label>
                        <label class="col-sm-1 col-form-label text-right"><i class="fa fa-info-circle" aria-hidden="true"></i> </label>
                        <div class="col-sm-8">
                            <form:select class="form-control form-control-lg" path="PrsGradosAcademicos.idGradoAcademico" id="tipoSexo">
                                <c:forEach var="ga" items="${requestScope.lGradosAcademicos}">
                                    <form:option value="${ga.idGradoAcademico}">${ga.descripcion}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="sexo" class="col-sm-3 col-form-label"><spring:message  code="persona.sexo"/></label>
                        <label class="col-sm-1 col-form-label text-right"><i class="fa fa-info-circle" aria-hidden="true"></i> </label>
                        <div class="col-sm-8">
                            <form:select class="form-control form-control-lg" path="PrsTiposSexos.idPrsTipoSexo" id="tipoSexo">
                                <c:forEach var="ts" items="${requestScope.lPrsTiposSexos}">
                                    <form:option value="${ts.idPrsTipoSexo}">${ts.prsTipoSexo}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="pais" class="col-sm-3 col-form-label"><spring:message  code="persona.pais"/></label>
                        <label class="col-sm-1 col-form-label text-right"><i class="fa fa-info-circle" aria-hidden="true"></i> </label>
                        <div class="col-sm-8">
                            <form:select class="form-control form-control-lg" path="PrsPaises.idPais" id="pais">
                                <c:forEach var="p" items="${requestScope.lPaises}">
                                    <form:option value="${p.idPais}">${p.pais}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-3 col-form-label"><spring:message  code="people.issued"/></label>
                        <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-8">
                            <form:select class="form-control form-control-lg" path="PrsCiExpedidos.idPrsCiExpedido" id="ciExpedido">
                                <c:forEach var="cix" items="${requestScope.lExpedidos}">
                                    <form:option value="${cix.idPrsCiExpedido}">${cix.prsCiExpedido}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                    </div>     

                </div>
                <div class="modal-footer">
                    <button class="btn btn-outline-secondary btn-lg" data-dismiss="modal" aria-hidden="true"><i class="fa fa-undo" aria-hidden="true"></i>  <spring:message  code="people.cancel"/></button>
                    <button type="submit" class="btn btn-success btn-lg float-right" id="btnPersonas" data-dismiss="modal" aria-hidden="true"><i class="fa fa-plus-square-o" aria-hidden="true"></i>   <spring:message  code="people.add"/></button>
                </div>
            </form:form>
        </div>
    </div>
</div>
<!-- final Modal registro de personas  -->

<!-- Modal registro de tipo comprobantes  -->
<div id="formSacTiposComprobantes" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form name="sacTiposComprobantesForm" action="guardarSacTiposComprobantes" role="form" id="sacTiposComprobantesForm" method="post">
                <div class="modal-header">
                    <p class="h3"><i class="fa fa-id-card" aria-hidden="true"></i>  <spring:message  code="voucher.type.register"/></p>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                </div>
                <div class="modal-body">
                    <div class="form-group row">
                        <spring:message  code="voucher.type" var="issued"/>
                        <label for="expedido" class="col-sm-3 col-form-label"><spring:message  code="voucher.type"/></label>
                        <label for="ciExpedido" class="col-sm-1 col-form-label"><i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-8">
                            <input type="text" name="sacTipoComprobante" class="form-control form-control-lg"  placeholder="${issued}" />
                        </div>
                    </div>

                </div>
                <div class="modal-footer">  
                    <button class="btn btn-outline-secondary btn-lg" data-dismiss="modal" aria-hidden="true"><i class="fa fa-undo" aria-hidden="true"></i>  <spring:message  code="people.cancel"/></button>
                    <button type="submit" class="btn btn-success btn-lg float-right" id="btnSacTiposComprobantes" data-dismiss="modal" aria-hidden="true"><i class="fa fa-plus-square-o" aria-hidden="true"></i>   <spring:message  code="people.add"/></button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- final Modal registro de tipo comprobantes  -->

<!-- Modal registro de  tipo pagos -->
<div id="formSacTiposPagos" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form name="sacTiposPagosForm" action="guardarSacTiposPagos" role="form" id="sacTiposPagosForm" method="post">
                <div class="modal-header">
                    <p class="h3"><i class="fa fa-user-plus yvela" aria-hidden="true"></i>  <spring:message  code="voucher.register.payment.type"/></p>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                </div>
                <div class="modal-body">
                    <div class="form-group row">
                        <spring:message  code="voucher.type.pay" var="issued"/>
                        <label for="expedido" class="col-sm-3 col-form-label"><spring:message  code="voucher.type.pay"/></label>
                        <label for="ciExpedido" class="col-sm-1 col-form-label"><i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-8">
                            <input type="text" name="sacTipoPago" class="form-control form-control-lg"  placeholder="${issued}" />
                        </div>
                    </div>
                </div>
                <div class="modal-footer">  
                    <button class="btn btn-outline-secondary btn-lg" data-dismiss="modal" aria-hidden="true"><i class="fa fa-undo" aria-hidden="true"></i>  <spring:message  code="people.cancel"/></button>
                    <button type="submit" class="btn btn-success btn-lg float-right" id="btnSacTiposPagos" data-dismiss="modal" aria-hidden="true"><i class="fa fa-plus-square-o" aria-hidden="true"></i>   <spring:message  code="people.add"/></button>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- final Modal registro de tipo pago  -->

<!-- Modal actualizacion numero comprobantes -->
<div id="formNroComprobante" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form:form name="sacNroComprobantesForm" modelAttribute="sacCompNroComprobante" action="actualizarNroComprobantes" role="form" id="sacTiposPagosForm" method="post">
                <form:hidden path="idSacCompNroComprobante" value="" id="idSacNroComp"/>
                <form:hidden path="SacComprobantes.idSacComprobante" value="" id="idSacComprob"/>
                <div class="modal-header">
                    <p class="h3"><i class="fa fa-edit yvela" aria-hidden="true"></i>  <spring:message  code="voucher.modify.number.voucher"/></p>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                </div>
                <div class="modal-body">
                    <div class="form-group row">
                        <spring:message  code="voucher.type.pay" var="issued"/>
                        <label for="expedido" class="col-sm-3 col-form-label"><spring:message  code="voucher.number"/></label>
                        <label for="ciExpedido" class="col-sm-1 col-form-label"><i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-8">
                            <form:input path="sacNroComprobante" class="form-control form-control-lg"  placeholder="${issued}" value="${sacCompNroComprobante.sacNroComprobante}" id="sacNroComp"/>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">  
                    <button class="btn btn-outline-secondary btn-lg" data-dismiss="modal" aria-hidden="true"><i class="fa fa-undo" aria-hidden="true"></i>  <spring:message  code="people.cancel"/></button>
                    <button type="submit" class="btn btn-success btn-lg float-right" id="btnSacNumeroComprobantes" data-dismiss="modal" aria-hidden="true"><i class="fa fa-pencil-square-o" aria-hidden="true"></i>  <spring:message  code="expenses.modify"/></button>
                </div>
            </form:form>
        </div>
    </div>
</div>
<!-- fina; numero comprobantes-->

<!-- Modal actualizacion numero cheque -->
<div id="formNroChequeEditar" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form:form name="sacNroChequesForm" modelAttribute="sacCompNroCheque" action="actualizarNroCheques" role="form" id="sacTiposPagosForm" method="post">
                <form:hidden path="idSacCompNroCheque" value="" id="idSacNroCheque"/>
                <form:hidden path="SacComprobantes.idSacComprobante" value="" id="idSacCompCheque"/>
                <div class="modal-header">
                    <p class="h3"><i class="fa fa-edit yvela" aria-hidden="true"></i>  <spring:message  code="voucher.modify.number.check"/></p>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                </div>
                <div class="modal-body">
                    <div class="form-group row">
                        <spring:message  code="voucher.type.pay" var="issued"/>
                        <label for="expedido" class="col-sm-3 col-form-label"><spring:message  code="voucher.number"/></label>
                        <label for="ciExpedido" class="col-sm-1 col-form-label"><i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-8">
                            <form:input path="sacNroCheque" class="form-control form-control-lg"  placeholder="${issued}" value="${sacCompNroComprobante.sacNroComprobante}" id="sacNroCheque"/>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">  
                    <button class="btn btn-outline-secondary btn-lg" data-dismiss="modal" aria-hidden="true"><i class="fa fa-undo" aria-hidden="true"></i>  <spring:message  code="people.cancel"/></button>
                    <button type="submit" class="btn btn-success btn-lg float-right" id="btnSacNumeroCheques" data-dismiss="modal" aria-hidden="true"><i class="fa fa-pencil-square-o" aria-hidden="true"></i>  <spring:message  code="expenses.modify"/></button>
                </div>
            </form:form>
        </div>
    </div>
</div>
<!-- fina; numero chequeee-->

<!-- Inicio modal modificar personas-->
<div id="formPersonasEditar" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form:form name="personasEditarForm" modelAttribute="personas" action="actualizarDatosPersonas" role="form" id="itemForm" method="post">
                <form:hidden path="idPersona" value="" id="idPersonaFrm"/>
                <form:hidden path="PrsCiExpedidos.idPrsCiExpedido" value="" id="idPrsCiExpedidoFrm"/>
                <form:hidden path="PrsPaises.idPais" value="" id="idPaisFrm"/>
                <form:hidden path="PrsTiposSexos.idPrsTipoSexo" value="" id="idPrsTipoSexoFrm"/>
                <form:hidden path="PrsGradosAcademicos.idGradoAcademico" value="" id="idGradoAcademicoFrm"/>

                <div class="modal-header">
                    <p class="h3">  <spring:message  code="persona.crearPersona"/></p>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                </div>
                <div class="modal-body">
                    <div class="form-group row">
                        <spring:message  code="people.name.msg" var="issued"/>
                        <label for="expedido" class="col-sm-3 col-form-label"><spring:message  code="persona.nombres"/></label>
                        <label for="ciExpedido" class="col-sm-1 col-form-label"><i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-8">
                            <input type="text" name="nombres" class="form-control form-control-lg"  placeholder="${issued}" id="nombresFrm"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <spring:message  code="people.paternal.msg" var="issued"/>
                        <label for="expedido" class="col-sm-3 col-form-label"><spring:message  code="persona.paterno"/></label>
                        <label for="ciExpedido" class="col-sm-1 col-form-label"></label>
                        <div class="col-sm-8">
                            <input type="text" name="paterno" class="form-control form-control-lg"  placeholder="${issued}" id="paternoFrm"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <spring:message  code="people.paternal.msg" var="issued"/>
                        <label for="expedido" class="col-sm-3 col-form-label"><spring:message  code="persona.paterno"/></label>
                        <label for="ciExpedido" class="col-sm-1 col-form-label"> </label>
                        <div class="col-sm-8">
                            <input type="text" name="materno" class="form-control form-control-lg"  placeholder="${issued}" id="maternoFrm"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="cedula" class="col-sm-3 col-form-label"><spring:message  code="persona.cedula"/></label>
                        <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-8">
                            <spring:message  code="people.identity.msg" var="ced"/>
                            <input type="text" name="ci" class="form-control form-control-lg"  placeholder="${ced}" id="ciFrm"/>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-outline-secondary btn-lg" data-dismiss="modal" aria-hidden="true"><i class="fa fa-undo" aria-hidden="true"></i>  <spring:message  code="people.cancel"/></button>
                    <button type="submit" class="btn btn-success btn-lg float-right" id="btnEditarPersonas" data-dismiss="modal" aria-hidden="true"><i class="fa fa-pencil-square-o" aria-hidden="true"></i>  <spring:message  code="expenses.modify"/></button>
                </div>
            </form:form>
        </div>
    </div>
</div>
<!-- final modificar personas-->

