<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Successful</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 600px;
            margin: 100px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        h1 {
            color: #333;
        }

        p {
            color: #666;
        }

        .redirect-message {
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Login Successful</h1>
        <p>Welcome back Admin</p>
        <div class="redirect-message">
            <p>Redirecting you to home page...</p>
        </div>
    </div>

   <script>
    // Redirect to the AdminDashboard servlet after 2 seconds
    setTimeout(function() {
        window.location.href = "add_vehicle.jsp";
    }, 2000); // 2000 milliseconds = 2 seconds
</script>

</body>
</html>
