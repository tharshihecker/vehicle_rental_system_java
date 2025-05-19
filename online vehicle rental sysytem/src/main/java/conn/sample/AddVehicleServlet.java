package conn.sample;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@WebServlet("/AddVehicle")
@MultipartConfig // This annotation allows handling of file uploads
public class AddVehicleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private VehicleService vehicleService;

    @Override
    public void init() {
        vehicleService = new VehicleService(); // Initialize the vehicle service
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get vehicle type
        String vehicleType = request.getParameter("vehicle_type");

        // Get charge per day
        String chargePerDayParam = request.getParameter("charge_per_day");
        double chargePerDay = 0.0;
        if (chargePerDayParam != null && !chargePerDayParam.trim().isEmpty()) {
            try {
                chargePerDay = Double.parseDouble(chargePerDayParam);
            } catch (NumberFormatException e) {
                request.setAttribute("errorMessage", "Invalid charge per day format.");
                request.getRequestDispatcher("add_vehicle.jsp").forward(request, response);
                return;
            }
        } else {
            request.setAttribute("errorMessage", "Charge per day is required.");
            request.getRequestDispatcher("add_vehicle.jsp").forward(request, response);
            return;
        }

        // Handle file upload
        Part filePart = request.getPart("photo"); // Retrieve the photo part from the form
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String uploadDir = getServletContext().getRealPath("/images"); // Directory to save the uploaded image

        // Ensure the directory exists
        File uploadDirFile = new File(uploadDir);
        if (!uploadDirFile.exists()) {
            uploadDirFile.mkdir(); // Create directory if it doesn't exist
        }

        // Save the uploaded file
        try {
            filePart.write(uploadDir + File.separator + fileName); // Save the file to the images directory
        } catch (IOException e) {
            request.setAttribute("errorMessage", "Error uploading file.");
            request.getRequestDispatcher("add_vehicle.jsp").forward(request, response);
            return;
        }

        // Create and save the vehicle object
        Vehicle vehicle = new Vehicle(0, vehicleType, "images/" + fileName, chargePerDay);
        vehicleService.addVehicle(vehicle); // Add the vehicle to the database

        // Fetch updated list of vehicles
        List<Vehicle> availableVehicles = vehicleService.getAllVehicles(); // Retrieve all vehicles

        // Set the updated list in request scope
        request.setAttribute("availableVehicles", availableVehicles);

        // Forward to index.jsp after successfully adding the vehicle
        request.getRequestDispatcher("add_vehicle.jsp").forward(request, response); // Forward request with updated vehicles
    }
}
