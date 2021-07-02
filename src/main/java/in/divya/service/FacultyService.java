/**
 * 
 */
package in.divya.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.divya.dao.FacultyRepository;
import in.divya.exceptions.InValidCredentialsException;
import in.divya.model.Faculty;
import in.divya.util.MessageUtil;

/**
 * @author divy2624
 *
 */
@Service
public class FacultyService {

	@Autowired
	FacultyRepository facultyRepository;
	@Autowired
	MessageUtil message;

	/**
	 * This method is used for faculty login purpose.
	 * 
	 * @param facultyName
	 * @param facultyEmailId
	 * @param facultyPassword
	 * @throws InValidCredentialsException
	 */

	public void facultyLogin(String facultyName, String facultyEmailId, String facultyPassword)
			throws InValidCredentialsException {
		boolean isLoggedIn = false;
		try {
			List<Faculty> facultyCredentials = facultyRepository.findFacultyData();
			for (Faculty faculty : facultyCredentials) {
				if (faculty.getFacultyName().equals(facultyName) && faculty.getFacultyEmailId().equals(facultyEmailId)
						&& faculty.getFacultyPassword().equals(facultyPassword)) {
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

	/**
	 * This method is used to display faculty details.
	 * 
	 * @param studentRollNumber
	 * @return
	 */

	public List<Faculty> getFacultyData(String studentRollNumber) {
		return facultyRepository.findFacultyDataById(studentRollNumber);
	}
}
