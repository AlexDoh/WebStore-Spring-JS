<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ProductSearch</title>
</head>
<body>
<h1 style="display: inline-block">${product.name}</h1>
<h2 style="display: inline-block">(${product.description})</h2><br>
<h3 style="position: relative;bottom: -20px;">This product can be found by link :</h3><br>
<a href="${pageContext.request.contextPath}/product?id=${product.id}&category=${category}"><c:out
        value="${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}
        /product?id=${product.id}&category=${category}"/></a><br>
<a href="${pageContext.request.contextPath}/products?categoryId=${product.category.id}"
   style="position: relative;bottom: -40px;">To product's category</a><br>
</body>
</html>
