<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="GUI/css/MyCss.css">
	<title>My Profile</title>
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
						<a href="userHome.jsp">Home</a>
						<a href="UserController?action=viewReservation">Reservation</a>
						<a href="userProfile.jsp" >Settings</a>
						<a href="LoginController?action=logout" >Logout</a>
					</div>
				</nav>
			</div>
			
			<div>
				<font face="Arial"><h2 align="center">My Profile</h2></font>
			</div>

			<div class="signupox">
				<form name="pofileForm" action="UserController" method="post">
					<fieldset>
						<legend>Profile Summary</legend>
						<table class="signup" cellpadding="10px">

							<tr>
								<td>UTA ID </td><td><input name="utaID"   value="<c:out value='${user.utaID}'/>" ></td>
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
								<td> <input name="pwordError" size="35" value="<c:out value='${uerrorMsgs.pwordMsg}'/>" type="text"  style ="background-color: white; color: red; border: none; "disabled> </td>
							</tr>
							<tr>
								<td>Confirm Password</td><td><input type="password" name="conpword" value=""   ></td>
							</tr>
				
								<tr>
									<td>Age </td><td><input name="age"   value="<c:out value='${user.age}'/>" ></td>
									<td> <input name="ageerror" size="35" value="<c:out value='${uerrorMsgs.ageMsg}'/>" type="text"  style ="background-color: white; color: red; border: none;"  disabled="disabled" maxlength="60"> </td>
								</tr>
								<tr>
									<td>Phone no. </td><td><input name="phone"   value="<c:out value='${user.phone}'/>" ></td>
									<td> <input name="phoneerror" size="35" value="<c:out value='${uerrorMsgs.phoneMsg}'/>" type="text"  style ="background-color: white; color: red; border: none;"  disabled="disabled" maxlength="60"> </td>
								</tr>
								<tr>
									<td>Address</td><td><input name="address"   size="35" value="<c:out value='${user.address}'/>" ></td>
								</tr>
								<tr>
									<td>State</td><td><input name="state" size="35"   value="<c:out value='${user.state}'/>" ></td>
								</tr>
								<tr>
									<td>Zipcode</td><td><input name="zipcode"   size="35" value="<c:out value='${user.zipcode}'/>" ></td>
									<td> <input name="zipcodeerror"  value="<c:out value='${uerrorMsgs.zipcodeMsg}'/>" type="text"  style ="background-color: white; color: red; border: none;"  disabled="disabled" maxlength="60"> </td>
								</tr>
								<tr>
									<td>AAC Member</td>
									<td>
										<input type="radio" name="aacTrue" value="true"> Yes<br>
										<input type="radio" name="aacFalse" value="false"> No
									</td>
								</tr>
								<td>License no.</td><td><input name="licNo" size="35"  value="<c:out value='${user.licNo}'/>" ></td>
							</tr>
							<tr>
								<td>License Exp</td><td><input name="licExp" size="35"  value="<c:out value='${user.licExp}'/>" ></td>
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