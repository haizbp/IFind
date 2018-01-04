package hm.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hm.annotation.ApiVersion;
import hm.model.Configuration;
import hm.model.Response;
import hm.service.IConfigurationService;

@RestController
@RequestMapping("/config")
@ApiVersion(1)
public class ConfigurationController {

	private Logger logger = LogManager.getLogger(ConfigurationController.class);

	@Autowired
	private IConfigurationService configurationService;

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public Response get(@RequestParam("key") String key) {
		Response response = new Response();

		try {
			if (StringUtils.isEmpty(key)) {
				response.setSuccess(false);
			}

			if (response.isSuccess()) {
				Configuration configuration = configurationService.get(key);

				response.addData("back", configuration);
			} else {
				throw new Exception("[Parameter Error]_ Param: " + key);
			}
		} catch (Exception e) {
			response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			logger.log(Level.ERROR, e);
			logger.error("Throw: ", e);
		}

		return response;
	}

	@RequestMapping(method = RequestMethod.GET)
	public Response save(@RequestParam("key") String key, @RequestParam("value") String value) {
		Response response = new Response();

		try {
			if (StringUtils.isEmpty(key)) {
				response.setSuccess(false);
			}

			if (StringUtils.isEmpty(value)) {
				response.setSuccess(false);
			}

			if (response.isSuccess()) {
				configurationService.save(key, value);
			} else {
				throw new Exception("[Parameter Error]_ Param: " + key + " : " + value);
			}
		} catch (Exception e) {
			response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			logger.log(Level.ERROR, e);
			logger.error("Throw: ", e);
		}

		return response;
	}

}
