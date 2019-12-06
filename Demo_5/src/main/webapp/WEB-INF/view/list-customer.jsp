
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="C"%>
<%@ page import="com.springhibernate.demo5.bean.CustomerBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List-Customer</title>
<!-- 
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js">
		</script>
		-->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/webjars/jquery/3.4.1/jquery.min.js">
	
</script>
<script>
	$(document).ready(function() {
		$("#Show").click(function() {
			$("#tableContent").toggle();
		});
		$("#AddCustomer").click(function() {
			$(location).attr('href', "addCustomer");
		});
	});
</script>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/style/css/style.css">
</head>
<body>
	<!-- 
		<h4>Hello Pravin</h4>
	<br>
	<br>
	-->
	<!-- <p>Value : ${pageContext.request.contextPath}/style/css/style.css</p><br><br> -->
	<div id="wrapper">
		<div id="header">
			<h2>CRM - Customer Relationship Manager</h2>
		</div>
	</div>
	<div id="container">
		<div id="content">
			<input type="button" id="AddCustomer" value="Add Customer" />&nbsp;&nbsp;
			<button id="Show">Show / Hide</button>
			&nbsp;&nbsp;
			<!--  <button id="Hide" >Hide</button> <br><br> -->
			<table id="tableContent">
				<tr>
					<th>ID</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>E-Mail</th>
					<th>Action</th>
				</tr>

				<C:forEach var="tempCustomer" items="${customerBList}">
					
					<C:url var="updateLink" value="/customer/showFormForUpdate">
						<C:param name="customerId" value="${tempCustomer.id}" />
						<C:param name="firstName" value="${tempCustomer.firstName}" />
					</C:url>
					
					<C:url var="deleteLink" value="/customer/delete">
						<C:param name="customerId" value="${tempCustomer.id}" />
						<C:param name="firstName" value="${tempCustomer.firstName}" />
					</C:url>
					
					<tr class="lcTableContent">
						<td>${tempCustomer.id}</td>
						<td>${tempCustomer.firstName}</td>
						<td>${tempCustomer.lastName}</td>
						<td>${tempCustomer.email}</td>
						<!-- Display the Update Link -->
						<td>
							<a href="${updateLink}">Update</a>
							|
							<a href="${deleteLink}"
							   onclick="if(!(confirm('Are you sure you want to delete this customer ???'))) return false">Delete</a>
						</td>
					</tr>
				</C:forEach>
			</table>
		</div>
	</div>
</body>
</html>