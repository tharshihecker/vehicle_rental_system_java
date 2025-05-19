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
    
       <style>
        body {
            background-image:url('images/23.jpg');
            background-repeat: no-repeat;
            background-attachment: fixed;
            background-size: cover;
            background-position: center 100px; /* Position the background image 200px from the top */
            margin: 0;
            padding: 0;
            color: black;
        }
        
        </style>
  </head>
  <body>
    
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
          <li><a href="AdminHome.jsp">Home</a></li>
         
             <li><a href="add_vehicle.jsp">Add Vehicle </a></li>
          
           <li>
           <a href="#" onclick="document.getElementById('viewuserForm').submit();">View User</a>
          <form id="viewuserForm" action="Admin" method="get" style="display: none;">
           <input type="submit" value="View booking"> 
          </form>
          </li>
          
         <li>
         <a href="#" onclick="document.getElementById('viewBookingForm').submit();">View Booking</a>
          <form id="viewBookingForm" action="AdminBookings" method="get" style="display: none;">
           <input type="submit" value="View booking"> 
         </form>
         </li>
         
         
           <li>
           <a href="#" onclick="document.getElementById('viewvehicleForm').submit();">View Vehicle</a>
          <form id="viewvehicleForm" action="AdminViewVehicle" method="get" style="display: none;">
           <input type="submit" value="View booking"> 
          </form>
          </li>
          
            <li>
           <a href="#" onclick="document.getElementById('viewContactForm').submit();">View Contact </a>
          <form id="viewContactForm" action="AdminContacts" method="get" style="display: none;">
           <input type="submit" value="View booking"> 
          </form>
          </li>
        										
        
           <li> <a href="Logoutsucess.jsp">Login out</a></li>
        </ul>
      </nav>
    </header>
   
    </body>
    </html>
    