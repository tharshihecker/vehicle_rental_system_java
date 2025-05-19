<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Delete Account Confirmation</title>
    <link rel="stylesheet" href="css/delete.css"> 
</head>
<body>

<%@ page session="false" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>

<%
    // Check if session exists
    HttpSession session = request.getSession(false); 
    if (session == null || session.getAttribute("user_email") == null) {
        // Redirect to login if session is not valid
        response.sendRedirect("Login.jsp");
        return;
    }
%>

    <div class="container">
        <h2>Confirm Account Deletion</h2>
        <p>Are you sure you want to delete your account? This action cannot be undone.</p>
        
        <!-- "Yes" button will submit a form to delete the account -->
        <form action="deleteUser" method="get">
            <button type="submit">Yes, Delete</button>
        </form>
        
        <!-- "No" button redirects back to the index page -->
        <form action="home.jsp" method="get">
            <button type="submit" class="go-back">No, Go Back</button>
        </form>
    </div>

</body>
</html>