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

@WebServlet("/StudentClass")
public class StudentClasses extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.println("<html><body>");
		out.println("<h1>View Student's class enrollment</h1>");
		out.println("<form action='StudentClass' method='post'>");
		out.println("<label for='firstName'>First Name:</label>");
		out.println("<input type='text' id='firstName' name='firstName'>");
		out.println("<label for='lastName'>Last Name:</label>");
		out.println("<input type='text' id='lastName' name='lastName'>");
		out.println("<input type='submit' value='Search'>");
		out.println("</form>");
		out.println("<a href='index.jsp'>Home</a>");
		out.println("<a href='Students'>Student List</a>");
		out.println("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		out.println("<html><body>");
		out.println("<h1>Student " + firstName + " " + lastName + " Enrolled In </h1>");

		try {
			connection = DatabaseUtility.getConnection();
			String sql = "SELECT C1.CourseName, C2.Schedule, C2.RoomNumber " +
					"FROM Students S " +
					"JOIN Enrollment E ON S.StudentID = E.StudentID " +
					"JOIN Classes C2 ON E.ClassID = C2.ClassID " +
					"JOIN Courses C1 ON C2.CourseID = C1.CourseID " +
					"WHERE S.FirstName = ? AND S.LastName = ?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, firstName);
			statement.setString(2, lastName);
			resultSet = statement.executeQuery();

			out.println("<table border='1'>");
			out.println("<tr><th>Course</th><th>Schedule</th><th>Room Number</th></tr>");

			while (resultSet.next()) {
				String courseName = resultSet.getString("CourseName");
				String schedule = resultSet.getString("Schedule");
				String roomNum = resultSet.getString("RoomNumber");

				out.println("<tr>");
				out.println("<td>" + courseName + "</td>");
				out.println("<td>" + schedule + "</td>");
				out.println("<td>" + roomNum + "</td>");
				out.println("</tr>");
			}

			out.println("</table>");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		out.println("<a href='StudentClass'>Back to Search</a>");
		out.println("<a href='index.jsp'>Home</a>");
		out.println("<a href='Students'>Student List</a>");
		out.println("</body></html>");
	}
}
