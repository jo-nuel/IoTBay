<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="uts.isd.model.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/styles.css" type="text/css">
    <title>Adding Payment</title> 
</head>
<body class="loginbody">

    <h1>Add A New Payment</h1>
    <form class="login-form" method="POST" action="/AddPaymentServlet">
        <label for="paymentType">Payment Type:</label>
        <input type="text" name="paymentType" id="paymentType" required><br>

        <label for="cardName">Card Name:</label>
        <input type="text" name="cardName" id="cardName" required><br>

        <label for="cardNumber">Card Number:</label>
        <input type="text" name="cardNumber" id="cardNumber" required><br>

        <label for="cardExpiryDate">Expiry Date:</label>
        <input type="text" name="cardExpiryDate" id="cardExpiryDate" required><br>

        <label for="cardCvv">CVV:</label>
        <input type="password" name="cardCvv" id="cardCvv" required><br>

        <button type="submit">Add Payment</button>
    </form>
    <button type="button" onclick="location.href='viewPayment.jsp';">Cancel</button>
</body>
</html>
