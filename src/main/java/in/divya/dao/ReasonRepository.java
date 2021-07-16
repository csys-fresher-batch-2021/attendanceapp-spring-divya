/**
 * 
 */
package in.divya.dao;

import java.time.LocalDate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import in.divya.model.Reason;

/**
 * @author divy2624
 *
 */
@Repository
public class ReasonRepository {
	@Autowired
	JdbcTemplate jdbctemplate;

	private final static String SQL1 = "insert into reason_spring(student_roll_number,reason_date,attendance_type,faculty_email_id,reason) values (?,?,?,?,?)";
	private final static String SQL2 = "select student_roll_number,reason_date,attendance_type,faculty_email_id,reason from reason_spring";
	private final static String SQL3 = "delete from reason_spring where student_roll_number=? and reason_date=?";

	/**
	 * This method is used to add reason into database.
	 * 
	 * @param reasonInformation
	 * @return
	 */

	public int save(Reason reasonInformation) {
		int rowsAffected = jdbctemplate.update(SQL1, reasonInformation.getStudentRollNumber(),
				reasonInformation.getReasonDate(), reasonInformation.getAttendanceType(),
				reasonInformation.getFacultyEmailId(), reasonInformation.getReason());
		return rowsAffected;

	}

	/**
	 * This method is used to display reasons list.
	 * 
	 * @return
	 */

	public List<Reason> findReasonList() {
		return jdbctemplate.query(SQL2, BeanPropertyRowMapper.newInstance(Reason.class));
	}

	/**
	 * This method is used to delete the reasons.
	 * 
	 * @param studentRollNumber
	 * @param reasonDate
	 * @return
	 */

	public int deleteReason(String studentRollNumber, LocalDate reasonDate) {
		int rowsAffected = jdbctemplate.update(SQL3, studentRollNumber, reasonDate);
		return rowsAffected;
	}
}
