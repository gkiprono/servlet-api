<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: Kiprono
  Date: 2/18/2022
  Time: 00:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Date date = new Date();

%>
<!DOCTYPE html>
<html>
<head>
    <title>signUp JSP </title>
</head>
<body>
    <h6><%=date%></h6>
    <h2 style="text-align: center">SignUp to continue</h2>
    <h4 style="text-align: center"> Fill in the form </h4>
    <form name="form" action="SignUp" method="post" onsubmit="return validate()">
        <table style="margin: 0px auto;">
            <tr>
                <td>First Name</td>
                <td><input type="text" name="f_name" required/></td>
            </tr><tr>
                <td>Last Name</td>
                <td><input type="text" name="l_name" required/></td>
            </tr>
            <tr>
                <td>Email</td>
                <td><input type="text" name="email" required placeholder="janedoe@some.com"/></td>
            </tr>
            <tr>
                <td>Username</td>
                <td><input type="text" name="username" required/></td>
            </tr>
            <tr>
                <td>Password</td>
                <td><input type="password" name="pass" required/></td>
            </tr>
            <tr>
                <td>Confirm Password</td>
                <td><input type="password" name="pass_conf" required/></td>
            </tr>
            <tr>
                <td><%=(request.getAttribute("errMessage") == null) ? "" : request.getAttribute("errMessage")%></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Register" />
                    <input type="reset" value="Reset" />
                </td>
            </tr>
        </table>
    </form>
</body>
</html>

<script>
    function validate(){
        const email_re =
            /^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;

        const userName_re = /^[A-Za-z0-9_-]{7,16}$/;
        const password_re = /^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{6,16}$/;

        let firstName = document.forms["form"]["f_name"].value;
        let lastName = document.forms["form"]["l_name"].value;
        let email = document.forms["form"]["email"].value;
        let userName = document.forms["form"]["username"].value;
        let password = document.forms["form"]["pass"].value;
        let cnf_pass = document.forms["form"]["pass_conf"].value;

        if(firstName === null || firstName === ""){
            alert("First Name cant be blank");
            return false;
        }

        if(lastName === null || lastName === ""){
            alert("Last Name cant be blank");
            return false;
        }

        if (! email.toLowerCase().match(email_re)){
            alert("Wrong email format");
            return false;
        }

        if(! userName.toLowerCase().match(userName_re)){
            alert("Wrong username format");
            return false;
        }

        // check length
        if (password.length < 8){
            alert("Password is too short!")
            return false;
        } else {
            // check for pattern
            if (! password.match(password_re)){
                alert("wrong password format, should have at least one special symbol and number");
                return false;
            } else {
                // lastly check if they match
                if (password !== cnf_pass){
                    alert("Passwords should match");
                    return false;
                }
            }
        }
    }

</script>
