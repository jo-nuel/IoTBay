<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="uts.isd.model.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/styles.css" type="text/css">
    <link rel="stylesheet" href="css/homepage.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>View Payment</title>
</head>
<body class="loginbody">
    <h1>View Payment</h1>
    <button type="button" onclick="location.href='addPayment.jsp';">Add a New Payment</button>
    <button type="button" onclick="location.href='updatePayment.jsp';">Update an Existing Payment</button>
    <button type="button" onclick="location.href='deletePayment.jsp';">Delete an Existing Payment</button>
    <form method="GET" action="/ViewPaymentServlet">
        <h2>Search Payments</h2>
        
        <label for="paymentID">Payment ID:</label>
        <input type="text" name="paymentID" id="paymentID" required><br>

        <button type="submit">View Payment</button>
    </form>

    <!-- Payment details will be displayed here -->
    <%
        String paymentID = String.valueOf(request.getParameter("paymentID"));
        Payment payment = (Payment) session.getAttribute("payment");
        
        if (payment != null && String.valueOf(payment.getpaymentID()).equals(paymentID)) {
    %>
        <h2>Payment Details</h2>
        <table>
            <tr>
                <th>Payment ID</th>
                <th>Payment Type</th>
                <th>Name on Card</th>
                <th>Card Number</th>
                <th>Card Expiry Date</th>
                <th>Card CVV</th>
            </tr>
            <tr>
                <td><%= payment.getpaymentID() %></td>
                <td><%= payment.getpaymentType() %></td>
                <td><%= payment.getcardName() %></td>
                <td><%= payment.getcardNumber() %></td>
                <td><%= payment.getcardExpiryDate() %></td>
                <td><%= payment.getcardCvv() %></td>
            </tr>
        </table>
    <% } %>

    <form method="GET" action="/ViewPaymentServlet">
        <button type="button" onclick="location.href='home.jsp';">Cancel</button>
    </form>
</body>
</html>
