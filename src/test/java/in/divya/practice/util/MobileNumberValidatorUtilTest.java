/**
 * 
 */
package in.divya.practice.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import in.divya.practice.exceptions.InValidLongNumberTypeException;

/**
 * @author divy2624
 *
 */
public class MobileNumberValidatorUtilTest {

	/**
	 * To check MobileNumber TestCase.
	 * 
	 * @throws ParseException
	 * @throws InValidLongNumberTypeException
	 */

	@Test
	public void validNumberIsTestedWithoutAnyAlphabets() throws InValidLongNumberTypeException {
		String number = "9600923846";
		Long isValidNumber = MobileNumberValidatorUtil.isValidNumberOnly(number,
				"Mobile number cannot contain alphabets");
		assertEquals(Long.valueOf(9600923846L), isValidNumber);
	}

	/**
	 * To check InValid MobileNumber Format TestCase.
	 */

	@Test
	public void invalidNumberIsTestedWithAlphabet() {
		try {
			String number = "960092384s";
			Long isValidNumber = MobileNumberValidatorUtil.isValidNumberOnly(number,
					"Mobile Number cannot contain alphabets");
			assertEquals(Long.valueOf(9600923846L), isValidNumber);
		} catch (Exception e) {
			assertEquals("Mobile Number cannot contain alphabets", e.getMessage());
		}
	}

}
