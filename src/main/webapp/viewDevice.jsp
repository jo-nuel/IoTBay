<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="uts.isd.model.*"%>
<!DOCTYPE html>
<html>
<head>
    <title>View Device</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link href="css/styles.css" rel="stylesheet">
    <style>
        .device-details {
            display: flex;
            align-items: flex-start;
            justify-content: space-between;
            margin-top: 20px;
        }
        .device-image {
            max-width: 500px;
            max-height: 500px;
            object-fit: cover;
        }
        .device-info {
            flex: 1;
            margin-left: 20px;
        }
        .device-info h1 {
            font-size: 2em;
            margin-bottom: 10px;
        }
        .device-info p {
            margin: 5px 0;
        }
        .device-info .price {
            font-size: 1.5em;
        }
        .btn-back {
            margin-top: 20px;
        }
        .user-info {
            display: flex;
            align-items: center;
            gap: 10px;
        }
    </style>
</head>
<body>
    <nav class="navbar navbar-expand-md navbar-dark bg-primary">
        <div class="container">
            <a href="deviceCatalogue.jsp" class="navbar-brand"><h1>IoTBay</h1></a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbar">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbar">
                <ul class="navbar-nav ms-auto">
                    <% if (session.getAttribute("customer") != null) { %>
                        <div class="user-info">
                            <span class="navbar-text"><%= ((Customer) session.getAttribute("customer")).getUserName() %></span>
                            <button class="btn btn-outline-light" onclick="location.href='logout.jsp';">Logout</button>
                        </div>
                    <% } else { %>
                        <li class="nav-item">
                            <a href="login.jsp" class="nav-link">Login</a>
                        </li>
                        <li class="nav-item">
                            <a href="register.jsp" class="nav-link">Register</a>
                        </li>
                    <% } %>
                </ul>
            </div>
        </div>
    </nav>

    <div class="container my-3">
        <div class="device-details">
            <div class="image-section">
                <% Device device = (Device) session.getAttribute("device"); %>
                <img src="<%= device.getDeviceImageURL() %>" alt="Device Image" class="device-image"/>
            </div>
            <div class="device-info">
                <h1><%= device.getDeviceName() %></h1>
                <p>Brand: <strong><%= device.getDeviceBrand() %></strong></p>
                <p class="price">$<%= device.getDevicePrice() %></p>
                <p>Description: <%= device.getDeviceDesc() %></p>
                <p>Stock: <%= device.getDeviceStock() %></p>
                <p>Category: <%= device.getDeviceCategory() %></p>
                <p>Availability: <%= device.isDeviceAvailability() ? "Available" : "Not Available" %></p>
                <br>
                <a href="deviceCatalogue.jsp" class="btn btn-primary">Back to Catalogue</a>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>
