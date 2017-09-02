<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User registration</title>
</head>
<body>
<h1>Please fill the registration form :</h1>
<spring:form modelAttribute="user" method="post" action="/registration">
    <ul style="list-style: none">
        <li>User name:
            <spring:input title="User name" type="text" path="name"/></li>
        <li>User password:
            <spring:input title="User password" type="password" path="password"/></li>
        <li>User email:
            <spring:input title="User email" type="email" path="email"/></li>
    </ul>
    <br>
    <input type="submit" value="Submit" style="position: relative;bottom: 20px"/>
</spring:form>
<a href="${pageContext.request.contextPath}/login" style="position: relative;bottom: -60px;">Back to Login
    Page</a><br>
</body>
</html>
