<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en-US">
<head>
<meta charset="UTF-8" />
<title>StarLabs: Admin</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />

<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Poppins%3A400%2C500%2C600%2C700%2C300&#038;ver=4.8.3'
	type='text/css' media='all' />
<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Montserrat%3A400%2C700&#038;ver=4.8.3'
	type='text/css' media='all' />


</head>

<body class="home blog">

	<%
		if (session.getAttribute("username") == null) {
			response.sendRedirect("Login.jsp");
		}
	%>
	<div class="body-content container">
		<jsp:include page="./userHeader.jsp" />

			
				<div><h5 style="color: red;">
						 ${msg1} 
					</h5></div>
					
						
					<form action="ChangePasswordServlet" method="post">
						<div class="login-fields">
							<div class="field">
							<label for="old_passwd">Current Password</label> <input type="password" class="login username-field" name="old_passwd" 
									 required />
							</div>	
							<div class="field">
							<label for="new_passwd">New Password</label> <input type="password" class="login username-field" name="new_passwd" 
									 required />
							</div>
							<div class="field">
							<label for="retype_passwd">Retype Password</label> <input type="password" class="login username-field" name="retype_passwd" 
									 required />
							</div>	
							<div class="login-actions" style="margin-top: 15px; margin-bottom: 0px;">
									<a href="change-password.jsp" class="btn btn-sm btn-primary">Reset</a>
									<button type="submit" class="btn btn-success" value="submit">Submit</button>
							</div>

						</div>
					</form>
					</div>


			<c:remove var="message" scope="session" />

	

</body>
</html>
