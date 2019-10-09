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
        <td>Эксклюзивные возможности</td>
    </tr>
  <c:forEach var="user" items="${userList}">
    <tr>
      <td>
          <c:out value="${user.id}"/>
      </td>
        <td>
            <c:out value="${user.login}"/>
        </td>
        <td>
            <c:out value="${user.name}"/>
        </td>
        <td>
            <c:out value="${user.password}"/>
        </td>
        <td>
            <form action="/delete" method="post">
                <button name="id" value="${user.id}">Удалить пользователя</button>
            </form>
            <form action="/update" method="post">
                <p><input type="submit" value="Изменить пользователя:"></p>
                <p>Name: <input type="text" name="name"> Password: <input type="text" name="password"></p>
                <input type="hidden" name="login" value="${user.login}">
            </form>
        </td>
    </tr>
  </c:forEach>
</table>
<form action="/add" method="post">
    <p>Login:</p>
    <p><input type="text" name="login"></p>
    <p>Name:</p>
    <p><input type="text" name="name"></p>
    <p>Password:</p>
    <p><input type="text" name="password"></p>
    <p><input type="submit"></p>
</form>
</body>
</html>
