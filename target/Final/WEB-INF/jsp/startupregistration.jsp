<%--
  Created by IntelliJ IDEA.
  User: 18572
  Date: 4/12/2020
  Time: 12:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="user" value="${pageContext.request.userPrincipal}"/>
<html>
<head>
    <title>Join Us</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
</head>
<body>
<c:if test="${user.name != null}">
    <form id="logoutForm" method="POST" action="${contextPath}/logout">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>

    <h2>Welcome ${user.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a></h2>

</c:if>
<form:form method="POST" modelAttribute="companyForm" class="form-signin">
    <h2 class="form-signin-heading">Join us</h2>
    <spring:bind path="name">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input type="text" path="name" class="form-control" placeholder="companyname"
                        autofocus="true"></form:input>
            <form:errors path="name"></form:errors>
        </div>
    </spring:bind>

    <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
</form:form>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</html>
