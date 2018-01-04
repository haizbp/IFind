package hm.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hm.annotation.ApiVersion;
import hm.model.Response;
import hm.service.LinkProcessingService;

@RestController
@RequestMapping("/lnkProcessing")
@ApiVersion(1)
public class LinkProcessingController {

	private Logger logger = LogManager.getLogger(LinkProcessingController.class);
	@Autowired
	private LinkProcessingService linkProcessingService;

	@RequestMapping(method = RequestMethod.GET)
	public Response insert(@RequestParam("url") String url) {

		Response response = new Response();

		try {
			if (StringUtils.isEmpty(url)) {
				response.setSuccess(false);
			}

			if (response.isSuccess()) {
				linkProcessingService.insert(url);
			} else {
				throw new Exception("[Parameter Error]_ Url: " + url);
			}
		} catch (Exception e) {
			response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			logger.error("Throw: ", e);
		}

		return response;
	}

}
