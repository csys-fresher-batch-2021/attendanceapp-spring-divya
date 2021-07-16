/**
 * 
 */
package in.divya.dto;

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
public class ReasonDTO {
	private String studentRollNumber;
	private LocalDate reasonDate;
	private String attendanceType;
	private String facultyEmailId;
	private String reason;
}
