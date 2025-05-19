package conn.sample;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            // Use DBConnection class to establish the connection
            conn = DBConnection.getConnection();
            
            String sql = "SELECT * FROM Users WHERE email = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                String dbPassword = rs.getString("password");
                String role = rs.getString("role");
                
                if (dbPassword.equals(password)) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user_email", email);
                    session.setAttribute("user_role", role);
                    
                    String redirectPage;
                    switch (role) {
                        case "admin":
                            redirectPage = "AdminHome.jsp";
                            break;
                        case "renter":
                            redirectPage = "home.jsp";
                            break;
                        default:
                            redirectPage = "error.jsp";
                            break;
                    }
                    response.sendRedirect(redirectPage);
                } else {
                    request.setAttribute("error", "Incorrect password.");
                    request.getRequestDispatcher("Login.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("error", "Email does not exist.");
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred. Please try again.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
