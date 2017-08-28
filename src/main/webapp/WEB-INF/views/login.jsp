<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Signin Template for Bootstrap</title>

    <link href="<c:url value="resources/bootstrap/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="resources/bootstrap/signin.css"/>" rel="stylesheet">
</head>
<body>

<div class="container">
    <spring:form class="form-signin" modelAttribute="user" method="post" action="/profile">
        <h2 class="form-signin-heading">Please sign in</h2>
        <spring:label path="name" class="sr-only">Email address</spring:label>
        <spring:input type="text" path="name" id="username" class="form-control" placeholder="Username"/>
        <spring:label path="password" class="sr-only">Password</spring:label>
        <spring:input type="password" path="password" class="form-control" placeholder="Password"/>
        <div class="checkbox">
            <spring:label path="token">
                <spring:checkbox path="token" value="true"/>Remember me
                <spring:hidden path="token" value="false"/>
            </spring:label>
        </div>
        <spring:button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</spring:button>
    </spring:form>
</div>
</body>
</html>
