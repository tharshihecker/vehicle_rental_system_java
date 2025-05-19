package conn.sample;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AdminRemoveUser")
public class AdminRemoveUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userId = request.getParameter("userId");

        try {
            // Remove the user by ID
            removeUserById(Integer.parseInt(userId));

            // Redirect back to the admin panel with a success message
            response.sendRedirect("Admin?message=User removed successfully.");
        } catch (SQLException | ClassNotFoundException e) {
            // Redirect back to the admin panel with an error message
            response.sendRedirect("Admin?error=Error removing user: " + e.getMessage());
        }
    }

    // Method to remove a user from the database
    private void removeUserById(int userId) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM Users WHERE id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            statement.executeUpdate();
        }
    }
}
