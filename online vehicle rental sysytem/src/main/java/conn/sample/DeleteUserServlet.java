package conn.sample;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/deleteUser")
public class DeleteUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get the email from the session
        HttpSession session = request.getSession(false); // Get existing session without creating a new one
        String email = (String) session.getAttribute("user_email");

        try (Connection connection = DBConnection.getConnection()) {
            // Prepare SQL query to delete user based on email
            String sql = "DELETE FROM users WHERE email = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, email);
                statement.executeUpdate();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new ServletException("Database error: " + e.getMessage(), e);
        }

        // Redirect to delete success page after the deletion
        response.sendRedirect("delete_success.jsp");
    }
}
