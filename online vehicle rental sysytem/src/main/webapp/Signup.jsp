<%@ page import="java.util.List" %> <!-- Import the List class -->

<!DOCTYPE html>


<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Vehicle Rental Signup</title>
    <link rel="stylesheet" href="css/signup.css">
</head>
<body>
    <div id="container">
        <h1>Signup Page</h1>
        
        <div id="errors">
            <% 
                // Retrieve error messages from the request attribute
                List<String> errors = (List<String>) request.getAttribute("errors");
                if (errors != null && !errors.isEmpty()) {
                    // Display error messages in red
                    for (String error : errors) {
                        out.println("<p style='color: red;'>" + error + "</p>");
                    }
                }
            %>
        </div>
        
        <form action="Signup" method="post">
            <table>
                <tr>
                    <td><label for="fullname">Full Name:</label></td>
                    <td><input type="text" id="fullname" name="fullname" placeholder="Enter your full name" required></td>
                </tr>
                <tr>
                    <td><label for="gender">Gender:</label></td>
                    <td>
                        <input type="radio" id="male" name="gender" value="male" required> Male
                        <input type="radio" id="female" name="gender" value="female" required> Female
                    </td>
                </tr>
                <tr>
                    <td><label for="job">Job:</label></td>
                    <td><input type="text" id="job" name="job" placeholder="Enter your job details" required></td>
                </tr>
                <tr>
                    <td><label for="email">Email:</label></td>
                    <td><input type="email" id="email" name="email" placeholder="Enter your email" required></td>
                </tr>
                <tr>
                    <td><label for="password">Password:</label></td>
                    <td>
                        <input type="password" minlength="8" id="password" name="password" placeholder="Enter your password" required oninput="validatePassword()">
                        <br>
                        <span id="password-validation"></span>
                    </td>
                </tr>
                <tr>
                    <td><label for="phone">Phone Number:</label></td>
                    <td>
                        <input type="text" id="phone" name="phone" placeholder="Enter your phone number" required oninput="validatePhoneNumber()">
                        <br>
                        <span id="phone-validation"></span>
                    </td>
                </tr>
                <tr>
                    <td><label for="address">Address:</label></td>
                    <td><input type="text" id="address" name="address" placeholder="Enter your address" required></td>
                </tr>
                <tr>
                    <td><label for="vehicle_type">Preferred Vehicle Type:</label></td>
                    <td>
                        <select id="vehicle_type" name="vehicle_type" required>
                            <option value="car">Car</option>
                            <option value="motorcycle">Motorcycle</option>
                            <option value="truck">Truck</option>
                            <option value="suv">SUV</option>
                            <option value="van">Van</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><label for="budget">Budget:</label></td>
                    <td>
                        <input type="number" id="budget" name="budget" placeholder="Enter your budget" required oninput="budgetValidation()">
                        <br>
                        <span id="budget-validation"></span>
                    </td>
                </tr>
                <tr>
                    <td><label for="comments">Additional Comments or Preferences:</label></td>
                    <td><textarea id="comments" name="comments" rows="4" cols="50" placeholder="Enter any additional comments or preferences" required></textarea></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <label>
                            <input type="checkbox" id="terms" name="terms" required>
                            I agree to the <a href="termsAndConditions.html" target="_blank">Terms and Conditions</a>
                        </label>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" style="text-align: center;">
                        <input type="submit" value="Submit" name="btn">
                    </td>
                </tr>
            </table>
        </form>
        
        <div class="center">
            <p>Already have an account? <a href="Login.jsp">Login here</a></p>
            <p>Forgot your password? <a href="Forgot_password.jsp">Reset here</a></p>
        </div>
    </div>
    
    <script src="js/signup.js"></script>
</body>
</html>