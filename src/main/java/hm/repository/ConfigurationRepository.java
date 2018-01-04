package hm.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import hm.model.Configuration;
import hm.model.Link;

@Repository
public interface ConfigurationRepository extends MongoRepository<Configuration, ObjectId> {

	Configuration findByKey(String key);
	
}
