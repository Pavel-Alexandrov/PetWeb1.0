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
    <style type="text/css">
        TABLE {
            width: 700px; /* Ширина таблицы */
            border-collapse: collapse; /* Убираем двойные линии между ячейками */
        }
        TD, TH {
            padding: 3px; /* Поля вокруг содержимого таблицы */
            border: 1px solid black; /* Параметры рамки */
            text-align: center;
        }
        TH {
            background: #b0e0e6; /* Цвет фона */
        }
    </style>
</head>
<body>
<h1>Текущие пользователи:</h1>
<table>
    <tr>
        <td>id</td>
        <td>login</td>
        <td>name</td>
        <td>password</td>
    </tr>
  <c:forEach var="user" items="${userList}">
    <tr>
      <c:forEach var="userParam" items="${user}">
        <td>
            <c:out value="${userParam}"/>
        </td>
      </c:forEach>
        <td>
            <form action="/delete" method="post">
                <button name="user" value="${user}">Удалить пользователя</button>
            </form>
            <form action="/update" method="post">
                <button name="user" value="${user}">Изменить пользователя</button>
            </form>
        </td>
    </tr>
  </c:forEach>
</table>
<form>
    <button>Добавить нового пользователя</button>
</form>
</body>
</html>
