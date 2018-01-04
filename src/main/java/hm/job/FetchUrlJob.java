package hm.job;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LogLevel;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import hm.Executing;
import hm.model.Link;
import hm.model.LinkProcessing;
import hm.service.ConfigurationService;
import hm.service.CrawlerService;
import hm.service.LinkProcessingService;

@Component
public class FetchUrlJob {

	private Logger logger = LogManager.getLogger(FetchUrlJob.class);

	@Autowired
	private CrawlerService crawlerService;
	@Autowired
	private ConfigurationService configurationService;
	@Autowired
	private LinkProcessingService linkProcessingService;
	private LinkProcessing linkProcessing; 

	// @Scheduled(cron = "${configuration.cron.task}")
//	@Scheduled(fixedRate = 1000)
	public void doFetch() {
		try {
			linkProcessing = linkProcessingService.getRandom();

			if (linkProcessing != null) {
				Executing.add(new Runnable() {
					@Override
					public void run() {
						LinkProcessing lnkProcessing = linkProcessing;
						try {
							logger.info("Cron Job begin: " + lnkProcessing.getUrl());

							linkProcessing.setProcessing(true);
							linkProcessingService.update(lnkProcessing);

							crawlerService.crawl(lnkProcessing.getUrl());
							logger.info("Cron Job end: " + lnkProcessing.getUrl());
						} catch (Exception e) {
							logger.error("Throw: ", e);
						}finally {
							try {
								lnkProcessing.setProcessing(false);
								linkProcessingService.update(lnkProcessing);
							} catch (Exception e) {
								logger.error("Throw: ", e);
							}
						}
						
					}

				});
			}
		} catch (Exception e) {
			logger.error("Throw: ", e);
		}
	}

}
