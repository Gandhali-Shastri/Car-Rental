<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="GUI/css/MyCss.css">
<title>Payment</title>
</head>

<body id="wrapper">
	<div class="main">
		<div class="row1">
			<header><center><h1>Arlington Auto Car Rental!</h1></center></header>
		</div>
	</div>
	<div class="paymentGateway"><center>
		<form name="paymentForm" action="UserController" method="post">
			<fieldset>
				<legend><center>Make Payment</center></legend>
				<table class="payment" cellpadding="10px" style="border: 1px solid black">

					<tr bgcolor="#DEF2F1">
						<td>UTA ID </td>
						<td><input name="utaID" value="<c:out value='${user.utaID}'/>" readonly  type="text" style ="background-color: #DEF2F1; border: none;"></td>
					</tr>
					<tr bgcolor="#DEF2F1">
						<td>First Name</td><td><input name="firstName" value="<c:out value='${user.firstName}'/>" readonly  type="text" style ="background-color: #DEF2F1; border: none;"></td>
					</tr>
					<tr bgcolor="#DEF2F1">
					<td>Last Name</td><td><input name="lastName" value="<c:out value='${user.lastName}'/>" readonly   type="text" style ="background-color: #DEF2F1; border: none;"></td>
					</tr>	
					<tr bgcolor="#DEF2F1">
						<td>Email Address</td><td><input name="email" value="<c:out value='${user.email}'/>" readonly  type="text" style ="background-color: #DEF2F1; border: none;"></td>
					</tr>

					<tr bgcolor="#DEF2F1">
						<td>Tax (8.25%)</td><td><input name="tax" value="<c:out value='${reservation.tax}'/>" readonly  type="text" style ="background-color: #DEF2F1; border: none;"> </td>
					</tr>
					<tr bgcolor="#DEF2F1">
						<td>Discount (10%)</td><td><input name="discount" value="<c:out value='${reservation.discount}'/>" readonly  type="text" style ="background-color: #DEF2F1; border: none;"> </td>
					</tr>
					<tr bgcolor="#DEF2F1">
						<td>Total Amount</td><td><input name="Tamount" value="<c:out value='${reservation.totalCost}'/>" readonly  type="text" style ="background-color: #DEF2F1; border: none;"> </td>
					</tr>
					<tr style="border: 1px solid black">
					<td >Enter your Card details below:</td>
					</tr>
					<tr>
						<td>Card type</td>
						<td>
							<select name="cardType">
							<option value="Visa" selected="selected">VISA</option>
							<option value="Master Card"> Master Card</option>
							<option value="American Express"> American Express</option>
							<option value="Discover"> Discover</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>Card Number</td><td><input name="cardnum"> </td>
						<td> <input readonly disabled name="cardnum" size="35" value="<c:out value='${perrormsg.creditCardNumberMsg}'/>" type="text"
							style ="background-color: white; color: red; border: none; "> </td>
					</tr>
					<tr>
						<td>Name on Card</td><td><input name="cardname"> </td>
						<td> <input readonly disabled name="cardname" size="35" value="<c:out value='${perrormsg.nameOnCardMsg}'/>" type="text"
							style ="background-color: white; color: red; border: none; "> </td>
					</tr>
					<tr>
						<td>CVV Number</td><td><input name="cvv"> </td>
						<td> <input readonly disabled name="cvv" size="35" value="<c:out value='${perrormsg.CVVMsg}'/>" type="text"
							style ="background-color: white; color: red; border: none; "> </td>
					</tr>
					</table>
			
			<table>
				<tr>
					<td width="45px"><td><input type="submit" value="Pay"></td>
					<td width="25px"></td><td><a href="userHome.jsp" ><-- Go Back</a></td>
				</tr>
				<input name="action" value="confirmPay" type="hidden">
			</table>			
		</fieldset>
	</form>
	</div> 
</body>
</html>