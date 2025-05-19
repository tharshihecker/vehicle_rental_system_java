package conn.sample;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/bookVehicle")
public class BookingServlet extends HttpServlet {

    private VehicleService vehicleService = new VehicleService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
        String email = request.getParameter("email");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        double totalCharge = Double.parseDouble(request.getParameter("totalCharge"));
        String paymentMethod = request.getParameter("paymentMethod");

        // Cash payment scenario
        if ("cash".equalsIgnoreCase(paymentMethod)) {
            // Insert booking into the database
            vehicleService.addBooking(vehicleId, email, startDate, endDate, totalCharge, paymentMethod);
            vehicleService.deleteVehicle(vehicleId);
             request.setAttribute("message", "Booking successful with cash payment!");
            request.getRequestDispatcher("view_vehicles.jsp").forward(request, response);
        }
        // Card payment scenario
        else if ("card".equalsIgnoreCase(paymentMethod)) {
            // Here, you should handle the card payment process. 
            // For demonstration, let's assume the payment was successful.

            // Insert booking into the database
            vehicleService.addBooking(vehicleId, email, startDate, endDate, totalCharge, paymentMethod);
            request.setAttribute("vehicleId", vehicleId);
            request.setAttribute("totalCharge", totalCharge);
            request.getRequestDispatcher("payment.jsp").forward(request, response);
        }
        
     
    }
}
