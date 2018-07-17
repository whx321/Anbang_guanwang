package com.anbang.entity;

import java.io.Serializable;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="anbang_message")
public class Message implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Indexed
    private String nickname;
    private String telephone;
    private String email;
    private String content;
	
    public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getcontent() {
		return content;
	}
	public void setcontent(String content) {
		this.content = content;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public Message() {
		super();
	}
	public Message(String nickname, String telephone, String email, String content) {
		this.nickname = nickname;
		this.telephone = telephone;
		this.email = email;
		this.content = content;
	}
	
	@Override
	public String toString() {
		return "content={nickname:" + nickname + ", telephone:" + telephone + ", email:" + email + ", content:"
				+ content + "}";
	}
}
