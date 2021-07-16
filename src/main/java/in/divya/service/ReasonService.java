/**
 * 
 */
package in.divya.service;

import java.time.LocalDate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import in.divya.dao.ReasonRepository;

import in.divya.exceptions.InValidCredentialsException;
import in.divya.model.Reason;
import in.divya.util.MessageUtil;

/**
 * @author divy2624
 *
 */
@Service
public class ReasonService {

	@Autowired
	ReasonRepository reasonRepository;
	@Autowired
	MessageUtil message;

	/**
	 * This method is used to add reasons.
	 * 
	 * @param reasonInformation
	 * @throws InValidCredentialsException
	 */

	public void addReason(Reason reasonInformation) throws InValidCredentialsException {
		int isAdded = 0;
		try {
			isAdded = reasonRepository.save(reasonInformation);
			if (isAdded > 0) {
				message.setInfoMessage("SUCESSFULLY ENTERED");
			} else {
				throw new InValidCredentialsException("ALREADY ENTERED");
			}
		} catch (Exception e) {
			throw new InValidCredentialsException("ALREADY ENTERED");
		}
	}

	/**
	 * This method is used to display reasons.
	 * 
	 * @return
	 */

	public List<Reason> getReasonList() {
		return reasonRepository.findReasonList();
	}

	/**
	 * This method is used to remove reasons.
	 * 
	 * @param studentRollNumber
	 * @param reasonDate
	 * @throws InValidCredentialsException
	 */

	public void removeReason(String studentRollNumber, LocalDate reasonDate) throws InValidCredentialsException {
		int isRemoveReason = 0;
		try {
			isRemoveReason = reasonRepository.deleteReason(studentRollNumber, reasonDate);
			if (isRemoveReason > 0) {
				message.setInfoMessage("SUCESSFULLY REMOVED");
			} else {
				throw new InValidCredentialsException("REASON DETAIL NOT FOUND");
			}

		} catch (Exception e) {
			throw new InValidCredentialsException("REASON DETAIL NOT FOUND");
		}
	}
}
