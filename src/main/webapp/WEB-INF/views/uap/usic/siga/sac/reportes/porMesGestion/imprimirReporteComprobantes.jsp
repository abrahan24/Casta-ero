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
        <title>Reporte Mes y Gestion</title>
        
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
                             <tr><td height="30" align="center"><font size="3" face="sans-serif"><b><spring:message code="rep.title"/></b></font></font></td> </tr>    
                             <tr><td height="50" align="center" valign="middle"><font size="5" face="sans-serif"><b><spring:message code="preventive.report.form.name.print"/></b></font></td></tr>
                          </table>
                      </td>
                     <td width="20%" align="center"> 
                           <table border="1" width="100%" height="100%" cellspacing="0" cellpadding="0">  
                               <tr><td   align="left"><font size="1" face="sans-serif">&nbsp;&nbsp;&nbsp;&nbsp;REP: 003-C </font></td></tr>
                               <tr><td  ><font size="1" face="sans-serif">&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="form.version"/></font></td></tr>
                               <tr><td  align="left" ><font size="1" face="sans-serif">&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript: window.print()'><spring:message code="form.validity"/></a></font></td> </tr> 
                           </table>
                     </td>
                  </tr>
             </table>
           </td>
        </tr>
        <tr><td align="center"><br><h1><font face="sans-serif"><spring:message code="preventive.report.preventives.title"/></font></h1></td></tr>
        <tr><td width="100%"><br><br>
            <table width="100%" border="1" cellspacing="0" cellpadding="0">
               <tr class="trIngreso">
                <th  align="center"><spring:message  code="items.year"/></th>
                <th  align="center"><spring:message  code="preventive.report.month"/></th>
                <th  align="center"><spring:message  code="manage.shelves.name"/></th>
                <th align="center"><spring:message  code="manage.folders.code"/></th>
              </tr>
            <tr id="thing">
                <td>${bSacCarpeta.gestion}</td>
                <td>${bSacCarpeta.sisMeses.mes}</td>
                 <td>${bSacCarpeta.sacEstantes.sacNombreEstante}</td>
                <td>${bSacCarpeta.sacCodigoCarpeta}</td>
              </tr>
            </table>
          </td>
        </tr>
     </thead>
     <tbody>
         <tr><td align="center"><br><br>
              <table width="100%" border="1" cellspacing="0" cellpadding="0">
                    <thead>
                        <tr>
                            <th>Nro</th>
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
                            <tr><td>${cont}</td>
                                <td>
                                    <c:forEach items="${lNroComprobante}" var="lNComp">
                                         <c:if test="${lNComp.sacComprobantes.idSacComprobante == lSac.idSacComprobante}">
                                              ${lNComp.sacNroComprobante} 
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
                       -
                    </tfoot>
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
                   <tr><td align="center"><spring:message  code="report.noteOrder.signatureApplicant"/></td>
                       <td align="center"><spring:message  code="report.noteOrder.signatureBoss"/></td>
                   </tr>
                  
                </table>
             </td>
          </tr>
     </tfoot>
  </table>
 </body>
</html>