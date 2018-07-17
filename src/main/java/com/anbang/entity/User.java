package com.anbang.entity;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="anbang_user")
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Indexed
	private Long userId;

	private String username;
    private String password;
    private List<String> roles;
    
    public Long getUserId() {
    	return userId;
    }
    public void setUserId(Long userId) {
    	this.userId = userId;
    }
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
	public User() {
	}
	public User(Long userId, String username, String password, List<String> roles) {
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.roles = roles;
	}
}
