package hm.model;

import java.io.Serializable;

public class YoutubeItemId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String kind;
	private String videoId;
	private String fullUrl;

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	public String getFullUrl() {
		
		if(videoId == null)
			return null;
		
		return "https://www.youtube.com/watch?v="+videoId;
	}
	
	public void setFullUrl(String fullUrl) {
		this.fullUrl = fullUrl;
	}

}
