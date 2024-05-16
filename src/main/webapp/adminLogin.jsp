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
    <%
        String adminUserName = request.getParameter("adminUserName");
        String adminPassword = request.getParameter("adminPassword");

    %>

    <% if (adminUserName != null || adminPassword != null){
        if (adminUserName.equals("admin") && adminPassword.equals("secret"))  {  %>
            <h1 style="color: white;">Choose Admin Option</h1>
            <form method="POST" action="/CustomerManagementServlet">
                <button type="submit">Customer Management</button>
            </form>
            <button type="button" onclick="location.href='supplierManagement.jsp';">Supplier Management</button>
        <% } else { %>
            <h1 style="color: white;">IoTBay Admin Login</h1>  
            <form class="login-form">
                <p style="color: red;">Incorrect Username or Password.</p>
                <label for="adminUserName">Username:</label>
                <input type="text" name="adminUserName" id="adminUserName" required>
                        
                <label for="adminPassword">Password:</label>
                <input type="password" name="adminPassword" id="adminPassword" required>

                <button type="submit">Login</button>
            </form>  
        <% } %>

    <% } else { %>
        <h1 style="color: white;">IoTBay Admin Login</h1>
        <form class="login-form">
            <label for="adminUserName">Username:</label>
            <input type="text" name="adminUserName" id="adminUserName" required>
                    
            <label for="adminPassword">Password:</label>
            <input type="password" name="adminPassword" id="adminPassword" required>

            <button type="submit">Login</button>
        </form>  
    <% } %> 

</body>
</html>