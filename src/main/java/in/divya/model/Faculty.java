/**
 * 
 */
package in.divya.model;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author divy2624
 *
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Faculty {
	private String facultyName;
	private String facultyClass;
	private String facultyEmailId;
	private long facultyMobileNumber;
	private String facultyPassword;

}
