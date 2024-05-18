<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Random"%>
<%@page import="uts.isd.model.*"%>
<%@page import="css.*"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/styles.css" type="text/css">
    <title>Admin Login</title> 
</head>
<body class="loginbody">
    <form action="<%=request.getContextPath()%>/adminLogin" method="post">
        <%
            String userName = request.getParameter("adminUserName");
            String password = request.getParameter("adminPassword");
        %>

        <% String username = (String) session.getAttribute("userName");%>

            <h1 style="color: white;">IoTBay Admin Login</h1>  
            <form class="login-form" action="<%=request.getContextPath()%>/adminLogin" method="post">
                <label for="adminUserName">Username:</label>
                <input type="text" name="adminUserName" id="adminUserName" required>
                        
                <label for="adminPassword">Password:</label>
                <input type="password" name="adminPassword" id="adminPassword" required>
                <button type="submit">Login</button>
            </form>
    </form>
</body>
</html>