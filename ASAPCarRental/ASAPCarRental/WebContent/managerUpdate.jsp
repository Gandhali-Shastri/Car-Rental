<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="GUI/css/MyCss.css">
	<title>Update User</title>
</head>
<body>
	<body id="wrapper">
		<div class="main">
			<div class="row1">
				<header><center><h1>Arlington Auto Car Rental!</h1></center></header>
			</div>
			
			<div class="row2">
				<nav>
					<div class="topnav">
						<a href="managerHome.jsp">Home</a>
						<a href="addCar.jsp">Add Car</a>
						<a href="managerViewReservation.jsp">Manage Reservation</a>
						<a href="managerUpdate.jsp" >Update User</a>
						<a href="LoginController?action=logout" >Logout</a>
					</div>
				</nav>
			</div>

		
			<div class="signupbo">
				<form name="profilForm" action="ManagerController" method="post">
					<fieldset>
						<legend>Profile Summary</legend>
						<table class="signup" cellpadding="10px">

							<tr>
								<td>UTA ID </td><td><input name="utaID" value="<c:out value='${user.utaID}'/>" ></td>
								<td> <input name="utaIDerror" size="35" value="<c:out value='${uerrorMsgs.utaIDMsg}'/>" type="text"  style ="background-color: white; color: red; border: none;" disabled> </td>
							</tr>
							<tr>
								<td>First Name</td><td><input name="firstName" value="<c:out value='${user.firstName}'/>"   ></td>
								<td> <input name="firstNameerror" size="35" value="<c:out value='${uerrorMsgs.firstNameMsg}'/>" type="text"  style ="background-color: white; color: red; border: none;"  disabled> </td>
							</tr>
							<tr>
								<td>Last Name</td><td><input name="lastName"  value="<c:out value='${user.lastName}'/>" ></td>
								<td> <input name="lastNameerror" size="35" value="<c:out value='${uerrorMsgs.lastNameMsg}'/>" type="text"  style ="background-color: white; color: red; border: none;x"  disabled> </td>
							</tr>
							<tr>
								<td>Email Address</td><td><input name="email"   value="<c:out value='${user.email}'/>" ></td>
								<td> <input name="emailerror" size="35" value="<c:out value='${uerrorMsgs.emailMsg}'/>" type="text"  style ="background-color: white; color: red; border: none;" disabled> </td>
							</tr>

							<tr>
								<td>Password</td><td><input type="password" name="pword" value=""  ></td>
								<td> <input name="pword" size="35" value="<c:out value='${uerrorMsgs.pwordMsg}'/>" type="text"  style ="background-color: white; color: red; border: none; "disabled> </td>
							</tr>
							<tr>
								<td>Confirm Password</td><td><input type="password" name="conpword" value=""   ></td>
							</tr>
				
							
							<tr>
							<input name="action" value="updateUser" type="hidden">
							<td width="150px"></td><td><input type="submit" value="Update Profile"></td>
						</tr>
						</table>

			</fieldset>
		</form>
	</div> 
</body>
</html>