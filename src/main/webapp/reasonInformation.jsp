<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.time.LocalDate"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>APPLY</title>
<style>
form {
	text-align: center;
}

label {
	font-weight: bold;
	width: 200px;
	font-size: 16px;
}

h2 {
	color: blank;
	text-align: center;
}
</style>
</head>
<body>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.21.1/axios.min.js"
		integrity="sha512-bZS47S7sPOxkjU/4Bt0zrhEtWx0y0CRkhEp8IckzK+ltifIIE9EMIMTuT/mEzoIMewUINruDBIR/jJnbguonqQ=="
		crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	<jsp:include page="header.jsp"></jsp:include>
	<main class="container-fluid">
		<%
		String studentRollNumber = (String) session.getAttribute("LOGGED_IN_USER_NO");
		%>
		<a href="studentHomePage.jsp">Previous page</a><br /> <br />
		<h2>REASON INFORMATION FORM</h2>
		<br /> <br />
		<form onsubmit="studentAttendanceReason()" method="post">
			<label>STUDENT ROLL NUMBER : </label> <input type="text"
				name="rollNumber" id="rollNumber" value="<%=studentRollNumber%>"
				readonly required><br /> <br /> <label>DATE : </label> <input
				type="date" name="date" id="date" min="2021-01-01" max="2021-12-31"
				value="<%=LocalDate.now()%>" required> <br /> <br /> <label>TYPE
				: </label><br /> <label for="absent"> <input type="radio"
				id="absent" name="attendanceType" value="ABSENT" required>
				ABSENT
			</label> <br /> <label for="onDuty"> <input type="radio" id="onDuty"
				name="attendanceType" value="ONDUTY" required> ONDUTY
			</label> <br /> <br /> <label>FACULTY-IN-CHARGE(EMAIL-ID) : </label> <input
				type="email" name="facultyEmailId" id="facultyEmailId"
				placeholder="Enter StaffEmailId" required><br /> <br /> <label>REASON
				: </label><input type="text" placeholder="Enter your reason" id="reason"
				title="reason field should not be empty" required><br /> <br />
			<button type="submit" class="btn btn-primary">SEND</button>
			<br /> <br />
			<p id="message"></p>
		</form>
	</main>
	<script>
		function studentAttendanceReason(){
			event.preventDefault();
			let studentRollNumber=document.getElementById("rollNumber").value;
			let reasonDate=document.getElementById("date").value;
			let attendanceType="";
		  	if(document.getElementById('absent').checked) {   
			  	attendanceType= document.getElementById("absent").value;   
          	}     
        	else {   
        	  attendanceType = document.getElementById("onDuty").value;   
         	}
			let facultyEmailId=document.getElementById("facultyEmailId").value;
			let reason=document.getElementById("reason").value;
			let data={
					"studentRollNumber":studentRollNumber,
					"reasonDate":reasonDate,
					"attendanceType":attendanceType,
					"facultyEmailId":facultyEmailId,
					"reason":reason		
			}
			let url="insertReason";
			let content="";
			axios.post(url,data).then(res=>{
				let data = res.data;
				console.log(data.infoMessage);
				content+=data.infoMessage;
				document.querySelector("#message").innerHTML= content; 				
			}).catch(err=>{
				 let data = err.response.data;
				content+=data.errorMessage;
				document.querySelector("#message").innerHTML= content; 						
			});
		}
	</script>
</body>
</html>