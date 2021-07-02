package in.divya.practice.dto;

import java.time.LocalDate;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserDTO {
	private String studentRollNumber;
	private String studentName;
	private String fatherName;
	private String motherName;
	private String studentEmailId;
	private String studentPassword;
	private String gender;
	private String studentAddress;
	private String studentCity;
	private String occupation;
	private String studentBloodGroup;
	private String studentStandard;
	private String facultyEmailId;
	private Long parentMobileNumber;
	private LocalDate dateOfBirth;

}
