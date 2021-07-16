<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>List Of Students</title>
<style>
h3 {
	color: blue;
	text-align: center;
}

h4 {
	color: black;
	text-align: center;
}

p {
	color: brown;
	text-align: center;
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
		<a href="facultyHomePage.jsp">Previous page</a><br />
		<!-- Display All Student Information-->
		<figure>
			<figcaption>
				<h3>DIVVLEARN SECONDARY SCHOOL</h3>
				<br />
			</figcaption>
			<label>SEARCH STUDENT : </label>
			<input type="text" id="myInput" onkeyup="studentDetails()"
				placeholder="ENTER ROLL NUMBER" title="Type in a roll number">
			<p id="message"></p>
			<table class="table" border="1" id="myDetails">

				<thead class="thead-dark">
					<tr>
						<th scope="col">S. No.</th>
						<th scope="col">STUDENT NAME</th>
						<th scope="col">ROLL NUMBER</th>
						<th scope="col">PERSONAL INFORMATION</th>
						<th scope="col">DROP OUT</th>
					</tr>
				</thead>
				<tbody id="listTable">


				</tbody>
			</table>
		</figure>
	</main>
	<script>
		function onLoadGetId(){
			let params = new URLSearchParams(window.location.search);
			let facultyId = params.get('facultyId');
			getAllStudents(facultyId);
			}
		function getAllStudents(facultyId) {
			let url ="getStudentsList/"+facultyId;
			axios.get(url).then(res=> {
				console.log(res.data);
				let lists=res.data;
				let i=0;
				let content="";
				for( let list of lists) {
					content+="<tr>";
					content+="<td>"+(++i)+"</td>";
					content+="<td>"+list.studentName+"</td>";
					content+="<td>"+list.studentRollNumber+"</td>";
					content+="<td><a href='studentInformation.jsp?studentRollNumber="+list.studentRollNumber+"'>VIEW</a></td>";
					content+="<td><button type='button' onclick=deleteRecord('"+ list.studentRollNumber +"') class='btn btn-primary'>DROP OUT</button></td>";
					content+="</tr>";		
				}		
				document.querySelector("#listTable").innerHTML=content;		
			});
		 }
		function studentDetails() {
				var input, filter, table, tr, td, i, txtValue;
				input = document.getElementById("myInput");
				filter = input.value.toUpperCase();
				table = document.getElementById("myDetails");
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
		function deleteRecord(id){
			let conform = confirm("Do you want to delete ?");
			alert("confirm");
			let url ="deleteStudent/"+ id;
			if(conform){
				axios.delete(url).then(res=>{
					let data = res.data;
					console.log(data.infoMessage);
					content=data.infoMessage;
					document.querySelector("#message").innerHTML= content; 	
				}).catch(err=>{
					 let data = err.response.data;
					content=data.errorMessage;
					document.querySelector("#message").innerHTML= content; 
					
				});
			}
		}
	</script>
</body>
</html>