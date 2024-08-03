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

@WebServlet("/Staff")
public class StaffServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = DatabaseUtility.getConnection();
			String sql = "SELECT FirstName, LastName, Email, Position FROM Staff";
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();

			out.println("<html><body>");
			out.println("<h1>Staff List</h1>");
			out.println("<table border='1'>");
			out.println("<tr><th>Name</th><th>Email</th><th>Position</th></tr>");

			while (resultSet.next()) {
				String name = resultSet.getString("FirstName") + " " + resultSet.getString("LastName");
				String email = resultSet.getString("Email");
				String pos = resultSet.getString("Position");

				out.println("<tr>");
				out.println("<td>" + name + "</td>");
				out.println("<td>" + email + "</td>");
				out.println("<td>" + pos + "</td>");
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
