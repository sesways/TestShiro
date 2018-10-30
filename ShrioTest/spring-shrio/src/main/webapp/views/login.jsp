<%--
  Created by IntelliJ IDEA.
  User: Leiming
  Date: 2018/10/30
  Time: 22:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
</head>
<body>
    <h3>Login Page</h3><br>
    <form action="<%= request.getContextPath() %>/shrioController/login">
        UserName:<input type="text" name="username" value="" /><br><br>
        PassWord:<input type="password" name="Password" value="" /><br><br>
        <input type="submit" value="Submit" />
    </form>
</body>
</html>
