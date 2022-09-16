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
        <title>Arqueo de Cuentas Global</title>
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
                             <tr><td height="50" align="center" valign="middle"><font size="5" face="sans-serif"><b><spring:message code="form.name.arching"/></b></font></td></tr>
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
        <tr><td align="center"><br><h1><font face="sans-serif"><spring:message code="report.global.title"/></font></h1></td></tr>
        <tr><td width="100%"><br><br>
                <table border="0" width="100%" cellspacing="0" cellpadding="0">
                         <tr>
                            <th width="35%" align="left"><font face="sans-serif"><spring:message code="report.arching.total"/></font></th> 
                            <td width="1%">::</td>
                            <td width="14%"><font face="sans-serif"><fmt:formatDate type="date" value="${bCajaIngreso.fecIngreso}"/></font></td>
                            <th width="35%" align="left"><font face="sans-serif"><spring:message  code="report.arching.amount"/></font></th> 
                            <td width="1%">::</td>
                            <td width="14%"><font face="sans-serif"><fmt:formatNumber value="${bCajaIngreso.monto}" pattern="0.00"/></font></td>
                        </tr>
                         <tr>
                            <th width="35%" align="left"><font face="sans-serif"><spring:message  code="ingreso.fechaAsignacion"/></th> 
                            <td width="1%">::</td>
                            <td width="14%"><font face="sans-serif"><fmt:formatDate type="date" value="${bCajaIngreso.fecIngreso}"/></font></td>
                            <th width="35%" align="left"><font face="sans-serif"><spring:message  code="report.arching.surplus"/></font></th> 
                            <td width="1%">::</td>
                            <td width="14%"><font face="sans-serif">
                                <c:if  test="${saldoAnterior != null}">                              
                                    <fmt:formatNumber value="${saldoAnterior.saldo}" pattern="0.00"/> 
                                </c:if>
                                  <c:if  test="${saldoAnterior == null}">                              
                                    0.00
                                  </c:if>
                                </font></td>
                         </tr>
                         <tr>
                            <th width="35%" align="left"><font face="sans-serif"><spring:message code="report.arching.totalAmount"/></font></th> 
                            <td width="1%">::</td>
                            <td width="14%"><font face="sans-serif"><fmt:formatNumber value="${bCajaIngreso.monto - saldoAnterior.saldo}" pattern="0.00"/> </font></td>
                        </tr>
                </table> 
          </td>
        </tr>
     </thead>
     <tbody>
         <tr><td align="center"><br><br>
             <table width="90%" border="1" cellspacing="0" cellpadding="0">
                <tr align="center" valign="middle">
                    <th colspan="4"><spring:message code="report.account.detail"/></th>
                </tr>
                <tr>
                   
                    <th><center><spring:message code="departure.expenses.code"/></center></th>
                    <th><center><spring:message code="report.minor.purchases.budget"/> </center></th>
                    <th><center><spring:message code="report.noteOrder.date"/></center></th>
                    <th><center><spring:message code="expenses.total"/></center></th>
                </tr>
           
            <c:set var="cont" value="0" />
              <c:set var="tGasto" value="0" />
            <c:forEach items="${lTiposCuenta}" var="tc">
                <c:set var="cont" value="${cont+1}" />
                <tr>
                   
                    <td>${tc.cjaTiposGastos.nroTipoGasto}</td>
                    <td>${tc.cjaTiposGastos.tipoGasto}</td>
                    <td><fmt:formatDate type="date" value="${tc.fecGasto}"/></td>
                    <td><fmt:formatNumber value="${tc.totalG}" pattern="0.00"/></td>
                </tr>
                 <c:set var="tGasto" value="${tGasto + tc.totalG}" />
            </c:forEach>
                <tr><td colspan="3"><b>Total Gasto Caja</b></td>
                    <td><fmt:formatNumber value="${tGasto}" pattern="0.00"/></td>
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