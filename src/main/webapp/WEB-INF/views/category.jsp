<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CategorySearch</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/products?categoryId=${category.id}"
   style="font-size: 20px"><c:out
        value="${category.name}"/></a><br>
<h3 style="position: relative;bottom: -20px;">This category can be found by link :</h3><br>
<a href="${pageContext.request.contextPath}/products?categoryId=${category.id}"><c:out
        value="${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}
        /products?categoryId=${category.id}"/></a><br>
<a href="${pageContext.request.contextPath}/categories" style="position: relative;bottom: -40px;">Back</a><br>
</body>
</html>
