<jsp:useBean id="now" class="java.util.Date" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:formatDate value="${bean.date}" pattern="dd-MM-yyyy" />
<html>
    <head>
        <style>
            
        </style>
        <title>Compras Menores</title>
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
                             <tr><td height="30" align="center"><font size="3" face="sans-serif"><b><spring:message code="form.title"/></b></font></td> </tr>    
                             <tr><td height="50" align="center" valign="middle"><font size="5" face="sans-serif"><b><spring:message code="form.name.minor"/></b></font></td></tr>
                          </table>
                      </td>
                     <td width="20%" align="center"> 
                           <table border="1" width="100%" height="100%" cellspacing="0" cellpadding="0">  
                               <tr><td align="left"><font size="1" face="sans-serif">&nbsp;&nbsp;&nbsp;&nbsp;FORM: 002-C </font></td></tr>
                               <tr><td ><font size="1" face="sans-serif">&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="form.version"/></font></td></tr>
                               <tr><td  align="left" ><font size="1" face="sans-serif">&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript: window.print()'><spring:message code="form.validity"/></a></font></td> </tr> 
                           </table>
                     </td>
                  </tr>
             </table>
           </td>
        </tr>
        <tr><td align="center"><br><h1><font face="sans-serif"><spring:message code="report.minor.purchases.title"/></font></h1></td></tr>
        <tr><td align="right"><font face="sans-serif"><b>NRO.</b> ${bCjaGastos.nroDocumento}</font></td></tr>
        <tr><td align="center"> <font face="sans-serif"><b>Bs.</b> <fmt:formatNumber value="${bCjaGastos.totalG}" pattern="0.00"/> <b> SON: </b> ${literal} <b><spring:message code="report.minor.purchases.currency"/></b></font></td></tr>

        <tr><td width="100%"><br><br>
                <table border="0" width="100%" cellspacing="0" cellpadding="0">
                    <tr><td align="center"><font face="sans-serif"><spring:message code="report.minor.purchases.amount"/>: </b> ${bCjaGastos.cjaProveedores.proveedor} </td><br></tr>
                </table> 
          </td>
        </tr>
     </thead>
     <tbody>
         <tr><td align="center"><br><br>
             <table width="90%" border="1" cellspacing="0" cellpadding="0">
                <tr><th><font face="sans-serif"><spring:message code="report.minor.purchases.concept"/></font></th>
                    <th><font face="sans-serif"><spring:message code="report.minor.purchases.budget"/></font></th>
                </tr>
                <tr>
                  <td><font face="sans-serif">${bCjaGastos.descripcion}</font></td>
                  <td><font face="sans-serif">${bCjaGastos.cjaTiposGastos.nroTipoGasto} - ${bCjaGastos.cjaTiposGastos.tipoGasto}</font></td>
                </tr>
            </table>
          </td>
        </tr>
      </tbody>
     <tfoot>
         <tr align="center"><td><br><br><font face="sans-serif">Cobija, <fmt:formatDate type="date" value="${bCjaGastos.fecGasto}"/></font></td></tr>
         <tr><td><br><br><br><br>
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                   <tr align="center"><td align="center" colspan="2">______________________</td></tr>
                   <tr align="center"><td align="center" colspan="2"><spring:message code="report.minor.purchases.manager"/></td>
                </tr>
                 <tr>
                       <td align="center"><br><br><br>______________________</td>
                       <td align="center"><br><br><br>______________________</td>
                   </tr>
                   <tr>
                       <td align="center">D.A.F</td>
                       <td align="center">RECTOR</td>
                   </tr>
                </table>
             </td>
          </tr>
     </tfoot>
  </table>
 </body>
</html>