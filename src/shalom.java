import jdbc.MyConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

/**
 * Created by jacobmenke on 1/7/17.
 */
@WebServlet(urlPatterns = { "/dogs/shaloms" })
public class shalom extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.getWriter().println("<h1>dogs are cool in teh shalom servlet</h1>");

        Connection connection = MyConnection.getConnection("root", "root", "abcd");

        System.out.println(connection);

    }
}
