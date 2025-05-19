<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Forgotten Password</title>
    <link rel="stylesheet" href="css/forgotpassword.css" />
    
    
</head>
<body>
    <div id="container">
        <h1>Forgotten Password</h1>
        
        <!-- Error Display Section -->
        <div id="errors">
            <%
                List<String> errors = (List<String>) request.getAttribute("errors");
                if (errors != null && !errors.isEmpty()) {
                    out.println("<ul>");
                    for (String error : errors) {
                        out.println("<li style='color: red;'>" + error + "</li>");
                    }
                    out.println("</ul>");
                }
            %>
        </div>

        <!-- Form to reset password -->
        <form action="ForgotpasswordServlet" method="post">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" placeholder="Enter your email" required>

            <label for="new_password">New Password:</label>
            <input type="password" id="new_password" name="new_password" minlength="8" placeholder="Enter your new password" required oninput="validatePassword1()">
            <span id="password-validation1"></span> <!-- Password length validation -->

            <label for="confirm_password">Confirm Password:</label>
            <input type="password" id="confirm_password" name="confirm_password" minlength="8" placeholder="Confirm your new password" required oninput="validatePassword2()">
            <span id="password-validation2"></span> <!-- Password match validation -->
             <br>
            <input type="submit" value="Reset Password" name="btn_reset">
        </form>

        <div class="center">
            <p>Remembered your password? <a href="Login.jsp">Login here</a></p>
            <p>Don't have an account? <a href="Signup.jsp">Sign up here</a></p>
        </div>
    </div>
    <script src="js/forgot_password.js">
      
    </script>
</body>
</html>
