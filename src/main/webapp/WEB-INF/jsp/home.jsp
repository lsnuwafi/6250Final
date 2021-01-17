<%--
  Created by IntelliJ IDEA.
  User: 18572
  Date: 4/10/2020
  Time: 6:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="user" value="${pageContext.request.userPrincipal}"/>
<html>
<head>
    <title>Home</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <nav class="navbar navbar-inverse">
        <div class="links">
            <a href="${contextPath}/">Home</a>
            <a href="${contextPath}/company/list">Startups</a>
        </div>
        <div class="messages">
            <a href="${contextPath}/user/login">Log in</a>
        </div>
    </nav>
</head>
<body>
<div class="container">
    <c:if test="${user.name == null}">
        not login yet please login<br/>

    </c:if>
    <c:if test="${user.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <h2>Welcome ${user.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a></h2>

    </c:if>
    <security:authorize access="hasRole('ROLE_USER')">

        <h3>user info:</h3>
        <h4>First name: <security:authentication property="principal.first_name"/></h4>
        <h4>Last  name: <security:authentication property="principal.last_name"/></h4>
        <div id="nameform" style="display:none">
        <form:form method="POST" action="${contextPath}/user/setInfo" modelAttribute="userForm">
            <spring:bind path="first_name">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="first_name" class="form-control" placeholder="First name"
                                autofocus="true"></form:input>
                    <form:errors path="first_name"></form:errors>
                </div>
            </spring:bind>
            <spring:bind path="last_name">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="last_name" class="form-control" placeholder="Last name"
                                autofocus="true"></form:input>
                    <form:errors path="last_name"></form:errors>
                </div>
            </spring:bind>

<%--            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>
            <button type="submit" class="btn btn-primary">set info</button>
        </form:form>
        </div>
        <br/>
        <button id="but" name="aa" class="btn btn-primary">change name</button>
    </security:authorize>
    <security:authorize access="hasRole('ROLE_MANAGER') && !hasRole('ROLE_ADMIN')">

        <h3 class="text-center"><a href="${contextPath}/company/startupregistration">Add your company</a></h3>
        <br/>
        <h3 class="text-center"><a href="${contextPath}/company/workstation">book workstation</a></h3>
    </security:authorize>
    <security:authorize access="hasRole('ROLE_ADMIN')">

        <h3 class="text-center"><a href="${contextPath}/user/registManager">Manage Manager</a></h3>
        <br/>
        <h3 class="text-center"><a href="${contextPath}/user/deleteUser">Delete User</a></h3>

    </security:authorize>
</div>
</body>
<script>
    $("#but").click(function(){
        if($("#nameform").css("display")=="none"){
            $("#nameform").show();
        }else{
            $("#nameform").hide();
        }
    });

</script>
</html>
