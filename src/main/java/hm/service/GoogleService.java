package hm.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import hm.Executing;
import hm.model.Key;
import hm.model.Link;
import hm.repository.KeyRepository;
import hm.repository.LinkRepository;

@Service
public class GoogleService implements IGoogleService {

	private Logger logger = LogManager.getLogger(GoogleService.class);
	@Value("${configuration.googleTranslatorUrl}")
	private String googleTranslatorUrl;
	@Value("${configuration.googleSearchUrl}")
	private String googleSearchUrl;
	@Value("${configuration.googleSearchUrl.total}")
	private int googleSearchTotal;
	@Value("${configuration.selenium.server}")
	private String seleniumServer;

	@Autowired
	private LinkRepository linkRepository;
	@Autowired
	private KeyRepository keyRepository;

	@Override
	public String[] translate(String source, String lang, String sessionId) throws Exception {

		String[] target = new String[2];

		try {

			// DesiredCapabilities caps = DesiredCapabilities.phantomjs();
			// // caps.setJavascriptEnabled(true);
			// // caps.setBrowserName("phantomjs");
			// WebDriver driver = new RemoteWebDriver(new URL(seleniumServer), caps);
			//
			// driver.get(String.format(googleTranslatorUrl,
			// Helper.now(Helper.DEFAULT_FORMAT_DATE_WITH_MILISECOND),
			// sessionId, lang, source));
			//
			// WebElement resultElement = driver.findElement(By.id("result_box"));
			// resultElement = resultElement.findElement(By.tagName("span"));
			//
			// target[0] = source;
			// target[1] = resultElement.getText();
			//
			// logger.info("Source: " + target[0] + " , target: " + target[1]);

		} catch (Exception e) {
			logger.error("Throw: ", e);
			throw e;
		}

		return target;
	}

	@Override
	public List<Map<String, String>> search(String key, int page) throws IOException {
		
		Executing.add(new Runnable() {
			
			@Override
			public void run() {
				keyRepository.save(new Key(key));
			}
		});
		
		Document document = Jsoup
				.connect(String.format(googleSearchUrl, key, googleSearchTotal, (page - 1) * googleSearchTotal))
				.userAgent("Mozilla/5.0").get();
		List<Map<String, String>> gresults = new ArrayList<Map<String, String>>(50);

		Elements els = document.select("div.g");
		Link link;
		Map<String, String> map;
		String url;
		String title;
		String description;
		for (Element el : els) {
			url = el.getElementsByTag("cite").text();
			title = el.getElementsByTag("h3").text();
			description = el.getElementsByTag("span").text();

			if (!StringUtils.isEmpty(url)) {
				map = new HashMap<String, String>();

				map.put("title", title);
				map.put("url", url);
				map.put("description", description);
				gresults.add(map);
				
				link = new Link(url, title, description);
				linkRepository.save(link);
			}

		}

		return gresults;
	}

}
