/**
 * 
 */
package in.divya.advice;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import in.divya.exceptions.CannotRegisterStudentException;
import in.divya.exceptions.InValidCredentialsException;
import in.divya.util.MessageUtil;

/**
 * @author divy2624
 *
 */
@ControllerAdvice
public class ErrorHandler {

	@Autowired
	MessageUtil message;

	/**
	 * This method is used for Register Error handling.
	 * 
	 * @param e
	 * @return
	 */

	@ExceptionHandler(CannotRegisterStudentException.class)
	public ResponseEntity<MessageUtil> registerServiceError(CannotRegisterStudentException e) {
		message.setErrorMessage(e.getMessage());
		return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	}

	/**
	 * This method is used for Login Error Handling.
	 * 
	 * @param e
	 * @return
	 */

	@ExceptionHandler(InValidCredentialsException.class)
	public ResponseEntity<MessageUtil> loginServiceError(InValidCredentialsException e) {
		message.setErrorMessage(e.getMessage());
		return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	}
}
