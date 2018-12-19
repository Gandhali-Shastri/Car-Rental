<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
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
<body>
<div style="font-size: large;">
<font face="Arial">Registration</font>
</div>


<form name="registrationForm" action="RegistrationController" method="post">
<font face="arial">
<table>
<tr>
<td>UTA ID </td><td><input name="utaID" value="<c:out value='${register.utaID}'/>" ></td>
<td> <input name="utaIDerror"  value="<c:out value='${errorMsgs.utaIDMsg}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"> </td>
</tr>

<tr>
<td>First Name</td><td><input name="firstName" value="<c:out value='${register.firstName}'/>" ></td>
<td> <input name="firstNameerror"  value="<c:out value='${errorMsgs.firstNameMsg}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"> </td>
</tr>


<tr>
<td>Last Name</td><td><input name="lastName" value="<c:out value='${register.lastName}'/>" ></td>
<td> <input name="lastNameerror"  value="<c:out value='${errorMsgs.lastNameMsg}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"> </td>
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
<td> <input name="emailerror"  value="<c:out value='${errorMsgs.emailMsg}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"> </td>
</tr>
<tr>
<td>User Name</td><td><input name="userName" value="<c:out value='${register.userName}'/>" ></td>
<td> <input name="userNameerror"  value="<c:out value='${errorMsgs.userNameMsg}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"> </td>
</tr>
<tr>
<td>Password</td><td><input type="password" name="pword" value="" ></td>
<td> <input name="pword"  value="<c:out value='${errorMsgs.pwordMsg}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"> </td>
</tr>
<tr>
<td>Confirm Password</td><td><input type="password" name="conpword" value="" ></td>
</tr>


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
</table>
<div id="dynamicUser">
<table>
<tr>
<td>Phone no. </td><td><input name="phone" value="<c:out value='${register.phone}'/>" ></td>
<td> <input name="phoneerror"  value="<c:out value='${errorMsgs.phoneMsg}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"> </td>
</tr>
<tr>
<td>Address</td><td><input name="address" value="<c:out value='${register.address}'/>" ></td>
</tr>
<tr>
<td>State</td><td><input name="state" value="<c:out value='${register.state}'/>" ></td>
</tr>
<tr>
<td>Zipcode</td><td><input name="zipcode" value="<c:out value='${register.zipcode}'/>" ></td>
<td> <input name="zipcodeerror"  value="<c:out value='${errorMsgs.zipcodeMsg}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"> </td>
</tr>
<tr>
<td>AAC Member</td>
<td>
<input type="radio" name="aacTrue" value="true"> Yes<br>
<input type="radio" name="aacFalse" value="false"> No
</td>
</tr>

<td>License no.</td><td><input name="licNo" value="<c:out value='${register.licNo}'/>" ></td>
</tr>
<tr>
<td>License Exp</td><td><input name="licExp" value="<c:out value='${register.licExp}'/>" ></td>
</tr>
</table>
</div> 
<table>
<tr>
<td></td><td><input type="submit" value="Register"></td>
</tr>
</table>
</font> 
</form>
</body>
</html>