package hm.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class YoutubeResponse implements Serializable {

	private String previousToken;
	private String nextToken;
	private List<Map<String, String>> items;

	public YoutubeResponse() {
		items = new ArrayList<Map<String, String>>();
	}

	public void add(Map<String, String> item) {
		if (item != null)
			items.add(item);
	}

	public String getPreviousToken() {
		return previousToken;
	}

	public void setPreviousToken(String previousToken) {
		this.previousToken = previousToken;
	}

	public String getNextToken() {
		return nextToken;
	}

	public void setNextToken(String nextToken) {
		this.nextToken = nextToken;
	}

	public List<Map<String, String>> getItems() {
		return items;
	}

	public void setItems(List<Map<String, String>> items) {
		this.items = items;
	}

}
