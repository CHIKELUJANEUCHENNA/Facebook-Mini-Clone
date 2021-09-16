<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Welcome</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<%--<h1><%= "Hello World!" %>--%>
<%--</h1>--%>
<%--<a href="login.jsp">Login</a>--%>
<div class="container">
    <div></div>
    <div class="row">
        <div class="col-md-6">
            <div class="card">
                <form action="${pageContext.request.contextPath}/login" class="box" method="post">
                    <h1>Login</h1>
                    <p class="text-muted"> Please enter your login and password!</p>
                    <input type="text" name="username" placeholder="Username" required/>
                    <input type="password" name="password" placeholder="Password" required/>
                    <a class="forgot text-muted" href="#">Forgot password?</a>
                    <input type="submit" name="" value="Login">
                    <button><a href="registration.jsp">New Users</a></button>
                </form>
            </div>
        </div>
    </div>
    <div></div>
</div>
</body>
</html>