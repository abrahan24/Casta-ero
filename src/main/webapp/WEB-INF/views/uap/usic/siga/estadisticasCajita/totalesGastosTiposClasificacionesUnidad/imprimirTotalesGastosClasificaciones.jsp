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
        <title>Gastos por Partida</title>
        <link rel="stylesheet" type="text/css"  href=" <c:url value="/resource/vendor/bootstrap/css/bootstrap.css"/>" />
        <script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
        <script type="text/javascript">
            window.onload = function () {

                var chart = new CanvasJS.Chart("chartContainer", {
                    theme: "dark1", // "light2", "dark1", "dark2"
                    animationEnabled: false, // change to true		
                    title: {
                        text: "<spring:message  code="departure.expenses.unity.porcentage"/>"
                    },
                    data: [
                        {
                            // Change type to "bar", "area", "spline", "pie",etc.
                            type: "column",
                            dataPoints: [
            <c:forEach var="gpu" items="${requestScope.lPartidasUnidad}">
                                {label: "${gpu.nroTipoClasificacion}", y: ${(gpu.total*100)/gpu.montoMaximo}},
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
                                        <tr><td height="50" align="center" valign="middle"><font size="5" face="sans-serif"><b><spring:message code="departure.expenses.unity.form"/></b></font></td></tr>
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
                <tr><td align="center"><br><h1><font face="sans-serif"><spring:message code="departure.expenses.unity.title"/></font></h1></td></tr>
                <tr><td width="100%"><br><br>
                        <table class="table" style="width:100%">
                            <tr>
                                <th width="20%"><spring:message  code="units.unit"/></th>
                                <th width="2%">::</th>
                                <td width="78%">${bUnidad.unidadFuncional}</td>
                            </tr>
                            <tr>
                                <th width="20%"><spring:message  code="items.institution.code"/></th>
                                <th width="2%">::</th>
                                <td width="78%">${bUnidad.sigla}</td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </thead>
            <tbody>
                <tr><td align="center"><br><br>
                        <table class="table" style="width:100%">
                            <thead>
                                <tr>
                                    <th><spring:message  code="departure.expenses.departure"/></th>
                                    <th><spring:message  code="departure.expenses.code"/></th>
                                    <th><spring:message  code="report.arching.total"/></th>
                                    <th><spring:message  code="income.percentage"/> %</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:set var="porcentaje" value="0" />
                                <c:set var="tPorcentaje" value="0" />
                                <c:set var="mMaximo" value="0" />
                                <c:forEach var="gpu" items="${requestScope.lPartidasUnidad}">
                                    <tr>
                                        <c:set var="porcentaje" value="${(gpu.total*100)/gpu.montoMaximo}" />
                                        <td>${gpu.tipoClasificacion}</td>
                                        <td>${gpu.nroTipoClasificacion}</td>
                                        <td><fmt:formatNumber value="${gpu.total}" pattern="0.00"/></td>
                                        <td><fmt:formatNumber value="${porcentaje}" pattern="0.00"/> %</td>
                                        <c:set var="tPorcentaje" value="${tPorcentaje + gpu.total}" />
                                        <c:set var="mMaximo" value="${gpu.montoMaximo}" />
                                        <c:set var="porcentaje" value="0" />
                                    </tr>
                                </c:forEach>
                                <tr>
                                    <th colspan="2">Total</th>
                                    <th><fmt:formatNumber value="${tPorcentaje}" pattern="0.00"/></th>
                                    <th><fmt:formatNumber value="${(tPorcentaje*100)/mMaximo}" pattern="0.00"/> %</th>
                                </tr>
                            </tbody>
                            <tfoot>
                            <th><spring:message  code="departure.expenses.departure"/></th>
                            <th><spring:message  code="departure.expenses.code"/></th>
                            <th><spring:message  code="report.arching.total"/></th>
                            <th><spring:message  code="income.percentage"/> %</th>
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