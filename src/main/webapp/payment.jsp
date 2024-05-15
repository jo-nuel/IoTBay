<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="uts.isd.model.Payment" %>
<%@ page import="uts.isd.model.dao.PaymentDAO" %>
<%@ page import="uts.isd.model.dao.DBConnector" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Payment</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        .container {
            max-width: 400px;
            margin: 50px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, 0.1);
        }

        h1 {
            text-align: center;
        }

        label {
            font-weight: bold;
        }

        input[type="text"],
        input[type="number"],
        input[type="date"],
        input[type="password"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }

        .payment-type {
            display: flex;
            justify-content: space-between;
            margin-bottom: 20px;
        }

        button {
            width: 100%;
            padding: 15px;
            background-color: #4CAF50;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }

        button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Payment</h1>
        <form action="confirmation.jsp" method="post"> <!-- Update action to point to confirmation.jsp -->
            <div class="payment-type">
                <%
                    // Fetch payment types from the database
                    DBConnector dbConnector = new DBConnector();
                    PaymentDAO paymentDAO = new PaymentDAO(dbConnector.openConnection());
                    List<Payment> paymentTypes = paymentDAO.getAllPayments();
                    for (Payment paymentType : paymentTypes) {
                %>
                    <label for="<%= paymentType.getpaymentType() %>"><%= paymentType.getpaymentType() %></label>
                    <input type="radio" name="payment-type" id="<%= paymentType.getpaymentType() %>">
                <%
                    }
                %>
            </div>
            <label for="card-name">Name on Card</label>
            <input type="text" id="card-name" name="card-name" required>
            <label for="card-number">Card Number</label>
            <input type="text" id="card-number" name="card-number" maxlength="19" required>
            <label for="expiry-date">Expiry Date</label>
            <input type="date" id="expiry-date" name="expiry-date" required>
            <label for="cvv">CVV</label>
            <input type="password" id="cvv" name="cvv" maxlength="3" required>
            <button type="submit">PAY</button>
        </form>
    </div>

    <script>
        document.getElementById('card-number').addEventListener('input', function (e) {
            var target = e.target;
            var input = target.value.replace(/\D/g, '').substring(0, 16);
            var formattedInput = input.replace(/\W/gi, '').replace(/(.{4})/g, '$1 ').trim();
            target.value = formattedInput;
        });
    </script>
</body>
</html>
