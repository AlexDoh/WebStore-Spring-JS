<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Successful</title>
</head>
<body>
<h1>Action successfully performed!<br>
    <%
        if (request.getParameter("type") != null) {
            request.getParameter("type");
        } else {
    %>
    ${requestScope.type}
    <%
        }
    %>
    ${requestScope.name} processed</h1>
<a href="${requestScope.get("javax.servlet.forward.request_uri")}/"
   style="position: relative;bottom: -20px;">Back to previous page</a><br>
</body>
</html>
