<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container">
    <section class="content-header">
        <div class="row">
            <div class="col-md-10 mb-3">
                <p class="h1"> <i class="fa fa-money" aria-hidden="true"></i> <spring:message code="report.global.title"/></p>
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



        <form:form modelAttribute="cjaGastosEjecutado" action="${repArqueoGlobal}" method="post" target="blank">

       <table class="table">           
           <tr class="trIngreso">
                  
                      <th align="center"> ?  </th>
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
                <c:forEach items="${lIngresos}" var="li">
                    <c:set var="cont" value="${cont+1}" />
                    <tr>
                      
                      <td><input type="radio"  name="idCjaIngreso"  required="true" value="${li.idCjaIngreso}" /></td>
                      <td>${li.cjaTiposIngresos.tipoIngreso}</td>
                      <td>${li.monto}</td>
                      <td><fmt:formatNumber value="${li.saldo}" pattern="0.00"/></td>
                      <td><b>${li.fecIngreso}</b></td>
                      <td>${li.porcentajeGasto}</td>
                      <td>${li.nroDocumento}</td>
                      <td>
                           <input type="date" name="fecIngreso${li.idCjaIngreso}" class="form-control form-control-lg">
                      </td>
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
</div>