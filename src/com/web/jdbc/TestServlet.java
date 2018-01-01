package com.web.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Step 1: Set up the printwriter
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
		// Step 2: Get a connection to the database
		Connection conn = null;
		Statement stmt = null;
		ResultSet resultSet = null;
		OracleConnection oracle = new OracleConnection();
		
		try {
			conn = oracle.getConnection();
			// Step 3: Create a SQL statements
			String sql = "SELECT * FROM USERS";
			stmt = conn.createStatement();
			// Step 4: Execute SQL query
			resultSet = stmt.executeQuery(sql);
			// Step 5: Process the result set
			while(resultSet.next()) {
				String email = resultSet.getString("email");
				out.println(email);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
