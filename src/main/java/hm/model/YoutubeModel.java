package hm.model;

import java.io.Serializable;
import java.util.List;

public class YoutubeModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String kind;
	private String etag;
	private String nextPageToken;
	private String prevPageToken;
	private String regionCode;
	private YoutubePageInfo pageInfo;
	private List<YoutubeItem> items;

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getEtag() {
		return etag;
	}

	public void setEtag(String etag) {
		this.etag = etag;
	}

	public String getNextPageToken() {
		return nextPageToken;
	}

	public void setNextPageToken(String nextPageToken) {
		this.nextPageToken = nextPageToken;
	}

	public String getPrevPageToken() {
		return prevPageToken;
	}

	public void setPrevPageToken(String prevPageToken) {
		this.prevPageToken = prevPageToken;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public YoutubePageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(YoutubePageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public List<YoutubeItem> getItems() {
		return items;
	}

	public void setItems(List<YoutubeItem> items) {
		this.items = items;
	}

}
