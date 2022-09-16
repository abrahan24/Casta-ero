<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/><!--opcion 1-->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title><tiles:insertAttribute name="title"/></title>
    <tiles:insertAttribute name="heading"/>

</head>

<body>
    
     <div class="header">
         <div class="">
                <tiles:insertAttribute name="navigation"/>
        </div>
     </div>

    <div class="mainContent p-t-50">
         <tiles:insertAttribute name="body"/>
     </div>

    <div class="footer p-t-100" >
        <div>
           <tiles:insertAttribute name="footer"/>
        </div>
    </div>

  </body>
</html>
