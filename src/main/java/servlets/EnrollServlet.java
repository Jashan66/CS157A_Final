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

@WebServlet("/Enroll")
public class EnrollServlet extends HttpServlet{


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<h1>Enroll Student</h1>");
        out.println("<form method='POST' action='Enroll'>");
        out.println("First Name: <input type='text' name='firstName' required><br><br>");
        out.println("Last Name: <input type='text' name='lastName' required><br><br>");
        out.println("Grade: <select name='grade' required>");
        out.println("<option value='4'>4</option>");
        out.println("<option value='5'>5</option>");
        out.println("<option value='6'>6</option>");
        out.println("</select><br><br>");
        out.println("Email: <input type='email' name='email' required><br><br>");
        out.println("Address: <input type='text' name='address' required><br><br>");
        out.println("Date of Birth (m/dd/yyyy): <input type='text' name='dob' required><br><br>");
        out.println("<input type='submit' value='Enroll'>");
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
        int grade = Integer.parseInt(request.getParameter("grade"));
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String dob = request.getParameter("dob");
        try {
            connection = DatabaseUtility.getConnection();

            // Get the new max ID
            String getID = "SELECT COALESCE(MAX(StudentID), 0) + 1 AS newId FROM students";
            statement = connection.prepareStatement(getID);
            resultSet = statement.executeQuery();
            System.out.println(resultSet);
            resultSet.next();
            int newId = resultSet.getInt("newId");

            // Insert the  studen
            String sql = "INSERT INTO students (StudentID, FirstName, LastName, DateOfBirth, GradeLevel, Address, Email) VALUES (?, ?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, newId);
            statement.setString(2, firstName);
            statement.setString(3, lastName);
            statement.setString(4, dob);

            statement.setInt(5, grade);
            statement.setString(6, email);
            statement.setString(7, address);

            statement.executeUpdate();

            out.println("<html><body>");
            out.println("<h1>Student Enrolled Successfully!</h1>");
            out.println("<a href='/Enroll'>Enroll Another Student</a><br>");
            out.println("<a href='index.jsp'>Home</a>");
            out.println("</body></html>");
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("<html><body>");
            out.println("<h1>Error enrolling student.</h1>");
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

