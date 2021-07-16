<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Faculty Home Page</title>
<style>
h3 {
	color: blue;
	text-align: center;
}
</style>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<main class="container-fluid">
		<!-- This page display the student home page -->
		<figure>
			<figcaption>Student Information</figcaption>
			<br />
			<%
			String facultyId = (String) session.getAttribute("LOGGED_IN_USER_ID");
			String facultyName = (String) session.getAttribute("LOGGED_IN_USER");
			out.println("<h3>WELCOME " + facultyName + "</h3><br/>");
			%>
			<br />
			<table class="table table-bordered">
				<tr>
					<th scope="col">STUDENT DETAILS</th>
					<th scope="col">
						<button type="button" class="btn btn-primary"
							onclick="window.location.href='http://localhost:9005/listOfStudents.jsp?facultyId=<%=facultyId%>'">
							STUDENTS LIST</button>
					</th>
				</tr>
				<tr>
					<th scope="col">REASON INFORMATION</th>
					<th scope="col">
						<button type="button" class="btn btn-primary"
							onclick="window.location.href='http://localhost:9005/viewReason.jsp'">REASON</button>
						<button type="button" class="btn btn-primary"
							onclick="window.location.href='http://localhost:9005/reasonInformationAdd.jsp'">ADD</button>
					</th>
				</tr>
				<tr>
					<th scope="col">REPORTS</th>
					<th scope="col">
						<button type="button" class="btn btn-primary"
							onclick="window.location.href='http://localhost:9005/absentReport.jsp?facultyId=<%=facultyId%>'">ABSENT
							REPORT</button>
						<button type="button" class="btn btn-primary"
							onclick="window.location.href='http://localhost:9005/onDutyReport.jsp?facultyId=<%=facultyId%>'">ONDUTY
							REPORT</button>
					</th>
				</tr>
			</table>
		</figure>
	</main>
</body>
</html>