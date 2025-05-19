<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Panel - Contact Submissions</title>
    <%@ include file="AdminHeater.jsp" %>
    
</head>

<body>
<br><br><br><br><br><br><br><br><br>

<h1>Contact Submissions</h1>

<div id="messages">
    <% 
        String error = request.getParameter("error");
        if (error != null) {
            out.println("<p style='color: red;'>" + error + "</p>");
        }
    %>
</div>

<table border="1" width="100%" cellspacing="0" cellpadding="10">
    <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email Address</th>
            <th>Phone</th>
            <th>Gender</th>
            <th>Comments</th>
        </tr>
    </thead>
    <tbody>
        <% 
            java.util.List<conn.sample.Contact> contacts = (java.util.List<conn.sample.Contact>) request.getAttribute("contacts");

            if (contacts != null && !contacts.isEmpty()) {
                for (conn.sample.Contact contact : contacts) {
        %>
        <tr>
            <td><%= contact.getId() %></td>
            <td><%= contact.getName() %></td>
            <td><%= contact.getEmailAddress() %></td>
            <td><%= contact.getPhone() %></td>
            <td><%= contact.getGender() %></td>
            <td><%= contact.getComments() %></td>
        </tr>
        <% 
                }
            } else {
        %>
        <tr>
            <td colspan="6">No contact submissions found.</td>
        </tr>
        <% 
            }
        %>
    </tbody>
</table>

</body>
</html>
