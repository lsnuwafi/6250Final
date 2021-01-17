<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 18572
  Date: 4/19/2020
  Time: 10:46 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Delete User</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <form method="POST" action="${contextPath}/user/deleteUser">
        <table class="table">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">user Name</th>
                <th scope="col">First name</th>
                <th scope="col">Last name</th>
                <th scope="col">delete</th>>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="listValue" items="${userlist}" varStatus="loop">
                <tr>
                    <th scope="row">${loop.index + 1}</th>
                    <td>${listValue.username}</td>
                    <td>${listValue.first_name}</td>
                    <td>${listValue.last_name}</td>
                    <td><input type="checkbox" class="custom-control-input" name="deleteUser" value="${listValue.username}"></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-primary">permition</button>
    </form>
</div>
</body>
</html>
