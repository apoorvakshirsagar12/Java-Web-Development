<%@ page
	import="com.mvc.controller.*, com.mvc.bean.*, java.util.*"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en-US">
<head>
<meta charset="UTF-8" />
<title>Add New</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />

<link rel='stylesheet'
	href='https://fonts.googleapis.com/css?family=Poppins%3A400%2C500%2C600%2C700%2C300&#038;ver=4.8.3'
	type='text/css' media='all' />
<link rel='stylesheet'
	href='https://fonts.googleapis.com/css?family=Montserrat%3A400%2C700&#038;ver=4.8.3'
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

		<div class="row home_content_wrapper">
			<div class="feature_content col-md-12">
			<h5 style="color: red;">
						 ${msg1} 
					</h5>
			
				<div class="two_col-div row">
				<div><h4 style="color: red;">
						 ${msg1} 
					</h4></div>
					<div style="width: 90%; margin-right: 5%; margin-left: 5%; text-align: center;">											
							<div id="questions">
								<a href="ClearHistoryServlet" class="btn btn-sm btn-primary">Clear History</a>				
								<table class="table table-striped table-list" style="border:5px;">
									<thead>
               							<tr>
                						<th></th>
                  						<th>ID</th>
                  						<th>Question</th>
                  						<th>Status</th>
                  						<th>Action</th>
                						</tr>
              						</thead>	
									<tbody>
									<%
										ArrayList<QuestionBean> questions = (ArrayList<QuestionBean>)request.getSession().getAttribute("all_questions");
        								int loopIndex = -1;
          								for (QuestionBean question : questions){
          								loopIndex++;
       								%>
									<%
									if(question.getStatus().equalsIgnoreCase("solved"))
									{
									%>
									<tr style="text-align:left;">
									<td><input type="checkbox" checked="checked" style='width:20px; height:20px;' id="chk" onclick="return false;"></td>
									<td><%=question.getQues_id()%></td>
									<td><%=question.getQues_name()%></td>
									<td><%=question.getStatus()%></td>
									<td><a href="#" class="btn btn-sm btn-primary" onclick="return false;">Solve</a></td>
									</tr>
									<%
									}
									else if(question.getStatus().equalsIgnoreCase("unsolved"))
									{
									%>
									<tr style="text-align:left;">
									<td><input type="checkbox" style='width:20px; height:20px;' id="chk" onclick="return false;"></td>
									<td><%=question.getQues_id()%></td>
									<td><%=question.getQues_name()%></td>
									<td><%=question.getStatus()%></td>
									<td>
									<a href="SolveQuestionServlet?qid=<%=question.getQues_id()%>" class="btn btn-sm btn-primary" 
									onclick="check(<%=question.getQues_id() %>);">Solve</a>
									</td>
									</tr>
									<%
									}
          						}
									%>
									</tbody>
							
								</table>
							</div>
            		</div>

			</div>

			</div>
		</div>
	</div>
<script type="text/javascript">
function check(q)
{
	console.log(q);
}
</script>
</body>
</html>
