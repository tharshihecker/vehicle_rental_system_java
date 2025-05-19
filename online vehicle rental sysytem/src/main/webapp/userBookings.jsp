<%@ page import="java.util.List" %>
<%@ page import="conn.sample.Booking" %>
 <%@ include file="Heater.jsp" %>
<%
    List<Booking> userBookings = (List<Booking>) request.getAttribute("userBookings");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Your Bookings</title>
   
</head>
      <style>
        body {
           background-image: url('https://wallpapercave.com/wp/wp11965297.jpg');
            background-repeat: no-repeat;
            background-attachment: fixed;
            background-size: cover;
            background-position: center 130px; /* Position the background image 200px from the top */
            margin: 0;
            padding: 0;
            color: white;
        }
      .content {
            margin-top: 100px; 
            text-align: center;
        }
      
        table {
            width: 80%;
            margin: 0 auto;
            background-color: rgba(0, 0, 0, 0.7); /* Semi-transparent background for better readability */
            color: white;
        }

        h1 {
            text-align: center;
            margin-top: 20px;
        }

        th, td {
            padding: 10px;
            text-align: center;
        }

        th {
            background-color: rgba(255, 255, 255, 0.3);
        }
    </style>
  
<body>
<BR><br><BR><br><BR><br>
    <h1>Your Bookings</h1>

    <%
        if (userBookings != null && !userBookings.isEmpty()) {
    %>
        <table border="1">
            <thead>
                <tr>
                    
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
                    for (Booking booking : userBookings) {
                %>
                <tr>
                   
                    <td><%= booking.getStartDate() %></td>
                    <td><%= booking.getEndDate() %></td>
                    <td>$<%= booking.getTotalCharge() %></td>
                    <td><%= booking.getPaymentMethod() %></td>
                    <td><%= booking.getBookingDate() %></td>
                    <td>
                        <form action="userBookings" method="post" onsubmit="return confirm('Are you sure you want to delete this booking?');">
                            <input type="hidden" name="bookingId" value="<%= booking.getBookingId() %>">
                            <input type="submit" value="Delete">
                        </form>
                    </td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    <%
        } else {
    %>
        <p>No bookings found.</p>
    <%
        }
    %>
</body>
</html>
