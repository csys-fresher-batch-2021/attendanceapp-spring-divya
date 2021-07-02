/**
 * 
 */
package in.divya.practice.advice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import in.divya.practice.exceptions.CannotRegisterStudentException;
import in.divya.practice.exceptions.InValidCredentialsException;
import in.divya.practice.util.MessageUtil;

/**
 * @author divy2624
 *
 */
@ControllerAdvice
public class ErrorHandler {

	@Autowired
	MessageUtil message;

	@ExceptionHandler(CannotRegisterStudentException.class)
	public ResponseEntity<MessageUtil> registerServiceError(CannotRegisterStudentException e) {
		message.setErrorMessage(e.getMessage());
		return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InValidCredentialsException.class)
	public ResponseEntity<MessageUtil> loginServiceError(InValidCredentialsException e) {
		message.setErrorMessage(e.getMessage());
		return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	}
}
