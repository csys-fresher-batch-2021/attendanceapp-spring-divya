package in.divya.practice.model;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Component
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {
	private String studentRollNumber;
	private String studentName;
	private String fatherName;
	private String motherName;
	private String studentEmailId;
	private String studentPassword;
	private String gender;
	private String address;
	private String city;
	private String parentOccupation;
	private String studentBloodGroup;
	private String studentStandard;
	private String facultyEmailId;
	private Long parentMobileNumber;
	private LocalDate dateOfBirth;

}
