<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Dashboard Template for Bootstrap</title>

    <link href="<c:url value="/resources/bootstrap/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/bootstrap/css/dashboard.css"/>" rel="stylesheet">
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
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/profile"/>">Profile</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/login"/>">Login</a>
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

<div class="container-fluid">
    <div class="row">
        <nav class="col-sm-3 col-md-2 d-none d-sm-block bg-light sidebar">
            <ul class="nav nav-pills flex-column">
                <li class="nav-item">
                    <a class="nav-link active" href="#">All categories <span class="sr-only">(current)</span></a>
                </li>
                <c:forEach var="c" items="${categories}">
                    <li class="nav-item">
                        <a class="nav-link"
                           href="${pageContext.request.contextPath}/category?id=${c.id}"
                           style="font-size: 20px">${c.name}</a>
                    </li>
                </c:forEach>
            </ul>
        </nav>

        <main class="col-sm-9 ml-sm-auto col-md-10 pt-3" role="main">
            <h1>All categories</h1>

            <section class="row text-center placeholders">
                <c:forEach var="c" items="${categories}">
                    <div class="col-6 col-sm-3 placeholder">
                        <a class="nav-link" href="${pageContext.request.contextPath}/category?id=${c.id}"
                           style="font-size: 20px">
                            <img src="data:image/gif;base64,R0lGODlhAQABAIABAAJ12AAAACwAAAAAAQABAAACAkQBADs="
                                 width="200"
                                 height="200" class="img-fluid rounded-circle" alt="Generic placeholder thumbnail"
                                 style="border:0">
                            <h4>${c.name}</h4>
                        </a>
                        <div class="text-muted">Detailed information</div>
                    </div>
                </c:forEach>
            </section>

            <h2>Products description</h2>
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>Category id</th>
                        <th>Category name</th>
                        <th>Product id</th>
                        <th>Product name</th>
                        <th>Product description</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="c" items="${categories}">
                        <c:forEach var="p" items="${c.productList}">
                            <tr>
                                <td>${c.id}</td>
                                <td>${c.name}</td>
                                <td>${p.id}</td>
                                <td>${p.name}</td>
                                <td>${p.description}</td>
                            </tr>
                        </c:forEach>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </main>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script>window.jQuery || document.write('<script src="../../../../assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="/resources/bootstrap/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.5/umd/popper.min.js"></script>
</body>
</html>
