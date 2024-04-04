<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Assign Task To Assigner</title>
<%@ include file="./components/common_cs_js.jsp"%>
</head>
<body>
	<%@ include file="./components/navbar.jsp"%>

	<%
	List<Assigner> assigners = null;
	assigners = (List<Assigner>) request.getAttribute("assigners");
	%>

	<div class="container-fluid">
		<div class="row mt-2">
			<div class="col-md-4 offset-md-4 admin">
				<div class="card">
					<%@ include file="./components/message.jsp"%>
					<div class="card-body px-5">
						<img src="resources/images/regis.jpeg"
							class="rounded mx-auto d-block" alt="img" height="90px"
							width="90px">
						<h3 class="text-center my-3">Department-Wise Task Assign</h3>
						<form action="getDepartmentWiseAssigners" method="post">

							<div class="form-group">
								<label for="email">Department</label> <select name="department"
									class="form-control">
									<option value="0">Select Department</option>
									<%
									for (Constants.Department department : Constants.Department.values()) {
									%>
									<option value="<%=department.value()%>"><%=department.value()%></option>
									<%
									}
									%>
								</select>
							</div>

							<div class="container text-center">
								<button class="btn custom-bg text-color">
									<b>Get Department Assigners</b>
								</button>
							</div>
						</form>

						<form action="assignTaskToAssigner" method="post">

							<div class="form-group">
								<label for="email">Assigners</label> <select name="assignerId"
									class="form-control">
									<option value="0">Select Assigners</option>
									<%
									if (assigners != null) {

										for (Assigner a : assigners) {
									%>
									<option value="<%=a.getId()%>"><%=a.getFirstname() + " " + a.getLastname()%></option>
									<%
									}
									}
									%>
								</select>
							</div>


							<div class="container text-center">
								<button class="btn custom-bg text-color">
									<b>Assign</b>
								</button>
							</div>

						</form>
					</div>
				</div>


			</div>
		</div>
	</div>
</body>
</html>

