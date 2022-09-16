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
        <title>Totales Gastos por Direcciones</title>
        <link rel="stylesheet" type="text/css"  href=" <c:url value="/resource/vendor/bootstrap/css/bootstrap.css"/>" />
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
    <body>
    <center>
        <table width="90%" border="0" cellspacing="0" cellpadding="0">  
            <thead>
                <tr><td>
                        <table width="100%" border="1" cellspacing="0" cellpadding="0">  
                            <tr><td align="center" width="20%" ><img border="0" src="${pageContext.request.contextPath}/resource/images/logs/logouap.jpg" class="img-rounded"  class="img-responsive" alt="UAP" width="65" height="80"></td>
                                <td width="60%" height="100%"> <table border="1" width="100%" cellspacing="0" cellpadding="0">  
                                        <tr><td height="30" align="center"><font size="3" face="sans-serif"><b><spring:message code="form.title"/></b></font></font></td> </tr>    
                                        <tr><td height="50" align="center" valign="middle"><font size="5" face="sans-serif"><b><spring:message code="totals.address.expenses.form"/></b></font></td></tr>
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
                <tr><td align="center"><br><h1><font face="sans-serif"><spring:message code="totals.address.expenses.title"/></font></h1></td></tr>
                <tr><td width="100%"><br><br>
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
                    </td>
                </tr>
            </thead>
            <tbody>
                <tr><td align="center"><br><br>

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