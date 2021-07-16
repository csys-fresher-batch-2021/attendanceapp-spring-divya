
package in.divya.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import in.divya.model.Student;

/**
 * @author divy2624
 *
 */
@Repository
public class StudentRepository {

	@Autowired
	JdbcTemplate jdbctemplate;

	private final static String SQL1 = "insert into student_spring(student_roll_number,student_name,father_name,mother_name,student_email_id,student_password,gender,address,city,parent_occupation,student_blood_group,student_standard,faculty_email_id,parent_mobile_number,date_of_birth) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private final static String SQL2 = "select student_roll_number,student_name,student_password from student_spring";
	private final static String SQL3 = "select student_name,student_roll_number from student_spring where faculty_email_id=? order by student_roll_number";
	private final static String SQL4 = "select student_roll_number,student_name,father_name,mother_name,student_email_id,gender,address,city,parent_occupation,student_blood_group,student_standard,parent_mobile_number,date_of_birth from student_spring where student_roll_number=?";
	private final static String SQL5 = "delete from student_spring where student_roll_number=?";

	/**
	 * This method is used to add student details into database.
	 * 
	 * @param student
	 * @return
	 */

	public int save(Student student) {
		int rowsAffected = jdbctemplate.update(SQL1, student.getStudentRollNumber(), student.getStudentName(),
				student.getFatherName(), student.getMotherName(), student.getStudentEmailId(),
				student.getStudentPassword(), student.getGender(), student.getAddress(), student.getCity(),
				student.getParentOccupation(), student.getStudentBloodGroup(), student.getStudentStandard(),
				student.getFacultyEmailId(), student.getParentMobileNumber(), student.getDateOfBirth());
		return rowsAffected;

	}

	/**
	 * This method is used to get some student data for student login purpose.
	 * 
	 * @return
	 */
	public List<Student> findStudentData() {
		return jdbctemplate.query(SQL2, BeanPropertyRowMapper.newInstance(Student.class));
	}

	/**
	 * This method is used to display students list.
	 * 
	 * @param facultyEmailId
	 * @return
	 */

	public List<Student> findStudentsListById(String facultyEmailId) {

		return jdbctemplate.query(SQL3, BeanPropertyRowMapper.newInstance(Student.class), facultyEmailId);
	}

	/**
	 * This method is used to display student information.
	 * 
	 * @param studentRollNumber
	 * @return
	 */

	public List<Student> findStudentById(String studentRollNumber) {

		return jdbctemplate.query(SQL4, BeanPropertyRowMapper.newInstance(Student.class), studentRollNumber);
	}

	/**
	 * This method is used to delete the student all details.
	 * 
	 * @param studentRollNumber
	 * @return
	 */

	public int deleteStudent(String studentRollNumber) {
		int deleteStudent = jdbctemplate.update(SQL5, studentRollNumber);
		return deleteStudent;
	}
}
