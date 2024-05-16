<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Random"%>
<%@page import="java.util.ArrayList"%>
<%@page import="uts.isd.model.*"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href = "css/styles.css">
        <link rel="stylesheet" href = "css/homepage.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>Customer Management</title>
    </head>
    <body>
        <h1>Customer Management</h1>
        <button type="button" onclick="location.href='login.jsp';">Exit</button>
        <button type="button" onclick="location.href='addCustomer.jsp';">Add a New Customer</button>
        <button type="button" onclick="location.href='changeCustomers.jsp';">Update an Existing Customer</button>
        <form>
            <h2>Search Customers</h2>

            <label for="searchName">Customer Name:</label>
            <input type="text" name="searchName" id="searchName">

            <br>

            <input type="checkbox" id="Individual" name="Individual" value="Individual">
            <label for="Individual"> Individuals</label><br>

            <input type="checkbox" id="Company" name="Company" value="Company">
            <label for="Company"> Companies</label><br>

            <button type="submit">Search</button>
        </form>

        <% 
            ArrayList<Customer> customers = (ArrayList<Customer>) session.getAttribute("customers");
            String searchName = request.getParameter("searchName");
            String individual = request.getParameter("Individual");
            String company = request.getParameter("Company");
        %>

        <table>
            <tr>
                <th>User ID</th>
                <th>Customer Name</th>
                <th>Email</th>
                <th>Password</th>
                <th>Type</th>
                <th>Address</th>
                <th>Account Active</th>
            </tr>
            <% for (Customer customer : customers) { %>
                <tr>
                    <td><%=customer.getUserID()%></td>
                    <td><%=customer.getUserName()%></td>
                    <td><%=customer.getUserEmail()%></td>
                    <td><%=customer.getPassword()%></td>
                    <td><%=customer.getCustomerType()%></td>
                    <td><%=customer.getShippingAddress()%></td>
                    <td><%=customer.activeString()%></td>
                </tr>
            <% } %>
        </table>
    </body>
</html>
