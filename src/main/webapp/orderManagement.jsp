<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order Management</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            color: #333;
        }

        h1, h2 {
            color: #ff0000; 
        }

        form {
            background-color: #ffffff;
            border: 1px solid #ccc;
            border-radius: 5px;
            padding: 20px;
            margin: 20px 0;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        label {
            display: block;
            margin-bottom: 5px;
            color: #d9534f; /* Soft red for labels */
        }

        input[type="text"],
        input[type="number"],
        input[type="date"],
        input[type="submit"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }

        input[type="submit"] {
            background-color: #d9534f; /* Soft red for buttons */
            color: white;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        input[type="submit"]:hover {
            background-color: #c9302c; /* Darker red for hover */
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            background-color: #ffffff;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #5bc0de; /* Light blue for table header */
            color: white;
        }

        td {
            background-color: #f9f9f9;
        }

        td form {
            display: inline;
        }

        input[type="hidden"] {
            display: none;
        }
    </style>
</head>
<body>
    <h1>Order Management</h1>
    
    <h2>Create Order</h2>
    <form action="OrderServlet" method="post">
        <input type="hidden" name="action" value="create">
        <label for="orderDate">Order Date:</label>
        <input type="date" id="orderDate" name="orderDate" required><br>
        <label for="orderStatus">Order Status:</label>
        <input type="number" id="orderStatus" name="orderStatus" required><br>
        <label for="itemsOrdered">Items Ordered:</label>
        <input type="text" id="itemsOrdered" name="itemsOrdered" required><br>
        <label for="itemsQuantity">Items Quantity:</label>
        <input type="number" id="itemsQuantity" name="itemsQuantity" required><br>
        <label for="finalPrice">Final Price:</label>
        <input type="number" step="0.01" id="finalPrice" name="finalPrice" required><br>
        <input type="submit" value="Create Order">
    </form>

    <h2>Update Order</h2>
    <form action="OrderServlet" method="post">
        <input type="hidden" name="action" value="update">
        <label for="orderID">Order ID:</label>
        <input type="number" id="orderID" name="orderID" required><br>
        <label for="orderDate">Order Date:</label>
        <input type="date" id="orderDate" name="orderDate" required><br>
        <label for="orderStatus">Order Status:</label>
        <input type="number" id="orderStatus" name="orderStatus" required><br>
        <label for="itemsOrdered">Items Ordered:</label>
        <input type="text" id="itemsOrdered" name="itemsOrdered" required><br>
        <label for="itemsQuantity">Items Quantity:</label>
        <input type="number" id="itemsQuantity" name="itemsQuantity" required><br>
        <label for="finalPrice">Final Price:</label>
        <input type="number" step="0.01" id="finalPrice" name="finalPrice" required><br>
        <input type="submit" value="Update Order">
    </form>

    <h2>Cancel Order</h2>
    <form action="OrderServlet" method="post">
        <input type="hidden" name="action" value="cancel">
        <label for="orderID">Order ID:</label>
        <input type="number" id="orderID" name="orderID" required><br>
        <input type="submit" value="Cancel Order">
    </form>

    <h2>View Orders</h2>
    <form action="OrderServlet" method="post">
        <input type="hidden" name="action" value="view">
        <input type="submit" value="View All Orders">
    </form>

    <h2>Orders List</h2>
    <c:if test="${not empty orders}">
        <table>
            <tr>
                <th>Order ID</th>
                <th>Order Date</th>
                <th>Order Status</th>
                <th>Items Ordered</th>
                <th>Items Quantity</th>
                <th>Final Price</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="order" items="${orders}">
                <tr>
                    <td>${order.orderID}</td>
                    <td>${order.orderDate}</td>
                    <td>${order.orderStatus}</td>
                    <td>${order.itemsOrdered}</td>
                    <td>${order.itemsQuantity}</td>
                    <td>${order.finalPrice}</td>
                    <td>
                        <form action="OrderServlet" method="post" style="display:inline;">
                            <input type="hidden" name="action" value="update">
                            <input type="hidden" name="orderID" value="${order.orderID}">
                            <input type="hidden" name="orderDate" value="${order.orderDate}">
                            <input type="hidden" name="orderStatus" value="${order.orderStatus}">
                            <input type="hidden" name="itemsOrdered" value="${order.itemsOrdered}">
                            <input type="hidden" name="itemsQuantity" value="${order.itemsQuantity}">
                            <input type="hidden" name="finalPrice" value="${order.finalPrice}">
                            <input type="submit" value="Update">
                        </form>
                        <form action="OrderServlet" method="post" style="display:inline;">
                            <input type="hidden" name="action" value="cancel">
                            <input type="hidden" name="orderID" value="${order.orderID}">
                            <input type="submit" value="Cancel">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</body>
</html>
