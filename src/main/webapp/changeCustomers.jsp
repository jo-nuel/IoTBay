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
        ArrayList<Customer> customers = (ArrayList<Customer>) session.getAttribute("customers");

        String selectedID = request.getParameter("customerID");
        String submitted = request.getParameter("submitted");
        Customer selectedCustomer = null;

        if (selectedID != null){
            for (Customer customer : customers) {
                if (customer.getUserID().equals(selectedID)) {
                    selectedCustomer = customer;
                    session.setAttribute("selectedCustomer", selectedCustomer);
                    s
                }
            }
        }
    %>

    <h1 style="color: white;">Changing Customer List</h1>
    <form class="login-form">
        <% if (selectedCustomer != null) { %>
            <label for="customerID">Customer ID:</label>
            <input type="number" name="customerID" id="customerID" readonly value="<%=Integer.valueOf(selectedCustomer.getUserID())%>">

            <label for="customerName">Name:</label>
            <input type="text" name="customerName" id="customerName" readonly value="<%=selectedCustomer.getUserName()%>">

            <label for="customerEmail">Email:</label>
            <input type="text" name="customerEmail" id="customerEmail" readonly value="<%=selectedCustomer.getUserEmail()%>">

            <label for="customerPassword">Password:</label>
            <input type="text" name="customerPassword" id="customerPassword" readonly value="<%=selectedCustomer.getPassword()%>">

            <label for="customerUserType">User Type:</label>
            <input type="text" name="customerUserType" id="customerUserType" readonly value="<%=selectedCustomer.getUserType()%>">

            <label for="customerType">Customer Type:</label>
            <input type="text" name="customerType" id="customerType" readonly value="<%=selectedCustomer.getCustomerType()%>">

            <label for="customerAddress">Shipping Address:</label>
            <input type="text" name="customerAddress" id="customerAddress" readonly value="<%=selectedCustomer.getShippingAddress()%>">

            <label for="customerActive">Account Active:</label>
            <input type="text" name="customerActive" id="customerActive" readonly value="<%=selectedCustomer.activeString()%>">

            <p>What would you like to do with this customer?</p>

            <button type="button" onclick="location.href='updateCustomer.jsp';">Update Customer</button>

            <form method="POST" action="/DeleteCustomerServlet">
                <button type="submit">Delete Customer</button>
            </form>

        <% } else { %>
            <p>Enter the ID of the customer you want to change.</p>

            <% if (submitted != null) { %>
                <p style="color: red;">Please enter a valid user ID.</p>
            <% } %>

            <label for="customerID">Customer ID:</label>
            <input type="number" name="customerID" id="customerID" required>

            <input type="hidden" name="submitted" id="submitted" value="true"/>

            <button type="submit">Confirm</button>
        <% } %>

        <form method="POST" action="/CustomerManagementServlet">
            <button type="submit">Cancel</button>
        </form>
    </form>
</body>
</html>