<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Admin console</title>

    <link href="<c:url value="/resources/bootstrap/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/bootstrap/css/adminConsole.css"/>" rel="stylesheet">

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
<main class="container pt-5">
    <div class="mb-5 user-management-button">
        <form action="/adminconsole/usermanagement">
            <button class="btn btn-primary">User management</button>
        </form>
    </div>
    <div class="card mb-5">
        <div class="card-header">Categories</div>
        <div class="card-block p-0">
            <table class="table table-bordered table-sm m-0">
                <thead class="">
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="c" items="${categories}">
                    <tr>
                        <td>${c.id}</td>
                        <td>${c.name}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="card-footer p-0">
            <nav aria-label="...">
                <ul class="pagination justify-content-end mt-3 mr-3">
                    <li class="page-item disabled">
                        <span class="page-link">Previous</span>
                    </li>
                    <li class="page-item"><a class="page-link" href="#">1</a></li>
                    <li class="page-item active">
                            <span class="page-link">2<span class="sr-only">(current)</span>
                            </span>
                    </li>
                    <li class="page-item"><a class="page-link" href="#">3</a></li>
                    <li class="page-item">
                        <a class="page-link" href="#">Next</a>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
    <div class="card mb-5">
        <spring:form modelAttribute="category" method="post" action="/adminconsole/managecategory" id="category">
            <div class="card-header">Category manipulation</div>
            <div class="card-block p-0">
                <table class="table table-bordered table-sm m-0">
                    <thead class="">
                    <tr>
                        <th>Choose category operation</th>
                        <th>Category name</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>
                            <select class="custom-select" title="Category action" name="action" form="category">
                                <option value="add">Create</option>
                                <option value="delete">Delete</option>
                            </select>
                        </td>
                        <td>
                            <spring:input cssClass="input-group" title="Category name" type="text" name="categoryName"
                                          path="name"/>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="card-footer p-1">
                <nav aria-label="...">
                    <ul class="pagination justify-content-center mt-3 mr-3">
                        <li class="page-item active">
                            <button form="category" class="btn btn-primary float-left">Submit</button>
                        </li>
                    </ul>
                </nav>
            </div>
        </spring:form>
    </div>
    <div class="card mb-5">
        <div class="card-header">Products</div>
        <div class="card-block p-0">
            <table class="table table-bordered table-sm m-0">
                <thead class="">
                <tr>
                    <th>Id</th>
                    <th>Product name</th>
                    <th>Category of the product</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="p" items="${products}">
                    <tr>
                        <td>${p.id}</td>
                        <td>${p.name}</td>
                        <td>${p.category.name}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="card-footer p-0">
            <nav aria-label="...">
                <ul class="pagination justify-content-end mt-3 mr-3">
                    <li class="page-item disabled">
                        <span class="page-link">Previous</span>
                    </li>
                    <li class="page-item"><a class="page-link" href="#">1</a></li>
                    <li class="page-item active">
                            <span class="page-link">2<span class="sr-only">(current)</span>
                            </span>
                    </li>
                    <li class="page-item"><a class="page-link" href="#">3</a></li>
                    <li class="page-item">
                        <a class="page-link" href="#">Next</a>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
    <div class="card mb-5">
        <spring:form modelAttribute="product" method="post" action="/adminconsole/manageproduct" id="product">
            <div class="card-header">Product manipulation</div>
            <div class="card-block p-0">
                <table class="table table-bordered table-sm m-0">
                    <thead class="">
                    <tr>
                        <th>Choose product operation</th>
                        <th>Product name</th>
                        <th>Product description</th>
                        <th>Category of the product</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>
                            <select class="custom-select" title="Product action" name="action" form="product">
                                <option value="add">Create</option>
                                <option value="update">Update</option>
                                <option value="delete">Delete</option>
                            </select>
                        </td>
                        <td>
                            <spring:input cssClass="input-group" title="Product name" type="text" name="productName"
                                          path="name"/>
                        </td>
                        <td>
                            <spring:input cssClass="input-group" title="Product description" type="text"
                                          name="productDescription"
                                          path="description"/>
                        </td>
                        <td>
                            <spring:select cssClass="custom-select" title="Product categoryName"
                                           name="productCategoryId" form="product"
                                           path="category.id">
                                <c:forEach var="c" items="${categories}">
                                    <spring:option value="${c.id}">
                                        ${c.name}
                                    </spring:option>
                                </c:forEach>
                            </spring:select>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="card-footer p-1">
                <nav aria-label="...">
                    <ul class="pagination justify-content-center mt-3 mr-3">
                        <li class="page-item active">
                            <button form="product" class="btn btn-primary float-left">Submit</button>
                        </li>
                    </ul>
                </nav>
            </div>
        </spring:form>
    </div>
</main>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="/resources/bootstrap/js/bootstrap.min.js"></script>
<script src="/resources/bootstrap/js/popper.min.js"></script>
<script src="/resources/bootstrap/js/bootstrap-imageupload.js"></script>
<script src="/resources/bootstrap/js/profile.js"></script>
</body>
</html>
