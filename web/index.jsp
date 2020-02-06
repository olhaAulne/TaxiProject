
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Users</title>

</head>
<body>

<div align="center">
    <table border="1" cellpadding="3">
        <caption>USERS</caption>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Surname</th>
        </tr>
        <c:forEach var="user" items="${userList}">
            <tr>
                <td><c:out value="${user.id}"/></td>
                <td><c:out value="${user.email}"/></td>
                <td><c:out value="${user.name}"/></td>
                <td><c:out value="${user.surname}"/></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
