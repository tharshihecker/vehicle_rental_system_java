package conn.sample;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/processPayment")
public class PaymentServlet extends HttpServlet {

    private VehicleService vehicleService = new VehicleService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));

        // You can add payment gateway integration here

        // After successful payment, delete the vehicle
        vehicleService.deleteVehicle(vehicleId);

        request.setAttribute("message", "Payment successful and booking confirmed!");
        request.getRequestDispatcher("view_vehicles.jsp").forward(request, response);
    }
}
