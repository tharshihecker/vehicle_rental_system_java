package conn.sample;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/userBookings")
public class UserBookingsServlet extends HttpServlet {

    private BookingService bookingService = new BookingService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Check if session exists and user is logged in
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user_email") == null) {
            response.sendRedirect("Login.jsp");
            return;
        }

        String userEmail = (String) session.getAttribute("user_email");

        // Retrieve user's bookings from the database
        List<Booking> userBookings = bookingService.getUserBookings(userEmail);
        request.setAttribute("userBookings", userBookings);
        request.getRequestDispatcher("userBookings.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle booking deletion
        int bookingId = Integer.parseInt(request.getParameter("bookingId"));
        bookingService.deleteBooking(bookingId);
        response.sendRedirect("userBookings"); // Redirect to the bookings page after deletion
    }
}
