package conn.sample;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AdminRemoveBooking")
public class AdminRemoveBookingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String bookingId = request.getParameter("bookingId");

        try {
            // Remove the booking by ID
            removeBookingById(Integer.parseInt(bookingId));

            // Redirect back to the admin booking page with a success message
            response.sendRedirect("AdminBookings?message=Booking removed successfully.");
        } catch (SQLException | ClassNotFoundException e) {
            // Redirect back to the admin booking page with an error message
            response.sendRedirect("AdminBookings?error=Error removing booking: " + e.getMessage());
        }
    }

    // Method to remove a booking from the database
    private void removeBookingById(int bookingId) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM bookings WHERE booking_id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, bookingId);
            statement.executeUpdate();
        }
    }
}
