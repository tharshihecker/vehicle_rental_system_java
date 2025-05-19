package conn.sample;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Admin")
public class AdminServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Fetch all users from the database
            List<User> users = getAllUsers();

            // Add users to request and forward to admin.jsp
            request.setAttribute("users", users);
            request.getRequestDispatcher("admin.jsp").forward(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            // In case of database error, forward to the admin page with an error message
            request.setAttribute("error", "Database error: " + e.getMessage());
            request.getRequestDispatcher("admin.jsp").forward(request, response);
        }
    }

    // Method to get all users from the database
    private List<User> getAllUsers() throws SQLException, ClassNotFoundException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM Users";  // Query to fetch all users

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setFullName(rs.getString("fullname"));
                user.setGender(rs.getString("gender"));
                user.setJob(rs.getString("job"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setAddress(rs.getString("address"));
                user.setVehicleType(rs.getString("vehicle_type"));
                user.setBudget(rs.getBigDecimal("budget"));
                user.setComments(rs.getString("comments"));
                users.add(user);
            }
        }

        return users;
    }
}
