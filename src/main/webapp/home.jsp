<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Random"%>
<%@page import="uts.isd.model.*"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href = "css/styles.css">
    <link rel="stylesheet" href = "css/homepage.css">
    <title>Home</title>
</head>
<body>
    <header>
        <div class="container">
            <div class="brand">
                <h1>IoTBay</h1>
            </div>
            <div class="search-bar">
                <input type="text" placeholder="Search for IoT devices...">
                <button type="submit"><i class="fa fa-search"></i></button>
            </div>
            <% if (session.getAttribute("user") == null) { %>
                <div class="login">
                    <a href="login.jsp">Login</a>
                </div>             
            <% } else { %>
                User user = (User) session.getAttribute("user");
                <div>
                    <h1><%=user.getUsername()%></h1>
                </div>
                <div>
                    <a href="login.jsp">Logout</a>
                </div>
            <% } %>
        </div>
    </header>
    <main>
        <section class="about">
            <h2>About IoTBay</h2>
            <p>IoTBay is an online platform for ordering IoT devices such as sensors, actuators, and gateways. Our platform provides a seamless experience for both customers and staff, allowing easy browsing, purchasing, and tracking of orders.</p>
        </section>
    </main>
    <footer>
        <p></p>
    </footer>
</body>
</html>
