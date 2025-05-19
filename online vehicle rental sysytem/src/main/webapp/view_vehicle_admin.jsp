<%@ page import="java.util.List" %>
<%@ page import="conn.sample.Vehicle" %>
<%@ include file="AdminHeater.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Available Vehicles - Admin</title>
    <link rel="stylesheet" href="css/admin_view_vehicle.css">
    
    
</head>
  <body>
  <br><br><br><br><br><br><br>


    <div class="container">
        <h1>Available Vehicles</h1>
        <%
            List<Vehicle> availableVehicles = (List<Vehicle>) request.getAttribute("availableVehicles");
            if (availableVehicles != null && !availableVehicles.isEmpty()) {
                for (Vehicle vehicle : availableVehicles) {
        %>
<div class="vehicle">
    <img src="<%= vehicle.getPhoto() %>" alt="Vehicle Image" style="width: 500px; height: 300px;">
    <p>Type: <%= vehicle.getType() %></p>

    <!-- ChargePerDay update form -->
    <div class="button-row">
        <form action="AdminUpdateVehicle" method="post">
            <label for="chargePerDay">Charge per day:</label>
            <input type="number" name="chargePerDay" value="<%= vehicle.getChargePerDay() %>" step="0.01" min="0" required>
            <input type="hidden" name="vehicleId" value="<%= vehicle.getId() %>">
            <input type="submit" value="Update Charge" class="update-button">
        </form>
        
        <!-- Remove Vehicle Button -->
        <form action="AdminRemoveVehicle" method="post" onsubmit="return confirm('Are you sure you want to remove this vehicle?');">
            <input type="hidden" name="vehicleId" value="<%= vehicle.getId() %>">
            <input type="submit" value="Remove Vehicle" class="remove-button">
        </form>
    </div>
</div>
   
        <%
                }
            } else {
        %>
            <p>No vehicles available at the moment.</p>
        <%
            }
        %>
    </div>
</body>
</html>
