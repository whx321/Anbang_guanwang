package com.anbang.introller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anbang.dao.AdminDao;
import com.anbang.entity.Admin;
import com.anbang.util.CommonVO;

@CrossOrigin
@RestController
@RequestMapping("/anbang")
public class AdminController {

	@Autowired
    private AdminDao adminDao;
	
	@RequestMapping("/adminlogin")
    public CommonVO login(@RequestParam("account") String account,
			@RequestParam("password") String password, HttpSession session) {
//    	String sessionId = session.getId();
    	Admin admin = adminDao.queryAdmin(account, password);
    	session.setAttribute("admin", admin);
    	CommonVO cvo = new CommonVO();
    	cvo.setSuccess(true);
    	cvo.setMsg("管理员登录");
    	cvo.setData(admin);
    	return cvo;
    }
	
	@RequestMapping("/adminlogout")
	public String logout(HttpSession session) {
		session.removeAttribute("admin");
		if (session.getAttribute("admin") == null) {
			return "success";
		}
		return "fail";
	}
    
}
