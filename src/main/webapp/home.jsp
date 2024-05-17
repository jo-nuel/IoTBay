<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Random"%>
<%@page import="uts.isd.model.*"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href = "css/styles.css">
        <link rel="stylesheet" href = "css/homepage.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>Home</title>
    </head>
    <body>
        <%
                Customer customer = (Customer) session.getAttribute("customer");
        %>

        <% if(customer == null) { %>
            <header>
                <div class="container">
                    <div class="brand">
                        <h1>IoTBay</h1>
                    </div>
                    <div class="search-bar">
                        <input type="text" placeholder="Search for IoT devices...">
                        <button type="submit"><i class="fa fa-search"></i></button>
                    </div>
                </div>
            </header>
            <main>
                <section>
                    <h1>You have not registered, please fill in the form to view IoTBay catalogue!</h1>
                    <button type="button" onclick="location.href='login.jsp';">Return to Login</button>
                </section>
            </main>
            <footer>
                <p></p>
            </footer>
                
        <% } else { %>    
            <header>
                <div class="container">
                    <div class="brand">
                        <h1>IoTBay</h1>
                    </div>
                    <div class="search-bar">
                        <input type="text" placeholder="Search for IoT devices...">
                        <button type="submit"><i class="fa fa-search"></i></button>
                    </div>
                    <div>
                        <h1><%=customer.getUserName()%></h1>
                    </div>
                    <div>
                        <button><a href="logout.jsp">Logout</a></button>
                    </div>
                </div>
            </header>
            <main>
                <section class="about">
                    <h2>About IoTBay</h2>
                    <p>IoTBay is an online platform for ordering IoT devices such as sensors, actuators, and gateways. Our platform provides a seamless experience for both customers and staff, allowing easy browsing, purchasing, and tracking of orders.</p>
                </section>
            </main>
            <footer>
                <button type="button" onclick="location.href='deviceCatalogue.jsp';">Go to catalogue</button>
                <button type="button" onclick="location.href='viewPayment.jsp';">Go to payment</button>
            </footer>
        <% } %>
    </body>
</html>
