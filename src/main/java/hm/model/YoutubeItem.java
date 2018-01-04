package hm.model;

import java.io.Serializable;

public class YoutubeItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String kind;
	private String etag;
	private YoutubeItemId id;
	private YoutubeItemSnippet snippet;

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

	public YoutubeItemId getId() {
		return id;
	}

	public void setId(YoutubeItemId id) {
		this.id = id;
	}

	public YoutubeItemSnippet getSnippet() {
		return snippet;
	}

	public void setSnippet(YoutubeItemSnippet snippet) {
		this.snippet = snippet;
	}

}
