<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<head>
    </head>
<section class="higher">

     <div class="panel-body">
        <div class="form-group row">
                 <div class="col-sm-2" id="combo2"> 
                   <div class="logouap">
                       <img src="<c:url value="/resource/images/logs/logouap.jpg" />"/>
                   </div>
                      <br> <center><p class="h2"><spring:message  code="system.institution.initials"/></p></cneter>
                  </div>
                 <div class="col-sm-10" id="combo1"> 
                    <p class="h1"><spring:message  code="system.welcome"/></p>
                    <hr align="left" noshade="noshade" size="10" width="100%" />
                    <p class="h5"><spring:message  code="system.name"/></p>
                    <br>
                    <br>
                    <br>
                     <p class="h7">  <spring:message  code="system.unit"/></p>
                    <br>
                    </div>
                   
            </div>
       </div> 
                          
</section>