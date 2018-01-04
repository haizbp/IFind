package hm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/")
@Controller
public class HomeController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/submit", method = RequestMethod.GET)
	public String submit() {
		return "submit";
	}

	@RequestMapping(value = "/configuration", method = RequestMethod.GET)
	public String configuration() {
		return "configuration";
	}

}
