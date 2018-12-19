<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link REL="stylesheet" type="text/css" href="GUI/css/MyCss.css">

	<title>Manager Home</title>
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

		<div class="row4">
			<form method="POST" action="ManagerController">
				<table class="search"  cellpadding="10px">

					<tr><div class="pickup"  style="padding-left: 50px" style="padding-right: 10px">
						<td><label class="label">Start date:</label></td>
						<td><input class="input-pickup" type="date" name="pickup" placeholder="mm/dd/yyyy" id="input-start">  </td>           
						<td> <input readonly name="pickerror" size="35" value="<c:out value='${rerrormsg.startDateMsg}'/>" type="text"
							style ="background-color: white; color: red; border: none; "> </td>
						</div> </tr>
						<tr>
							<div class="dropoff" >
								<td><label class="label">End date:</label></td>
								<td ><input class="input-dropoff" type="date" name="dropoff" placeholder="mm/dd/yyyy" id="input-end"></td>
								<td> <input readonly name="droperror" size="35" value="<c:out value='${rerrormsg.endDateMsg}'/>" type="text"
									style ="background-color: white; color: red; border: none; "> </td>
							</div>
						</tr>		

							<input name="action" value="carSearch" type="hidden">
							<tr><td width=20%></td><td><input class="btn-submit m-t-0" type="submit" value="search"></td></tr>
				</table>
			</form>


			<div class="column1"> 
				<label class="label">Cars Booked in the given range of dates:</label>
				<form action="UserController" method="post">
					<input name="action" value="makeresrvation" type="hidden"> 
					<table class="searchList" border="1" cellpadding="10" >
						<thead class="reservationthead">
							<th>Reservation ID</th>
							<th>Car Name</th>
							<th>Pick-up Date</th>
							<th>Pick-up Time</th>
							<th>Drop-off Date</th>
							<th>Drop-off Time</th>
							<th>Booking Status</th>
						</thead>
						<c:forEach items="${r}" var="item">
						<tr>
							<td><input type="text" name="reservationID" readonly value="<c:out value='${item.reservationID}'/>" ></td>
							<td><input type="text" name="cname" readonly value="<c:out value='${item.carID}'/>" ></td>
							<td><input type="text" name="pickDate" readonly value="<c:out value='${item.startDate}'/>" ></td>
							<td><input type="text" name="PickTime" readonly value="<c:out value='${item.pickUpTime}'/>" ></td>
							<td><input type="text" name="dropDate" readonly value="<c:out value='${item.endDate}'/>" ></td>
							<td><input type="text" name="dropTime" readonly value="<c:out value='${item.dropTime}'/>" ></td>
							<td><input type="text" name="booking" readonly value="<c:out value='${item.status}'/>" ></td>
						</tr>
						</c:forEach>

					</table>
				</form>	
						<br>
						<br>

				<label class="label">Cars Available in the given range of dates:</label>
				<form action="UserController" method="post">
					<input name="action" value="makeresrvation" type="hidden"> 
					<table class="searchList" border="1" cellpadding="10" >
						<thead class="reservationthead">
							<th>Car Name</th>
							<th>Capacity</th>
							<th>Weekday Price</th>
							<th>Weekend Price</th>
							<th>GPS</th>
							<th>OnStar</th>
							<th>SiriusXM</th>
						</thead>
						<c:forEach items="${cars}" var="item">
						<tr>
							<td><input type="text" name="cname" readonly value="<c:out value='${item.carType}'/>" ></td>
							<td><input type="text" name="cap" readonly value="<c:out value='${item.capacity}'/>" ></td>
							<td><input type="text" name="weekday" readonly value="<c:out value='${item.weekdayPrice}'/>" ></td>
							<td><input type="text" name="weekend" readonly value="<c:out value='${item.weekendPrice}'/>" ></td>
							<td><input type="text" name="gps" readonly value="<c:out value='${item.gpsPrice}'/>" ></td>
							<td><input type="text" name="onstar" readonly value="<c:out value='${item.onstarPrice}'/>" ></td>
							<td><input type="text" name="xm" readonly value="<c:out value='${item.siriusXMPrice}'/>" ></td>
						</tr>
						</c:forEach>

					</table>
				</form>	
			</div>
		</div>
		</div>	
	</body>
	</html>