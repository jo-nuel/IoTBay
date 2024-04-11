<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Random"%>
<%@page import="uts.isd.model.*"%>
<%@page import="css.*"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title> 
    <link rel="stylesheet" href="css/styles.css" type="text/css">
</head>
<body>
    <h1>IoTBay</h1>
    <form class="login-form">
        <label for="Username">Username:</label>
        <input type="text" name="username" id="Username">
        
        <label for="Password">Password:</label>
        <input type="password" name="password" id="Password">
        
        <button type="submit" onclick="location.href='home.jsp';">Login</button>
        <button type="button" onclick="location.href='register.jsp';">Register</button> 
    </form>
</body>
</html>