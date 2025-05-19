package conn.sample;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleService {

    // Method to add a vehicle to the database
    public void addVehicle(Vehicle vehicle) {
        String query = "INSERT INTO vehicles (type, photo, charge_per_day) VALUES (?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, vehicle.getType());
            statement.setString(2, vehicle.getPhoto());
            statement.setDouble(3, vehicle.getChargePerDay());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding vehicle: " + e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("Database driver not found: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Method to retrieve all vehicles from the database
    public List<Vehicle> getAllVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM vehicles";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String type = resultSet.getString("type");
                String photo = resultSet.getString("photo");
                double chargePerDay = resultSet.getDouble("charge_per_day");

                Vehicle vehicle = new Vehicle(id, type, photo, chargePerDay);
                vehicles.add(vehicle);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving vehicles: " + e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("Database driver not found: " + e.getMessage());
            e.printStackTrace();
        }
        return vehicles;
    }

    // Method to retrieve a vehicle by its ID
    public Vehicle getVehicleById(int id) {
        String query = "SELECT * FROM vehicles WHERE id = ?";
        Vehicle vehicle = null;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String type = resultSet.getString("type");
                    String photo = resultSet.getString("photo");
                    double chargePerDay = resultSet.getDouble("charge_per_day");

                    vehicle = new Vehicle(id, type, photo, chargePerDay);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving vehicle by ID: " + e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("Database driver not found: " + e.getMessage());
            e.printStackTrace();
        }

        return vehicle;
    }
    
    

    // Method to delete a vehicle by its ID
    public void deleteVehicle(int id) {
        String query = "DELETE FROM vehicles WHERE id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting vehicle: " + e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("Database driver not found: " + e.getMessage());
            e.printStackTrace();
        }
    }
   
    
 // Method to update the charge per day for a specific vehicle
    public void updateVehicleCharge(int id, double newChargePerDay) {
        String query = "UPDATE vehicles SET charge_per_day = ? WHERE id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setDouble(1, newChargePerDay);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating vehicle charge: " + e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("Database driver not found: " + e.getMessage());
            e.printStackTrace();
        }
    }

	
    
    // Method to add a booking to the database
    public void addBooking(int vehicleId, String email, String startDate, String endDate, double totalCharge, String paymentMethod) {
        String query = "INSERT INTO bookings (vehicle_id, email, start_date, end_date, total_charge, payment_method) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, vehicleId);
			statement.setString(2, email);
            statement.setString(3, startDate);
            statement.setString(4, endDate);
            statement.setDouble(5, totalCharge);
            statement.setString(6, paymentMethod);

            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding booking: " + e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("Database driver not found: " + e.getMessage());
            e.printStackTrace();
        }
        
    }
    
    
    
   
}
