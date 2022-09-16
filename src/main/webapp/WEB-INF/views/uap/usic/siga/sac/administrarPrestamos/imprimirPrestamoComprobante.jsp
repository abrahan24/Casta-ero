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
        <title>Acta de Prestamo</title>
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
                                        <tr><td height="50" align="center" valign="middle"><font size="5" face="sans-serif"><b><spring:message code="loans.minutes.name.msg"/></b></font></td></tr>
                                    </table>
                                </td>
                                <td width="20%" align="center"> 
                                    <table border="1" width="100%" height="100%" cellspacing="0" cellpadding="0">  
                                        <tr><td   align="left"><font size="1" face="sans-serif">&nbsp;&nbsp;&nbsp;&nbsp;ACT: 001-A </font></td></tr>
                                        <tr><td  ><font size="1" face="sans-serif">&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="form.version"/></font></td></tr>
                                        <tr><td  align="left" ><font size="1" face="sans-serif">&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript: window.print()'>Vigencia 03-10-2019</a></font></td> </tr> 
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr><td align="center"><br><h1><font face="sans-serif"><spring:message code="loans.voucher.name"/></font></h1></td></tr>
                <tr><td width="100%"><br><br>
                        <table border="0" width="100%" cellspacing="0" cellpadding="0">
                            <tr>
                                <td align="justify">
                                    En la ciudad de Cobija Capital del Departamento de Pando, a los ${fechaPrestamo} a Hrs. ${bPrestamoComp.horaPrestamo} 
                                    en las Oficinas de la ${bPersonalAdministrativo.insUnidadesFuncionales.unidadFuncional} se hizo presente
                                    <c:if test="${bPrestamoComp.personas.prsTiposSexos.idPrsTipoSexo == 1}">la </c:if> <c:if test="${bPrestamoComp.personas.prsTiposSexos.idPrsTipoSexo == 2}">el </c:if> 
                                    ${bPrestamoComp.personas.prsGradosAcademicos.descripcion} ${bPrestamoComp.personas.nombres} ${bPrestamoComp.personas.paterno} ${bPrestamoComp.personas.materno}
                                    a objeto de recibir la siguiente documentación de 
                                    <c:if test="${bPersona.prsTiposSexos.idPrsTipoSexo == 1}"> la Sra. </c:if> <c:if test="${bPersona.prsTiposSexos.idPrsTipoSexo == 2}"> el Sr. </c:if> 
                                    ${bPersona.nombres} ${bPersona.paterno} ${bPersona.materno} ${bPersonalAdministrativo.pnlCargos.cargo}, dando cumplimiento
                                    a instructivo de la Direccion Administrativa y Financiera. El detalle a continuación:
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
                                </tr>
                            </thead>
                            <tbody>
                                <c:set var="cont" value="0" />
                                <c:forEach items="${lCompPrestaDetalles}" var="lSac">
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
                                <td align="center">${bPrestamoComp.personas.nombres} ${bPrestamoComp.personas.paterno} ${bPrestamoComp.personas.materno}</td>
                                <td align="center">${bPersona.nombres} ${bPersona.paterno} ${bPersona.materno}</td>
                            </tr>
                            <tr><td align="center"><spring:message  code="loans.received"/></td>
                                <td align="center"><spring:message  code="loans.deliver"/></td>
                            </tr>

                        </table>
                    </td>
                </tr>
            </tfoot>
        </table>
    </center>
</body>
</html>