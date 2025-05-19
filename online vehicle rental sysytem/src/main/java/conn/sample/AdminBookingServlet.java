package conn.sample;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AdminBookings")
public class AdminBookingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Fetch all bookings from the database
            List<Booking> bookings = getAllBookings();

            // Add bookings to request and forward to adminBookings.jsp
            request.setAttribute("bookings", bookings);
            request.getRequestDispatcher("adminBookings.jsp").forward(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            // In case of database error, forward to the page with an error message
            request.setAttribute("error", "Database error: " + e.getMessage());
            request.getRequestDispatcher("adminBookings.jsp").forward(request, response);
        }
    }

    // Method to get all bookings from the database
    private List<Booking> getAllBookings() throws SQLException, ClassNotFoundException {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT * FROM bookings";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                Booking booking = new Booking();
                booking.setBookingId(rs.getInt("booking_id"));
                booking.setVehicleId(rs.getInt("vehicle_id"));
                booking.setemail(rs.getString("email"));
                booking.setStartDate(rs.getDate("start_date"));
                booking.setEndDate(rs.getDate("end_date"));
                booking.setTotalCharge(rs.getBigDecimal("total_charge"));
                booking.setPaymentMethod(rs.getString("payment_method"));
                booking.setBookingDate(rs.getDate("booking_date"));
                bookings.add(booking);
            }
        }

        return bookings;
    }
}
