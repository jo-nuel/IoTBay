<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Random"%>
<%@page import="uts.isd.model.*"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href = "/css/styles.css" type="text/css">
        <title>Register</title>
    </head>
    <body >
        <%
            String email = request.getParameter("email");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String tos = request.getParameter("tos");
            String submitted = request.getParameter("submitted");

            if (submitted != null){
                User user = new User(email, username, password);
                session.setAttribute("user", user);
            }
        %>
        <% if (session.getAttribute("user") != null) { %>
            <h1>An account already exists, log in or reset to register again.</h1>
            <button><a href="login.jsp">Back to Login</a></button>

        <% } else { %>
        <form class="registration-form">
            <label for="Email">Email:</label>
            <input type="email" name="email" id="email" required> 

            <label for="Username">Username:</label>
            <input type="username" name="username" id="username" required> 
                
            <label for="password">Password:</label>
            <input type="password" name="password" id="password" required>

            <label for="tos">TOS:</label>
            <input type="checkbox" name="tos" id="tos" placeholder="tos" />
                
            <button type="submit">Register</button>    
            <a id="cancelbutton" href="login.jsp">Cancel</a>
        </form>

        <% } %>
    </body>
</html>
