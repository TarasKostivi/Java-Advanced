<%--
  Created by IntelliJ IDEA.
  User: taras
  Date: 25.05.2021
  Time: 18:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
<form action="/registration" method="post">
    <label>
        Email
        <input type="text" name="email">
    </label> <br>
    <label>
        Password
        <input type="text" name="password">
    </label> <br>
    <label>
        Name
        <input type="text" name="name">
    </label> <br>
    <input type="submit">
</form>
</body>
</html>
