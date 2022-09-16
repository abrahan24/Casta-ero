<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<head>
    <link rel="stylesheet" type="text/css"  href=" <c:url value="/resource/admIngresos/ingresos.css"/>" />
    <script src="<spring:url value="/resource/admIngresos/ingresos.js" />" type="text/javascript"></script>
    <script type="text/javascript">
    </script>
 </head>
<div class="container">      <section class="content-header">
        <div class="row">
            <div class="col-md-10 mb-3">
                 <p class="h1"> <i class="fa fa-money" aria-hidden="true"></i> <spring:message  code="income.title"/> </p>
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
    <br>
    <center>
        <p class="h3"><i class="fa fa-list-alt" aria-hidden="true"></i> <spring:message  code="income.title.balance.summary"/></p> 
    </center>
    <br>
     <table class="table">
                <thead>
                    <tr class="trIngreso">
                      <th><spring:message  code="income.type.income"/></th>
                      <th><spring:message  code="income.amount.entered"/></th>
                      <th><spring:message  code="income.balance"/></th>
                      <th><spring:message  code="income.date.entered"/></th>
                      <th><spring:message  code="income.percentage"/></th>
                      <th><spring:message  code="income.document.number"/></th>
                      <th><spring:message  code="income.option"/></th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr id="thing">
                      <td>${bCajaIngresosUltimo.cjaTiposIngresos.tipoIngreso}</td>
                      <td class="monto">${bCajaIngresosUltimo.monto}</td>
                      <td class="saldo"><fmt:formatNumber value="${bCajaIngresosUltimo.saldo}" pattern="0.00"/></td>
                      <td>${bCajaIngresosUltimo.fecIngreso}</td>
                      <td>${bCajaIngresosUltimo.porcentajeGasto}</td>
                      <td class="ndoc">${bCajaIngresosUltimo.nroDocumento}</td>
                      <td>
                         <c:if test="${bCajaIngresosUltimo.caja == 0}">
                            <form  method="POST" action="${urlClose}">
                                <input type="hidden" name="idCjaIngreso" value="${bCajaIngresosUltimo.idCjaIngreso}">
                               <span class="icon-input-btn"><i class="fa fa-user-plus yvela" aria-hidden="true"></i> <input type="submit" class="btn btn-success btn-lg" value="<spring:message  code="income.close.box"/>"></span>
                            </form>
                         </c:if>
                          <c:if test="${bCajaIngresosUltimo.caja == 1}">
                             <spring:message  code="income.closed.box"/>
                         </c:if>
                      </td>
                   </tr>
               </tbody>
         </table> 
       <!----------------Formulario Registro Ingresos ======================================================== -->

    <hr align="left" noshade="noshade" size="10" width="100%" />

<br>
  <spring:url var="formUrl" value="cjaIngresosFondos" />
    <c:choose>
        <c:when test="${operation eq 'reg' }">
             <center> <p class="h3"><i class="fa fa-th" aria-hidden="true"></i>   <spring:message  code="income.register.form"/></p> </center>
            <br>
            <form:form modelAttribute="cjaIngresos" action="${cjaIng}" method="post">
                <c:set var="idCjaTipoIngresoVal" value="0" />
                <c:set var="montoInicial" value="0" />
                <c:if test="${bCajaIngresosUltimo == null}">
                   <c:set var="idCjaTipoIngresoVal" value="1" />
                   <c:set var="montoInicial" value="${mFunciones.valor}" />
                </c:if>
                <c:if test="${bCajaIngresosUltimo != null}">
                   <c:set var="idCjaTipoIngresoVal" value="2" />
                    <c:set var="montoInicial" value="${(mFunciones.valor - bCajaIngresosUltimo.saldo)}" />
                </c:if>
                <input type="hidden" name="saldoReg" value="${bCajaIngresosUltimo.saldo}" id="saldoReg">
                <form:hidden path="periodo"  id="periodo" value="${periodo}" />
               <div class="form-group row">
                    <label for="ingreso" class="col-sm-2 col-form-label"><spring:message  code="ingreso.tipoIngreso"/></label> 
                    <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                    <div class="col-sm-6">
                        <form:select class="form-control form-control-lg" path="CjaTiposIngresos.idCjaTipoIngreso"  id="tipoIngreso">
                            <c:forEach var="p" items="${requestScope.lTiposIngresos}">
                                <c:if test="${p.idCjaTipoIngreso == idCjaTipoIngresoVal}">
                                  <form:option value="${p.idCjaTipoIngreso}">${p.tipoIngreso}</form:option>
                                </c:if>
                            </c:forEach>
                        </form:select>
                    </div>
                    <div class="col-sm-3" id="comd">
                        <small class="form-text text-muted">
                              <c:choose>
                                    <c:when test="${idMnuTipoF eq 'reg' }">
                                       <a class="btn add" href="#" data-toggle="modal" data-target="#formTipoIngreso"><i class="fa fa-plus-square-o" aria-hidden="true"></i>  <spring:message  code="people.add"/></a></small>
                                    </c:when>
                                </c:choose>
                         </small>
                         <small class="msgError" >
                              <c:choose>
                                    <c:when test="${idMnuTipoF eq 'resp' }">
                                       <span class="parpadea text"> <i class="fa fa-exclamation-triangle" aria-hidden="true"></i> </span> <spring:message  code="income.tiype.income.error"/>
                                    </c:when>
                                </c:choose> 
                            </small>   
                    </div>
                </div>
                <c:if test="${bCajaIngresosUltimo == null}">
                  <div class="form-group row">
                    <spring:message  code="ingreso.monto" var="montom"/>
                    <label for="monto" class="col-sm-2 col-form-label"><spring:message  code="ingreso.monto"/></label>
                    <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                    <div class="col-sm-6">
                         <form:input path="monto"  id="montoReg" class="form-control form-control-lg"  placeholder="${montom}" value="${mFunciones.valor}" readonly="true" />
                    </div>
                    <div class="col-sm-3" id="comd">
                        <small class="form-text text-muted">
                            <spring:message  code="ingreso.monto.msg"/>
                        </small>
                        <small id="emailHp" class="form-text text-muted formFieldError">
                            <form:errors path="monto" cssClass="alert alert-danger"/>
                        </small>
                    </div>
                  </div>
                </c:if>
                <c:if test="${bCajaIngresosUltimo != null}">
                  <div class="form-group row">
                    <spring:message  code="ingreso.monto" var="montom"/>
                    <label for="monto" class="col-sm-2 col-form-label"><spring:message  code="ingreso.monto"/></label>
                    <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                    <div class="col-sm-6">
                        <form:input path="monto"  id="montoReg" class="form-control form-control-lg"  placeholder="${montom}" value="${montoInicial}" onchange="controlarMonto()"/>
                    </div>
                    <div class="col-sm-3" id="comd">
                        <small class="form-text text-muted">
                            <spring:message  code="ingreso.monto.msg"/>
                        </small>
                        <small id="emailHp" class="form-text text-muted formFieldError">
                            <form:errors path="monto" cssClass="alert alert-danger"/>
                        </small>
                    </div>
                  </div>
                </c:if>
                    
                  <div class="form-group row">
                    <spring:message  code="ingreso.fechaAsignacion" var="ingreso"/>
                    <label for="fecIngreso" class="col-sm-2 col-form-label"><spring:message  code="ingreso.fechaAsignacion"/></label>
                    <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                    <div class="col-sm-6">
                        <form:input type="date" path="fecIngreso" class="form-control form-control-lg"    placeholder="${ingreso}"/>  
                    </div>
                    <div class="col-sm-3" id="comd">
                        <small class="form-text text-muted">
                            <spring:message  code="expenses.date.msg"/>
                        </small>
                        <small id="emailHp" class="form-text text-muted formFieldError">
                            <form:errors path="fecIngreso" cssClass="alert alert-danger"/>
                        </small>
                    </div>
                </div>
                  
                  <div class="form-group row">
                    <spring:message  code="income.maximum.amount.msg" var="montoS"/>
                    <label for="montom" class="col-sm-2 col-form-label"><spring:message  code="income.maximum.amount"/></label>
                    <label class="col-sm-1 col-form-label text-right" > </label>
                    <div class="col-sm-6">
                        <form:input path="montoMaximo" id="montoMax" class="form-control form-control-lg"  placeholder="${montoS}" value="${mFunciones.valor}" readonly="true" />
                    </div>
                    <div class="col-sm-3" id="comd">
                        <small class="form-text text-muted">
                            <spring:message  code="income.maximum.amount.msg"/>
                        </small>
                        <small id="emailHp" class="form-text text-muted formFieldError">
                            <form:errors path="montoMaximo" cssClass="alert alert-danger"/>
                        </small>
                    </div>
                </div> 
                <div class="form-group row">
                    <spring:message  code="income.expense.percentage.msg" var="pExpens"/>
                    <label for="monto" class="col-sm-2 col-form-label"><spring:message  code="income.expense.percentage"/></label>
                    <label class="col-sm-1 col-form-label text-right" ></label>
                    <div class="col-sm-6">
                        <form:input path="porcentajeGasto" id="porcGasto" class="form-control form-control-lg"  placeholder="${pExpens}" value="${(mFunciones.valor*10)/100}" readonly="true" />
                    </div>
                    <div class="col-sm-3" id="comd">
                        <small class="form-text text-muted">
                            <spring:message  code="income.expense.percentage.msg"/>
                        </small>
                        <small id="emailHp" class="form-text text-muted formFieldError">
                            <form:errors path="porcentajeGasto" cssClass="alert alert-danger"/>
                        </small>
                    </div>
                </div> 
                 <div class="form-group row">
                    <spring:message  code="income.percentage.balance.msg" var="pSaldo"/>
                    <label for="monto" class="col-sm-2 col-form-label"><spring:message  code="income.percentage.balance"/></label>
                    <label class="col-sm-1 col-form-label text-right" > </i></label>
                    <div class="col-sm-6">
                        <form:input path="porcentajeSaldo" value="${(mFunciones.valor*10)/100}"  id="porcSaldo" class="form-control form-control-lg"  placeholder="${pSaldo}" readonly="true" />
                    </div>
                    <div class="col-sm-3" id="comd">
                        <small class="form-text text-muted">
                            <spring:message  code="income.percentage.balance"/>
                        </small>
                        <small id="emailHp" class="form-text text-muted formFieldError">
                            <form:errors path="porcentajeSaldo" cssClass="alert alert-danger"/>
                        </small>
                    </div>
                </div> 
                 <div class="form-group row">
                    <spring:message  code="items.year" var="year"/>
                    <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="items.year"/></label>
                    <label class="col-sm-1 col-form-label text-right" > </label>
                    <div class="col-sm-6" id="combo1"> 
                        <form:input class="form-control form-control-lg" path="gestion"  id="gestion" onkeyUp="return ValNumero(this);" placeholder="${year}" value="${gestion}" />
                     </div>
                    <div class="col-sm-3" id="combo1"> 
                        <small id="emailHelp" class="form-text text-muted">
                            <spring:message  code="items.year.msg"/>
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
                    
    <!----------------Formulario de Modificar ======================================================== -->
    <c:choose>
        <c:when test="${operation eq 'modf' }"><br>
            <center> <p class="h3"><i class="fa fa-pencil-square-o" aria-hidden="true"></i>  <spring:message  code="income.modify.form"/></p> </center>
            <br>
            <form:form modelAttribute="cjaIngresos" action="${urlConfirmarMod}" method="post">
                <form:input type="hidden" path="idCjaIngreso" />
                <form:input type="hidden" path="idEstado" />  
                <form:input type="hidden" path="registro" />
                <form:input type="hidden" path="modificacion" />
                <form:input type="hidden" path="porcentajeGasto" />
               
                <form:input type="hidden" path="nroDocumento" />
                <input type="hidden" name="saldod" id="saldoOriginal" value="${cjaIngresos.saldo}">
                <input type="hidden" name="montod" id="montoOriginal" value="${cjaIngresos.monto}">
                <form:hidden path="periodo"  id="periodo" value="${periodo}" />

                <div class="form-group row">
                    <label for="ingreso" class="col-sm-2 col-form-label"><spring:message  code="ingreso.tipoIngreso"/></label> 
                    <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                    <div class="col-sm-6">
                        <form:select class="form-control form-control-lg" path="CjaTiposIngresos.idCjaTipoIngreso" id="tipoIngreso" >
                           <form:options items="${lTiposIngresos}" itemValue="idCjaTipoIngreso" itemLabel="tipoIngreso"></form:options>
                    </form:select>
                     </div>
                    <div class="col-sm-3" id="comd">
                        <small class="form-text text-muted">
                            <a class="btn add" href="#" data-toggle="modal" data-target="#formTipoIngreso"><i class="fa fa-plus-square-o" aria-hidden="true"></i>  <spring:message  code="people.add"/></a></small>
                        </small>
                    </div>
                </div>     
                  <c:if test="${bCajaIngresosUltimo == null}">
                  <div class="form-group row">
                    <spring:message  code="ingreso.monto" var="montom"/>
                    <label for="monto" class="col-sm-2 col-form-label"><spring:message  code="ingreso.monto"/></label>
                    <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                    <div class="col-sm-6">
                        <form:input path="monto"  id="monto" class="form-control form-control-lg"  placeholder="${montom}" value="${mFunciones.valor}"  readonly="true"/>
                    </div>
                    <div class="col-sm-3" id="comd">
                        <small class="form-text text-muted">
                            <spring:message  code="ingreso.monto.msg"/>
                        </small>
                        <small id="emailHp" class="form-text text-muted formFieldError">
                            <form:errors path="monto" cssClass="alert alert-danger"/>
                        </small>
                    </div>
                  </div>
                </c:if>
                <c:if test="${bCajaIngresosUltimo != null}">
                  <div class="form-group row">
                    <spring:message  code="ingreso.monto" var="montom"/>
                    <label for="monto" class="col-sm-2 col-form-label"><spring:message  code="ingreso.monto"/></label>
                    <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                    <div class="col-sm-6">
                        <form:input path="monto"  id="montoMod" class="form-control form-control-lg"  placeholder="${montom}" value="${montoInicial}" onchange="controlarMonto()"/>
                    </div>
                    <div class="col-sm-3" id="comd">
                        <small class="form-text text-muted">
                            <spring:message  code="ingreso.monto.msg"/>
                        </small>
                        <small id="emailHp" class="form-text text-muted formFieldError">
                            <form:errors path="monto" cssClass="alert alert-danger"/>
                        </small>
                    </div>
                  </div>
                </c:if>
                         
                  <div class="form-group row">
                        <spring:message  code="ingreso.saldo" var="monto"/>
                        <label for="monto" class="col-sm-2 col-form-label"><spring:message  code="ingreso.saldo"/></label>
                         <label class="col-sm-1 col-form-label text-right" ></label>
                        <div class="col-sm-6">
                            <input name="saldo" id="saldo" value="${cjaIngresos.saldo}" readonly="readonly" class="form-control form-control-lg"  placeholder="${monto}" readonly="true" >
                        </div>
                      
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <spring:message  code="ingreso.saldo"/>
                            </small>
                            <small id="emailHp" class="form-text text-muted formFieldError">
                                <form:errors path="saldo" cssClass="alert alert-danger"/>
                            </small>
                        </div>
                </div>
                        
                  <div class="form-group row">
                    <spring:message  code="ingreso.fechaAsignacion" var="ingreso"/>
                    <label for="fecIngreso" class="col-sm-2 col-form-label"><spring:message  code="ingreso.fechaAsignacion"/></label>
                    <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                    <div class="col-sm-6">
                        <form:input type="date" path="fecIngreso" class="form-control form-control-lg"    placeholder="${ingreso}"/>  
                    </div>
                    <div class="col-sm-3" id="comd">
                        <small class="form-text text-muted">
                            <spring:message  code="expenses.date.msg"/>
                        </small>
                        <small id="emailHp" class="form-text text-muted formFieldError">
                            <form:errors path="fecIngreso" cssClass="alert alert-danger"/>
                        </small>
                    </div>
                </div>
                  <div class="form-group row">
                    <spring:message  code="income.maximum.amount.msg" var="montoS"/>
                    <label for="montom" class="col-sm-2 col-form-label"><spring:message  code="income.maximum.amount"/></label>
                    <label class="col-sm-1 col-form-label text-right" > </label>
                    <div class="col-sm-6">
                        <form:input path="montoMaximo" id="montoMax" class="form-control form-control-lg"  placeholder="${montoS}" value="${mFunciones.valor}" readonly="true" />
                    </div>
                    <div class="col-sm-3" id="comd">
                        <small class="form-text text-muted">
                            <spring:message  code="income.maximum.amount.msg"/>
                        </small>
                        <small id="emailHp" class="form-text text-muted formFieldError">
                            <form:errors path="montoMaximo" cssClass="alert alert-danger"/>
                        </small>
                    </div>
                </div> 
                <div class="form-group row">
                    <spring:message  code="income.expense.percentage.msg" var="pExpens"/>
                    <label for="monto" class="col-sm-2 col-form-label"><spring:message  code="income.expense.percentage"/></label>
                    <label class="col-sm-1 col-form-label text-right" ></label>
                    <div class="col-sm-6">
                        <form:input path="porcentajeGasto" value="${cjaIngresos.porcentajeGasto}" id="montoMax" class="form-control form-control-lg"  placeholder="${pExpens}" readonly="true" />
                    </div>
                    <div class="col-sm-3" id="comd">
                        <small class="form-text text-muted">
                            <spring:message  code="income.expense.percentage.msg"/>
                        </small>
                        <small id="emailHp" class="form-text text-muted formFieldError">
                            <form:errors path="porcentajeGasto" cssClass="alert alert-danger"/>
                        </small>
                    </div>
                </div> 
                 <div class="form-group row">
                    <spring:message  code="income.percentage.balance.msg" var="pSaldo"/>
                    <label for="monto" class="col-sm-2 col-form-label"><spring:message  code="income.percentage.balance"/></label>
                    <label class="col-sm-1 col-form-label text-right" > </i></label>
                    <div class="col-sm-6">
                        <form:input path="porcentajeSaldo"  id="montoMax" class="form-control form-control-lg"  placeholder="${pSaldo}" value="${cjaIngresos.porcentajeSaldo}" readonly="true"/>
                    </div>
                    <div class="col-sm-3" id="comd">
                        <small class="form-text text-muted">
                            <spring:message  code="income.percentage.balance"/>
                        </small>
                        <small id="emailHp" class="form-text text-muted formFieldError">
                            <form:errors path="porcentajeSaldo" cssClass="alert alert-danger"/>
                        </small>
                    </div>
                </div> 
                 <div class="form-group row">
                    <spring:message  code="items.year" var="year"/>
                    <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="items.year"/></label>
                    <label class="col-sm-1 col-form-label text-right" > </label>
                    <div class="col-sm-6" id="combo1"> 
                        <form:input class="form-control form-control-lg" path="gestion"  id="gestion"  placeholder="${year}" value="${gestion}" />
                     </div>
                    <div class="col-sm-3" id="combo1"> 
                        <small id="emailHelp" class="form-text text-muted">
                            <spring:message  code="items.year.msg"/>
                         </small>
                     </div>
                </div>
                
                         
             <hr align="left" noshade="noshade" size="10" width="100%" />
                    <div class="form-group row">
                        <label for="msg" class="col-sm-2 col-form-label text-right"></label>
                        <label for="msg" class="col-sm-10 col-form-label"><span class="parpadea text"> <i class="fa fa-info-circle" aria-hidden="true"></i></span> &nbsp;&nbsp;&nbsp;<spring:message  code="people.warning"/> </label>
                    </div>  
                    <div class="form-group row mt-12">
                        <div class="col-sm-3" id="combo1"> 
                        </div>
                        <div class="col-sm-3" id="combo1">
                            <span class="icon-input-btn"><i class="fa fa-pencil-square-o yvela" aria-hidden="true"></i> <input class="btn btn-success" type="submit" value="<spring:message  code="expenses.modify"/>"></span>
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                            <a href="<spring:url value="/ingresos/formCjaIngresos"/>" class="btn btn-yvela-green"><i class="fa fa-undo" aria-hidden="true"></i>  <spring:message  code="expenses.btn.cancel"/></a>
                        </div>
                    </div>
            </form:form>
        </c:when>
    </c:choose>
             
    <!-- ------Finnnnnn formulario Modificar -------- -->

    <!-- ======= Inicio Eliminar Ingreso de Gastos ================= -->

    <c:choose>
        <c:when test="${operation eq 'delet' }">
            <div id="" class="col-lg-12" >
               
                <center><h5><i class="fa fa-trash-o" aria-hidden="true"></i>  <spring:message code="income.remove.title.form"/></h5></center>
                <br>
                <div class="form-group row">
                    <div class="col-sm-8" id="combo1"> 
                        <form:form modelAttribute="cjaIngresos" action="${urlCEI}" method="post">
                            <form:input type="hidden" path="idCjaIngreso" />
                            <form:input type="hidden" path="idEstado" />  
                            <form:input type="hidden" path="registro" />
                            <form:input type="hidden" path="modificacion" />
                            <form:input type="hidden" path="porcentajeGasto" />
                            <form:input type="hidden" path="gestion" />
                            <form:input type="hidden" path="periodo" />
                            <input type="hidden" name="saldod" id="saldoOriginal" value="${cjaIngresos.saldo}">
                            <input type="hidden" name="montod" id="montoOriginal" value="${cjaIngresos.monto}">

                            <div class="form-group row">
                                <label for="ingreso" class="col-sm-3 col-form-label"><spring:message  code="ingreso.tipoIngreso"/></label> 
                                <div class="col-sm-8">
                                    <form:select path="CjaTiposIngresos.idCjaTipoIngreso" readonly="true" items="${lTiposIngresos}"  itemValue="idCjaTipoIngreso" itemLabel="tipoIngreso" class="form-control form-control-lg" />
                                </div>
                            </div>     
                            <div class="form-group row">
                                <spring:message  code="ingreso.monto" var="monto"/>
                                <label for="monto" class="col-sm-3 col-form-label"><spring:message  code="ingreso.monto"/></label>
                                <div class="col-sm-8">
                                    <form:input type="text" path="monto" id="monto" readonly="true" class="form-control form-control-lg"  placeholder="${monto}" onchange="mSumar(this.value);" />
                                </div>
                            </div>
                              <div class="form-group row">
                                <spring:message  code="ingreso.fechaAsignacion" var="ingreso"/>
                                <label for="fecIngreso" class="col-sm-3 col-form-label"><spring:message  code="ingreso.fechaAsignacion"/></label>
                                <div class="col-sm-8">
                                    <form:input type="date" path="fecIngreso"  class="form-control form-control-lg"  placeholder="${ingreso}" readonly="true" />  
                                </div>
                            </div>
                            <div class="form-group row">
                                <spring:message  code="ingreso.saldo" var="monto"/>
                                <label for="monto" class="col-sm-3 col-form-label"><spring:message  code="ingreso.saldo"/></label>
                                <div class="col-sm-8">
                                    <form:input path="saldo" id="saldo"  readonly="true" class="form-control form-control-lg"  placeholder="${monto}" />
                                </div>
                            </div>
                           
                         <div class="form-group row">
                            <spring:message  code="income.maximum.amount.msg" var="montoS"/>
                            <label for="montom" class="col-sm-3 col-form-label"><spring:message  code="income.maximum.amount"/></label>
                            <div class="col-sm-8">
                                <form:input type="text" readonly="true" path="montoMaximo" id="montoMax" class="form-control form-control-lg"  placeholder="${montoS}" onchange="maximo(this.value);" />
                            </div>
                        </div> 
                        <div class="form-group row">
                            <spring:message  code="income.expense.percentage.msg" var="pExpens"/>
                            <label for="monto" class="col-sm-3 col-form-label"><spring:message  code="income.expense.percentage"/></label>
                           <div class="col-sm-8">
                                <form:input type="text" readonly="true" path="porcentajeGasto" id="montoMax" class="form-control form-control-lg"  placedisableholder="${pExpens}" onchange="maximo(this.value);" />
                            </div>
                        </div> 
                         <div class="form-group row">
                            <spring:message  code="income.percentage.balance.msg" var="pSaldo"/>
                            <label for="monto" class="col-sm-3 col-form-label"><spring:message  code="income.percentage.balance"/></label>
                            <div class="col-sm-8">
                                <form:input type="text" readonly="true" path="porcentajeSaldo" id="montoMax" class="form-control form-control-lg"  placeholder="${pSaldo}" onchange="maximo(this.value);" />
                            </div>
                         </div>   
                         <div class="form-group row">
                            <spring:message  code="items.year" var="year"/>
                            <label for="sedes" class="col-sm-3 col-form-label"><spring:message  code="items.year"/></label>
                            <div class="col-sm-8" id="combo1"> 
                                <form:input class="form-control form-control-lg" readonly="true" path="gestion"  id="gestion"  placeholder="${year}" value="${gestion}" />
                             </div>
                        </div>
                       <hr align="left" noshade="noshade" size="10" width="100%" />
                                <div class="form-group row mt-12">
                                    <div class="col-sm-3" id="combo1"> 
                                    </div>

                                    <div class="col-sm-3" id="combo1">
                                        <span class="icon-input-btn"><i class="fa fa-times yvela" aria-hidden="true"></i> 
                                            <c:if  test="${banderita == 1}">  
                                                <input type="submit" disabled="true" class="btn btn-danger" value="<spring:message  code="expenses.btn.confirm"/>"> </span>
                                            </c:if>
                                            <c:if  test="${banderita == 0}">  
                                            <input type="submit"  class="btn btn-danger" value="<spring:message  code="expenses.btn.confirm"/>">
                                        </c:if>
                                    </div>
                                    <div class="col-sm-3" id="combo1"> 
                                    </div>
                                    <div class="col-sm-3" id="combo1"> 
                                        <a href="<spring:url value="/ingresos/formCjaIngresos"/>" class="btn btn-yvela-green"><i class="fa fa-undo" aria-hidden="true"></i>  <spring:message  code="expenses.btn.cancel"/></a>
                                    </div>
                                </div>
                        </form:form>
                    </div>
                        <div class="col-sm-4" id="combo1"> 
                            <fieldset class="col-md-12">    	
                                <legend><i class="fa fa-exclamation-triangle" aria-hidden="true"></i>  <spring:message  code="expenses.remove.info.title"/></legend>
                                <br> 
                                <p><spring:message  code="people.remove.msg"/> </p>
                                <br>
                                    <c:choose>
                                        <c:when test="${mensage eq 'err' }">
                                            <span class="parpadea text"><p class="h1"><i class="fa fa-exclamation-triangle" aria-hidden="true"></i></p></span>  <spring:message  code="people.warning.remove"/> 
                                        </c:when>
                                    </c:choose>
                                <br>
                            </fieldset>
                        </div>
                </div>
            </div>
        </c:when>
    </c:choose> 
    <!-- ======= Fin Eliminar Ingreso de Gastos ================= -->

     <!-- ======= Inicio Cerrar Caja  Ingreso de Gastos ================= -->

    <c:choose>
        <c:when test="${operation eq 'close' }">
            <div id="" class="col-lg-12" >
               
                <center><h5><i class="fa fa-trash-o" aria-hidden="true"></i>  <spring:message code="income.form.close.box"/></h5></center>
                <br>
                <div class="form-group row">
                    <div class="col-sm-8" id="combo1"> 
                        <form:form modelAttribute="cjaIngresos" action="${urlCloseConf}" method="post">
                            <input type="hidden" name="idCjaIngreso" value="${cjaIngresos.idCjaIngreso}">
                            <div class="form-group row">
                                <label for="ingreso" class="col-sm-3 col-form-label"><spring:message  code="ingreso.tipoIngreso"/></label> 
                                <div class="col-sm-8">
                                    <form:select path="CjaTiposIngresos.idCjaTipoIngreso" readonly="true" items="${lTiposIngresos}"  itemValue="idCjaTipoIngreso" itemLabel="tipoIngreso" class="form-control form-control-lg" />
                                </div>
                            </div>     
                            <div class="form-group row">
                                <spring:message  code="ingreso.monto" var="monto"/>
                                <label for="monto" class="col-sm-3 col-form-label"><spring:message  code="ingreso.monto"/></label>
                                <div class="col-sm-8">
                                    <form:input type="text" path="monto" id="monto" readonly="true" class="form-control form-control-lg"  placeholder="${monto}" onchange="mSumar(this.value);" />
                                </div>
                            </div>
                              <div class="form-group row">
                                <spring:message  code="ingreso.fechaAsignacion" var="ingreso"/>
                                <label for="fecIngreso" class="col-sm-3 col-form-label"><spring:message  code="ingreso.fechaAsignacion"/></label>
                                <div class="col-sm-8">
                                    <form:input type="date" path="fecIngreso"  class="form-control form-control-lg"  placeholder="${ingreso}" readonly="true" />  
                                </div>
                            </div>
                            <div class="form-group row">
                                <spring:message  code="ingreso.saldo" var="monto"/>
                                <label for="monto" class="col-sm-3 col-form-label"><spring:message  code="ingreso.saldo"/></label>
                                <div class="col-sm-8">
                                    <form:input path="saldo" id="saldo"  readonly="true" class="form-control form-control-lg"  placeholder="${monto}" />
                                </div>
                            </div>
                           
                         <div class="form-group row">
                            <spring:message  code="income.maximum.amount.msg" var="montoS"/>
                            <label for="montom" class="col-sm-3 col-form-label"><spring:message  code="income.maximum.amount"/></label>
                            <div class="col-sm-8">
                                <form:input type="text" readonly="true" path="montoMaximo" id="montoMax" class="form-control form-control-lg"  placeholder="${montoS}" onchange="maximo(this.value);" />
                            </div>
                        </div> 
                        <div class="form-group row">
                            <spring:message  code="income.expense.percentage.msg" var="pExpens"/>
                            <label for="monto" class="col-sm-3 col-form-label"><spring:message  code="income.expense.percentage"/></label>
                           <div class="col-sm-8">
                                <form:input type="text" readonly="true" path="porcentajeGasto" id="montoMax" class="form-control form-control-lg"  placedisableholder="${pExpens}" onchange="maximo(this.value);" />
                            </div>
                        </div> 
                         <div class="form-group row">
                            <spring:message  code="income.percentage.balance.msg" var="pSaldo"/>
                            <label for="monto" class="col-sm-3 col-form-label"><spring:message  code="income.percentage.balance"/></label>
                            <div class="col-sm-8">
                                <form:input type="text" readonly="true" path="porcentajeSaldo" id="montoMax" class="form-control form-control-lg"  placeholder="${pSaldo}" onchange="maximo(this.value);" />
                            </div>
                         </div>   
                         <div class="form-group row">
                            <spring:message  code="items.year" var="year"/>
                            <label for="sedes" class="col-sm-3 col-form-label"><spring:message  code="items.year"/></label>
                            <div class="col-sm-8" id="combo1"> 
                                <form:input class="form-control form-control-lg" readonly="true" path="gestion"  id="gestion"  placeholder="${year}" value="${gestion}" />
                             </div>
                        </div>
                       <hr align="left" noshade="noshade" size="10" width="100%" />
                                <div class="form-group row mt-12">
                                    <div class="col-sm-3" id="combo1"> 
                                    </div>

                                    <div class="col-sm-3" id="combo1">
                                        <span class="icon-input-btn"><i class="fa fa-times yvela" aria-hidden="true"></i> 
                                             <input type="submit"  class="btn btn-danger" value="<spring:message  code="expenses.btn.confirm"/>"> </span>
                                       </div>
                                    <div class="col-sm-3" id="combo1"> 
                                    </div>
                                    <div class="col-sm-3" id="combo1"> 
                                        <a href="<spring:url value="/ingresos/formCjaIngresos"/>" class="btn btn-yvela-green"><i class="fa fa-undo" aria-hidden="true"></i>  <spring:message  code="expenses.btn.cancel"/></a>
                                    </div>
                                </div>
                        </form:form>
                    </div>
                        <div class="col-sm-4" id="combo1"> 
                            <fieldset class="col-md-12">    	
                                <legend><i class="fa fa-exclamation-triangle" aria-hidden="true"></i>  <spring:message  code="expenses.remove.info.title"/></legend>
                                <br> 
                                <p><spring:message  code="people.remove.msg"/> </p>
                                <br>
                                     
                                <br>
                            </fieldset>
                        </div>
                </div>
            </div>
        </c:when>
    </c:choose> 
    <!-- ======= Fin Cerrar Caja ================= -->
      
    <!--Tabla lista de Todos los Ingresos Registrados -->

    <c:choose>
        <c:when test="${busqueda eq 'find' }">
            <center><br>
                <p class="h3"><i class="fa fa-list-alt" aria-hidden="true"></i> <spring:message  code="income.list.income.title"/></p> 
             </center>
         <table id="tIngresos"  class="table" style="width:100%">
               <thead>
                  <tr>
                      <th><spring:message  code="income.type.income"/></th>
                      <th><spring:message  code="income.amount.entered"/></th>
                      <th><spring:message  code="income.balance"/></th>
                      <th><spring:message  code="income.date.entered"/></th>
                       <th><spring:message  code="income.document.number"/></th>
                      <th><spring:message  code="expenses.modify"/> </th>
                      <th><spring:message  code="expenses.remove"/></th>
                     </tr>
              </thead>
              <tbody>
                    <c:set var="cont" value="0" />
                    <c:forEach items="${lIngresos}" var="li">
                        <c:set var="cont" value="${cont+1}" />
                        <tr>
                            <td>${li.cjaTiposIngresos.tipoIngreso}</td>
                            <td>${li.monto}</td>
                            <td><fmt:formatNumber value="${li.saldo}" pattern="0.00"/></td>
                            <td>${li.fecIngreso}</td>
                            <td>${li.nroDocumento}</td>
                            <c:if  test="${li.idEstado == 'A'}">   
                             <td><form  method="post" action="${urlMod}">
                                <input type="hidden" name="idCjaIngreso" value="${li.idCjaIngreso}">
                                <button type="submit" class="btn btn-success"><i class="fa fa-pencil-square-o" aria-hidden="true"></i> <spring:message  code="expenses.modify"/></button> 
                              </form></center>
                          </td>
                           <td>
                                <form name="Delet" action="${urlEI}" method="post">
                                  <input type="hidden" name="idCjaIngreso" value="${li.idCjaIngreso}">
                                <button type="submit" class="btn btn-danger"><i class="fa fa-times" aria-hidden="true"></i> <spring:message  code="expenses.remove"/></button>
                              </form>
                            </td>
                          </tr>
                        </c:if>
                          <c:if  test="${li.idEstado != 'A'}">   
                            <td><spring:message  code="expenses.modify"/></center> </td>
                            <td><spring:message  code="expenses.remove"/></center> </td>
                        </c:if>
                    </tr>
          </c:forEach >
          </thead>
            <tfoot>
                  <tr>
                      <th><spring:message  code="income.type.income"/></th>
                      <th><spring:message  code="income.amount.entered"/></th>
                      <th><spring:message  code="income.balance"/></th>
                      <th><spring:message  code="income.date.entered"/></th>
                      <th><spring:message  code="income.document.number"/></th>
                      <th><spring:message  code="expenses.modify"/> </th>
                      <th><spring:message  code="expenses.remove"/></th>
                     </tr>
              </tfoot>
          </table>
        </c:when>
    </c:choose>
    <br>

    <hr align="left" noshade="noshade" size="10" width="100%" />
    <br>
   </section>
</div>

<div id="formTipoIngreso" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form name="tipoIngresoForm" action="guardarInstituciones" role="form" id="itemForm" method="post">
                <div class="modal-header">
                    <p class="h3"><i class="fa fa-user-secret" aria-hidden="true"></i>  <spring:message  code="income.typeIncome.new"/></p>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                </div>
                <div class="modal-body">
                    <div class="form-group row">
                        <spring:message  code="ingreso.tipoIngreso" var="inst"/>
                        <label for="tIngreso" class="col-sm-3 col-form-label"><spring:message  code="ingreso.tipoIngreso"/></label>
                        <label for="ingreso" class="col-sm-1 col-form-label"><i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-8">
                            <input type="text" name="tipoIngreso" class="form-control form-control-lg"  placeholder="${inst}" />
                        </div>
                    </div>
                     <div class="form-group row">
                        <spring:message  code="institution.code.msg" var="instCode"/>
                        <label for="codigo" class="col-sm-3 col-form-label"><spring:message  code="institution.code"/></label>
                        <label for="codiogs" class="col-sm-1 col-form-label"><i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-8">
                            <input type="text" name="codigo" class="form-control form-control-lg"  placeholder="${instCode}" />
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-outline-secondary btn-lg" data-dismiss="modal" aria-hidden="true"><i class="fa fa-undo" aria-hidden="true"></i>  <spring:message  code="people.cancel"/></button>
                    <button type="submit" class="btn btn-success btn-lg float-right" id="btnTipoIngreso" data-dismiss="modal" aria-hidden="true"><i class="fa fa-plus-square-o" aria-hidden="true"></i>   <spring:message  code="people.add"/></button>
                </div>
            </form>
        </div>
    </div>
</div>
