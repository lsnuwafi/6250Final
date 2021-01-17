<%--
  Created by IntelliJ IDEA.
  User: 18572
  Date: 4/10/2020
  Time: 6:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Company</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <table class="table">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Company Name</th>
            <th scope="col">Manager</th>
        </tr>
        </thead>
        <tbody>

            <c:forEach var="listValue" items="${companylist}" varStatus="loop">
            <tr>
                <th scope="row">${loop.index + 1}</th>
                <td>${listValue.name}</td>
                <td>${listValue.manager.first_name} ${listValue.manager.last_name}</td>
            </tr>
            </c:forEach>

        </tbody>
    </table>
    <a href="${contextPath}/">return to Home</a>
</div>
<!-- /container -->

</body>
</html>
