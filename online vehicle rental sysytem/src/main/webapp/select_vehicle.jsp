<%@ page import="conn.sample.VehicleService" %>
<%@ page import="conn.sample.Vehicle" %>

<html>
<head>
    <title>Book Vehicle</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }

        .container {
            display: flex;
            justify-content: space-between;
            margin: 100px;
        }

        .left-side, .right-side {
            width: 48%;
            background-color: white;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .left-side img {
            display: block;
            margin: 20px 0;
            max-width: 100%;
            height: auto;
        }

        h1 {
            text-align: center;
        }

        form {
            display: flex;
            flex-direction: column;
        }

        form p {
            margin: 10px 0;
        }

        form input[type="date"],
        form input[type="radio"],
        form button {
            padding: 10px;
            margin: 5px 0;
        }

        form button {
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
            padding: 12px 20px;
            font-size: 16px;
        }

        form button:hover {
            background-color: #45a049;
        }

        #totalCharge {
            font-weight: bold;
        }
    </style>

    <script>
    function setMinStartDate() {
        const today = new Date().toISOString().split('T')[0];
        document.getElementById('startDate').setAttribute('min', today);
    }

    function validateDate() {
        const startDate = document.getElementById("startDate").value;
        const endDate = document.getElementById("endDate").value;

        // Reset total charge message
        document.getElementById('totalCharge').innerText = 'Total Charge: $0';
        
        if (startDate && endDate) {
            const start = new Date(startDate);
            const end = new Date(endDate);
            if (end <= start) {
                alert('End date must be after the start date.');
                document.getElementById('endDate').value = ''; // Reset end date
            }
        }
    }

    function calculateTotalCharge() {
        const startDate = new Date(document.getElementById('startDate').value);
        const endDate = new Date(document.getElementById('endDate').value);
        const chargePerDay = parseFloat(document.getElementById('chargePerDay').value);
        const days = (endDate - startDate) / (1000 * 3600 * 24);

        if (days > 0) {
            const totalCharge = days * chargePerDay;
            document.getElementById('totalCharge').innerText = 'Total Charge: $' + totalCharge.toFixed(2);
            document.getElementById('totalChargeInput').value = totalCharge.toFixed(2);
        } else {
            document.getElementById('totalCharge').innerText = 'Invalid date selection';
        }
    }
    </script>
</head>
<body onload="setMinStartDate()">

<%@ include file="Heater.jsp" %>

<%
    int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
    VehicleService vehicleService = new VehicleService();
    Vehicle vehicle = vehicleService.getVehicleById(vehicleId);
%>

<h1>Book Vehicle</h1>
<div class="container">
    <!-- Left side - Vehicle details -->
    <div class="left-side">
        <p><strong>Vehicle Type:</strong> <%= vehicle.getType() %></p>
        <p><strong>Charge Per Day:</strong> $<%= vehicle.getChargePerDay() %></p>
        <img src="<%= vehicle.getPhoto() %>" alt="Vehicle" />
    </div>

    <!-- Right side - Booking form -->
    <div class="right-side">
        <form action="bookVehicle" method="post">
            <input type="hidden" name="vehicleId" value="<%= vehicleId %>" />
            <input type="hidden" id="chargePerDay" value="<%= vehicle.getChargePerDay() %>" />
            <input type="hidden" id="email" name="email"  
                value="<%= session.getAttribute("user_email") != null ? session.getAttribute("user_email").toString() : "" %>" />
            
            <p><strong>Start Date:</strong> <input type="date" id="startDate" name="startDate" required onchange="validateDate(); calculateTotalCharge();" /></p>
            <p><strong>End Date:</strong> <input type="date" id="endDate" name="endDate" required onchange="validateDate(); calculateTotalCharge();" /></p>

            <p id="totalCharge">Total Charge: $0</p>
            <input type="hidden" id="totalChargeInput" name="totalCharge" />

            <p><strong>Select Payment Method:</strong></p>
            <input type="radio" name="paymentMethod" value="cash" checked> Cash<br>
            <input type="radio" name="paymentMethod" value="card"> Card<br>

            <button type="submit">Book Now</button>
        </form>
    </div>
</div>

<%@ include file="Footer.jsp" %>
</body>
</html>
