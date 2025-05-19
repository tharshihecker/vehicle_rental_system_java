<%@ page import="java.util.List" %>
<%@ page import="conn.sample.Booking" %>

<html>
<head>
    <title>Admin Panel - Booking Management</title>
    <link rel="stylesheet" href="css/admin.css">
    <%@ include file="AdminHeater.jsp" %>
    
       <style>
       .content {
            margin-top: 100px; 
            text-align: center;
            text-color:black;
      
            
    }
        
        </style>
</head>

<body>
<br><br><br><br><br><br><br>
<div id="container">
    <h1>Admin Panel - Booking Management</h1>

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

    <!-- Bookings Table -->
    <table border="1" width="100%" cellspacing="0" cellpadding="10">
        <thead>
            <tr>
                <th>Booking ID</th>
                <th>Email</th>
                <th>Start Date</th>
                <th>End Date</th>
                <th>Total Charge</th>
                <th>Payment Method</th>
                <th>Booking Date</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <% 
                List<Booking> bookings = (List<Booking>) request.getAttribute("bookings");

                if (bookings != null && !bookings.isEmpty()) {
                    for (Booking booking : bookings) {
            %>
            <tr>
                <td><%= booking.getBookingId() %></td>
                <td><%= booking.getemail() %></td>
                <td><%= booking.getStartDate() %></td>
                <td><%= booking.getEndDate() %></td>
                <td><%= booking.getTotalCharge() %></td>
                <td><%= booking.getPaymentMethod() %></td>
                <td><%= booking.getBookingDate() %></td>
                <td>
                    <form action="AdminRemoveBooking" method="post" onsubmit="return confirm('Are you sure you want to remove this booking?');">
                        <input type="hidden" name="bookingId" value="<%= booking.getBookingId() %>">
                        <input type="submit" value="Remove" class="remove-button">
                    </form>
                </td>
            </tr>
            <% 
                    }
                } else {
            %>
            <tr>
                <td colspan="9">No bookings found.</td>
            </tr>
            <% 
                }
            %>
        </tbody>
    </table>
</div>
</body>
</html>

