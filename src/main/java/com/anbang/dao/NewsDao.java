package com.anbang.dao;

import java.util.List;

import com.anbang.entity.News;

public interface NewsDao {

	public void saveNews(News news);

	public void updateNews(News news);

	public void deleteNews(String title);

	public News queryNewsByTitle(String title);
	
	public List<News> queryNewsAll();

	public List<News> queryNewsListPaged(Integer page, Integer pageSize);

}
