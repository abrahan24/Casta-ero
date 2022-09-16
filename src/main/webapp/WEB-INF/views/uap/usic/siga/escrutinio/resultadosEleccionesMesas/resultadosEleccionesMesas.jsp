<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<head>
          <style>

        </style>
       
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
		innerRadius: "0%",
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
<div class="container">      <section class="content-header">
        <div class="row">
            <div class="col-md-10 mb-3">
                 <p class="h1"> <i class="fa fa-money" aria-hidden="true"></i>  <spring:message  code="sufragio.resultado.repo.mesas.title"/> </p>
            </div>
        </div>
        <hr align="left" noshade="noshade" size="10" width="100%" />
    </section>
    <!--   ======= Inicio Tabla ===================   -->
    <section>
     <c:choose>
         <c:when test="${operation eq 'table' }">
    	 <center>
        <p class="h3"><i class="fa fa-list-alt" aria-hidden="true"></i> <spring:message  code="sufragio.table.titulo.resumen"/></p> 
    </center>
    <br>
     <table class="table">
                <thead>
                    <tr class="trIngreso">
                      <th><spring:message  code="sufragio.form.campo.facultad"/></th>
                      <th><spring:message  code="sufragio.form.campo.eleccion"/></th>
                      <th><spring:message  code="sufragio.form.campo.estamento"/></th>
                      <th><spring:message  code="ingreso.gestion"/></th>
                      <th><spring:message  code="income.option"/></th>
                    </tr>
                  </thead>
                  <tbody>
                  <c:forEach var="lea" items="${requestScope.lEscrutinioActas}">
                    <tr id="thing">
                      <td>${lea.facultad}</td>
                      <td>${lea.eleccion}</td>
                      <td>${lea.estamento}</td>
                      <td>${lea.gestion}</td>
                      <td ><form  method="post" action="${lstsum}">
                                <input type="hidden" name="idFacultad" value="${lea.idFacultad}">
                                <input type="hidden" name="idEleccion" value="${lea.idEleccion}">
                                <input type="hidden" name="idEstamento" value="${lea.idEstamento}">
                                <button type="submit" class="btn btn-success"><i class="fa fa-pencil-square-o" aria-hidden="true"></i> Siguiente</button> 
                              </form></center>
                           </td>
                       </tr>
                  </c:forEach>
               </tbody>
               <tfoot>
                <tr class="trIngreso">
                   <th><spring:message  code="sufragio.form.campo.facultad"/></th>
                      <th><spring:message  code="sufragio.form.campo.eleccion"/></th>
                      <th><spring:message  code="sufragio.form.campo.estamento"/></th>
                      <th><spring:message  code="ingreso.gestion"/></th>
                      <th><spring:message  code="income.option"/></th>
                    </tr>
               </tfoot>
         </table> 
         <br>
           <hr align="left" noshade="noshade" size="10" width="100%" />
         </c:when>
      </c:choose>  
    </section>
    <!--  ========= Final de la Tabla ==================== --> 
    <section>
     <c:choose>
         <c:when test="${operation eq 'lsum' }">
    	 <center>
        <p class="h3"><i class="fa fa-list-alt" aria-hidden="true"></i>  <spring:message  code="sufragio.tabla.titulo.res.mesas"/></p> 
    </center>
    <br>
    
     <table class="table">
                <thead>
                    <tr class="trIngreso">
                      <th><spring:message  code="sufragio.form.campo.facultad"/></th>
                      <th><spring:message  code="sufragio.form.campo.estamento"/></th>
                       <th><spring:message  code="sugragio.menu.enlace.mesa"/></th>
                      <th><spring:message  code="sufragio.form.campo.vemitidos"/></th>
                      <th><spring:message  code="sufragio.form.campo.vnulos"/></th>
                      <th><spring:message  code="sufragio.form.campo.vblancos"/></th>
                      <th><spring:message  code="sufragio.form.campo.vvalidos"/></th>
                      <th><spring:message  code="sufragio.form.campo.vfrentes"/></th>
                      <th><spring:message  code="income.option"/></th>
                    </tr>
                  </thead>
                  <tbody>
                  <c:forEach var="lsm" items="${requestScope.lResultadosMesas}">
                     <tr id="thing">
                      <td>${lsm.facultad}</td>
                      <td>${bEstamentos.estamento}</td>
                       <td>${lsm.mesa}</td>
                      <td>${lsm.votosEmitidos}</td>
                      <td>${lsm.votosNulos}</td>
                      <td>${lsm.votosBlancos}</td>
                      <td >${lsm.votosValidos}</td>
                      <td >${lsm.votosFrente}</td>
                      <td><form  method="post" action="${urlimp}" target="blank">
                                <input type="hidden" name="idFacultad" value="${idFacultad}">
                                <input type="hidden" name="idEleccion" value="${idEleccion}">
                                <input type="hidden" name="idEstamento" value="${idEstamento}">
                                <button type="submit" class="btn btn-success"><i class="fa fa-pencil-square-o" aria-hidden="true"></i> <spring:message  code="expenses.btn.print"/></button> 
                              </form>
                              </td>
                        </tr>
                </c:forEach>
               <c:forEach var="ls" items="${requestScope.lResultadosSufragio}">
                     <tr id="thing">
                      <td>${ls.facultad}</td>
                      <td>${bEstamentos.estamento}</td>
                       <td>${ls.mesa}</td>
                      <td>${ls.votosEmitidos}</td>
                      <td>${ls.votosNulos}</td>
                      <td>${ls.votosBlancos}</td>
                      <td >${ls.votosValidos}</td>
                      <td >${ls.votosFrente}</td>
                      <td><form  method="post" action="${urlimp}" target="blank">
                                <input type="hidden" name="idFacultad" value="${idFacultad}">
                                <input type="hidden" name="idEleccion" value="${idEleccion}">
                                <input type="hidden" name="idEstamento" value="${idEstamento}">
                                <button type="submit" class="btn btn-success"><i class="fa fa-pencil-square-o" aria-hidden="true"></i> <spring:message  code="expenses.btn.print"/></button> 
                              </form>
                              </td>
                    </tr>
                  </c:forEach>
               </tbody>
               <tfoot>
                   <tr class="trIngreso">
                        <th><spring:message  code="sufragio.form.campo.facultad"/></th>
                      <th><spring:message  code="sufragio.form.campo.estamento"/></th>
                       <th><spring:message  code="sugragio.menu.enlace.mesa"/></th>
                      <th><spring:message  code="sufragio.form.campo.vemitidos"/></th>
                      <th><spring:message  code="sufragio.form.campo.vnulos"/></th>
                      <th><spring:message  code="sufragio.form.campo.vblancos"/></th>
                      <th><spring:message  code="sufragio.form.campo.vvalidos"/></th>
                      <th><spring:message  code="sufragio.form.campo.vfrentes"/></th>
                      <th><spring:message  code="income.option"/></th>
                    </tr>
               </tfoot>
         </table> 
         <br>
          <div class="form-group row mt-12">
            <div class="col-sm-12" id="chartContainer" style="height: 370px; max-width: 920px; margin: 0px auto;"></>
            </div> 
        </div> 
           <hr align="left" noshade="noshade" size="10" width="100%" />
         </c:when>
      </c:choose>  
    </section>
    <!--  ========= Final de la Tabla ==================== --> 
    
    
    </div>
    
    