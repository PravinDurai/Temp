<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Add Customer</title>
		<script type="text/javascript" src="${pageContext.request.contextPath}/webjars/jquery/3.4.1/jquery.min.js">
		</script>
		<style type="text/css">
			.error{
				color:red
			}
		</style>
		<link type="text/css"
			  rel="stylesheet"
			  href="${pageContext.request.contextPath}/style/css/style.css">
		<link type="text/css"
			  rel="stylesheet"
			  href="${pageContext.request.contextPath}/style/css/add-customer-style.css">
	</head>
	<body>
		<div id="wrapper">
			<div id="header">
				<h2>CRM - Customer Relationship Manager</h2>
			</div> <br>
			<div id="form1Content">
				<h3>Save Customer</h3><br>
				<form:form action="newCustomer" modelAttribute="customerModel" method="post">
					
					<form:hidden path="id" />
					
					<div id="acTableContent">
						<table>
							<tr class="accTable">
								<td>
									<label id="aclFirstName" >First Name :</label>
								</td>
								<td class="actdi">
									<form:input placeholder="First Name" path="firstName" />
								</td>
							</tr>
							<tr>
								<td>
									<label></label>
								</td>
								<td>
									<form:errors path="firstName" cssClass="error" element="div" />	
								</td>
							</tr>
							<tr class="accTable">
								<td>
									<label id="aclLastName" >Last Name :</label>
								</td>
								<td class="actdi">
									<form:input placeholder="Last Name" path="lastName" />
								</td>
							</tr>
							<tr class="accTable">
								<td>
									<label></label>
								</td>
								<td>
									<form:errors path="lastName" cssClass="error" element="div" />
								</td>
							</tr>
							
							<tr class="accTable">
								<td>
									<label id="aclEMail" >E-Mail :</label>
								</td>
								<td class="actdi">
									<form:input placeholder="E-Mail" path="email" />
								</td>
							</tr>
							<tr>
								<td>
									<label></label>
								</td>
								<td>
									<form:errors path="email" cssClass="error" />
								</td>
							</tr>
							<tr>
								<td>
									<label></label>
								</td>
								<td>
									<input id="actdsubmit" type="submit" value="Save/Update" class="save" />
								</td>
							</tr>
						</table>
					</div>
				</form:form>
				<div style="clear;both;">
					<p>
						<a href="${pageContext.request.contextPath}/customer/list">Back To List Customer</a>
					</p>					
				</div>
			</div>
		</div>
	</body>
</html>
