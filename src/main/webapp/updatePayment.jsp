<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="uts.isd.model.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/styles.css" type="text/css">
    <title>Update Payment</title> 
</head>
<body class="loginbody">

    <h1>Update Payment</h1>
    <form class="login-form" method="POST" action="/UpdatePaymentServlet">
        <label for="paymentID">Payment ID:</label>
        <input type="text" name="paymentID" id="paymentID" required><br>

        
        
        <button type="submit">Update Payment</button>
    </form>
    <form method="GET" action="/PaymentManagementServlet">
        <button type="submit">Cancel</button>
    </form>
</body>
</html>
