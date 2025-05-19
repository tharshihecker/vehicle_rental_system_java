package conn.sample;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class BookingService {

    public List<Booking> getUserBookings(String email) {
        List<Booking> userBookings = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM bookings WHERE email = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            
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
                
                userBookings.add(booking);
            }
        }  catch (SQLException e) {
            System.err.println("Error adding booking: " + e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("Database driver not found: " + e.getMessage());
            e.printStackTrace();
        }
        return userBookings;
        }
       
  

    // Method to delete a booking by bookingId
    public void deleteBooking(int bookingId) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "DELETE FROM bookings WHERE booking_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, bookingId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding booking: " + e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("Database driver not found: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
