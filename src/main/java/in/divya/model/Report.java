/**
 * 
 */
package in.divya.model;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.ToString;

/**
 * @author divy2624
 *
 */
@Data
@ToString
@Component
public class Report {
	private String studentRollNumber;
	private String studentName;
	private LocalDate reasonDate;
	private String attendanceType;
	private String reason;
	private Long parentMobileNumber;
}
