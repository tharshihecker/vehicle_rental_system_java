package conn.sample;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/AdminRemoveVehicle")
public class AdminRemoveVehicleServlet extends HttpServlet {
    private VehicleService vehicleService;

    @Override
    public void init() throws ServletException {
        vehicleService = new VehicleService(); // Initialize the vehicle service
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the vehicle ID from the form
        String vehicleIdStr = request.getParameter("vehicleId");
        if (vehicleIdStr != null && !vehicleIdStr.isEmpty()) {
            try {
                int vehicleId = Integer.parseInt(vehicleIdStr);

                // Call the deleteVehicle method to remove the vehicle
                vehicleService.deleteVehicle(vehicleId);
            } catch (NumberFormatException e) {
                System.err.println("Invalid vehicle ID format: " + e.getMessage());
            }
        }

        // Redirect back to the AdminViewVehicle page after deletion
        response.sendRedirect("AdminViewVehicle");
    }
}
