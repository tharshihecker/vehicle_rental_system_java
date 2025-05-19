package conn.sample;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AdminUpdateVehicle")
public class AdminUpdateVehicleServlet extends HttpServlet {

    private VehicleService vehicleService = new VehicleService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get vehicle ID and new charge per day from the request
        int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
        double newChargePerDay = Double.parseDouble(request.getParameter("chargePerDay"));

        // Call the VehicleService to update the vehicle's charge
        vehicleService.updateVehicleCharge(vehicleId, newChargePerDay);

        // Redirect to the admin view vehicle page after update
        response.sendRedirect("AdminHeater.jsp");
    }
}
