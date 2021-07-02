<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>StudentInformation</title>
<style>
h3 {
	color: blue;
	text-align: center;
}
</style>
</head>
<body onload=onLoadGetId()>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.21.1/axios.min.js"
		integrity="sha512-bZS47S7sPOxkjU/4Bt0zrhEtWx0y0CRkhEp8IckzK+ltifIIE9EMIMTuT/mEzoIMewUINruDBIR/jJnbguonqQ=="
		crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	<jsp:include page="header.jsp"></jsp:include>
	<br />
	<main class="container-fluid">
		<a href="listOfStudents.jsp">Previous page</a><br />
		<!-- Display  Student Information-->
		<figure>
			<figcaption>
				<h3>DIVVLEARN STUDENT INFORMATION</h3>
				<br />
			</figcaption>	

			<table class="table" border="1">
				<thead class="thead-dark">
					<tr>
						<th scope="col">STUDENT</th>
						<th scope="col">INFORMATION</th>
					</tr>
				<tbody id="studentInformation">
				</tbody>
			</table>
			
		</figure>
	</main>
	<script>
	function onLoadGetId(){
		let params = new URLSearchParams(window.location.search);
		let studentRollNumber = params.get('studentRollNumber');
		getStudentInformation(studentRollNumber);
		}
	function getStudentInformation(studentRollNumber) {
		let url ="getStudentInformation/"+studentRollNumber;
		axios.get(url).then(res=> {
			console.log(res.data);
			let informations=res.data;
			let i=0;
			let content="";
			for( let information of informations) {
				content+="<tr>";
				content+="<th>NAME</th>";
				content+="<td>"+information.studentName+"</td>";
				content+="</tr>";
				content+="<tr>";
				content+="<th>ROLL NUMBER</th>";
				content+="<td>"+information.studentRollNumber+"</td>";
				content+="</tr>";
				content+="<tr>";
				content+="<th>FATHER NAME</th>";
				content+="<td>"+information.fatherName+"</td>";
				content+="</tr>";
				content+="<tr>";
				content+="<th>MOTHER NAME</th>";
				content+="<td>"+information.motherName+"</td>";
				content+="</tr>";
				content+="<tr>";
				content+="<th>EMAIL ID</th>";
				content+="<td>"+information.studentEmailId+"</td>";
				content+="</tr>";
				content+="<tr>";
				content+="<th>GENDER</th>";
				content+="<td>"+information.gender+"</td>";
				content+="</tr>";
				content+="<tr>";
				content+="<th>ADDRESS</th>";
				content+="<td>"+information.address+"</td>";
				content+="</tr>";
				content+="<tr>";
				content+="<th>CITY</th>";
				content+="<td>"+information.city+"</td>";
				content+="</tr>";
				content+="<tr>";
				content+="<th>PARENT OCCUPATION</th>";
				content+="<td>"+information.parentOccupation+"</td>";
				content+="</tr>";
				content+="<tr>";
				content+="<th>BLOOD GROUP</th>";
				content+="<td>"+information.studentBloodGroup+"</td>";
				content+="</tr>";
				content+="<tr>";
				content+="<th>STANDARD</th>";
				content+="<td>"+information.studentStandard+"</td>";
				content+="</tr>";
				content+="<tr>";
				content+="<th>PARENT MOBILE NUMBER</th>";
				content+="<td>"+information.parentMobileNumber+"</td>";
				content+="</tr>";
				content+="<tr>";
				content+="<th>DATE OF BIRTH</th>";
				content+="<td>"+information.dateOfBirth+"</td>";
				content+="</tr>";
			}		
			console.log(content);
			document.querySelector("#studentInformation").innerHTML=content;
			
				
		});
	 }
	</script>
</body>
</html>