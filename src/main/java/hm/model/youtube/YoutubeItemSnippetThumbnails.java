package hm.model.youtube;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class YoutubeItemSnippetThumbnails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty(value = "default")
	private Thumbnail defaulte;
	private Thumbnail medium;
	private Thumbnail high;

	public Thumbnail getDefault() {
		return defaulte;
	}

	public void setDefault(Thumbnail defaulte) {
		this.defaulte = defaulte;
	}

	public Thumbnail getMedium() {
		return medium;
	}

	public void setMedium(Thumbnail medium) {
		this.medium = medium;
	}

	public Thumbnail getHigh() {
		return high;
	}

	public void setHigh(Thumbnail high) {
		this.high = high;
	}

}
