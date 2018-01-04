package hm.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import hm.Helper;
import hm.model.Image;
import hm.model.Link;
import hm.repository.LinkRepository;

@Service
public class CrawlerService implements ICrawlerService {

	private Logger logger = LogManager.getLogger(CrawlerService.class);

	@Autowired
	private LinkRepository linkRepository;
	@Value("${ignoreLink}")
	private String ignoreLink;

	@Override
	public void crawl(String url) throws IOException {

		try {

			
			Document document = Jsoup.connect(url).userAgent("Mozilla").get();
			Elements linksOnPage = document.select("a[href]");

			Link link;
			Image image;
			List<Image> images;
			String absHref;
			Document sub;
			Elements es;
			Elements img;

			for (Element page : linksOnPage) {
				try {
					absHref = page.absUrl("href");

					if (StringUtils.isEmpty(absHref))
						continue;

					if (!absHref.startsWith("http"))
						continue;

					if (!isUrlPassed(absHref))
						continue;
					
					if(linkRepository.findByUrl(Helper.removeProtocal(absHref)) != null)
						continue;

					logger.info("Processiong: " + absHref);

					sub = Jsoup.connect(absHref).userAgent("Mozilla").get();

					link = new Link(Helper.removeProtocal(absHref), sub.title());

					es = sub.select("meta[name=keywords]");
					if (es.size() > 0)
						link.setKeywords(es.first().attr("content"));

					es = sub.select("meta[name=description]");
					if (es.size() > 0)
						link.setDescription(es.first().attr("content"));

					img = sub.getElementsByTag("img");
					images = new ArrayList<>();

					for (Element el : img) {

						if (!StringUtils.isEmpty(el.attr("src"))) {
							image = new Image();
							image.setUrl(el.attr("src"));
							image.setAlt(el.attr("alt"));

							images.add(image);

							logger.info("image tag: " + el.attr("src") + " Alt: " + el.attr("alt"));
						}

					}

					link.setImages(images);
					linkRepository.save(link);

					logger.info("Done: " + absHref);
				} catch (Exception e) {
					logger.error("Throw: ", e);
				}
			}

		} catch (IOException e) {
			logger.error("Throw: ", e);
			throw e;
		}

	}

	private boolean isUrlPassed(String url) {
		boolean result = true;

		String[] urls = ignoreLink.split(",");

		for (String s : urls) {
			if ((url != null) && (url.contains(s.trim()))) {
				result = false;
				break;
			}
		}

		return result;
	}

	@Override
	public void crawl(String url, int from, int to) throws Exception {

		while (from <= to) {

			crawl(String.format(url.replaceAll("_page_", String.valueOf(from))));

			from++;
		}

	}

	@Override
	public List<Link> get(int offset, int length) throws Exception {
		List<Link> lists = linkRepository.findAll(new PageRequest(offset, length)).getContent();
		return lists;
	}

	@Override
	public long totalRecord() {
		return linkRepository.count();
	}

}
