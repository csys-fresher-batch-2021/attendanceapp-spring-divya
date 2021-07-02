package in.divya.controller;

import java.io.IOException;


import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.divya.dao.StudentRepository;
import in.divya.dto.StudentDTO;
import in.divya.exceptions.CannotRegisterStudentException;
import in.divya.exceptions.InValidCredentialsException;
import in.divya.model.Student;
import in.divya.service.StudentService;
import in.divya.util.BloodGroupValidatorUtil;
import in.divya.util.DateValidatorUtil;
import in.divya.util.EmailValidatorUtil;
import in.divya.util.MessageUtil;
import in.divya.util.MobileNumberValidatorUtil;
import in.divya.util.PasswordValidatorUtil;
import in.divya.util.StringValidatorUtil;
import in.divya.validator.RollNumberValidator;
import in.divya.validator.StandardValidator;

@RestController
public class StudentController {

	@Autowired
	StudentService studentService;
	@Autowired
	MessageUtil message;
	@Autowired
	Student student;
	@Autowired
	StudentRepository studentRepository;

	/**
	 * This method is used for student registration purpose.
	 * 
	 * @param studentDTO
	 * @return
	 * @throws CannotRegisterStudentException
	 */

	@PostMapping("insert")
	public ResponseEntity<MessageUtil> insert(@RequestBody StudentDTO studentDTO)
			throws CannotRegisterStudentException {
		try {
			String studentRollNumber = studentDTO.getStudentRollNumber();
			String studentName = studentDTO.getStudentName();
			String fatherName = studentDTO.getFatherName();
			String motherName = studentDTO.getMotherName();
			String studentEmailId = studentDTO.getStudentEmailId();
			String studentPassword = studentDTO.getStudentPassword();
			String gender = studentDTO.getGender();
			String studentAddress = studentDTO.getStudentAddress();
			String studentCity = studentDTO.getStudentCity();
			String occupation = studentDTO.getOccupation();
			String studentBloodGroup = studentDTO.getStudentBloodGroup();
			String studentStandard = studentDTO.getStudentStandard();
			String facultyEmailId = studentDTO.getFacultyEmailId();
			Long parentMobileNumber = studentDTO.getParentMobileNumber();
			LocalDate dateOfBirth = studentDTO.getDateOfBirth();
			RollNumberValidator.isValidRollNumberFormat(studentRollNumber, "Invalid RollNumber Format");
			StringValidatorUtil.isStringNotNullOrEmpty(studentName, "Name cannot Accept Empty and Null Value");
			StringValidatorUtil.isStringNotNullOrEmpty(fatherName, "Father name cannot Accept Empty and Null Value");
			StringValidatorUtil.isStringNotNullOrEmpty(motherName, "Mother name cannot Accept Empty and Null Value");
			EmailValidatorUtil.isValidEmailId(studentEmailId, "InValid EmailId Format");
			RollNumberValidator.isValidRollNumberFormat(studentRollNumber, "Invalid RollNumber Format");
			PasswordValidatorUtil.isValidPasswordFormat(studentPassword, "InValid Password Format");
			StringValidatorUtil.isStringNotNullOrEmpty(gender, "Gender cannot accept empty and null value");
			StringValidatorUtil.isStringNotNullOrEmpty(studentAddress, "Address cannot Accept Empty and Null Value");
			StringValidatorUtil.isStringNotNullOrEmpty(studentCity, "City cannot Accept Empty and Null Value");
			StringValidatorUtil.isStringNotNullOrEmpty(occupation, "Occupation cannot Accept Empty and Null Value");
			BloodGroupValidatorUtil.isValidBloodGroupFormat(studentBloodGroup, "InValid BloodGroup Format");
			StandardValidator.isValidStandardValidation(studentStandard, "InValid Standard Format");
			EmailValidatorUtil.isValidEmailId(facultyEmailId, "InValid EmailId Format");
			MobileNumberValidatorUtil.isValidMobileNumber(parentMobileNumber);
			DateValidatorUtil.isNotAFutureDate(dateOfBirth, "Date cannot be a future date");
			student.setStudentRollNumber(studentRollNumber);
			student.setStudentName(studentName);
			student.setFatherName(fatherName);
			student.setMotherName(motherName);
			student.setStudentEmailId(studentEmailId);
			student.setStudentPassword(studentPassword);
			student.setGender(gender);
			student.setAddress(studentAddress);
			student.setCity(studentCity);
			student.setParentOccupation(occupation);
			student.setStudentBloodGroup(studentBloodGroup);
			student.setStudentStandard(studentStandard);
			student.setFacultyEmailId(facultyEmailId);
			student.setParentMobileNumber(parentMobileNumber);
			student.setDateOfBirth(dateOfBirth);
			studentService.addStudent(student);

		} catch (Exception e) {
			throw new CannotRegisterStudentException(e.getMessage());
		}
		return new ResponseEntity<>(message, HttpStatus.OK);

	}

	/**
	 * This method is used for student login purpose.
	 * 
	 * @param studentDTO
	 * @param request
	 * @return
	 * @throws InValidCredentialsException
	 */

	@PostMapping("studentLogin")
	public ResponseEntity<MessageUtil> studentLogin(@RequestBody StudentDTO studentDTO, HttpServletRequest request)
			throws InValidCredentialsException {
		try {
			HttpSession session = request.getSession();
			session.setAttribute("LOGGED_IN_USER", studentDTO.getStudentName());
			session.setAttribute("LOGGED_IN_USER_NO", studentDTO.getStudentRollNumber());
			String studentRollNumber = studentDTO.getStudentRollNumber();
			String studentName = studentDTO.getStudentName();
			String studentPassword = studentDTO.getStudentPassword();
			StringValidatorUtil.isStringNotNullOrEmpty(studentName, "Name cannot Accept Empty and Null Value");
			RollNumberValidator.isValidRollNumberFormat(studentRollNumber, "Invalid RollNumber Format");
			PasswordValidatorUtil.isValidPasswordFormat(studentPassword, "InValid Password Format");
			studentService.studentLogin(studentRollNumber, studentName, studentPassword);

		} catch (Exception e) {
			throw new InValidCredentialsException(e.getMessage());
		}
		return new ResponseEntity<>(message, HttpStatus.OK);

	}

	/**
	 * This method is used to display the list of students.
	 * 
	 * @param facultyId
	 * @return
	 */
	@GetMapping("getStudentsList/{id}")
	public List<in.divya.model.Student> getStudentsList(@PathVariable("id") String facultyId) {
		return studentService.getStudentsList(facultyId);
	}

	/**
	 * This method is used to display student information.
	 * 
	 * @param studentRollNumber
	 * @return
	 */

	@GetMapping("getStudentInformation/{id}")
	public List<Student> getStudentInformation(@PathVariable("id") String studentRollNumber) {
		return studentService.getStudentInformation(studentRollNumber);
	}

	/**
	 * This method is used for Logout purpose.
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */

	@GetMapping("logout")
	public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("LOGGED_IN_USER");
		response.sendRedirect("index.jsp");
	}

}
