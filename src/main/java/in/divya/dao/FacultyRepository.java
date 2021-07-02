/**
 * 
 */
package in.divya.dao;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import in.divya.model.Faculty;

/**
 * @author divy2624
 *
 */
@Repository
public class FacultyRepository {

	@Autowired
	JdbcTemplate jdbctemplate;

	private final static String SQL1 = "select faculty_name,faculty_class,faculty_email_id,faculty_password from faculty_spring";
	private final static String SQL2 = "select faculty_name,faculty_class,faculty_email_id,faculty_mobile_number from faculty_spring where faculty_email_id in (select faculty_email_id from student_spring where student_roll_number=?)";

	/**
	 * This method is used to get the some faculty data for faculty login purpose.
	 * 
	 * @return
	 */
	public List<Faculty> findFacultyData() {

		return jdbctemplate.query(SQL1, BeanPropertyRowMapper.newInstance(Faculty.class));

	}

	/**
	 * This method is used to display faculty data.
	 * 
	 * @param studentRollNumber
	 * @return
	 */

	public List<Faculty> findFacultyDataById(String studentRollNumber) {

		return jdbctemplate.query(SQL2, BeanPropertyRowMapper.newInstance(Faculty.class), studentRollNumber);
	}
}
