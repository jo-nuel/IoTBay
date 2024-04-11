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
    <title>Login</title> 
</head>
<body>
    <%
        String submitted = request.getParameter("submitted");

        if (submitted != null){
            response.sendRedirect("home.jsp");
        }
    %>

    <h1>IoTBay</h1>
    <%
        User user = (User) session.getAttribute("user");
    %>
    <form class="login-form">
        <label for="Username">Username:</label>
        <input type="text" name="username" id="Username" required>
        
        <label for="Password">Password:</label>
        <input type="password" name="password" id="Password" required>

        <input type="hidden" name="submitted" id="submitted" value="true" />

        <% if(user == null) { %>
            <button type="submit" disabled>Login</button>
        <% } else { %>
            <button type="submit">Login</button>
        <% } %>  
        <button type="button" onclick="location.href='register.jsp';">Register</button> 
    </form>
</body>
</html>