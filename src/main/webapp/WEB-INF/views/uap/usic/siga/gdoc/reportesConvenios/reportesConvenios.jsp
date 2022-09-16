<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<head>
    <link rel="stylesheet" type="text/css"  href=" <c:url value="/resource/admIngresos/ingresos.css"/>" />
    <script src="<spring:url value="/resource/gdoc/resolucion.js" />" type="text/javascript"></script>
    <script type="text/javascript">
    </script>
 </head>
<div class="container">      <section class="content-header">
        <div class="row">
            <div class="col-md-10 mb-3">
                 <p class="h1"> <i class="fa fa-money" aria-hidden="true"></i>  Reportes -  <spring:message  code="gdoc.convenio.titulo.modulo"/> </p>
            </div>
            <div class="col-sm-2 text-right" id="reporte">
                <c:choose>
                    <c:when test="${busqueda eq 'find' }">
                         <form  method="POST" action="${urlRIn}">
                            <span class="icon-input-btn"><i class="fa fa-user-plus yvela" aria-hidden="true"></i> <input type="submit" class="btn btn-success btn-lg" value="<spring:message  code="income.btn.new"/>"></span>
                        </form>
                    </c:when>
                </c:choose> 
            </div>
        </div>
        <hr align="left" noshade="noshade" size="10" width="100%" />
    </section>
<section> 
     <c:if test="${not empty error}">
        <div class="alert alert-danger">
            <spring:message  code="AbstractUserDetailsAuthenticationProvider.badCredentials"/>
        </div>
    </c:if>
       
    <!--Tabla lista de Todos los Ingresos Registrados -->

    <c:choose>
        <c:when test="${busqueda eq 'find' }">
            <center><br>
                <p class="h3"><i class="fa fa-list-alt" aria-hidden="true"></i> <spring:message  code="gdoc.convenio.titulo.tabla"/></p> 
               <br>
             </center>
         <table id="tComprobantes"  class="table" style="width:100%">
                        <thead>
                            <tr>
                                <th><spring:message  code="items.year"/>-- ${id} -- ${gestion}</th>
                                <th><spring:message  code="gdoc.resol.form.autoridad"/></th>
                                <th><spring:message  code="gdoc.convenio.form.objeto.convenio"/></th>
                                <th><spring:message  code="voucher.viem.pdf"/></th>
                                <th><spring:message  code="expenses.modify"/> </th>
                          </tr>
                        </thead>
                        <tbody>
                           <c:set var="cont" value="0" />
                            <c:forEach items="${lTiposConvenios}" var="lcnv">
                                <c:set var="cont" value="${cont+1}" />
                                <form  method="post" action="${urlList}">
                                 <tr>
                                    <td>${cont}</td>
                                   <td>${lcnv.tipoConvenio}</td>
                                    <td>${lcnv.siglaConvenio}</td>
                                    <td>
                                        <input type="text" name="gestion${lcnv.idGdocTipoConvenio}" class="form-control form-control-lg" >
                                    </td>
                                    <td>
				                          <input type="hidden" name="idGdocTipoConvenio" value="${lcnv.idGdocTipoConvenio}">
				                          <button type="submit" class="btn btn-success"><i class="fa fa-pencil-square-o" aria-hidden="true"></i> Siguiente</button>      
                                    </td>
                              </tr>
                              </form>
                          </c:forEach>          
                        </tbody>
                        <tfoot>
                            <tr>
                                <th><spring:message  code="items.year"/></th>
                                <th><spring:message  code="gdoc.resol.form.autoridad"/></th>
                                <th><spring:message  code="gdoc.convenio.form.objeto.convenio"/></th>
                                <th><spring:message  code="voucher.viem.pdf"/></th>
                                <th><spring:message  code="expenses.modify"/> </th>
                          </tr>
                        </tfoot>
                    </table>
        </c:when>
    </c:choose>
              
         
  <!----------------------- Fin Formulario Eliminar Convenios  ======================================================== -->
              <br><br>
         
             <hr align="left" noshade="noshade" size="10" width="100%" />
   </section>
</div>
