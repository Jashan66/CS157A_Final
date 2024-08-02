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

@WebServlet ("/Reports")
public class StudentServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseUtility.getConnection();
            String sql = "SELECT StudentID, Description, GradeInClass FROM Reports";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            out.println("<html><body>");
            out.println("<h1>Student Reports</h1>");
            out.println("<table border='1'>");
            out.println("<tr><th>StudentID</th><th>Grade</th><th>Description</th></tr>");

            while (resultSet.next()) {
                int studentId = resultSet.getInt("StudentID");
                String grade = resultSet.getString("GradeInClass");
                String desc = resultSet.getString("Decription");

                out.println("<tr>");
                out.println("<td>" + studentId + "</td>");
                out.println("<td>" + grade + "</td>");
                out.println("<td>" + desc + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");
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
