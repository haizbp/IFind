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

import hm.annotation.ApiVersion;
import hm.model.Response;
import hm.model.youtube.YoutubeResponse;
import hm.service.IYoutubeService;

@RestController
@RequestMapping("/youtube")
@ApiVersion(1)
public class YoutubeController {

	private Logger logger = LogManager.getLogger(YoutubeController.class);
	@Autowired
	private IYoutubeService youtubeService;
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public Response crawl(@RequestParam("key") String key,
			@RequestParam(value = "pageToken", required = false) String pageToken,
			HttpSession httpSession) {
		Response response = new Response();

		try {
			if (StringUtils.isEmpty(key)) {
				response.setSuccess(false);
			}

			if (response.isSuccess()) {
				String sessionId = httpSession.getId();
				YoutubeResponse target = youtubeService.search(key,pageToken);
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
