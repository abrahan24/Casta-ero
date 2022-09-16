<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container">
    <section class="content-header">
        <div class="row">
            <div class="col-md-10 mb-3">
                <p class="h1"> <i class="fa fa-money" aria-hidden="true"></i> <spring:message code="report.noteOrder.title"/></p>
            </div>
            <div class="col-sm-2 text-right" id="reporte">
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
            <p class="h3"><i class="fa fa-list-alt" aria-hidden="true"></i> <spring:message  code="income.list.income.title"/></p>
        </center>
        <br>
        <table class="table">
                <tr class="trIngreso">
                      <th align="center"><spring:message  code="income.type.income"/></th>
                      <th align="center"><spring:message  code="income.amount.entered"/></th>
                      <th align="center"><spring:message  code="income.balance"/></th>
                      <th align="center"><spring:message  code="income.date.entered"/></th>
                      <th align="center"><spring:message  code="income.percentage"/></th>
                      <th align="center"><spring:message  code="income.document.number"/></th>
                    <th width="100"><center><spring:message  code="expenses.btn.detail"/></center></th>
                </tr>
                <c:forEach items="${lIngresos}" var="li">
                    <tr  id="thing">
                        <td>${li.cjaTiposIngresos.tipoIngreso}</td>
                            <td>${li.monto}</td>
                            <td><fmt:formatNumber value="${li.saldo}" pattern="0.00"/></td>
                            <td><b>${li.fecIngreso}</b></td>
                            <td>${li.porcentajeGasto}</td>
                            <td>${li.nroDocumento}</td>
                    <td><center>
                    <form modelAttribute="cjaGastosEjecutados" action="${repNotaPed}" method="post">
                        <input type="hidden" name="idCjaIngreso" value="${li.idCjaIngreso}">
                        <button type="submit" class="btn btn-success"><i class="fa fa-eye" aria-hidden="true"> </i> <spring:message  code="expenses.btn.detail"/> </button> 
                    </form>
                </center>
                </td>
                </tr>
            </c:forEach> 
        </table>
    </section>
</div>