<%@page import="uts.isd.model.Device"%>
<%@page import="uts.isd.model.Customer"%>
<%@page import="uts.isd.model.dao.DBConnector"%>
<%@page import="uts.isd.model.dao.DeviceDAO"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.SQLException"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Device</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link href="css/styles.css" rel="stylesheet">
    <style>
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
        <h2 class="display-6">Edit Device</h2>
        <%
            int deviceId = Integer.parseInt(request.getParameter("id"));
            Device device = null;
            try {
                DBConnector dbConnector = new DBConnector();
                Connection conn = dbConnector.openConnection();
                DeviceDAO deviceDAO = new DeviceDAO(conn);
                device = deviceDAO.findDevice(deviceId);
                conn.close();
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                out.println("Error retrieving device details.");
            }

            if (device != null) {
        %>
        <form action="/UpdateDeviceServlet" method="post" class="form-control" enctype="multipart/form-data">
            <input type="hidden" name="deviceID" value="<%= device.getDeviceID() %>">
            <div class="mb-3">
                <label for="deviceName" class="form-label">Device Name:</label>
                <input type="text" class="form-control" id="deviceName" name="deviceName" value="<%= device.getDeviceName() %>" required>
            </div>
            <div class="mb-3">
                <label for="devicePrice" class="form-label">Price:</label>
                <input type="number" step="0.01" class="form-control" id="devicePrice" name="devicePrice" value="<%= device.getDevicePrice() %>" required>
            </div>
            <div class="mb-3">
                <label for="deviceDesc" class="form-label">Description:</label>
                <input type="text" class="form-control" id="deviceDesc" name="deviceDesc" value="<%= device.getDeviceDesc() %>" required>
            </div>
            <div class="mb-3">
                <label for="deviceStock" class="form-label">Stock:</label>
                <input type="number" class="form-control" id="deviceStock" name="deviceStock" value="<%= device.getDeviceStock() %>" required>
            </div>
            <div class="mb-3">
                <label for="deviceAvailability" class="form-label">Availability:</label>
                <select class="form-control" id="deviceAvailability" name="deviceAvailability" required>
                    <option value="true" <%= device.isDeviceAvailability() ? "selected" : "" %>>Available</option>
                    <option value="false" <%= !device.isDeviceAvailability() ? "selected" : "" %>>Unavailable</option>
                </select>
            </div>
            <div class="mb-3">
                <label for="deviceCategory" class="form-label">Category:</label>
                <input type="text" class="form-control" id="deviceCategory" name="deviceCategory" value="<%= device.getDeviceCategory() %>" required>
            </div>
            <div class="mb-3">
                <label for="deviceBrand" class="form-label">Brand:</label>
                <input type="text" class="form-control" id="deviceBrand" name="deviceBrand" value="<%= device.getDeviceBrand() %>" required>
            </div>
           <div class="mb-3">
                <label for="deviceImage" class="form-label">Device Image:</label>
                <input type="file" class="form-control" id="deviceImage" name="deviceImage">
                <input type="hidden" name="deviceImageURL" value="<%= device.getDeviceImageURL() %>">
                <img src="<%= device.getDeviceImageURL() %>" alt="Device Image" style="width:100px;height:100px;"/>
            </div>
            <button type="submit" class="btn btn-primary">Update Device</button>
            <button type="button" class="btn btn-secondary" onclick="location.href='deviceCatalogue.jsp';">Cancel</button>
        </form>
        <% } else { %>
        <p>Device not found.</p>
        <% } %>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>
