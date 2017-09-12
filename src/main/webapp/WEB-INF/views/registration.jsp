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

    <title>Registration</title>

    <link href="<c:url value="/resources/bootstrap/css/bootstrap.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/bootstrap/css/signin.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/bootstrap/css/bootstrap-imageupload.css"/>" rel="stylesheet">

</head>
<body>

<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
    <a class="navbar-brand" href="<c:url value="/"/>">Home</a>
    <button class="navbar-toggler d-lg-none" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault"
            aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarsExampleDefault">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="<c:url value="/profile"/>">Profile <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/profile/settings"/>">Settings</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/categories"/>">Categories</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/help"/>">Help</a>
            </li>
        </ul>
        <form class="form-inline mt-2 mt-md-0 mr-5" action="<c:url value="/category"/>">
            <input class="form-control mr-sm-2" name="id" title="Category id" type="text" placeholder="Put category id"
                   aria-label="Put category id">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search category</button>
        </form>
        <form class="form-inline mt-2 mt-md-0 ml-5" action="<c:url value="/product"/>">
            <input class="form-control mr-sm-2" name="id" title="id" type="text" placeholder="Put product id"
                   aria-label="Put product id">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search product</button>
        </form>
    </div>
</nav>

<div class="container">
    <spring:form class="form-signin" modelAttribute="user" method="post" action="/registration"
                 enctype="multipart/form-data">
        <h2 class="form-signin-heading">Please fill the registration form</h2>
        <spring:label path="name" class="sr-only">Name</spring:label>
        <spring:input type="text" path="name" id="username" class="form-control" placeholder="Username"/>
        <spring:label path="password" class="sr-only">Password</spring:label>
        <spring:input type="password" path="password" class="form-control" placeholder="Password"/>
        <spring:label path="email" class="sr-only">Email</spring:label>
        <spring:input type="email" path="email" class="form-control" placeholder="Email"/>
        <div class="imageupload panel panel-default">
            <div class="panel-heading clearfix">
                <h3 class="panel-title pull-left">Upload Image</h3>
                <div class="btn-group pull-right">
                    <button type="button" class="btn btn-default active">File</button>
                    <button type="button" class="btn btn-default">URL</button>
                </div>
            </div>
            <div class="file-tab panel-body">
                <label class="btn btn-default btn-file">
                    <span>Browse</span>
                    <input type="file" name="image">
                </label>
                <button type="button" class="btn btn-default">Remove</button>
            </div>
            <div class="url-tab panel-body">
                <div class="input-group">
                    <input type="url" class="form-control hasclear" placeholder="Image URL">
                    <button type="button" class="btn btn-default">Submit</button>
                </div>
                <input type="hidden" name="image" multiple>
                <button type="button" class="btn btn-default">Remove</button>
            </div>
            <button type="button" id="imageupload-disable" class="btn btn-danger">Disable</button>
            <button type="button" id="imageupload-enable" class="btn btn-success">Enable</button>
            <button type="button" id="imageupload-reset" class="btn btn-primary">Reset</button>
        </div>
        <spring:button class="btn btn-lg btn-primary btn-block" type="submit">Register</spring:button>
    </spring:form>
</div>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="/resources/bootstrap/js/bootstrap.min.js"></script>
<script src="/resources/bootstrap/js/bootstrap-imageupload.js"></script>
</body>
</html>
