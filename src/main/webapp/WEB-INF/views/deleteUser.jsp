<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete User</title>
</head>
<body>
<h2>Are you sure want to delete user ${userName} ?</h2>
<form method="post" id="delete" action="<c:url value="/adminconsole/deleteuser"/>"
      style="display:inline-block;margin-right:10px">
    <button name="userName" title="OK" form="delete" value="<c:out value="${userName}"/>" style="margin-right:10px">OK
    </button>
    <button name="userName" title="Cancel" form="delete" formmethod="get"
            formaction="<c:url value="/adminconsole/usermanagement"/>">Cancel
    </button>
</form>
</body>
</html>
