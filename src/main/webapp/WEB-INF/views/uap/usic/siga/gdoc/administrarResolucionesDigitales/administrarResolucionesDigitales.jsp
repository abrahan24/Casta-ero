<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<head>
    <link rel="stylesheet" type="text/css"  href=" <c:url value="/resource/admIngresos/ingresos.css"/>" />
    <script src="<spring:url value="/resource/gdoc/resolucion.js" />" type="text/javascript"></script>
    <script type="text/javascript">
    </script>
      <script>

</script>
    
 </head>
<div class="container">      <section class="content-header">
        <div class="row">
            <div class="col-md-10 mb-3">
                 <p class="h1"> <i class="fa fa-money" aria-hidden="true"></i> Resoluciones Digitales <spring:message  code="gdoc.resol.titulo.modulo"/> </p>
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
                <p class="h3"><i class="fa fa-list-alt" aria-hidden="true"></i> <spring:message  code="gdoc.resol.titulo.tabla.reporte"/></p> 
             <br>
             </center>
         <table id="tComprobantes"  class="table" style="width:100%">
                        <thead>
                            <tr>
                                <th><spring:message  code="gdoc.resol.form.nro.resolucion"/></th>
                                <th><spring:message  code="items.year"/></th>
                                <th><spring:message  code="gdoc.resol.form.autoridad"/></th>
                                <th><spring:message  code="voucher.viem.pdf"/></th>
                                <th><spring:message  code="expenses.modify"/> </th>
                                <th><spring:message  code="expenses.remove"/></th>
                            </tr>
                        </thead>
                        <tbody>
                        
                            <c:set var="cont" value="0" />
                            <c:forEach items="${lResoluciones}" var="lres">
                                  <c:set var="cont" value="${cont+1}" />
                                   <tr>
                                    <td>${lres.nroResolucion}</td>
                                    <td>${lres.gestion}</td>
                                    <td>${lres.tituloResolucion}</td>
                                    <td>
                                        <form  method="post" action="${openResol}">
                                            <input type="hidden" name="idGdocResolucionDigital" value="${lres.idGdocResolucionDigital}">
                                            <span class="icon-input-btn"> <i class="fa fa-pencil-square-o yvela" aria-hidden="true"> </i> <input class="btn btn-success" type="submit"  value="<spring:message  code="expenses.modify"/>" > </span>
                                        </form>
                                    </td>
                                    <td>
                                        <form  method="post" action="${urlM}">
                                            <input type="hidden" name="idGdocResolucionDigital" value="${lres.idGdocResolucionDigital}">
                                            <span class="icon-input-btn"> <i class="fa fa-pencil-square-o yvela" aria-hidden="true"> </i> <input class="btn btn-success" type="submit"  value="<spring:message  code="expenses.modify"/>" > </span>
                                        </form>
                                    </td>
                                    <td>
                                        <form name="Delet" action="${urlEI}" method="post">
                                            <input type="hidden" name="idGdocResolucionDigital" value="${lres.idGdocResolucionDigital}">
                                            <span class="icon-input-btn"> <i class="fa fa-times yvela" aria-hidden="true"> </i> <input class="btn btn-danger" type="submit"  value="<spring:message  code="expenses.remove"/>"> </span>
                                        </form>
                                    </td>
                                </tr>
                          </c:forEach>
                            
                        </tbody>
                        <tfoot>
                            <tr>
                                   <th><spring:message  code="gdoc.resol.form.nro.resolucion"/></th>
                                <th><spring:message  code="items.year"/></th>
                                <th><spring:message  code="gdoc.resol.form.autoridad"/></th>
                                <th><spring:message  code="voucher.viem.pdf"/></th>
                                <th><spring:message  code="expenses.modify"/> </th>
                                <th><spring:message  code="expenses.remove"/></th>
                           </tr>
                        </tfoot>
                    </table>
         
        </c:when>
    </c:choose>
  
          <!----------------Formulario Registro Resolusiones Digitales ======================================================== -->
<br>
  <spring:url var="formUrl" value="cjaIngresosFondos" />
    <c:choose>
        <c:when test="${operation eq 'reg' }">
             <center> <p class="h3"><i class="fa fa-th" aria-hidden="true"></i><spring:message  code="gdoc.resol.titulo.form.nuevo"/></p> </center>
            <br>
              <form:form modelAttribute="gdocResolucionesDigitales" action="${nRsol}" method="post" enctype="multipart/form-data">
               <form:hidden path="GdocConsejos.idGdocConsejo"  id="idConsejo" value="${bGdocConsejos.idGdocConsejo}" />
               <form:hidden path="GdocGestionConsejos.idGdocGestionConsejo"  id="idGestConsejo" value="${bGestionConsejo.idGdocGestionConsejo}" />
               <input type="hidden" name="sigla"  id="sigla2" value="${bGdocConsejos.sigla}" />
               
               <div class="form-group row">
                    <spring:message  code="gdoc.resol.form.msg.nro.resolucion" var="nres"/>
                    <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="gdoc.resol.form.nro.resolucion"/></label>
                    <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                    <div class="col-sm-6" id="combo1"> 
                        <form:input class="form-control form-control-lg" path="nroResolucion"  id="nroRes" onkeyUp="return ValNumero(this);" placeholder="${nres}" />
                     </div>
                    <div class="col-sm-3" id="combo1"> 
                        <small id="emailHelp" class="form-text text-muted">
                            <spring:message  code="gdoc.resol.form.msg.nro.resolucion"/>
                         </small>
                          <small id="ci" class="form-text text-muted formFieldError">
                                <form:errors path="nroResolucion" cssClass="alert alert-danger"/>
                            </small>
                     </div>
                </div>
               
                <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="expenses.description"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6" id="combo1"> 
                            <spring:message  code="expenses.description.msg" var="description"/>
                            <form:textarea  path="tituloResolucion" class="form-control form-control-lg" placeholder="${description}" rows="3" cols="20" /> 
                        </div>
                        <div class="col-sm-3" id="comd"> 
                            <small id="emailHp" class="form-text text-muted">
                                <spring:message  code="expenses.description.msg"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="tituloResolucion" />
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-2 col-form-label"><spring:message  code="gdoc.resol.form.autoridad"/></label>
                        <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div   id="content"   class="col-sm-6 " >
                                     <form:textarea  path="vistosResolucion" id="vistosResolucion" class="form-control" />
				          </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                  <spring:message  code="gdoc.resol.form.msg.autoridad"/>
                            </small>
                            <small id="ci" class="form-text text-muted formFieldError">
                                <form:errors path="vistosResolucion" cssClass="alert alert-danger"/>
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-2 col-form-label"><spring:message  code="gdoc.resol.form.autoridad"/></label>
                        <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div   id="content"   class="col-sm-6 " >
                                    <form:textarea  path="considerandosResolucion" id="considerandosResolucion" class="form-control" />
				         </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                  <spring:message  code="gdoc.resol.form.msg.autoridad"/>
                            </small>
                            <small id="ci" class="form-text text-muted formFieldError">
                                <form:errors path="considerandosResolucion" cssClass="alert alert-danger"/>
                            </small>
                        </div>
                    </div>
                <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-2 col-form-label"><spring:message  code="gdoc.resol.form.autoridad"/></label>
                        <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div   id="content"   class="col-sm-6 " >
                                   <form:textarea  path="porlotantoResolucion" id="porlotantoResolucion" class="form-control" />
				        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                  <spring:message  code="gdoc.resol.form.msg.autoridad"/>
                            </small>
                            <small id="ci" class="form-text text-muted formFieldError">
                                <form:errors path="porlotantoResolucion" cssClass="alert alert-danger"/>
                            </small>
                        </div>
                    </div>
                <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-2 col-form-label"><spring:message  code="gdoc.resol.form.autoridad"/></label>
                        <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div   id="content"   class="col-sm-6 " >
                                   <form:textarea  path="resuelveResolucion" id="resuelveResolucion" class="form-control" />
				        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                  <spring:message  code="gdoc.resol.form.msg.autoridad"/>
                            </small>
                            <small id="ci" class="form-text text-muted formFieldError">
                                <form:errors path="resuelveResolucion" cssClass="alert alert-danger"/>
                            </small>
                        </div>
                    </div>
                <div class="form-group row">
                        <label for="ciExpedido" class="col-sm-2 col-form-label"><spring:message  code="gdoc.resol.form.autoridad"/></label>
                        <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div   id="content"   class="col-sm-6 " >
                                  <form:textarea  path="esdadoResolucion" id="esdadoResolucion" class="form-control" />
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                  <spring:message  code="gdoc.resol.form.msg.autoridad"/>
                            </small>
                            <small id="ci" class="form-text text-muted formFieldError">
                                esdadoResolucion<form:errors path="resuelveResolucion" cssClass="alert alert-danger"/>
                            </small>
                        </div>
                    </div>     
           <div class="form-group row">
                    <spring:message  code="gdoc.resol.form.msg.fecha.resolucion" var="obj"/>
                    <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="gdoc.resol.form.fecha.resolucion"/></label>
                   <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                    <div class="col-sm-6" id="combo1"> 
                          <form:input type="date" path="fecResolucion" class="form-control form-control-lg"    placeholder="${ingreso}"/>
                     </div>
                    <div class="col-sm-3" id="combo1"> 
                        <small id="emailHelp" class="form-text text-muted">
                            <spring:message  code="gdoc.resol.form.msg.fecha.resolucion"/>
                         </small>
                          <small id="ci" class="form-text text-muted formFieldError">
                                <form:errors path="fecResolucion" cssClass="alert alert-danger"/>
                            </small>
                     </div>
                </div>           
              <div class="form-group row">
                    <spring:message  code="items.year" var="year"/>
                    <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="items.year"/></label>
                   <label class="col-sm-1 col-form-label text-right">  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                    <div class="col-sm-6" id="combo1"> 
                        <form:input class="form-control form-control-lg" path="gestion"  id="gestion" onkeyUp="return ValNumero(this);" placeholder="${year}" value="${gestion}" />
                     </div>
                    <div class="col-sm-3" id="combo1"> 
                        <small id="emailHelp" class="form-text text-muted">
                            <spring:message  code="items.year.msg"/>
                         </small>
                          <small id="ci" class="form-text text-muted formFieldError">
                                <form:errors path="gestion" cssClass="alert alert-danger"/>
                            </small>
                     </div>
                </div>
                  
                    
                                                         
                <div class="form-group row">
                        <label for="msg" class="col-sm-2 col-form-label text-right"></label>
                        <label for="msg" class="col-sm-10 col-form-label"><span class="parpadea text"><i class="fa fa-info-circle" aria-hidden="true"></i></span> &nbsp;&nbsp;&nbsp;<spring:message  code="people.warning"/> </label>
                    </div>   

                    <hr align="left" noshade="noshade" size="10" width="100%" />
                    <div class="form-group row mt-12">
                        <div class="col-sm-3" id="combo1">
                        </div> 
                        <div class="col-sm-3" id="combo1">
                            <span class="icon-input-btn"><i class="fa fa-pencil-square-o yvela" aria-hidden="true"></i> <input class="btn btn-success" type="submit" value="<spring:message  code="institution.register"/>"></span>
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                        </div>
                        <div class="col-sm-3" id="combo1">
                            <a href="<spring:url value="/ingresos/formCjaIngresos"/>" class="btn btn-yvela-green"><i class="fa fa-undo yvela" aria-hidden="true"></i>  <spring:message  code="expenses.btn.cancel"/></a>
                        </div> 
                    </div>
             </form:form>
         </c:when>
    </c:choose>
        <!-----------------------Fin Formulario Registro Ingresosss ======================================================== -->
              <br><br>
  <hr align="left" noshade="noshade" size="10" width="100%" />      
   </section>
</div>
