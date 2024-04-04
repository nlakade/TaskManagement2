<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Dashboard Page</title>
<%@ include file="./components/common_cs_js.jsp"%>
</head>
<body>
	<%@ include file="./components/navbar.jsp"%>

	<%@ include file="./components/message.jsp"%>

	<div class="container-fluid mt-3 ml-5 mr-5">
		<a href="assigntasktoassigner"><button type="button"
				class="btn custom-bg">Assign Task To Assigner</button></a>
				
				<a href="getAllTasks"><button type="button"
				class="btn custom-bg mt-2">Check All Task</button></a>
	</div>


</body>
</html>

