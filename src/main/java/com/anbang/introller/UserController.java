package com.anbang.introller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anbang.dao.UserDao;
import com.anbang.entity.User;
import com.anbang.util.CommonVO;

@CrossOrigin
@RestController
@RequestMapping("/anbang")
public class UserController {

	@Autowired
    private UserDao userDao;
	
	private User user;
	
	//index页面
    @RequestMapping("/login")
    public String index() {
        return "login";
    }
	
	@RequestMapping("/getUser")
	public CommonVO getUserJson(HttpServletRequest request) {
		String username = request.getParameter("username");
		user = userDao.queryUserByUsername(username);
		CommonVO cvo = new CommonVO();
		cvo.setSuccess(true);
		cvo.setMsg("获取用户信息成功");
		cvo.setData(user);
		
		return cvo;
	}
}
