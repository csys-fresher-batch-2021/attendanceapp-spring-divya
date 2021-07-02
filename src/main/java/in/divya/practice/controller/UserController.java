package in.divya.practice.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.divya.practice.dao.UserRepository;
import in.divya.practice.dto.UserDTO;
import in.divya.practice.exceptions.CannotRegisterStudentException;
import in.divya.practice.exceptions.InValidCredentialsException;
import in.divya.practice.model.Demo;
import in.divya.practice.model.Faculty;
import in.divya.practice.model.User;
import in.divya.practice.service.UserService;
import in.divya.practice.util.BloodGroupValidatorUtil;
import in.divya.practice.util.DateValidatorUtil;
import in.divya.practice.util.EmailValidatorUtil;
import in.divya.practice.util.MessageUtil;
import in.divya.practice.util.MobileNumberValidatorUtil;
import in.divya.practice.util.PasswordValidatorUtil;
import in.divya.practice.util.StringValidatorUtil;
import in.divya.practice.validator.RollNumberValidator;
import in.divya.practice.validator.StandardValidator;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	MessageUtil message;
	@Autowired
	User user;

	@PostMapping("insert")
	public ResponseEntity<MessageUtil> insert(@RequestBody UserDTO userdto) throws CannotRegisterStudentException {
		try {
			String studentRollNumber = userdto.getStudentRollNumber();
			String studentName = userdto.getStudentName();
			String fatherName = userdto.getFatherName();
			String motherName = userdto.getMotherName();
			String studentEmailId = userdto.getStudentEmailId();
			String studentPassword = userdto.getStudentPassword();
			String gender = userdto.getGender();
			String studentAddress = userdto.getStudentAddress();
			String studentCity = userdto.getStudentCity();
			String occupation = userdto.getOccupation();
			String studentBloodGroup = userdto.getStudentBloodGroup();
			String studentStandard = userdto.getStudentStandard();
			String facultyEmailId = userdto.getFacultyEmailId();
			Long parentMobileNumber = userdto.getParentMobileNumber();
			LocalDate dateOfBirth = userdto.getDateOfBirth();
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
			user.setStudentRollNumber(studentRollNumber);
			user.setStudentName(studentName);
			user.setFatherName(fatherName);
			user.setMotherName(motherName);
			user.setStudentEmailId(studentEmailId);
			user.setStudentPassword(studentPassword);
			user.setGender(gender);
			user.setAddress(studentAddress);
			user.setCity(studentCity);
			user.setParentOccupation(occupation);
			user.setStudentBloodGroup(studentBloodGroup);
			user.setStudentStandard(studentStandard);
			user.setFacultyEmailId(facultyEmailId);
			user.setParentMobileNumber(parentMobileNumber);
			user.setDateOfBirth(dateOfBirth);
			userService.addStudent(user);

		} catch (Exception e) {
			throw new CannotRegisterStudentException(e.getMessage());
		}
		return new ResponseEntity<>(message, HttpStatus.OK);

	}

	@Autowired
	UserRepository userrepo;

	@PostMapping("facultyLogin")
	public ResponseEntity<MessageUtil> facultyLogin(@RequestBody Faculty faculty, HttpServletRequest request)
			throws InValidCredentialsException {
		try {
			HttpSession session = request.getSession();
			session.setAttribute("LOGGED_IN_USER", faculty.getFacultyName());
			session.setAttribute("LOGGED_IN_USER_ID", faculty.getFacultyEmailId());
			String facultyName = faculty.getFacultyName();
			System.out.println(facultyName);
			String facultyEmailId = faculty.getFacultyEmailId();
			String facultyPassword = faculty.getFacultyPassword();
			StringValidatorUtil.isStringNotNullOrEmpty(facultyName, "Name cannot Accept Empty and Null Value");
			EmailValidatorUtil.isValidEmailId(facultyEmailId, "InValid EmailId Format");
			PasswordValidatorUtil.isValidPasswordFormat(facultyPassword, "InValid Password Format");
			userService.facultyLogin(facultyName, facultyEmailId, facultyPassword);
		} catch (Exception e) {
			throw new InValidCredentialsException(e.getMessage());
		}
		return new ResponseEntity<>(message, HttpStatus.OK);

	}

	@PostMapping("studentLogin")
	public ResponseEntity<MessageUtil> studentLogin(@RequestBody UserDTO userdto, HttpServletRequest request)
			throws InValidCredentialsException {
		try {
			HttpSession session = request.getSession();
			session.setAttribute("LOGGED_IN_USER", userdto.getStudentName());
			session.setAttribute("LOGGED_IN_USER_NO", userdto.getStudentRollNumber());
			String studentRollNumber = userdto.getStudentRollNumber();
			String studentName = userdto.getStudentName();
			String studentPassword = userdto.getStudentPassword();
			StringValidatorUtil.isStringNotNullOrEmpty(studentName, "Name cannot Accept Empty and Null Value");
			RollNumberValidator.isValidRollNumberFormat(studentRollNumber, "Invalid RollNumber Format");
			PasswordValidatorUtil.isValidPasswordFormat(studentPassword, "InValid Password Format");
			userService.studentLogin(studentRollNumber, studentName, studentPassword);

		} catch (Exception e) {
			throw new InValidCredentialsException(e.getMessage());
		}
		return new ResponseEntity<>(message, HttpStatus.OK);

	}

	@PostMapping("insertDemo")
	public Demo insertUser(@RequestBody Demo demo) {
		System.out.println(demo);
		return userrepo.insert(demo);
	}

	@PutMapping("updateuser")
	public Demo updateUser(@RequestBody Demo demo) {
		return userrepo.updateUser(demo);
	}

	@DeleteMapping("deleteuser")
	public String updateUser() {
		return userrepo.deleteUser();
	}

	@GetMapping("getAllUsers")
	public List<Demo> getAllUsers() {
		return userrepo.getAllUsers();
	}

	@DeleteMapping("deleteUserId/{id}")
	public String updateUser(@PathVariable("id") String studentName) {
		return userrepo.deleteUserId(studentName);
	}

	@GetMapping("getAllUsers/{id}")
	public List<User> getUsers(@PathVariable("id") String name) {
		return userrepo.findById(name);
	}

	@GetMapping("logout")
	public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("LOGGED_IN_USER");
		response.sendRedirect("index.jsp");
	}

}
