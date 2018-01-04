package hm.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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

	@Override
	public String[] translate(String source, String lang, String sessionId) throws Exception {

		String[] target = new String[2];

		try {

//			DesiredCapabilities caps = DesiredCapabilities.phantomjs();
//			// caps.setJavascriptEnabled(true);
//			// caps.setBrowserName("phantomjs");
//			WebDriver driver = new RemoteWebDriver(new URL(seleniumServer), caps);
//
//			driver.get(String.format(googleTranslatorUrl, Helper.now(Helper.DEFAULT_FORMAT_DATE_WITH_MILISECOND),
//					sessionId, lang, source));
//
//			WebElement resultElement = driver.findElement(By.id("result_box"));
//			resultElement = resultElement.findElement(By.tagName("span"));
//
//			target[0] = source;
//			target[1] = resultElement.getText();
//
//			logger.info("Source: " + target[0] + " , target: " + target[1]);

		} catch (Exception e) {
			logger.error("Throw: ", e);
			throw e;
		}

		return target;
	}

	@Override
	public List<Map<String, String>> search(String key, int page) throws IOException {
		Document document = Jsoup
				.connect(String.format(googleSearchUrl, key, googleSearchTotal, (page - 1) * googleSearchTotal))
				.userAgent("Mozilla/5.0").get();
		List<Map<String, String>> gresults = new ArrayList<Map<String, String>>(50);

		Elements els = document.select("div.g");

		for (Element el : els) {
			Map<String, String> map = new HashMap<String, String>();

			map.put("title", el.getElementsByTag("h3").text());
			map.put("site", el.getElementsByTag("cite").text());
			map.put("abstract", el.getElementsByTag("span").text());
			gresults.add(map);

		}

		return gresults;
	}

}
