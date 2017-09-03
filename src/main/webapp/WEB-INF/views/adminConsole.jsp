<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin console</title>
</head>
<body>
<h1>Hello, Admin!</h1>
<a href="${pageContext.request.contextPath}/adminconsole/usermanagement"
   style="position: relative;bottom: -5px;"><h3>User management</h3></a><br>
<h3>Category manipulation:</h3><br>
<h3 style="display:inline-block;margin-right:15px">Present Categories:</h3>
<c:forEach var="c" items="${categories}">
    <h4 style="display:inline-block;margin-right:5px"><i>${c.name}</i></h4>
</c:forEach>
<spring:form modelAttribute="category" method="post" action="/adminconsole/managecategory" id="category">
    <ul style="list-style: none">
        <li>Category action:
            <select title="Category action" name="action" form="category" style="position: relative;top: -5px">
                <option value="add">Create</option>
                <option value="delete">Delete</option>
            </select>
        </li>
        <li>Category name:
            <spring:input title="Category name" type="text" name="categoryName" path="name"/></li>
    </ul>
    <br>
    <input type="submit" value="Submit" style="position: relative;bottom: 20px">
</spring:form>
<h3>Product manipulation:</h3>
<h3 style="display:inline-block;margin-right:15px">Present products:</h3>
<c:forEach var="p" items="${products}">
    <h4 style="display:inline-block;margin-right:5px"><i>${p.name}</i></h4><h5
        style="display:inline-block;margin-right:5px">(${p.category.name})</h5>
</c:forEach>
<spring:form modelAttribute="product" method="post" action="/adminconsole/manageproduct" id="product">
    <ul style="list-style: none">
        <li>Choose product operation:
            <select title="Product action" name="action" form="product" style="position: relative;top: -5px">
                <option value="add">Create</option>
                <option value="update">Update</option>
                <option value="delete">Delete</option>
            </select>
        </li>
        <li style="position: relative;bottom: -5px">Product name:
            <spring:input title="Product name" type="text" name="productName" path="name"/></li>
        <li style="position: relative;bottom: -10px">Product description:
            <spring:input title="Product description" type="text" name="productDescription" path="description"/></li>
        <li style="position: relative;bottom: -15px">Category name for product:
            <spring:select title="Product categoryName" name="productCategoryId" form="product" path="category.id">
                <c:forEach var="c" items="${categories}">
                    <spring:option value="${c.id}">
                        ${c.name}
                    </spring:option>
                </c:forEach>
            </spring:select>
        </li>
    </ul>
    <br>
    <input type="submit" value="Submit" style="position: relative;bottom: 5px">
</spring:form>
<a href="${pageContext.request.contextPath}/" style="position: relative;bottom: -35px;">Back</a><br>
</body>
</html>
