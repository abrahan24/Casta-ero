<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
    <link rel="stylesheet" type="text/css"  href=" <c:url value="/resource/admGastos/gastos.css"/>" />
    <script src="<spring:url value="/resource/admReportes/reportesAutoridad/reporteArqueoAutoridad.js" />" type="text/javascript"></script>
    <script type="text/javascript">
    </script>
</head>

<div class="container">
    <section class="content-header">
        <div class="row">
         <c:choose>
            <c:when test="${operation eq 'unidad' }">
            <div  class="col-md-02 mb-3">
                <p><a  class="btn btn-primary" href="formReporteArqueoFondosCajaAutoridad"   id="show"><i class="fa fa-arrow-left" aria-hidden="true"></i> Volver</a></p>
            </div>
            </c:when>
         </c:choose>
            
            <div class="col-md-10 mb-3">
                <p class="h1"><i class="fa fa-money" aria-hidden="true"></i> <spring:message  code="report.cash.box.unity.menu"/> </p>
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
        <!----------------Formulario Reporte Arqueo Cajas Gastos Unidades-Autoridad======================================================== -->
        <spring:url var="formUrl" value="signupsss" />    
        <c:choose>
            <c:when test="${operation eq 'form' }">

                <center> <p class="h3"><i class="fa fa-th" aria-hidden="true"></i>  <spring:message  code="report.cash.box.unity.detail"/></p> </center>
                <br>
                <form action="${mostrarUnidaades}" method="post" >

                    <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="ingreso.gestion"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6" id="gestion"> 
                            <spring:message  code="report.cash.box.unity.mangement" var="description"/>
                            <input type="text" name="gestion" class="form-control form-control-lg" placeholder="${description}" rows="3" cols="20" > 
                        </div>
                        <div class="col-sm-3" id="comd"> 
                            <small id="emailHp" class="form-text text-muted">
                                <spring:message  code="report.cash.box.unity.mangement"/>
                            </small>
                            <small class="msgError">
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="sede.sede"/></label>
                        <label class="col-sm-1 col-form-label text-right" ></label>
                        <div class="col-sm-6" id="combop"> 
                            <select class="form-sedes form-control form-control-lg" name="idSede" id="idSede" >
                                <c:forEach var="l" items="${requestScope.lSedes}">
                                    <option value="${l.idSede}">${l.sede}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                            <small id="emailHp" class="form-text text-muted">
                                <spring:message  code="institution.headquarters.msg"/>
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="report.cash.box.unity.direction"/></label>
                        <label class="col-sm-1 col-form-label text-right" ></label>
                        <div class="col-sm-6" id="comboDireccion"> 
                            <select class="form-sedes form-control form-control-lg" name="idDireccionFuncional"  id="dFuncional" >
                                <option value=""> <spring:message  code="report.cash.box.unity.direction.msg"/></option>
                            </select>
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                            <small id="emailHp" class="form-text text-muted">
                                <spring:message  code="report.cash.box.unity.direction.msg"/>
                            </small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="sedes" class="col-sm-2 col-form-label"><spring:message  code="units.unit"/></label>
                        <label class="col-sm-1 col-form-label text-right" ></label>
                        <div class="col-sm-6" id="comboDireccion"> 
                            <select class="form-sedes form-control form-control-lg" name="idUnidadFuncional"  id="uFuncional" >
                                <option value=""> <spring:message  code="items.unit.msg"/></option>
                            </select>
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                            <small id="emailHp" class="form-text text-muted">
                                <spring:message  code="items.unit.msg"/>
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
                            <span class="icon-input-btn"><i class="fa fa-pencil-square-o yvela" aria-hidden="true"></i> <input class="btn btn-success" type="submit" value="<spring:message  code="report.cash.box.unity.search"/>"></span>
                        </div>
                        <div class="col-sm-3" id="combo1"> 
                        </div>
                        <div class="col-sm-3" id="combo1">
                            <a href="<spring:url value="/reportes/formReporteArqueoFondosCajaAutoridad"/>" class="btn btn-yvela-green"><i class="fa fa-undo yvela" aria-hidden="true"></i>  <spring:message  code="expenses.btn.cancel"/></a>
                        </div> 
                    </div>
                </c:when>
            </c:choose>
        </form>

        <!--finalll -->

        <!-- Inicio listar unidades -->
        <c:choose>
            <c:when test="${operation eq 'unidad' }">
                <center>
                    <p class="h3"><i class="fa fa-list-alt" aria-hidden="true"></i> <spring:message  code="income.list.income.title"/></p>
                </center>
                <br>
                <form:form modelAttribute="cjaGastosEjecutado" action="${repArqCajaAutoridad}" method="post" target="blank">
                    <table class="table">
                        <tr class="trIngreso">
                            <th align="center"> ? </th>
                            <th align="center"><spring:message  code="income.type.income"/></th>
                            <th align="center"><spring:message  code="income.amount.entered"/></th>
                            <th align="center"><spring:message  code="income.balance"/></th>
                            <th align="center"><spring:message  code="income.date.entered"/></th>
                            <th align="center"><spring:message  code="income.percentage"/></th>
                            <th align="center"><spring:message  code="income.document.number"/></th>
                            <th align="center"><spring:message  code="items.start.date"/></th>
                            <th align="center"><spring:message  code="items.finish.date"/></th>
                        </tr>

                        <c:set var="cont" value="0" />
                        <c:forEach items="${lIngresosU}" var="li">
                            <c:set var="cont" value="${cont+1}" />
                            <tr id="thing">
                                <td><input type="radio"  name="idCjaIngreso"  required="true" value="${li.idCjaIngreso}" /></td>
                                <td>${li.cjaTiposIngresos.tipoIngreso}</td>
                                <td><fmt:formatNumber value="${li.monto}" pattern="0.00"/></td>
                                <td><fmt:formatNumber value="${li.saldo}" pattern="0.00"/></td>
                                <td><fmt:formatDate type="date" value="${li.fecIngreso}"/></td>
                                <td><fmt:formatNumber value="${li.porcentajeGasto}" pattern="0.00"/></td>
                                <td>${li.nroDocumento}</td>
                                <td><input type="date" name="fecIngreso${li.idCjaIngreso}" class="form-control form-control-lg" ></td>
                                <td><input type="date" name="fecGasto${li.idCjaIngreso}" class="form-control form-control-lg" /> 
                                    <input type="hidden" name="algo${li.idCjaIngreso}" value="algo">
                                </td>
                            </tr>
                        </c:forEach> 
                    </table>
                    <hr align="left" noshade="noshade" size="10" width="100%" />
                    <div class="form-group row mt-3">
                        <div class="col-10 offset-2">
                            <input class="btn btn-success" type="submit" value="<spring:message  code="reporte.verReporte"/>">
                        </div>
                    </div>
                </form:form> 
            </section>
        </c:when>
    </c:choose>
</section>
</div>>