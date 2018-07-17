package com.anbang.introller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anbang.dao.MessageDao;
import com.anbang.entity.Message;

@CrossOrigin
@RestController
@RequestMapping("/anbang")
public class MessageController {

	@Autowired
    private MessageDao messageDao;
	
	private Message message;
	
	//index页面
    @RequestMapping("/message")
    public String index() {
        return "message";
    }
	
	@RequestMapping("/saveMessage")
	public void getMessage(@RequestParam(name="nickname") String nickname, @RequestParam(name="telephone") String telephone, 
			@RequestParam(name="email") String email, @RequestParam(name="content") String content) {
		if(nickname!=null && telephone!=null && email!=null && content!=null) {
			message = new Message(nickname, telephone, email, content);
			messageDao.saveMessage(message);
		}
	}
}
