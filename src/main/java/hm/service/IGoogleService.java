package hm.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IGoogleService {

	String[] translate(String source, String lang, String sessionId) throws Exception;
	List<Map<String, String>> search(String key, int page) throws IOException;
	
}
