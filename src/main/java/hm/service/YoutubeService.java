package hm.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import hm.Helper;
import hm.model.YoutubeItem;
import hm.model.YoutubeModel;
import hm.model.YoutubeResponse;

@Service
public class YoutubeService implements IYoutubeService {

	@Value("${configuration.google.api.key}")
	private String googleKey;

	@Value("${configuration.youtube.search.api}")
	private String youtubeSearchApi;

	@Value("${configuration.youtube.search.api.max-result}")
	private String youtubeSearchApiMaxResult;

	@Value("${configuration.youtube.search.api.part}")
	private String youtubeSearchApiPart;

	public static final String CHANNEL = "youtube#channel";
	public static final String VIDEO = "youtube#video";

	@Override
	public YoutubeResponse search(String key, String pageToken) {
		Map<String, Object> data = new HashMap<>();
		data.put(Helper.YOUTUBE_MAX_RESULT, youtubeSearchApiMaxResult);
		data.put(Helper.YOUTUBE_PART_PARAM, youtubeSearchApiPart);
		data.put(Helper.YOUTUBE_KEY_PARAM, googleKey);
		data.put("q", key);

		if (!StringUtils.isEmpty(pageToken))
			data.put("pageToken", pageToken);

		YoutubeModel model = Helper.doGet(data, youtubeSearchApi, YoutubeModel.class);

		YoutubeResponse response = new YoutubeResponse();
		if (model != null) {

			response.setNextToken(model.getNextPageToken());
			response.setPreviousToken(model.getPrevPageToken());

			Map<String, String> i;
			for (YoutubeItem item : model.getItems()) {
				i = new HashMap<>();
				i.put("channelId", item.getSnippet().getChannelId());
				i.put("channelTitle", item.getSnippet().getChannelTitle());
				i.put("description", item.getSnippet().getDescription());
				i.put("thumbnail", item.getSnippet().getThumbnails().getHigh().getUrl());
				i.put("type", item.getId().getKind());
				i.put("videoId", item.getId().getVideoId());
				i.put("videoUrl", item.getId().getFullUrl());
				
				response.add(i);
			}
		}

		return response;
	}

}
