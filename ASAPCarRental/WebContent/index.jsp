<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">



<html lang="en">
<head>
	<title>Login</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" href="GUI/css/MyCss.css">

	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.2/jquery-ui.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function () {
			toggleFields(); 
			$("#userType").change(function () {
				toggleFields();
			});
		});

		function toggleFields() {
			if ($("#userType").val() == "user")
				$("#dynamicUser").show();
			else
				$("#dynamicUser").hide();
		}
	</script>

</head>
<body id="wrapper">
	<div class="main">
		<div class="row1">
			<header><center><h1>Arlington Auto Car Rental!</h1></center></header>
		</div>
		<nav>
			<div class="intro_topnav">
				<table class="titlebar" width="100%">
					<tr>
						<td><a href ="about.html">About Us</a></td>
						<form class="login" name="login" action="LoginController" method="post">
							<td align="right">
								<label id="username">Username:</label>
								<input name="username" value="" class="input100" type="text" placeholder="Username">
								<label id="password">Password:</label>
								<input name="password" value= "" class="input100" type="password" placeholder="Password">
							</td>
							<td><input type="submit" value="Login"></td></tr>
						</form>
					
					<tr>
						<td> <input readonly name="usernameerror" size="35" value="<c:out value='${lerrorMsgs.userNameMsg}'/>" type="text"
							style ="background-color: #25244D; color: red; border: none;"> </td>
						</tr><tr>
							<td> <input readonly name="passworderror" size="35" value="<c:out value='${lerrorMsgs.pwordMsg}'/>" type="text"
								style ="background-color: #25244D; color: red; border: none;"> </td>
								</tr>
								</table>
								</div>
								</nav>
								<div class="row">
									<div class="column1">
										<img src="GUI/car1.png" style="width:100%">
									</div>
								</div>
								<div class="column2">
									<div class="signupbox">
										<form name="registrationForm" action="RegistrationController" method="post">
											<fieldset>
												<legend>SignUp</legend>
												<table class="signup" cellpadding="10px">
													<tr>
														<td>User Type</td>
														<td>
															<select id="userType" name="userType">
																<option>Select Account Type</option>
																<option value="user">User</option>
																<option value="manager">Manager</option>
																<option value="admin">Admin</option>
															</select>
														</td>
													</tr>       
													<tr>
														<td>UTA ID </td><td><input name="utaID" value="<c:out value='${register.utaID}'/>" ></td>
														<td> <input name="utaIDerror" size="35" value="<c:out value='${errorMsgs.utaIDMsg}'/>" type="text"  style ="background-color: #DEF2F1; color: red; border: none;" readonly> </td>
													</tr>
													<tr>
														<td>First Name</td><td><input name="firstName" value="<c:out value='${register.firstName}'/>" ></td>
														<td> <input name="firstNameerror" size="35" value="<c:out value='${errorMsgs.firstNameMsg}'/>" type="text"  style ="background-color: #DEF2F1; color: red; border: none;"  readonly> </td>
													</tr>
													<tr>
														<td>Last Name</td><td><input name="lastName" value="<c:out value='${register.lastName}'/>" ></td>
														<td> <input name="lastNameerror" size="35" value="<c:out value='${errorMsgs.lastNameMsg}'/>" type="text"  style ="background-color: #DEF2F1; color: red; border: none;x"  readonly> </td>
													</tr>
													<tr>
														<td>Gender</td>
														<td>
															<input type="radio" name="male" value='${register.gender}'> Male<br>
															<input type="radio" name="female" value='${register.gender}'> Female
														</td>
													</tr>
													<tr>
														<td>Email Address</td><td><input name="email" value="<c:out value='${register.email}'/>" ></td>
														<td> <input name="emailerror" size="35" value="<c:out value='${errorMsgs.emailMsg}'/>" type="text"  style ="background-color: #DEF2F1; color: red; border: none;" readonly> </td>
													</tr>
													<tr>
														<td>User Name</td><td><input name="userName" value="<c:out value='${register.userName}'/>" ></td>
														<td> <input name="userNameerror" size="35" value="<c:out value='${errorMsgs.userNameMsg}'/>" type="text"  style ="background-color: #DEF2F1; color: red; border: none;" readonly> </td>
													</tr>
													<tr>
														<td>Password</td><td><input type="password" name="pword" value="" ></td>
														<td> <input name="pword" size="35" value="<c:out value='${errorMsgs.pwordMsg}'/>" type="text"  style ="background-color: #DEF2F1; color: red; border: none; "readonly> </td>
													</tr>
													<tr>
														<td>Confirm Password</td><td><input type="password" name="conpword" value="" ></td>
													</tr>
												</table>
												<div id="dynamicUser">
													<table class="signup" cellpadding="10px">
														<tr>
															<td>Age </td><td><input name="age" value="<c:out value='${register.age}'/>" ></td>
															<td> <input name="ageerror" size="35" value="<c:out value='${errorMsgs.ageMsg}'/>" type="text"  style ="background-color: #DEF2F1; color: red; border: none;"  disabled="disabled" maxlength="60"> </td>
														</tr>
														<tr>
															<td>Phone no. </td><td><input name="phone" value="<c:out value='${register.phone}'/>" ></td>
															<td> <input name="phoneerror" size="35" value="<c:out value='${errorMsgs.phoneMsg}'/>" type="text"  style ="background-color: #DEF2F1; color: red; border: none;"  disabled="disabled" maxlength="60"> </td>
														</tr>
														<tr>
															<td>Address</td><td><input name="address" size="35" value="<c:out value='${register.address}'/>" ></td>
														</tr>
														<tr>
															<td>State</td><td><input name="state" size="35" value="<c:out value='${register.state}'/>" ></td>
														</tr>
														<tr>
															<td>Zipcode</td><td><input name="zipcode" size="35" value="<c:out value='${register.zipcode}'/>" ></td>
															<td> <input name="zipcodeerror"  value="<c:out value='${errorMsgs.zipcodeMsg}'/>" type="text"  style ="background-color: #DEF2F1; color: red; border: none;"  disabled="disabled" maxlength="60"> </td>
														</tr>
														<tr>
															<td>AAC Member</td>
															<td>
																<input type="radio" name="aacTrue" value="true"> Yes<br>
																<input type="radio" name="aacFalse" value="false"> No
															</td>
														</tr>
														<td>License no.</td><td><input name="licNo" size="35" value="<c:out value='${register.licNo}'/>" ></td>
													</tr>
													<tr>
														<td>License Exp</td><td><input name="licExp" size="35" value="<c:out value='${register.licExp}'/>" ></td>
													</tr>
												</table>
											</div> 
											<table class="signup" cellpadding="10px">
												<tr>
													<td width="150px"></td><td><input type="submit" value="Register"></td>
												</tr>
											</table>			
										</fieldset>
									</form>
								</div>
							</body>
							</html>