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
import jakarta.servlet.http.HttpSession;

@WebServlet("/ForgotpasswordServlet")
public class ForgotpasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve form parameters
        String email = request.getParameter("email");
        String newPassword = request.getParameter("new_password");
        String confirmPassword = request.getParameter("confirm_password");

        List<String> errors = new ArrayList<>();

        // Check if passwords match
        if (!newPassword.equals(confirmPassword)) {
            errors.add("Passwords do not match.");
        }

        // Proceed to check email even if passwords do not match
        try {
            Connection conn = DBConnection.getConnection();
            // Check if email exists in the database
            String checkEmailSql = "SELECT * FROM users WHERE email = ?";
            try (PreparedStatement checkEmailStmt = conn.prepareStatement(checkEmailSql)) {
                checkEmailStmt.setString(1, email);
                ResultSet rs = checkEmailStmt.executeQuery();

                if (rs.next()) {
                    // Only update password if no errors so far (passwords match)
                    if (errors.isEmpty()) {
                        String updatePasswordSql = "UPDATE users SET password = ? WHERE email = ?";
                        try (PreparedStatement updatePasswordStmt = conn.prepareStatement(updatePasswordSql)) {
                            updatePasswordStmt.setString(1, newPassword); // Keep password simple as requested
                            updatePasswordStmt.setString(2, email);
                            updatePasswordStmt.executeUpdate();
                        }

                        // Set session attributes
                        HttpSession session = request.getSession();
                        session.setAttribute("user_email", email);
                        session.setAttribute("password_change_success", true);

                        // Redirect based on user role
                        String role = rs.getString("role");
                        if ("admin".equals(role)) {
                            response.sendRedirect("admin_dashboard.jsp");
                        } else {
                            response.sendRedirect("password_change.jsp");
                        }
                        return; // Exit after redirecting
                    }
                } else {
                    // Email does not exist
                    errors.add("Email does not exist.");
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            errors.add("Database error: " + e.getMessage());
        }

        // If there are any errors, forward them to the JSP
        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("Forgot_password.jsp").forward(request, response);
        }
    }
}