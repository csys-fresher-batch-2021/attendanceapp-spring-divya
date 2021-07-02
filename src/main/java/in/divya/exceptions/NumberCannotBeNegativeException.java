/**
 * 
 */
package in.divya.exceptions;

/**
 * @author divy2624
 *
 */
public class NumberCannotBeNegativeException extends Exception {
	private static final long serialVersionUID = 1L;

	/**
	 * @param message
	 */
	public NumberCannotBeNegativeException(String message) {
		super(message);
	}
}
