package conn.sample;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the current session and invalidate it
        HttpSession session = request.getSession(false); // Get session if exists, else return null
        if (session != null) {
            session.invalidate(); // Destroy session
        }

        // Redirect to logout success page
        response.sendRedirect("Logoutsucess.jsp");
    }
}
