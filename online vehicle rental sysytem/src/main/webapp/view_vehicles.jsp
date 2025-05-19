<%@ page import="java.util.List" %>
<%@ page import="conn.sample.Vehicle" %>
<%@ page import="conn.sample.VehicleService" %>

<html>
<head>
    <title>Available Vehicles</title>
     <%@ include file="Heater.jsp" %>
    <style>
        /* Add your CSS styling here */
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
<br><br><br><br><br><br><br><br>
    <h1>Available Vehicles</h1>
    <%
        VehicleService vehicleService = new VehicleService();
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
    %>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Type</th>
                <th>Photo</th>
                <th>Charge Per Day</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <% for (Vehicle vehicle : vehicles) { %>
                <tr>
                    <td><%= vehicle.getId() %></td>
                    <td><%= vehicle.getType() %></td>
                    <td><img src="<%= vehicle.getPhoto() %>" alt="Vehicle Image" width="100" /></td>
                    <td><%= vehicle.getChargePerDay() %></td>
                    <td><a href="select_vehicle.jsp?vehicleId=<%= vehicle.getId() %>">Select</a></td>
                </tr>
            <% } %>
        </tbody>
    </table>
    <%@ include file="Footer.jsp" %>
</body>
</html>
