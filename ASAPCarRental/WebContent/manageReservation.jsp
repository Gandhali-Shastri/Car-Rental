<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
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

	<div><h2 align="center">My Reservations</h2></div>
		<table class="signup1" border="1" cellpadding="10" >
			<thead class="reservationthead">
			<th>Reference Number</th>
			<th>Car Name</th>
			<th>Capacity</th>
			<th>Total Amount</th>
			<th>Pick-up Date</th>
			<th>Pick-up Time</th>
			<th>Drop-off Date</th>
			<th>Drop-off Time</th>
			<th>Booking Status</th>
			</thead>
			<tr>
				<td>1</td>
				<td>Smart</td>
				<td>1</td>
				<td>$101.75</td>
				<td>10/02/2018</td>
				<td>8:00</td>
				<td>10/03/2018</td>
				<td>8:00</td>
				<!-- if status active only then enable this button -->
				<td><input type="submit" value="Cancel"></td>

			</tr>
			<tr>
				<td>2</td>
				<td>Economy</td>
				<td>3</td>
				<td>$101.75</td>
				<td>10/02/2018</td>
				<td>8:00</td>
				<td>10/03/2018</td>
				<td>8:00</td>
				<!-- if status active only then enable this button -->
				<td><input type="submit" value="Cancel"></td>
			</tr>
		</table>	
	</div>
	</body>
</html>