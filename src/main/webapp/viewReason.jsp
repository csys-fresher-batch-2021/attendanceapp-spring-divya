<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.time.LocalDate"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>REASON DISPLAY</title>
<style>
label {
	font-weight: bold;
	font-size: 16px;
}

form {
	text-align: center;
}

h2 {
	color: black;
	text-align: center;
}

h4 {
	color: brown;
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
		<a href="facultyHomePage.jsp">Previous page</a><br />
		<!-- Display onduty or absent reasons-->
		<figure>
			<figcaption>
				<h2>REASONS FOR ABSENT AND ONDUTY</h2>
				<br />
			</figcaption>
			<label> SEARCH DATE : </label>
			<input type="text" id="myInput1" onkeyup="dateFunction()"
				placeholder="ENTER DATE" title="Type in a date">
			<br />
			<form onsubmit="reasonRemove()">
				REMOVE REASON : <input type="text" id="studentRollNumber"
					placeholder="ENTER ROLL NUMBER" required> DATE : <input
					type="date" id="reasonDate" min="2021-01-01" max="2021-12-31"
					value="<%=LocalDate.now()%>" required>
				<button class="button">REMOVE</button>
				<br /> <br />
				<p id="message"></p>
			</form>
			<table border="1" class="table" id="myTable">
				<thead class="thead-dark">

					<tr>
						<th scope="col">S.NO.</th>
						<th scope="col">STUDENT ROLL NUMBER</th>
						<th scope="col">DATE</th>
						<th scope="col">ATTENDANCE TYPE</th>
						<th scope="col">REASON</th>
					</tr>
				<tbody id="reasonTable">

				</tbody>
				<thead>
			</table>
		</figure>
	</main>
	<script>
	getAllStudents();
	function getAllStudents() {
		let url ="getReasonList";
		axios.get(url).then(res=> {
			console.log(res.data);
			let lists=res.data;
			let i=0;
			let content="";
			for( let list of lists) {
				content+="<tr>";
				content+="<td>"+(++i)+"</td>";
				content+="<td>"+list.studentRollNumber+"</td>";
				content+="<td>"+list.reasonDate+"</td>";
				content+="<td>"+list.attendanceType+"</td>";
				content+="<td>"+list.reason+"</td>";
				content+="</tr>";		
			}		
			document.querySelector("#reasonTable").innerHTML=content;		
		});
	 }
	
	function reasonRemove(){
		event.preventDefault();
		let studentRollNumber=document.getElementById("studentRollNumber").value;
		let reasonDate=document.getElementById("reasonDate").value;
		let url ="removeReason/"+studentRollNumber+","+reasonDate;
		let content="";
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
		function dateFunction() {
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
</body>
</html>