package hm.repository;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import hm.model.LinkProcessing;

public class LinkProcessingRepositoryImpl implements LinkProcessingRepositoryCustom {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public LinkProcessing getRandom() {
		LinkProcessing res = null;

		DB db = mongoTemplate.getDb();
		DBCollection linkProcessingColl = db.getCollection("linkProcessing");

		DBObject q1 = new BasicDBObject();
		DBObject param = new BasicDBObject();
		param.put("isProcessing", false);
		q1.put("$match", param);

		DBObject q2 = new BasicDBObject();
		q2.put("$sample", new BasicDBObject("size", 1));

		AggregationOutput ao = linkProcessingColl.aggregate(Arrays.asList(q1, q2));
		Iterable<DBObject> iter = ao.results();

		for (DBObject dbObject : iter) {
			res = mongoTemplate.getConverter().read(LinkProcessing.class, dbObject);
		}

		return res;
	}

	@Override
	public void resetProcessing() {
		DB db = mongoTemplate.getDb();
		DBCollection linkProcessingColl = db.getCollection("linkProcessing");

		BasicDBObject updateQuery = new BasicDBObject();
		updateQuery.append("$set", new BasicDBObject().append("isProcessing", false));

		linkProcessingColl.update(new BasicDBObject(), updateQuery);
	}

}
