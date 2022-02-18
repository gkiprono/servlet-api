<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %> </h1>
<br/>
<a href="/Login">SignUp</a>
<form action="/HelloServlet" method="post">
    <label>USERNAME</label> <input type="text" name="userName" placeholder="username">
    <br>
    <label>PASSWORD </label> <input type="password" name="password">
    <br>
    <input type="submit" value="OK" >
</form>
</body>
</html>