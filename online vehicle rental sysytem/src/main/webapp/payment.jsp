<%@ page import="conn.sample.VehicleService" %>

<html>
<head>
    <title>Card Payment</title>
    <link rel="stylesheet" href="css/payment.css" />
</head>
<body>
   
    <form action="processPayment" method="post">
      <h1>Complete Your Payment</h1>
        <input type="hidden" name="vehicleId" value="<%= request.getAttribute("vehicleId") %>" />
        <p><strong>Total Amount to Pay:</strong> $<%= request.getAttribute("totalCharge") %></p>
         <p>
        <strong>Card Number:</strong>
        <input type="text" name="cardNumber" required
               pattern="^\d{16}$" title="Please enter a valid 16-digit card number." />
       </p>
    
    <p>
        <strong>Expiry Date:</strong>
        <input type="month" name="expiryDate" required />
    </p>
    
    <p>
        <strong>CVV:</strong>
        <input type="text" name="cvv" required
               pattern="^\d{3}$" maxlength="3" minlength="3" title="Please enter a valid 3-digit CVV." />
    </p>
        
        <button type="submit">Pay</button>
    </form>
</body>
</html>
