<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Home</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link REL="stylesheet" type="text/css" href="GUI/css/MyCss.css">
</head>
<body id="wrapper">
	<div class="main">
		<div class="row1">
			<header><center><h1>Arlington Auto Car Rental!</h1></center></header>
		</div>
		
		<div class="row2">
				<nav>
					<div class="topnav">
						<a href="userHome.jsp">Home</a>
						<a href="userReservation.jsp">Reservation</a>
						<a href="userProfile.jsp" >Settings</a>
						<a href="LoginController?action=logout" >Logout</a>
					</div>
				</nav>
			</div>

	<div><h2>My Reservations</h2></div>

		<form form method="POST" action="UserController">
		<table class="signup1" border="1" cellpadding="10" >
			<thead class="reservationthead">
			
				<th>Reference Number</th>
				<th>Car Name</th>
				<th>Capacity</th>
				<th>Total Amount</th>
				<th>Pick-up Date</th>
			<th>Drop-off Date</th>
			<th>Booking Status</th>
			<thead>
			
			${items}
			
		</table>
		
		</form>
		<br>
		<br>
	</div>
	</body>
</html>