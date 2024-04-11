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
        <title>Logout</title> 
    </head>
    <body>
        <% session.invalidate(); %>
        <h1>IoTBay</h1>

        <h1>Your account has been reset. Re-register to access site.</h1>

        <button><a href="register.jsp">Back to registration.</a></button>
    </body>
</html>