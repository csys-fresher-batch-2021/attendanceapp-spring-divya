/**
 * 
 */
package in.divya.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.divya.dao.ReportRepository;
import in.divya.model.Faculty;
import in.divya.model.Report;

/**
 * @author divy2624
 *
 */
@Service
public class ReportService {

	@Autowired
	ReportRepository reportRepository;

	/**
	 * This method is used tho display the student incharge details.
	 * 
	 * @param facultyId
	 * @return
	 */

	public List<Faculty> getFacultyInformation(String facultyId) {
		return reportRepository.findFacultyDetails(facultyId);
	}

	/**
	 * This method is used to display the absent details.
	 * 
	 * @param facultyId
	 * @return
	 */

	public List<Report> getAbsentReason(String facultyId) {
		return reportRepository.findAbsentReasonDetails(facultyId);
	}

	/**
	 * This method is used to display the onduty details.
	 * 
	 * @param facultyId
	 * @return
	 */

	public List<Report> getOnDutyReason(String facultyId) {
		return reportRepository.findOnDutyReasonDetails(facultyId);
	}
}
