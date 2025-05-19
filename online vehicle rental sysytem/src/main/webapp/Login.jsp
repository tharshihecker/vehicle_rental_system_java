<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Vehicle Rental System - Login</title>
    <link rel="stylesheet" href="css/login.css">

</head>
<body>
    <div id="container">
        <h1>Login to Vehicle Rental</h1>
        <div id="errors">
            <% 
                String error = (String) request.getAttribute("error");
                if (error != null) {
                    out.println("<p style='color: red;'>" + error + "</p>");
                }
            %>
        </div>
        <form action="Login" method="post">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" placeholder="Enter your email" required>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" placeholder="Enter your password" minlength="8" required oninput="validatePassword1()">
            <br>
            <span id="password-validation1">
            </span> <!-- Password length validation message -->
<br>
            <input type="submit" value="Login">
        </form>
        <p>Forgot your password? <a href="Forgot_password.jsp">Reset here</a></p>
        <p>Don't have an account? <a href="Signup.jsp">Sign up here</a></p>
    </div>
        <script src="js/login.js">
    
    </script>
</body>
</html>
