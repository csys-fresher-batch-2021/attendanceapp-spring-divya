<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Faculty Details</title>
<%
String studentRollNumber = (String) session.getAttribute("LOGGED_IN_USER_NO");
%>
<style>
#facultyTable {
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
</style>
</head>
<body onload="onLoadGetId()">
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.21.1/axios.min.js"
		integrity="sha512-bZS47S7sPOxkjU/4Bt0zrhEtWx0y0CRkhEp8IckzK+ltifIIE9EMIMTuT/mEzoIMewUINruDBIR/jJnbguonqQ=="
		crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	<jsp:include page="header.jsp"></jsp:include>
	<main class="container-fluid">
		<a href="studentHomePage.jsp">Previous page</a><br /> <br />
		<!-- Display Attendance Incharge Details -->
		<figure>
			<figcaption>
				<p>CONTACT INFORMATION</p>
			</figcaption>
			<br />
			<table border="1" id="facultyTable" class="table">
				<thead class="thead-dark">
					<tr>
						<th scope="col"></th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</figure>
	</main>

	<script>
		function getFacultyData(studentRollNumber){
					let url ="getFacultyData/"+studentRollNumber;
					axios.get(url).then(res=> {
						console.log(res.data);
						let data=res.data;
						console.log(data);
						var tableData = '<thead class="thead-dark"><tr><th>INCHARGE</th><th>DETAILS</th></tr></thead>'
							tableData+= '<tbody>'
							    for(i = 0;i < data.length; i++){
						    	tableData+= '<tr>';
						    	tableData+= '<th> NAME </th>';
						    	tableData+= '<td>' + data[i].facultyName+ '</td>';
						    	tableData+= '</tr>';
						    	tableData+= '<tr>';
						    	tableData+= '<th> CLASS </th>';
						    	tableData+= '<td>' + data[i].facultyClass + '</td>';
						    	tableData+= '</tr>';
						    	tableData+= '<tr>';
						    	tableData+= '<th> EMAIL ID </th>';
						    	tableData+= '<td>' + data[i].facultyEmailId + '</td>';
						    	tableData+= '</tr>';
						    	tableData+= '<tr>';
						    	tableData+= '<th> MOBILE NUMBER </th>';
						    	tableData+= '<td>' + data[i].facultyMobileNumber + '</td>';
						    	
						    	tableData+= '</tr>';
							    }
							tableData+='</tbody>';
						    document.getElementById("facultyTable").innerHTML = tableData;				
					})
						
		 }
		function onLoadGetId(){
			let params = new URLSearchParams(window.location.search);
			let studentRollNumber = params.get('studentRollNumber');
			getFacultyData(studentRollNumber);
			}
	</script>
</body>
</html>

