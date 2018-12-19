<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link REL="stylesheet" type="text/css" href="GUI/css/MyCss.css">

<title>Revoke User</title>
</head>
<body id="wrapper">
	<div class="main">
		<div class="row1">
			<header><center><h1>Arlington Auto Car Rental!</h1></center></header>
		</div>
		
		<div class="row2">
					<nav>
					<div class="topnav">
						<a href="/AdminController?action=returnAllUsers">Home</a>
						<a href="revokeUser.jsp">Revoke User</a>
						<a href="adminUpdate.jsp" >Update user</a>
						<a href="LoginController?action=logout" >Logout</a>
					</div>
				</nav>
			</div>
			
		
	</div>
	
	<form name="profileFor" action="AdminController" method="post">
				<input name="action" value="revokeUser" type="hidden">
						<table cellpadding="10px">
						<tr>
								<td>Username</td><td><input name="username" value="<c:out value='${usr.userName}'/>"   ></td>
								<td> <input disabled name="firstNameerror" size="35" value="<c:out value='${revokeErrorMsg.userNameMsg}'/>" type="text"  style ="background-color: white; color: red; border: none;"  readonly> </td>
							</tr>
						<tr>
							<input name="action" value="search" type="hidden">
							<td width="150px"></td><td><input type="submit" value="Search" name="checkReservation"></td>
						</tr>
						</table>
			</form>
		
		<div class="column22">
		<table class="signup1" border="1" cellpadding="10" width="100%">
			<thead class="reservationthead">
			<th>Username</th>
			<th>UTA ID</th>
			<th>Email ID</th>
			<th>Revoke</th>
			</thead>
			<c:forEach items="${adminusers}" var="item">
						<tr>
						
						<td><input type="text" name="username" readonly value="<c:out value='${item.userName}'/>" ></td>
						<td><input type="text" name="utaid" readonly value="<c:out value='${item.utaID}'/>" ></td>
						<td><input type="text" name="email" readonly value="<c:out value='${item.email}'/>" ></td>
						<td><input type="submit" value="Revoke"></td>
						<input name="action" value="revokeUser" type="hidden">
						</tr>
				</c:forEach>
			</table>
			</div>

		
</body>
</html>