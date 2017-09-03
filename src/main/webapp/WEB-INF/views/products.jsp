<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Products</title>
</head>
<body>
<form action="<c:url value="/product"/>">
    Find product by id:
    <input title="Find product by id:" type="text" name="id" style="position: relative;top: -5px"><br>
    <input title="Category" type="hidden" name="category" value="${category.name}" style="position: relative;top: -10px"><br>
    <input type="submit" value="Submit" style="position: relative;bottom: -15px">
</form>
<h2>Category - ${category.name}</h2>
<h2>Products: </h2>
<c:forEach var="p" items="${products}">
    <h1><c:out value="${p.name} (${p.description})"/></h1>
</c:forEach>
<a href="${pageContext.request.contextPath}/categories" style="position: relative;bottom: -25px">Back</a><br>
</body>
</html>
