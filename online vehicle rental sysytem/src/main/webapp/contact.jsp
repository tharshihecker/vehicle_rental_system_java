<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Contact Us</title>
    <link rel="stylesheet" href="css/contact.css" />
    <style>
      .content {
            margin-top: 100px; 

        }
        </style>
</head>

<%@ include file="Heater.jsp" %>

<body>
<br><br><br><br><br><br><br><br>
    <div id="content">
        <div id="messages">
            <% 
                String message = request.getParameter("message");
                if (message != null) {
                    out.println("<p style='color: green;'>" + message + "</p>");
                }
                String error = request.getParameter("error");
                if (error != null) {
                    out.println("<p style='color: red;'>" + error + "</p>");
                }
            %>
        </div>

        <form action="Contact" method="post">
            <h1>Contact Us</h1>

            <!-- Name field -->
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" required pattern="[A-Za-z]{5,}" 
                   title="Name must contain only letters and be at least 5 characters long."><br><br>

            <!-- Email address field (auto-filled from session) -->
            <label for="emailAddress">Email Address:</label>
            <input type="email" id="emailAddress" name="emailAddress" 
                   value="<%= session.getAttribute("user_email") != null ? session.getAttribute("user_email") : "" %>" 
                   readonly><br><br>

            <!-- Phone field -->
            <label for="phone">Phone:</label>
            <input type="tel" id="phone" name="phone" required pattern="0[0-9]{9}" 
                   title="Phone number must start with 0 and must be 10 digits."><br><br>

            <!-- Gender field -->
            <label for="gender">Gender:</label>
            <select id="gender" name="gender" required>
                <option value="Male">Male</option>
                <option value="Female">Female</option>
                <option value="Other">Other</option>
            </select><br><br>

            <!-- Comments field -->
            <label for="comments">Comments:</label><br>
            <textarea id="comments" name="comments" rows="5" cols="30" required></textarea><br><br>

            <!-- Submit button -->
            <input type="submit" value="Submit">
        </form>
    </div>
</body>

<%@ include file="Footer.jsp" %>
</html>
