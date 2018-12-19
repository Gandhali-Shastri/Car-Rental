<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
						<a href="/AdminController?action=returnAllUsers">Home</a>
						<a href="revokeUser.jsp">Revoke User</a>
						<a href="adminUpdate.jsp" >Update user</a>
						<a href="LoginController?action=logout" >Logout</a>
					</div>
				</nav>
			</div>
			
			<div class="column22">
		<table class="signup1" border="1" cellpadding="10" >
			<thead class="reservationthead">
			<th>User Name</th>
			<th>User Role</th>
			<th>Status</th>
			
		
			</thead>
			<c:forEach items="${adminuser}" var="item">
						<tr>
						<td><input type="text" name="uname" readonly value="<c:out value='${item.userName}'/>" ></td>
						<td><input type="text" name="role" readonly value="<c:out value='${item.userRole}'/>" ></td>
						<td><input type="text" name="status" readonly value="<c:out value='${item.status}'/>" ></td>
						</tr>
						</c:forEach>
			</table>
		
		</div>
	</div>
</body>
</html>