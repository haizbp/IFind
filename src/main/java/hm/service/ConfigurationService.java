package hm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hm.model.Configuration;
import hm.repository.ConfigurationRepository;

@Service
public class ConfigurationService implements IConfigurationService {

	@Autowired
	private ConfigurationRepository configurationRepository;

	@Override
	public boolean save(String key, String value) {

		Configuration config = configurationRepository.findByKey(key);

		if (config == null)
			config = new Configuration();

		config.setKey(key);
		config.setValue(value);

		configurationRepository.save(config);

		return false;
	}

	@Override
	public Configuration get(String key) {

		Configuration config = configurationRepository.findByKey(key);

		if (config == null)
			config = new Configuration();

		return config;
	}

}
