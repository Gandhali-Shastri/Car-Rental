<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html lang="en">

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
						<a href="manageReservation.jsp">Reservation</a>
						<a href="userProfile.jsp" >Settings</a>
						<a href="index.jsp" >Logout</a>
					</div>
				</nav>
			</div>
			<div class="row3">	
				<div class="marquee">
					<marquee>
						<b> <i>Working hours: Mon to Fri --> 8am to 8pm, Sat --> 8am to 5pm, Sun --> noon to 5pm</i></b>
					</marquee>
				</div>	
			</div>
			<div class="row4">
				<form method="POST" action="UserController">
				<table class="search"  cellpadding="10px">
					<tr><div class="capacity">
						<td><label class="label">Capacity:</label></td>
						<td><input class="input-capacity" type="text" name="traveller" placeholder="Enter the maximum capacity"></td>
						<td> <input readonly name="caperror" size="35" value="<c:out value='${cerrormsg.capacityMsg}'/>" type="text"
							style ="background-color: white; color: red; border: none; "> </td>
					</div></tr>
					<tr><div class="pickup"  style="padding-left: 50px" style="padding-right: 10px">
						<td><label class="label">Pick-up date:</label></td>
						<td><input class="input-pickup" type="text" name="pickup" placeholder="mm/dd/yyyy" id="input-start">  </td>           
						<td> <input readonly name="pickerror" size="35" value="<c:out value='${rerrormsg.startDateMsg}'/>" type="text"
							style ="background-color: white; color: red; border: none; "> </td>
					</div> </tr>
					<tr>
					<div class="dropoff" >
						<td><label class="label">Drop-off date:</label></td>
						<td><input class="input-dropoff" type="text" name="dropoff" placeholder="mm/dd/yyyy" id="input-end"></td>
						<td> <input readonly name="droperror" size="35" value="<c:out value='${rerrormsg.endDateMsg}'/>" type="text"
							style ="background-color: white; color: red; border: none; "> </td>
					</div></tr>		
					<tr><div class="time">
						<td><label class="label">Pick-up time:</label></td>
						<td><select name="time-pickup">
							<option selected="selected" value="8">8:00</option>
							<option value="9"> 9:00</option><option value="10">10:00</option>
							<option value="11">11:00</option><option value="12">12:00</option>
							<option value="13">13:00</option><option value="14">14:00</option>
							<option value="15">15:00</option><option value="16">16:00</option>
							<option value="17">17:00</option><option value="18">18:00</option>
							<option value="19">19:00</option><option value="20">20:00</option>
							</select>  </td>              
					</div>	</tr>
					<tr>
					<div class="time">
						<td><label class="label">Drop-off time:</label></td>
						<td><select name="time-dropoff">
							<option selected="selected" value="8">8:00</option>
							<option value="9"> 9:00</option><option value="10">10:00</option>
							<option value="11">11:00</option><option value="12">12:00</option>
							<option value="13">13:00</option><option value="14">14:00</option>
							<option value="15">15:00</option><option value="16">16:00</option>
							<option value="17">17:00</option><option value="18">18:00</option>
							<option value="19">19:00</option><option value="20">20:00</option>
							</select>  </td>              
					</div>	</tr>
					<input name="action" value="carSearch" type="hidden">
					<tr><td width=20%></td><td><input class="btn-submit m-t-0" type="submit" value="search"></td></tr>
				</table>
				</form>
			</div>
			<div class="column1"> 
				<label class="label">Your search found:</label>
				<form action="UserController" method="post">
					<input name="action" value="makeresrvation" type="hidden"> 
					<table class="searchList" border="1" cellpadding="10" >
						<th>Car Name</th>
						<th>Capacity</th>
						<th>GPS</th>
						<th>OnStar</th>
						<th>SiriusXm</th>
						<c:forEach items="${cars}" var="item">
						<tr>
						<td><input type="text" name="cname" readonly value="<c:out value='${item.carType}'/>" ></td>
						<td><input type="text" name="cap" readonly value="<c:out value='${item.capacity}'/>" ></td>
						<td><input type="checkbox" checked="checked" name="gps"></td>
						<td><input type="checkbox" checked="checked" name="onstar"></td>
						<td><input type="checkbox" checked="checked" name="sirius"></td>
						<td><input type="submit" name="resrve" readonly value="Select"></td>
						</tr>
						</c:forEach>
						
					</table>
					</form>	
				</div>
		</div>	
	<footer>
		<p>Copyright &copy 2018 Arlington Auto Car Rental</p>
	</footer>
	</body>
</html>