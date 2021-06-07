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
<table>
    <tr>
        <th>Name</th>
        <th>Description</th>
        <th>Price</th>
    </tr>

    <c:forEach var="product" items="${products}">
        <tr>
            <td>${product.name}></td>
            <td>${product.description}></td>
            <td>${product.price}></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
