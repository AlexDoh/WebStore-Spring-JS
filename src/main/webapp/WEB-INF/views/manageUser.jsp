<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit user</title>
</head>
<body>
<h2>Enter new values for ${user.name} :</h2>
<spring:form modelAttribute="user" method="post" id="update" action="/adminconsole/manageuser"
      style="display:inline-block;margin-right:10px">
    <spring:hidden name="userName" value="${user.name}" path="name"/>
    <ul style="list-style: none">
        <li style="position: relative;bottom: -10px">User password:
            <spring:input name="" autocomplete="false" title="User password" type="password" path="password"/>
        </li>
        <li style="position: relative;bottom: -15px">User email:
            <spring:input title="User email" type="email" path="email"/>
        </li>
        <li style="position: relative;bottom: -20px">User has next roles:
            <c:forEach var="r" items="${user.roles}">
                <c:out value="${r}"/>
            </c:forEach>
        </li>
        <li style="position: relative;bottom: -25px">
            Set new Roles:
            <spring:select multiple="true" title="Add roles" name="roles" form="update" path="roles">
                <c:forEach items="${allRoles}" var="r">
                        <spring:option value="${r}">${r}</spring:option>
                </c:forEach>
            </spring:select>
        </li>
    </ul>
    <input type="submit" name="Submit" value="Submit" style="position: relative;bottom: -40px"/>
</spring:form>
</body>
</html>