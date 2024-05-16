<%@page import="uts.isd.model.Device"%>
<%@page import="uts.isd.model.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Delete Device</title>
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
        <h2 class="display-6">Delete Device</h2>
        <form action="/DeleteDeviceServlet" method="post" class="form-control">
            <p>Are you sure you want to delete this device?</p>
            <input type="hidden" name="deviceID" value="<%= request.getParameter("id") %>">
            <button type="submit" class="btn btn-danger">Delete</button>
            <a href="deviceCatalogue.jsp" class="btn btn-secondary">Cancel</a>
        </form>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>
