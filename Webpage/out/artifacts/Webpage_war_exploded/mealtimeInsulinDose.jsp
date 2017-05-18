<%--
  Created by IntelliJ IDEA.
  User: Afonso
  Date: 15/0/2017
  Time: 16:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="stylesheet" type="text/css" href="style.css">
    <meta content="text/html;charset=utf-8" http-equiv="Content-Type">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="charset" content="utf-8">
    <script src="jquery-3.2.1.min.js"></script>
    <title>Insulin Calculator</title>

    <jsp:useBean id="mealtimeInsulinDose" class="types.MealtimeInsulinDose" scope="application"/>
    <jsp:setProperty name="mealtimeInsulinDose" property="*"/>

    <title>Mealtime Insuline Dose - Standard Insulin Sensitivity (Results)</title>
</head>
<body>


<div class="results_title">
    <p>Number of insulin units:</p>
</div>
<div id="result">
    <p><%= mealtimeInsulinDose.getResult()%></p>
</div>
<div id="buttons-box">
    <div id="buttons">
        <button id="back-button"><a href="index.html">Back to initial menu</a></button>
        <button id="details-button" onClick="myFunction()">See details</button>
    </div>
</div>
<div id="panel">
    <ul>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
    </ul>
</div>

</div>

</body>
</html>
