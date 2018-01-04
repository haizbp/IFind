package hm.repository;

import hm.model.LinkProcessing;

public interface LinkProcessingRepositoryCustom {

	LinkProcessing getRandom();
	
	void resetProcessing();
	
}
