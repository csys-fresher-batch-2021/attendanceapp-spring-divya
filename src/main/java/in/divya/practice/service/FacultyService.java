/**
 * 
 */
package in.divya.practice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.divya.practice.dao.UserRepository;
import in.divya.practice.model.Faculty;
import in.divya.practice.model.User;

/**
 * @author divy2624
 *
 */
@Service
public class FacultyService {

	@Autowired
	UserRepository userrepo;

	public List<Faculty> getFacultyData(String studentRollNumber) {
		return userrepo.findFacultyDataById(studentRollNumber);

	}

	public List<User> getStudentsList(String facultyId) {
		return userrepo.findStudentsListById(facultyId);

	}

	public List<User> getStudentInformation(String studentRollNumber) {
		return userrepo.findStudentById(studentRollNumber);
	}

}
