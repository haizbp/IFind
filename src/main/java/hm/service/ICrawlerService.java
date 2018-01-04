package hm.service;

import java.util.List;
import java.util.Set;

import hm.model.Link;

public interface ICrawlerService {

	void crawl(String url) throws Exception;
	
	void crawl(String url, int from, int to) throws Exception;
	
	List<Link> get(int offset, int length) throws Exception;
	
	long totalRecord();
	
//	List<Link> get(String url) throws Exception;

}
