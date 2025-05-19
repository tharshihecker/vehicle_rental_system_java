package conn.sample;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;  // Import ArrayList
import java.util.List;      // Import List
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/Signup")
public class SignupServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve form parameters from the signup form
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String budgetStr = request.getParameter("budget");

        // Initialize a list to collect error messages
        List<String> errorMessages = new ArrayList<>();

        // Validate phone number
        if (phone.length() != 10 || !phone.startsWith("0")) {
            errorMessages.add("Need a valid phone number starting with 0.");
        }

        // Validate budget
        try {
            double budget = Double.parseDouble(budgetStr);
            if (budget <= 1000) {
                errorMessages.add("Budget should be more than 1000.");
            }
        } catch (NumberFormatException e) {
            errorMessages.add("Invalid budget format.");
        }

        // Check if the email already exists in the database
        try {
            if (emailExists(email)) {
                errorMessages.add("Email already exists.");
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new ServletException("Database error: " + e.getMessage(), e);
        }

        // Check if there are any errors
        if (!errorMessages.isEmpty()) {
            // Forward to the signup page with the error messages
            request.setAttribute("errors", errorMessages);
            request.getRequestDispatcher("Signup.jsp").forward(request, response);
            return;
        }

        // If no errors, insert the new user into the database
        try {
            insertUser(request);

            // Create a session for the newly signed-up user
            HttpSession session = request.getSession();  // Create a new session if it doesn't exist
            session.setAttribute("user_email", email);    // Store user's email in session
            session.setAttribute("user_role", "renter");  // Default role set as 'renter'

            // Redirect to the signup success page
            response.sendRedirect("signup_success.jsp");
        } catch (ClassNotFoundException | SQLException e) {
            // Handle any database errors
            throw new ServletException("Database error: " + e.getMessage(), e);
        }
    }

    // Method to check if the email already exists in the database
    private boolean emailExists(String email) throws SQLException, ClassNotFoundException {
        String sql = "SELECT COUNT(*) FROM users WHERE email = ?";
        try (Connection connection = DBConnection.getConnection();  // Use DBConnection to get connection
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;  // Returns true if email exists
            }
            return false;  // Email does not exist
        }
    }

    // Method to insert a new user into the database
    private void insertUser(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO users (fullname, gender, job, email, password, phone, address, vehicle_type, budget, comments, role) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();  // Use DBConnection to get connection
             PreparedStatement statement = connection.prepareStatement(sql)) {
            // Set the parameters from the signup form
            statement.setString(1, request.getParameter("fullname"));
            statement.setString(2, request.getParameter("gender"));
            statement.setString(3, request.getParameter("job"));
            statement.setString(4, request.getParameter("email"));
            statement.setString(5, request.getParameter("password"));  // Consider hashing the password
            statement.setString(6, request.getParameter("phone"));
            statement.setString(7, request.getParameter("address"));
            statement.setString(8, request.getParameter("vehicle_type"));
            statement.setString(9, request.getParameter("budget"));
            statement.setString(10, request.getParameter("comments"));
            statement.setString(11, "renter");  // Default role is 'renter'
            statement.executeUpdate();  // Insert the user into the database
        }
    }
}