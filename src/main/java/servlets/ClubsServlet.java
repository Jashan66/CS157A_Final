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

@WebServlet("/Clubs")
public class ClubsServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = DatabaseUtility.getConnection();
			String sql = "SELECT C.ClubName, S.FirstName, S.LastName " +
					"FROM Clubs C " +
					"JOIN Students S ON C.StudentID = S.StudentID " +
					"ORDER BY C.ClubName";
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();

			out.println("<html><body>");
			out.println("<h1>Clubs and Participants</h1>");
			out.println("<table border='1'>");
			out.println("<tr><th>Club</th><th>Student</th></tr>");

			while (resultSet.next()) {
				String clubName = resultSet.getString("ClubName");
				String studentName = resultSet.getString("FirstName") + " " + resultSet.getString("LastName");

				out.println("<tr>");
				out.println("<td>" + clubName + "</td>");
				out.println("<td>" + studentName + "</td>");
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
