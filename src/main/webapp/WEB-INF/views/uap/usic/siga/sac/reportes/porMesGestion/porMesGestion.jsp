<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<head>
    <link rel="stylesheet" type="text/css"  href=" <c:url value="/resource/admPersonas/personas.css"/>" />
    <script src="<spring:url value="/resource/sac/admComprob/admComprob.js" />" type="text/javascript"></script>
    <style type="text/css">
       
    </style>
    <script>

    </script>
</head>

<div class="container">    
    <section class="content-header">
        <div class="row">
            <div class="col-md-10 mb-3">
                <p class="h1"><i class="fa fa-file-archive-o" aria-hidden="true"> </i> <spring:message code="preventive.report.title"/> </p>
            </div>
            <div class="col-sm-2 text-right" id="reporte">
               <!-- <c:choose>
                    <c:when test="${busqueda eq 'find' }">
                        <form  method="POST" action="${urlN}">
                            <span class="icon-input-btn"><i class="fa fa-folder yvela" aria-hidden="true"></i> <input type="submit" class="btn btn-success btn-lg" value="<spring:message  code="income.btn.new"/>"></span>
                        </form>
                    </c:when>
                </c:choose> 
              -->
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
        ${rs.idSacRazonSocial} : ${est.sacNombreEstante}
        <!--------------- Inicio Listar Anio y Estantes  -->
        <c:choose>
            <c:when test="${operation eq 'month'}">
               <center><br>
                    <p class="h3"><i cl ass="fa fa-folder-open-o" aria-hidden="true"></i> <spring:message  code="preventive.report.list.month.title"/></p> 
                    <br>
                </center>
                <table id="tComprobantes"  class="table" style="width:100%">
                    <thead>
                        <tr>
                            <th>Nro.</th>
                            <th><spring:message  code="items.year"/></th>
                            <th><spring:message  code="preventive.report.month"/></th>
                            <th><spring:message  code="preventive.report.btn.view.folders"/></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:set var="cont" value="0" />
                        <c:forEach items="${lMeses}" var="lMes">
                            <c:set var="cont" value="${cont+1}" />
                            <tr> 
                                 <td>${cont}</td>
                                 <td>${lMes.gestion}</td>
                                 <td>${lMes.mes}</td>
                                <td>
                                    <form  method="post" action="${urlC}">
                                         <input type="hidden" name="idMes" value="${lMes.idMes}">
                                         <input type="hidden" name="idSacEstante" value="${idSacEstante}">
                                         <span class="icon-input-btn"> <i class="fa fa-folder-open-o yvela" aria-hidden="true"> </i> <input class="btn btn-success" type="submit"  value="<spring:message  code="preventive.report.btn.view.folders"/>" > </span>
                                    </form>
                                </td>
                             </tr>
                        </c:forEach>
                    </tbody>
                    <tfoot>
                        <tr>
                            <th>Nro.</th>
                            <th><spring:message  code="items.year"/></th>
                            <th><spring:message  code="preventive.report.month"/></th>
                            <th><spring:message  code="preventive.report.btn.view.folders"/></th>
                        </tr>
                    </tfoot>
                </table>
                <br><br>
                <hr align="left" noshade="noshade" size="10" width="100%" />

            </c:when>
        </c:choose>
        <!--------------- Fin Listar Anio y Estantes  -->
  
    <!--------------- Inicio Listar Carpetas por Mes  -->
        <c:choose>
            <c:when test="${operation eq 'folder'}">
               <center><br>
                    <p class="h3"><i cl ass="fa fa-folder-open-o" aria-hidden="true"></i> <spring:message  code="preventive.report.list.month.title"/></p> 
                    <br>
                </center>
                <table id="tComprobantes"  class="table" style="width:100%">
                    <thead>
                        <tr>
                            <th>Nro.</th>
                            <th><spring:message  code="manage.shelves.name"/></th>
                            <th><spring:message  code="manage.folders.number"/></th>
                            <th><spring:message  code="manage.folders.code"/></th>
                            <th><spring:message  code="preventive.report.btn.view.preventive"/></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:set var="cont" value="0" />
                        <c:forEach items="${lCarpetas}" var="lFold">
                            <c:set var="cont" value="${cont+1}" />
                            <tr> 
                                 <td>${cont}</td>
                                 <td>${lFold.sacNombreEstante}</td>
                                 <td>${lFold.sacNumeroCarpeta}</td>
                                 <td>${lFold.sacCodigoCarpeta}</td>
                                <td>
                                    <form  method="post" action="${urlComp}">
                                         <input type="hidden" name="idSacCarpeta" value="${lFold.idSacCarpeta}">
                                         <span class="icon-input-btn"> <i class="fa fa-folder-open-o yvela" aria-hidden="true"> </i> <input class="btn btn-success" type="submit"  value="<spring:message  code="preventive.report.btn.view.preventive"/>" > </span>
                                    </form>
                                </td>
                             </tr>
                        </c:forEach>
                    </tbody>
                    <tfoot>
                         <tr>
                            <th>Nro.</th>
                            <th><spring:message  code="manage.shelves.name"/></th>
                            <th><spring:message  code="manage.folders.number"/></th>
                            <th><spring:message  code="manage.folders.code"/></th>
                            <th><spring:message  code="preventive.report.btn.view.preventive"/></th>
                        </tr>
                    </tfoot>
                </table>
                <br><br>
                <hr align="left" noshade="noshade" size="10" width="100%" />

            </c:when>
        </c:choose>
        <!--------------- Fin Listar Carpetas por Mes ----------------  -->
         
        <!--------------- Inicio Listar Comprobantes por Carpetas --------------  -->
        <c:choose>
            <c:when test="${operation eq 'compr' }">
                <center>
                      <p class="h3"><i class="fa fa-folder-open-o" aria-hidden="true"></i> <spring:message  code="preventive.report.description.location.title"/></p> 
                </center>         
       
       <br>
        <table class="table">
            <tr class="trIngreso">
                <th  align="center"><spring:message  code="items.year"/></th>
                <th  align="center"><spring:message  code="preventive.report.month"/></th>
                <th  align="center"><spring:message  code="manage.shelves.name"/></th>
                <th align="center"><spring:message  code="manage.folders.code"/></th>
                <th align="center"><spring:message  code="expenses.btn.print"/></th>
             </tr>
            <tr id="thing">
                <td>${bSacCarpeta.gestion}</td>
                <td>${bSacCarpeta.sisMeses.mes}</td>
                 <td>${bSacCarpeta.sacEstantes.sacNombreEstante}</td>
                <td>${bSacCarpeta.sacCodigoCarpeta}</td>
                <td>
                    <form  method="post" action="${urlPrint}" target="blank">
                       <input type="hidden" name="idSacCarpeta" value="${bSacCarpeta.idSacCarpeta}">
                       <span class="icon-input-btn"> <i class="fa fa-folder-open-o yvela" aria-hidden="true"> </i> <input class="btn btn-success" type="submit"  value="<spring:message  code="expenses.btn.print"/>" > </span>
                    </form>
                </td>
             </tr>
            </table>
              <br>
              <center>
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
                            <th><spring:message  code="voucher.date.elaboration"/></th>
                            <th><spring:message  code="voucher.file.name"/> </th>
                         </tr>
                    </thead>
                    <tbody>
                        <c:set var="cont" value="0" />
                        <c:forEach items="${lSacComprobantes}" var="lSac">
                            <c:set var="cont" value="${cont+1}" />
                            <tr>
                                <td>
                                    <c:forEach items="${lNroComprobante}" var="lNComp">
                                         <c:if test="${lNComp.sacComprobantes.idSacComprobante == lSac.idSacComprobante}">
                                              ${lNComp.sacNroComprobante} - 
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
                                <td>${lSac.fecElaboracion}</td>
                                <td> 
                                    <c:forEach items="${lArchivos}" var="lArch">
                                         <c:if test="${lArch.sacComprobantes.idSacComprobante == lSac.idSacComprobante}">
                                              ${lArch.nombreArchivo}  
                                         </c:if>
                                    </c:forEach>
                                </td>
                             </tr>
                        </c:forEach>
                    </tbody>
                    <tfoot>
                        <tr>
                            <th><spring:message  code="voucher.number"/></th>
                            <th><spring:message  code="voucher.check.number"/></th>
                            <th><spring:message  code="voucher.Name.CorporateName"/></th>
                            <th><spring:message  code="voucher.gloss"/></th>
                            <th><spring:message  code="voucher.date.elaboration"/></th>
                            <th><spring:message  code="voucher.file.name"/></th>
                         </tr>
                    </tfoot>
                </table>
                <br><br>
                <hr align="left" noshade="noshade" size="10" width="100%" />

            </c:when>
        </c:choose>
        <!--------------- Fin Listar Comprobantes por Carpetas ----------------  -->
 
        <!--Inicio modificar Comprobantes -->
        <c:choose>
            <c:when test="${operation eq 'edit' }">
                <center><br>
                    <p class="h3"><i class="fa fa-pencil-square-o" aria-hidden="true"></i>  <spring:message  code="voucher.modify.form"/></p> 
                    <br>
                </center>
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
                </c:when>
            </c:choose>       

        
        <!------------------------------------ Final Eliminar Personas------------------------------------------- -->
        
        <!--Tabla lista de Todas las Personas Registradas -->
        <c:choose>
            <c:when test="${busqueda eq 'find' }">
                <center>
                    <p class="h3"><i class="fa fa-folder-open-o" aria-hidden="true"></i> <spring:message  code="preventive.report.view.shelves.year"/></p> 
                    <br>
                </center>
                <table id="tComprobantes"  class="table" style="width:100%">
                    <thead>
                        <tr>
                            <th>Nro.</th>
                            <th><spring:message  code="items.year"/></th>
                            <th><spring:message  code="manage.shelves.name"/></th>
                            <th><spring:message  code="preventive.report.btn.list.months"/></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:set var="cont" value="0" />
                        <c:forEach items="${lEstantes}" var="lEst">
                            <c:set var="cont" value="${cont+1}" />
                            <tr> 
                                 <td>${cont}</td>
                                 <td>${lEst.gestion}</td>
                                 <td>${lEst.sacNombreEstante}</td>
                               <td>
                                    <form  method="post" action="${urlF}">
                                        <input type="hidden" name="gestion" value="${lEst.gestion}">
                                        <input type="hidden" name="idSacEstante" value="${lEst.idSacEstante}">
                                        <span class="icon-input-btn"> <i class="fa fa-folder-open-o yvela" aria-hidden="true"> </i> <input class="btn btn-success" type="submit"  value="<spring:message  code="preventive.report.btn.list.months"/>" > </span>
                                    </form>
                                </td>
                             </tr>
                        </c:forEach>
                    </tbody>
                    <tfoot>
                       <tr>
                            <th>Nro.</th>
                            <th><spring:message  code="items.year"/></th>
                            <th><spring:message  code="manage.shelves.name"/></th>
                            <th><spring:message  code="preventive.report.btn.list.months"/></th>
                        </tr>
                    </tfoot>
                </table>
                <br><br>
                <hr align="left" noshade="noshade" size="10" width="100%" />

            </c:when>
        </c:choose>
    </section>
</div>
