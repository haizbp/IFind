package hm.service;

import java.util.List;
import java.util.Map;

import hm.model.YoutubeResponse;

public interface IYoutubeService {

	YoutubeResponse search(String key, String pageToken);
	
}
