<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<head>
    <link rel="stylesheet" type="text/css"  href=" <c:url value="/resource/admPersonas/personas.css"/>" />
    <script src="<spring:url value="/resource/sac/busqueda/buscarCompr.js" />" type="text/javascript"></script>
    <style>
    </style>

</head>
<div class="container">    
    <section class="content-header">
        <div class="row">
            <div class="col-md-10 mb-3">
                <p class="h1"><i class="fa fa-search" aria-hidden="true"></i> <spring:message code="preventive.search.title"/> </p>
            </div>
            <div class="col-sm-2 text-right" id="reporte">
                 <c:choose>
                    <c:when test="${operation eq 'vista' }">
                        <form  method="GET" action="${urlVol}">
                            <span class="icon-input-btn"><i class="fa fa-arrow-circle-left yvela" aria-hidden="true"></i> <input type="submit" class="btn btn-success btn-lg" value="<spring:message  code="preventive.report.btn.return"/>"></span>
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

 <!--------------- Inicio formulario busqueda de comprobante ------------- -->
     <c:choose>
        <c:when test="${operation eq 'find' }">
             <center><br>
               <p class="h3"><i class="fa fa-lightbulb-o" aria-hidden="true"></i> <spring:message  code="preventive.search.form.title"/></p> <br>
            </center>
             <form action="${urlB}" method="POST">

                  <div class="form-group row">
                     <label for="ciExpedido" class="col-sm-3 col-form-label"><spring:message  code="voucher.type"/></label>
                     <div class="col-sm-6">
                       <select class="form-control form-control-lg" name="idSacTipoComprobante" id="tComprobante">
                          <c:forEach var="ltc" items="${requestScope.lTiposComprobantes}">
                             <option value="${ltc.idSacTipoComprobante}">${ltc.sacTipoComprobante}</option>
                          </c:forEach>
                        </select>
                     </div>
                     <div class="col-sm-3" id="comd">
                     </div>
                 </div>
                 <div class="form-group row">
                     <label for="ciExpedido" class="col-sm-3 col-form-label"><spring:message  code="voucher.Name.CorporateName"/></label>
                     <div class="col-sm-6">
                          <input type="text" name="nombres"  class="form-control form-control-lg"  placeholder="<spring:message  code="voucher.Name.CorporateName" />"/> 
                     </div>
                     <div class="col-sm-3" id="comd">
                         <small class="form-text text-muted">
                            <span class="icon-input-btn"><i class="fa fa-search yvela" aria-hidden="true"></i> <input type="submit" class="btn btn-success btn-lg" value="<spring:message  code="preventive.search.form.btn.search"/>"></span>
                          </small>
                     </div>
                </div>
                <div class="form-group row">
                     <label for="ciExpedido" class="col-sm-3 col-form-label"><spring:message  code="voucher.number"/></label>
                     <div class="col-sm-6">
                         <input type="text" name="sacNroComprobante" value="0"  class="form-control form-control-lg"  placeholder="<spring:message  code="voucher.number.msg" />"/> 
                     </div>
                     <div class="col-sm-3" id="comd">
                         <small class="form-text text-muted">
                            <span class="icon-input-btn"><i class="fa fa-search yvela" aria-hidden="true"></i> <input type="submit" class="btn btn-success btn-lg" value="<spring:message  code="preventive.search.form.btn.search"/>"></span>
                          </small>
                     </div>
                </div>
                <div class="form-group row">
                     <label for="ciExpedido" class="col-sm-3 col-form-label"><spring:message  code="voucher.check.number"/></label>
                     <div class="col-sm-6">
                          <input type="text" name="sacNroCheque" value="0" class="form-control form-control-lg"  placeholder="<spring:message  code="voucher.check.number.msg" />"/> 
                     </div>
                     <div class="col-sm-3" id="comd">
                         <small class="form-text text-muted">
                            <span class="icon-input-btn"><i class="fa fa-search yvela" aria-hidden="true"></i> <input type="submit" class="btn btn-success btn-lg" value="<spring:message  code="preventive.search.form.btn.search"/>"></span>
                          </small>
                     </div>
                </div>
             </form>
        </c:when>
     </c:choose>
 <!--------------- fil formulario busqueda de comprobante ------------- -->
 
  <!--------------- Inicio Listar Razon Social  ------------- -->
     <c:choose>
        <c:when test="${listarRazonSocial eq 'lRazSoc' }">
            <center><br>
               <p class="h4"><i class="fa fa-users" aria-hidden="true"></i> <spring:message  code="preventive.search.form.list.title"/></p> <br>
            </center>
           <table id="tComprobantes"  class="table" style="width:100%">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th><spring:message  code="items.year"/></th>
                            <th><spring:message  code="preventive.report.month"/></th>
                            <th><spring:message  code="persona.cedula"/></th>
                            <th><spring:message  code="voucher.Name.CorporateName"/></th>
                            <th><spring:message  code="preventive.report.btn.view.preventive"/></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:set var="cont" value="0" />
                        <c:forEach var="lSac" items="${requestScope.lSacComprobantesResponseRs}">
                              <tr>
                                 <td>${lSac.idSacComprobante}</td>
                                 <td>${lSac.gestion}</td>
                                 <td>${lSac.mes}</td>
                                 <td>${lSac.ci}</td>
                                 <td>${lSac.nombres} ${lSac.paterno} ${lSac.materno}</td>
                                 
                                <td>
                                    <form name="Delet" action="${urlD}" method="post">
                                        <input type="hidden" name="idSacComprobante" value="${lSac.idSacComprobante}">
                                        <span class="icon-input-btn"> <i class="fa fa-search yvela" aria-hidden="true"> </i> <input class="btn btn-success" type="submit"  value="<spring:message  code="preventive.report.btn.view.preventive"/>"> </span>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                    <tfoot>
                        <tr>
                            <th>ID</th>
                            <th><spring:message  code="items.year"/></th>
                            <th><spring:message  code="preventive.report.month"/></th>
                            <th><spring:message  code="persona.cedula"/></th>
                            <th><spring:message  code="voucher.Name.CorporateName"/></th>
                            <th><spring:message  code="preventive.report.btn.view.preventive"/></th>
                         </tr>
                    </tfoot>
                </table>
                <br><br>
                <hr align="left" noshade="noshade" size="10" width="100%" />
            
        </c:when>
     </c:choose>   
  <!--------------- Fin Listar Razon Social  ------------- -->
  
  <!--------------- Inicio Listar Numero de Comprobante  ------------- -->
     <c:choose>
        <c:when test="${listarNroComprobante eq 'lNroComp' }">
            <center><br>
               <p class="h4"><i class="fa fa-list-alt" aria-hidden="true"></i> <spring:message  code="preventive.search.from.list.preventive.number.title"/></p> <br>
            </center>
           <table id="tComprobantes"  class="table" style="width:100%">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th><spring:message  code="items.year"/></th>
                            <th><spring:message  code="preventive.report.month"/></th>
                            <th><spring:message  code="voucher.number"/></th>
                            <th><spring:message  code="preventive.report.btn.view.preventive"/></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:set var="cont" value="0" />
                        <c:forEach var="lSac" items="${requestScope.lSacComprobantesResponseNc}">
                              <tr>
                                 <td>${lSac.idSacComprobante}</td>
                                 <td>${lSac.gestion}</td>
                                 <td>${lSac.mes}</td>
                                 <td>${lSac.sacNroComprobante}</td>
                               <td>
                                    <form name="Delet" action="${urlD}" method="post">
                                        <input type="hidden" name="idSacComprobante" value="${lSac.idSacComprobante}">
                                        <span class="icon-input-btn"> <i class="fa fa-search yvela" aria-hidden="true"> </i> <input class="btn btn-success" type="submit"  value="<spring:message  code="preventive.report.btn.view.preventive"/>"> </span>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                    <tfoot>
                        <tr>
                            <th>ID</th>
                            <th><spring:message  code="items.year"/></th>
                            <th><spring:message  code="preventive.report.month"/></th>
                            <th><spring:message  code="voucher.number"/></th>
                            <th><spring:message  code="preventive.report.btn.view.preventive"/></th>
                         </tr>
                    </tfoot>
                </table>
                <br><br>
                <hr align="left" noshade="noshade" size="10" width="100%" />
            
        </c:when>
     </c:choose>   
  <!--------------- Inicio Listar Numero de Comprobante  ------------- -->
 
  <!--------------- Inicio Listar Numero de Cheque  ------------- -->
     <c:choose>
        <c:when test="${listarNroCheque eq 'lNroChe' }">
            <center><br>
               <p class="h4"><i class="fa fa-list-alt" aria-hidden="true"></i> <spring:message  code="preventive.search.from.list.check.number.title"/></p> <br>
            </center>
           <table id="tComprobantes"  class="table" style="width:100%">
                   <thead>
                        <tr>
                            <th>ID</th>
                            <th><spring:message  code="items.year"/></th>
                            <th><spring:message  code="preventive.report.month"/></th>
                            <th><spring:message  code="voucher.check.number"/></th>
                            <th><spring:message  code="preventive.report.btn.view.preventive"/></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:set var="cont" value="0" />
                        <c:forEach var="lSac" items="${requestScope.lSacComprobantesResponseNch}">
                              <tr>
                                 <td>${lSac.idSacComprobante}</td>
                                 <td>${lSac.gestion}</td>
                                 <td>${lSac.mes}</td>
                                 <td>${lSac.sacNroCheque}</td>
                               <td>
                                    <form name="Delet" action="${urlD}" method="post">
                                        <input type="hidden" name="idSacComprobante" value="${lSac.idSacComprobante}">
                                        <span class="icon-input-btn"> <i class="fa fa-search yvela" aria-hidden="true"> </i> <input class="btn btn-success" type="submit"  value="<spring:message  code="preventive.report.btn.view.preventive"/>"> </span>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                    <tfoot>
                        <tr>
                            <th>ID</th>
                            <th><spring:message  code="items.year"/></th>
                            <th><spring:message  code="preventive.report.month"/></th>
                            <th><spring:message  code="voucher.check.number"/></th>
                            <th><spring:message  code="preventive.report.btn.view.preventive"/></th>
                         </tr>
                    </tfoot>
                </table>
                <br><br>
                <hr align="left" noshade="noshade" size="10" width="100%" />
            
        </c:when>
     </c:choose>   
  <!--------------- Inicio Listar Numero de Comprobante  ------------- -->
  
   <!--------------- Inicio Vista de Comprobante ------------- -->
  
    <c:choose>
            <c:when test="${operation eq 'vista' }">
                <div id="" class="col-lg-12" >
                    <br>
                    <center><h5><i class="fa fa-money" aria-hidden="true"></i>  <spring:message  code="preventive.search.from.view.preventive"/></h5></center>
                    <br>
               <div class="form-group row">
                 <div class="col-sm-8" id="combo1"> 
                  <form action="${urlDel}" enctype="multipart/form-data" method="POST">                    
                     <div class="form-group row">
                        <label for="codigo" class="col-sm-4 col-form-label"><spring:message  code="manage.folders.bookcase"/></label>
                        <div class="col-sm-8">
                            <input name=""  class="form-control form-control-lg"  placeholder="${nom}" value="${sacComprobantes.sacCarpetas.sacEstantes.sacNombreEstante}" readonly="true"/> 
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-4 col-form-label"><spring:message  code="voucher.folders"/></label>
                        <div class="col-sm-8">
                            <input name=""  class="form-control form-control-lg"  placeholder="" value="${sacComprobantes.sacCarpetas.sacCodigoCarpeta}" readonly="true"/> 
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-4 col-form-label"><spring:message  code="voucher.type"/></label>
                         <div class="col-sm-8">
                              <input name=""  class="form-control form-control-lg"  placeholder="" value="${sacComprobantes.sacTiposComprobantes.sacTipoComprobante}" readonly="true"/> 
                         </div>
                    </div>
                    <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-4 col-form-label"><spring:message  code="voucher.type.pay"/></label>
                        <div class="col-sm-8">
                            <input name=""  class="form-control form-control-lg"  placeholder="" value="${sacComprobantes.sacTiposPagos.sacTipoPago}" readonly="true"/> 
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
                                     <tr class="nroCompTr${lnroComp.idSacCompNroComprobante}"> 
                                        <td><span id="idNroComp${lnroComp.idSacCompNroComprobante}">${lnroComp.idSacCompNroComprobante}</span></td>
                                        <td><span id="nroComp${lnroComp.idSacCompNroComprobante}">${lnroComp.sacNroComprobante}</span></td>
                                        <td>
                                         </td>
                                        <td>
                                         </td>
                                    </tr> 
                                </c:forEach>
                             </table>
                        </div>
                     </div>
                    
                    <div class="form-group row">
                        <label for="sedes" class="col-sm-4 col-form-label"><spring:message  code="voucher.check.number"/></label>
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
                                         </td>
                                        <td>
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
                            <input type="date" name="fecElaboracion" value="${sacComprobantes.fecElaboracion}" class="form-control form-control-lg"  placeholder="${nom}" readonly="true"/> 
                        </div>
                     </div>

                    <div class="form-group row">
                        <label for="codigo" class="col-sm-4 col-form-label"><spring:message  code="voucher.roadmap"/></label>
                        <div class="col-sm-8">
                             <input type="number" name="hojaRuta" value="${sacComprobantes.hojaRuta}"  class="form-control form-control-lg"  placeholder="${road}" readonly="true"/> 
                        </div>
                     </div>
                    <div class="form-group row">
                        <label for="codigo" class="col-sm-4 col-form-label"><spring:message  code="voucher.gloss"/></label>
                         <div class="col-sm-8">
                             <textarea name="glosa" class="form-control form-control-lg" placeholder="" rows="3" cols="20" readonly="true">${sacComprobantes.glosa}</textarea>
                        </div>
                     </div>
                    <div class="form-group row">
                        <label for="codigo" class="col-sm-4 col-form-label"><spring:message  code="voucher.rode"/></label>
                         <div class="col-sm-8">
                             <input type="number" name="sacMonto" value="${sacComprobantes.sacMonto}"  class="form-control form-control-lg"  placeholder="${rode}" readonly="true"/> 
                        </div>
                     </div>
                    <div class="form-group row">
                        <spring:message  code="items.year" var="year"/>
                        <label for="sedes" class="col-sm-4 col-form-label"><spring:message  code="items.year"/></label>
                        <div class="col-sm-8" id="combo1"> 
                            <input class="form-control form-control-lg" name="gestion" value="${sacComprobantes.gestion}" onkeyUp="return ValNumero(this);" placeholder="${year}" value="${gestion}" readonly="true"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="msg" class="col-sm-2 col-form-label text-right"></label>
                        <label for="msg" class="col-sm-10 col-form-label"><span class="parpadea text"><i class="fa fa-eye" aria-hidden="true"></i></span> &nbsp;&nbsp;&nbsp;<spring:message  code="people.warning"/> </label>
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
                                    <c:forEach var="lrs" items="${requestScope.lRazonSocial}"  varStatus="status">
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
                        <spring:message  code="voucher.file.name.msg" var="adj"/>
                        <label for="sedes" class="col-sm-4 col-form-label"><spring:message  code="voucher.file.name"/></label>
                        <div class="col-sm-6" id="combo1"> 
                            <input class="form-control form-control-lg" name="nombreArchivo"  id="fileName" placeholder="${adj}" value="${arch.nombreArchivo}" readonly="true" />
                        </div>
                         <div class="col-sm-2" id="combo1"> 
                             <small class="msgError">
                                <a class="btn add" href="<spring:url value="/comprobante/openFile/${sacComprobantes.idSacComprobante}"/>" target="blank"><i class="fa fa-plus-square-o" aria-hidden="true"></i>  <spring:message  code="voucher.viem.pdf"/></a>
                            </small>
                        </div>
                     </div>     
                     <hr align="left" noshade="noshade" size="10" width="100%" />
                     <div class="form-group row mt-12">
                         
                      </div>
                    </form>
                   </div>
                   <div class="col-sm-4" id="combo1"> 
                         <fieldset class="col-md-12">    	
                             <legend><i class="fa fa-exclamation-triangle" aria-hidden="true"></i>  <spring:message  code="expenses.remove.info.title"/></legend>
                                <br> 
                                <p><spring:message  code="preventive.search.from.view.msg"/> </p>
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
    <!--------------- Final Vista de Comprobante ------------- -->
  </section>