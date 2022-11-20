<%--
  Created by IntelliJ IDEA.
  User: mah
  Date: 26.09.2022
  Time: 12:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>Adding new user</title>
</head>
<body>
<h1>Add a new User</h1>
<form method="post" action="/admin/add">
    <label>Login:<input type="text" name="login" required="required"></label><br>
    <label>Password:<input type="text" name="password" required="required"></label><br>
    <label>Email:<input type="email" name="email" required="required"></label><br>
    <label>FirstName:<input type="text" name="firstName" required="required"></label><br>
    <label>LastName:<input type="text" name="lastName" required="required"></label><br>
    <label>Birthday:<input type="date" name="birthday" required="required"></label><br>
    <label for="role">Role</label>
    <select name="role" id="role">
        <c:forEach var="role" items="${requestScope.roles}">
        <option value="${role.id}">${role.name}</option>
        </c:forEach>
    </select><br/>
    <input type="submit" value="Create" ><br>
</form>

</body>
</html>