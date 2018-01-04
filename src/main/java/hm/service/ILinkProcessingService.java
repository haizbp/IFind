package hm.service;

import hm.model.LinkProcessing;

public interface ILinkProcessingService {

	void insert(String url) throws Exception;

	void update(String url) throws Exception;
	
	LinkProcessing getRandom() throws Exception;

	void update(LinkProcessing linkProcessing) throws Exception;
	
}
