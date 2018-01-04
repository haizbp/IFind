package hm.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import hm.model.LinkProcessing;

@Repository
public interface LinkProcessingRepository
		extends MongoRepository<LinkProcessing, ObjectId>, LinkProcessingRepositoryCustom {

	LinkProcessing findByUrl(String url);

}
