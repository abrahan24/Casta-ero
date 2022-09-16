<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<head>
     <link rel="stylesheet" type="text/css"  href=" <c:url value="/resource/admPersonas/personas.css"/>" />
    <script src="<spring:url value="/resource/admPersonas/personas.js" />" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css"  href=" <c:url value="/resource/admGastos/gastos.css"/>" />
    <script src="<spring:url value="/resource/admGastos/gastos.js" />" type="text/javascript"></script>
    <script type="text/javascript">
    </script>
</head>

<div class="container">
    <section class="content-header">
        <div class="row">
            <div class="col-md-10 mb-3">
                <p class="h1"> <i class="fa fa-money" aria-hidden="true"></i> <spring:message  code="expenses.title"/> </p>
            </div>
            <div class="col-sm-2 text-right" id="reporte">
                <c:choose>
                    <c:when test="${busqueda eq 'find' }">
                        <form  method="POST" action="${urlN}">
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
                <br/>

            </div>
        </c:if>
        <br>
        <center>
            <p class="h3"><i class="fa fa-list-alt" aria-hidden="true"></i>  <spring:message  code="income.title.balance.summary"/></p>
        </center>
        <br>
        <table class="table">
            <tr class="trIngreso">
                <th  align="center">Nro</th>
                <th  align="center"><spring:message  code="expenses.type.income"/></th>
                <th  align="center"><spring:message  code="expenses.amount.entered"/></th>
                <th  align="center"><spring:message  code="expenses.balance"/></th>
                <th align="center"><spring:message  code="expenses.date.entered"/></th>
                <th  align="center"><spring:message  code="expenses.percentage"/></th>
                <th  align="center"><spring:message  code="expenses.document.number"/></th>
            </tr>
            <tr id="thing">
                <td>1</td>
                <td>${bSaldo.cjaTiposIngresos.tipoIngreso}</td>
                <td>${bSaldo.monto}</td>
                <td class="name"><fmt:formatNumber value="${bSaldo.saldo}" pattern="0.00"/></td>
                <td>${bSaldo.fecIngreso}</td>
                <td>${bSaldo.porcentajeGasto}</td>
                <td class="ndoc">${bSaldo.nroDocumento}</td>
            </tr>
        </table>

        <!----------------Formulario Registro Gasto Ejecutados======================================================== -->
         <br>
        <hr align="left" noshade="noshade" size="10" width="100%" />
        <br>
        <spring:url var="formUrl" value="signupsss" />    
        <c:choose>
            <c:when test="${operation eq 'nuevo' }">
                <center> <p class="h3"><i class="fa fa-th" aria-hidden="true"></i>  <spring:message  code="expenses.register.form"/></p> </center>
                  <br>
                <form:form  modelAttribute="cjaGastosEjecutados" action="${urlR}" method="post" >

                    <form:hidden  path="CjaIngresos.idCjaIngreso" value="${bSaldo.idCjaIngreso}" />
                    <form:hidden  path="PnlPersonalAdministrativos.idPnlPersonalAdministrativo" value="${pnlAdministrativo.idPnlPersonalAdministrativo}" />
                    <input type="hidden" name="saldoI" value="${bSaldo.saldo}" id="saldoOriginal">
                    <input type="hidden" name="porcentajeGasto" value="${bSaldo.porcentajeGasto}" id="porcentajeGasto">
                    <input type="hidden" name="porcentajeSaldo" value="${bSaldo.porcentajeSaldo}" id="porcentajeSaldo">
                    <input type="hidden" name="saldoGb" value="saldo" id="saldoPrincipal">
                    <input type="hidden" name="montoMaximo" value="${bSaldo.montoMaximo}" id="montoMaximo">
                    <form:hidden path="cantidad" value="1" /> 
                    <form:hidden path="monto" value="1" /> 
                    <div class="form-group row">
                        <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="expenses.provider"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6" id="combo1"> 
                            <form:select class="form-control form-control-lg" path="CjaProveedores.idCjaProveedor" id="sProveedores" >
                                <c:forEach var="p" items="${requestScope.lProveedores}">
                                    <form:option value="${p.idCjaProveedor}">${p.proveedor}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                            <small id="emailHelp" class="form-text text-muted">
                                <a class="btn add" href="#" data-toggle="modal" data-target="#formCjaProveedores"><i class="fa fa-plus-square-o" aria-hidden="true"></i>  <spring:message  code="people.add"/></a></small>
                            </small>
                        </div>
                    </div>
                      <div class="form-group row">
                        <label for="nombres" class="col-sm-2 col-form-label"><spring:message  code="report.noteOrder.name"/></label>
                        <label class="col-sm-1 col-form-label text-right" ><i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6">
                            <form:select class="form-control form-control-lg" path="Personas.idPersona" id="personaS">
                                <c:forEach var="p" items="${requestScope.lPersonas}">
                                    <form:option value="${p.personas.idPersona}">${p.personas.nombres} ${p.personas.paterno} ${p.personas.materno} </form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="comd">
                            <small class="form-text text-muted">
                                <spring:message  code="info.login.people"/>
                            </small>
                            <small id="emailHp" class="form-text text-muted formFieldError">
                                <form:errors path="Personas.idPersona" cssClass="alert alert-danger"/>
                            </small>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="Expenses" class="col-sm-2 col-form-label"><spring:message  code="expenses.type"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6" id="combo2"> 
                            <form:select class="form-control form-control-lg" path="CjaTiposGastos.idCjaTipoGasto" id="gastos">
                                <c:forEach var="tg" items="${requestScope.lTiposGastos}">
                                    <form:option value="${tg.idCjaTipoGasto}">${tg.nroTipoGasto}-${tg.tipoGasto} </form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                            <small id="emailHp" class="form-text text-muted">
                                <a class="btn add" href="#" data-toggle="modal" data-target="#formCjaTiposGastos"><i class="fa fa-plus-square-o" aria-hidden="true"></i>  <spring:message  code="people.add"/></a></small>
                            </small>
                        </div>
                    </div>
  
                    <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="expenses.warehouse.certification"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-3" id="combo1"> 
                            <form:radiobutton path="certificacionAlmacen"  value="1" />&nbsp;&nbsp; : &nbsp;&nbsp;&nbsp;&nbsp;<spring:message  code="expenses.warehouse.certification"/>
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                            <form:radiobutton path="certificacionAlmacen" value="0"/>&nbsp;&nbsp; : &nbsp;&nbsp;&nbsp;&nbsp;<spring:message  code="expenses.Certificate.not"/>
                        </div>
                        <div class="col-sm-3" id="comd"> 
                            <small id="emailHp" class="form-text text-muted">
                                <spring:message  code="expenses.warehouse.certification.msg"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="certificacionAlmacen" />
                            </small>
                        </div>
                    </div>
                     <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="expenses.description"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6" id="combo1"> 
                            <spring:message  code="expenses.description.msg" var="description"/>
                            <form:textarea  path="descripcion" class="form-control form-control-lg" placeholder="${description}" rows="3" cols="20" /> 
                        </div>
                        <div class="col-sm-3" id="comd"> 
                            <small id="emailHp" class="form-text text-muted">
                                <spring:message  code="expenses.description.msg"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="descripcion" />
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="expenses.cost"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6" id="combo1"> 
                            <spring:message  code="expenses.cost.msg" var="cost"/>
                            <form:input path="totalG" id="costoM" class="montos form-control form-control-lg" placeholder="${cost}" onchange="montoM(this.value);"/> 
                        </div>
                        <div class="col-sm-3" id="comd"> 
                            <small id="emailHp" class="form-text text-muted">
                                <spring:message  code="expenses.cost.msg"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="totalG" />
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="expenses.withholding.amount"/></label>
                        <label class="col-sm-1 col-form-label text-right" > </label>
                        <div class="col-sm-3" id="combo1"> 
                            <form:radiobutton path="retencionCheck" id="noCert" value="0" onclick="noRetenImp(this.value);"/>&nbsp;&nbsp; : &nbsp;&nbsp;&nbsp;&nbsp;<spring:message  code="expenses.retention.not"/>
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                            <form:radiobutton path="retencionCheck" id="cert" value="1" onclick="retenImp(this.value);"/>&nbsp;&nbsp; : &nbsp;&nbsp;&nbsp;&nbsp;<spring:message  code="expenses.retention"/>
                        </div>
                        <div class="col-sm-3" id="comd"> 
                            <small id="emailHp" class="form-text text-muted">
                                <spring:message  code="expenses.withholding.tax.msg"/>
                            </small>
                            <small class="msgError">
                                 <form:errors path="retencionCheck" />
                            </small>
                        </div>
                     </div>
                    
                    <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="expenses.withholding.amount"/></label>
                        <label class="col-sm-1 col-form-label text-right" > </label>
                        <div class="col-sm-6" id="combo1"> 
                            <spring:message  code="expenses.withholding.amount.msg" var="withholding"/>
                            <form:input path="retencion" id="reten" class="form-control form-control-lg" placeholder="${withholding}" readOnly="true" value="0"/> 
                        </div>
                        <div class="col-sm-3" id="comd"> 
                            <small id="emailHp" class="form-text text-muted">
                                <spring:message  code="expenses.withholding.amount.msg"/>
                            </small>
                            <small class="msgError">
                               <form:errors path="retencion" />
                            </small>
                        </div>
                    </div>
                            
                    <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="expenses.purchase"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6" id="combo1"> 
                            <spring:message  code="expenses.purchase.msg" var="purchase"/>
                            <form:input path="nroFactura" id="factura" class="form-control form-control-lg" placeholder="${purchase}" /> 
                        </div>
                        <div class="col-sm-3" id="comd"> 
                            <small id="emailHp" class="form-text text-muted">
                                <spring:message  code="expenses.purchase.msg"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="nroFactura" />
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="expenses.document"/></label>
                        <label class="col-sm-1 col-form-label text-right" ></label>
                        <div class="col-sm-6" id="combo1"> 
                            <spring:message  code="expenses.document.msg" var="document"/>
                            <form:input path="nroDocumento" id="nDoc" class="form-control form-control-lg" placeholder="${document}" value="${bSaldo.nroDocumento+1}" readonly="true" /> 
                        </div>
                        <div class="col-sm-3" id="comd"> 
                            <small id="emailHp" class="form-text text-muted">
                                <spring:message  code="expenses.document.msg"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="nroDocumento" />
                            </small>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="expenses.date"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6" id="combo1"> 
                            <spring:message  code="expenses.date.msgg" var="date"/>
                            <form:input type="date" path="fecGasto" class="form-control form-control-lg" /> 
                        </div>
                        <div class="col-sm-3" id="comd"> 
                            <small id="emailHp" class="form-text text-muted">
                                <spring:message  code="expenses.date.msgg"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="fecGasto" />
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
                            <span class="icon-input-btn"><i class="fa fa-pencil-square-o yvela" aria-hidden="true"></i> <input class="btn btn-success" type="submit" value="<spring:message  code="institution.register"/>"></span>
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                        </div>
                        <div class="col-sm-3" id="combo1">
                            <a href="<spring:url value="/gastos/inicioGastos"/>" class="btn btn-yvela-green"><i class="fa fa-undo yvela" aria-hidden="true"></i>  <spring:message  code="expenses.btn.cancel"/></a>
                        </div> 
                    </div>
 
                </form:form>
            </c:when>
        </c:choose>
        <!-----------------------Fin Formulario Registro Gasto ======================================================== -->

        <!-- ============= Inicio Modificar Gastos Ejecutados ============ -->
        <c:choose>
            <c:when test="${operation eq 'edit' }">
                <div id="" class="col-lg-12" >
                    <center> <h5><spring:message  code="expenses.modify.form"/></h5> </center><br>
                        <spring:url var="formUrl" value="modify" />
                        <form:form name="modifyExpenses"  modelAttribute="cjaGastosEjecutados" action="${urlMG}" method="post" >
                            <form:hidden path="idCjaGastoEjecutado" value="${cjaGastosEjecutados.idCjaGastoEjecutado}" />
                            <form:hidden path="CjaIngresos.idCjaIngreso" value="${bSaldo.idCjaIngreso}" />
                            <form:hidden path="PnlPersonalAdministrativos.idPnlPersonalAdministrativo" value="${pnlAdministrativo.idPnlPersonalAdministrativo}" />


                        <input type="hidden" name="saldoMod" value="${bSaldo.saldo}" id="saldoModificar">
                        <input type="hidden" name="saldoOrg" value="${bSaldo.saldo}" id="saldoOrigin">
                        <input type="hidden" name="mTotal" id="mTotalModificad" value="${cjaGastosEjecutados.totalG}">
                        <input type="hidden" name="porcentajeGasto" value="${bSaldo.porcentajeGasto}" id="pGastoModificar">
                        <input type="hidden" name="porcentajeSaldo" value="${bSaldo.porcentajeSaldo}" id="pSaldoModificar">
                        <input type="hidden" name="costoOriginal" value="${cjaGastosEjecutados.totalG}" id="costoOriginal">
                        <input type="hidden" name="factur" value="${cjaGastosEjecutados.nroFactura}" id="fOriginal">
                        <form:hidden path="cantidad" value="1" /> 
                        <form:hidden path="monto" value="1" />
                        
                        <div class="form-group row">
                            <label for="Provider" class="col-sm-2 col-form-label"><spring:message  code="expenses.provider"/></label>
                            <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                            <div class="col-sm-6" id="combo1"> 
                             <form:select class="form-control form-control-lg" path="CjaProveedores.idCjaProveedor" id="sProveedores" >
                                <c:forEach var="p" items="${requestScope.lProveedores}">
                                    <form:option value="${p.idCjaProveedor}">${p.proveedor}</form:option>
                                </c:forEach>
                            </form:select>
                            </div>
                            <div class="col-sm-3" id="combo1"> 
                                <small id="emailHp" class="form-text text-muted">
                                     <a class="btn add" href="#" data-toggle="modal" data-target="#formCjaProveedores"><i class="fa fa-plus-square-o" aria-hidden="true"></i>  <spring:message  code="people.add"/></a></small>
                                 </small>
                                <small id="emailHp" class="form-text text-muted formFieldError">
                                    <form:errors path="CjaProveedores.idCjaProveedor" />
                                </small>
                            </div>
                        </div>
                         <div class="form-group row">
                            <label for="nombres" class="col-sm-2 col-form-label"><spring:message  code="login.people"/></label>
                            <label class="col-sm-1 col-form-label text-right" ><i class="fa fa-info-circle" aria-hidden="true"></i></label>
                            <div class="col-sm-6">
                                <form:select class="form-control form-control-lg" path="Personas.idPersona" id="personaS">
                                    <c:forEach var="p" items="${requestScope.lPersonas}">
                                        <form:option value="${p.personas.idPersona}">${p.personas.nombres} ${p.personas.paterno} ${p.personas.materno} </form:option>
                                    </c:forEach>
                                </form:select>
                            </div>
                            <div class="col-sm-3" id="comd">
                                <small class="form-text text-muted">
                                    <spring:message  code="info.login.people"/>
                                </small>
                                <small id="emailHp" class="form-text text-muted formFieldError">
                                    <form:errors path="Personas.idPersona" cssClass="alert alert-danger"/>
                                </small>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="Expenses" class="col-sm-2 col-form-label"><spring:message  code="expenses.type"/></label>
                            <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label> 
                            <div class="col-sm-6" id="combo2"> 
                                <form:select class="form-control form-control-lg" path="CjaTiposGastos.idCjaTipoGasto" id="gastos">
                                    <c:forEach var="tg" items="${requestScope.lTiposGastos}">
                                        <form:option value="${tg.idCjaTipoGasto}">${tg.nroTipoGasto} ${tg.tipoGasto} </form:option>
                                    </c:forEach>
                                </form:select>
                            </div>
                            <div class="col-sm-3" id="combo1"> 
                                <small id="emailHp" class="form-text text-muted">
                                    <a class="btn add" href="#" data-toggle="modal" data-target="#formCjaTiposGastos"><i class="fa fa-plus-square-o" aria-hidden="true"></i>  <spring:message  code="people.add"/></a></small>
                                </small>
                                <small id="emailHp" class="form-text text-muted formFieldError">
                                    <form:errors path="CjaTiposGastos.idCjaTipoGasto" />
                                </small>
                            </div>
                        </div>
                        <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="expenses.warehouse.certification"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-3" id="combo1"> 
                            <form:radiobutton path="certificacionAlmacen" value="1"/>&nbsp;&nbsp; : &nbsp;&nbsp;&nbsp;&nbsp;<spring:message  code="expenses.warehouse.certification"/>
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                            <form:radiobutton path="certificacionAlmacen" value="0"/>&nbsp;&nbsp; : &nbsp;&nbsp;&nbsp;&nbsp;<spring:message  code="expenses.Certificate.not"/>
                        </div>
                        <div class="col-sm-3" id="comd"> 
                            <small id="emailHp" class="form-text text-muted">
                                <spring:message  code="expenses.warehouse.certification.msg"/>
                            </small>
                            <small class="msgError">
                                <form:errors path="certificacionAlmacen" />
                            </small>
                        </div>
                        </div>
                        <div class="form-group row">
                            <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="expenses.description"/></label>
                            <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                            <div class="col-sm-6" id="combo1"> 
                                <spring:message  code="expenses.description.msg" var="description"/>
                                <form:textarea  path="descripcion" class="form-control form-control-lg" placeholder="${description}" rows="3" cols="20" /> 
                            </div>
                            <div class="col-sm-3" id="comd"> 
                                <small id="emailHp" class="form-text text-muted">
                                    <spring:message  code="expenses.description.msg"/>
                                </small>
                                <small class="msgError">
                                    <form:errors path="descripcion" />
                                </small>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="expenses.cost"/></label>
                            <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                            <div class="col-sm-6" id="combo1"> 
                                <spring:message  code="expenses.cost.msg" var="cost"/>
                                <form:input path="totalG" id="mCosto" class="montos form-control form-control-lg" placeholder="${cost}" onchange="mMontoMod(this.value);" /> 
                            </div>
                            <div class="col-sm-3" id="comd"> 
                                <small id="emailHp" class="form-text text-muted">
                                    <spring:message  code="expenses.cost.msg"/>
                                </small>
                                <small class="msgError">
                                    <form:errors path="totalG" />
                                </small>
                            </div>
                        </div>
                       <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="expenses.withholding.amount"/></label>
                        <label class="col-sm-1 col-form-label text-right" > </label>
                        <div class="col-sm-3" id="combo1"> 
                            <form:radiobutton path="retencionCheck" id="noCertM" value="0" onclick="noRetenImpM(this.value);"/>&nbsp;&nbsp; : &nbsp;&nbsp;&nbsp;&nbsp;<spring:message  code="expenses.retention.not"/>
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                            <form:radiobutton path="retencionCheck" id="certM" value="1" onclick="retenImpM(this.value);"/>&nbsp;&nbsp; : &nbsp;&nbsp;&nbsp;&nbsp;<spring:message  code="expenses.retention"/>
                        </div>
                        <div class="col-sm-3" id="comd"> 
                            <small id="emailHp" class="form-text text-muted">
                                <spring:message  code="expenses.withholding.tax.msg"/>
                            </small>
                            <small class="msgError">
                                 <form:errors path="retencionCheck" />
                            </small>
                        </div>
                     </div>
                     <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="expenses.withholding.amount"/></label>
                        <label class="col-sm-1 col-form-label text-right" > </label>
                        <div class="col-sm-6" id="combo1"> 
                            <spring:message  code="expenses.withholding.amount.msg" var="withholding"/>
                            <form:input path="retencion" id="retenM" class="form-control form-control-lg" placeholder="${withholding}" readOnly="true" /> 
                        </div>
                        <div class="col-sm-3" id="comd"> 
                            <small id="emailHp" class="form-text text-muted">
                                <spring:message  code="expenses.withholding.amount.msg"/>
                            </small>
                            <small class="msgError">
                               <form:errors path="retencion" />
                            </small>
                        </div>
                    </div>
                   
                         <div class="form-group row">
                            <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="expenses.purchase"/></label>
                            <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                            <div class="col-sm-6" id="combo1"> 
                                <spring:message  code="expenses.purchase.msg" var="purchase"/>
                                <form:input path="nroFactura" id="facturaM" class="form-control form-control-lg" placeholder="${purchase}" /> 
                            </div>   
                            <div class="col-sm-3" id="comd"> 
                                <small id="emailHp" class="form-text text-muted">
                                    <spring:message  code="expenses.purchase.msg"/>
                                </small>
                                <small class="msgError">
                                    <form:errors path="nroFactura" />
                                </small>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="expenses.document"/></label>
                            <label class="col-sm-1 col-form-label text-right" > </label>
                            <div class="col-sm-6" id="combo1"> 
                                <spring:message  code="expenses.document.msg" var="document"/>
                                <form:input path="nroDocumento" class="form-control form-control-lg" placeholder="${document}" readonly="true"/> 
                            </div>
                            <div class="col-sm-3" id="comd"> 
                                <small id="emailHp" class="form-text text-muted">
                                    <spring:message  code="expenses.document.msg"/>
                                </small>
                                <small class="msgError">
                                    <form:errors path="nroDocumento" />
                                </small>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="expenses.date"/></label>
                            <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                            <div class="col-sm-6" id="combo1"> 
                                <spring:message  code="expenses.date.msgg" var="date"/>
                                <form:input type="date" path="fecGasto" value="${cjaGastosEjecutados.fecGasto}" class="form-control form-control-lg" /> 
                            </div>
                            <div class="col-sm-3" id="comd"> 
                                <small id="emailHp" class="form-text text-muted">
                                    <spring:message  code="expenses.date.msgg"/>
                                </small>
                                <small class="msgError">
                                    <form:errors path="fecGasto" />
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
                                <a href="<spring:url value="/gastos/inicioGastos"/>" class="btn btn-yvela-green"><i class="fa fa-undo" aria-hidden="true"></i>  <spring:message  code="expenses.btn.cancel"/></a>
                            </div>
                        </div>
                    </form:form>

                </div>
            </c:when>
        </c:choose>
        <!-- ============= Fin Modificar Gastos Ejecutados ============ -->

        <!-- ============= Inicio Eliminar Gastos Ejecutados ============ -->
        <c:choose>
            <c:when test="${operation eq 'delet' }">
                <div id="" class="col-lg-12" >
                    <br>
                    <center><h5><i class="fa fa-trash-o" aria-hidden="true"></i>  <spring:message  code="expenses.remove.form"/></h5></center>
                    <br>
                    <div class="form-group row">
                        <div class="col-sm-8" id="combo1"> 
                            <spring:url var="formUrl" value="modify" />
                            <form:form name="modifyExpenses"  modelAttribute="cjaGastosEjecutados" action="${urlEG}" method="post" >
                                <form:hidden path="idCjaGastoEjecutado" value="${cjaGastosEjecutados.idCjaGastoEjecutado}"/>
                                <form:hidden path="CjaIngresos.idCjaIngreso" value="${bSaldo.idCjaIngreso}"/>
                                <form:hidden  path="PnlPersonalAdministrativos.idPnlPersonalAdministrativo" value="${pnlAdministrativo.idPnlPersonalAdministrativo}" />
                                <input type="hidden" name="saldoMod" value="${bSaldo.saldo}" id="saldoModificar">
                                <form:hidden path="cantidad"  value="1" />
                                <form:hidden path="monto" value="1" /> 
                                
                                <div class="form-group row">
                                    <label for="Provider" class="col-sm-4 col-form-label"><spring:message  code="expenses.provider"/></label>
                                    <div class="col-sm-8" id="combo1"> 
                                        <form:select path="CjaProveedores.idCjaProveedor" items="${lProveedores}" itemValue="idCjaProveedor" itemLabel="proveedor" class="form-control form-control-lg" readonly="true" />
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <label for="Expenses" class="col-sm-4 col-form-label"><spring:message  code="expenses.type"/></label>
                                    <div class="col-sm-8" id="combo2"> 
                                        <form:select path="CjaTiposGastos.idCjaTipoGasto" items="${lTiposGastos}" itemValue="idCjaTipoGasto" itemLabel="tipoGasto" class="form-control form-control-lg" readonly="true" />
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <label for="nombres" class="col-sm-4 col-form-label"><spring:message  code="report.noteOrder.name"/></label>
                                    <div class="col-sm-8" id="combo2">
                                        <form:select class="form-control form-control-lg" path="Personas.idPersona" readonly="true">
                                            <c:forEach var="p" items="${requestScope.lPersonas}">
                                                <form:option value="${p.personas.idPersona}">${p.personas.nombres} ${p.personas.paterno} ${p.personas.materno} </form:option>
                                            </c:forEach>
                                        </form:select>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="codigo" class="col-sm-4 col-form-label"><spring:message  code="expenses.warehouse.certification"/></label>
                                     <div class="col-sm-4" id="combo1"> 
                                        <form:radiobutton path="certificacionAlmacen" value="1"/>&nbsp;&nbsp; : &nbsp;&nbsp;&nbsp;&nbsp;<spring:message  code="expenses.warehouse.certification"/>
                                    </div>
                                    <div class="col-sm-4" id="combo1"> 
                                        <form:radiobutton path="certificacionAlmacen" value="0"/>&nbsp;&nbsp; : &nbsp;&nbsp;&nbsp;&nbsp;<spring:message  code="expenses.Certificate.not"/>
                                    </div>

                                </div>
                                <div class="form-group row">
                                    <label for="codigo" class="col-sm-4 col-form-label"><spring:message  code="expenses.description"/></label>
                                    <div class="col-sm-8" id="combo1"> 
                                        <spring:message  code="expenses.description.msg" var="description"/>
                                        <form:textarea  path="descripcion" class="form-control form-control-lg" placeholder="${description}" rows="2" cols="20" readonly="true" /> 
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <label for="codigo" class="col-sm-4 col-form-label"><spring:message  code="expenses.cost"/></label>
                                    <div class="col-sm-8" id="combo1"> 
                                        <spring:message  code="expenses.cost.msg" var="cost"/>
                                        <form:input path="totalG" id="cost" value="${cjaGastosEjecutados.totalG}" class="montos form-control form-control-lg" placeholder="${cost}" readonly="true"/> 
                                    </div>
                                </div>
                                  <div class="form-group row">
                                    <label for="codigo" class="col-sm-4 col-form-label"><spring:message  code="expenses.withholding.amount"/></label>
                                    <div class="col-sm-4" id="combo1"> 
                                        <form:radiobutton path="retencionCheck" id="noCert" value="0" onclick="noRetenImp(this.value);"/>&nbsp;&nbsp; : &nbsp;&nbsp;&nbsp;&nbsp;<spring:message  code="expenses.retention.not"/>
                                    </div>
                                    <div class="col-sm-4" id="combo1"> 
                                        <form:radiobutton path="retencionCheck" id="cert" value="1" onclick="retenImp(this.value);"/>&nbsp;&nbsp; : &nbsp;&nbsp;&nbsp;&nbsp;<spring:message  code="expenses.retention"/>
                                    </div>
                                  </div>
                                 <div class="form-group row">
                                    <label for="codigo" class="col-sm-4 col-form-label"><spring:message  code="expenses.withholding.amount"/></label>
                                     <div class="col-sm-8" id="combo1"> 
                                        <spring:message  code="expenses.withholding.amount.msg" var="withholding"/>
                                        <form:input path="retencion" id="reten" class="form-control form-control-lg" placeholder="${withholding}" readOnly="true" /> 
                                    </div>
                                 </div>
                                 <div class="form-group row">
                                    <label for="codigo" class="col-sm-4 col-form-label"><spring:message  code="expenses.purchase"/></label>
                                    <div class="col-sm-8" id="combo1"> 
                                        <spring:message  code="expenses.purchase.msg" var="purchase"/>
                                        <form:input path="nroFactura" value="${cjaGastosEjecutados.nroFactura}" class="form-control form-control-lg" placeholder="${purchase}" readonly="true"/> 
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="codigo" class="col-sm-4 col-form-label"><spring:message  code="expenses.document"/></label>
                                    <div class="col-sm-8" id="combo1"> 
                                        <spring:message  code="expenses.document.msg" var="document"/>
                                        <form:input path="nroDocumento" value="${cjaGastosEjecutados.nroDocumento}" class="form-control form-control-lg" placeholder="${document}" readonly="true"/> 
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <label for="codigo" class="col-sm-4 col-form-label"><spring:message  code="expenses.date"/></label>
                                    <div class="col-sm-8" id="combo1"> 
                                        <spring:message  code="expenses.date.msgg" var="date"/>
                                        <form:input type="date" path="fecGasto" value="${cjaGastosEjecutados.fecGasto}" class="form-control form-control-lg"  readonly="true"/> 
                                    </div>
                                </div>
                                <hr align="left" noshade="noshade" size="10" width="100%" />
                                <div class="form-group row mt-12">
                                    <div class="col-sm-3" id="combo1"> 
                                    </div>

                                    <div class="col-sm-3" id="combo1">
                                        <span class="icon-input-btn"><i class="fa fa-times yvela" aria-hidden="true"></i> 
                                            <input type="submit"  class="btn btn-danger" value="<spring:message  code="expenses.btn.confirm"/>">
                                            </div>
                                            <div class="col-sm-3" id="combo1"> 
                                            </div>
                                            <div class="col-sm-3" id="combo1"> 
                                                <a href="<spring:url value="/gastos/inicioGastos"/>" class="btn btn-yvela-green"><i class="fa fa-undo" aria-hidden="true"></i>  <spring:message  code="expenses.btn.cancel"/></a>
                                            </div>
                                    </div>
                                </form:form>
                            </div>
                            <div class="col-sm-4" id="combo1"> 
                                <fieldset class="col-md-12">    
                                    <h6><span class="parpadea text"><p class="h5"><i class="fa fa-exclamation-triangle" aria-hidden="true"></i></p></span>   <spring:message  code="expenses.remove.info.title"/></h6> 
                                    <br>
                                    <c:set var="saldo" value="${bSaldo.saldo + cjaGastosEjecutados.totalG}" />
                                    <p><spring:message  code="expenses.delet.msg1"/> <b>${cjaGastosEjecutados.totalG}</b> Bs, <spring:message  code="expenses.delet.msg2"/><b> ${saldo}</b> Bs,<spring:message  code="expenses.delet.msg3"/> 
                                    </p>
                                </fieldset>
                            </div>
                        </div>
                    </div>
                </c:when>
            </c:choose>       

            <!-- ============= Fin Eliminar Gastos Ejecutados ============ -->
            <c:choose>
                <c:when test="${busqueda eq 'find' }">
                    <div class="col-sm-12" id="reporte"> 
                        <center>
                            <h5><i class="fa fa-list-alt" aria-hidden="true"></i> <spring:message  code="expenses.title.transaction"/></h5> 
                        </center>
                        <br>
                        <table id="tGastos"  class="table" style="width:100%">
                            <thead>
                                <tr>
                                    <th><spring:message  code="expenses.description"/></th>
                                    <th><spring:message  code="expenses.cost"/></th>
                                    <th><spring:message  code="expenses.date"/></th>
                                    <th><spring:message  code="expenses.purchase"/></th>
                                    <th><spring:message  code="expenses.type"/></th>
                                    <th><spring:message  code="expenses.modify"/></th>
                                    <th><spring:message  code="expenses.remove"/></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:set var="cont" value="0" />
                                <c:forEach var="gts" items="${requestScope.lGastosEjecutados}">
                                    <tr>
                                        <c:set var="cont" value="${cont+1}" />
                                        <td>${gts.descripcion}</td>
                                        <td>${gts.totalG}</td>
                                        <td>${gts.fecGasto}</td>
                                        <td>${gts.nroFactura}</td>
                                        <td>${gts.cjaTiposGastos.nroTipoGasto} ${gts.cjaTiposGastos.tipoGasto}</td>
                                        <td>
                                            <form action="${urlIM}" method="post" >
                                                <input type="hidden" name="idCjaGastoEjecutado" value="${gts.idCjaGastoEjecutado}">
                                                <button type="submit" class="btn btn-success"><i class="fa fa-pencil-square-o" aria-hidden="true"></i> <spring:message  code="expenses.modify"/></button>
                                            </form>
                                        </td>
                                        <td>
                                            <form name="eliminarGasto" action="${urlIE}" method="post" >
                                                <input type="hidden" name="idCjaGastoEjecutado" value="${gts.idCjaGastoEjecutado}">
                                                <button type="submit" class="btn btn-danger"><i class="fa fa-times" aria-hidden="true"></i>   <spring:message  code="expenses.remove"/></button>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                            <tfoot>
                            <th><spring:message  code="expenses.description"/></th>
                            <th><spring:message  code="expenses.cost"/></th>
                            <th><spring:message  code="expenses.date"/></th>
                            <th><spring:message  code="expenses.purchase"/></th>
                            <th><spring:message  code="expenses.type"/></th>
                            <th><spring:message  code="expenses.modify"/></th>
                            <th><spring:message  code="expenses.remove"/></th>
                            </tfoot>
                        </table>
                    </div>
                </c:when>
            </c:choose>
        </div>
    </section>    
</div>

<!--Modal Registrar Tipos Proveedores ----------- -->

<div id="formCjaProveedores" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form name="cjaProveedoresForm" action="guardarCjaProveedores" role="form" id="proveedorForm" method="post">
                <div class="modal-header">
                    <p class="h3"><i class="fa fa-user-secret" aria-hidden="true"></i>  <spring:message  code="expenses.provider.new"/></p>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                </div>
                <div class="modal-body">
                    <div class="form-group row">
                        <spring:message  code="expenses.provider.new.msg" var="items"/>
                        <label for="item" class="col-sm-3 col-form-label"><spring:message  code="expenses.provider"/></label>
                        <label for="items" class="col-sm-1 col-form-label"><i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-8">
                            <input type="text" name="proveedor" class="form-control form-control-lg"  placeholder="${items}" />
                        </div>
                    </div>
                    <div class="form-group row">
                        <spring:message  code="items.institution.code.msg" var="sigla"/>
                        <label for="cargo" class="col-sm-3 col-form-label"><spring:message  code="items.institution.code"/></label>
                        <label for="tipoSexo" class="col-sm-1 col-form-label"><i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-8">
                            <input type="text" name="sigla" class="form-control form-control-lg"  placeholder="${sigla}" />
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-outline-secondary btn-lg" data-dismiss="modal" aria-hidden="true"><i class="fa fa-undo" aria-hidden="true"></i>  <spring:message  code="people.cancel"/></button>
                    <button type="submit" class="btn btn-success btn-lg float-right" id="btnCjaProveedores" data-dismiss="modal" aria-hidden="true"><i class="fa fa-plus-square-o" aria-hidden="true"></i>   <spring:message  code="people.add"/></button>
                </div>
            </form>
        </div>
    </div>
</div>
<!--Modal Registrar Tpos Gastos -->             
<div id="formCjaTiposGastos" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form:form name="cjaTiposGastosForm" modelAttribute="cjaTiposGastos" action="guardarCjaTiposGastos" role="form" id="gastosForm" method="post">
                <div class="modal-header">
                    <p class="h3"><i class="fa fa-user-secret" aria-hidden="true"></i>  <spring:message  code="expenses.type.expenses.new"/></p>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                </div>
                <div class="modal-body">
                    <div class="form-group row">
                        <spring:message  code="expenses.type.expenses.msg" var="sedes"/>
                        <label for="sedes" class="col-sm-3 col-form-label"><spring:message  code="report.account.expense"/></label>
                        <label for="sdess" class="col-sm-1 col-form-label"><i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-8">
                            <input type="text" name="tipoGasto" class="form-control form-control-lg"  placeholder="${sedes}" />
                        </div>
                    </div>
                    <div class="form-group row">
                        <spring:message  code="expenses.type.expenses.code" var="cmsg"/>
                        <label for="cItem" class="col-sm-3 col-form-label"><spring:message  code="institucion.codigo"/></label>
                        <label for="cItems" class="col-sm-1 col-form-label"><i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-8">
                            <input type="text" name="nroTipoGasto" class="form-control form-control-lg"  placeholder="${cmsg}" />
                        </div>
                    </div>
                    <div class="form-group row">
                        <spring:message  code="expenses.type.clasification" var="sigla"/>
                        <label for="cItem" class="col-sm-3 col-form-label"><spring:message  code="expenses.type.clasification"/></label>
                        <label for="cItems" class="col-sm-1 col-form-label"><i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-8">
                            <form:select class="form-institution form-control form-control-lg" path="CjaGastosClasificaciones.idCjaGastoClasificacion" >
                                <c:forEach var="l" items="${requestScope.lGastoClasificacion}">
                                    <form:option value="${l.idCjaGastoClasificacion}"> ${l.nroGastoClasificacion}-${l.gastoClasificacion}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-outline-secondary btn-lg" data-dismiss="modal" aria-hidden="true"><i class="fa fa-undo" aria-hidden="true"></i>  <spring:message  code="people.cancel"/></button>
                    <button type="submit" class="btn btn-success btn-lg float-right" id="btnCjaTiposGastos" data-dismiss="modal" aria-hidden="true"><i class="fa fa-plus-square-o" aria-hidden="true"></i>   <spring:message  code="people.add"/></button>
                </div>
            </form:form>
        </div>
    </div>
</div>

