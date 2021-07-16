<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Student Registration</title>
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
		<br />

		<button class="btn btn-link"
			onclick="window.location.href='http://localhost:9005/index.jsp'">BACK</button>
		<h2>REGISTRATION FORM</h2>
		<br /> <br />
		<!--  Get the StudentDetails -->
		<form onsubmit="registerData()" method="post">
			<label>ROLL NUMBER : </label> <input type="text"
				name="studentRollNumber" id="studentRollNumber"
				placeholder="Enter Your Roll Number" required autofocus><br />
			<br /> <label> NAME : </label> <input type="text" name="studentName"
				id="studentName" placeholder="Enter Your Name" required><br />
			<br /> <label>FATHER NAME : </label> <input type="text"
				name="fatherName" id="fatherName"
				placeholder="Enter Your Father Name" required><br /> <br />
			<label>MOTHER NAME : </label> <input type="text" name="motherName"
				id="motherName" placeholder="Enter Your Mother Name" required><br />
			<br /> <label>EMAIL-ID : </label> <input type="email"
				name="studentEmailId" id="studentEmailId"
				placeholder="Enter Your EmailId" required><br /> <br /> <label>PASSWORD
				: </label> <input type="password" name="studentPassword"
				id="studentPassword" placeholder="Enter Your Password" required><br />
			<br /> <label>GENDER : </label><br /> <label for="male"> <input
				type="radio" id="male" name="gender" value="MALE" required>
				Male
			</label> <label for="female"> <input type="radio" id="female"
				name="gender" value="FEMALE" required> Female
			</label> <label for="others"> <input type="radio" id="others"
				name="gender" value="OTHERS" required> Others
			</label> <br /> <br /> <label>ADDRESS : </label> <input type="text"
				name="studentAddress" id="studentAddress"
				placeholder="Enter Your Address" required><br /> <br /> <label>CITY
				: </label> <input type="text" name="studentCity" id="studentCity"
				placeholder="Enter Your City" required><br /> <br /> <label>OCCUPATION(PARENTS)
				: </label> <input type="text" name="occupation" id="occupation"
				placeholder="Enter Your Parents Occupation" required><br />
			<br /> <label>BLOOD GROUP : </label> <input type="text"
				name="studentBloodGroup" id="studentBloodGroup"
				placeholder="Enter Your Blood-Group" required><br /> <br />
			<label>STANDARD : </label> <input type="text" name="studentStandard"
				id="studentStandard" placeholder="Enter Your Standard" required><br />
			<br /> <label>FACULTY-INCHARGE(EMAIL-ID) : </label> <input
				type="email" name="facultyEmailId" id="facultyEmailId"
				placeholder="Enter Your Incharge Id" required><br /> <br />
			<label> PARENT MOBILE NUMBER:</label> <input type="number"
				name="parentMobileNumber" id="parentMobileNumber"
				placeholder="Enter Mobile Number"><br /> <br /> <label>DATE
				OF BIRTH: </label> <input type="date" name="dateOfBirth" id="dateOfBirth"
				min="2004-01-01" max="2016-01-01" placeholder="YYYY-MM-DD" required><br />
			<br />
			<button class="btn btn-primary">SUBMIT</button>
			<button type="reset" class="btn btn-secondary">RESET</button>
			<br /> <br />
			<p id="message"></p>
		</form>
	</main>
	<script>
		function registerData(){
			event.preventDefault();
			let studentRollNumber=document.getElementById("studentRollNumber").value;
			let studentName=document.getElementById("studentName").value;
			let fatherName=document.getElementById("fatherName").value;
			let motherName=document.getElementById("motherName").value;
			let studentEmailId=document.getElementById("studentEmailId").value;
			let studentPassword=document.getElementById("studentPassword").value;
			let gender="";
			  if(document.getElementById('male').checked) {   
	                 gender  = document.getElementById("male").value;   
	          }   
	          else if(document.getElementById('female').checked) {   
	        	  gender  = document.getElementById("female").value;
	          }    
	          else {   
	              gender  = document.getElementById("others").value;   
	          }
			let studentAddress=document.getElementById("studentAddress").value;
			let studentCity=document.getElementById("studentCity").value;
			let occupation=document.getElementById("occupation").value;
			let studentBloodGroup=document.getElementById("studentBloodGroup").value;
			let studentStandard=document.getElementById("studentStandard").value;
			let facultyEmailId=document.getElementById("facultyEmailId").value;
			let parentMobileNumber=document.getElementById("parentMobileNumber").value;
			let dateOfBirth=document.getElementById("dateOfBirth").value;
			let data={
					"studentRollNumber":studentRollNumber,
					"studentName":studentName,
					"fatherName":fatherName,
					"motherName":motherName,
					"studentEmailId":studentEmailId,
					"studentPassword":studentPassword,
					"gender":gender,
					"studentAddress":studentAddress,
					"studentCity":studentCity,
					"occupation":occupation,
					"studentBloodGroup":studentBloodGroup,
					"studentStandard":studentStandard,
					"facultyEmailId":facultyEmailId,
					"parentMobileNumber":parentMobileNumber,
					"dateOfBirth":dateOfBirth			
			}
			let url="insert";
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