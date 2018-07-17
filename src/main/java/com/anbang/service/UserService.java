package com.anbang.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.anbang.dao.UserDao;
import com.anbang.entity.User;

@Service
public class UserService implements UserDao{

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void saveUser(User user) {
		mongoTemplate.insert(user);
	}

	@Override
	public void updateUser(User user) {
		Query query=new Query(Criteria.where("username").is(user.getUsername()));
        Update update= new Update().set("password", user.getPassword());
        //更新查询返回结果集的第一条
        mongoTemplate.updateFirst(query,update,User.class);
        //更新查询返回结果集的所有
        // mongoTemplate.updateMulti(query,update,UserEntity.class);
	}

	@Override
	public void deleteUser(String username) {
		Query query = new Query(Criteria.where("username").is(username));
		mongoTemplate.remove(query, User.class);
	}

	@Override
	public User queryUserByUsername(String username) {
		Query query = new Query(Criteria.where("username").is(username));
		User user = mongoTemplate.findOne(query, User.class);
		return user;
	}
	
}
