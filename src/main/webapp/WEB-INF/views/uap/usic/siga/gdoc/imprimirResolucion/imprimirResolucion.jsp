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
           <table width="100%" border="0" cellspacing="0" cellpadding="0">  
                <tr><td align="center" width="20%" ><img border="0" src="${pageContext.request.contextPath}/resource/images/logs/logouap.jpg" class="img-rounded"  class="img-responsive" alt="UAP" width="65" height="80"></td>
                     <td width="60%" height="100%"> <table border="0" width="100%" cellspacing="0" cellpadding="0">  
                             <tr><td height="30" align="center"><font size="5" face="sans-serif"><b>UNIVERSIDAD AMAÓNICA DE PANDO </b></font></font></td> </tr>    
                             <tr><td height="50" align="center" valign="middle"><font size="2" face="sans-serif"><b>Honorable Consejo Universitario </b></font></td></tr>
                          </table>
                      </td>
                     <td width="20%" align="center"> 
                           <table border="0" width="100%" height="100%" cellspacing="0" cellpadding="0">  
                               <tr><td   align="left"><font size="1" face="sans-serif"></font></td></tr>
                               <tr><td  ><font size="1" face="sans-serif"></font></td></tr>
                               <tr><td  align="left" ><font size="1" face="sans-serif"><a href='javascript: window.print()'></a></font></td> </tr> 
                           </table>
                     </td>
                  </tr>
             </table>
             <hr>
           </td>
        </tr>
        <tr><td align="center"><br><br><h3><font face="sans-serif">${bResolucion.tituloResolucion}</font></h3></td></tr>
      
     </thead>
     <tbody>
         <tr><td><br><br>
                             ${bResolucion.vistosResolucion}
                   </td> 
           </tr>
           <tr><td><br>
                             ${bResolucion.considerandosResolucion}
                   </td> 
           </tr>
           <tr><td><br>
                             ${bResolucion.porlotantoResolucion}
                   </td> 
           </tr>
           <tr><td><br>
                             ${bResolucion.resuelveResolucion}
                   </td> 
           </tr>
              <tr><td><br>
                             ${bResolucion.esdadoResolucion}
                   </td> 
           </tr>
           
                
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