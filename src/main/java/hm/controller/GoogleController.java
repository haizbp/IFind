package hm.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hm.Executing;
import hm.annotation.ApiVersion;
import hm.model.Response;
import hm.service.IGoogleService;

@RestController
@RequestMapping("/google")
@ApiVersion(1)
public class GoogleController {

	private Logger logger = LogManager.getLogger(GoogleController.class);
	@Autowired
	private IGoogleService googleTranslator;

	@RequestMapping(value = "/translate", method = RequestMethod.GET)
	public Response crawl(@RequestParam("source") String source, @RequestParam("lang") String lang,
			HttpSession httpSession) {
		Response response = new Response();

		try {
			if (StringUtils.isEmpty(source)) {
				response.setSuccess(false);
			}

			if (StringUtils.isEmpty(lang)) {
				response.setSuccess(false);
			}

			if (response.isSuccess()) {
				String sessionId = httpSession.getId();
				String[] target = googleTranslator.translate(source, lang, sessionId);
				response.addData("back", target);
			} else {
				throw new Exception("[Parameter Error]_ lang: " + lang + ", source: " + source);
			}
		} catch (Exception e) {
			response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			logger.error("Throw: ", e);
		}

		return response;
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public Response crawl(@RequestParam("key") String key,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			HttpSession httpSession) {
		Response response = new Response();

		try {
			if (StringUtils.isEmpty(key)) {
				response.setSuccess(false);
			}

			if (response.isSuccess()) {
				String sessionId = httpSession.getId();
				List<Map<String, String>> target = googleTranslator.search(key,page);
				response.addData("back", target);
			} else {
				throw new Exception("[Parameter Error]_ key: " + key);
			}
		} catch (Exception e) {
			response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			logger.error("Throw: ", e);
		}

		return response;
	}

}
