package com.anbang.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.anbang.dao.MessageDao;
import com.anbang.entity.Message;

@Service
public class MessageService implements MessageDao{

	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public void saveMessage(Message message)  {
		mongoTemplate.save(message);
	}
}
