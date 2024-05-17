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
    <title>Change Customers</title> 
</head>
<body class="loginbody">
    <%
        Customer selectedCustomer = (Customer) session.getAttribute("selectedCustomer");
    %>

    <h1 style="color: white;">Updating Customer</h1>
    <form class="login-form" method="POST" action="/UpdateCustomerServlet">

        <label for="customerID">Customer ID:</label>
        <input type="number" name="customerID" id="customerID" readonly value="<%=Integer.valueOf(selectedCustomer.getUserID())%>">

        <label for="customerName">Name:</label>
        <input type="text" name="customerName" id="customerName" value="<%=selectedCustomer.getUserName()%>">

        <label for="customerEmail">Email:</label>
        <input type="text" name="customerEmail" id="customerEmail" value="<%=selectedCustomer.getUserEmail()%>">

        <label for="customerPassword">Password:</label>
        <input type="text" name="customerPassword" id="customerPassword" value="<%=selectedCustomer.getPassword()%>">

        <label for="customerUserType">User Type:</label>
        <input type="text" name="customerUserType" id="customerUserType" readonly value="<%=selectedCustomer.getUserType()%>">

        <br>

        <% if (selectedCustomer.getCustomerType().equals("Individual")) { %>

            <input type="radio" id="Individual" name="customerType" value="Individual" checked>
            <label for="Individual">Individual</label><br>

            <input type="radio" id="Company" name="customerType" value="Company">
            <label for="Company">Company</label><br>

        <% } else { %>

            <input type="radio" id="Individual" name="customerType" value="Individual">
            <label for="Individual">Individual</label><br>

            <input type="radio" id="Company" name="customerType" value="Company" checked>
            <label for="Company">Company</label><br>

        <% } %>

        <label for="customerAddress">Shipping Address:</label>
        <input type="text" name="customerAddress" id="customerAddress" value="<%=selectedCustomer.getShippingAddress()%>">

        <br>
            
        <% if (selectedCustomer.activeString().equals("True")) { %>

            <input type="radio" id="Active" name="accountActive" value="Active" checked>
            <label for="Active">Active</label><br>

            <input type="radio" id="Inactive" name="accountActive" value="Inactive">
            <label for="Inactive">Inactive</label><br>

        <% } else { %>

            <input type="radio" id="Active" name="accountActive" value="Active">
            <label for="Active">Active</label><br>

            <input type="radio" id="Inactive" name="accountActive" value="Inactive" checked>
            <label for="Inactive">Inactive</label><br>

        <% } %>

            <button type="submit">Save Changes</button>
    </form>
    <form method="GET" action="/CustomerManagementServlet">
        <button type="submit">Cancel</button>
    </form>
</body>
</html>