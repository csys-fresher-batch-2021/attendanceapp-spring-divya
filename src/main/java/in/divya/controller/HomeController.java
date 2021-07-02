package in.divya.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	/**
	 * This method is used to access the index page.
	 * 
	 * @return
	 */

	@GetMapping
	public String index() {
		return "index.jsp";

	}

}
