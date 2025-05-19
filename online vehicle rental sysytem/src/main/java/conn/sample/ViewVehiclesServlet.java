package conn.sample;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/viewVehicles")
public class ViewVehiclesServlet extends HttpServlet {

    private VehicleService vehicleService = new VehicleService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        request.setAttribute("vehicles", vehicles);
        request.getRequestDispatcher("view_vehicles.jsp").forward(request, response);
    }
}
