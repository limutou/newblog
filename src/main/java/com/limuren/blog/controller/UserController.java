package com.limuren.blog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.limuren.blog.common.Const;
import com.limuren.blog.common.ResponseCode;
import com.limuren.blog.common.ServerResponse;
import com.limuren.blog.pojo.User;
import com.limuren.blog.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("register.do")
	public ServerResponse<String> register(User user){
		//防止恶意攻击
		user.setUserid(null);
		//防止越权
		user.setRole(null);
		//防止恶意操作
		user.setStatus(null);
		
		user.setCreatetime(null);
		
		user.setUpdatetime(null);
		
		return userService.register(user);
	}
	
	@PostMapping("login.do")
	public ServerResponse<String> login(String username, String password,HttpSession session){
		ServerResponse response = userService.login(username, password);
		if(response.isSuccess()) {
            session.setAttribute(Const.CURRENT_USER,response.getData());
		}
		return response;
	}
	@GetMapping("dislogin.do")
	public ServerResponse dislogin(HttpSession session) {
		
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if(currentUser==null) return ServerResponse.createByErrorMessage("您还没有登录");
        
        session.removeAttribute(Const.CURRENT_USER);
		return ServerResponse.createBySuccessMessage("退出登录成功");
	}
	@PostMapping("resetPassword.do")
	public ServerResponse<String> resetPassword(HttpSession session,String passwordOld,String passwordNew){
		
		User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
		if (currentUser == null) {
			return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登陆后再继续操作");
		}
		
		return userService.resetPassword(currentUser.getUserid(), passwordOld, passwordNew);
	}
	@GetMapping("getInformation.do")
    public ServerResponse<User> getInformation(Integer userId,HttpSession session){
		if(userId!=null) {
			return userService.getInformation(userId);
		}
		User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
		if (currentUser == null) {
			return ServerResponse.createByErrorMessage("未指定要查询信息的用户");
		}
		return userService.getInformation(currentUser.getUserid());
	}
	@PostMapping("updateInformation.do")
    public ServerResponse<User> updateInformation(HttpSession session,User user){
		User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
		if (currentUser == null) {
			return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登陆后再继续操作");
		}
		user.setUserid(currentUser.getUserid());
		return userService.updateInformation(user);
	}
}
