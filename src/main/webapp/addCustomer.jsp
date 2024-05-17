<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Random"%>
<%@page import="java.util.ArrayList"%>
<%@page import="uts.isd.model.*"%>
<%@page import="css.*"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/styles.css" type="text/css">
    <title>Adding Customer</title> 
</head>
<body class="loginbody">

    <h1>Add A New Customer</h1>
    <form class="login-form" method="POST" action="/AddCustomerServlet">
        <label for="customerName">Name:</label>
        <input type="text" name="customerName" id="customerName" required><br>

        <label for="customerEmail">Email:</label>
        <input type="text" name="customerEmail" id="customerEmail" required><br>

        <label for="customerPassword">Password:</label>
        <input type="password" name="customerPassword" id="customerPassword" required><br>

        <label for="customerUserType">User Type:</label>
        <input type="text" name="customerUserType" id="customerUserType" readonly value="Customer">

        <br>

        <input type="radio" id="Individual" name="customerType" value="Individual" required>
        <label for="Individual">Individual</label>

        <br>

        <input type="radio" id="Company" name="customerType" value="Company" required>
        <label for="Company">Company</label><br>

        <label for="customerAddress">Shipping Address:</label>
        <input type="text" name="customerAddress" id="customerAddress" required><br>

        <button type="submit">Add Customer</button>
    </form>
    <form method="GET" action="/CustomerManagementServlet">
        <button type="submit">Cancel</button>
    </form>
</body>
</html>