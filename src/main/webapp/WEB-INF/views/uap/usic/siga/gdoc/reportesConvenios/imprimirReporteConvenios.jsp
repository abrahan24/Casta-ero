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
                             <tr><td height="50" align="center" valign="middle"><font size="5" face="sans-serif"><b>REPORTES CONVENIOS</b></font></td></tr>
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
        <tr><td align="center"><br><h1><font face="sans-serif"><spring:message code="report.contracts.print.title"/></font></h1></td></tr>
        <tr><td width="100%"><br><br>
            <table width="100%" border="1" cellspacing="0" cellpadding="0">
               <tr class="trIngreso">
                <th  align="center"><spring:message  code="items.year"/></th>
                <th  align="center"><spring:message  code="contracts.form.contract.modality"/></th>
              </tr>
            <tr id="thing">
                <td align="center">${gestion}</td>
                <td align="center">${bConvneios.gdocTiposConvenios.tipoConvenio}</td>
               </tr>
            </table>
          </td>
        </tr>
     </thead>
     <tbody>
         <tr><td align="center"><br><br>
              <table  width="100%" border="1" cellspacing="0" cellpadding="0">
                    <thead>
                        <tr>
                           
                           		 <th><spring:message  code="gdoc.convenio.form.representante"/></th>
                                <th><spring:message  code="gdoc.resol.form.nro.folio"/></th>
                                <th><spring:message  code="gdoc.convenio.form.nro.convenio"/></th>
                                <th><spring:message  code="items.year"/></th>
                                <th><spring:message  code="gdoc.resol.form.autoridad"/></th>
                                <th><spring:message  code="gdoc.convenio.form.objeto.convenio"/></th>
                        </tr>
                    </thead>
                    
                    <tbody>
                        <c:set var="cont" value="0" />
                        <c:forEach items="${lConvneios}" var="lcnv">
                            <c:set var="cont" value="${cont+1}" />
                            <tr>
                                  <td>${lcnv.gdocRepresentantes.personas.paterno}</td>
                                    <td>${lcnv.nroFolio}</td>
                                    <td>${lcnv.nroConvenio}</td>
                                    <td>${lcnv.gestion}</td>
                                    <td>${lcnv.gdocAutoridades.personas.nombres} ${lres.gdocAutoridades.personas.paterno} ${lres.gdocAutoridades.personas.materno}</td>
                                    <td>${lcnv.objetoConvenio}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                    <tfoot>
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