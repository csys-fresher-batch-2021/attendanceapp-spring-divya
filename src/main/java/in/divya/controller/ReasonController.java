/**
 * 
 */
package in.divya.controller;

import java.time.LocalDate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.divya.dto.ReasonDTO;
import in.divya.exceptions.InValidCredentialsException;
import in.divya.model.Reason;
import in.divya.service.ReasonService;
import in.divya.util.DateValidatorUtil;
import in.divya.util.EmailValidatorUtil;
import in.divya.util.MessageUtil;
import in.divya.util.StringValidatorUtil;
import in.divya.validator.RollNumberValidator;

/**
 * @author divy2624
 *
 */
@RestController
public class ReasonController {
	@Autowired
	MessageUtil message;
	@Autowired
	Reason reasonInformation;
	@Autowired
	ReasonService reasonService;

	/**
	 * This method is used for inserting reasons.
	 * 
	 * @param reasonDTO
	 * @return
	 * @throws InValidCredentialsException
	 */

	@PostMapping("insertReason")
	public ResponseEntity<MessageUtil> insertReason(@RequestBody ReasonDTO reasonDTO)
			throws InValidCredentialsException {
		try {
			String studentRollNumber = reasonDTO.getStudentRollNumber();
			LocalDate reasonDate = reasonDTO.getReasonDate();
			String attendanceType = reasonDTO.getAttendanceType();
			String facultyEmailId = reasonDTO.getFacultyEmailId();
			String reason = reasonDTO.getReason();
			RollNumberValidator.isValidRollNumberFormat(studentRollNumber, "InValid RollNumber Format");
			EmailValidatorUtil.isValidEmailId(facultyEmailId, "InValid EmailId Format");
			StringValidatorUtil.isStringNotNullOrEmpty(reason, "Reason cannot Accept Empty and Null Value");
			reasonInformation.setStudentRollNumber(studentRollNumber);
			reasonInformation.setReasonDate(reasonDate);
			reasonInformation.setAttendanceType(attendanceType);
			reasonInformation.setFacultyEmailId(facultyEmailId);
			reasonInformation.setReason(reason);
			reasonService.addReason(reasonInformation);
		} catch (Exception e) {
			throw new InValidCredentialsException(e.getMessage());
		}
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	/**
	 * This method is used to display reasons.
	 * 
	 * @return
	 */

	@GetMapping("getReasonList")
	public List<Reason> getReasonList() {
		return reasonService.getReasonList();
	}

	/**
	 * This method is used to remove reasons.
	 * 
	 * @param studentRollNumber
	 * @param reasonDate
	 * @return
	 * @throws InValidCredentialsException
	 */

	@DeleteMapping("removeReason/{id},{id1}")
	public ResponseEntity<MessageUtil> removeReason(@PathVariable("id") String studentRollNumber,
			@PathVariable("id1") String reasonDate) throws InValidCredentialsException {
		try {
			RollNumberValidator.isValidRollNumberFormat(studentRollNumber, "InValid RollNumber Format");
			LocalDate reasonsDate = DateValidatorUtil.isDateFormatOrNot(reasonDate, "InValid Date Format");
			reasonService.removeReason(studentRollNumber, reasonsDate);
		} catch (Exception e) {
			throw new InValidCredentialsException(e.getMessage());
		}
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
}
