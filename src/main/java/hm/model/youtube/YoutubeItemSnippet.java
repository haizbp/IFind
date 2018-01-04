package hm.model.youtube;

import java.io.Serializable;
import java.sql.Timestamp;

public class YoutubeItemSnippet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Timestamp publishedAt;
	private String channelId;
	private String title;
	private String description;
	private String channelTitle;
	private String liveBroadcastContent;
	private YoutubeItemSnippetThumbnails thumbnails;

	public Timestamp getPublishedAt() {
		return publishedAt;
	}

	public void setPublishedAt(Timestamp publishedAt) {
		this.publishedAt = publishedAt;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getChannelTitle() {
		return channelTitle;
	}

	public void setChannelTitle(String channelTitle) {
		this.channelTitle = channelTitle;
	}

	public String getLiveBroadcastContent() {
		return liveBroadcastContent;
	}

	public void setLiveBroadcastContent(String liveBroadcastContent) {
		this.liveBroadcastContent = liveBroadcastContent;
	}

	public YoutubeItemSnippetThumbnails getThumbnails() {
		return thumbnails;
	}

	public void setThumbnails(YoutubeItemSnippetThumbnails thumbnails) {
		this.thumbnails = thumbnails;
	}

}
