/**
 * 
 */
package in.divya.practice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import in.divya.practice.model.Faculty;
import in.divya.practice.model.User;
import in.divya.practice.service.FacultyService;

/**
 * @author divy2624
 *
 */
@RestController
public class FacultyController {
	@Autowired
	FacultyService facultyservice;

	@GetMapping("getFacultyData/{id}")
	public List<Faculty> getFacultyData(@PathVariable("id") String studentRollNumber) {
		return facultyservice.getFacultyData(studentRollNumber);
	}

	@GetMapping("getStudentsList/{id}")
	public List<User> getStudentsList(@PathVariable("id") String facultyId) {
		return facultyservice.getStudentsList(facultyId);
	}

	@GetMapping("getStudentInformation/{id}")
	public List<User> getStudentInformation(@PathVariable("id") String studentRollNumber) {
		return facultyservice.getStudentInformation(studentRollNumber);
	}
}
