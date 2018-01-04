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

import hm.Executing;
import hm.annotation.ApiVersion;
import hm.model.Response;
import hm.service.ICrawlerService;

@RestController
@RequestMapping("/crawl")
@ApiVersion(1)
public class CrawlerController {

	private Logger logger = LogManager.getLogger(CrawlerController.class);
	@Autowired
	private ICrawlerService crawlerService;

	@RequestMapping(method = RequestMethod.GET)
	public Response crawl(@RequestParam("url") String url) {
		Response response = new Response();

		try {
			if (StringUtils.isEmpty(url)) {
				response.setSuccess(false);
			}

			if (response.isSuccess()) {
				Executing.add(new Runnable() {

					@Override
					public void run() {
						try {
							logger.info("Start crawling: " + url);
							crawlerService.crawl(url);
							logger.info("End crawling: " + url);
						} catch (Exception e) {
							logger.error("Throw: ", e);
						}
					}
				});
			} else {
				throw new Exception("[Parameter Error]_ Url: " + url);
			}
		} catch (Exception e) {
			response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			logger.error("Throw: ", e);
		}

		return response;
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public Response crawlPage(@RequestParam("url") String url, @RequestParam("from") int from,
			@RequestParam("to") int to) {
		Response response = new Response();

		try {
			if (StringUtils.isEmpty(url)) {
				response.setSuccess(false);
			}

			if (from < 0) {
				response.setSuccess(false);
			}

			if (to < 0) {
				response.setSuccess(false);
			}

			if (to < from) {
				response.setSuccess(false);
			}

			if (response.isSuccess()) {
				Executing.add(new Runnable() {

					@Override
					public void run() {
						try {
							logger.info("Start crawling: " + url);
							crawlerService.crawl(url, from, to);
							logger.info("End crawling: " + url);
						} catch (Exception e) {
							response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
							logger.error("Throw: ", e);
						}
					}
				});
			} else {
				throw new Exception("[Parameter Error]_ Url: " + url + ", page [" + from + "," + to + "]");
			}
		} catch (Exception e) {
			response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			logger.error("Throw: ", e);
		}

		return response;
	}
}
