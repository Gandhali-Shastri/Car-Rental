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

			<div>
				<font face="Arial"><h2 align="center">My Profile</h2></font>
			</div>

			<div class="signupbox">
				<form name="profileForm" action="UserController" method="post">
					<fieldset>
						<legend>Profile Summary</legend>
						<table class="signup" cellpadding="10px">

							<tr>
								<td>UTA ID </td><td><input name="utaID" style="background-color: white; border: none;" value="<c:out value='${user.utaID}'/>" ></td>
								<td> <input name="utaIDerror" size="35" value="<c:out value='${errorMsgs.utaIDMsg}'/>" type="text"  style ="background-color: #DEF2F1; color: red; border: none;" readonly> </td>
							</tr>
							<tr>
								<td>First Name</td><td><input name="firstName" value="<c:out value='${user.firstName}'/>" style="background-color: white; border: none;" ></td>
								<td> <input name="firstNameerror" size="35" value="<c:out value='${errorMsgs.firstNameMsg}'/>" type="text"  style ="background-color: #DEF2F1; color: red; border: none;"  readonly> </td>
							</tr>
							<tr>
								<td>Last Name</td><td><input name="lastName"style="background-color: white; border: none;" value="<c:out value='${user.lastName}'/>" ></td>
								<td> <input name="lastNameerror" size="35" value="<c:out value='${errorMsgs.lastNameMsg}'/>" type="text"  style ="background-color: #DEF2F1; color: red; border: none;x"  readonly> </td>
							</tr>
							<tr>
								<td>Email Address</td><td><input name="email" style="background-color: white; border: none;" value="<c:out value='${user.email}'/>" ></td>
								<td> <input name="emailerror" size="35" value="<c:out value='${errorMsgs.emailMsg}'/>" type="text"  style ="background-color: #DEF2F1; color: red; border: none;" readonly> </td>
							</tr>

							<tr>
								<td>Password</td><td><input type="password" name="pword" value="" style="background-color: white; border: none;"></td>
								<td> <input name="pword" size="35" value="<c:out value='${errorMsgs.pwordMsg}'/>" type="text"  style ="background-color: #DEF2F1; color: red; border: none; "readonly> </td>
							</tr>
							<tr>
								<td>Confirm Password</td><td><input type="password" name="conpword" value="" style="background-color: white; border: none;" ></td>
							</tr>
				
								<tr>
									<td>Age </td><td><input name="age" style="background-color: white; border: none;" value="<c:out value='${user.age}'/>" ></td>
									<td> <input name="ageerror" size="35" value="<c:out value='${errorMsgs.ageMsg}'/>" type="text"  style ="background-color: #DEF2F1; color: red; border: none;"  disabled="disabled" maxlength="60"> </td>
								</tr>
								<tr>
									<td>Phone no. </td><td><input name="phone" style="background-color: white; border: none;" value="<c:out value='${user.phone}'/>" ></td>
									<td> <input name="phoneerror" size="35" value="<c:out value='${errorMsgs.phoneMsg}'/>" type="text"  style ="background-color: #DEF2F1; color: red; border: none;"  disabled="disabled" maxlength="60"> </td>
								</tr>
								<tr>
									<td>Address</td><td><input name="address" style="background-color: white; border: none;" size="35" value="<c:out value='${user.address}'/>" ></td>
								</tr>
								<tr>
									<td>State</td><td><input name="state" size="35" style="background-color: white; border: none;" value="<c:out value='${user.state}'/>" ></td>
								</tr>
								<tr>
									<td>Zipcode</td><td><input name="zipcode" style="background-color: white; border: none;" size="35" value="<c:out value='${user.zipcode}'/>" ></td>
									<td> <input name="zipcodeerror"  value="<c:out value='${errorMsgs.zipcodeMsg}'/>" type="text"  style ="background-color: #DEF2F1; color: red; border: none;"  disabled="disabled" maxlength="60"> </td>
								</tr>
								<tr>
									<td>AAC Member</td>
									<td>
										<input type="radio" name="aacTrue" value="true"> Yes<br>
										<input type="radio" name="aacFalse" value="false"> No
									</td>
								</tr>
								<td>License no.</td><td><input name="licNo" size="35" style="background-color: #DEF2F1; border: none;" value="<c:out value='${user.licNo}'/>" ></td>
							</tr>
							<tr>
								<td>License Exp</td><td><input name="licExp" size="35" style="background-color: #DEF2F1; border: none;" value="<c:out value='${user.licExp}'/>" ></td>
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