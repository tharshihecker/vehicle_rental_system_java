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

@WebServlet("/AdminContacts")
public class AdminViewContactsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Fetch all contacts from the database
            List<Contact> contacts = getAllContacts();

            // Add contacts to request and forward to adminContacts.jsp
            request.setAttribute("contacts", contacts);
            request.getRequestDispatcher("adminContacts.jsp").forward(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            request.setAttribute("error", "Database error: " + e.getMessage());
            request.getRequestDispatcher("adminContacts.jsp").forward(request, response);
        }
    }

    // Method to get all contacts from the database
    private List<Contact> getAllContacts() throws SQLException, ClassNotFoundException {
        List<Contact> contacts = new ArrayList<>();
        String sql = "SELECT * FROM contacts";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                Contact contact = new Contact();
                contact.setId(rs.getInt("id"));
                contact.setName(rs.getString("name"));
                contact.setEmailAddress(rs.getString("email_address"));
                contact.setPhone(rs.getString("phone"));
                contact.setGender(rs.getString("gender"));
                contact.setComments(rs.getString("comments"));
                contacts.add(contact);
            }
        }

        return contacts;
    }
}
