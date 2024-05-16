<%@page import="uts.isd.model.Device"%>
<%@page import="uts.isd.model.Staff"%>
<%@page import="uts.isd.model.*"%>
<%@page import="uts.isd.model.dao.DBConnector"%>
<%@page import="uts.isd.model.dao.DeviceDAO"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Device Catalogue</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link href="css/styles.css" rel="stylesheet">
    <style>
        .device-card {
            border: 1px solid #ddd;
            border-radius: 5px;
            padding: 15px;
            margin: 10px;
            text-align: center;
            cursor: pointer;
            transition: transform 0.2s;
        }
        .device-card:hover {
            transform: scale(1.05);
        }
        .device-card img {
            width: 100%;
            height: 200px;
            object-fit: contain;
        }
        .device-card a {
            text-decoration: none;
            color: inherit;
        }
        .device-card .buttons {
            margin-top: 10px;
        }
        .navbar .form-control {
            width: 600px;
        }
        .navbar .btn {
            width: 100px;
        }
        .user-info {
            display: flex;
            align-items: center;
            gap: 10px;
        }
    </style>
</head>
<body>
<%
    Customer customer = (Customer) session.getAttribute("customer");
    ArrayList<String> categories = (ArrayList<String>) session.getAttribute("categories");
%>

    <nav class="navbar navbar-expand-md navbar-dark bg-primary">
        <div class="container">
            <a href="home.jsp" class="navbar-brand"><h1>IoTBay</h1></a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbar">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbar">
                <form class="d-flex mx-auto" action="SearchDeviceServlet" method="get">
                    <div class="dropdown">
                        <button class="btn btn-secondary dropdown-toggle" type="button" id="categoryDropdown" data-bs-toggle="dropdown" aria-expanded="false">
                            Categories
                        </button>
                        <ul class="dropdown-menu" aria-labelledby="categoryDropdown">
                            <% for (String category : categories) { %>
                                <li><a class="dropdown-item" href="SearchDeviceServlet?category=<%= category %>"><%= category %></a></li>
                            <% } %>
                        </ul>
                    </div>
                    <input class="form-control me-2" type="search" placeholder="Search for devices" aria-label="Search" name="query">
                    <button class="btn btn-outline-light" type="submit">Search</button>
                </form>
                <ul class="navbar-nav ms-auto">
                    <%
                        if (customer != null) {
                    %>
                    <div class="user-info">
                        <span class="navbar-text"><%= customer.getUserName() %></span>
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
    <div class="d-flex justify-content-between align-items-center">
        <h2 class="display-6">Product List</h2>
        <a href="addDevice.jsp"><button class="btn btn-primary btn-add-device">Add New Device</button></a>
    </div>
    <div class="row">
        <%
            List<Device> devices = (List<Device>) session.getAttribute("devices");
            if (devices == null) {
                DBConnector dbConnector = new DBConnector();
                Connection conn = dbConnector.openConnection();
                DeviceDAO deviceDAO = new DeviceDAO(conn);
                devices = deviceDAO.listAllDevices();
                conn.close();
            }

            for (Device device : devices) {
        %>
        <div class="col-md-3">
            <a href="ViewDeviceServlet?id=<%= device.getDeviceID() %>">
                <div class="device-card">
                    <img src="<%= device.getDeviceImageURL() %>" alt="<%= device.getDeviceName() %>">
                    <h5><%= device.getDeviceName() %></h5>
                    <p><strong>Brand:</strong> <%= device.getDeviceBrand() %></p>
                    <p><strong>Price:</strong> $<%= device.getDevicePrice() %></p>
                    <div class="buttons">
                        <a href="editDevice.jsp?id=<%= device.getDeviceID() %>" class="btn btn-warning btn-sm">Edit</a>
                        <a href="DeleteDeviceServlet?id=<%= device.getDeviceID() %>" class="btn btn-danger btn-sm">Delete</a>
                    </div>
                </div>
            </a>
        </div>
        <%
            }
            session.removeAttribute("devices");
        %>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>
