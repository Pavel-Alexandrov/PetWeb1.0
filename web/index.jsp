<%--
  Created by IntelliJ IDEA.
  User: DNS
  Date: 08.10.2019
  Time: 5:14
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title></title>
</head>
<body>
<h1>Текущие пользователи:</h1>
<c:set var="userService" value="${UserService}"/>
<c:set var="users" value="${}"/>
<table>
  <c:forEach var="user" items="${users}">
    <tr>
      <c:forEach var="userParam" items="${user}">
        <td><c:out value="${userParam}"/>></td>
      </c:forEach>
    </tr>
  </c:forEach>
</table>
</body>
</html>
