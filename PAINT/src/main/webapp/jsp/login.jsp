<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
</head>
<body>
    <div align="center">
        <img src="../media/PAINT.png" alt="Logo">
        <table border="1" cellpadding="10" cellspacing="0">
            <tr>
                <td>
                    <form action="loginServlet" method="post">
                        <table>
                            <tr>
                                <td><label for="email">Email/Username:</label></td>
                                <td><input type="text" id="email" name="email" required></td>
                            </tr>
                            <tr>
                                <td><label for="password">Password:</label></td>
                                <td><input type="password" id="password" name="password" required></td>
                            </tr>
                            <tr>
                                <td colspan="2" align="center">
                                    <a href="signup_personalinformations.jsp">Sign up</a>
                                    <input type="submit" value="Sign In">
                                </td>
                            </tr>
                        </table>
                    </form>
                    <p><a href="forgotPassword.jsp">Forgot password?</a></p>
                </td>
            </tr>
        </table>
    </div>
</body>
</html>