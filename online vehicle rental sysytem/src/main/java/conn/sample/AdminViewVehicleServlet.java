package conn.sample;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;

import java.io.IOException;
import java.util.List;

@WebServlet("/AdminViewVehicle")
public class AdminViewVehicleServlet extends HttpServlet {
    private VehicleService vehicleService;

    @Override
    public void init() {
        vehicleService = new VehicleService(); // Initialize the vehicle service
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Fetch all available vehicles
        List<Vehicle> availableVehicles = vehicleService.getAllVehicles(); // Retrieve all vehicles
        request.setAttribute("availableVehicles", availableVehicles); // Set the available vehicles in request scope

        // Forward to view_vehicle_admin.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("view_vehicle_admin.jsp");
        dispatcher.forward(request, response);
    }
}
