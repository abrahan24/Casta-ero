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
        <title>Gastos Por Cuenta</title>
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
                             <tr><td height="30" align="center"><font size="3" face="sans-serif"><b><spring:message code="form.title"/></b></font></font></td> </tr>    
                             <tr><td height="50" align="center" valign="middle"><font size="5" face="sans-serif"><b><spring:message code="form.name.expenses"/></b></font></td></tr>
                          </table>
                      </td>
                     <td width="20%" align="center"> 
                           <table border="1" width="100%" height="100%" cellspacing="0" cellpadding="0">  
                               <tr><td   align="left"><font size="1" face="sans-serif">&nbsp;&nbsp;&nbsp;&nbsp;FORM: 004-C </font></td></tr>
                               <tr><td  ><font size="1" face="sans-serif">&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="form.version"/></font></td></tr>
                               <tr><td  align="left" ><font size="1" face="sans-serif">&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript: window.print()'><spring:message code="form.validity"/></a></font></td> </tr> 
                           </table>
                     </td>
                  </tr>
             </table>
           </td>
        </tr>
        <tr><td align="center"><br><h1><font face="sans-serif"><spring:message code="report.account.title"/></font></h1></td></tr>
        <tr><td width="100%"><br><br>
                 <table border="0" width="100%" cellspacing="0" cellpadding="0">
                         <tr >
                            <th width="14%" align="left"><font face="sans-serif"><spring:message code="report.account.dateStart"/></font></th> 
                            <td width="1%">::</td>
                            <td width="35%"><font face="sans-serif"><fmt:formatDate type="date" value="${f1}"/></font></td>
                            <th width="14%" align="left"><font face="sans-serif"><spring:message  code="report.account.dateFinish"/></font></th> 
                            <td width="1%">::</td>
                            <td width="35%"><font face="sans-serif"><fmt:formatDate type="date" value="${f2}"/></font></td>
                        </tr>
                        
                </table> 
          </td>
        </tr>
     </thead>
     <tbody>
         <tr><td align="center"><br><br>
             <table width="90%" border="1" cellspacing="0" cellpadding="0">
                 <tr align="center" valign="middle">
                    <th colspan="4"><spring:message  code="report.account.detail"/></th>
                </tr>
                <tr>
                    <th width="10%"><center><spring:message  code="report.account.account"/></center></th>
                    <th width="50%"><center><spring:message  code="report.account.expense"/></center></th>
                    <th width="30%"><center><spring:message  code="report.account.expenditure"/></center></th>
                    <th widht="10%"><center><spring:message  code="report.account.monthly"/></center></th>
                </tr>
              <c:set var="totalGral" value="0" />
              <c:forEach items="${lTipoClasificacion}" var="ltc">
                <tr>
                    <td><b>${ltc.nroTipoClasificacion}</td>
                    <td colspan="2"><b>${ltc.tipoClasificacion}</td>
                    <td><b>${ltc.total}</td>
                </tr>
                 <c:forEach items="${lGastoClasificacion}" var="lgc">
                    <c:if  test="${lgc.idCjaTipoClasificacion == ltc.idCjaTipoClasificacion}">  
                     <tr>
                        <td>${lgc.nroGastoClasificacion}</td>
                        <td colspan="2">${lgc.gastoClasificacion}</td>
                        <td>${lgc.total}</td>
                     </tr>
                      <c:set var="tGasto" value="0" />
                     <c:forEach items="${lGastosA}" var="tc">
                        <c:if  test="${lgc.idCjaGastoClasificacion == tc.cjaTiposGastos.cjaGastosClasificaciones.idCjaGastoClasificacion}">  
                           <tr>
                            <td align="right">${tc.cjaTiposGastos.nroTipoGasto}</td>
                            <td>${tc.cjaTiposGastos.tipoGasto}</td>
                            <td>${tc.totalG}</td>
                        </tr>
                         <c:set var="tGasto" value="${tGasto + tc.totalG}" />
                       </c:if>
                    </c:forEach>
                    <tr><td colspan="3"><b>Total</b></td>
                        <td>${tGasto}</td>
                    </tr>
                </c:if>
               </c:forEach>
                    <c:set var="totalGral" value="${totalGral + ltc.total}" />
              </c:forEach>
              <tr><td colspan="3"><b>TOTAL GENERAL</b></td>
                        <td><b>${totalGral}</td>
              </tr>
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