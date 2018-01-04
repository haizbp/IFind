package hm.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import hm.model.Key;
import hm.model.Link;

@Repository
public interface KeyRepository extends MongoRepository<Key, ObjectId> {

	Key findByKey(String key);
	
}
