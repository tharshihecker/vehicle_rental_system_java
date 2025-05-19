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

@WebServlet("/Contact")
public class ContactServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String emailAddress = request.getParameter("emailAddress");
        String phone = request.getParameter("phone");
        String gender = request.getParameter("gender");
        String comments = request.getParameter("comments");

        try {
            // Insert contact details into the database
            saveContact(name, emailAddress, phone, gender, comments);

            // Redirect to a success page
            response.sendRedirect("contact.jsp?message=Thank you for contacting us!");
        } catch (SQLException | ClassNotFoundException e) {
            response.sendRedirect("contact.jsp?error=Failed to submit your details. Please try again later.");
        }
    }

    // Method to save contact details into the database
    private void saveContact(String name, String email, String phone, String gender, String comments)
            throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO contacts (name, email_address, phone, gender, comments) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setString(3, phone);
            statement.setString(4, gender);
            statement.setString(5, comments);
            statement.executeUpdate();
        }
    }
}
