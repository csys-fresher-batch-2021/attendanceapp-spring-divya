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
import in.divya.model.Report;

/**
 * @author divy2624
 *
 */
@Repository
public class ReportRepository {
	@Autowired
	JdbcTemplate jdbctemplate;

	private final static String SQL1 = "select faculty_name,faculty_class,faculty_email_id,faculty_mobile_number from faculty_spring where faculty_email_id=?";
	private final static String SQL2 = "select reason_spring.student_roll_number,student_spring.student_name,reason_spring.reason_date,reason_spring.attendance_type,reason_spring.reason,student_spring.parent_mobile_number from reason_spring inner join student_spring on reason_spring.student_roll_number=student_spring.student_roll_number and reason_spring.faculty_email_id=? and reason_spring.attendance_type=? order by reason_spring.reason_date";
	private final static String SQL3 = "select reason_spring.student_roll_number,student_spring.student_name,reason_spring.reason_date,reason_spring.attendance_type,reason_spring.reason,student_spring.parent_mobile_number from reason_spring inner join student_spring on reason_spring.student_roll_number=student_spring.student_roll_number and reason_spring.faculty_email_id=? and reason_spring.attendance_type=? order by reason_spring.reason_date";

	/**
	 * This method is used to display the student incharge details.
	 * 
	 * @param facultyId
	 * @return
	 */
	public List<Faculty> findFacultyDetails(String facultyId) {
		return jdbctemplate.query(SQL1, BeanPropertyRowMapper.newInstance(Faculty.class), facultyId);
	}

	/**
	 * This method is used to display absent details.
	 * 
	 * @param facultyId
	 * @return
	 */

	public List<Report> findAbsentReasonDetails(String facultyId) {
		return jdbctemplate.query(SQL2, BeanPropertyRowMapper.newInstance(Report.class), facultyId, "ABSENT");
	}

	/**
	 * This method is used to display onduty details
	 * 
	 * @param facultyId
	 * @return
	 */

	public List<Report> findOnDutyReasonDetails(String facultyId) {
		return jdbctemplate.query(SQL3, BeanPropertyRowMapper.newInstance(Report.class), facultyId, "ONDUTY");
	}
}
