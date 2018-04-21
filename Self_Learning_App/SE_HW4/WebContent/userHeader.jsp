<!DOCTYPE html>
<html lang="en-US">
<head>
<meta charset="UTF-8" />
<title>User Home</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />

<link rel='stylesheet' href='css/bootstrap2.css' type='text/css'
	media='all' />

<script src="js/bootstrap.js"></script>
<script type='text/javascript' src='js/jquery.js'></script>

</head>
<body class="home blog">

	<!--Start Container-->
	<div>
		<!--Start Header Wrapper-->
		
			<!--Start Header-->
			<div class="header">
				<div class="col-md-6 col-sm-6">
				</div>
				<div class="col-md-2 col-sm-2"></div>
				<div class="col-md-4 col-sm-4">

					<div class="nav-collapse">
						<ul class="nav pull-right">
							<li class="dropdown"><a href="userHome.jsp">Logged in as: <%=session.getAttribute("username")%></a>
								</li>
						</ul>
					</div>
				</div>
				<div class="clear"></div>
				<div class="menu_wrapper col-md-12">
					<div id="MainNav">
						<div >
							<ul>
								<li><a href="userHome.jsp">Dashboard</a></li>
								<li><a href="add-question.jsp">Add Questions</a></li>
								<li><a href="ViewQuestionServlet">View Questions</a></li>
								<li><a href="update-profile.jsp">Update Profile</a></li>
								<li><a href="change-password.jsp">Change Password</a></li>
								<li><a href="LogoutServlet">Logout</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>



</body>
</html>
