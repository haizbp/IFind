package hm.service;

import hm.model.Configuration;

public interface IConfigurationService {

	boolean save(String key, String value);
	
	Configuration get(String key);
}
