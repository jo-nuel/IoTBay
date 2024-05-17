<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Random"%>
<%@page import="uts.isd.model.*"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href = "/css/styles.css" type="text/css">
        <title>Register</title> 
    </head>
    <body class="registerbody">
        <%
            String email = request.getParameter("email");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String address = request.getParameter("address");
            String customerType = request.getParameter("customerType");
            boolean accountActive = Boolean.parseBoolean(request.getParameter("accountActive"));
            String submitted = request.getParameter("submitted");

            if (submitted != null){
                String userID = java.util.UUID.randomUUID().toString();
                String userType = "Customer";
                Customer customer = new Customer(userID, username, email, password, userType, customerType, address, accountActive);
                session.setAttribute("customer", customer);
            }
        %>
        <% if (session.getAttribute("customer") != null) {
            response.sendRedirect("login.jsp");
        } 
        else { %>
            <h1 style="color: white;">IoTBay</h1>
            <form class="registration-form">
                <label for="Email">Email:</label>
                <input type="email" name="email" id="email" required> 

                <label for="Username">Username:</label>
                <input type="username" name="username" id="username" required> 
                    
                <label for="password">Password:</label>
                <input type="password" name="password" id="password" required>

                <label for="tos">TOS:</label>
                <input type="checkbox" name="tos" id="tos" placeholder="tos" required/>

                <input type="hidden" name="submitted" id="submitted" value="true" />
                    
                <button type="submit">Register</button>    
                <a id="cancelbutton" href="login.jsp">Cancel</a>
            </form>

        <% } %>
    </body>
</html>
