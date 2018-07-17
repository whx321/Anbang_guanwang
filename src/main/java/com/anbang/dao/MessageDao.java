package com.anbang.dao;

import org.springframework.stereotype.Repository;

import com.anbang.entity.Message;

@Repository
public interface MessageDao {

	public void saveMessage(Message message);
}
