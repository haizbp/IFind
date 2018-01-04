package hm.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import hm.model.Link;

@Repository
public interface LinkRepository extends MongoRepository<Link, ObjectId> {

	Link findByUrl(String url);
	
}
