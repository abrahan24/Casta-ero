<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<head>
    <style>

        #numero {padding:10px;text-align:right;}
    </style>
</head>
<div class="container">
    <div class="form-group row">
        <div class="col-sm-2" id="combo2"> 
            <div class="logouap">
                <img src="<c:url value="/resource/images/logs/logouap.jpg" />"/>
            </div>
            <br> <center><p class="h2"><spring:message  code="system.institution.initials"/></p></cneter>
        </div>
        <div class="col-sm-10" id="combo1"> 
            <p class="h1">GACETA INSTITUCIONAL UAP</p>
            <hr align="left" noshade="noshade" size="10" width="100%" />
            
            <div class="row">
	            <div class="col-sm-7">
		            <br>
		            
		            <a class="h6" href="">1.- Congreso Institucional Universitario</a>
		            <br>
		            
		            <a class="h6" href="">2.- Resoluciones del Honorable Consejo Universitario</a>
		            <br>
		            
		            <a class="h6" href="">3.- Resoluciones del Comité Ejecutivo del H.C.U.</a>
		            <br>
		            
		            <a class="h6" href="">4.- Resoluciones Rectorales</a>
		            <br>
		            
		            <a class="h6" href="">5.- Reglamentos Universitarios</a>
		            <br>
		            
		            <a class="h6" href="">6.- Convenios</a>
		            <br>
		            
		            <a class="h6" href="">7.- Normativa</a>
		            <br>
	            
	            </div>
	            
	            <div class="col-sm-5">
	            
		            <img style="max-width: 67%;" src="<c:url value="/resource/images/construccion.jpg" />"/>
		            
	            </div>
            </div>
            
            
            <hr align="left" noshade="noshade" size="10" width="100%" />
            <br>
        </div>
    </div>
</div>
