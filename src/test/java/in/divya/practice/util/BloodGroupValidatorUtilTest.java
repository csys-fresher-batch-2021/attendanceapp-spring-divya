package in.divya.practice.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.divya.practice.exceptions.InValidBloodGroupFormatException;

/**
 * @author divy2624
 *
 */
public class BloodGroupValidatorUtilTest {

	/**
	 * To check Valid BloodGroup Format TestCase
	 * 
	 * @throws InValidBloodGroupFormatException
	 */

	@Test
	public void validBloodGroupFormatIsTeste() throws InValidBloodGroupFormatException {
		String bloodGroup = "A-";
		boolean isValidBloodGroup = BloodGroupValidatorUtil.isValidBloodGroupFormat(bloodGroup,
				"Invalid bloodGroup format");
		assertTrue(isValidBloodGroup);
	}

	/**
	 * To check InValid BloodGroup Format TestCase
	 */

	@Test
	public void inValidBloodGroupFormatIsTested() {
		try {
			String bloodGroup = "-A";
			boolean isValidBloodGroup = BloodGroupValidatorUtil.isValidBloodGroupFormat(bloodGroup,
					"InValid BloodGroup format");
			assertFalse(isValidBloodGroup);
		} catch (Exception e) {
			assertEquals("InValid BloodGroup format", e.getMessage());
		}
	}

}
