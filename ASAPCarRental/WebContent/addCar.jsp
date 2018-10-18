<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link REL="stylesheet" type="text/css" href="GUI/css/MyCss.css">
<title>Add Car</title>
</head>
<body id="wrapper">
	<div class="main">
		<div class="row1">
			<header><center><h1>Arlington Auto Car Rental!</h1></center></header>
		</div>
		
		<div class="row2">
				<nav>
					<div class="topnav">
						<a href="./home.html">Home</a>
						<a href="./Reservations.html">View Reservation</a>
						<a href="./Settings.html" >Settings</a>
						<a href="./home.html" >Logout</a>
					</div>
				</nav>
		</div>
	
	<div class="signupbox">
		<form name="addForm" action="ManagerController" method="post">
			<fieldset>
				<legend>Add Car</legend>
				<table class="signup" cellpadding="10px">   

					<tr>
						<td>Car name</td><td><input name="carName" value="<c:out value='${addcar.carname}'/>" ></td>
						<td> <input name="carnameerr"  value="<c:out value='${c.carnameMsg}'/>" type="text"  style ="background-color: #DEF2F1; color: red; border: none;"> </td>
					</tr>
					<tr>
						<td>Capacity</td><td><input name="capacity" value="<c:out value='${addcar.capacity}'/>" ></td>
						<td> <input name="capacityerr"  value="<c:out value='${c.capacityMsg}'/>" type="text"  style ="background-color: #DEF2F1; color: red; border: none;"> </td>
					</tr>
					<tr>
						<td>Weekday</td><td><input name="weekday" value="<c:out value='${addcar.weekday}'/>" ></td>
						<td> <input name="weekdayerr"  value="<c:out value='${c.weekdayMsg}'/>" type="text"  style ="background-color: #DEF2F1; color: red; border: none;"> </td>
					</tr>
					<tr>
						<td>Weekend</td><td><input name="weekend" value="<c:out value='${addcar.weekend}'/>" ></td>
						<td> <input name="weekenderr"  value="<c:out value='${c.weekendMsg}'/>" type="text"  style ="background-color: #DEF2F1; color: red; border: none;"> </td>
					</tr>
					<tr>
						<td>Week</td><td><input name="week" value="<c:out value='${addcar.week}'/>" ></td>
						<td> <input name="weekerr"  value="<c:out value='${c.weekeMsg}'/>" type="text"  style ="background-color: #DEF2F1; color: red; border: none;"> </td>
					</tr>
					<tr>
						<td>GPS</td><td><input name="gps" value="<c:out value='${addcar.gps}'/>" ></td>
						<td> <input name="gpserr"  value="<c:out value='${c.gpsMsg}'/>" type="text"  style ="background-color: #DEF2F1; color: red; border: none;"> </td>
					</tr>
					<tr>
						<td>On Star</td><td><input name="onstar" value="<c:out value='${addcar.onstar}'/>" ></td>
						<td> <input name="onstarerr"  value="<c:out value='${c.onstarMsg}'/>" type="text"  style ="background-color: #DEF2F1; color: red; border: none;"> </td>
					</tr>
					<tr>
						<td>SiriusXM</td><td><input name="sirius" value="<c:out value='${addcar.sirius}'/>" ></td>
						<td> <input name="siriuserr"  value="<c:out value='${c.siriusMsg}'/>" type="text"  style ="background-color: #DEF2F1; color: red; border: none;"> </td>
					</tr>
					<tr><td><input type="submit" value="Add Car"></td></tr>
				</table>
				<input name="action" value="addCar" type="hidden">
			</fieldset>
		</form>	
	</div>
</div>
</body>
</html>