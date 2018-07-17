package com.anbang.introller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anbang.dao.NewsDao;
import com.anbang.entity.News;
import com.anbang.util.CommonVO;
import com.qiniu.util.Auth;

@CrossOrigin
@RestController
@RequestMapping("/anbang")
public class NewsController {

	private static Logger logger = LoggerFactory.getLogger(NewsController.class);
	
	@Autowired
    private NewsDao newsDao;
	
	@RequestMapping("/getnews")
	public CommonVO getNews(@RequestParam(name="title") String title, HttpServletRequest request, HttpSession session) {
		News news = new News();
		news = newsDao.queryNewsByTitle(title);
		CommonVO cvo =new CommonVO();
		cvo.setSuccess(true);
		cvo.setMsg("通过标题获取新闻资讯");
		cvo.setData(news);
		if(request.getSession().getId().equals(session.getAttribute("admin"))) {
			return cvo;
		} else {
			return null;
		}
	}
	
	@RequestMapping("/getnewsnist")
	public CommonVO getNewsList(HttpSession session) {
		CommonVO cvo = new CommonVO();
		List<News> newsList = newsDao.queryNewsAll();
		cvo.setSuccess(true);
		cvo.setData(newsList);
		cvo.setMsg("获取新闻资源列表");
		if(session.getAttribute("admin") != null) {
			return cvo;
		} else {
			return null;
		}
	}
	
	@RequestMapping("savenews")
	public void saveNews(@RequestParam(name="titles") String title, @RequestParam(name="dates") String date, 
			@RequestParam(name="files") ArrayList<String> imgList, @RequestParam(name="texts") String content, HttpServletRequest request, HttpSession session) {
		
		News news = new News();
		news.setTitle(title);
		news.setDate(date);
		news.setImgList(imgList);
		news.setContent(content);
		if(request.getSession().getId().equals(session.getAttribute("admin"))) {
			newsDao.saveNews(news);
		}
	}
	
	@RequestMapping("/updatenews")
	public void updateNews(@RequestParam(name="title") String title, @RequestParam(name="date") String date,
			@RequestParam(name="imgUrl") ArrayList<String> imgList, @RequestParam(name="content") String content, HttpServletRequest request, HttpSession session) {
		News news = new News();
		news = newsDao.queryNewsByTitle(title);
		news.setDate(date);
		news.setImgList(imgList);
		news.setContent(content);
		if(request.getSession().getId().equals(session.getAttribute("admin"))) {
			newsDao.updateNews(news);
		}
	}
	
	@RequestMapping("/updatenewstitle")
	public void updateNewsTitle(@RequestParam(name="oldTitle") String oldTitle, 
			@RequestParam(name="newTitle") String newTitle, HttpServletRequest request, HttpSession session) {
		News news = new News();
		news = newsDao.queryNewsByTitle(oldTitle);
		news.setTitle(newTitle);
		if(session.getAttribute("admin") != null) {
			newsDao.updateNews(news);
		}
	}
	
	/**邮件图片上传，获取七牛云token
	 * **/
	@RequestMapping("/gettoken")
	public CommonVO uptoken(String accessKey, String secretKey, String bucket, HttpServletRequest request, HttpSession session) {
		CommonVO cvo = new CommonVO();
//		String accessKey = "qQj7mRKyvE7dOOjObMC8W58i6Yn3penfr7-_fg4d";
//		String secretKey = "9f70kmAddF1maP1U0jy0vRNAhwWNv_huR1xDSH_s";
//		String bucket = "anbang";
		logger.info("密钥:"+accessKey+secretKey+bucket);
		Auth auth = Auth.create(accessKey, secretKey);
		String uptoken = auth.uploadToken(bucket);
		cvo.setSuccess(true);
		cvo.setData(uptoken);
		cvo.setMsg("Obtaintoken");
		if(session.getAttribute("admin") != null) {
			return cvo;
		} else {
			return null;
		}
	}
}

