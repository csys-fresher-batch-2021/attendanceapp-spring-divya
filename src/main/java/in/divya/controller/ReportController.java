/**
 * 
 */
package in.divya.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import in.divya.model.Faculty;
import in.divya.model.Report;
import in.divya.service.ReportService;

/**
 * @author divy2624
 *
 */
@RestController
public class ReportController {
	@Autowired
	ReportService reportService;

	/**
	 * This metod is used to deiplay the studentIncharge details.
	 * 
	 * @param facultyId
	 * @return
	 */

	@GetMapping("getInchargeReport/{id}")
	public List<Faculty> facultyInformation(@PathVariable("id") String facultyId) {
		return reportService.getFacultyInformation(facultyId);
	}

	/**
	 * This method is used to display absent report.
	 * 
	 * @param facultyId
	 * @return
	 */

	@GetMapping("getReasonAbsent/{id}")
	public List<Report> absentReason(@PathVariable("id") String facultyId) {
		return reportService.getAbsentReason(facultyId);
	}

	/**
	 * This method is used to display the onduty report.
	 * 
	 * @param facultyId
	 * @return
	 */

	@GetMapping("getReasonOnDuty/{id}")
	public List<Report> onDutyReason(@PathVariable("id") String facultyId) {
		return reportService.getOnDutyReason(facultyId);
	}
}
