package com.anbang.entity;

import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.data.mongodb.core.index.Indexed;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class News implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Indexed
    private String title;
	private String date;
	
	@JsonInclude(Include.NON_NULL)
    private ArrayList<String> imgList;
	@JsonInclude(Include.NON_NULL)
	private String content;
	
	public News() {
	}
	public News(String title, String date, ArrayList<String> imgList, String content) {
		this.title = title;
		this.date = date;
		this.imgList = imgList;
		this.content = content;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public ArrayList<String> getImgList() {
		return imgList;
	}
	public void setImgList(ArrayList<String> imgList) {
		this.imgList = imgList;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "News={titel:" + title + ", date:" + date 
				+ ", imgList:" + imgList + ", content:" 
				+ content + "}";
	}
}
