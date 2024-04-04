<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Task Status Update By Assigner</title>
<%@ include file="./components/common_cs_js.jsp"%>
</head>
<body>
	<%@ include file="./components/navbar.jsp"%>
	<%
	Task task = taskDao.findById(Integer.parseInt((String) request.getAttribute("taskId"))).get();

	Assignee asgnee = assigneeDao.findById(task.getAssigneeId()).get();
	%>
	<div class="container-fluid">
		<div class="row mt-2">
			<div class="col-md-4 offset-md-4 admin">
				<div class="card">
					<%@ include file="./components/message.jsp"%>
					<div class="card-body px-5">
						<h3 class="text-center my-3">Update Task Status</h3>
						<form action="updateTaskAssigned" method="post">
							<input type="hidden" name="id" value="<%=task.getId()%>">
							<input type="hidden" name="assignerId"
								value="<%=task.getAssignerId()%>">
								<input type="hidden" name="assigneeId"
								value="<%=task.getAssigneeId()%>">
								 <input type="hidden"
								name="department" value="<%=task.getDepartment()%>">

							<div class="form-group">
								<label>Task Name</label> <input type="text" class="form-control"
									name="name" placeholder="Enter Task Name"
									value="<%=task.getName()%>" required readonly>
							</div>

							<div class="form-group">
								<label>Assignee Name</label> <input type="text"
									class="form-control" name="name"
									value="<%=asgnee.getFirstname() + " " + asgnee.getLastname()%>"
									required readonly>
							</div>

							<div class="form-group">
								<label>Task Description</label>
								<textarea style="height: 150px" class="form-control"
									name="description" placeholder="Enter Task Description here"
									required readonly><%=task.getDescription()%></textarea>
							</div>

							<div class="form-group">
								<input type="text" class="form-control" name="startDate"
									value="<%=task.getStartDate()%>" readonly required>
							</div>

							<div class="form-group">
								<input type="text" class="form-control" name="endDate"
									value="<%=task.getEndDate()%>" readonly required>
							</div>
							
							<div class="form-group">
								<input type="text" class="form-control" name="assigneeStatus"
									value="<%=task.getAssigneeStatus()%>" readonly required>
							</div>
							
							<div class="form-group">
								<label>Assignee Remark</label>
								<textarea style="height: 150px" class="form-control"
									name="assigneeRemark" placeholder="Enter Remark"
									required readonly><%=task.getAssigneeRemark()%></textarea>
							</div>

							<div class="form-group">
								<label for="email">Assigner Status</label> <select
									name="assignerStatus" class="form-control">
									<option value="0">Select Status</option>
									<%
									for (Constants.TaskStatus s : Constants.TaskStatus.values()) {
									%>
									<option value="<%=s.value()%>"><%=s.value()%></option>
									<%
									}
									%>
								</select>
							</div>
							
							<div class="form-group">
								<label>Assigner Remark</label>
								<textarea style="height: 150px" class="form-control"
									name="assignerRemark" placeholder="Enter Remark"
									required></textarea>
							</div>


							<div class="container text-center">
								<button class="btn custom-bg text-white">Update Task
									Status</button>
								<button type="button" class="btn btn-secondary"
									data-dismiss="modal">Close</button>
							</div>
						</form>
					</div>
				</div>


			</div>
		</div>
	</div>
</body>
</html>