<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>
    <link rel="stylesheet" type="text/css"  href=" <c:url value="../../../../resource/css/main.css"/>" />
    <link rel="stylesheet" type="text/css"  href=" <c:url value="/resource/admGastos/gastos.css"/>" />
    <script src="<spring:url value="/resource/estadisticas/cajita/gastoUnidad.js" />" type="text/javascript"></script>
    <script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>

    <script type="text/javascript">
        window.onload = function () {

        var chart = new CanvasJS.Chart("chartContainer", {
        theme: "dark1", // "light2", "dark1", "dark2"
                animationEnabled: false, // change to true		
                title:{
                text: "<spring:message  code="totals.address.expenses.percentage"/>"
                },
                data: [
                {
                // Change type to "bar", "area", "spline", "pie",etc.
                type: "column",
                        dataPoints: [
        <c:forEach var="ldf" items="${requestScope.lDirecciones}">
            <c:set var="porcentaje" value="0" />
            <c:set var="montoMaximo" value="0" />
            <c:set var="saldo" value="0" />
            <c:set var="totalGasto" value="0" />
            <c:forEach var="gts" items="${requestScope.lGastoDireccion}">
                <c:if  test="${gts.idDireccionFuncional == ldf.idDireccionFuncional}">
                    <c:set var="montoMaximo" value="${montoMaximo + gts.montoMaximo}" />
                    <c:set var="saldo" value="${saldo + gts.saldo}" />
                    <c:set var="totalGasto" value="${totalGasto + gts.totalGasto}" />
                </c:if>
            </c:forEach>
            <c:set var="porcentaje" value="${(totalGasto*100)/montoMaximo}" />
                        { label: "${ldf.sigla}", y: ${porcentaje}  },
        </c:forEach>

                        ]
                }
                ]
        });
        chart.render();
        }
    </script>
</head>

<div class="container">
    <section class="content-header">
        <div class="row">
            <c:choose>
                <c:when test="${operation eq 'list' }">
                    <div class="col-md-02 mb-3">
                        <p><a  class="btn btn-primary" href="inicioGastoDirection"   id="show"><i class="fa fa-arrow-left" aria-hidden="true"></i> Volver</a></p>
                    </div>
                </c:when>
            </c:choose>
            <div class="col-md-10 mb-3">
                <p class="h1"><i class="fa fa-money" aria-hidden="true"></i> <spring:message  code="totals.address.expenses.title"/> </p>
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
        <!----------------Formulario Totales Gastos Unidades======================================================== -->
        <spring:url var="formUrl" value="signupsss" />    
        <c:choose>
            <c:when test="${operation eq 'form' }">
                <center> <p class="h3"><i class="fa fa-th" aria-hidden="true"></i>  <spring:message  code="totals.address.expenses.menu"/></p> </center>
                <br>
                <form action="${urlI}" method="post" >

                    <div class="form-group row">
                        <label for="codigo" class="col-sm-2 col-form-label"><spring:message  code="items.year"/></label>
                        <label class="col-sm-1 col-form-label text-right" >  <i class="fa fa-info-circle" aria-hidden="true"></i></label>
                        <div class="col-sm-6" id="gestion"> 
                            <spring:message  code="report.cash.box.unity.mangement" var="description"/>
                            <input type="text" name="gestion"  class="form-control form-control-lg" placeholder="${description}"> 
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
                            <select class="form-sedes form-control form-control-lg" name="idSede" >
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
                            <a href="<spring:url value="/gDirection/inicioGastoDirection"/>" class="btn btn-yvela-green"><i class="fa fa-undo yvela" aria-hidden="true"></i>  <spring:message  code="expenses.btn.cancel"/></a>
                        </div> 
                    </div>
                </form>
            </c:when>
        </c:choose>

        <!-- ============= vista Previa Gastos Unidad ========= -->
        <c:choose>
            <c:when test="${operation eq 'list' }">
                <center> <p class="h3"><i class="fa fa-th" aria-hidden="true"></i>  <spring:message  code="totals.address.expenses.menu"/></p> </center>
                <br>
                <form action="${urlImp}" method="post" target="blank" >
                    <input type="hidden" name="idSede" value="${idSede}">
                    <input type="hidden" name="gestion" value="${gestion}">
                    <table class="table" style="width:100%">
                        <tr>
                            <th width="20%"><spring:message  code="sede.sede"/> </th>
                            <th width="2%">::</th>
                            <td width="78%">${bSede.sede}</td>
                        </tr>
                        <tr>
                            <th width="20%"><spring:message  code="items.institution.code"/></th>
                            <th width="2%">::</th>
                            <td width="78%">${bSede.codigo}</td>
                        </tr>
                    </table>
                    <br><br>
                    <table class="table" style="width:100%">
                        <thead>
                            <tr>
                                <th><spring:message  code="report.cash.box.unity.direction"/></th>
                                <th><spring:message  code="items.institution.code"/></th>
                                <th><spring:message  code="report.arching.totalAmount"/></th>
                                <th><spring:message  code="income.balance"/></th>
                                <th><spring:message  code="report.arching.total"/></th>
                                <th><spring:message  code="income.percentage"/> %</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="ldf" items="${requestScope.lDirecciones}">
                                <c:set var="porcentaje" value="0" />
                                <c:set var="montoMaximo" value="0" />
                                <c:set var="saldo" value="0" />
                                <c:set var="totalGasto" value="0" />
                                <c:forEach var="gts" items="${requestScope.lGastoDireccion}">
                                    <c:if  test="${gts.idDireccionFuncional == ldf.idDireccionFuncional}">  
                                        <c:set var="montoMaximo" value="${montoMaximo + gts.montoMaximo}" />
                                        <c:set var="saldo" value="${saldo + gts.saldo}" />
                                        <c:set var="totalGasto" value="${totalGasto + gts.totalGasto}" />
                                    </c:if>
                                </c:forEach>
                                <tr>
                                    <c:set var="porcentaje" value="${(totalGasto*100)/montoMaximo}" />
                                    <td>${ldf.direccionFuncional}</td>
                                    <td>${ldf.sigla}</td>
                                    <td><fmt:formatNumber value="${montoMaximo}" pattern="0.00"/></td>
                                    <td><fmt:formatNumber value="${saldo}" pattern="0.00"/></td>
                                    <td><fmt:formatNumber value="${totalGasto}" pattern="0.00"/></td>
                                    <td><fmt:formatNumber value="${porcentaje}" pattern="0.00"/> %</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                        <tfoot>
                        <th><spring:message  code="report.cash.box.unity.direction"/></th>
                        <th><spring:message  code="items.institution.code"/></th>
                        <th><spring:message  code="report.arching.totalAmount"/></th>
                        <th><spring:message  code="income.balance"/></th>
                        <th><spring:message  code="report.arching.total"/></th>
                        <th><spring:message  code="income.percentage"/> %</th>
                        </tfoot>
                    </table>

                    <hr align="left" noshade="noshade" size="10" width="100%" />
                    <div class="form-group row mt-12">
                        <div class="col-sm-12" id="chartContainer" style="height: 370px; max-width: 920px; margin: 0px auto;"></>
                        </div> 
                    </div> 
                    <hr align="left" noshade="noshade" size="10" width="100%" />
                    <div class="form-group row">
                        <label for="msg" class="col-sm-2 col-form-label text-right"></label>
                    </div>  
                    <div class="form-group row mt-12">
                        <div class="col-sm-3" id="combo1">
                        </div> 
                        <div class="col-sm-3" id="combo1"> 
                               <span class="icon-input-btn"><i class="fa fa-pencil-square-o yvela" aria-hidden="true"></i> <input class="btn btn-success" type="submit" value="<spring:message  code="expenses.btn.print"/>"></span>
                        </div>
                        <div class="col-sm-3" id="combo1">
                        </div> 
                    </div>
                </form>
            </c:when>
        </c:choose>

    </section>
</div>
