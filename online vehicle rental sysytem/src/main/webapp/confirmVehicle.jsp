<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Confirm Vehicle Selection</title>
</head>
<body>
    <h2>Confirm Vehicle Selection</h2>
    <p>Vehicle Type: ${selectedVehicle.type}</p>
    <p>Charge Per Day: ${selectedVehicle.chargePerDay}</p>
    <p><img src="${selectedVehicle.photo}" alt="Vehicle Image" width="150" height="150"/></p>
    
    <form action="bookVehicle" method="post">
        <input type="hidden" name="vehicleId" value="${selectedVehicle.id}" />
        <input type="submit" value="Confirm and Book" />
    </form>
</body>
</html>
