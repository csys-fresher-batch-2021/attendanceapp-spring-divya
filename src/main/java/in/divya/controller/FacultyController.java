/**
 * 
 */
package in.divya.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.divya.exceptions.InValidCredentialsException;
import in.divya.model.Faculty;
import in.divya.service.FacultyService;
import in.divya.util.EmailValidatorUtil;
import in.divya.util.MessageUtil;
import in.divya.util.PasswordValidatorUtil;
import in.divya.util.StringValidatorUtil;

/**
 * @author divy2624
 *
 */
@RestController
public class FacultyController {

	@Autowired
	FacultyService facultyService;
	@Autowired
	MessageUtil message;

	/**
	 * This method is used for faculty login purpose.
	 * 
	 * @param faculty
	 * @param request
	 * @return
	 * @throws InValidCredentialsException
	 */

	@PostMapping("facultyLogin")
	public ResponseEntity<MessageUtil> facultyLogin(@RequestBody Faculty faculty, HttpServletRequest request)
			throws InValidCredentialsException {
		try {
			HttpSession session = request.getSession();
			session.setAttribute("LOGGED_IN_USER", faculty.getFacultyName());
			session.setAttribute("LOGGED_IN_USER_ID", faculty.getFacultyEmailId());
			String facultyName = faculty.getFacultyName();
			String facultyEmailId = faculty.getFacultyEmailId();
			String facultyPassword = faculty.getFacultyPassword();
			StringValidatorUtil.isStringNotNullOrEmpty(facultyName, "Name cannot Accept Empty and Null Value");
			EmailValidatorUtil.isValidEmailId(facultyEmailId, "InValid EmailId Format");
			PasswordValidatorUtil.isValidPasswordFormat(facultyPassword, "InValid Password Format");
			facultyService.facultyLogin(facultyName, facultyEmailId, facultyPassword);
		} catch (Exception e) {
			throw new InValidCredentialsException(e.getMessage());
		}
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	/**
	 * This method is used to display faculty details.
	 * 
	 * @param studentRollNumber
	 * @return
	 */

	@GetMapping("getFacultyData/{id}")
	public List<Faculty> getFacultyData(@PathVariable("id") String studentRollNumber) {
		return facultyService.getFacultyData(studentRollNumber);
	}
}
