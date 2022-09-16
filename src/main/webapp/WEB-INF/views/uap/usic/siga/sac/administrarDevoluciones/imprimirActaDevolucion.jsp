<jsp:useBean id="now" class="java.util.Date" />
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<fmt:formatDate value="${bean.date}" pattern="dd-MM-yyyy" />
<html>
    <head>
        <style>

        </style>
        <title>Acta de Devolución</title>
        <link rel="stylesheet" type="text/css"  href=" <c:url value="/resource/admReportes/reportes.css"/>" />
        <script src="<spring:url value="/resource/admReportes/reportes.js" />" type="text/javascript"></script>

    </head>
    <body>
    <center>
        <table width="90%" border="0" cellspacing="0" cellpadding="0">  
            <thead>
                <tr><td>
                        <table width="100%" border="1" cellspacing="0" cellpadding="0">  
                            <tr><td align="center" width="20%" ><img border="0" src="${pageContext.request.contextPath}/resource/images/logs/logouap.jpg" class="img-rounded"  class="img-responsive" alt="UAP" width="65" height="80"></td>
                                <td width="60%" height="100%"> <table border="1" width="100%" cellspacing="0" cellpadding="0">  
                                        <tr><td height="30" align="center"><font size="3" face="sans-serif"><b><spring:message code="loans.minutes.name"/></b></font></font></td> </tr>    
                                        <tr><td height="50" align="center" valign="middle"><font size="5" face="sans-serif"><b><spring:message code="returns.minutes"/></b></font></td></tr>
                                    </table>
                                </td>
                                <td width="20%" align="center"> 
                                    <table border="1" width="100%" height="100%" cellspacing="0" cellpadding="0">  
                                        <tr><td   align="left"><font size="1" face="sans-serif">&nbsp;&nbsp;&nbsp;&nbsp;ACT: 002-AD </font></td></tr>
                                        <tr><td  ><font size="1" face="sans-serif">&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="form.version"/></font></td></tr>
                                        <tr><td  align="left" ><font size="1" face="sans-serif">&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript: window.print()'>Vigencia 03-10-2019</a></font></td> </tr> 
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr><td align="center"><br><h1><font face="sans-serif"><spring:message code="returns.minutesmsg"/></font></h1></td></tr>
                <tr><td width="100%"><br><br>
                        <table border="0" width="100%" cellspacing="0" cellpadding="0">
                            <tr>
                                <td align="justify">
                                    En la ciudad de Cobija Capital del Departamento de Pando, en Fecha ${fechaDevolucion} a Hrs. ${bDevolucionComp.horaDevolucion} 
                                    se recibio <c:if test="${bDevolucionComp.personas.prsTiposSexos.idPrsTipoSexo == 1}">de la </c:if> <c:if test="${bDevolucionComp.personas.prsTiposSexos.idPrsTipoSexo == 2}">del </c:if> 
                                    ${bDevolucionComp.personas.prsGradosAcademicos.descripcion} ${bDevolucionComp.personas.nombres} ${bDevolucionComp.personas.paterno} ${bDevolucionComp.personas.materno},
                                    los siguientes comprobantes contables, pertenecientes a esta oficina de  ${bPersonalAdministrativo.insUnidadesFuncionales.unidadFuncional}
                                    el cual se hizo entrega 
                                    <c:if test="${bPersona.prsTiposSexos.idPrsTipoSexo == 1}"> a la Sra. </c:if> <c:if test="${bPersona.prsTiposSexos.idPrsTipoSexo == 2}"> al Sr. </c:if> 
                                    ${bPersona.nombres} ${bPersona.paterno} ${bPersona.materno} ${bPersonalAdministrativo.pnlCargos.cargo}.
                                </td>
                            </tr>
                        </table> 
                    </td>
                </tr>
            </thead>
            <tbody>
                <tr><td align="center"><br><br>
                        <table width="90%" border="1" cellspacing="0" cellpadding="0">
                            <thead>
                                <tr>
                                    <th>Nro</th>
                                    <th><spring:message  code="voucher.number"/></th>
                                    <th><spring:message  code="voucher.date.elaboration"/></th>
                                    <th><spring:message  code="voucher.Name.CorporateName"/></th>
                                    <th><spring:message  code="loans.table.detail"/></th>
                                    <th><spring:message  code="manage.folders.number"/></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:set var="cont" value="0" />
                                <c:forEach items="${lDevCompDetalles}" var="lSac">
                                    <c:set var="cont" value="${cont+1}" />
                                    <tr>
                                        <td>${cont}</td>
                                        <td>
                                            <c:forEach var="nc" items="${requestScope.lSacNumComp}">
                                                <c:if  test="${lSac.idSacComprobante == nc.sacComprobantes.idSacComprobante}">
                                                    - ${nc.sacNroComprobante} 
                                                </c:if>
                                            </c:forEach>
                                        </td>
                                        <td>${lSac.fecElaboracion}</td>
                                        <td>
                                            <c:forEach var="rs" items="${requestScope.lRazonSocialP}">
                                                <c:if  test="${lSac.idSacComprobante == rs.sacComprobantes.idSacComprobante}">
                                                    - ${rs.personas.nombres} ${rs.personas.paterno} ${rs.personas.materno} 
                                                </c:if>
                                            </c:forEach>
                                        </td>
                                        <td>${lSac.glosa}</td>
                                        <td>${lSac.sacCarpetas.sacCodigoCarpeta}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </td>
                </tr>
            </tbody>
            <tfoot>
                <tr><td><br><br>
                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr><td align="center"><br><br>______________________</td>
                                <td align="center"><br><br>______________________</td>
                            </tr>
                            <tr>
                                <td align="center">${bDevolucionComp.personas.nombres} ${bDevolucionComp.personas.paterno} ${bDevolucionComp.personas.materno}</td>
                                <td align="center">${bPersona.nombres} ${bPersona.paterno} ${bPersona.materno}</td>
                            </tr>
                            <tr>
                                <td align="center"><spring:message  code="loans.deliver"/></td>
                                <td align="center"><spring:message  code="loans.received"/></td>
                            </tr>

                        </table>
                    </td>
                </tr>
            </tfoot>
        </table>
    </center>
</body>
</html>