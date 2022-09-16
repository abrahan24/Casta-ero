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
        <title>Gastos por Sedes</title>
        <link rel="stylesheet" type="text/css"  href=" <c:url value="/resource/vendor/bootstrap/css/bootstrap.css"/>" />
        <script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
        <script type="text/javascript">
            window.onload = function () {

            var chart = new CanvasJS.Chart("chartContainer", {
            theme: "dark1", // "light2", "dark1", "dark2"
                    animationEnabled: false, // change to true		
                    title:{
                    text: "<spring:message  code="headquarters.statistics.porcentage"/>"
                    },
                    data: [
                    {
                    // Change type to "bar", "area", "spline", "pie",etc.
                    type: "column",
                            dataPoints: [
            <c:set var="montucho" value="0" />
            <c:forEach var="mx" items="${requestScope.lMontosMaximo}">
                <c:set var="montucho" value="${montucho + mx.montoMaximo}" />
            </c:forEach>
            <c:forEach var="ltg" items="${requestScope.lTotalesClasif}">
                            { label: "${ltg.nroTipoClasificacion}", y: <fmt:formatNumber value="${(ltg.total*100)/montucho}" pattern="0.00"/> },
            </c:forEach>
                            ]
                    }
                    ]
            });
            chart.render();
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
                                        <tr><td height="50" align="center" valign="middle"><font size="5" face="sans-serif"><b><spring:message code="headquarters.statistics.form"/></b></font></td></tr>
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
                <tr><td align="center"><br><h1><font face="sans-serif"><spring:message code="headquarters.statistics.title"/></font></h1></td></tr>
                <tr><td width="100%"><br><br>
                        <table class="table" style="width:100%">
                            <tr>
                                <th width="20%"><spring:message  code="sede.sede"/></th>
                                <th width="2%">::</th>
                                <td width="78%">${bSede.sede}</td>
                            </tr>
                            <tr>
                                <th width="20%"><spring:message  code="items.institution.code"/></th>
                                <th width="2%">::</th>
                                <td width="78%">${bSede.codigo}</td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </thead>
            <tbody>
                <tr><td align="center"><br><br>
                        <table id="tGeneral" class="table" style="width:100%">
                            <thead>
                                <tr>
                                    <th><spring:message  code="headquarters.statistics.unit"/></th> 
                                        <c:set var="cant" value="0" />
                                        <c:forEach var="ltc" items="${requestScope.lClasificacion}">
                                            <c:set var="cant" value="${cant+1}"/>
                                        <th>${ltc.nroTipoClasificacion} </th>
                                        </c:forEach>
                                </tr>
                            </thead>
                            <tbody>
                                <c:set var="montoM" value="0" />
                                <c:forEach var="mx" items="${requestScope.lMontosMaximo}">
                                    <c:set var="montoM" value="${montoM + mx.montoMaximo}" />
                                </c:forEach>
                            <p class="h4" style="text-align: center"><spring:message  code="headquarters.statistics.form.detail"/></p><br> 
                            <c:forEach var="ldr" items="${requestScope.lDireccion}">
                                <tr><th colspan="${cant+1}" style="text-align: center">${ldr.direccionFuncional} </th></tr>  
                                        <c:forEach var="luf" items="${requestScope.lUnidades}">
                                            <c:if  test="${ldr.idDireccionFuncional == luf.insDireccionesFuncionales.idDireccionFuncional}">
                                        <tr><th>${luf.unidadFuncional}</th>
                                                <c:set var="clasif" value="0" />
                                                <c:set var="cero" value="0" />
                                                <c:forEach var="ltc" items="${requestScope.lClasificacion}">  
                                                    <c:set var="band" value="0" />
                                                    <c:forEach var="lgr" items="${requestScope.lGeneral}">  
                                                        <c:if  test="${(luf.idUnidadFuncional == lgr.idUnidadFuncional) && (ltc.idCjaTipoClasificacion == lgr.idCjaTipoClasificacion) }">
                                                        <td>${lgr.total}</td>
                                                        <c:set var="band" value="1" />
                                                    </c:if>
                                                </c:forEach>
                                                <c:if  test="${(cero == 0) && (band == 0) && (clasif == 0) }">
                                                    <td>0</td>
                                                    <c:set var="band" value="1" />
                                                    <c:set var="cero" value="1" />
                                                </c:if>
                                                <c:if  test="${(clasif == 1) && (band == 0) }">
                                                    <td>0</td>
                                                </c:if>
                                                <c:set var="clasif" value="1" />
                                            </c:forEach> 
                                        </tr>
                                    </c:if>
                                </c:forEach>
                            </c:forEach>
            </tbody>
            <tfoot>
                <tr>
                    <th><spring:message  code="headquarters.statistics.total"/></th> 
                        <c:set var="clasif" value="0" />
                        <c:set var="cant" value="0" />
                        <c:forEach var="ltc" items="${requestScope.lClasificacion}">
                            <c:set var="clasif" value="1" />
                            <c:set var="band" value="0" />
                            <c:forEach var="ltg" items="${requestScope.lTotalesClasif}">
                                <c:if  test="${(ltg.idCjaTipoClasificacion == ltc.idCjaTipoClasificacion) }">
                                <th>${ltg.total} </th>
                                    <c:set var="band" value="1" />
                                </c:if>
                            </c:forEach>
                            <c:if  test="${(clasif == 1) && (band == 0) }">
                            <th>0 </th>
                            </c:if>
                        </c:forEach>
                </tr>
                <tr>
                    <th><spring:message  code="headquarters.statistics.total.porcentage"/></th> 
                        <c:set var="cant" value="0" />
                        <c:forEach var="ltc" items="${requestScope.lClasificacion}">
                            <c:set var="clasif" value="1" />
                            <c:set var="band" value="0" />
                            <c:forEach var="ltg" items="${requestScope.lTotalesClasif}">
                                <c:if  test="${(ltg.idCjaTipoClasificacion == ltc.idCjaTipoClasificacion) }">
                                <th><fmt:formatNumber value="${(ltg.total*100)/montoM}" pattern="0.00"/> %</th>
                                    <c:set var="band" value="1" />
                                </c:if>
                            </c:forEach>
                            <c:if  test="${(clasif == 1) && (band == 0) }">
                            <th>0</th>
                            </c:if>
                        </c:forEach>
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