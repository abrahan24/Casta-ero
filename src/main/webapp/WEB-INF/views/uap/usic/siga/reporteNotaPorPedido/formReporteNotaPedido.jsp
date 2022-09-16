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
        <title>Nota de Pedido</title>
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
                             <tr><td height="50" align="center" valign="middle"><font size="5" face="sans-serif"><b><spring:message  code="form.name.note"/></b></font></td></tr>
                          </table>
                      </td>
                     <td width="20%" align="center"> 
                           <table border="1" width="100%" height="100%" cellspacing="0" cellpadding="0">  
                               <tr><td   align="left"><font size="1" face="sans-serif">&nbsp;&nbsp;&nbsp;&nbsp;FORM: 001-C </font></td></tr>
                               <tr><td  ><font size="1" face="sans-serif">&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="form.version"/></font></td></tr>
                               <tr><td  align="left" ><font size="1" face="sans-serif">&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript: window.print()'><spring:message code="form.validity"/></a></font></td> </tr> 
                           </table>
                     </td>
                  </tr>
             </table>
           </td>
        </tr>
        <tr><td align="center"><br><h1><font face="sans-serif"><spring:message  code="report.noteOrder.title.report"/></font></h1></td></tr>
        <tr><td align="center"><h3><font face="sans-serif">(<spring:message  code="report.noteOrder.title.sub"/> ${bSaldo.porcentajeGasto} <spring:message  code="report.noteOrder.title.sub1"/> )</font></h3></td></tr>
        <tr><td width="100%"><br><br>
                <table border="0" width="100%" cellspacing="0" cellpadding="0">
                         <tr>
                            <th width="14%" align="left"><font face="sans-serif"><spring:message  code="report.noteOrder.name"/></font></th> 
                            <td width="1%">::</td>
                            <td width="35%"><font face="sans-serif"> ${bPersona.nombres} ${bPersona.paterno} ${bPersona.materno}</font></td>
                            <th width="14%" align="left"><font face="sans-serif"><spring:message  code="units.unit"/></font></th> 
                            <td width="1%">::</td>
                            <td width="35%"><font face="sans-serif">${bPersonalAdministrativo.insUnidadesFuncionales.unidadFuncional}</font></td>
                        </tr>
                         <tr>
                            <th width="14%" align="left"><font face="sans-serif"><spring:message  code="cargo.cargo"/></th> 
                            <td width="1%">::</td>
                            <td width="35%"><font face="sans-serif"> ${bPersonalAdministrativo.pnlCargos.cargo}</font></td>
                            <th width="14%" align="left"><font face="sans-serif"><spring:message  code="report.noteOrder.date"/></font></th> 
                            <td width="1%">::</td>
                            <td width="35%"><font face="sans-serif"><fmt:formatDate type="date" value="${bCjaGastos.fecGasto}"/></font></td>
                         </tr>
                </table> 
          </td>
        </tr>
     </thead>
     <tbody>
         <tr><td align="center"><br><br>
             <table width="90%" border="1" cellspacing="0" cellpadding="0">
                <tr><th><font face="sans-serif"><spring:message  code="report.noteOrder.description"/></font></th>
                  
                </tr>
                <tr>
                  <td><font face="sans-serif">${bCjaGastos.descripcion}</font></td>
                  
                </tr>
            </table>
          </td>
        </tr>
      </tbody>
     <tfoot>
         <tr><td><br><br><font face="sans-serif"><spring:message  code="report.noteOrder.approved"/>:</font></td></tr>
         <tr><td><br><br>
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                   <tr><td align="center"><br><br>______________________</td>
                       <td align="center"><br><br>______________________</td>
                   </tr>
                   <tr><td align="center"><spring:message  code="report.noteOrder.signatureApplicant"/></td>
                       <td align="center"><spring:message  code="report.noteOrder.signatureBoss"/></td>
                   </tr>
                   <tr><th align="center"><br><br><spring:message  code="report.noteOrder.proof"/>:</th>
                       <th align="center"><br><br><spring:message  code="report.noteOrder.recived"/>:</th>
                   </tr>
                   <tr><td align="center"><spring:message  code="report.noteOrder.date"/>_________________</td>
                       <td align="center"><spring:message  code="report.noteOrder.date"/>_________________</td>
                   </tr>
                   <tr><td align="center"><br><br>______________________</td>
                       <td align="center"><br><br>______________________</td></tr>
                   <tr><td align="center"><spring:message  code="report.noteOrder.signatureCashier"/></td>
                       <td align="center"><spring:message  code="report.noteOrder.signatureApplicant"/></td></tr>
                </table>
             </td>
          </tr>
     </tfoot>
  </table>
 </body>
</html>