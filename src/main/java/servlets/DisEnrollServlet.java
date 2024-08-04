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

@WebServlet("/DisEnroll")
public class DisEnrollServlet extends HttpServlet{


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<h1>Dis Enroll Your Student</h1>");
        out.println("<form method='POST' action='DisEnroll'>");
        out.println("First Name: <input type='text' name='firstName' required><br><br>");
        out.println("Last Name: <input type='text' name='lastName' required><br><br>");
        out.println("<input type='submit' value='DisEnroll'>");
        out.println("</form>");
        out.println("<a href='index.jsp'>Home</a>");
        out.println("</body></html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        try {
            connection = DatabaseUtility.getConnection();

            //get student id
            String getStudentID = "SELECT StudentID FROM students WHERE FirstName = ? AND LastName = ?";
            statement = connection.prepareStatement(getStudentID);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("StudentID");

                // delete stuednt using id
                String deleteStudent = "DELETE FROM students WHERE StudentID = ?";
                statement = connection.prepareStatement(deleteStudent);
                statement.setInt(1, id);
                statement.executeUpdate();

                out.println("<html><body>");
                out.println("<h1>Student: " + firstName + " " + lastName + " dis enrolled successfully" + "</h1>");
                out.println("<a href='index.jsp'>Home</a>");
                out.println("</body></html>");
            } else {
                out.println("<html><body>");
                out.println("<h1>Student not found!</h1>");
                out.println("<a href='index.jsp'>Home</a>");
                out.println("</body></html>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("<html><body>");
            out.println("<h1>Error Dis enrolling student.</h1>");
            out.println("<a href='/Enroll'>Try Again</a><br>");
            out.println("<a href='index.jsp'>Home</a>");
            out.println("</body></html>");
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}