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

@WebServlet("/UpdateStudent")
public class UpdateStudentServlet extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        //popualte fields with sutdent data
        try {
            connection = DatabaseUtility.getConnection();
            String sql = "SELECT StudentID, FirstName, LastName FROM students";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            out.println("<html><body>");

            out.println("<h1>Update Student Information</h1>");
            out.println("<form method='GET' action='UpdateStudent'>");
            out.println("Select Student: <select name='studentId' onchange='this.form.submit()'>");
            out.println("<option value=''>Select</option>");

            while (resultSet.next()) {
                int studentId = resultSet.getInt("StudentID");

                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                out.println("<option value='" + studentId + "'>" + firstName + " " + lastName + "</option>");
            }

            out.println("</select>");

            out.println("</form>");

            //check if student is valid
            if (request.getParameter("studentId") != null) {

                int studentId = Integer.parseInt(request.getParameter("studentId"));
                sql = "SELECT * FROM students WHERE StudentID=" +studentId;
                statement = connection.prepareStatement(sql);
                resultSet = statement.executeQuery();

                if (resultSet.next()) {

                    String firstName = resultSet.getString("FirstName");
                    String lastName = resultSet.getString("LastName");
                    String dob = resultSet.getString("DateOfBirth");

                    int grade = resultSet.getInt("GradeLevel");
                    String address = resultSet.getString("Address");
                    String email = resultSet.getString("Email");

                    out.println("<form method='POST' action='UpdateStudent'>");
                    out.println("<input type='hidden' name='studentId' value='" + studentId + "'>");


                    out.println("First Name: <input type='text' name='firstName' value='" + firstName + "' required><br><br>");
                    out.println("Last Name: <input type='text' name='lastName' value='" + lastName + "' required><br><br>");


                    out.println("Grade: <select name='grade' required>");
                    out.println("<option value='4'" + (grade == 4 ? " selected" : "") + ">4</option>");
                    out.println("<option value='5'" + (grade == 5 ? " selected" : "") + ">5</option>");
                    out.println("<option value='6'" + (grade == 6 ? " selected" : "") + ">6</option>");

                    out.println("</select><br><br>");

                    out.println("Email: <input type='email' name='email' value='" + email + "' required><br><br>");
                    out.println("Address: <input type='text' name='address' value='" + address + "' required><br><br>");

                    out.println("Date of Birth (m/dd/yyyy) <input type='text' name='dob' value='" + dob + "' required><br><br>");


                    out.println("<input type='submit' value='Update'>");
                    out.println("</form>");
                }
            }

            out.println("<a href='index.jsp'>Home</a>");
            out.println("</body></html>");
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("<html><body>");
            out.println("<h1>Error getting student information.</h1>");
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        Connection connection = null;
        PreparedStatement statement = null;

        int studentId = Integer.parseInt(request.getParameter("studentId"));
        String firstName = request.getParameter("firstName");

        String lastName = request.getParameter("lastName");
        int grade = Integer.parseInt(request.getParameter("grade"));
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String dob = request.getParameter("dob");

        try {
            connection = DatabaseUtility.getConnection();
            String sql = "UPDATE students SET FirstName=?, LastName=?, DateOfBirth=?, GradeLevel=?, Address=?, Email=? WHERE StudentID=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, dob);

            statement.setInt(4, grade);
            statement.setString(5, address);
            statement.setString(6, email);

            statement.setInt(7, studentId);
            statement.executeUpdate();

            out.println("<html><body>");
            out.println("<h1>Student Information Updated Successfully!</h1>");

            out.println("<a href='index.jsp'>Home</a>");
            out.println("</body></html>");
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("<html><body>");
            out.println("<h1>Error updating student information.</h1>");
            out.println("<a href='index.jsp'>Home</a>");
            out.println("</body></html>");
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
