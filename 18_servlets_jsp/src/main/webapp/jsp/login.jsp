<%--
  Created by IntelliJ IDEA.
  User: mah
  Date: 26.09.2022
  Time: 14:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form action="/login" method="post" >
    <label>Login</label>
    <input type="text" name="login" required="required" > <br/>
    <label>Password</label>
    <input type="password" name="password" required="required" > <br/>
    <button type="submit" name="submit" value="submit">Enter</button>
</form>
</body>
</html>
