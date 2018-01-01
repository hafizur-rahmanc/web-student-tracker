<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Update User</title>
	<link type="text/css" rel="stylesheet" href="css/style.css">
	<link type="text/css" rel="stylesheet" href="css/add-user-style.css">
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>Restaurant Delivery</h2>
		</div>
	</div>
	<div id="container">
		<h3>Update User</h3>
		<form action="UserControllerServlet" method="POST">
			<input type="hidden" name="command" value="UPDATE" />
			<input type="hidden" name="userId" value="${THE_USER.userId}" />
			<table>
				<tbody>
					<tr>
						<td><label>First Name:</label></td>
						<td><input type="text" name="firstName" value="${THE_USER.firstName}"/></td>
					</tr>
					<tr>
						<td><label>Last Name:</label></td>
						<td><input type="text" name="lastName" value="${THE_USER.lastName}"/></td>
					</tr>
					<tr>
						<td><label>User Name:</label></td>
						<td><input type="text" name="userName" value="${THE_USER.userName}"/></td>
					</tr>
					<tr>
						<td><label>Password:</label></td>
						<td><input type="password" name="password" value="${THE_USER.password}"/></td>
					</tr>
					<tr>
						<td><label>Gender:</label></td>
						<td>
							<select name="gender">
								<option value="${THE_USER.gender}" selected>${THE_USER.gender}</option>
								<option value="M">M</option>
								<option value="F">F</option>
							</select>
						</td>
					</tr>
					
					<tr>
						<td><label>Address:</label></td>
						<td><input type="text" name="address" value="${THE_USER.address}"/></td>
					</tr>
					<tr>
						<td><label>Phone Number:</label></td>
						<td><input type="text" name="phoneNumber" value="${THE_USER.phoneNumber}"/></td>
					</tr>
					
					<tr>
						<td><label>Email:</label></td>
						<td><input type="text" name="email" value="${THE_USER.email}"/></td>
					</tr>
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Update" class="save" /></td>
					</tr>
				</tbody>
			</table>
		</form>
		
		<div style="clear: both;"></div>
		<p>
			<a href="UserControllerServlet">Back to User List</a>
		</p>
	</div>
</body>
</html>