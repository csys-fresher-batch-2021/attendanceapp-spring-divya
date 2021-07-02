/**
 * 
 */
package in.divya.practice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.divya.practice.dao.UserRepository;
import in.divya.practice.exceptions.CannotRegisterStudentException;
import in.divya.practice.exceptions.InValidCredentialsException;
import in.divya.practice.model.Faculty;
import in.divya.practice.model.User;

import in.divya.practice.util.MessageUtil;

/**
 * @author divy2624
 *
 */
@Service
public class UserService {

	@Autowired
	UserRepository userrepo;
	@Autowired
	User user;
	@Autowired
	MessageUtil message;

	public void addStudent(User user) throws CannotRegisterStudentException {
		int isAdded = 0;

		try {
			isAdded = userrepo.save(user);
			if (isAdded > 0) {
				message.setInfoMessage("SUCESSFULLY REGISTERED");
			} else {
				throw new CannotRegisterStudentException("ALREADY EXISTS");
			}

		} catch (Exception e) {
			throw new CannotRegisterStudentException("ALREADY EXISTS");

		}

	}

	public void facultyLogin(String facultyName, String facultyEmailId, String facultyPassword) throws InValidCredentialsException {
		boolean isLoggedIn = false;
		try {
			List<Faculty> facultyCredentials = userrepo.findFacultyData();
			for (Faculty data : facultyCredentials) {
				if (data.getFacultyName().equals(facultyName)
						&&data.getFacultyEmailId().equals(facultyEmailId)
						&& data.getFacultyPassword().equals(facultyPassword)) {
					isLoggedIn = true;
					break;
				}
			}
			if (isLoggedIn) {
				message.setInfoMessage("SUCESSFULLY LOGIN");
			} else {
				throw new InValidCredentialsException("INVALID LOGIN CREDENTIALS");
			}

		} catch (Exception e) {
			throw new InValidCredentialsException("INVALID LOGIN CREDENTIALS");

		}
		
	}

	public void studentLogin(String studentRollNumber, String studentName, String studentPassword)
			throws InValidCredentialsException {
		boolean isLogging = false;
		try {
			List<User> studentCredentials = userrepo.findStudentData();
			for (User details : studentCredentials) {
				if (details.getStudentRollNumber().equals(studentRollNumber)
						&& details.getStudentName().equals(studentName)
						&& details.getStudentPassword().equals(studentPassword)) {
					isLogging = true;
					break;
				}
			}
			if (isLogging) {
				message.setInfoMessage("SUCESSFULLY LOGIN");
			} else {
				throw new InValidCredentialsException("INVALID LOGIN CREDENTIALS");
			}

		} catch (Exception e) {
			throw new InValidCredentialsException("INVALID LOGIN CREDENTIALS");

		}
	}
}
