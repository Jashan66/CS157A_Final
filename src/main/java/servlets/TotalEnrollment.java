package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utility.DatabaseUtility;

@WebServlet("/TotalEnrollment")
public class TotalEnrollment extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseUtility.getConnection();
            String sql = "SELECT COUNT(*) AS total FROM students";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            out.println("<html><body>");
            out.println("<h1>Total Enrollment</h1>");

            if (resultSet.next()) {
                int total = resultSet.getInt("total");
                out.println("<h2>Total number of students enrolled: " + total + "</h2>");
            } else {
                out.println("<p>No enrollment data available.</p>");
            }




            out.println("<a href='index.jsp'>Home</a>");
            out.println("</body></html>");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
