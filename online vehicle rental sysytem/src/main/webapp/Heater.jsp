<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Rent Vehicle Booking</title>
    
    <link rel="stylesheet" href="css/homecss.css" />
    <link rel="stylesheet" href="css/homeFooter.css" />
    
    <link
      href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css"
      rel="stylesheet"
    />
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
    
    <header>
      <nav>
        <div class="nav_logo">
          <a href="#">
            <img src="images/car4.jpg" alt="ren vehicle booking logo" />
            <h2>BOOK MY VEHICLE</h2>
          </a>
        </div>

        <input type="checkbox" id="click" />
        <label for="click">
          <i class="menu_btn bx bx-menu"></i>
          <i class="close_btn bx bx-x"></i>
        </label>

        <ul>
          <li><a href="home.jsp">Home</a></li>
          <li><a href="view_vehicles.jsp">View Vehicle</a></li>
          <li><a href="contact.jsp">Contact Us</a></li>
          
          <li>
           <a href="userBookings" onclick="document.getElementById('viewbookingForm').submit();">View Booking</a>
          <form id="viewbokkingForm" action="userBookings" method="get" style="display: none;">
           <input type="submit" value="View booking"> 
          </form>
          </li>
          
          
          
           <li> <a href="Logoutsucess.jsp">Login out</a></li>
            <li> <a href="delete conformation.jsp">Delete Account</a></li>
            
            
            
         </ul>
      </nav>
    </header>
    </body>
    </html>
    