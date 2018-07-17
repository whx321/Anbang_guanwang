package com.anbang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.anbang.dao.NewsDao;
import com.anbang.entity.News;

@Service
public class NewsService implements NewsDao {

	private News news;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public void saveNews(News news) {
		mongoTemplate.insert(news);
	}

	@Override
	public void updateNews(News news) {
		Query query = new Query(Criteria.where("title").is(news.getTitle()));
        Update update = new Update().set("date", news.getDate())
        							.set("imgList", news.getImgList())
        							.set("content", news.getContent());
        //更新查询返回结果集的第一条
        mongoTemplate.updateFirst(query,update,News.class);
        //更新查询返回结果集的所有
        // mongoTemplate.updateMulti(query,update,UserEntity.class);
	}

	@Override
	public void deleteNews(String title) {
		Query query = new Query(Criteria.where("title").is(title));
		mongoTemplate.remove(query, News.class);
	}

	@Override
	public News queryNewsByTitle(String title) {
		Query query = new Query(Criteria.where("title").is(title));
		news = mongoTemplate.findOne(query, News.class);
		return news;
	}

	@Override
	public List<News> queryNewsAll() {
		List<News> newsList = mongoTemplate.findAll(News.class);
		return newsList;
	}

	@Override
	public List<News> queryNewsListPaged(Integer page, Integer pageSize) {
		return null;
	}

}
