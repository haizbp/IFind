package hm.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "linkProcessing")
public class LinkProcessing implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private ObjectId id;
	private String url;
	private Date processingTime = new Date();
	private boolean isProcessing;

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getProcessingTime() {
		return processingTime;
	}

	public void setProcessingTime(Date processingTime) {
		this.processingTime = processingTime;
	}

	public boolean isProcessing() {
		return isProcessing;
	}

	public void setProcessing(boolean isProcessing) {
		this.isProcessing = isProcessing;
	}

}
