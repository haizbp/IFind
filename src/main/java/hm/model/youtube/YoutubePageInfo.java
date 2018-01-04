package hm.model.youtube;

import java.io.Serializable;

public class YoutubePageInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long totalResults;
	private long resultsPerPage;

	public long getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(long totalResults) {
		this.totalResults = totalResults;
	}

	public long getResultsPerPage() {
		return resultsPerPage;
	}

	public void setResultsPerPage(long resultsPerPage) {
		this.resultsPerPage = resultsPerPage;
	}

}
