<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Faculty Login Page</title>
<style type="text/css">
body {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 14px;
}

.head {
	font-weight: bold;
	font-size: 25px;
	text-align: center;
}

label {
	font-weight: bold;
	width: 100px;
	font-size: 14px;
}

.box {
	border: #666666 solid 1px;
}

h1 {
	color: blank;
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

		<div class="head">
			<h1>DIVVLEARN SECONDARY SCHOOL</h1>
		</div>
		<br>

		<div style="width: 400px; height: 450px; border: solid 1px #333333;">
			<div style="background-color: #333333; color: #FFFFFF; padding: 3px;">
				<h2>FACULTY LOGIN PORTAL</h2>
			</div>
			<div style="margin: 30px">
				<!-- Faculty Login Page -->
				<form onsubmit="facultyLogin()" method="post">
					<label>NAME : </label><br /> <input type="text" name="facultyName"
						id="facultyName" placeholder="Enter Your Name" required><br />
					<br /> <label>EMAIL ID : </label><br /> <input type="text"
						name="facultyEmailId" id="facultyEmailId"
						placeholder="Enter EmailId" required><br /> <br /> <label>PASSWORD
						: </label><br /> <input type="password" name="facultyPassword"
						id="facultyPassword" placeholder="Enter Your Password" required><br />
					<br />
					<button class="btn btn-primary">LOGIN</button>
					<br /><br />
					<p id="message"></p>
				</form>
				<div style="font-size: 11px; color: #cc0000; margin-top: 10px"></div>

			</div>
		</div>
	</main>
	<script>
	function facultyLogin(){
		event.preventDefault();
		let facultyName =document.getElementById("facultyName").value;
		let facultyEmailId=document.getElementById("facultyEmailId").value;
		let facultyPassword=document.getElementById("facultyPassword").value;
		
		let data={
				"facultyName":facultyName,
				"facultyEmailId":facultyEmailId,
				"facultyPassword":facultyPassword,
						
		}
		let url="facultyLogin";
		content="";
		axios.post(url,data).then(res=>{
			console.log()
			console.log("Success");
			let data = res.data;
			console.log(data.infoMessage);
			content+=data.infoMessage;
			document.querySelector("#message").innerHTML= content; 
			setTimeout(function(){window.location="facultyHomePage.jsp";},2000);
			
			
}).catch(err=>{
	console.log("Error");
	 let data = err.response.data;
	content+=data.errorMessage;
	document.querySelector("#message").innerHTML= content; 
			
		});
		
}
	</script>
</body>
</html>