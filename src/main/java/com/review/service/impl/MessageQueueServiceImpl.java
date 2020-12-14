/**
 * 
 */
package com.review.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.review.constant.SystemConstant;
import com.review.model.MessageQueue;
import com.review.repository.MessageQueueRepository;
import com.review.service.CounterService;
import com.review.service.MessageQueueService;

/**
 * @author ddung
 *
 */
@Service
public class MessageQueueServiceImpl implements MessageQueueService {

	@Autowired
	private MessageQueueRepository messageQueueRepositoryImpl;
	@Autowired
	private CounterService counterService;

	@Override
	public MessageQueue findById(long messageQueueId) {
		return messageQueueRepositoryImpl.findById(messageQueueId);
	}

	@Override
	public List<MessageQueue> findMessageQueues(Integer state) {
		return messageQueueRepositoryImpl.findMessageQueues(state);
	}

	@Override
	public MessageQueue createMessageQueue(String className, Long classPK, String typeQueue, String payload) {
		MessageQueue messageQueue = new MessageQueue();

		long messageQueueId = counterService.increment(MessageQueue.class.getName());

		Date now = new Date();
		messageQueue.setMessageQueueId(messageQueueId);
		messageQueue.setClassName(className);
		messageQueue.setClassPK(classPK);
		messageQueue.setTypeQueue(typeQueue);
		messageQueue.setPayload(payload);
		messageQueue.setState(SystemConstant.PENDING);
		messageQueue.setRetry(0);
		messageQueue.setCreateDate(now);
		messageQueue.setModifiedDate(now);

		return messageQueueRepositoryImpl.createMessageQueue(messageQueue);
	}

	@Override
	public MessageQueue updateMessageQueue(MessageQueue messageQueue) {
		return messageQueueRepositoryImpl.updateMessageQueue(messageQueue);
	}

}
