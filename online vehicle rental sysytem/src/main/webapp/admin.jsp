<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Panel - User Management</title>
    <link rel="stylesheet" href="css/admin.css">
    <%@ include file="AdminHeater.jsp" %>
    
   <style>
       .content {
            margin-top: 50px; /* Adds 100px of space from the top */
            text-align: center;
            
 table {
    width: 100%; /* Full width */
    border-collapse: collapse; /* Collapse borders */
    margin-top: 20px; /* Space above the table */
    background-color: rgba(255, 255, 255, 0.9); /* Background color for the table */
    border-radius: 8px; /* Round corners */
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); /* Shadow effect */
}
        }
        
        </style>
</head>
 
<body>
 <br><br><br><br><br><br><br>

<div id="container">
    <h1>Admin Panel - User Management</h1>

    <!-- Display Errors or Success Messages -->
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

    <!-- Users Table -->
    <table border="1" width="100%" cellspacing="0" cellpadding="10">
        <thead>
            <tr>
                <th>ID</th>
                <th>Full Name</th>
                <th>Gender</th>
                <th>Job</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Address</th>
                <th>Vehicle Type</th>
                <th>Budget</th>
                <th>Comments</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <% 
                java.util.List<conn.sample.User> users = (java.util.List<conn.sample.User>) request.getAttribute("users");

                if (users != null && !users.isEmpty()) {
                    for (conn.sample.User user : users) {
            %>
            <tr>
                <td><%= user.getId() %></td>
                <td><%= user.getFullName() %></td>
                <td><%= user.getGender() %></td>
                <td><%= user.getJob() %></td>
                <td><%= user.getEmail() %></td>
                <td><%= user.getPhone() %></td>
                <td><%= user.getAddress() %></td>
                <td><%= user.getVehicleType() %></td>
                <td><%= user.getBudget() %></td>
                <td><%= user.getComments() %></td>
                <td>
                    <form action="AdminRemoveUser" method="post" onsubmit="return confirm('Are you sure you want to remove this user?');">
                        <input type="hidden" name="userId" value="<%= user.getId() %>">
                        <input type="submit" value="Remove" class="remove-button">
                    </form>
                </td>
            </tr>
            <% 
                    }
                } else {
            %>
            <tr>
                <td colspan="11">No users found.</td>
            </tr>
            <% 
                }
            %>
        </tbody>
    </table>
</div>
</body>
</html>
