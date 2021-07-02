package in.divya.practice.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.divya.practice.exceptions.InValidStandardFormatException;

public class StandardValidatorTest {

	/**
	 * To check Standard TestCase.
	 * 
	 * @throws InValidStandardFormatException
	 */

	@Test
	public void validStandardFormatIsTested() throws InValidStandardFormatException {
		String standard = "XII";
		boolean isValidRollNumber = StandardValidator.isValidStandardValidation(standard, "InValid standard format");
		assertTrue(isValidRollNumber);
	}

	/**
	 * To check Invalid Standard TestCase.
	 */

	@Test
	public void InValidStandardFormatIsTested1() {
		try {
			String standard = "X&7";
			boolean isValidRollNumber = StandardValidator.isValidStandardValidation(standard,
					"InValid standard format");
			assertFalse(isValidRollNumber);
		} catch (Exception e) {
			assertEquals("InValid standard format", e.getMessage());
		}
	}

}
