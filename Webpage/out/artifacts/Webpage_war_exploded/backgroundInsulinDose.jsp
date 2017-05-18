<%--
  Created by IntelliJ IDEA.
  User: Afonso
  Date: 16/05/2017
  Time: 12:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">

    <jsp:useBean id="backgroundInsulinDose" class="types.BackgroundInsulinDose" scope="application"/>
    <jsp:setProperty name="backgroundInsulinDose" property="*"/>

    <title>Background Insulin Calculator (Results)</title>
</head>
<body>

<div class="alert alert-success" role="alert" style="margin:20px;">

    <h3 style="margin-left: 10px">Number of insulin units: </h3>

    <h2 style="margin-left: 10px"><%= backgroundInsulinDose.getResult()%></h2>

    <h3 style="margin-left: 10px"><%= backgroundInsulinDose.getWebServiceName()%></h3>

</div>

</body>
</html>
