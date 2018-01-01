package com.web.jdbc;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.exception.RegistrationException;

/**
 * Servlet implementation class UserControllerServlet
 */
@WebServlet("/UserControllerServlet")
public class UserControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserDAO userDAO;
	

	@Override
	public void init() throws ServletException {
		super.init();
		// create our userDAO  and pass in the 
		userDAO = new UserDAO();
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// list the students ... in the MVC fashion
		String command = request.getParameter("command");
		if (command != null && command.equals("LOAD")){
			try {
				loadUser(request, response);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (RegistrationException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if(command != null && command.equals("DELETE")){
			try {
				deleteUser(request, response);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (RegistrationException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			try {
				listUsers(request, response);
			} catch (RegistrationException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, RegistrationException, SQLException, Exception {
		// read user id from form data
		int userId = Integer.parseInt(request.getParameter("userId"));
	
		// delete user from database
		userDAO.deleteUser(userId);
		
		// send them back to "list users" page
		listUsers(request, response);
		
	}


	private void updateUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// read user info from the form data
		int userId = Integer.parseInt(request.getParameter("userId"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		String address = request.getParameter("address");
		Long phoneNumber = Long.parseLong(request.getParameter("phoneNumber"));
		String email = request.getParameter("email");
		
		// create a new user object
		User theUser = new User();
		theUser.setUserId(userId);
		theUser.setFirstName(firstName);
		theUser.setLastName(lastName);
		theUser.setUserName(userName);
		theUser.setPassword(password);
		theUser.setGender(gender);
		theUser.setAddress(address);
		theUser.setPhoneNumber(phoneNumber);
		theUser.setEmail(email);
		
		// perform update on database
		userDAO.updateUser(theUser);
		
		// send them back to the "list users" page
		listUsers(request, response);
	}


	private void loadUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, RegistrationException, SQLException {
		// read the user id from the form data
		int theUserId = Integer.parseInt(request.getParameter("userId"));
		
		// get the user from database 
		User theUser = userDAO.getUser(theUserId);
		
		// place student in the request attribute
		request.setAttribute("THE_USER", theUser);
		
		// send to jsp page update-user-form.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update-user-form.jsp");
		dispatcher.forward(request, response);
	}


	private void listUsers(HttpServletRequest request, HttpServletResponse response) throws Exception, RegistrationException, SQLException, IOException {
		List<User> users = userDAO.getAllUsers();
		// get students from the database
		
		// add students to the request
		request.setAttribute("usersList", users);
		
		// send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-users.jsp");
		dispatcher.forward(request, response);
		
		
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command = request.getParameter("command");
		if(command != null && command.equals("UPDATE")){
			try {
				updateUser(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			// read user info from the form data
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String userName = request.getParameter("userName");
			String password = request.getParameter("password");
			String gender = request.getParameter("gender");
			String address = request.getParameter("address");
			Long phoneNumber = Long.parseLong(request.getParameter("phoneNumber"));
			String email = request.getParameter("email");
			
			// create a new user object
			User theUser = new User();
			theUser.setFirstName(firstName);
			theUser.setLastName(lastName);
			theUser.setUserName(userName);
			theUser.setPassword(password);
			theUser.setGender(gender);
			theUser.setAddress(address);
			theUser.setPhoneNumber(phoneNumber);
			theUser.setEmail(email);
			
			// add the user to the database
			try {
				userDAO.createUser(theUser);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (RegistrationException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			// send back to the main page (the users list)
			doGet(request, response);
		}
	}

}
