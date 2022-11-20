<%--
  Created by IntelliJ IDEA.
  User: mah
  Date: 26.09.2022
  Time: 12:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <style>
        .loading:after {
            overflow: hidden;
            display: inline-block;
            vertical-align: bottom;
            -webkit-animation: ellipsis steps(4, end) 900ms infinite;
            animation: ellipsis steps(4, end) 900ms infinite;
            content: "\2026";
            /* ascii code for the ellipsis character */
            width: 0px;
        }

        @keyframes ellipsis {
            to {
                width: 40px;
            }
        }

        @-webkit-keyframes ellipsis {
            to {
                width: 40px;
            }
        }
    </style>
    <title>Ok!</title>
</head>
<body>
<h1> <p class="loading" > <c:out value="${requestScope.user}"/> </p> </h1>
<meta http-equiv="refresh" content="5;url=http://localhost:8080/admin/get"/>
</body>
</html>
