
package in.divya.practice.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import in.divya.practice.model.Demo;
import in.divya.practice.model.Faculty;
import in.divya.practice.model.User;

/**
 * @author divy2624
 *
 */
@Repository
public class UserRepository {

	private final static String SQL = "insert into demo(name,password)values(?,?)";
	private final static String SQL2 = "update demo set password=? where name=?";
	private final static String SQL3 = "delete from demo";
	private final static String SQL4 = "select * from demo";
	private final static String SQL5 = "delete from student_spring where student_name=?";
	// private final static String SQL6="select * from student_spring where
	// student_name=?";
	private final static String SQL7 = "insert into student_spring(student_roll_number,student_name,father_name,mother_name,student_email_id,student_password,gender,address,city,parent_occupation,student_blood_group,student_standard,faculty_email_id,parent_mobile_number,date_of_birth) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private final static String SQL8 = "select faculty_name,faculty_class,faculty_email_id,faculty_password from faculty_spring";
	private final static String SQL9 = "select student_roll_number,student_name,student_password from student_spring";

	@Autowired
	JdbcTemplate jdbctemplate;

	public Demo insert(Demo demo) {
		System.out.println(demo);
		jdbctemplate.update(SQL, demo.getName(), demo.getPassword());
		return demo;

	}

	public Demo updateUser(Demo demo) {
		jdbctemplate.update(SQL2, demo.getPassword(), demo.getName());
		return demo;
	}

	public String deleteUser() {
		jdbctemplate.update(SQL3);
		return "Data Deleted Successfully";
	}

	public List<Demo> getAllUsers() {
		return jdbctemplate.query(SQL4, (rs, rowNum) -> new Demo(rs.getString("name"), rs.getString("password")));

	}

	public String deleteUserId(String studentName) {
		int row = jdbctemplate.update(SQL5, studentName);
		System.out.println(row);
		return "Data Deleted Successfully";
	}

	public int save(User user) {
		int rowsAffected = jdbctemplate.update(SQL7, user.getStudentRollNumber(), user.getStudentName(),
				user.getFatherName(), user.getMotherName(), user.getStudentEmailId(), user.getStudentPassword(),
				user.getGender(), user.getAddress(), user.getCity(), user.getParentOccupation(),
				user.getStudentBloodGroup(), user.getStudentStandard(), user.getFacultyEmailId(),
				user.getParentMobileNumber(), user.getDateOfBirth());
		return rowsAffected;

	}

	public List<User> findById(String studentName) {

		String sql = "SELECT * FROM student_spring WHERE student_name = ?";

		return jdbctemplate.query(sql, BeanPropertyRowMapper.newInstance(User.class), studentName);
	}

	public List<Faculty> findFacultyData() {

		return jdbctemplate.query(SQL8, BeanPropertyRowMapper.newInstance(Faculty.class));

	}

	public List<User> findStudentData() {
		return jdbctemplate.query(SQL9, BeanPropertyRowMapper.newInstance(User.class));
	}

	public List<Faculty> findFacultyDataById(String studentRollNumber) {
		String sql = "select faculty_name,faculty_class,faculty_email_id,faculty_mobile_number from faculty_spring where faculty_email_id in (select faculty_email_id from student_spring where student_roll_number=?)";

		return jdbctemplate.query(sql, BeanPropertyRowMapper.newInstance(Faculty.class), studentRollNumber);
	}

	public List<User> findStudentsListById(String facultyEmailId) {
		String sql = "select student_name,student_roll_number from student_spring where faculty_email_id=? order by student_roll_number";

		return jdbctemplate.query(sql, BeanPropertyRowMapper.newInstance(User.class), facultyEmailId);
	}

	public List<User> findStudentById(String studentRollNumber) {
		String sql = "select student_roll_number,student_name,father_name,mother_name,student_email_id,gender,address,city,parent_occupation,student_blood_group,student_standard,parent_mobile_number,date_of_birth from student_spring where student_roll_number=?";

		return jdbctemplate.query(sql, BeanPropertyRowMapper.newInstance(User.class), studentRollNumber);
	}

}
