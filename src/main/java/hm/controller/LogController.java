package hm.controller;

import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hm.service.ILogService;

@RestController
@RequestMapping("/log")
public class LogController {

	@Autowired
	private ILogService iLogService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String getLog() throws FileNotFoundException {

		return iLogService.getLog();
	}

}
