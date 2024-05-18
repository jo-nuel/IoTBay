<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Random"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List" %>
<%@page import="uts.isd.model.*"%>
<%@page import="uts.isd.model.dao.*"%>
<%@page import="java.sql.Connection"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href = "css/styles.css">
        <link rel="stylesheet" href = "css/homepage.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>Supplier Management</title>
    </head>
    <body>
        <h1>Supplier Management</h1>
        <button type="button" onclick="location.href='login.jsp';">Exit</button>
        <button type="button" onclick="location.href='addCustomer.jsp';">Add a New Supplier</button>
        <button type="button" onclick="location.href='changeCustomers.jsp';">Update an Existing Supplier</button>
        <form>
            <h2>Search Suppliers</h2>

            <label for="searchName">Supplier Name:</label>
            <input type="text" name="searchName" id="searchName">

            <button type="submit">submit</button>
        </form>

        <% 
            
                SupplierDAO supplierDAO = (SupplierDAO) session.getAttribute("supplierDAO");
                List<Supplier> suppliers = supplierDAO.getAllSuppliers();
                session.setAttribute("suppliers", suppliers);
                String searchName = request.getParameter("searchName");
                session.setAttribute("searchName", searchName);
        %>

        <table>
            <tr>
                <th>Supplier ID</th>
                <th>Supplier Name</th>
                <th>Email</th>
                <th>Phone Number</th>
                <th>Record Active</th>
            </tr>
            <% for (Supplier supplier : suppliers) { %>
                <tr>
                    <td><%=supplier.getsupplierID()%></td>
                    <td><%=supplier.getsupplierName()%></td>
                    <td><%=supplier.getemailAddress()%></td>
                    <td><%=supplier.getphoneNum()%></td>
                    <td><%=supplier.isrecordActive()%></td>
                </tr>
            <% } %>
        </table>
    </body>
</html>
