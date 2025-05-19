<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Vehicle</title>
    <link rel="stylesheet" href="css/addvehice.css">
     
        <style>
      
         .content {
            margin-top: 100px; /* Adds 100px of space from the top */
            text-align: center;
        }
        </style>
</head>
<%@ include file="AdminHeater.jsp" %>
<body>

  
    <div class="container">
        <h1>Add Vehicle</h1> 
        <form action="AddVehicle" method="post" enctype="multipart/form-data">
            <table>
                <tr>
                    <td><label for="vehicle_type">Vehicle Type:</label></td>
                    <td>
                        <select id="vehicle_type" name="vehicle_type" required>
                            <option value="car">Car</option>
                            <option value="motorcycle">Motorcycle</option>
                            <option value="van">Van</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><label for="photo">Vehicle Photo:</label></td>
                    <td><input type="file" id="photo" name="photo" accept="image/*" required></td>
                </tr>
              <tr>
                <td><label for="charge_per_day">Charge Per Day:</label></td>
                 <td>
                <input type="number" id="charge_per_day" name="charge_per_day" placeholder="Enter charge per day"  min="0" step="0.01" required oninput="this.value = this.value.replace(/[^0-9.]/g, '');">
                 </td>
               </tr>
              
                <tr>
                    <td colspan="2" style="text-align: center;">
                        <input type="submit" value="Add Vehicle">
                    </td>
                </tr>
            </table>
        </form>

        <%-- Display error message if available --%>
        <%
            String errorMessage = (String) request.getAttribute("errorMessage");
            if (errorMessage != null) {
        %>
            <p style="color: red;"><%= errorMessage %></p>
        <%
            }
        %>
    </div>
</body>
</html>
