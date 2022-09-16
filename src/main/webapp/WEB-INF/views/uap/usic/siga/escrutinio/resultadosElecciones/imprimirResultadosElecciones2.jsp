<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<head>
          <style>

        </style>
        <title>Resultado de Cómputo Oficial </title>
        <link rel="stylesheet" type="text/css"  href=" <c:url value="/resource/vendor/bootstrap/css/bootstrap.css"/>" />
        <script src="https://canvasjs.com/assets/script/jquery-1.11.1.min.js"></script>
		<script src="https://canvasjs.com/assets/script/jquery.canvasjs.min.js"></script>
		
                
<script>
window.onload = function () {

var options = {
	animationEnabled: true,
	title: {
		text: "<spring:message  code="sufragio.resultado.repo.title"/>"
	},
	data: [{
		type: "doughnut",
		innerRadius: "40%",
		showInLegend: true,
		legendText: "{label}",
		indexLabel: "{label}: #percent%",
		dataPoints: [
			<c:forEach var="lsm" items="${requestScope.lResultadosSufragio}">
			{ label: "<spring:message  code="sufragio.form.campo.vblancos"/>", y: <fmt:formatNumber value="${lsm.votosBlancos}" pattern="0"/> },
            { label: "<spring:message  code="sufragio.form.campo.vnulos"/>", y: <fmt:formatNumber value="${lsm.votosNulos}" pattern="0"/> },
            { label: "${lsm.frente}", y: <fmt:formatNumber value="${lsm.votosFrente}" pattern="0"/> }
			</c:forEach>
		]
	}]
};
$("#chartContainer").CanvasJSChart(options);

}
</script>
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
                                        <tr><td height="50" align="center" valign="middle"><font size="5" face="sans-serif"><b> <spring:message code="sufragio.table.titulo"/></b></font></td></tr>
                                    </table>
                                </td>
                                <td width="20%" align="center"> 
                                    <table border="1" width="100%" height="100%" cellspacing="0" cellpadding="0">  
                                        <tr><td   align="left"><font size="1" face="sans-serif">&nbsp;&nbsp;&nbsp;&nbsp;FORM: 003-C </font></td></tr>
                                        <tr><td  ><font size="1" face="sans-serif">&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="form.version"/></font></td></tr>
                                        <tr><td  align="left" ><font size="1" face="sans-serif">&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript: window.print()'><spring:message code="form.validity"/></a></font></td> </tr> 
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr><td align="center"><br><h1><font face="sans-serif"><spring:message code="sufragio.table.titulo.reprote"/></font></h1></td></tr>
                <tr><td width="100%"><br><br>
              <br>
               <table cellpadding="0" cellspacing="0" border="1"  width="100%">
                    <tr >
                      <th width="10%"><spring:message  code="sufragio.form.campo.facultad"/></th>
                      <th width="2%">::</th>
                      <td width="88%">${bFacultad.facultad}</td>
                    </tr>
                    <tr>
                      <th width="10%"><spring:message  code="sufragio.form.campo.eleccion"/></th>
                      <th width="2%">::</th>
                      <td width="88%">${bEleccion.eleccion}</td>
                    </tr>
                      <tr>
                      <th width="10%"><spring:message  code="sufragio.form.campo.estamento"/></th>
                      <th width="2%">::</th>
                      <td width="88%">${bEstamento.estamento}</td>
                    </tr>
                  </table>  
              <br>
               
     <table cellpadding="0" cellspacing="0" border="1"  width="100%">
                <thead>
                    <tr class="trIngreso">
                      <th><spring:message  code="sufragio.table.datos"/></th>
                      <th><spring:message  code="sufragio.form.campo.vemitidos"/></th>
                      <th><spring:message  code="sufragio.form.campo.vnulos"/></th>
                      <th><spring:message  code="sufragio.form.campo.vblancos"/></th>
                      <th><spring:message  code="sufragio.form.campo.vvalidos"/></th>
                      <th><spring:message  code="sufragio.form.campo.vfrentes"/></th>
                    </tr>
                  </thead>
                  <tbody>
                  <c:forEach var="lsm" items="${requestScope.lResultadosSufragio}">
                     <tr id="thing">
                     <td><spring:message  code="sufragio.table.totales"/></td>
                     <td>${lsm.votosEmitidos}</td>
                      <td>${lsm.votosNulos}</td>
                      <td>${lsm.votosBlancos}</td>
                      <td >${lsm.votosValidos}</td>
                      <td >${lsm.votosFrente}</td>
                     </tr>
                      <tr id="thing">
                      <td><spring:message  code="income.option"/></td>
                      <td>${lsm.votosEmitidos}</td>
                      <td> <fmt:formatNumber value="${(lsm.votosNulos*100)/lsm.votosEmitidos}" pattern="0.00"/>%</td>
                      <td><fmt:formatNumber value="${(lsm.votosBlancos*100)/lsm.votosEmitidos}" pattern="0.00"/>%</td>
                      <td >${lsm.votosValidos}</td>
                      <td ><fmt:formatNumber value="${(lsm.votosFrente*100)/lsm.votosEmitidos}" pattern="0.00"/>%</td>
                    </tr>
                  </c:forEach>
               </tbody>
               <tfoot>
                   <tr class="trIngreso">
                       <th><spring:message  code="sufragio.table.datos"/></th>
                      <th><spring:message  code="sufragio.form.campo.vemitidos"/></th>
                      <th><spring:message  code="sufragio.form.campo.vnulos"/></th>
                      <th><spring:message  code="sufragio.form.campo.vblancos"/></th>
                      <th><spring:message  code="sufragio.form.campo.vvalidos"/></th>
                      <th><spring:message  code="sufragio.form.campo.vfrentes"/></th>
                    </tr>
               </tfoot>
         </table> 
                          <br>
                        <hr align="left" noshade="noshade" size="10" width="100%" />
                        <div class="form-group row mt-12">
                            <div class="col-sm-12" id="chartContainer" style="height: 370px; max-width: 920px; margin: 0px auto;"></>
                            </div> 
                        </div> 
                    </td>
                </tr>
            </tbody>
            <tfoot>

                <tr><td><br><br>
                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr><td align="center"><br><br>______________________</td>
                                <td align="center"><br><br>______________________</td>
                            </tr>
                            <tr><td align="center"><spring:message  code="sufragio.table.firma"/></td>
                                <td align="center"><spring:message  code="sufragio.table.firma"/></td>
                            </tr>

                        </table>
                    </td>
                </tr>
            </tfoot>
        </table>
    </body>
 
    <br><br>
    