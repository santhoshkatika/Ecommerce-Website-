import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String phoneNumber = request.getParameter("PhNo");
        String address = request.getParameter("Address");
        String password = request.getParameter("password");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/EcommerceUsers", "rudhra", "rudhra");

            // Update the query to include phoneNumber and address fields
            String query = "INSERT INTO login (username, phone_number, address, password) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, phoneNumber);
            ps.setString(3, address);
            ps.setString(4, password);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                String message = "Your Registration is successful!";
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                out.println("<script type=\"text/javascript\">");
                out.println("alert('" + message + "');");
                out.println("window.location.href='index.html';");
                out.println("</script>");
            } else {
                response.sendRedirect("error.html");
            }

            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.html");
        }
    }
}
