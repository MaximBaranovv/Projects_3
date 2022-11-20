<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <style>
        .red-star {
            color: red;
        }
    </style>
    <meta charset="UTF-8">
    <title>Updating user</title>
</head>
<body>
<form method="post" action="/admin/update">
    <label>New Login: <input type="text" name="login" required="required" readonly="readonly" value="${requestScope.user.login}"/> <span class="red-star">&#x2605; </span> - this field cannot be changed while updating </label><br>
    <label>New Password: <input type="text" name="password" required="required"  value="${requestScope.user.password}" /> </label><br>
    <label>New Email: <input type="text" name="email" required="required"  value="${requestScope.user.email}" /> </label><br>
    <label>New FirstName: <input type="text" name="firstName" required="required" value="${requestScope.user.firstName}" /> </label><br>
    <label>New LastName: <input type="text" name="lastName" required="required"  value="${requestScope.user.lastName}" /> </label><br>
    <label>New BirthDay: <input type="date" name="birthday" required="required"  value="${requestScope.user.birthday}" /> </label><br>
    <label>New Role: </label>
    <select name="role">
        <c:forEach var="role" items="${requestScope.roles}">
            <option value="${role.id}">${role.name}</option>
        </c:forEach>
    </select>
    <br/>
    <input type="hidden" name="id" required="required"  value="${requestScope.user.id}" /> <br/>
    <input type="submit" value="Ok" name="Ok"><br>
</form>
</body>
</html>