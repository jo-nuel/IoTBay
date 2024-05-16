<%@page import="uts.isd.model.Device"%>
<%@page import="uts.isd.model.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Device</title>
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
        <h2 class="display-6">Add New Device</h2>
        <form action="/AddDeviceServlet" method="post" class="form-control" enctype="multipart/form-data">
            <div class="mb-3">
                <label for="deviceName" class="form-label">Device Name:</label>
                <input type="text" class="form-control" id="deviceName" name="deviceName" required>
            </div>
            <div class="mb-3">
                <label for="devicePrice" class="form-label">Price:</label>
                <input type="number" step="0.01" class="form-control" id="devicePrice" name="devicePrice" required>
            </div>
            <div class="mb-3">
                <label for="deviceDesc" class="form-label">Description:</label>
                <input type="text" class="form-control" id="deviceDesc" name="deviceDesc" required>
            </div>
            <div class="mb-3">
                <label for="deviceStock" class="form-label">Stock:</label>
                <input type="number" class="form-control" id="deviceStock" name="deviceStock" required>
            </div>
            <div class="mb-3">
                <label for="deviceAvailability" class="form-label">Availability:</label>
                <select class="form-control" id="deviceAvailability" name="deviceAvailability" required>
                    <option value="true">Available</option>
                    <option value="false">Unavailable</option>
                </select>
            </div>
            <div class="mb-3">
                <label for="deviceCategory" class="form-label">Category:</label>
                <input type="text" class="form-control" id="deviceCategory" name="deviceCategory" required>
            </div>
            <div class="mb-3">
                <label for="deviceBrand" class="form-label">Brand:</label>
                <input type="text" class="form-control" id="deviceBrand" name="deviceBrand" required>
            </div>
            <div class="mb-3">
                <label for="deviceImage" class="form-label">Device Image:</label>
                <input type="file" class="form-control" id="deviceImage" name="deviceImage" accept="image/*" required>
            </div>
            <button type="submit" class="btn btn-primary">Add Device</button>
            <button type="button" class="btn btn-secondary" onclick="location.href='deviceCatalogue.jsp';">Cancel</button>
        </form>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>
