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
/* Search function to see the courses offered and other info related
 * (teachers, room num, schedule)
 * 
 */

@WebServlet("/Courses")
public class CourseList extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.println("<html><body>");
		out.println("<h1>Search for a Course</h1>");
		out.println("<form action='Courses' method='post'>");
		out.println("<label for='courseName'>Course Name:</label>");
		out.println("<input type='text' id='courseName' name='courseName'>");
		out.println("<input type='submit' value='Search'>");
		out.println("</form>");
		out.println("<a href='index.jsp'>Home</a>");
		out.println("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String courseName = request.getParameter("courseName");
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		out.println("<html><body>");
		out.println("<h1>Classes for Course: " + courseName + "</h1>");
		// C1 -> Courses, C2 -> Classes
		try {
			connection = DatabaseUtility.getConnection();
			String sql = "SELECT C1.CourseName, C2.ClassID, C2.RoomNumber, C2.Schedule, S.FirstName, S.LastName " +
					"FROM Courses C1 " +
					"JOIN Classes C2 ON C1.CourseID = C2.CourseID " +
					"JOIN Staff S ON C2.StaffID = S.StaffID " +
					"WHERE C1.CourseName LIKE ?";
			statement = connection.prepareStatement(sql);
			statement.setString(1,"%" + courseName + "%"); 
			resultSet = statement.executeQuery();

			out.println("<table border='1'>");
			out.println("<tr><th>Class ID</th><th>Room Number</th><th>Schedule</th><th>Teacher</th></tr>");

			while (resultSet.next()) {
				int classID = resultSet.getInt("ClassID");
				String roomNumber = resultSet.getString("RoomNumber");
				String schedule = resultSet.getString("Schedule");
				String teacher = resultSet.getString("FirstName") + " " + resultSet.getString("LastName");

				out.println("<tr>");
				out.println("<td>" + classID + "</td>");
				out.println("<td>" + roomNumber + "</td>");
				out.println("<td>" + schedule + "</td>");
				out.println("<td>" + teacher + "</td>");
				out.println("</tr>");
			}

			out.println("</table>");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		out.println("<a href='Courses'>Back to Search</a>");
		out.println("<a href='index.jsp'>Home</a>");
		out.println("</body></html>");
	}

}
