<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Management</title>
</head>
<body>
<h1>List of users</h1>
<c:forEach var="u" items="${users}">
    <p>
    <h4 style="display:inline-block;margin-right:5px">Account name: </h4>
    <h3 style="display:inline-block;margin-right:5px"><i><c:out value="${u.name}"/></i></h3>
    <spring:form method="get" modelAttribute="user" id="edit" action="/adminconsole/manageuser"
                 style="display:inline-block;margin-right:10px">
        <spring:hidden value="${u.name}" path="name"/>
        <spring:hidden value="${u.password}" path="password"/>
        <spring:button title="Edit user" form="edit">Edit</spring:button>
    </spring:form>
    <form id="delete" action="<c:url value="/adminconsole/deleteuser"/>"
          style="display:inline-block;margin-right:10px">
        <button name="userName" title="Delete user" form="delete" value="<c:out value="${u.name}"/>">Delete</button>
    </form>
    </p>
</c:forEach>
</body>
</html>
