package hm;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class Helper {

	public static final String CRAWL_DEFAULT_FOLDER = "crawl";
	public static final String DEFAULT_FORMAT_DATE = "yyyy-MM-dd";
	public static final String DEFAULT_FORMAT_DATE_WITH_MILISECOND = "yyyy-MM-dd-HH-mm-ss-SSS";

	public static final String YOUTUBE_MAX_RESULT = "maxResults";
	public static final String YOUTUBE_KEY_PARAM = "key";
	public static final String YOUTUBE_KIND_PARAM = "youtube#video";
	public static final String YOUTUBE_PART_PARAM = "part";
	public static final String YOUTUBE_CHART_PARAM = "chart";
	public static final String YOUTUBE_REGION_PARAM = "regionCode";

	public static final String API_PATTERN = "v";

	public static String removeSpecialChar(String s) {

		if (s == null || "".equalsIgnoreCase(s)) {
			return "";
		}

		return s.replaceAll("[^\\w\\s]", "");
	}

	public static String removeProtocal(String url) {
		return url.replace("http:", "").replaceAll("https:", "");
	}

	private static ObjectMapper objectMapper = new ObjectMapper();
	private static RestTemplate restTemplate = new RestTemplate();

	public static String now(String pattern) {

		if (pattern == null || "".equalsIgnoreCase(pattern))
			pattern = DEFAULT_FORMAT_DATE;

		DateFormat dateFormat = new SimpleDateFormat(pattern);
		Date date = new Date();

		return dateFormat.format(date);

	}

	public static <T> T toObject(Object object, Class<T> clazz) {
		T t = null;

		try {
			String s = objectMapper.writeValueAsString(object);
			t = objectMapper.readValue(s, clazz);
		} catch (JsonProcessingException e) {
		} catch (IOException e) {
		}

		return t;
	}

	public static <T> T toObject(String json, Class<T> clazz) {
		T t = null;

		try {
			t = objectMapper.readValue(json, clazz);
		} catch (JsonProcessingException e) {
		} catch (IOException e) {
		}

		return t;
	}

	public static String doGet(Map<String, Object> param, String url) {
		String result;

		try {

			if (param != null) {
				url += "?";
				for (String key : param.keySet()) {
					url += key + "=" + param.get(key) + "&";
				}
			}

			result = restTemplate.getForObject(url, String.class);

		} catch (Exception e) {
			result = null;
		}

		return result;
	}

	public static <T> T doGet(Map<String, Object> param, String url, Class<T> clazz) {
		T result;

		try {

			if (param != null) {
				url += "?";
				for (String key : param.keySet()) {
					url += key + "=" + param.get(key) + "&";
				}
			}

			result = restTemplate.getForObject(url, clazz);

		} catch (Exception e) {
			result = null;
		}

		return result;
	}

}
