<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %> </h1>
<br/>

<form action="/HelloServlet" method="post">
    <label>USERNAME</label> <input type="text" name="userName" placeholder="username" required>
    <br>
    <label>PASSWORD </label> <input type="password" name="password" required>
    <br>
    <span>
        <input type="submit" value="SignIn">
        <input type="button" value="SignUp" name="signup" onclick= "location.href= '/SignUp'"/>

    </span>

</form>
</body>
</html>

<script>

    function signup(){
        location.href = "/Login";
    }

</script>