package conn.sample;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/selectVehicle")
public class SelectVehicleServlet extends HttpServlet {

    private VehicleService vehicleService = new VehicleService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String vehicleId = request.getParameter("id");

        // You can pass the selected vehicle to another JSP or process further
        Vehicle selectedVehicle = vehicleService.getVehicleById(Integer.parseInt(vehicleId));
        request.setAttribute("selectedVehicle", selectedVehicle);
        request.getRequestDispatcher("confirmVehicle.jsp").forward(request, response);
    }
}
