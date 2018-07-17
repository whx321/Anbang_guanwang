package com.anbang.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.anbang.dao.AdminDao;
import com.anbang.entity.Admin;
import com.anbang.entity.User;

@Service
public class AdminService implements AdminDao {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void addAdmin(Admin admin) {
		mongoTemplate.insert(admin);
	}

	@Override
	public void updateAdmin(Admin admin) {
		Query query=new Query(Criteria.where("account").is(admin.getAccount()));
        Update update= new Update().set("password", admin.getPassword());
        //更新查询返回结果集的第一条
        mongoTemplate.updateFirst(query,update, Admin.class);
        //更新查询返回结果集的所有
        // mongoTemplate.updateMulti(query,update,UserEntity.class);
	}

	@Override
	public void deleteAdmin(String account) {
		Query query = new Query(Criteria.where("account").is(account));
		mongoTemplate.remove(query, User.class);
	}

	@Override
	public Admin queryAdmin(String account, String password) {
		Query query = new Query();
		Criteria criteria = new Criteria();
		criteria.and("account").is(account);
		criteria.and("password").is(password);
		query.addCriteria(criteria);
		
		return mongoTemplate.findOne(query, Admin.class);
	}

}
