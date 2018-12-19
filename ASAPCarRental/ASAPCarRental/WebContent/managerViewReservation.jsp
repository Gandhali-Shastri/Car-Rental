<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link REL="stylesheet" type="text/css" href="GUI/css/MyCss.css">

<title>Manage Reservation</title>
</head>
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
		
	</div>
	
	<form name="profileFor" action="ManagerController" method="post">
				<input name="action" value="checkReservation" type="hidden">
						<table cellpadding="10px">
						<tr>
								<td>Reservation Id</td><td><input name="reservationID" value="<c:out value='${res.reservationID}'/>"   ></td>
								<td> <input disabled name="firstNameerror" size="35" value="<c:out value='${rerrormsg.dropTimeMsg}'/>" type="text"  style ="background-color: white; color: red; border: none;"  readonly> </td>
							</tr>
						<tr>
							<input name="action" value="checkReservation" type="hidden">
							<td width="150px"></td><td><input type="submit" value="Search" name="checkReservation"></td>
						</tr>
						</table>
			</form>
		
		<div class="column22">
		<form action="ManagerController" method="post">
		<table class="signup1" border="1" cellpadding="10">
			<thead class="reservationthead">
			<th>Sr. No.</th>
			<th>User Name</th>
			<th>Reference Number</th>
			<th>Car Name</th>
			<th>Total Amount</th>
			</thead>
			${reserve}
		</table>
			
		<table class="signup1" border="1" cellpadding="10">
			<thead>
			<th>Pick-up Date</th>
			<th>Pick-up Time</th>
			<th>Drop-off Date</th>
			<th>Drop-off Time</th>
			<th>Status</th>
			</thead>
			${reserve1}
			</table>
			</form>
			<br>
		<br>		
		</div>

		
</body>
</html>