package hm.model;

import java.io.Serializable;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "link")
public class Link implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Indexed
	private ObjectId id;
	private String url;
	@Indexed
	private String title;
	@Indexed
	private String keywords;
	@Indexed
	private String description;
	private long rate;
	private long level;
	private List<Image> images;

	public Link(String url, String title) {
		super();
		this.url = url;
		this.title = title;
	}
	
	public Link(String url, String title, String description) {
		super();
		this.url = url;
		this.title = title;
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getRate() {
		return rate;
	}

	public void setRate(long rate) {
		this.rate = rate;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getLevel() {
		return level;
	}

	public void setLevel(long level) {
		this.level = level;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	@Override
	public int hashCode() {
		return 1;
	}

	@Override
	public boolean equals(Object obj) {
		String url = "";
		String title = "";

		if (obj != null) {
			Link o = (Link) obj;
			url = o.getUrl();
			title = o.getTitle();
		}

		if (this.url.equalsIgnoreCase(url) && this.title.equalsIgnoreCase(title)) {
			return true;
		} else {
			return false;
		}
	}
}
