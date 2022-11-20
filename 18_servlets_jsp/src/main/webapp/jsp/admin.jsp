<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <style>
        #customers {
            font-family: Arial, Helvetica, sans-serif;
            border-collapse: collapse;
            width: 70%;
            margin: auto;
        }

        #customers td, #customers th {
            border: 1px solid #ddd;
            padding: 8px;
        }

        #customers tr:nth-child(even){background-color: #f2f2f2;}

        #customers tr:hover {background-color: #ddd;}

        #customers th {
            padding-top: 12px;
            padding-bottom: 12px;
            text-align: left;
            background-color: #04AA6D;
            color: white;
        }
        .button {
            font: bold 11px Arial;
            text-decoration: none;
            background-color: #EEEEEE;
            color: #333333;
            padding: 2px 6px 2px 6px;
            border-top: 1px solid #CCCCCC;
            border-right: 1px solid #333333;
            border-bottom: 1px solid #333333;
            border-left: 1px solid #CCCCCC;
        }
    </style>
    <meta charset="UTF-8">
    <title>Users</title>
</head>
<body>
<h1>This is the users</h1>
<table id="customers">
    <tr>
        <th>Login</th>
        <th>FirstName</th>
        <th>LastName</th>
        <th>Date_of_birthday</th>
        <th>Role</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="user" items="${requestScope.users}">
        <tr>
            <td><p> <c:out value="${user.login}"/> </p></td>
            <td><p> <c:out value="${user.firstName}"/> </p></td>
            <td><p> <c:out value="${user.lastName}"/> </p></td>
            <td><p> <c:out value="${user.birthday}"/> </p></td>
            <td><p> <c:out value="${user.role.name}"/> </p></td>
            <td>
                <form action="/admin/delete" method="post" onclick="return confirm('Are you sure?')">
                    <input type="hidden" name="id" required="required" value="${user.id}">
                    <button type="submit" name="delete" value="delete">Delete</button>
                </form>
                <script>
                    var el_up = document.getElementById("GFG_UP");
                    el_up.innerHTML =
                        "Click on the LINK for further confirmation.";
                </script>
                <form action="/admin/update" >
                    <input type="hidden" name="id" required="required" value="${user.id}">
                    <button type="submit" name="update" value="update">Update</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<br/>
<a class="button" href="/admin/add">Add a new User </a><br/>
<br/>
<br/>
<a class="button" href="/logout">Logout </a>

</body>
</html>