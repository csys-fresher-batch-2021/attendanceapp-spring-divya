/**
 * 
 */
package in.divya.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.divya.dao.StudentRepository;
import in.divya.exceptions.CannotRegisterStudentException;
import in.divya.exceptions.InValidCredentialsException;
import in.divya.model.Student;
import in.divya.util.MessageUtil;

/**
 * @author divy2624
 *
 */
@Service
public class StudentService {

	@Autowired
	StudentRepository studentRepository;
	@Autowired
	Student user;
	@Autowired
	MessageUtil message;

	/**
	 * This method is used for student registration purpose.
	 * 
	 * @param user
	 * @throws CannotRegisterStudentException
	 */

	public void addStudent(Student user) throws CannotRegisterStudentException {
		int isAdded = 0;
		try {
			isAdded = studentRepository.save(user);
			if (isAdded > 0) {
				message.setInfoMessage("SUCESSFULLY REGISTERED");
			} else {
				throw new CannotRegisterStudentException("ALREADY EXISTS");
			}
		} catch (Exception e) {
			throw new CannotRegisterStudentException("ALREADY EXISTS");
		}

	}

	/**
	 * This method is used for student login purpose.
	 * 
	 * @param studentRollNumber
	 * @param studentName
	 * @param studentPassword
	 * @throws InValidCredentialsException
	 */

	public void studentLogin(String studentRollNumber, String studentName, String studentPassword)
			throws InValidCredentialsException {
		boolean isLogging = false;
		try {
			List<Student> studentCredentials = studentRepository.findStudentData();
			for (Student details : studentCredentials) {
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

	/**
	 * This method is used to display students list.
	 * 
	 * @param facultyId
	 * @return
	 */

	public List<Student> getStudentsList(String facultyId) {
		return studentRepository.findStudentsListById(facultyId);
	}

	/**
	 * This method is used to display student information.
	 * 
	 * @param studentRollNumber
	 * @return
	 */

	public List<Student> getStudentInformation(String studentRollNumber) {
		return studentRepository.findStudentById(studentRollNumber);
	}
}
