<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>ONDUTY REPORT</title>
<style>
label {
	font-weight: bold;
	font-size: 16px;
}

table {
	background-color: #AAB7B8;
	font-weight: bold;
	border: none;
}

p {
	background-color: pink;
	font-weight: bolder;
	padding: 5px;
	text-align: center;
	font-size: 15px;
}

h2 {
	color: black;
	text-align: center;
}

h3 {
	color: black;
	text-align: center;
}

h4 {
	color: black;
	text-align: center;
}
</style>
</head>
<body style="background-color: pink" onload="onLoadGetId()">
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.21.1/axios.min.js"
		integrity="sha512-bZS47S7sPOxkjU/4Bt0zrhEtWx0y0CRkhEp8IckzK+ltifIIE9EMIMTuT/mEzoIMewUINruDBIR/jJnbguonqQ=="
		crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	<jsp:include page="header.jsp"></jsp:include>
	<main class="container-fluid">
		<a href="facultyHomePage.jsp">Previous page</a><br />
		<!-- Display Absent Report -->
		<figure>
			<figcaption>
				<h2>DIVVLEARN SECONDARY SCHOOL</h2>
				<br />
			</figcaption>
			<strong>DOWNLOAD REPORT : </strong>
			<button onclick="window.print()">DOWNLOAD</button>
			<label>SEARCH STUDENT : </label>
			<input type="text" id="myInput" onkeyup="studentOnDuty()"
				placeholder="ENTER ROLL NUMBER" title="Type in a roll number">
			<label> SEARCH DATE : </label>
			<input type="text" id="myInput1" onkeyup="dateOnDuty()"
				placeholder="ENTER DATE" title="Type in a date">
			<br />
			<table border="1" class="table">
				<thead class="thead-dark">
					<tr>
						<td colspan="6"><h3>ABSENT REPORT</h3></td>
					</tr>
					<tr>
						<th scope="col" colspan="4" style="text-align: center">FACULTY-IN-CHARGE
							DETAILS</th>
					</tr>
					<tr>
						<th scope="col">NAME</th>
						<th scope="col">CLASS</th>
						<th scope="col">EMAIL ID</th>
						<th scope="col">MOBILE NUMBER</th>
					</tr>
				</thead>
				<tbody id="facultyTable">
				</tbody>
			</table>
			<table border="1" class="table" id="myTable">
				<thead class="thead-dark">
					<tr>
						<th scope="col">STUDENT ROLL NUMBER</th>
						<th scope="col">STUDENT NAME</th>
						<th scope="col">ATTENDANCE DATE</th>
						<th scope="col">ATTENDANCE TYPE</th>
						<th scope="col">REASON</th>
						<th scope="col">PARENT MOBILE NUMBER</th>
					</tr>
				</thead>
				<tbody id="onDutyTable">
				</tbody>
			</table>
			<script>
			function onLoadGetId(){
				let params = new URLSearchParams(window.location.search);
				let facultyId = params.get('facultyId');
				getFacultyDetails(facultyId);
				getReasonsDetails(facultyId);
				}
			function getFacultyDetails(facultyId) {
				let url ="getInchargeReport/"+facultyId;
				axios.get(url).then(res=> {
					console.log(res.data);
					let lists=res.data;
					let i=0;
					let content="";
					for( let list of lists) {
						content+="<tr>";
						content+="<td>"+list.facultyName+"</td>";
						content+="<td>"+list.facultyClass+"</td>";
						content+="<td>"+list.facultyEmailId+"</td>";
						content+="<td>"+list.facultyMobileNumber+"</td>";
						content+="</tr>";		
					}		
					document.querySelector("#facultyTable").innerHTML=content;		
				});
			 }
			function getReasonsDetails(facultyId) {
				let url ="getReasonOnDuty/"+facultyId;
				axios.get(url).then(res=> {
					console.log(res.data);
					let details=res.data;
					let i=0;
					let content="";
					for( let onduty of details) {
						content+="<tr>";
						content+="<td>"+onduty.studentRollNumber+"</td>";
						content+="<td>"+onduty.studentName+"</td>";
						content+="<td>"+onduty.reasonDate+"</td>";
						content+="<td>"+onduty.attendanceType+"</td>";
						content+="<td>"+onduty.reason+"</td>";
						content+="<td>"+onduty.parentMobileNumber+"</td>";
						content+="</tr>";
					}		
					document.querySelector("#onDutyTable").innerHTML=content;		
				});
			 }
			function studentOnDuty() {
					var input, filter, table, tr, td, i, txtValue;
					input = document.getElementById("myInput");
					filter = input.value.toUpperCase();
					table = document.getElementById("myTable");
					tr = table.getElementsByTagName("tr");
					for (i = 0; i < tr.length; i++) {
						td = tr[i].getElementsByTagName("td")[0];
						if (td) {
							txtValue = td.textContent || td.innerText;
							if (txtValue.toUpperCase().indexOf(filter) > -1) {
								tr[i].style.display = "";
							} else {
								tr[i].style.display = "none";
							}
						}
					}
				}
				function dateOnDuty() {
					var input, filter, table, tr, td, i, txtValue;
					input = document.getElementById("myInput1");
					filter = input.value.toUpperCase();
					table = document.getElementById("myTable");
					tr = table.getElementsByTagName("tr");
					for (i = 0; i < tr.length; i++) {
						td = tr[i].getElementsByTagName("td")[2];
						if (td) {
							txtValue = td.textContent || td.innerText;
							if (txtValue.toUpperCase().indexOf(filter) > -1) {
								tr[i].style.display = "";
							} else {
								tr[i].style.display = "none";
							}
						}
					}
				}
			</script>
		</figure>
	</main>
</body>
</html>

