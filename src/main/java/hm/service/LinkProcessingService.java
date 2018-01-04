package hm.service;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hm.model.LinkProcessing;
import hm.repository.LinkProcessingRepository;

@Service
public class LinkProcessingService implements ILinkProcessingService {

	@Autowired
	private LinkProcessingRepository queueRepository;

	@Override
	public void insert(String url) throws Exception {
		LinkProcessing queue = queueRepository.findByUrl(url);

		if (queue == null) {
			queue = new LinkProcessing();
			queue.setUrl(url);

			queueRepository.save(queue);
		}

	}

	@Override
	public void update(String url) throws Exception {
		LinkProcessing queue = queueRepository.findByUrl(url);

		if (queue != null) {
			queue.setUrl(url);
			queue.setProcessingTime(new Timestamp(new Date().getTime()));

			queueRepository.save(queue);
		}
	}
	
	@Override
	public void update(LinkProcessing linkProcessing) throws Exception {
		
		if(linkProcessing == null)
			return;
		
		queueRepository.save(linkProcessing);
	}

	@Override
	public LinkProcessing getRandom() throws Exception {
		LinkProcessing queue = queueRepository.getRandom();

		return queue;
	}

}
