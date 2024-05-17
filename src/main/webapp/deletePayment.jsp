<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="uts.isd.model.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/styles.css" type="text/css">
    <title>Delete Payment</title> 
</head>
<body class="loginbody">

    <h1>Delete Payment</h1>
    <form class="login-form" method="POST" action="/DeletePaymentServlet">
        <label for="paymentID">Payment ID:</label>
        <input type="text" name="paymentID" id="paymentID" required><br>

        <button type="submit">Delete Payment</button>
    </form>
    <button type="button" onclick="location.href='viewPayment.jsp';">Cancel</button>
</body>
</html>
